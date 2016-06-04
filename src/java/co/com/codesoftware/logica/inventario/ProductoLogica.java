/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.logica.inventario;

import co.com.codesoftware.logica.receta.RecetaLogica;
import co.com.codesoftware.persistencia.HibernateUtil;
import co.com.codesoftware.persistencia.ReadFunction;
import co.com.codesoftware.persistencia.entidad.admin.SedeEntity;
import co.com.codesoftware.persistencia.entidad.inventario.ExistenciaXSedeEntity;
import co.com.codesoftware.persistencia.entidad.inventario.PrecioProductoEntity;
import co.com.codesoftware.persistencia.entidad.inventario.ProductoEntity;
import co.com.codesoftware.persistencia.entidad.inventario.PromPonderaEntity;
import co.com.codesoftware.persistencia.entidad.receta.PrecioRecetaEntity;
import co.com.codesoftware.persistencia.utilities.DataType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author nicolas
 */
public class ProductoLogica implements AutoCloseable {

    private Session sesion;
    private Transaction tx;

    /**
     * Funcion con la cual obtengo todos los productos del sistema que tengan
     * precio dentro de una sede
     *
     * @param idSede Integer Sede la cual se tendra como referencia
     * @return
     */
    public List<PrecioProductoEntity> obtieneProductosConPrecioXSede(Integer idSede) {
        List<PrecioProductoEntity> rta = null;
        try {
            this.initOperation();
            Criteria crit = sesion.createCriteria(PrecioProductoEntity.class);
            crit.createAlias("idSede", "sede").add(Restrictions.eq("sede.id", idSede));
            crit.add(Restrictions.eq("estado", "A"));
            crit.setFetchMode("producto", FetchMode.JOIN);
            crit.setFetchMode("producto.referencia", FetchMode.JOIN);
            crit.setFetchMode("producto.marca", FetchMode.JOIN);
            crit.setFetchMode("producto.categoria", FetchMode.JOIN);
            crit.setFetchMode("producto.subcuenta", FetchMode.JOIN);
            crit.addOrder(Order.asc("producto.id"));
            rta = crit.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
    
    /**
     * metodo que consulta los productos por 
     * @param idSede
     * @return 
     */
    public List<ProductoEntity> obtieneProductosXSede(Integer idSede) {
        List<ProductoEntity> rta = null;
        try {
            this.initOperation();
            Criteria crit = sesion.createCriteria(ProductoEntity.class);
            rta = crit.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }


    /**
     * Funcion con la cual obtengo todos los productos del sistema que tengan
     * precio dentro de una sede
     *
     * @param idSede Integer Sede la cual se tendra como referencia
     * @param criterio
     * @return
     */
    public List<PrecioProductoEntity> obtieneProductosConPrecioXSedeYCrit(Integer idSede, String criterio) {
        List<PrecioProductoEntity> rta = null;
        try {
            this.initOperation();
            Criteria crit = sesion.createCriteria(PrecioProductoEntity.class);
            crit.createAlias("idSede", "sede").add(Restrictions.eq("sede.id", idSede));
            crit.createAlias("producto", "prod").add(Restrictions.like("prod.descripcion", criterio, MatchMode.ANYWHERE).ignoreCase());
            crit.add(Restrictions.eq("estado", "A"));
            crit.setFetchMode("producto", FetchMode.JOIN);
            crit.setFetchMode("producto.referencia", FetchMode.JOIN);
            crit.setFetchMode("producto.marca", FetchMode.JOIN);
            crit.setFetchMode("producto.categoria", FetchMode.JOIN);
            crit.setFetchMode("producto.subcuenta", FetchMode.JOIN);
            crit.addOrder(Order.asc("producto.id"));
            rta = crit.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion con la cual valido si el precio que se le dara cumple con los
     * parametros establecidos para poder venderlo
     *
     * @param idProducto
     * @param precio
     * @return
     */
    public String consultaPromPonderado(Integer idProducto, BigDecimal precio) {
        List<String> rta = new ArrayList<>();
        try (ReadFunction rf = new ReadFunction()) {
            rf.setNombreFuncion("FX_VALIDA_PROMEDIO_PONDERADO");
            rf.setNumParam(2);
            rf.addParametro("" + idProducto, DataType.INT);
            rf.addParametro("" + precio, DataType.BIGDECIMAL);
            rf.callFunctionJdbc();
            rta = rf.getRespuestaPg();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta.get(0);
    }

    /**
     * Funcion con la cual se busca el promedio ponderado y las existencias
     * totales de un producto
     *
     * @param idDska
     * @return
     */
    public PromPonderaEntity buscaPromedioPondProd(Integer idDska) {
        PromPonderaEntity rta = null;
        try {
            initOperation();
            Criteria crit = sesion.createCriteria(PromPonderaEntity.class).add(Restrictions.eq("idDska", idDska));
            rta = (PromPonderaEntity) crit.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion con la cual se buscan las existencias por sede de un producto
     *
     * @param idDska
     * @return
     */
    public List<ExistenciaXSedeEntity> buscoExistenciaProd(Integer idDska) {
        List<ExistenciaXSedeEntity> rta = null;
        try {
            initOperation();
            Criteria crit = sesion.createCriteria(ExistenciaXSedeEntity.class);
            crit.add(Restrictions.eq("idDska", idDska));
            crit.setFetchMode("sede", FetchMode.JOIN);
            rta = crit.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion con la cual busco un producto por medio de su codigo y que tenga
     * precio en la sede
     *
     * @param codigo
     * @param idSede
     * @return
     */
    public PrecioProductoEntity buscoProductoEntityXCodigo(String codigo, Integer idSede) {
        PrecioProductoEntity rta = null;
        try {
            initOperation();
            Criteria crit = sesion.createCriteria(PrecioProductoEntity.class);
            crit.createAlias("producto", "prod");
            crit.add(Restrictions.eq("prod.codigo", codigo));
            crit.createAlias("idSede", "sede").add(Restrictions.eq("sede.id", idSede));
            crit.add(Restrictions.eq("estado", "A"));
            crit.setFetchMode("producto", FetchMode.JOIN);
            crit.setFetchMode("producto.referencia", FetchMode.JOIN);
            crit.setFetchMode("producto.marca", FetchMode.JOIN);
            crit.setFetchMode("producto.categoria", FetchMode.JOIN);
            crit.setFetchMode("producto.subcuenta", FetchMode.JOIN);
            rta = (PrecioProductoEntity) crit.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
    /**
     * Funcion con la cual busco un producto por medio de su codigo y que tenga
     * precio en la sede
     *
     * @return
     */
    public PrecioProductoEntity buscoProductoEntityXCodExt(String codExt, Integer idSede) {
        PrecioProductoEntity rta = null;
        try {
            initOperation();
            Criteria crit = sesion.createCriteria(PrecioProductoEntity.class);
            crit.createAlias("producto", "prod");
            crit.add(Restrictions.eq("prod.codigoExt", codExt));
            crit.createAlias("idSede", "sede").add(Restrictions.eq("sede.id", idSede));
            crit.add(Restrictions.eq("estado", "A"));
            crit.setFetchMode("producto", FetchMode.JOIN);
            crit.setFetchMode("producto.referencia", FetchMode.JOIN);
            crit.setFetchMode("producto.marca", FetchMode.JOIN);
            crit.setFetchMode("producto.categoria", FetchMode.JOIN);
            crit.setFetchMode("producto.subcuenta", FetchMode.JOIN);
            rta = (PrecioProductoEntity) crit.uniqueResult();
            if(rta ==null || rta.getId()==0 || rta.getId()==null){
                RecetaLogica logica = new RecetaLogica();
                PrecioRecetaEntity receta = new PrecioRecetaEntity();
                receta = logica.getRecetaXCodigo(codExt, idSede);
                if(receta!=null)
                if(receta.getId()!=0 && receta.getId()!=null){
                    rta = convierteRecetaProducto(receta);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
    
    /**
     * metodo que convierte una receta en producto
     * @param receta
     * @return 
     */
    public PrecioProductoEntity convierteRecetaProducto(PrecioRecetaEntity receta){
        PrecioProductoEntity prd = new PrecioProductoEntity();
        try {
            prd.setEstado(receta.getEstado());
            prd.setId(receta.getId());
            SedeEntity sede = new SedeEntity();
            sede.setId(receta.getIdSede());
            prd.setIdSede(sede);
            prd.setPrecio(receta.getPrecio());
            prd.setPrecioIva(receta.getPrecio());
            prd.setPrecioXCien(receta.getPrecio());
            prd.setPrecioXMil(receta.getPrecio());
            prd.setPrecioXUnidad(receta.getPrecio());
            ProductoEntity entityprd = new ProductoEntity();
            entityprd.setCodigo(receta.getReceta().getCodigo());
            entityprd.setCodigoBarras(receta.getReceta().getCodigo());
            entityprd.setCodigoExt(receta.getReceta().getCodigo());
            entityprd.setDescripcion(receta.getReceta().getDescripcion());
            entityprd.setEstado(receta.getReceta().getEstado());
            entityprd.setId(receta.getReceta().getId());
            entityprd.setIva(receta.getReceta().getIva());
            entityprd.setNombre(receta.getReceta().getNombre());
            prd.setProducto(entityprd);
            

        } catch (Exception e) {
            e.printStackTrace();
        }
        return prd;
    }
    
    /**
     * metodo que consulta las cantidades por producto y sede
     *
     * @param sede
     * @param idProducto
     * @return
     */
    public ExistenciaXSedeEntity consultaCantidad(Integer sede, Integer idProducto) {
        ExistenciaXSedeEntity resultado = new ExistenciaXSedeEntity();
        initOperation();
        try {
            Criteria crit = sesion.createCriteria(ExistenciaXSedeEntity.class)
                    .add(Restrictions.eq("idDska", idProducto)).
                    createAlias("sede","sd").add(Restrictions.eq("sd.id", sede));
            resultado = (ExistenciaXSedeEntity) crit.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }
    
    /**
     * Funcion con la cual busco un producto por medio de su codigo y que tenga
     * precio en la sede
     *
     * @return
     */
    public PrecioProductoEntity buscoProductoEntityXCodBarras(String codBarras, Integer idSede) {
        PrecioProductoEntity rta = null;
        try {
            initOperation();
            Criteria crit = sesion.createCriteria(PrecioProductoEntity.class);
            crit.createAlias("producto", "prod");
            crit.add(Restrictions.eq("prod.codigoBarras", codBarras));
            crit.createAlias("idSede", "sede").add(Restrictions.eq("sede.id", idSede));
            crit.add(Restrictions.eq("estado", "A"));
            crit.setFetchMode("producto", FetchMode.JOIN);
            crit.setFetchMode("producto.referencia", FetchMode.JOIN);
            crit.setFetchMode("producto.marca", FetchMode.JOIN);
            crit.setFetchMode("producto.categoria", FetchMode.JOIN);
            crit.setFetchMode("producto.subcuenta", FetchMode.JOIN);
            rta = (PrecioProductoEntity) crit.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
    
    /**
     * Consulta cantidades en todas las sedes por producto
     * @param idProducto
     * @return 
     */
    public List<ExistenciaXSedeEntity> consultaCantidades(Integer idProducto) {
        List<ExistenciaXSedeEntity> resultado = null;
        initOperation();
        try {
            Criteria crit = sesion.createCriteria(ExistenciaXSedeEntity.class)
                    .add(Restrictions.eq("idDska", idProducto));
            resultado = crit.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
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
