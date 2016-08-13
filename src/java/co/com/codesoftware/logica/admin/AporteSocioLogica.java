/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.logica.admin;

import co.com.codesoftware.persistencia.HibernateUtil;
import co.com.codesoftware.persistencia.entidad.admin.AporteSocioEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ACER
 */
public class AporteSocioLogica implements AutoCloseable {
    
    private Session sesion;
    private Transaction tx;
    
    public String insertaAporte(AporteSocioEntity entity){
        String rta = "";
        try {
            this.initOperation();
            this.sesion.save(entity);
            rta = "Ok";
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
