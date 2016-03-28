/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.logica.admin;

import co.com.codesoftware.persistencia.HibernateUtil;
import co.com.codesoftware.persistencia.entidad.admin.ClienteEntity;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author nicolas
 */
public class ClienteLogica implements AutoCloseable {

    private Session sesion;
    private Transaction tx;

    /**
     * Funcion encargade de buscar un cliente por su Cedula
     *
     * @param clien_cedula
     * @return
     */
    public ClienteEntity obtieneclienteXCedula(String clien_cedula) {
        ClienteEntity cliente;
        try {
            initOperation();
            Criteria crit = sesion.createCriteria(ClienteEntity.class);
            crit.add(Restrictions.eq("cedula", clien_cedula));
            cliente = (ClienteEntity) crit.uniqueResult();
            if (cliente == null && "0".equalsIgnoreCase(clien_cedula)) {
                cliente = new ClienteEntity();
                cliente.setId(this.getMaxId() + 1);
                cliente.setApellidos("generico");
                cliente.setCedula("0");
                cliente.setCorreo("generico@generico.com");
                cliente.setNombres("generico");
                cliente.setTelefono("1234567");
                sesion.save(cliente);
                tx.commit();
                obtieneclienteXCedula("0");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return cliente;
    }

    /**
     * Metodo que retorna el valor maximo de la columna
     *
     * @return
     */
    public Integer getMaxId() {
        Integer result;
        try {
            result = (Integer) sesion.createQuery("select coalesce(max(id),0) from ClienteEntity ").uniqueResult();
        } catch (Exception e) {
            result = null;
        }
        return result;
    }

    /**
     * Metodo con el cual actualizo un cliente
     *
     * @param cliente
     * @return
     */
    public boolean updateCliente(ClienteEntity cliente) {
        try {
            initOperation();
            sesion.update(cliente);
            tx.commit();
            tx = null;
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        } finally {
            //sesion.close();
        }
    }

    /**
     * Funcion con la cual obtengo todos los clientes del sistema
     *
     * @return
     */
    public List<ClienteEntity> getListCliente() {
        List<ClienteEntity> clientes = null;
        try {
            initOperation();
            Criteria crit = sesion.createCriteria(ClienteEntity.class);
            clientes = crit.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return clientes;
    }
    /**
     * Metodo que crea una entidad de cliente
     *
     * @param cliente
     * @return
     */
    public Integer createCliente(ClienteEntity cliente) {
        try {
            initOperation();
            if (cliente.getId() == null) {
                cliente.setId(this.getMaxId() + 1);
            }
            sesion.save(cliente);
            tx.commit();
            tx = null;
            return cliente.getId();
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Metodo con el cual obtengo un cliente por su Id
     *
     * @param id
     * @return
     */
    public ClienteEntity getCliente(Integer id) {
        ClienteEntity cliente = new ClienteEntity();
        try {
            initOperation();
            cliente = (ClienteEntity) sesion.get(ClienteEntity.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return cliente;
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
