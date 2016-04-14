/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.logica.facturacion;

import co.com.codesoftware.persistencia.HibernateUtil;
import co.com.codesoftware.persistencia.entidad.facturacion.DetProdRemision;
import co.com.codesoftware.persistencia.entidad.facturacion.RemisionEntity;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author nicolas
 */
public class RemisionLogica implements AutoCloseable {

    private Session sesion;
    private Transaction tx;

    /**
     * Funcion con la cual obtengo las remisiones basandome en el id del cliente
     *
     * @param idCliente
     * @return
     */
    public List<RemisionEntity> obtieneRemisionesXCliente(Integer idCliente, Date fechaIni, Date fechaFin) {
        List<RemisionEntity> rta = null;
        try {
            this.initOperation();
            Criteria crit = sesion.createCriteria(RemisionEntity.class);
            crit.setFetchMode("usuario.perfil", FetchMode.JOIN);
            crit.setFetchMode("usuario.persona", FetchMode.JOIN);
            crit.setFetchMode("usuario.sede", FetchMode.JOIN);
            if (fechaIni != null && fechaFin != null) {
                crit.add(Restrictions.between("fechaCreacion", fechaIni, fechaFin));
            }
            rta = crit.list();
        } catch (Exception e) {
            e.printStackTrace();
            rta = null;
        }
        return rta;
    }

    /**
     * Funcion con la cual obtengo los detalles de la remision teniendo en
     * cuenta su id
     *
     * @param idRemision
     * @return
     */
    public List<DetProdRemision> buscaDetallesRemision(Integer idRemision) {
        List<DetProdRemision> rta = null;
        try {
            this.initOperation();
            Criteria crit = sesion.createCriteria(DetProdRemision.class);
            crit.add(Restrictions.eq("idRemi", idRemision));
            crit.setFetchMode("producto.categoria", FetchMode.JOIN);
            crit.setFetchMode("producto.marca", FetchMode.JOIN);
            crit.setFetchMode("producto.referencia", FetchMode.JOIN);
            crit.setFetchMode("producto.subcuenta", FetchMode.JOIN);
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

    @Override
    public void close() throws Exception {
        try {
            if (sesion != null) {
                sesion.close();
            }
        } catch (Exception e) {
            System.err.println("Error al cerrar la sesion del cliente hibernate " + e);
        }

    }
}
