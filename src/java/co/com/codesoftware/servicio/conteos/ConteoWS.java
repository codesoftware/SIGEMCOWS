/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.servicio.conteos;

import co.com.codesoftware.persistencia.entidad.inventario.ConteoEntity;
import co.com.codesoftware.persistencia.entidad.inventario.ProductoConteoEntity;
import co.com.codesoftware.logica.inventario.ConteoLogica;
import co.com.codesoftware.persistencia.utilities.RespuestaEntity;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;

/**
 *
 * @author nicolas
 */
@WebService(serviceName = "ConteoWS")
public class ConteoWS {

    /**
     * Funcion que inserta un producto al conteo
     *
     * @param codExterno
     * @param codConteo
     * @param Cantidad
     * @param ubicacion
     * @param codigoBarras
     * @return
     */
    @WebMethod(operationName = "insertaProductoConteo")
    @WebResult(name = "RespuestaEntity")
    public RespuestaEntity insertaProductoConteo(@WebParam(name = "codExterno") String codExterno, 
            @WebParam(name = "codConteo") Integer codConteo, 
            @WebParam(name = "cantidad") Integer Cantidad, 
            @WebParam(name = "codigoBarras") String codigoBarras, 
            @WebParam(name = "ubicacion") String ubicacion) {
        RespuestaEntity rta = null;
        try (ConteoLogica logic = new ConteoLogica()) {
            rta = logic.insProdConteo(codConteo, codExterno, Cantidad, codigoBarras, ubicacion);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * metodo el cual consulta los conteos por estado
     *
     * @param estado
     * @return
     */
    @WebMethod(operationName = "obtenerConteoEstado")
    @WebResult(name = "ConteosEntity")
    public List<ConteoEntity> obtenerConteoEstado(@WebParam(name = "estado") String estado) {
        List<ConteoEntity> resultado = null;
        try (ConteoLogica logic = new ConteoLogica()) {
            resultado = logic.consultaConteosEstado(estado);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    /**
     * metodo que consulta un producto de un conteo especifico
     *
     * @param codExterno
     * @param conteo
     * @return
     */
    @WebMethod(operationName = "obtenerProdConteo")
    @WebResult(name = "ProductoConteoEntityTR")
    public ProductoConteoEntity obtenerProdConteo(@WebParam(name = "codigoExterno") String codExterno, @WebParam(name = "idConteo") Integer conteo) {
        ProductoConteoEntity rta = null;
        try (ConteoLogica logic = new ConteoLogica()) {
            rta = logic.consultaProductoConteo(conteo, codExterno);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Metodo que consulta todos los porductos que se han añadido al conteo
     *
     * @param conteo
     * @return
     */
    @WebMethod(operationName = "obtenerProductosConteo")
    @WebResult(name = "ProductoConteoEntityTR")
    public List<ProductoConteoEntity> obtenerProductosConteo(@WebParam(name = "idConteo") Integer conteo) {
        List<ProductoConteoEntity> rta = null;
        try (ConteoLogica logic = new ConteoLogica()) {
            rta = logic.consultaProductosConteo(conteo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
    
    /**
     * Metodo en el cual insertamos un conteo en el sistema
     *
     * @param conteo
     * @return
     */
    @WebMethod(operationName = "insertaConteo")
    @WebResult(name = "respuesta")
    public String insertaConteo(@WebParam(name = "conteo") ConteoEntity conteo) {
        String rta = null;
        try (ConteoLogica logic = new ConteoLogica()) {
            rta = logic.insertaConteo(conteo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
    /**
     * Funcion con la cual obtengo un conteo por medio de su id
     * @param idConteo
     */
    @WebMethod(operationName = "obtenerConteo")
    @WebResult(name = "conteo")
    public ConteoEntity obtenerConteo(@WebParam(name = "idConteo")Integer idConteo){
        ConteoEntity objEntity = null;
        try (ConteoLogica objLogica = new ConteoLogica()){
            objEntity = objLogica.obtenerConteoXId(idConteo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objEntity;
    }
    /**
     * Funcion con la cual ejecuta el cierre de conteo
     * @param idConteo
     * @return 
     */
    @WebMethod(operationName = "ejecutaCierreConteo")
    @WebResult(name = "conteo")
    public String ejecutaCierreConteo(Integer idConteo){
        String rta = "";
        try {
            ConteoLogica objLogica = new ConteoLogica();
            rta = objLogica.cierraConteo(idConteo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

}
