/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.servicio.general;

import co.com.codesoftware.logica.admin.ParametrosEmpresaLogic;
import co.com.codesoftware.logica.admin.SedesLogica;
import co.com.codesoftware.persistencia.entidad.admin.ParametrosEmpresaEntity;
import co.com.codesoftware.persistencia.entidad.admin.SedeEntity;
import co.com.codesoftware.persistencia.entidad.contabilidad.SubCuentaEntity;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebResult;

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
    
}
