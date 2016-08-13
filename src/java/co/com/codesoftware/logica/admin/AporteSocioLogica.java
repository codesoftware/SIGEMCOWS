/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.logica.admin;

import co.com.codesoftware.persistencia.HibernateUtil;
import co.com.codesoftware.persistencia.entidad.admin.AporteSocioEntity;
import co.com.codesoftware.persistencia.entidad.admin.ProductoAporte;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ACER
 */
public class AporteSocioLogica implements AutoCloseable {

    private Session sesion;
    private Transaction tx;

    /**
     * metodo que inserta un aporte
     *
     * @param entity
     * @return
     */
    public String insertaAporte(AporteSocioEntity entity) {
        String rta = "";
        try {
            this.initOperation();
            this.sesion.save(entity);
            rta = "Ok";
        } catch (Exception e) {
            rta = "Error " + e.toString();
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * metodo que consulta todos los aportes
     *
     * @return
     */
    public List<AporteSocioEntity> consultaAportes() {
        List<AporteSocioEntity> rta = new ArrayList<>();
        try {
            initOperation();
            rta = sesion.createCriteria(AporteSocioEntity.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * metodo que actualiza un aporte
     *
     * @param entidad
     * @return
     */
    public String actualizaAporte(AporteSocioEntity entidad) {
        String rta = "";
        try {
            initOperation();
            sesion.update(entidad);
            rta = "Ok";
        } catch (Exception e) {
            rta = "Error " + e.toString();
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * metodo que consulta un aporte por id
     *
     * @param id
     * @return
     */
    public AporteSocioEntity consultaAporte(Integer id) {
        AporteSocioEntity rta = new AporteSocioEntity();
        try {
            initOperation();
            rta = (AporteSocioEntity) sesion.createCriteria(AporteSocioEntity.class).
                    add(Restrictions.eq("id", id)).uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
    /**
     * 
     * @param idAporte
     * @return 
     */
    public List<ProductoAporte> consultaProductosAporte(Integer idAporte){
        List<ProductoAporte> rta = null;
        try {
            this.initOperation();
            Criteria crit = this.sesion.createCriteria(ProductoAporte.class);
            crit.add(Restrictions.eq("idAporte", idAporte));
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
