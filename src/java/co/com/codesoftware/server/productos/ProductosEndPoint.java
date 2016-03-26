/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.server.productos;

import co.com.codesoftware.logic.ProductoLogic;
import co.com.codesoftware.logic.productos.FacturaCompraLogic;
import co.com.codesoftware.persistence.entities.FacturaCompraGeneralEntity;
import co.com.codesoftware.persistence.entities.simple.PrecioProdSimpleEntity;
import co.com.codesoftware.persistence.entities.simple.ProductoSimpleEntity;
import co.com.codesoftware.persistencia.entidad.inventario.ProductoEntity;
import co.com.codesoftware.persistence.entity.administracion.RespuestaEntity;
import co.com.codesoftware.persistence.entity.productos.FacturaCompraTotalEntity;
import co.com.codesoftware.persistence.entity.productos.ImagenesFacCompraEntity;
import co.com.codesoftware.persistence.entity.productos.KardexProductoEntity;
import co.com.codesoftware.persistence.entity.productos.PorcentajePrecioEntity;
import java.util.Date;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 *
 * @author john
 */
@WebService(serviceName = "ProductosEndPoint")
public class ProductosEndPoint {

    /**
     *
     * @return
     */
    @WebMethod(operationName = "consultaProductos")
    @WebResult(name = "ProductoSimple")
    public List<ProductoSimpleEntity> consultaProductos() {
        List<ProductoSimpleEntity> respuesta = null;
        try (ProductoLogic logic = new ProductoLogic()) {
            respuesta = logic.consultaProductos();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * Funcion con la cual busco un producto existente en la base de datos
     *
     * @return
     */
    @WebMethod(operationName = "consultaProductoXId")
    @WebResult(name = "ProductoSimple")
    public ProductoSimpleEntity consultaProductoXId(@WebParam(name = "dska_dska") Integer idDska) {
        ProductoSimpleEntity respuesta = null;
        try (ProductoLogic logic = new ProductoLogic()) {
            respuesta = logic.consultaProductoXId(idDska);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * Metodo con el cual valido si un codigo externo ya existe excentuando el
     * id que envie
     *
     * @return
     */
    @WebMethod(operationName = "validaCodigoExterno")
    @WebResult(name = "validacion")
    public String validaCodigoExterno(@WebParam(name = "dska_dska") Integer idDska, @WebParam(name = "dska_cod_ext") String codexterno) {
        String rta = "";
        try (ProductoLogic logic = new ProductoLogic()) {
            rta = logic.validaCodigoExterno(idDska, codexterno);
        } catch (Exception e) {
            e.printStackTrace();
            rta = "Error " + e;
        }
        return rta;
    }

    /**
     * Funcion con la cual actualizo un producto simple
     *
     * @param producto
     * @return
     */
    @WebMethod(operationName = "actualizaProductoSimple")
    @WebResult(name = "respuesta")
    public String actualizaProductoSimple(@WebParam(name = "productoSimple") ProductoSimpleEntity producto) {
        String rta = "";
        try (ProductoLogic objLogic = new ProductoLogic()) {
            rta = objLogic.actualizaProductoSimple(producto);
        } catch (Exception e) {
            e.printStackTrace();
            rta = "Error " + e;
        }
        return rta;
    }

    /**
     * Funcion con la cual busco el historico de precion de un producto por una
     * sede en especifico
     *
     * @param idDska
     * @param idSede
     * @return
     */
    @WebMethod(operationName = "buscaHistProdXSede")
    @WebResult(name = "listaPrecios")
    public List<PrecioProdSimpleEntity> buscaHistProdXSede(@WebParam(name = "idDska") Integer idDska, @WebParam(name = "idSede") Integer idSede) {
        List<PrecioProdSimpleEntity> rta = null;
        try (ProductoLogic objLogic = new ProductoLogic()) {
            rta = objLogic.buscaHisPrecioProdXSede(idDska, idSede);
        } catch (Exception e) {
            e.printStackTrace();
            rta = null;
        }
        return rta;
    }

    /**
     * Funcion con la cual se realiza la parametrizacion que se utilizara para
     * la parametrizacion de precios
     *
     * @return
     */
    @WebMethod(operationName = "insercionParamPrecioMasivo")
    @WebResult(name = "respuesta")
    public String insercionParamPrecioMasivo(Integer idCate, Integer idRefe, Integer idMarca, Integer idSede, Integer porcUnid, Integer porcCent, Integer porcMill, Integer porcBase, Integer idTius) {
        String rta = "";
        try (ProductoLogic objLogic = new ProductoLogic()) {
            rta = objLogic.insercionParamPrecioMasivo(idCate, idRefe, idMarca, idSede, porcUnid, porcCent, porcMill, porcBase, idTius);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion con el cual se obtienenen la parametrizacion de porcentajes que
     * se tendran encuenta para la parametrizacion de precios masivo
     *
     * @param estado
     * @return
     */
    @WebMethod(operationName = "buscaPorcentajePrecio")
    @WebResult(name = "listaPorcPrecio")
    public List<PorcentajePrecioEntity> buscaPorcentajePrecio(String estado) {
        List<PorcentajePrecioEntity> rta = null;
        try (ProductoLogic objLogic = new ProductoLogic()) {
            rta = objLogic.buscaPorcentajePrecio(estado);
        } catch (Exception e) {
            e.printStackTrace();
            rta = null;
        }
        return rta;
    }

    /**
     * Funcion con el cual se obtienenen la parametrizacion de porcentajes que
     * se tendran encuenta para la parametrizacion de precios masivo
     *
     * @param estado
     * @param idSede
     * @param idCate
     * @return
     */
    @WebMethod(operationName = "buscaPorcentajePrecioFiltros")
    @WebResult(name = "listaPorcPrecio")
    public List<PorcentajePrecioEntity> buscaPorcentajePrecioFiltros(String estado,
            Integer idSede, Integer idCate,
            Integer idRefe, Integer idMarca) {
        List<PorcentajePrecioEntity> rta;
        try (ProductoLogic objLogic = new ProductoLogic()) {
            rta = objLogic.buscaPorcentajePrecioXFiltros(estado, idSede, idCate, idRefe, idMarca);
        } catch (Exception e) {
            e.printStackTrace();
            rta = null;
        }
        return rta;
    }

    /**
     * Metodo que consulta por filtros
     *
     * @param codigoBarras
     * @param codigoExterno
     * @param descripcion
     * @return
     */
    @WebMethod(operationName = "buscaProductoFiltros")
    @WebResult(name = "listaProducto")
    public ProductoEntity buscaProductoFiltros(String codigoBarras, String codigoExterno) {
        ProductoEntity rta = null;
        try (ProductoLogic objLogic = new ProductoLogic();) {
            rta = objLogic.consultaProductosFiltro(codigoBarras, codigoExterno);
        } catch (Exception e) {
            e.printStackTrace();

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
     * @param idTius
     * @return
     */
    @WebMethod(operationName = "ejecutaCambioPrecioMasivo")
    @WebResult(name = "respuesta")
    public String ejecutaCambioPrecioMasivo(Integer idSede, Integer idCate, Integer idRefe, Integer idMarca, Integer idTius) {
        String rta = null;
        try (ProductoLogic objLogic = new ProductoLogic()) {
            rta = objLogic.ejecutaProcesoPrecioMasivo(idSede, idCate, idRefe, idMarca, idTius);
        } catch (Exception e) {
            e.printStackTrace();
            rta = null;
        }
        return rta;
    }
    
    /**
     * Funcion con la cual ejecuto el cambio de precios masivos
     *
     * @param idSede
     * @param idCate
     * @param idRefe
     * @param idMarca
     * @param idTius
     * @return
     */
    @WebMethod(operationName = "ejecutaCambioPrecioMasivoEnProd")
    @WebResult(name = "respuesta")
    public String ejecutaCambioPrecioMasivoEnProd(Integer idSede, Integer idCate, Integer idRefe, Integer idMarca, Integer idTius) {
        String rta = null;
        try (ProductoLogic objLogic = new ProductoLogic()) {
            rta = objLogic.ejecutaProcesoPrecioMasivoEnCate(idSede, idCate, idRefe, idMarca, idTius);
        } catch (Exception e) {
            e.printStackTrace();
            rta = null;
        }
        return rta;
    }
    
    /**
     * metodo que consulta la factura e compra por filtros
     * @param idProveedor
     * @param fechaInicial
     * @param fechaFinal
     * @param estado
     * @param imagen
     * @return 
     */
    @WebMethod(operationName = "consultaFacturaCompraFiltros")
    @WebResult(name = "FacturaCompraTotalEntity")
    public List<FacturaCompraTotalEntity> consultaFacturaCompraFiltros(Integer idProveedor,Date fechaInicial,Date fechaFinal,String estado,String imagen){
         List<FacturaCompraTotalEntity> respuesta = null;
         try (ProductoLogic objLogic = new ProductoLogic()){
            respuesta = objLogic.consultaFacturas(idProveedor, fechaInicial, fechaFinal, estado, imagen);
        } catch (Exception e) {
            e.printStackTrace();
        }
         return respuesta;
    }
    
    /**
     * funcion que inserta imagenes para la factura de compra
     * @param entity
     * @return 
     */
    @WebMethod(operationName = "insertaImagenesFacturaCompra")
    @WebResult(name = "ResultEntity")
    public RespuestaEntity insertaImagenesFacturaCompra(ImagenesFacCompraEntity entity){
        RespuestaEntity res = new RespuestaEntity();
        try (FacturaCompraLogic logic = new FacturaCompraLogic()){
            res = logic.insertaImagen(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    /**
     * metodo que consulta ua factura en especifico
     * @param id
     * @return 
     */
    @WebMethod(operationName = "consultaFacturaTotal")
    @WebResult(name = "FacturaCompraGeneralEntity")
    public FacturaCompraGeneralEntity consultaFacturaTotal(Integer id){
        FacturaCompraGeneralEntity respuesta = new FacturaCompraGeneralEntity();
        try (ProductoLogic logic = new ProductoLogic()){
            respuesta = logic.consultaFacturaEspecifico(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }
    /**
     * Funcion con la cual obtengo el kardex de un producto 
     * @param idDska
     * @param idSede
     * @param fechaIni
     * @param fechaFin
     * @return 
     */
    @WebMethod(operationName = "buscaKardexProductoXSede")
    @WebResult(name = "listaKardexProd")
    //@ResponseWrapper(localName = "buscaKardexProductoXSedeResponse", targetNamespace = "http://entity.ws.codesoftware.com.co/", className = "co.com.codesoftware.ws.entity.ConsultaFacturaTotalResponse")
    public List<KardexProductoEntity> buscaKardexProductoXSede(@WebParam(name = "idDska") Integer idDska,@WebParam(name = "idSede") Integer idSede,@WebParam(name = "fechaIni") Date fechaIni,@WebParam(name = "fechaFin") Date fechaFin){
        List<KardexProductoEntity> rta = null;
        try(ProductoLogic objLogic = new ProductoLogic()) {
            rta = objLogic.buscaKardexProducto(idDska,idSede,fechaIni,fechaFin);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
    
}
