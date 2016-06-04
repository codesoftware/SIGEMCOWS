/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.servicio.importacion;

import co.com.codesoftware.logica.importacion.ProveedorIntLogica;
import co.com.codesoftware.persistencia.entidad.importacion.ProveedorInterEntity;
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
     * @param objEntity
     * @return 
     */
    @WebMethod(operationName = "insertarProveedorInternacional")
    public String insertarProveedorInternacional(@WebParam(name = "provInter") ProveedorInterEntity objEntity) {
        String rta = "";
        try (ProveedorIntLogica objLogica = new ProveedorIntLogica()){
            rta = objLogica.insertaProveedor(objEntity);
        } catch (Exception e) {
            e.printStackTrace();
            rta = "Error " + e;
        }
        return rta;
    }
}