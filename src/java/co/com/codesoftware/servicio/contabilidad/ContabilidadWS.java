/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.servicio.contabilidad;

import co.com.codesoftware.logica.facturacion.PucLogica;
import co.com.codesoftware.persistencia.entidad.contabilidad.ClaseEntity;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;

/**
 *
 * @author ACER
 */
@WebService(serviceName = "ContabilidadWS")
public class ContabilidadWS {
    /**
     * Funcion con la cual obtengo todas las clases
     * @return 
     */
    @WebMethod(operationName = "obtenerClases")
    @WebResult(name = "listaClases")
    public List<ClaseEntity> obtenerClases(){
        List<ClaseEntity> rta = null;
        try (PucLogica objLogica = new PucLogica()){
            rta = objLogica.obtieneClases();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
}
