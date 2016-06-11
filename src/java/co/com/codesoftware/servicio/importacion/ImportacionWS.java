/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.servicio.importacion;

import co.com.codesoftware.logica.importacion.ImportacionLogica;
import co.com.codesoftware.logica.importacion.ProveedorIntLogica;
import co.com.codesoftware.persistencia.entidad.importacion.ImportacionEntity;
import co.com.codesoftware.persistencia.entidad.importacion.ProveedorInterEntity;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author nicolas
 */
@WebService(serviceName = "ImportacionWS")
public class ImportacionWS {

    /**
     * Metodo con el cual se inserta un proveedor internacional
     *
     * @param objEntity
     * @return
     */
    @WebMethod(operationName = "insertarProveedorInternacional")
    public String insertarProveedorInternacional(@WebParam(name = "provInter") ProveedorInterEntity objEntity) {
        String rta = "";
        try (ProveedorIntLogica objLogica = new ProveedorIntLogica()) {
            rta = objLogica.insertaProveedor(objEntity);
        } catch (Exception e) {
            e.printStackTrace();
            rta = "Error " + e;
        }
        return rta;
    }

    /**
     * Funcion con la cual realizo la obtencion de proveedores internacionales
     *
     * @return
     */
    @WebMethod(operationName = "obtenerProveedoresInter")
    public List<ProveedorInterEntity> obtenerProveedoresInter() {
        try (ProveedorIntLogica objLogica = new ProveedorIntLogica()) {
            return objLogica.consultaProveedoresInterna();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Funcion con la cual inserto una importacion en el sistema
     *
     * @param objEntity
     * @return
     */
    @WebMethod(operationName = "insertaImportacion")
    public String insertaImportacion(@WebParam(name = "importEntity") ImportacionEntity objEntity) {
        String rta = "Ok";
        try (ImportacionLogica objLogica = new ImportacionLogica()){
            rta = objLogica.insertaImportacion(objEntity);
        } catch (Exception e) {
            e.printStackTrace();
            rta = "Error " + e;
        }
        return rta;
    }
    
    /**
     * metodo que se usa para consultar las importaciones dependiendo de la fecha
     * @param fechaInicial
     * @param fechaFinal
     * @return 
     */
    @WebMethod(operationName = "obtenerImportaciones")
    public List<ImportacionEntity> obtenerImportaciones(@WebParam(name = "fechaInicial") Date fechaInicial,@WebParam(name = "fechaFinal") Date fechaFinal){
        List<ImportacionEntity> respuesta = null;
        try (ImportacionLogica objLogica = new ImportacionLogica()){
            respuesta= objLogica.consultaImportaciones(fechaInicial, fechaFinal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }
}
