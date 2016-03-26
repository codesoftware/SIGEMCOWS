/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.wrapperrequest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author john
 */
@XmlType(propOrder = {"mensaje", "asunto","correo"})
@XmlAccessorType(XmlAccessType.FIELD)
public class CorreoWrapperRequest {

    @XmlElement(name = "mensaje", required = true)
    public String mensaje;
    @XmlElement(name = "asunto", required = true)
    private String asunto;
    @XmlElement(name = "correo", required = true)
    private String correo;
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    

}
