/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.logic;


import co.com.codesoftware.persistence.entity.administracion.ProveedoresEntity;
import co.com.codesoftware.persistence.entity.administracion.RespuestaEntity;
import co.com.codesoftware.persistencia.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author john
 */
public class ProveedoresLogic implements AutoCloseable {
    
    private Session sesion;
    private Transaction tx;

    /**
     * Funcion que consulta en la base de datos los proveedores activos
     *
     * @return
     */
    public List<ProveedoresEntity> consultaProveedores() {
        List<ProveedoresEntity> respuesta = null;
        try {
            this.initOperation();
            Criteria crit = sesion.createCriteria(ProveedoresEntity.class);
            crit.add(Restrictions.eq("estado", "A"));
            respuesta = crit.list();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * Funcion que inserta un proveedor a la base de datos
     *
     * @param entidad
     * @return
     */
    public RespuestaEntity insertaProveedores(ProveedoresEntity entidad) {
        RespuestaEntity entity = new RespuestaEntity();
        try {
            this.initOperation();
            if(entidad.getEstado() == null){
                entidad.setEstado("A");
            }
            entidad.setId(selectMaxProveedor());
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
     * metodo que actualiza un proveedor
     * @param entidad
     * @return 
     */
    public RespuestaEntity actualizaProveedor(ProveedoresEntity entidad){
         RespuestaEntity entity = new RespuestaEntity();
        try {
            this.initOperation();
            sesion.update(entidad);
            entity.setCodigoRespuesta(1);
            entity.setDescripcionRespuesta("EXITOSO");
            entity.setMensajeRespuesta("OK");
            
        } catch (Exception e) {
            entity.setCodigoRespuesta(0);
            entity.setDescripcionRespuesta("ERROR");
            entity.setMensajeRespuesta(e.getMessage());
            e.printStackTrace();
        }
        return entity;
    }

    /**
     * Metodo que consulta el valor mximo del campo id
     *
     * @return
     */
    public Integer selectMaxProveedor() {
        Integer resultado = null;
        try {
            Criteria crit = sesion.createCriteria(ProveedoresEntity.class)
                    .setProjection(Projections.max("id"));
            resultado = (Integer) crit.uniqueResult() + 1;
        } catch (Exception e) {
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
