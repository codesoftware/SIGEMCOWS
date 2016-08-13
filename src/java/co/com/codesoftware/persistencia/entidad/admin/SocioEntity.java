/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.persistencia.entidad.admin;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author John
 */
@Entity
@Table(name = "ad_tsoci")
public class SocioEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SOCI_SOCI")
    private Integer id;
    @Column(name = "SOCI_RAZO")
    private String razonSocial;
    @Column(name = "SOCI_NIT")
    private String nit;
    @Column(name = "SOCI_DIVE")
    private String digitoVer;
    @Column(name = "SOCI_REPR")
    private String representanteLegal;
    @Column(name = "SOCI_DIRE")
    private String direccion;
    @Column(name = "SOCI_MPIO")
    private Integer municipio;
    @Column(name = "SOCI_CIUD")
    private Integer ciudad;
    @Column(name = "SOCI_ESTA")
    private String estado;
    @Column(name = "SOCI_USUA")
    private Integer usuario;
    @Temporal(TemporalType.DATE)
    @Column(name = "SOCI_FCRE")
    private Date fechaCreacion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDigitoVer() {
        return digitoVer;
    }

    public void setDigitoVer(String digitoVer) {
        this.digitoVer = digitoVer;
    }

    public String getRepresentanteLegal() {
        return representanteLegal;
    }

    public void setRepresentanteLegal(String representanteLegal) {
        this.representanteLegal = representanteLegal;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Integer municipio) {
        this.municipio = municipio;
    }

    public Integer getCiudad() {
        return ciudad;
    }

    public void setCiudad(Integer ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

}
