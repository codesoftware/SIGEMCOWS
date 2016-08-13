/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.logica.admin;


import co.com.codesoftware.persistencia.HibernateUtil;
import co.com.codesoftware.persistencia.entidad.admin.SocioEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author John
 */
public class SocioLogica implements AutoCloseable {

    private Session sesion;
    private Transaction tx;

    /**
     * metodo que inserta un socio a la base de datos
     *
     * @param entidad
     * @return
     */
    public String insertarSocio(SocioEntity entidad) {
        String respuesta = "Error";
        try {
            entidad.setFechaCreacion(new Date());
            initOperation();
            sesion.save(entidad);
            respuesta = "OK";
        } catch (Exception e) {
            respuesta += e.toString();
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * metodo que consulta todos los socios
     *
     * @return
     */
    public List<SocioEntity> consultaSocios() {
        List<SocioEntity> respuesta = new ArrayList<>();
        try {
            initOperation();
            respuesta = sesion.createCriteria(SocioEntity.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * metodo que consulta en la base de datos un socio por id
     *
     * @param id
     * @return
     */
    public SocioEntity consultaSocio(Integer id) {
        SocioEntity respuesta = new SocioEntity();
        try {
            initOperation();
            respuesta = (SocioEntity) sesion.createCriteria(SocioEntity.class).
                    add(Restrictions.eq("id", id)).uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * metodo que actualiza un socio
     * @param socio
     * @return 
     */
    public String actualizaSocio(SocioEntity socio) {
        String respuesta = "Error";
        try {
            initOperation();
            sesion.update(socio);
            respuesta = "OK";
        } catch (Exception e) {
            respuesta += e.toString();
            e.printStackTrace();
        }
        return respuesta;
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
     * metodo que inicializa la sesion
     *
     * @throws HibernateException
     */
    public void initOperation() throws HibernateException {
        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
    }

}
