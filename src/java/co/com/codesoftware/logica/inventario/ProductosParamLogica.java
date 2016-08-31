/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.logica.inventario;

import co.com.codesoftware.persistencia.HibernateUtil;
import co.com.codesoftware.persistencia.entidad.inventario.ProductosParamEntity;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author john
 */
public class ProductosParamLogica implements AutoCloseable {

    private Session sesion;
    private Transaction tx;

    /**
     * metodo que inserta un producto a la tabla de parametrizacion
     *
     * @param entidad
     * @return
     */
    public boolean insertarProducto(ProductosParamEntity entidad) {
        try {
            initOperation();
            sesion.save(entidad);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * metodo que actualiza un producto parametrizado
     * @param entidad
     * @return 
     */
    public boolean actualizarProducto(ProductosParamEntity entidad) {
        try {
            initOperation();
            sesion.update(entidad);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * metodo que consulta un producto por su id
     * @param id
     * @return 
     */
    public ProductosParamEntity consultaUnicoProd(Integer id){
        ProductosParamEntity rta = new ProductosParamEntity();
        try {
            initOperation();
            rta = (ProductosParamEntity) sesion.createCriteria(ProductosParamEntity.class)
                    .add(Restrictions.eq("id", id)).uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
    
    /**
     * metodo que consulta la lista de todos los productos parametrizados
     * @return 
     */
    public List<ProductosParamEntity> consultaLista(){
        List<ProductosParamEntity> rta = new ArrayList<>();
        try {
            initOperation();
            rta = sesion.createCriteria(ProductosParamEntity.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
    

    /**
     * metodo para inicializar la session de hibernate
     */
    private void initOperation() {
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            tx = sesion.beginTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * metodo para cerrar la session de hibernate
     *
     * @throws Exception
     */
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
