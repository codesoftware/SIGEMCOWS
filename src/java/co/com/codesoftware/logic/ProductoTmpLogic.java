/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.logic;


import co.com.codesoftware.persistence.entity.administracion.RespuestaEntity;
import co.com.codesoftware.persistence.entity.productos.ProductoTmpEntity;
import co.com.codesoftware.persistencia.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;

/**
 *
 * @author root
 */
public class ProductoTmpLogic implements AutoCloseable {

    private Session sesion;
    private Transaction tx;

    /**
     * metodo que inserta en la tabla
     *
     * @param productos
     * @return
     */
    public RespuestaEntity insertaProductoTmp(List<ProductoTmpEntity> productos) {
        this.initOperation();
        RespuestaEntity respuesta = new RespuestaEntity();
        List<ProductoTmpEntity> listaBorrar = null;
        try {
            Criteria crit = sesion.createCriteria(ProductoTmpEntity.class);
            listaBorrar = crit.list();
            if (!listaBorrar.isEmpty()) {
                for (ProductoTmpEntity iteratorBorrar : listaBorrar) {
                    sesion.delete(iteratorBorrar);
                }
            }
            for (ProductoTmpEntity iterator : productos) {
                iterator.setId(consultaMax());
                sesion.save(iterator);
            }
            respuesta.setCodigoRespuesta(1);
            respuesta.setDescripcionRespuesta("EXITOSO");
            respuesta.setMensajeRespuesta("OK");

        } catch (Exception e) {
            e.printStackTrace();
            respuesta.setCodigoRespuesta(0);
            respuesta.setDescripcionRespuesta(e.getMessage());
            respuesta.setMensajeRespuesta("ERROR" + e.getMessage());

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

    /**
     * Funcion que consulta todos los productos de la tabla temporal
     *
     * @return
     */
    public List<ProductoTmpEntity> consultaTablaTemporal() {
        this.initOperation();
        List<ProductoTmpEntity> respuesta = null;
        try {
            Criteria crit = sesion.createCriteria(ProductoTmpEntity.class);
            respuesta = crit.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * Funcion para consultar el maximo de una tabla
     *
     * @return
     */
    public Integer consultaMax() {
        Criteria crit = sesion.createCriteria(ProductoTmpEntity.class);
        crit.setProjection(Projections.max("id"));
        Integer count = 1;
        try {
            count = (Integer) crit.uniqueResult() + 1;
        } catch (Exception e) {
            count = 1;
        }
        return count;
    }
}
