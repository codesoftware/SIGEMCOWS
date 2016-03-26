/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.logic;

import co.com.codesoftware.persistence.entity.administracion.MarcaEntity;
import co.com.codesoftware.persistence.entity.administracion.RespuestaEntity;
import co.com.codesoftware.persistencia.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author root
 */
public class MarcaLogic implements AutoCloseable {

    private Session sesion;
    private Transaction tx;

    /**
     * Funcion que consulta todas las marcas activas del sistema
     *
     * @return
     */
    public List<MarcaEntity> consultaMarcas() {
        List<MarcaEntity> respuesta = null;
        try {
            this.initOperation();
            Criteria crit = sesion.createCriteria(MarcaEntity.class);
            crit.add(Restrictions.eq("estado", "A"));
            crit.addOrder(Order.asc("descripcion"));
            respuesta = crit.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }
    
    /**
     * Funcion que consulta todas las marcas que tiene una serie de productos que tienen determinada categoria
     *
     * @return
     */
    public List<MarcaEntity> consultaMarcasRelCat(Integer idCate) {
        List<MarcaEntity> respuesta = null;
        try {
            this.initOperation();
            Query query = sesion.createQuery("from MarcaEntity where id in (select distinct marca from ProductoSimpleEntity where dska_cate = :idCate) order by descripcion ");
            query.setInteger("idCate", idCate);
            respuesta = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }
    
    
    /**
     * Funcion que inserta una marca en la base de datos
     * @param entidad
     * @return 
     */
    public RespuestaEntity insertaMarcas(MarcaEntity entidad) {
        RespuestaEntity entity = new RespuestaEntity();
        try {
            this.initOperation();
            entidad.setId(selectMaxMarca());
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
     * @return 
     */

    public Integer selectMaxMarca() {
        Integer resultado = null;
        try {
            Criteria crit = sesion.createCriteria(MarcaEntity.class)
                    .setProjection(Projections.max("id"));
            resultado = (Integer) crit.uniqueResult() + 1;
        } catch (Exception e) {
            resultado = 1;
            e.printStackTrace();
        }
        return resultado;
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
