/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.logica.admin;

import co.com.codesoftware.persistencia.HibernateUtil;
import co.com.codesoftware.persistencia.entidad.admin.ResolucionFactEntity;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

/**
 *
 * @author nicolas
 */
public class ResolucionFactLogica implements AutoCloseable{
    
    private Session sesion;
    private Transaction tx;
    
    /**
     * Funcion con la cula inserto las resoluciones de facturacion
     * @param resolucionEntity
     * @return 
     */
    public String insertaResolucion(ResolucionFactEntity resolucionEntity){
        String rta = "";
        try {
            this.initOperation();
            resolucionEntity.setConsecutivo(0);
            Date c = new Date();
            resolucionEntity.setFecha(c);
            sesion.save(resolucionEntity);
            rta = "Ok";
        } catch (Exception e) {
            e.printStackTrace();
            rta = "Error " +e ;
        }
        return rta;
    }
    /**
     * Funcion con la cual actualizo una resolucion de facturacion
     * @param resolucionEntity
     * @return 
     */
    public String actualizarResolucion(ResolucionFactEntity resolucionEntity){
        String rta = "";
        try {
            this.initOperation();
            sesion.update(resolucionEntity);
            rta = "Ok";
        } catch (Exception e) {
            e.printStackTrace();
            rta = "Error " +e ;
        }
        return rta;
    }
    /**
     * Funcion con la cual obtengo todas las resoluciones de facturacion que se encuentran en el sistema
     * @return 
     */
    public List<ResolucionFactEntity> obtieneResolucionesFacturacion(){
        List<ResolucionFactEntity> rta = null;
        try {
            this.initOperation();
            Criteria crit = sesion.createCriteria(ResolucionFactEntity.class);
            crit.addOrder(Order.desc("id"));
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
