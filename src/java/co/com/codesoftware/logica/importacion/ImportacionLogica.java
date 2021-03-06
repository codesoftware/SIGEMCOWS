/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.logica.importacion;

import co.com.codesoftware.persistencia.HibernateUtil;
import co.com.codesoftware.persistencia.ReadFunction;
import co.com.codesoftware.persistencia.entidad.importacion.DetalleGastoEntity;
import co.com.codesoftware.persistencia.entidad.importacion.GastoImpoEntity;
import co.com.codesoftware.persistencia.entidad.importacion.ImportacionEntity;
import co.com.codesoftware.persistencia.entidad.importacion.ProductoImportacionEntity;
import co.com.codesoftware.persistencia.utilities.DataType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author nicolas
 */
public class ImportacionLogica implements AutoCloseable {

    private Session sesion;
    private Transaction tx;

    /**
     * Funcion con la cual inserto una importacion
     *
     * @param objEntity
     * @return
     */
    public String insertaImportacion(ImportacionEntity objEntity) {
        String rta = "";
        try {
            this.initOperation();
            objEntity.setFechaCrea(new Date());
            objEntity.setVlrTotal(new BigDecimal(0));
            objEntity.setVlrImpuestos(new BigDecimal(0));
            objEntity.setEstado("C");
            this.sesion.save(objEntity);
            rta = "Ok";
        } catch (Exception e) {
            rta = "Error " + e;
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion con la cual inserto un gasto a una importacion
     *
     * @param gasto
     * @return
     */
    public String insertaGastoImportacion(GastoImpoEntity gasto) {
        String rta = "";
        try {
            this.initOperation();
            gasto.setFechaRegistro(new Date());
            this.sesion.save(gasto);
            rta = "Ok";
        } catch (Exception e) {
            e.printStackTrace();
            rta = "Error " + e;
        }
        return rta;
    }

    /**
     * Funcion con la cual obtengo los gastos de una importacion
     *
     * @return
     */
    public List<GastoImpoEntity> obtenerGastosImportacion(Integer idImpo) {
        List<GastoImpoEntity> rta = null;
        try {
            this.initOperation();
            Criteria crit = this.sesion.createCriteria(GastoImpoEntity.class);
            crit.add(Restrictions.eq("idImpo", idImpo));
            crit.setFetchMode("proveedor", FetchMode.JOIN);
            crit.createAlias("proveedor", "prov");
            crit.setFetchMode("proveedor.ciudad", FetchMode.JOIN);
            crit.setFetchMode("proveedor.municipio", FetchMode.JOIN);
            crit.setFetchMode("proveedor.retenciones", FetchMode.JOIN);
            crit.addOrder(Order.asc("id"));
            rta = crit.list();
            if (rta != null) {
                int i = 0;
                for (GastoImpoEntity item : rta) {
                    i++;
                    item.setConsecutivo(i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * metodo que consulta las importaciones en un rango de fechas
     *
     * @param fechaInicial
     * @param fechaFinal
     * @return
     */
    public List<ImportacionEntity> consultaImportaciones(Date fechaInicial, Date fechaFinal) {
        List<ImportacionEntity> rta = null;
        try {
            this.initOperation();

            Criteria crit = sesion.createCriteria(ImportacionEntity.class);
            if (fechaInicial != null && fechaFinal != null) {
                fechaFinal.setHours(23);
                fechaFinal.setMinutes(59);
                fechaFinal.setSeconds(59);
                crit.add(Restrictions.ge("fechaCrea", fechaInicial))
                        .add(Restrictions.lt("fechaCrea", fechaFinal));
            }
            crit.addOrder(Order.asc("estado"));
            crit.addOrder(Order.desc("id"));
            rta = crit.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion con la cual inserto un producto a una importacion
     *
     * @param idImpo
     * @param codExterno
     * @param cantidad
     * @param precio
     * @return
     */
    public String insertarProductoImportacion(Integer idImpo,
            String codExterno,
            Integer cantidad,
            BigDecimal precio) {
        String rta = "";
        List<String> response = new ArrayList<>();
        try (ReadFunction rf = new ReadFunction()) {
            rf.setNombreFuncion("IN_INSERTA_PROD_IMPORTACION");
            rf.setNumParam(4);
            rf.addParametro("" + idImpo, DataType.INT);
            rf.addParametro(codExterno, DataType.TEXT);
            rf.addParametro("" + cantidad, DataType.INT);
            rf.addParametro(precio.toString(), DataType.BIGDECIMAL);
            rf.callFunctionJdbc();
            response = rf.getRespuestaPg();
            rta = response.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            rta = "Error de ejecucion del proceso " + e;
        }
        return rta;
    }

    /**
     * Funcion con la cual convierto los productos de dolares a pesos
     *
     * @param idImpo
     * @param trm
     * @param tazaProm
     * @return
     */
    public String insertarTazasCambio(Integer idImpo,
            BigDecimal trm,
            BigDecimal tazaProm) {
        String rta = "";
        List<String> response = new ArrayList<>();
        try (ReadFunction rf = new ReadFunction()) {
            rf.setNombreFuncion("im_convierte_dolares_importacion");
            rf.setNumParam(3);
            rf.addParametro("" + idImpo, DataType.INT);
            rf.addParametro(trm.toString(), DataType.BIGDECIMAL);
            rf.addParametro(tazaProm.toString(), DataType.BIGDECIMAL);
            rf.callFunctionJdbc();
            response = rf.getRespuestaPg();
            rta = response.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion con la cual obtengo los productos que tiene una importacion
     *
     * @param idImportacion
     * @return
     */
    public List<ProductoImportacionEntity> obtenerProductosImportacion(Integer idImportacion) {
        List<ProductoImportacionEntity> rta = null;
        try {
            this.initOperation();
            Criteria crit = this.sesion.createCriteria(ProductoImportacionEntity.class);
            crit.add(Restrictions.eq("idImpo", idImportacion));
            crit.setFetchMode("producto", FetchMode.JOIN);
            crit.addOrder(Order.asc("id"));
            rta = crit.list();
            int i = 0;
            if (rta != null) {
                for (ProductoImportacionEntity item : rta) {
                    i++;
                    item.setConsecutivo(i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion con la cual inserto un detalle al gasto
     *
     * @return
     */
    public String insertaDetalleGasto(DetalleGastoEntity gasto) {
        String rta = "";
        try {
            this.initOperation();
            this.initOperation();
            gasto.setFechaRegi(new Date());
            this.sesion.save(gasto);
            rta = "Ok";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion con la cual obtengo el detalle de un gasto basandome en su id
     *
     * @return
     */
    public List<DetalleGastoEntity> obtenerDetalleGasto(Integer idGasto) {
        List<DetalleGastoEntity> rta = null;
        try {
            this.initOperation();
            Criteria crit = this.sesion.createCriteria(DetalleGastoEntity.class);
            crit.add(Restrictions.eq("idGasto", idGasto));
            crit.setFetchMode("idAuxconta", FetchMode.JOIN);
            rta = crit.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion con la cual llamo al proceso que realizara el proceso de
     * importacion dentro del sistema
     *
     * @param idTius
     * @param idImpo
     * @return
     */
    public String ejecutaProcesoImportacion(Integer idTius, Integer idImpo, Integer idSede) {
        String rta = "";
        List<String> response = new ArrayList<>();
        try (ReadFunction rf = new ReadFunction()) {
            rf.setNombreFuncion("IM_FEJECUTAIMPORTACION");
            rf.setNumParam(3);
            rf.adicionarParametro(idTius, DataType.INT);
            rf.adicionarParametro(idImpo, DataType.INT);
            rf.adicionarParametro(idSede, DataType.INT);
            rf.llamarFuncion();
            response = rf.getRespuestaPg();
            rta = response.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            rta = "Error de ejecucion del proceso " + e;
        }
        return rta;
    }

    /**
     * Funcion con la cual borro todos los productos de una importacion
     *
     * @return
     */
    public String borrarProductosImportacion(Integer idImpo) {
        String rta = "";
        try {
            this.initOperation();
            Query query = this.sesion.createQuery("delete ProductoImportacionEntity where idImpo = :idImpo");
            query.setParameter("idImpo", idImpo);
            int result = query.executeUpdate();
            if (result > 0) {
                rta = "Ok Eliminados " + result + " de la importacion";
            } else {
                rta = "Error al eliminar los productos ";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion con la cual obtengo la informacion basica de la importacion
     *
     * @return
     */
    public ImportacionEntity obtenerImportacion(Integer idImpo) {
        ImportacionEntity objEntity = null;
        try {
            this.initOperation();
            objEntity = (ImportacionEntity) this.sesion.createCriteria(ImportacionEntity.class).add(Restrictions.eq("id", idImpo)).uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objEntity;
    }
    /**
     * Funcion con la cual elimino un detalle de una importacion
     * @param idImpo
     * @param idDet
     * @return 
     */
    public String eliminarDetalleGasto(Integer idDet){
        String rta= "";
        try {
            this.initOperation();
            Query query = this.sesion.createQuery("delete DetalleGastoEntity where id = :id");
            query.setInteger("id", idDet);
            query.executeUpdate();
            rta = "Ok";
        } catch (Exception e) {
            e.printStackTrace();
            rta = "Error " + e;
        }
        return rta;
    }
    /**
     * Funcion con la cual se elimina un gasto
     * @param idGasto
     * @return 
     */
    public String eliminarGasto(Integer idGasto){
        String rta = "";
        try {
            this.initOperation();
            GastoImpoEntity objEntity = new GastoImpoEntity();
            objEntity.setId(idGasto);
            this.sesion.delete(objEntity);
            this.tx.commit();
            rta = "Ok";
        } catch (Exception e) {
            e.printStackTrace();
            rta = "Error "+ e;
        }
        return rta;
    }

    /**
     * metodo que inicia la sesion de base de datos
     */
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
