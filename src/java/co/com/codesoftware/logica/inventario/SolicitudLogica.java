/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.logica.inventario;

import co.com.codesoftware.persistence.entities.simple.ProductoSimpleEntity;
import co.com.codesoftware.persistencia.HibernateUtil;
import co.com.codesoftware.persistencia.ReadFunction;
import co.com.codesoftware.persistencia.entidad.inventario.SolicitudEntity;
import co.com.codesoftware.persistencia.entidad.inventario.SolicitudProdEntity;
import co.com.codesoftware.persistencia.utilities.DataType;
import co.com.codesoftware.persistencia.utilities.RespuestaEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author john
 */
public class SolicitudLogica implements AutoCloseable {

    private Session sesion;
    private Transaction tx;

    /**
     * metodo que inserta una solicitud
     *
     * @param solicitud
     * @return
     */
    public RespuestaEntity insertaSolicitud(SolicitudEntity solicitud) {
        RespuestaEntity respuesta = new RespuestaEntity();
        try {
            initOperation();
            sesion.save(solicitud);
            //sol = consultaSolicitudXFecha(solicitud.getFecha(), solicitud.getSede().getId(), solicitud.getUsuario().getId());
            respuesta.setMensajeRespuesta("OK");
            respuesta.setCodigoRespuesta(1);
        } catch (Exception e) {
            e.printStackTrace();
            respuesta.setCodigoRespuesta(0);
            respuesta.setMensajeRespuesta(e.getMessage());
            respuesta.setDescripcionRespuesta(e.toString());
        }
        return respuesta;
    }

    /**
     * metodo que inserta los productos de una solicitud
     *
     * @param productos
     * @return
     */
    public RespuestaEntity insertaProductosSolicitud(List<SolicitudProdEntity> productos) {
        RespuestaEntity respuesta = new RespuestaEntity();
        try {
            for (SolicitudProdEntity item : productos) {
                initOperation();
                sesion.save(item);
                close();
            }
            respuesta.setCodigoRespuesta(1);
            respuesta.setMensajeRespuesta("OK");
            respuesta.setDescripcionRespuesta("OK");
        } catch (Exception e) {
            e.printStackTrace();
            respuesta.setCodigoRespuesta(0);
            respuesta.setMensajeRespuesta(e.getMessage());
            respuesta.setDescripcionRespuesta(e.toString());
        }
        return respuesta;
    }

    /**
     * metodo que consulta las solicitudes por los filtros seleccionados
     *
     * @param fechaInicial
     * @param fechaFinal
     * @param estado
     * @param usuario
     * @param sede
     * @return
     */
    public List<SolicitudEntity> consultaSolicitudesXFiltro(Date fechaInicial, Date fechaFinal, String estado, Integer usuario, Integer sede) {
        System.out.println(fechaInicial);
        System.out.println(fechaFinal);
        System.out.println(estado);
        System.out.println(sede);
        System.out.println(usuario);
        List<SolicitudEntity> respuesta = null;
        try {
            initOperation();
            Criteria crit = sesion.createCriteria(SolicitudEntity.class);
            if (fechaInicial != null && fechaFinal != null) {
                crit.add(Restrictions.ge("fecha", fechaInicial));
                fechaFinal.setHours(23);
                fechaFinal.setMinutes(59);
                fechaFinal.setSeconds(59);
                crit.add(Restrictions.lt("fecha", fechaFinal));
            }
            if (estado != null && !"".equalsIgnoreCase(estado)) {
                crit.add(Restrictions.eq("estado", estado));
            }
            if (usuario != null && usuario != 0) {
                crit.createAlias("usuario", "usu").add(Restrictions.eq("usu.id", usuario));
            }
            if (sede != null && sede != 0) {
                crit.createAlias("sede", "sede").add(Restrictions.eq("sede.id", sede));
            }
            crit.addOrder(Order.desc("id"));
            respuesta = crit.setFetchMode("sede", FetchMode.JOIN)
                    .setFetchMode("usuario", FetchMode.JOIN)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * metodo que consulta la lista de productos de una solicitud
     *
     * @param solicitud
     * @return
     */
    public List<SolicitudProdEntity> consultaProductosXSoliciud(Integer solicitud) {
        List<SolicitudProdEntity> respuesta = new ArrayList<>();
        try {
            initOperation();
            respuesta = sesion.createCriteria(SolicitudProdEntity.class)
                    .createAlias("solicitud", "sol").add(Restrictions.eq("sol.id", solicitud))
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * funcion que consulta las solicitudes por fecha
     *
     * @param fecha
     * @param sede
     * @param usuario
     * @return
     */
    public SolicitudEntity consultaSolicitudXFecha(Date fecha, Integer sede, Integer usuario) {
        SolicitudEntity solicitud = new SolicitudEntity();
        try {
            initOperation();
            solicitud = (SolicitudEntity) sesion.createCriteria(SolicitudEntity.class)
                    .add(Restrictions.eq("fecha", fecha))
                    .createAlias("sede", "sed")
                    .createAlias("usuario", "usu")
                    .add(Restrictions.eq("sede.id", sede))
                    .add(Restrictions.eq("usu.id", usuario))
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return solicitud;
    }

    /**
     * metodo que consulta usa solicitud por ID
     *
     * @param id
     * @return
     */
    public SolicitudEntity consultaSolicitudXId(Integer id) {
        SolicitudEntity solicitud = new SolicitudEntity();
        try {
            System.out.println("co.com.codesoftware.logica.inventario.SolicitudLogica.consultaSolicitudXId()" + id);
            initOperation();
            solicitud = (SolicitudEntity) sesion.createCriteria(SolicitudEntity.class)
                    .setFetchMode("sede", FetchMode.JOIN)
                    .setFetchMode("usuario", FetchMode.JOIN)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return solicitud;
    }

    /**
     * metoddo que actualiza los productos de una solicitud
     *
     * @param productos
     * @return
     */
    public RespuestaEntity actualizaProductosSolicitud(List<SolicitudProdEntity> productos, Integer idUsuario) {
        RespuestaEntity respuesta = new RespuestaEntity();
        try {
            for (SolicitudProdEntity item : productos) {
                initOperation();
                sesion.update(item);
                close();
            }
            //respuesta = actualizaSolicitud("E", productos.get(0).getSolicitud());
            respuesta = ejecutaProcedimientoSolicitud(idUsuario, productos.get(0).getSolicitud().getId());

        } catch (Exception e) {
            e.printStackTrace();
            respuesta.setCodigoRespuesta(0);
            respuesta.setDescripcionRespuesta(e.getMessage());
            respuesta.setMensajeRespuesta(e.toString());
        }
        return respuesta;
    }

    /**
     * metodo que consulta un produnto simple por codigo externo
     *
     * @param codigoExterno
     * @return
     */
    public ProductoSimpleEntity consultaProductoXCodExterno(String codigoExterno) {
        ProductoSimpleEntity rta = new ProductoSimpleEntity();
        try {
            initOperation();
            rta = (ProductoSimpleEntity) sesion.createCriteria(ProductoSimpleEntity.class).add(Restrictions.eq("codigoExt", codigoExterno)).uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * funcion que actualiza el estado de la solicitud
     *
     * @param estado
     * @param solicitud
     * @return
     */
    public RespuestaEntity actualizaSolicitud(String estado, SolicitudEntity solicitud) {
        RespuestaEntity respuesta = new RespuestaEntity();
        try {
            initOperation();
            solicitud.setEstado(estado);
            sesion.update(solicitud);
            close();
            respuesta.setCodigoRespuesta(1);
            respuesta.setDescripcionRespuesta("OK");
            respuesta.setMensajeRespuesta("OK");
        } catch (Exception e) {
            e.printStackTrace();
            respuesta.setCodigoRespuesta(0);
            respuesta.setDescripcionRespuesta(e.getMessage());
            respuesta.setMensajeRespuesta(e.toString());
        }
        return respuesta;
    }

    /**
     * funcion que llama al procedimiento de solicitudes
     *
     * @param idUsuario
     * @param idSede
     * @return
     */
    public RespuestaEntity ejecutaProcedimientoSolicitud(Integer idUsuario, Integer idSede) {
        RespuestaEntity respuesta = new RespuestaEntity();
        try (ReadFunction rf = new ReadFunction()) {
            rf.setNombreFuncion("FA_ENVIASOLICITUD");
            rf.setNumParam(2);
            rf.addParametro(idUsuario.toString(), DataType.INT);
            rf.addParametro(idSede.toString(), DataType.INT);
            boolean valida = rf.callFunctionJdbc();
            if (valida) {
                if (rf.getRespuestaPg().get(0).startsWith("Error")) {
                    respuesta.setCodigoRespuesta(0);
                } else {
                    respuesta.setCodigoRespuesta(1);
                }
                respuesta.setDescripcionRespuesta(rf.getRespuestaPg().get(0));

            } else {
                respuesta.setDescripcionRespuesta("Error al realizar solicitud ");
                respuesta.setCodigoRespuesta(0);
            }
        } catch (Exception e) {
            respuesta.setDescripcionRespuesta("Error" + e.getMessage());
            respuesta.setCodigoRespuesta(0);
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * metodo que inicia la factoria de conexiones hibernate
     *
     * @throws HibernateException
     */
    private void initOperation() throws HibernateException {
        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
    }

    /**
     * metodo que cierra la factoria de conexiones de hibernate
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
        }
    }
}
