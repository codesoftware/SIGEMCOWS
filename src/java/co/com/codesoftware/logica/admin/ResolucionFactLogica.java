/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.logica.admin;

import co.com.codesoftware.persistencia.HibernateUtil;
import co.com.codesoftware.persistencia.entidad.admin.ResolucionFactEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author nicolas
 */
public class ResolucionFactLogica implements AutoCloseable{
    
    private Session sesion;
    private Transaction tx;
    
    /**
     * Funcion con la cula inserto las resoluciones de facturacion
     * @return 
     */
    public String insertaResolucion(ResolucionFactEntity resolucionEntity){
        String rta = "";
        try {
            this.initOperation();
            sesion.save(ResolucionFactEntity.class);
        } catch (Exception e) {
            e.printStackTrace();
            return rta;
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
