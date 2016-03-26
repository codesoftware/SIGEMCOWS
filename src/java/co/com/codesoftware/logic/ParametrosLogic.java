/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.logic;

import co.com.codesoftware.persistencia.HibernateUtil;
import co.com.codesoftware.persistence.entity.administracion.ParametrosEntity;
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
public class ParametrosLogic implements AutoCloseable {

    private Session sesion;
    private Transaction tx;

    /**
     * metodo que consulta los parametros de correo de envio
     *
     * @return
     */
    public List<ParametrosEntity> consultaParametrosCorreoEnvio() {
        List<ParametrosEntity> rta = null;
        try {
            Object[] datos = {"CORREOENVIO","CLAVECORRENV","PUERTOENV","SERVSMTP"}; 
            initOperation();
            Criteria crit = sesion.createCriteria(ParametrosEntity.class);
            crit.add(Restrictions.in("clave", datos));
            rta = crit.list();  
        } catch (Exception e) {
            e.printStackTrace();
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
        }
    }

}
