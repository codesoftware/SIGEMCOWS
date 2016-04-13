/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.logica.receta;

import co.com.codesoftware.persistencia.HibernateUtil;
import co.com.codesoftware.persistencia.entidad.receta.PrecioRecetaEntity;
import java.util.List;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author nicolas
 */
public class RecetaLogica implements AutoCloseable {

    private Session sesion;
    private Transaction tx;

    /**
     * metodo que consulta las recetas por sede
     *
     * @param sede
     * @return
     * @throws HibernateException
     */
    public List<PrecioRecetaEntity> getRecetas(Integer sede) throws HibernateException {
        List<PrecioRecetaEntity> rta = null;
        try {
            initOperation();
            rta = sesion.createCriteria(PrecioRecetaEntity.class).
                    add(Restrictions.eq("estado", "A")).
                    add(Restrictions.eq("idSede", sede)).
                    setFetchMode("receta", FetchMode.JOIN).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * funcion que obtiene las recetas por codigo
     *
     * @param codigo
     * @param sede
     * @return
     */
    public PrecioRecetaEntity getRecetaXCodigo(String codigo, Integer sede) {
        PrecioRecetaEntity rta = new PrecioRecetaEntity();
        try {
            initOperation();
            rta = (PrecioRecetaEntity) sesion.createCriteria(PrecioRecetaEntity.class).
                    createAlias("receta", "rec").
                    add(Restrictions.eq("rec.codigo", codigo)).
                    add(Restrictions.eq("idSede", sede)).
                    add(Restrictions.eq("estado", "A")).
                    setFetchMode("receta", FetchMode.JOIN)
                    .uniqueResult();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
    /**
     * funcion que consulta las recetas por criterio
     * @param sede
     * @param criterio
     * @return 
     */
    public List<PrecioRecetaEntity> getRecetasXCriterio(Integer sede, String criterio) {
        List<PrecioRecetaEntity> receta = null;
        try {
            initOperation();
            receta = sesion.createCriteria(PrecioRecetaEntity.class).
                    createAlias("receta", "rec").
                    add(Restrictions.like("rec.nombre", criterio, MatchMode.ANYWHERE).ignoreCase()).
                    add(Restrictions.eq("estado", "A")).
                    add(Restrictions.eq("idSede", sede)).
                    setFetchMode("receta", FetchMode.JOIN).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return receta;
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
