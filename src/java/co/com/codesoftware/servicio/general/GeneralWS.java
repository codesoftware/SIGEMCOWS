/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.servicio.general;

import co.com.codesoftware.logica.admin.ParametrosEmpresaLogic;
import co.com.codesoftware.logica.admin.ResolucionFactLogica;
import co.com.codesoftware.logica.admin.SedesLogica;
import co.com.codesoftware.logica.facturacion.RemisionLogica;
import co.com.codesoftware.logica.reportes.ReporteLogica;
import co.com.codesoftware.persistence.entities.MapaEntity;
import co.com.codesoftware.persistencia.entidad.admin.ParametrosEmpresaEntity;
import co.com.codesoftware.persistencia.entidad.admin.ResolucionFactEntity;
import co.com.codesoftware.persistencia.entidad.admin.SedeEntity;
import co.com.codesoftware.persistencia.entidad.facturacion.DetProdRemision;
import co.com.codesoftware.persistencia.entidad.facturacion.RemisionEntity;
import java.util.Date;
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
@WebService(serviceName = "GeneralWS")
public class GeneralWS {

    /**
     * Metodo con el cual obtengo todos los parametros empresariales de la
     * compañia
     *
     * @return
     */
    @WebMethod(operationName = "obtenerParametrosEmpresa")
    @WebResult(name = "parametros")
    public List<ParametrosEmpresaEntity> obtenerParametrosEmpresa() {
        try (ParametrosEmpresaLogic objLogic = new ParametrosEmpresaLogic()) {
            return objLogic.obtienePrametrosEmpresa();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Metodo que consulta todas las sedes que hay en el sistema
     *
     * @return
     */
    @WebMethod(operationName = "obtenerSedes")
    public List<SedeEntity> obtenerSedes() {
        try (SedesLogica logic = new SedesLogica()) {
            return logic.sedeList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * Metodo con el cual se insertan las resoluciones de facturacion compañia
     *
     * @return
     */
    @WebMethod(operationName = "insertarResolucion")
    @WebResult(name = "respuesta")
    public String insertarResolucion(ResolucionFactEntity objEntity) {
        try (ResolucionFactLogica objLogica = new ResolucionFactLogica()) {
            return objLogica.insertaResolucion(objEntity);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Metodo con el cual se insertan las resoluciones de facturacion compañia
     *
     * @return
     */
    @WebMethod(operationName = "obtenerResolucionesFact")
    @WebResult(name = "resoluciones")
    public List<ResolucionFactEntity> obtenerResolucionesFact() {
        try (ResolucionFactLogica objLogica = new ResolucionFactLogica()) {
            return objLogica.obtieneResolucionesFacturacion();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Metodo con el cual obtienen las remisiones por medio del cliente compañia
     *
     * @param idCliente
     * @param fechaIni
     * @param fechafin
     * @return
     */
    @WebMethod(operationName = "obtenerRemisionesXCliente")
    @WebResult(name = "remisiones")
    public List<RemisionEntity> obtenerRemisionesXCliente(@XmlElement(required = true) @WebParam(name = "idCliente") Integer idCliente,
            @WebParam(name = "fechaIni") Date fechaIni,
            @WebParam(name = "fechafin") Date fechafin) {
        try (RemisionLogica objLogica = new RemisionLogica()) {
            return objLogica.obtieneRemisionesXCliente(idCliente, fechaIni, fechafin);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Metodo con el cual obtienen el detalle de productos compañia
     *
     * @param idRemision
     * @return
     */
    @WebMethod(operationName = "obtenerDetalleRemision")
    @WebResult(name = "detalles")
    public List<DetProdRemision> obtenerDetalleRemision(@XmlElement(required = true) @WebParam(name = "idRemision") Integer idRemision) {
        try (RemisionLogica objLogica = new RemisionLogica()) {
            return objLogica.buscaDetallesRemision(idRemision);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Metodo con el cual obtienen el detalle de productos compañia
     *
     * @param idRemision
     * @param idTius
     * @return
     */
    @WebMethod(operationName = "realizarFacturaXRemision")
    @WebResult(name = "respuesta")
    public String realizarFacturaXRemision(@XmlElement(required = true) @WebParam(name = "idRemision") Integer idRemision,
            @XmlElement(required = true) @WebParam(name = "idTius") Integer idTius) {
        String rta = "";
        try{
            RemisionLogica objLogica = new RemisionLogica();
            rta = objLogica.realizarFacturaXRemision(idRemision, idTius);
        } catch (Exception e) {
            return null;
        }
        return rta;
    }
    
    /**
     * metodo que devuelve la ruta del reporte
     * @param parametros
     * @param datosReporte
     * @return 
     */
    @WebMethod(operationName = "generaReportes")
    public String generaReportes(List<MapaEntity> parametros,List<MapaEntity> datosReporte){
        String rta = "";
        try(ReporteLogica logica = new ReporteLogica()) {
            rta = logica.generaReporte(parametros, datosReporte);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
    /**
     * 
     * @return 
     */
    @WebMethod(operationName = "generaReportes")
    public String actualizarSede(@XmlElement(required = true) @WebParam(name = "sede") SedeEntity sede){
        String rta = "";
        try (SedesLogica objLogica = new SedesLogica()){
            rta = objLogica.actualizaSede(sede);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
}