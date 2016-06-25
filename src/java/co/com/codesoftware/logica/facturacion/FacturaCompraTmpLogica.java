/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.logica.facturacion;

import co.com.codesoftware.persistencia.HibernateUtil;
import co.com.codesoftware.persistencia.entidad.facturacion.FacturaCompraTmpEntity;
import co.com.codesoftware.persistencia.entidad.facturacion.ProdFacCompraTmpEntity;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author john
 */
public class FacturaCompraTmpLogica implements AutoCloseable {

    private Session sesion;
    private Transaction tx;

    /**
     * metodo para insertar factura de compra
     *
     * @param entidad
     * @return
     */
    public Integer insertaFacCompraTmp(FacturaCompraTmpEntity entidad) {
        Integer id = getMaxId() + 1;
        try {
            entidad.setId(id);
            entidad.setEstado("B");
            sesion.save(entidad);
        } catch (Exception e) {
            id = 0;
            e.printStackTrace();
        }
        return id;
    }

    /**
     * metodo que actualiza los datos basicos de la factura temporal de compra
     *
     * @param entidad
     * @return
     */
    public FacturaCompraTmpEntity actualizaFacturaCompraEntity(FacturaCompraTmpEntity entidad) {
        FacturaCompraTmpEntity rta = new FacturaCompraTmpEntity();
        try {
            initOperation();
            sesion.update(entidad);
            rta = entidad;
            rta.setCodigoRespuesta(1);
        } catch (Exception e) {
            rta.setCodigoRespuesta(0);
            rta.setMensajeRespuesta("Error al actualizar" + e.getLocalizedMessage());
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * metodo que consulta las facturas de compra temporales por estado
     *
     * @param estado
     * @return
     */
    public List<FacturaCompraTmpEntity> consultaFacturaTemporalXEstado(String estado) {
        List<FacturaCompraTmpEntity> rta = new ArrayList<>();
        try {
            this.initOperation();
            Criteria crit = sesion.createCriteria(FacturaCompraTmpEntity.class);
            if (!"".equalsIgnoreCase(estado)) {
                crit.add(Restrictions.eq("estado", estado));
            }
            rta = crit.list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;

    }

    /**
     * metodo que consulta la factura temporal de compra por id
     *
     * @param idFactura
     * @return
     */
    public FacturaCompraTmpEntity consultaFacturaTemporal(Integer idFactura) {
        FacturaCompraTmpEntity respuesta = new FacturaCompraTmpEntity();
        try {
            initOperation();
            respuesta = (FacturaCompraTmpEntity) sesion.createCriteria(FacturaCompraTmpEntity.class)
                    .add(Restrictions.eq("id", idFactura)).uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * metodo que inserta los productos de una factura temporal
     *
     * @param listaProd
     * @param idFactura
     * @return
     */
    public List<ProdFacCompraTmpEntity> insertaProdFacTemporal(List<ProdFacCompraTmpEntity> listaProd, Integer idFactura) {
        try {
            borrarProductosAnteriores(idFactura);
            for (ProdFacCompraTmpEntity item : listaProd) {
                item.setIdFacturaCompra(idFactura);
                sesion.save(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaProd;
    }

    /**
     * funcion que borra los productos anteriores de la factura de compra
     * temporal
     *
     * @param idFactura
     * @return
     */
    public boolean borrarProductosAnteriores(Integer idFactura) {
        boolean respuesta = false;
        try {
            this.initOperation();
            List<ProdFacCompraTmpEntity> listaProd = sesion.createCriteria(ProdFacCompraTmpEntity.class)
                    .add(Restrictions.eq("idFacturaCompra", idFactura)).list();
            for (ProdFacCompraTmpEntity item : listaProd) {
                sesion.delete(item);
            }
            respuesta = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * metodo para consultar los productos de una factura de compra temporal
     *
     * @param idFactura
     * @return
     */
    public List<ProdFacCompraTmpEntity> consultaProductos(Integer idFactura) {
        List<ProdFacCompraTmpEntity> rta = new ArrayList<>();
        try {
            initOperation();
            Criteria crit = sesion.createCriteria(ProdFacCompraTmpEntity.class)
                    .add(Restrictions.eq("idFacturaCompra", idFactura));
            rta = crit.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;

    }

    /**
     * metodo que muestra el id de la secuencia de la factura temporala de
     * compra
     *
     * @return
     */
    public Integer getMaxId() {
        Integer result = null;
        try {
            initOperation();
            result = (Integer) sesion.createQuery("select coalesce(max(id),0) from FacturaCompraTmpEntity").uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
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