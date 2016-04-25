/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.logic;

import co.com.codesoftware.persistencia.HibernateUtil;

import co.com.codesoftware.persistence.entities.FacturaCompraGeneralEntity;
import co.com.codesoftware.persistence.entities.simple.PrecioProdSimpleEntity;
import co.com.codesoftware.persistence.entities.simple.ProductoSimpleEntity;
import co.com.codesoftware.persistencia.entidad.inventario.CategoriaEntity;
import co.com.codesoftware.persistencia.entidad.inventario.ProductoEntity;
import co.com.codesoftware.persistence.entity.administracion.ProveedoresEntity;
import co.com.codesoftware.persistence.entity.administracion.RespuestaEntity;
import co.com.codesoftware.persistence.entity.productos.FacturaCompraEntity;
import co.com.codesoftware.persistence.entity.productos.FacturaCompraTotalEntity;
import co.com.codesoftware.persistence.entity.productos.ImagenesFacCompraEntity;
import co.com.codesoftware.persistence.entity.productos.KardexProductoEntity;
import co.com.codesoftware.persistence.entity.productos.PagoFacCompraEntity;
import co.com.codesoftware.persistence.entity.productos.PorcentajePrecioEntity;
import co.com.codesoftware.persistence.entity.productos.ProductoFacCompraEntity;
import co.com.codesoftware.persistencia.ReadFunction;
import co.com.codesoftware.persistencia.utilities.DataType;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author root
 */
public class ProductoLogic implements AutoCloseable {

    private Session sesion;
    private Transaction tx;
    private String valida;

    /**
     * Funcion que consulta a la base de datos los productos mediante los
     * filtros y por defecto consulta el estado activo
     *
     * @param codigo
     * @param nombre
     * @param descripcion
     * @return
     */
    public List<ProductoEntity> consultaProductos(String codigo, String nombre, String descripcion) {
        List<ProductoEntity> respuesta = null;
        try {
            this.initOperation();
            Criteria crit = sesion.createCriteria(ProductoEntity.class);
            if (codigo != null && !"".equalsIgnoreCase(codigo)) {
                crit.add(Restrictions.like("codigo", codigo, MatchMode.ANYWHERE));
            }
            if (nombre != null && !"".equalsIgnoreCase(nombre)) {
                crit.add(Restrictions.like("nombre", nombre, MatchMode.ANYWHERE));
            }
            if (descripcion != null && !"".equalsIgnoreCase(descripcion)) {
                crit.add(Restrictions.like("descripcion", descripcion, MatchMode.ANYWHERE));
            }
            crit.add(Restrictions.eq("estado", "A"));
            respuesta = crit.list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    public ProductoEntity consultaProductosFiltro(String codigoBarras, String codigoExterno) {
        ProductoEntity respuesta = null;
        System.out.println("" + codigoBarras);
        try {
            this.initOperation();
            Criteria crit = sesion.createCriteria(ProductoEntity.class);
            if (codigoBarras != null && !"".equalsIgnoreCase(codigoBarras)) {
                crit.add(Restrictions.like("codigoBarras", codigoBarras));
            } else if (codigoExterno != null && !"".equalsIgnoreCase(codigoExterno)) {
                crit.add(Restrictions.like("codigoExt", codigoExterno));
            }
            crit.add(Restrictions.eq("estado", "A"));
            respuesta = (ProductoEntity) crit.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * Funcion que consulta los prodiuctos por los tres filtros
     *
     * @param categoria
     * @param subcategoria
     * @param marca
     * @return
     */
    public List<ProductoEntity> consultaProductosCatSubMarc(Integer categoria, String subcategoria, String marca) {
        List<ProductoEntity> respuesta = null;
        try {
            CategoriaEntity categoriaEntity = new CategoriaEntity();
            categoriaEntity.setId(categoria);
            this.initOperation();
            Criteria crit = sesion.createCriteria(ProductoEntity.class);
            if (subcategoria != null && !"".equalsIgnoreCase(subcategoria)) {
                crit.createAlias("referencia", "r").add(Restrictions.like("r.descripcion", subcategoria, MatchMode.ANYWHERE));
            }
            if (marca != null && "".equalsIgnoreCase(marca)) {
                crit.createAlias("marca", "m").add(Restrictions.like("nombre", marca, MatchMode.ANYWHERE));
            }
            crit.add(Restrictions.eq("estado", "A"));
            crit.add(Restrictions.eq("categoria", categoriaEntity));
            respuesta = crit.list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * Funcion la cual busca un producto por medio de su codigo externo
     *
     * @param codExterno
     * @return
     */
    public List<ProductoEntity> consultaProductosXCodigoExt(String codExterno) {
        List<ProductoEntity> respuesta = null;
        try {
            this.initOperation();
            Criteria crit = sesion.createCriteria(ProductoEntity.class)
                    .add(Restrictions.like("codigoExt", codExterno, MatchMode.ANYWHERE).ignoreCase())
                    .setFetchMode("referencia", FetchMode.JOIN)
                    .setFetchMode("marca", FetchMode.JOIN)
                    .setFetchMode("categoria", FetchMode.JOIN)
                    .setFetchMode("subcuenta", FetchMode.JOIN); 
            respuesta = crit.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * Funcion que inserta el registro de un producto
     *
     * @param producto
     * @return
     */
    public RespuestaEntity insertaProducto(ProductoEntity producto) {
        RespuestaEntity respuesta = new RespuestaEntity();
        try {
            ProveedoresEntity entity = new ProveedoresEntity();
            this.initOperation();
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, 0);
            producto.setCodigo("1-" + consultaCount());
            producto.setEstado("A");
            producto.setFechaIngreso(c.getTime());
            producto.setId(consultaCount());
            producto.setIva("S");
            producto.setPorcentajeIva(16);
            producto.setSubcuenta(null);
            sesion.save(producto);
            respuesta.setCodigoRespuesta(1);
            respuesta.setDescripcionRespuesta("OK");
            respuesta.setMensajeRespuesta("INGRESO EXITOSO");
        } catch (Exception e) {
            respuesta.setCodigoRespuesta(0);
            respuesta.setMensajeRespuesta(e.getMessage());
            respuesta.setDescripcionRespuesta(e.toString());
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * Funcion que consulta el count de la tabla de productos
     *
     * @return
     */
    public Integer consultaCount() {
        Criteria crit = sesion.createCriteria(ProductoEntity.class);
        crit.setProjection(Projections.max("id"));
        Integer count = (Integer) crit.uniqueResult() + 1;
        return count;
    }

    /**
     * Funcion con la cual registro un producto por medio de una funcion de base
     * de datos
     *
     * @param objEntity
     * @return
     */
    public RespuestaEntity registraProductoFunc(ProductoEntity objEntity) {
        RespuestaEntity respuesta = new RespuestaEntity();
        List<String> response = new ArrayList<>();
        try (ReadFunction rf = new ReadFunction();) {

            rf.setNombreFuncion("IN_REGISTRA_PRODUCTO");
            rf.setNumParam(6);
            rf.addParametro("" + objEntity.getReferencia().getId(), co.com.codesoftware.persistencia.utilities.DataType.INT.INT);
            rf.addParametro(objEntity.getDescripcion().toUpperCase(), DataType.TEXT);
            rf.addParametro("" + objEntity.getMarca().getId(), DataType.INT);
            rf.addParametro("" + objEntity.getCategoria().getId(), DataType.INT);
            rf.addParametro(objEntity.getCodigoExt(), DataType.TEXT);
            rf.addParametro(objEntity.getUbicacion(), DataType.TEXT);
            boolean valida = rf.callFunctionJdbc();
            rf.ListResponsePg();
            if (valida) {
                response = rf.getRespuestaPg();
                if (response.get(0).toUpperCase().contains("OK")) {
                    respuesta.setCodigoRespuesta(1);
                    respuesta.setMensajeRespuesta("Ok");
                    respuesta.setDescripcionRespuesta("INGRESO EXITOSO");
                } else {
                    respuesta.setCodigoRespuesta(0);
                    respuesta.setMensajeRespuesta("Error");
                    respuesta.setDescripcionRespuesta(response.get(0));
                }

            } else {
                respuesta.setCodigoRespuesta(0);
                respuesta.setMensajeRespuesta("Error");
                respuesta.setDescripcionRespuesta("Error al llamar a la funcion de registrar un producto");
            }

        } catch (Exception e) {
            respuesta.setCodigoRespuesta(0);
            respuesta.setMensajeRespuesta(e.getMessage());
            respuesta.setDescripcionRespuesta(e.toString());
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * Funcion que ejecuta un procedimiento almacenado que consulta los datos
     * que fueron ingresados por excel
     *
     * @return
     */
    public RespuestaEntity registraDatosExcelFunc() {
        RespuestaEntity respuesta = new RespuestaEntity();
        List<String> response = new ArrayList<>();
        try (ReadFunction rf = new ReadFunction()) {
            rf.setNombreFuncion("in_finsertaexceltmp");
            rf.setNumParam(0);
            boolean valida = rf.callFunctionJdbc();
            if (valida) {
                response = rf.getRespuestaPg();
                if ("Ok".equalsIgnoreCase(response.get(0))) {
                    if (this.ingresaExistenciasProductos()) {
                        respuesta.setCodigoRespuesta(0);
                        respuesta.setMensajeRespuesta("Ok");
                        respuesta.setDescripcionRespuesta("INGRESO EXITOSO");
                    } else {
                        respuesta.setCodigoRespuesta(1);
                        respuesta.setMensajeRespuesta("Error al ingresar las existencias");
                        respuesta.setDescripcionRespuesta("Error al llamar a la funcion que ingresa existencias y precios (ingresaExistenciasProductos) , por favor verifique los porcentajes para los precios." + this.valida);
                    }
                } else {
                    respuesta.setCodigoRespuesta(1);
                    respuesta.setMensajeRespuesta("Error al ingresar los productos masivamente");
                    respuesta.setDescripcionRespuesta("Error enviado desde postgres... " + response.get(0));
                }

            } else {
                respuesta.setCodigoRespuesta(1);
                respuesta.setMensajeRespuesta(rf.getRespuesta());
                respuesta.setDescripcionRespuesta("Error al llamar a la funcion de ingreso de productos por excel");
            }
        } catch (Exception e) {
            e.printStackTrace();
            respuesta.setCodigoRespuesta(0);
            respuesta.setMensajeRespuesta(e.getMessage());
            respuesta.setDescripcionRespuesta(e.toString());
        }
        return respuesta;

    }

    /**
     * Funcion con la cual ingreso las existencias de los productos al
     * inventario inicial
     *
     * @return
     */
    public boolean ingresaExistenciasProductos() {
        System.out.println("Inicio Cargue de existencias y precios");
        boolean validaProc = true;
        List<String> response = new ArrayList<>();
        Integer registros = this.cuentaNumeroRegistrosTemporales();
        if (registros > 0) {
            Integer ciclo = (registros / 100) + 1;
            for (int i = 0; i < ciclo; i++) {
                if (validaProc) {
                    System.out.println("Registros procesados: " + (i * 100));
                    try (ReadFunction rf = new ReadFunction();) {
                        rf.setNombreFuncion("FA_INSERTA_PROD_MASIVO");
                        rf.setNumParam(0);
                        boolean valida = rf.callFunctionJdbc();
                        if (valida) {
                            response = rf.getRespuestaPg();
                            if ("Ok".equalsIgnoreCase(response.get(0))) {
                                validaProc = true;
                            } else {
                                this.valida = response.get(0);
                                validaProc = false;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        return false;
                    }

                }
            }
        }
        return validaProc;
    }

    /**
     * Funcion con la cual obtengo el numero de registros que se encuentran en
     * la tabla temporal
     *
     * @return
     */
    public Integer cuentaNumeroRegistrosTemporales() {
        Integer rta = 0;
        try (ReadFunction rf = new ReadFunction()) {
            String query = "select count(1) conteo from in_tmpidexc";
            ResultSet rs = rf.enviaQuery(query);
            while (rs.next()) {
                rta = rs.getInt("conteo");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion con la cual cambio el precio del producto
     *
     * @param user
     * @param sede
     * @param dska
     * @param precio
     * @return
     */
    public RespuestaEntity cambioPrecioProducto(Integer user, Integer sede, Integer dska, BigDecimal precio, BigDecimal precioUni, BigDecimal precioDec, BigDecimal precioMil, String estatic) {
        RespuestaEntity rta = new RespuestaEntity();
        try (ReadFunction rf = new ReadFunction()) {
            rf.setNombreFuncion("IN_PARA_PRECIO_PROD_PORCE");
            rf.setNumParam(8);
            rf.addParametro("" + user, DataType.INT);
            rf.addParametro("" + sede, DataType.INT);
            rf.addParametro("" + dska, DataType.INT);
            rf.addParametro(precio.toString(), DataType.BIGDECIMAL);
            rf.addParametro(estatic, DataType.TEXT);
            rf.addParametro(precioUni.toString(), DataType.BIGDECIMAL);
            rf.addParametro(precioDec.toString(), DataType.BIGDECIMAL);
            rf.addParametro(precioMil.toString(), DataType.BIGDECIMAL);
            boolean valida = rf.callFunctionJdbc();
            if (valida) {
                List<String> response = new ArrayList<>();
                response = rf.getRespuestaPg();
                if ("OK".equalsIgnoreCase(response.get(0))) {
                    rta.setCodigoRespuesta(1);
                    rta.setMensajeRespuesta("Ok");
                } else {
                    rta.setCodigoRespuesta(0);
                    rta.setMensajeRespuesta(response.get(0));
                }
            } else {
                rta.setCodigoRespuesta(0);
                rta.setMensajeRespuesta("Error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            rta.setCodigoRespuesta(0);
            rta.setMensajeRespuesta("Error");
            rta.setDescripcionRespuesta(e.toString());
        }
        return rta;
    }

    /**
     * metodo que consulta los productos sin dependencias, es decir sin
     * joincolumn
     *
     * @return
     */
    public List<ProductoSimpleEntity> consultaProductos() {
        List<ProductoSimpleEntity> respuesta = null;
        try {
            initOperation();
            respuesta = sesion.createCriteria(ProductoSimpleEntity.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * Consutla un producto por id
     *
     * @param idDska identificador primario de la tabla in_tdska
     * @return
     */
    public ProductoSimpleEntity consultaProductoXId(Integer idDska) {
        ProductoSimpleEntity respuesta = null;
        try {
            initOperation();
            Criteria crit = sesion.createCriteria(ProductoSimpleEntity.class);
            crit.add(Restrictions.eq("id", idDska));
            respuesta = (ProductoSimpleEntity) crit.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * Funcion con la cual valido la exitencia de un codigo externo para que no
     * halla duplicidad
     *
     * @param idDska
     * @param codExterno
     * @return
     */
    public String validaCodigoExterno(Integer idDska, String codExterno) {
        String rta = "";
        try {
            initOperation();
            Query query = sesion.createQuery("select count(*) from ProductoSimpleEntity where codigoExt = :codExt and id <> :idDska");
            query.setParameter("codExt", codExterno);
            query.setInteger("idDska", idDska);
            String count = "" + query.uniqueResult();
            if (Integer.parseInt(count) == 0) {
                rta = "unico";
            } else {
                rta = "duplicado";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion con la cual se realiza la actualizacion de un producto en
     * especifico
     *
     * @param producto
     * @return
     */
    public String actualizaProductoSimple(ProductoSimpleEntity producto) {
        String rta = "";
        try {
            initOperation();
            sesion.update(producto);
            tx.commit();
            tx = null;
            rta = this.actualizaNombreProducto(producto.getId());
        } catch (Exception e) {
            e.printStackTrace();
            rta = "Error " + e;
        }
        return rta;
    }

    /**
     * Funcion la cual llama el cambio de nombre por id del producto
     *
     * @param idDska
     * @return
     */
    public String actualizaNombreProducto(Integer idDska) {
        String rta = "";
        try (ReadFunction rf = new ReadFunction();) {
            List<String> response = null;
            rf.setNombreFuncion("cambio_nombre_prod_dska");
            rf.setNumParam(1);
            rf.addParametro("" + idDska, DataType.INT);
            boolean valida = rf.callFunctionJdbc();
            if (valida) {
                response = rf.getRespuestaPg();
                if ("Ok".equalsIgnoreCase(response.get(0))) {
                    return "Ok";
                } else {
                    return "Error " + response.get(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion con la cual busco el historico de precios que a tenido un
     * producto en una sede en especifico
     *
     * @param idDska
     * @param idSede
     * @return
     */
    public List<PrecioProdSimpleEntity> buscaHisPrecioProdXSede(Integer idDska, Integer idSede) {
        List<PrecioProdSimpleEntity> rta = null;
        try {
            initOperation();
            Query query = sesion.createQuery("from PrecioProdSimpleEntity  where idDska = :idDska and idSede = :idSede order by id desc ");
            query.setInteger("idDska", idDska);
            query.setInteger("idSede", idSede);
            rta = query.list();
        } catch (Exception e) {
            e.printStackTrace();
            rta = null;
        }
        return rta;
    }

    /**
     * Funcion con la cual se llama funcion con la cual se parametrizan los
     * porcentajes de precios masivos
     *
     * @param idCate
     * @param idRefe
     * @param idMarca
     * @param idSede
     * @param porcUnid
     * @param porcCent
     * @param porcMill
     * @param porcBase
     * @return
     */
    public String insercionParamPrecioMasivo(Integer idCate, Integer idRefe, Integer idMarca, Integer idSede, Integer porcUnid, Integer porcCent, Integer porcMill, Integer porcBase, Integer idTius) {
        String rta = "";
        List<String> response = null;
        try (ReadFunction rf = new ReadFunction()) {
            rf.setNombreFuncion("IN_PARA_PORC_PRECIOS");
            rf.setNumParam(9);
            rf.addParametro(idSede.toString(), DataType.INT);
            rf.addParametro(idTius.toString(), DataType.INT);
            rf.addParametro(idCate.toString(), DataType.INT);
            rf.addParametro(idRefe.toString(), DataType.INT);
            rf.addParametro(idMarca.toString(), DataType.INT);
            rf.addParametro(porcBase.toString(), DataType.INT);
            rf.addParametro(porcUnid.toString(), DataType.INT);
            rf.addParametro(porcCent.toString(), DataType.INT);
            rf.addParametro(porcMill.toString(), DataType.INT);
            boolean valida = rf.callFunctionJdbc();
            if (valida) {
                response = rf.getRespuestaPg();
                if ("Ok".equalsIgnoreCase(response.get(0))) {
                    return "Ok";
                } else {
                    return "Error " + response.get(0);
                }
            }
        } catch (Exception e) {
            rta = "Error " + e;
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion con la cual se realiza la consulta
     *
     * @param estado
     * @return
     */
    public List<PorcentajePrecioEntity> buscaPorcentajePrecio(String estado) {
        List<PorcentajePrecioEntity> rta = null;
        try {
            initOperation();
            Query query = sesion.createQuery("from PorcentajePrecioEntity where estado = :estado ");
            query.setString("estado", estado);
            rta = query.list();
        } catch (Exception e) {
            e.printStackTrace();
            rta = null;
        }
        return rta;
    }

    /**
     * Funcion con la cual se realiza la consulta de parametros basandose en una
     * sede y una categoria como minimo
     *
     * @param estado
     * @param idSede
     * @param idCate
     * @return
     */
    public List<PorcentajePrecioEntity> buscaPorcentajePrecioXFiltros(String estado, Integer idSede, Integer idCate, Integer idRefe, Integer idMarca) {
        List<PorcentajePrecioEntity> rta;
        try {
            initOperation();
            Criteria criteria = sesion.createCriteria(PorcentajePrecioEntity.class).
                    createAlias("sede", "sed").add(Restrictions.eq("sed.id", idSede)).
                    createAlias("referencia", "ref").createAlias("marca", "marc").add(Restrictions.eq("estado", estado)).
                    createAlias("categoria", "cate").add(Restrictions.eq("cate.id", idCate)).addOrder(Order.asc("fecha")).
                    addOrder(Order.asc("sed.nombre")).addOrder(Order.asc("cate.descripcion")).
                    addOrder(Order.asc("ref.descripcion")).addOrder(Order.asc("marc.descripcion"));
            if (!"-1".equalsIgnoreCase(idRefe.toString().trim())) {
                criteria.add(Restrictions.eq("ref.id", idRefe));
            }
            if (!"-1".equalsIgnoreCase(idMarca.toString().trim())) {
                criteria.add(Restrictions.eq("marc.id", idMarca));
            }

            rta = criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
            rta = null;
        }
        return rta;
    }

    /**
     * Funcion con la cual llamo el proceso que guarda los
     * parametros(porcentajes) con los cuales se ejecutara el cambio de precio
     *
     * @param idSede
     * @param idCate
     * @param idRefe
     * @param idMarca
     * @return
     */
    public String ejecutaProcesoPrecioMasivo(Integer idSede, Integer idCate, Integer idRefe, Integer idMarca, Integer idTius) {
        String rta = "";
        List<String> response = null;
        try (ReadFunction rf = new ReadFunction()) {
            rf.setNombreFuncion("IN_EJECUTA_PROC_PRECIOS");
            rf.setNumParam(5);
            rf.addParametro(idSede.toString(), DataType.INT);
            rf.addParametro(idTius.toString(), DataType.INT);
            rf.addParametro(idCate.toString(), DataType.INT);
            rf.addParametro(idRefe.toString(), DataType.INT);
            rf.addParametro(idMarca.toString(), DataType.INT);
            boolean valida = rf.callFunctionJdbc();
            if (valida) {
                response = rf.getRespuestaPg();
                if ("Ok".equalsIgnoreCase(response.get(0))) {
                    return "Ok";
                } else {
                    return "Error " + response.get(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion con la cual ejecuto el proceso de precios masivos
     *
     * @param idSede
     * @param idCate
     * @param idRefe
     * @param idMarca
     * @return
     */
    public String ejecutaProcesoPrecioMasivoEnCate(Integer idSede, Integer idCate, Integer idRefe, Integer idMarca, Integer idTius) {
        String rta = "";
        List<String> response = null;
        try (ReadFunction rf = new ReadFunction()) {
            rf.setNombreFuncion("IN_EJECUTA_MASIVOS");
            rf.setNumParam(5);
            rf.addParametro(idSede.toString(), DataType.INT);
            rf.addParametro(idTius.toString(), DataType.INT);
            rf.addParametro(idCate.toString(), DataType.INT);
            rf.addParametro(idRefe.toString(), DataType.INT);
            rf.addParametro(idMarca.toString(), DataType.INT);
            boolean valida = rf.callFunctionJdbc();
            if (valida) {
                response = rf.getRespuestaPg();
                if ("Ok".equalsIgnoreCase(response.get(0).split("-")[0])) {
                    return response.get(0);
                } else {
                    return "Error " + response.get(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * funcion que consulta las facturas por filtros
     *
     * @param idProveedor
     * @param fechaInicial
     * @param fechaFinal
     * @param estado
     * @param imagen
     * @return
     */
    public List<FacturaCompraTotalEntity> consultaFacturas(Integer idProveedor, Date fechaInicial, Date fechaFinal, String estado, String imagen) {
        List<FacturaCompraTotalEntity> lista = null;
        try {
            initOperation();
            Criteria criteria = sesion.createCriteria(FacturaCompraTotalEntity.class)
                    .setFetchMode("proveedor", FetchMode.JOIN);
            if (idProveedor != -1) {
                criteria.createAlias("proveedor", "pr").add(Restrictions.eq("pr.id", idProveedor));
            }
            if (fechaInicial != null && fechaFinal != null) {
                criteria.add(Restrictions.ge("fechaCreacion", fechaInicial));
                criteria.add(Restrictions.lt("fechaCreacion", fechaFinal));
            }
            if (estado != null && "".equalsIgnoreCase(estado) && "-1".equalsIgnoreCase(estado)) {
                criteria.add(Restrictions.eq("estado", estado));
            }
            if (imagen != null && "".equalsIgnoreCase(imagen) && "-1".equalsIgnoreCase(imagen)) {
                criteria.add(Restrictions.eq("rutaImagen", imagen));
            }
            lista = criteria.list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    /**
     * metodo que consulta una factura en especifico
     *
     * @param id
     * @return
     */
    public FacturaCompraGeneralEntity consultaFacturaEspecifico(Integer id) {
        FacturaCompraGeneralEntity respuesta = new FacturaCompraGeneralEntity();
        try {
            initOperation();
            FacturaCompraEntity resultFact = (FacturaCompraEntity) sesion.createCriteria(FacturaCompraEntity.class).add(Restrictions.eq("id", id)).uniqueResult();
            List<ImagenesFacCompraEntity> resultImag = sesion.createCriteria(ImagenesFacCompraEntity.class).createAlias("facturaCompra", "iF").setFetchMode("iF", FetchMode.JOIN).add(Restrictions.eq("iF.id", id)).list();
            List<PagoFacCompraEntity> resultPago = sesion.createCriteria(PagoFacCompraEntity.class).add(Restrictions.eq("idFacturaCompra", id)).list();
            List<ProductoFacCompraEntity> resultProdu = sesion.createCriteria(ProductoFacCompraEntity.class).add(Restrictions.eq("idFacturaCompra", id)).list();
            respuesta.setFactura(resultFact);
            respuesta.setImagenes(resultImag);
            respuesta.setPagos(resultPago);
            respuesta.setProductos(resultProdu);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * Funcion con la cual busco el
     *
     * @param idDska
     * @param idSede
     * @param fechaIni
     * @param fechaFin
     * @return
     */
    public List<KardexProductoEntity> buscaKardexProducto(Integer idDska, Integer idSede,Date fechaIni, Date fechaFin) {
        List<KardexProductoEntity> rta = null;
        try {
            initOperation();
            Criteria crit = sesion.createCriteria(KardexProductoEntity.class).createAlias("producto", "prod");
            crit.add(Restrictions.eq("prod.id", idDska)).setFetchMode("sede", FetchMode.JOIN).
                    setFetchMode("producto", FetchMode.JOIN).setFetchMode("usuario", FetchMode.JOIN).
                    setFetchMode("usuario.persona", FetchMode.JOIN).setFetchMode("usuario.sede", FetchMode.JOIN).
                    setFetchMode("usuario.idPerfil", FetchMode.JOIN).setFetchMode("movInv", FetchMode.JOIN);            
            crit.addOrder(Order.desc("id"));
            if(fechaIni != null && fechaFin == null){
                crit.add(Restrictions.ge("fecha", fechaIni));
            }else if(fechaIni == null && fechaFin != null){
                crit.add(Restrictions.lt("fecha", fechaFin));
            }else if(fechaIni != null && fechaFin != null){
                crit.add(Restrictions.between("fecha", fechaIni, fechaFin));
            }
            if (idSede != -1) {
                crit.createAlias("sede", "sed").add(Restrictions.eq("sed.id", idSede));
            }
            rta = crit.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
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
