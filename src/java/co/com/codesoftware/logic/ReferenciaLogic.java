/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.logic;


import co.com.codesoftware.persistence.entity.administracion.ReferenciaEntity;
import co.com.codesoftware.persistence.entity.administracion.RespuestaEntity;
import co.com.codesoftware.persistencia.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author root
 */
public class ReferenciaLogic implements AutoCloseable {

    private Session sesion;
    private Transaction tx;

    /**
     * Funcion que consulta todas las referencias activas del sistema
     *
     * @return
     */
    public List<ReferenciaEntity> consultaReferencias() {
        List<ReferenciaEntity> respuesta = null;
        try {
            this.initOperation();
            Criteria crit = sesion.createCriteria(ReferenciaEntity.class);
            crit.add(Restrictions.eq("estado", "A"));
            respuesta = crit.list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * Funcion que inserta una referencia en la base de datos
     *
     * @param entidad
     * @return
     */
    public RespuestaEntity insertaReferencias(ReferenciaEntity entidad) {
        RespuestaEntity entity = new RespuestaEntity();
        try {
            this.initOperation();
            entidad.setId(selectMaxReferencia());
            entidad.setEstado("A");
            sesion.save(entidad);
            entity.setCodigoRespuesta(1);
            entity.setDescripcionRespuesta("EXITOSO");
            entity.setMensajeRespuesta("OK");
        } catch (Error e) {
            entity.setCodigoRespuesta(0);
            entity.setDescripcionRespuesta("ERROR");
            entity.setMensajeRespuesta(e.getMessage());
            e.printStackTrace();
        }
        return entity;
    }

    /**
     * Funcion que consulta el maximo del id de la marca y le suma 1
     *
     * @return
     */
    public Integer selectMaxReferencia() {
        Integer resultado = null;
        try {
            Criteria crit = sesion.createCriteria(ReferenciaEntity.class)
                    .setProjection(Projections.max("id"));
            resultado = (Integer) crit.uniqueResult() + 1;
        } catch (Exception e) {
            resultado = 1;
            e.printStackTrace();
        }
        return resultado;
    }

    /**
     * Funcion con la cual actualizo una referencia
     *
     * @param referencia
     * @return
     */
    public String actualizaReferencia(ReferenciaEntity referencia) {
        String rta = "";
        try {
            initOperation();
            sesion.update(referencia);
            rta = "Ok";
        } catch (Exception e) {
            e.printStackTrace();
            rta = "Error";
        }
        return rta;
    }

    /**
     * *
     * Funcion con la cual obtengo una lista de Referencias basandose en el id
     * de la categoria
     *
     * @param idCategoria
     * @return
     */
    public List<ReferenciaEntity> consultaReferenciaXCategoria(Integer idCategoria) {
        List<ReferenciaEntity> respuesta = null;
        try {
            this.initOperation();
            Criteria crit = sesion.createCriteria(ReferenciaEntity.class);
            crit.add(Restrictions.eq("estado", "A"));
            crit.add(Restrictions.eq("categoriaId", idCategoria));
            crit.addOrder(Order.asc("descripcion"));
            respuesta = crit.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
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
        }
    }

}
