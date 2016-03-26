/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.logic.facturas;


import co.com.codesoftware.persistence.entity.administracion.MoviContableEntity;
import co.com.codesoftware.persistence.entity.facturacion.HistorialFacturaEntity;
import co.com.codesoftware.persistence.entity.facturacion.ImagenFacturaEntity;

import co.com.codesoftware.persistencia.HibernateUtil;
import co.com.codesoftware.persistencia.ReadFunction;
import co.com.codesoftware.persistencia.utilities.DataType;
import co.com.codesoftware.wrapperrequest.CancelaFacturaWrapRequest;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author john
 */
public class FacturaLogic implements AutoCloseable {

    private Session sesion;
    private Transaction tx;

    /**
     * metodo que llama al procedimiento almacenado de cancelacion de facturas
     *
     * @param datosCancelacion
     * @return
     */
    public String llamaProcesoCancelaFac(CancelaFacturaWrapRequest datosCancelacion) {
        String rta = "";
        try (ReadFunction rf = new ReadFunction()) {
            rf.setNombreFuncion("FA_CANCELA_FACTURA_HIS");
            rf.setNumParam(4);
            rf.addParametro(datosCancelacion.getIdUsuario().toString(), DataType.INT);
            rf.addParametro(datosCancelacion.getIdFactura().toString(), DataType.INT);
            rf.addParametro(datosCancelacion.getComentario(), DataType.TEXT);
            rf.addParametro(datosCancelacion.getEstado(), DataType.TEXT);
            boolean valida = rf.callFunctionJdbc();
            if (valida) {
                rta = rf.getRespuestaPg().get(0);
            } else {
                rta = "Error al llamar a la funcion de Cancelación de factura";
            }
        } catch (Exception e) {
            e.printStackTrace();
            rta = "Error" + e.getMessage();
        }
        return rta;
    }

    public Integer llamaProcesoIdCancelacion(Integer idFactura) {
        Integer rta = null;
        try (ReadFunction rf = new ReadFunction()) {
            rf.setNombreFuncion("FA_ID_CONSULTA_NOTA");
            rf.setNumParam(1);
            rf.addParametro(idFactura.toString(), DataType.INT);
            boolean valida = rf.callFunctionJdbc();
              if (valida) {
                rta = Integer.parseInt(rf.getRespuestaPg().get(0));
            } else {
                rta = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * funcion que consulta el historial de un factura por su id
     *
     * @param idFactura
     * @return
     */
    public List<HistorialFacturaEntity> consultaHistorialFactura(Integer idFactura) {
        List<HistorialFacturaEntity> rta = null;
        try {
            initOperation();
            rta = sesion.createCriteria(HistorialFacturaEntity.class).
                    add(Restrictions.eq("idFactura", idFactura)).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion que consulta los movimientos contables de una factura especifica
     *
     * @param idFactura
     * @param estado
     * @return
     */
    public List<MoviContableEntity> consultaMovContableXFac(Integer idFactura, String estado) {
        List<MoviContableEntity> rta = null;
        try {
            if("notcr".equalsIgnoreCase(estado)){
                idFactura=llamaProcesoIdCancelacion(idFactura);
            }
            initOperation();
            rta = sesion.createCriteria(MoviContableEntity.class)
                    .add(Restrictions.eq("idLlave", idFactura))
                    .add(Restrictions.eq("llave", estado))
                    .addOrder(Order.asc("naturaleza"))
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * metodo que inserta una imagen a una factura especifica
     *
     * @param datosImagen
     * @return
     */
    public String insertaImagenFactura(ImagenFacturaEntity datosImagen) {
        String rta = "OK";
        try {
            initOperation();
            sesion.save(datosImagen);

        } catch (Exception e) {
            e.printStackTrace();
            rta = "Error " + e.getMessage();
        }
        return rta;
    }

    /**
     * metodo que consulta las imagenes por id de facturación
     *
     * @param idFactura
     * @return
     */
    public List<ImagenFacturaEntity> consultaImagenesFactura(Integer idFactura) {
        List<ImagenFacturaEntity> rta = null;
        try {
            initOperation();
            rta = sesion.createCriteria(ImagenFacturaEntity.class)
                    .add(Restrictions.eq("idFactura", idFactura))
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    private void initOperation() throws HibernateException {
        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
    }

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
