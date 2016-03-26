/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.logic;


import co.com.codesoftware.persistencia.entidad.admin.SedeEntity;
import co.com.codesoftware.persistencia.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author root
 */
public class SedeLogic implements AutoCloseable {

    private Session sesion;
    private Transaction tx;

    
     /**
     * Funcion que consulta todas las sedes activas del sistema
     *
     * @return
     */
    public List<SedeEntity> consultaSedes() {
        List<SedeEntity> respuesta = null;
        try {
            this.initOperation();
            Criteria crit = sesion.createCriteria(SedeEntity.class);
            crit.add(Restrictions.eq("estado", "A"));
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
