/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.persistence.entity.facturacion;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author john
 */
@Entity
@Table(name = "fa_timfa")
public class ImagenFacturaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "imfa_infa")
    private Integer id;
    @Column(name = "imfa_fact")
    private Integer idFactura;
    @Column(name = "imfa_tipo")
    private String tipoFactura;
    @Column(name = "imfa_ruta")
    private String rutaImagen;
    @Column(name = "imfa_esta")
    private String estado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public String getTipoFactura() {
        if("NC".equalsIgnoreCase(tipoFactura)){
            tipoFactura = "NOTA CRÉDITO";
        }else{
            tipoFactura = "NOTA CREDITO";
        }
        return tipoFactura;
    }

    public void setTipoFactura(String tipoFactura) {
        this.tipoFactura = tipoFactura;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    

}
