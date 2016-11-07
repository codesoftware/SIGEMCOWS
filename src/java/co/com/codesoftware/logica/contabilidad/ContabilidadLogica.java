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
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 * /*
 * MO-001 Consulta movimientos contables terceros: se modifica la consulta para
 * que reciba el id del tercero y el tipo 
 * jmorenor1986 
 * 07/11/2016
 * -------------------------------------------------------------------------------------------------------
 */
public class ContabilidadLogica implements AutoCloseable {

    private Session sesion;
    private Transaction tx;

    /**
     * Funcion con la cual obtengo los movimientos contables de acuerdo a las
     * fechas y al tipo de documento
     *
     * @param fechaIn
     * @param fechaFin
     * @param tipoDoc
     * @return
     */
    public List<MoviContableEntity> consultarMovimientoscontable(Date fechaIn, Date fechaFin, String tipoDoc) {
        List<MoviContableEntity> rta = null;
        try {
            this.initOperation();
            Criteria crit = this.sesion.createCriteria(MoviContableEntity.class);
            crit.setFetchMode("subcuenta", FetchMode.JOIN);
            crit.setFetchMode("tipoDocumento", FetchMode.JOIN);
            crit.setFetchMode("auxiliar", FetchMode.JOIN);
            if (!"-1".equalsIgnoreCase(tipoDoc)) {
                crit.add(Restrictions.eq("llave", tipoDoc));
            }
            if (fechaIn != null || fechaFin != null) {
                if (fechaIn != null && fechaFin != null) {
                    fechaFin.setHours(23);
                    fechaFin.setMinutes(59);
                    fechaFin.setSeconds(59);
                    crit.add(Restrictions.between("fecha", fechaIn, fechaFin));
                } else if (fechaIn != null) {
                    crit.add(Restrictions.le("fecha", fechaIn));
                } else if (fechaFin != null) {
                    crit.add(Restrictions.gt("fecha", fechaFin));
                }

            }
            rta = crit.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    public List<MoviContableEntity> consultarMovContXCuenta(Date fechaIn, Date fechaFin, String cuenta, String tipo, Integer tercero) {
        List<MoviContableEntity> rta = null;
        try {
            System.out.println("cuenta:"+cuenta);
            System.out.println("tipo:"+tipo);
            System.out.println("tercero:"+tercero);
            this.initOperation();
            Criteria crit = this.sesion.createCriteria(MoviContableEntity.class);
            crit.setFetchMode("subcuenta", FetchMode.JOIN);
            crit.setFetchMode("tipoDocumento", FetchMode.JOIN);
            crit.setFetchMode("auxiliar", FetchMode.JOIN);
            if (cuenta != null && !"".equalsIgnoreCase(cuenta)) {
                crit.createAlias("subcuenta", "sbcu");
                crit.add(Restrictions.like("sbcu.codigo", cuenta, MatchMode.ANYWHERE));
            }
            //MO-001
            if(tipo!=null && !"".equalsIgnoreCase(tipo)){
                crit.add(Restrictions.eq("tipoTercero", Integer.parseInt(tipo)));
            }
            if(tercero!=null && tercero !=-1){
                crit.add(Restrictions.eq("tercero", tercero));
            }
            //MO-001
            if (fechaIn != null || fechaFin != null) {
                if (fechaIn != null && fechaFin != null) {
                    fechaFin.setHours(23);
                    fechaFin.setMinutes(59);
                    fechaFin.setSeconds(59);
                    crit.add(Restrictions.between("fecha", fechaIn, fechaFin));
                } else if (fechaIn != null) {
                    crit.add(Restrictions.le("fecha", fechaIn));
                } else if (fechaFin != null) {
                    crit.add(Restrictions.gt("fecha", fechaFin));
                }

            }
            crit.addOrder(Order.desc("id"));
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
