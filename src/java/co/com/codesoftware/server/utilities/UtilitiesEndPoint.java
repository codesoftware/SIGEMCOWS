/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.server.utilities;

import co.com.codesoftware.logic.EnviaCorreosLogic;
import co.com.codesoftware.wrapperrequest.CorreoWrapperRequest;
import com.sun.xml.ws.developer.SchemaValidation;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 *
 * @author john
 */
@WebService(name = "UtilitiesEndPoint")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
@SchemaValidation
public class UtilitiesEndPoint {
    
    /**
     * Metodo que envia un mensaje desde el correo electronico
     * @param mensajeCorreo
     * @return 
     */
    @WebMethod(operationName = "enviaCorreo")
    public String enviaCorreo(@WebParam(name = "mensajeCorreo") CorreoWrapperRequest mensajeCorreo) {
        String rta = "";
        try {
            EnviaCorreosLogic logic = new EnviaCorreosLogic();
            rta = logic.enviaCorreos(mensajeCorreo.getAsunto(), mensajeCorreo.getMensaje(), mensajeCorreo.getCorreo());
        } catch (Exception e) {
            rta = e.toString();
            e.printStackTrace();
        }
        return rta;
    }

}
