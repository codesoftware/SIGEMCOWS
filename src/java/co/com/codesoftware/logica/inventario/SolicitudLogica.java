/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.logica.inventario;

import co.com.codesoftware.persistencia.HibernateUtil;
import co.com.codesoftware.persistencia.entidad.inventario.SolicitudEntity;
import co.com.codesoftware.persistencia.entidad.inventario.SolicitudProdEntity;
import co.com.codesoftware.persistencia.utilities.RespuestaEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
        List<SolicitudEntity> respuesta = new ArrayList<>();
        try {
            initOperation();
            Criteria crit = sesion.createCriteria(SolicitudEntity.class);
            if (fechaInicial != null && fechaFinal != null) {
                crit.add(Restrictions.ge("fecha", fechaInicial));
                crit.add(Restrictions.lt("fecha", fechaFinal));
            }
            if (estado != null && !"".equalsIgnoreCase(estado)) {
                crit.add(Restrictions.eq("", estado));
            }
            if (usuario != null && usuario != 0) {
                crit.createAlias("usuario", "usu").add(Restrictions.eq("usu.id", usuario));
            }
            if (sede != null && sede != 0) {
                crit.createAlias("sede", "sede").add(Restrictions.eq("sede.id", sede));
            }
            respuesta = crit.list();
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
    
    public SolicitudEntity consultaSolicitudXFecha(Date fecha,Integer sede, Integer usuario){
        SolicitudEntity solicitud = new SolicitudEntity();
        try {
            initOperation();
            solicitud= (SolicitudEntity) sesion.createCriteria(SolicitudEntity.class)
                    .add(Restrictions.eq("fecha",fecha))
                    .createAlias("sede","sed")
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
