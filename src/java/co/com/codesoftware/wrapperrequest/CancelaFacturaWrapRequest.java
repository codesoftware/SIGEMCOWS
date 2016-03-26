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
@XmlType(propOrder = {"idFactura", "idUsuario","comentario","estado"})
@XmlAccessorType(XmlAccessType.FIELD)
public class CancelaFacturaWrapRequest {
    @XmlElement(name = "idFactura", required = true)
    private Integer idFactura;
    @XmlElement(name = "idUsuario", required = true)
    private Integer idUsuario;
    @XmlElement(name = "comentario", required = true)
    private String comentario;
    @XmlElement(name = "estado", required = true)
    private String estado;

    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
