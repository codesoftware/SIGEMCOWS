/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.logica.admin;

import co.com.codesoftware.persistencia.HibernateUtil;
import co.com.codesoftware.persistencia.entidad.admin.DepartamentoEntity;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ACER
 */
public class UbicacionLogica implements AutoCloseable {
    
    private Session sesion;
    private Transaction tx;

    /**
     * Funcion con la cual obtengo los departamentos parametrizados en el
     * sistema
     *
     * @return
     */
    public List<DepartamentoEntity> obtieneDepartamentos() {
        List<DepartamentoEntity> rta = null;
        try {
            this.initOperation();
            Criteria crit = sesion.createCriteria(DepartamentoEntity.class);
            rta = crit.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
    
    @Override
    public void close() throws Exception {
        if (tx != null) {
            tx.commit();
        }
        if (sesion != null) {
            sesion.close();
        }
    }

    /**
     * Funcion con la cual inicia la transaccion hibernate
     */
    private void initOperation() {
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            if (tx == null) {
                tx = sesion.beginTransaction();
            }
        } catch (Exception e) {
            e.printStackTrace();
            HibernateUtil.generaNuloSesion();
            try {
                sesion = HibernateUtil.getSessionFactory().openSession();
                if (tx == null) {
                    tx = sesion.beginTransaction();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
