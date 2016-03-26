/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.logic;


import co.com.codesoftware.persistence.entity.administracion.ReteFuenteEntity;
import co.com.codesoftware.persistencia.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author john
 */
public class RetencionLogic implements AutoCloseable {

    private Session sesion;
    private Transaction tx;

    /**
     * metodo que consulta las tablas de retencion de fuente por version
     *
     * @param version
     * @return
     */
    public List<ReteFuenteEntity> consultaTablaRetencion(Integer version) {
        List<ReteFuenteEntity> respuesta = null;
        try {
            initOperation();
            respuesta = sesion.createCriteria(ReteFuenteEntity.class)
                    .add(Restrictions.eq("idVersion",version)).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
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
