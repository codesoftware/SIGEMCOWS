/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.logica.importacion;

import co.com.codesoftware.persistencia.HibernateUtil;
import co.com.codesoftware.persistencia.entidad.importacion.ProveedorInterEntity;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author nicolas
 */
public class ProveedorIntLogica implements AutoCloseable {

    private Session sesion;
    private Transaction tx;

    /**
     * Funcion con la cual se inserta un proveedor
     *
     * @return
     */
    public String insertaProveedor(ProveedorInterEntity objEntity) {
        String rta = "";
        try {
            this.initOperation();
            Integer id = this.getSecunceProvInter();
            objEntity.setId(id);
            this.sesion.save(objEntity);
            rta = "Ok-"+id;
        } catch (Exception e) {
            e.printStackTrace();
            rta ="Error " + e;
        }
        return rta;
    }
    /**
     * Funcion con la cual consulta los proveedores internacionales
     * @return 
     */
    public List<ProveedorInterEntity> consultaProveedoresInterna(){
        List<ProveedorInterEntity> rta = null;
        try {
            this.initOperation();
            Criteria crit = this.sesion.createCriteria(ProveedorInterEntity.class);
            rta = crit.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
    
    /**
     * Funcion la cual obtiene el valor unico de transaccion el cual tendran
     * todos los items de la transaccion
     *
     * @return
     */
    public Integer getSecunceProvInter() {
        Integer result = null;
        try {
            result = (Integer) sesion.createSQLQuery("select cast(nextval('im_tpvin_pvin_pvin_seq')as int) ").uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

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
