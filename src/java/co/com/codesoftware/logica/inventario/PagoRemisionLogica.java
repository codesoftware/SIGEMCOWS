/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.logica.inventario;

import co.com.codesoftware.persistencia.HibernateUtil;
import co.com.codesoftware.persistencia.entidad.inventario.DetallePagoRemision;
import co.com.codesoftware.persistencia.entidad.inventario.PagoRemisionEntity;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ACER
 */
public class PagoRemisionLogica implements AutoCloseable {

    private Session sesion;
    private Transaction tx;

    /**
     * Funcion con la cual obtengo la informacion principal del pago
     *
     * @param idRemision
     * @param idFactura
     * @return
     */
    public PagoRemisionEntity obtieneRemison(Integer idRemision, Integer idFactura) {
        PagoRemisionEntity rta = null;
        try {
            this.initOperation();
            Criteria crit = this.sesion.createCriteria(PagoRemisionEntity.class);
            crit.add(Restrictions.eq("idRemision", idRemision));
            crit.add(Restrictions.eq("idFactu", idFactura));
            rta = (PagoRemisionEntity) crit.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
    /**
     * Funcion con la cual obtengo el detalle de un pago
     * @param idPago
     * @return 
     */
    public List<DetallePagoRemision> obtieneDetallePago(Integer idPago){
        List<DetallePagoRemision> rta = null;
        try {
            this.initOperation();
            Criteria crit = this.sesion.createCriteria(DetallePagoRemision.class);
            crit.add(Restrictions.eq("idPago", idPago));
            rta = crit.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    private void initOperation() throws HibernateException {
        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
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

}
