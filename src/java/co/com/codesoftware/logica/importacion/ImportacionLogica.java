/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.logica.importacion;

import co.com.codesoftware.persistencia.HibernateUtil;
import co.com.codesoftware.persistencia.entidad.importacion.ImportacionEntity;
import java.math.BigDecimal;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author nicolas
 */
public class ImportacionLogica implements AutoCloseable {

    private Session sesion;
    private Transaction tx;
    
    public String insertaImportacion(ImportacionEntity objEntity){
        String rta = "";
        try {
            this.initOperation();
            objEntity.setFechaCrea(new Date());
            objEntity.setVlrTotal(new BigDecimal(0));
            objEntity.setVlrImpuestos(new BigDecimal(0));
            objEntity.setEstado("A");
            this.sesion.save(objEntity);
            rta = "Ok";
        } catch (Exception e) {
            rta = "Error " + e;
            e.printStackTrace();
        }
        return rta;
    }

    private void initOperation() {
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            tx = sesion.beginTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        try {
            if (tx != null) {
                tx.commit();
            }
            if (sesion != null) {
                sesion.close();
            }
        } catch (Exception e) {
            System.err.println("Error al cerrar la sesion del cliente hibernate " + e);
        }

    }

}
