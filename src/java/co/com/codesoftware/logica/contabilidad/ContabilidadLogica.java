/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.logica.contabilidad;

import co.com.codesoftware.persistencia.HibernateUtil;
import co.com.codesoftware.persistencia.entidad.contabilidad.MoviContableEntity;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ACER
 */
public class ContabilidadLogica implements AutoCloseable {

    private Session sesion;
    private Transaction tx;

    /**
     * Funcion con la cual obtengo los movimientos contables de acuerdo a las
     * fechas y al tipo de documento
     *
     * @return
     */
    public List<MoviContableEntity> consultarMovimientoscontable(Date fechaIn, Date fechaFin, String tipoDoc) {
        List<MoviContableEntity> rta = null;
        try {
            this.initOperation();
            Criteria crit = this.sesion.createCriteria(MoviContableEntity.class);
            if(!"".equalsIgnoreCase(tipoDoc)){
                crit.add(Restrictions.eq("llave", tipoDoc));
            }
            crit.setFetchMode("subcuenta", FetchMode.JOIN);
            crit.setFetchMode("tipoDocumento", FetchMode.JOIN);
            crit.setFetchMode("auxiliar", FetchMode.JOIN);
            rta = crit.list();
        } catch (Exception e) {
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
