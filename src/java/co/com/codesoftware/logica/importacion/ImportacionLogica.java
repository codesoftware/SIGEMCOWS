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
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author nicolas
 */
public class ImportacionLogica implements AutoCloseable {

    private Session sesion;
    private Transaction tx;

    public String insertaImportacion(ImportacionEntity objEntity) {
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

    /**
     * metodo que consulta las importaciones en un rango de fechas
     *
     * @param fechaInicial
     * @param fechaFinal
     * @return
     */
    public List<ImportacionEntity> consultaImportaciones(Date fechaInicial, Date fechaFinal) {
        List<ImportacionEntity> rta = null;
        try {
            this.initOperation();

            Criteria crit = sesion.createCriteria(ImportacionEntity.class);
            if (fechaInicial != null && fechaFinal != null) {
                fechaFinal.setHours(23);
                fechaFinal.setMinutes(59);
                fechaFinal.setSeconds(59);
                crit.add(Restrictions.ge("fechaCrea", fechaInicial))
                        .add(Restrictions.lt("fechaCrea", fechaFinal));
            }
            rta = crit.list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * metodo que inicia la sesion de base de datos
     */
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
