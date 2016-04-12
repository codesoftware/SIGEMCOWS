/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.servicio.producto;

import co.com.codesoftware.logica.inventario.ProductoLogica;
import co.com.codesoftware.logica.inventario.ProductosGenericosLogica;
import co.com.codesoftware.logica.receta.RecetaLogica;
import co.com.codesoftware.persistencia.entidad.generico.producto.ProductoGenericoEntity;
import co.com.codesoftware.persistencia.entidad.inventario.ExistenciaXSedeEntity;
import co.com.codesoftware.persistencia.entidad.inventario.PrecioProductoEntity;
import co.com.codesoftware.persistencia.entidad.inventario.PromPonderaEntity;
import co.com.codesoftware.persistencia.entidad.receta.PrecioRecetaEntity;
import java.math.BigDecimal;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author nicolas
 */
@WebService(serviceName = "ProductoWS")
public class ProductoWS {

    /**
     * Metodo el cual busca los productos y recetas parametrizados en el sistema
     * y que esten disponibles para su venta
     *
     * @param sede_sede
     * @return
     */
    @WebMethod(operationName = "obtenerProductosYRecetas")
    @WebResult(name = "ListGeneric")
    public List<ProductoGenericoEntity> obtenerProductosYRecetas(@XmlElement(required = true) @WebParam(name = "sede_sede") Integer sede_sede) {
        try {
            ProductosGenericosLogica logica = new ProductosGenericosLogica();
            return logica.obtieneProductosYRecetas(sede_sede);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @WebMethod(operationName = "obtieneRecetasXSede")
     @WebResult(name = "ListaReceta")
    public List<PrecioRecetaEntity> consultaRecetas(@WebParam(name = "idSede") Integer idSede){
        List<PrecioRecetaEntity> rta = null;
        try(RecetaLogica logica = new RecetaLogica();) {
            rta = logica.getRecetas(idSede);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion con la cual consulto el promedio ponderado de un producto y lo
     * comparo con el precio que quieren darle
     *
     * @param idProducto
     * @param precio
     * @return
     */
    @WebMethod(operationName = "consultaPromPonderado")
    public String consultaPromPonderado(@XmlElement(required = true) @WebParam(name = "dska_dska") Integer idProducto, @XmlElement(required = true) @WebParam(name = "precio") BigDecimal precio) {
        String rta = "";
        try {
            ProductoLogica objLogica = new ProductoLogica();
            rta = objLogica.consultaPromPonderado(idProducto, precio);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion con la cual busco el promedio ponderado y las existencias en toda
     * la compañia
     *
     * @param idDska
     * @return
     */
    @WebMethod(operationName = "obtenerPromedioPonderadoProducto")
    public PromPonderaEntity obtenerPromedioPonderadoProducto(@XmlElement(required = true) @WebParam(name = "idDska") Integer idDska) {
        PromPonderaEntity rta = null;
        try (ProductoLogica objLogica = new ProductoLogica()) {
            rta = objLogica.buscaPromedioPondProd(idDska);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion con la cual obtengo las existencias que hay en cada sede de cada
     * producto
     *
     * @param idDska
     * @return
     */
    @WebMethod(operationName = "obtenerExistenciasProducto")
    public List<ExistenciaXSedeEntity> obtenerExistenciasProducto(@XmlElement(required = true) @WebParam(name = "idDska") Integer idDska) {
        List<ExistenciaXSedeEntity> rta = null;
        try (ProductoLogica objLogica = new ProductoLogica()) {
            rta = objLogica.buscoExistenciaProd(idDska);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Metodo el cual busca los productos y recetas parametrizados en el sistema
     *
     * @param sede_sede
     * @param criterio
     * @return
     */
    @WebMethod(operationName = "obtenerProductosYRecetasXCriterio")
    @WebResult(name = "ListGeneric")
    public List<ProductoGenericoEntity> obtenerProductosYRecetasXCriterio(@XmlElement(required = true) @WebParam(name = "sede_sede") Integer sede_sede,
            @XmlElement(required = true) @WebParam(name = "criterio") String criterio) {
        try {
            ProductosGenericosLogica logic = new ProductosGenericosLogica();
            return logic.buscaProductosRecetasXCriterio(sede_sede, criterio);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * metodo que consulta los productos por sede que se pueden vender
     *
     * @param sede
     * @return
     */
    @WebMethod(operationName = "obtenerProductoXSede")
    @WebResult(name = "PrecioProducto")
    public List<PrecioProductoEntity> obtenerProductoXSede(@XmlElement(required = true) @WebParam(name = "sede_sede") Integer sede) {
        List<PrecioProductoEntity> resultado = null;
        try (ProductoLogica logica = new ProductoLogica()) {
            resultado = logica.obtieneProductosConPrecioXSede(sede);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    /**
     * Metodo con el cual se obtiene un producto en especifico basandose en su
     * codigo unico y en la sede.
     *
     * @param dska_cod
     * @param sede_sede
     * @return
     */
    @WebMethod
    @WebResult(name = "ProductoTable", partName = "ProductoTable")
    public PrecioProductoEntity obtenerProductoXCodigo(@WebParam(name = "dska_cod") String dska_cod,
            @WebParam(name = "sede_sede") Integer sede_sede) {
        try (ProductoLogica logic = new ProductoLogica()) {
            return logic.buscoProductoEntityXCodigo(dska_cod, sede_sede);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Funcion la cual busca un producto por medio de su codigo externo
     *
     * @param codExt
     * @param sede_sede
     * @return
     */
    @WebMethod
    @WebResult(name = "ProductoTable", partName = "ProductoTable")
    public PrecioProductoEntity obtenerProductoXCodExt(@WebParam(name = "codExt") String codExt,
            @WebParam(name = "sede_sede") Integer sede_sede) {
        try (ProductoLogica logic = new ProductoLogica()) {
            return logic.buscoProductoEntityXCodExt(codExt, sede_sede);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Funcion la cual busca un producto por medio de su codigo externo
     *
     * @param codBarras
     * @param sede_sede
     * @return
     */
    @WebMethod
    @WebResult(name = "ProductoTable", partName = "ProductoTable")
    public PrecioProductoEntity obtenerProductoXCodBarras(@WebParam(name = "codBarras") String codBarras,
            @WebParam(name = "sede_sede") Integer sede_sede) {
        try (ProductoLogica logic = new ProductoLogica()) {
            return logic.buscoProductoEntityXCodBarras(codBarras, sede_sede);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Metodo que consulta cantidades por sede
     *
     * @param sede
     * @param idProducto
     * @return
     */
    @WebMethod(operationName = "obtenerCantidadesXSede")
    @WebResult(name = "ExistenciaXSedeEntity")
    public ExistenciaXSedeEntity obtenerCantidadesXSede(@XmlElement(required = true) @WebParam(name = "sede_sede")Integer sede, 
            @XmlElement(required = true) @WebParam(name = "idDska")Integer idProducto) {
        ExistenciaXSedeEntity resultado = null;
        try (ProductoLogica logic = new ProductoLogica()) {
            resultado = logic.consultaCantidad(sede, idProducto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }
    
    /**
     * Funcion con la cual obtengo todas las cantidades que tiene un producto dentro del sistema
     * @param idProducto
     * @return
     */
    @WebMethod(operationName = "obtenerCantidadesXProducto")
    @WebResult(name = "ExistenciaXSedeEntity")
    public List<ExistenciaXSedeEntity> obtenerCantidadesXProducto(@XmlElement(required = true) @WebParam(name = "idDska")Integer idProducto) {
        List<ExistenciaXSedeEntity> resultado = null;
        try (ProductoLogica logic = new ProductoLogica()) {
            resultado = logic.consultaCantidades(idProducto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }
}
