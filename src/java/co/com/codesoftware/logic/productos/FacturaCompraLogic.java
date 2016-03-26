/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.logic.productos;

import co.com.codesoftware.persistence.entity.administracion.RespuestaEntity;
import co.com.codesoftware.persistence.entity.productos.FacturaCompraEntity;
import co.com.codesoftware.persistence.entity.productos.ImagenesFacCompraEntity;
import co.com.codesoftware.persistence.entity.productos.PagoFacCompraEntity;
import co.com.codesoftware.persistence.entity.productos.ProductoFacCompraEntity;
import co.com.codesoftware.persistencia.HibernateUtil;
import co.com.codesoftware.persistencia.ReadFunction;
import co.com.codesoftware.persistencia.utilities.DataType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;

/**
 *
 * @author john
 */
public class FacturaCompraLogic implements AutoCloseable {

    private Session sesion;
    private Transaction tx;

    /**
     * metodo que inserta la factura de compra
     *
     * @param factura
     * @param lista
     * @param productos
     * @return
     */
    public RespuestaEntity insertaFactura(FacturaCompraEntity factura, List<PagoFacCompraEntity> lista, List<ProductoFacCompraEntity> productos) {
        RespuestaEntity respuesta = new RespuestaEntity();
        factura.setId(selectMaxFactura());
        try {
            initOperation();
            factura.setRutaImagen("");
            sesion.save(factura);
            respuesta.setCodigoRespuesta(1);
            respuesta.setDescripcionRespuesta("OK");
            respuesta.setMensajeRespuesta("OK");
            close();
            if (insertaPagoFC(lista, factura.getId()).getCodigoRespuesta() == 1) {
                respuesta.setCodigoRespuesta(1);
                respuesta.setDescripcionRespuesta("OK PAGO");
                respuesta.setMensajeRespuesta("OK PAGO");
                if (insertaProductoFC(productos, factura.getId()).getCodigoRespuesta() == 1) {
                    String validaProc = this.ejecutaPreciosFacturaCompra(factura.getId());
                    if ("Ok".equalsIgnoreCase(validaProc)) {
                        respuesta.setCodigoRespuesta(1);
                        respuesta.setDescripcionRespuesta("OK PRODUCTOS");
                        respuesta.setMensajeRespuesta("OK PRODUCTOS");
                    }else{
                        respuesta.setCodigoRespuesta(0);
                        respuesta.setDescripcionRespuesta(validaProc);
                        respuesta.setMensajeRespuesta(validaProc);
                    }
                } else {
                    respuesta.setCodigoRespuesta(0);
                    respuesta.setDescripcionRespuesta("Error insertando productos");
                    respuesta.setMensajeRespuesta("Error insertando productos");
                }
            } else {
                respuesta.setCodigoRespuesta(0);
                respuesta.setDescripcionRespuesta("Error insertando pagos");
                respuesta.setMensajeRespuesta("Error insertando pagos");
            }

        } catch (Exception e) {
            e.printStackTrace();
            respuesta.setCodigoRespuesta(0);
            respuesta.setDescripcionRespuesta(e.getMessage());
            respuesta.setMensajeRespuesta(e.toString());
        }
        return respuesta;
    }

    /**
     * funcion que inserta los pagos realizados de la factura de compra
     *
     * @param pagos
     * @param idFacturaCompra
     * @return
     */
    public RespuestaEntity insertaPagoFC(List<PagoFacCompraEntity> pagos, Integer idFacturaCompra) {
        RespuestaEntity respuesta = new RespuestaEntity();
        try {
            for (PagoFacCompraEntity item : pagos) {
                item.setId(selecMaxPago());
                initOperation();
                item.setIdFacturaCompra(idFacturaCompra);
                sesion.save(item);
                close();
            }
            respuesta.setCodigoRespuesta(1);
        } catch (Exception e) {
            e.printStackTrace();
            respuesta.setCodigoRespuesta(0);
            respuesta.setDescripcionRespuesta(e.getMessage());
            respuesta.setMensajeRespuesta(e.getLocalizedMessage());
        }
        return respuesta;
    }

    /**
     * funcion que inserta los productos de la factura de compra
     *
     * @param producto
     * @return
     */
    public RespuestaEntity insertaProductoFC(List<ProductoFacCompraEntity> producto, Integer idFacturaCompra) {
        RespuestaEntity respuesta = new RespuestaEntity();
        try {
            for (ProductoFacCompraEntity item : producto) {
                item.setIdFacturaCompra(idFacturaCompra);
                item.setId(selectMaxProd());
                initOperation();
                item.setFecha(new Date());
                sesion.save(item);
                close();
            }
            respuesta.setCodigoRespuesta(1);
        } catch (Exception e) {
            respuesta.setCodigoRespuesta(0);
            respuesta.setDescripcionRespuesta(e.getMessage());
            respuesta.setMensajeRespuesta(e.getLocalizedMessage());
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * metodo que trae el consecutivo para insertar en la factura de compra
     *
     * @return
     */
    public Integer selectMaxFactura() {
        Long resultado = new Long(1);
        try {
            initOperation();
            resultado = (Long) sesion.createCriteria(FacturaCompraEntity.class)
                    .setProjection(Projections.rowCount()).uniqueResult() + 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado.intValue();
    }

    /**
     * metodo que inserta los datos de las imagenes de la factura de compra
     *
     * @param entidad
     * @return
     */
    public RespuestaEntity insertaImagen(ImagenesFacCompraEntity entidad) {
        RespuestaEntity respuesta = new RespuestaEntity();
        try {
            initOperation();
            entidad.setId(selectMaxImagen());
            entidad.setFecha(new Date());
            sesion.save(entidad);
            respuesta.setCodigoRespuesta(1);
            respuesta.setDescripcionRespuesta("OK");
            respuesta.setMensajeRespuesta("OK");
        } catch (Exception e) {
            respuesta.setCodigoRespuesta(0);
            respuesta.setDescripcionRespuesta("ERROR" + e.getLocalizedMessage());
            respuesta.setMensajeRespuesta("OK" + e.toString());
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * metodo que consulta el id de las imagenes
     *
     * @return
     */
    public Integer selectMaxImagen() {
        Long resultado = new Long(1);
        try {
            initOperation();
            resultado = (Long) sesion.createCriteria(ImagenesFacCompraEntity.class)
                    .setProjection(Projections.rowCount()).uniqueResult() + 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado.intValue();
    }

    /**
     * metodo que consulta el id de la tabla producto
     *
     * @return
     */
    public Integer selectMaxProd() {
        Long resultado = new Long(1);
        try {
            initOperation();
            resultado = (Long) sesion.createCriteria(ProductoFacCompraEntity.class)
                    .setProjection(Projections.rowCount()).uniqueResult() + 1;
            close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado.intValue();
    }

    /**
     * funcion que consulta el maximo de la tabla de pagos
     *
     * @return
     */
    public Integer selecMaxPago() {
        Long resultado = new Long(1);
        try {
            initOperation();
            resultado = (Long) sesion.createCriteria(PagoFacCompraEntity.class)
                    .setProjection(Projections.rowCount()).uniqueResult() + 1;
            close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado.intValue();
    }

    /**
     * Funcion con la cual ejecuto el proceso que crea una facutra de compra
     *
     * @param idFacom Id de la factura de compra
     * @return
     */
    public String ejecutaPreciosFacturaCompra(Integer idFacom) {
        String rta = "";
        try (ReadFunction rf = new ReadFunction()) {
            List<String> response = new ArrayList<>();
            rf.setNombreFuncion("FA_REGISTRA_FACT_COMPRA");
            rf.setNumParam(1);
            rf.addParametro(idFacom.toString(), DataType.INT);
            boolean valida = rf.callFunctionJdbc();
            if (valida) {
                response = rf.getRespuestaPg();
                rta = response.get(0);
            } else {
                response = rf.getRespuestaPg();
                rta = "Error al realizar el llamado de FA_REGISTRA_FACT_COMPRA ";
            }
        } catch (Exception e) {
            e.printStackTrace();
            rta = "Error " + e;
        }
        return rta;
    }

    /**
     * Funcion que inicializa la clase de hibernate
     *
     * @throws HibernateException
     */
    private void initOperation() throws HibernateException {
        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
    }

    /**
     * Funcion para cerrar la sesion
     *
     * @throws Exception
     */
    @Override
    public void close() throws Exception {
        if (tx != null) {
            tx.commit();
        }
        if (sesion != null) {
            sesion.close();
            sesion = null;
        }
    }
}
