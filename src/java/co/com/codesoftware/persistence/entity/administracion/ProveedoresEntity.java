/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.persistence.entity.administracion;

import co.com.codesoftware.persistencia.entidad.admin.CiudadEntity;
import co.com.codesoftware.persistencia.entidad.admin.DepartamentoEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author john
 */
@Entity
@Table(name = "in_tprov")
public class ProveedoresEntity implements Serializable {

    @Id
    @Column(name = "prov_prov")
    private Integer id;
    @Column(name = "prov_nombre")
    private String nombre;
    @Column(name = "prov_nit")
    private String nit;
    @Column(name = "prov_razon_social")
    private String razonSocial;
    @Column(name = "prov_representante")
    private String representante;
    @Column(name = "prov_telefono")
    private String telefono;
    @Column(name = "prov_direccion")
    private String direccion;
    @Column(name = "prov_celular")
    private String celular;
    @Column(name = "prov_estado")
    private String estado;
    @Column(name = "prov_dver")
    private String dverificacion;
    @Column(name = "prov_dcre")
    private Integer dcredito;
     @Column(name = "prov_rete")
    private BigDecimal porcRetencion;
    @Column(name = "prov_corre")
    private String correo;
    @JoinColumn(name = "prov_retde")
    @OneToOne(fetch = FetchType.LAZY)
    private ReteFuenteEntity retenciones;
    @Column(name = "prov_gcron")
    private String granContribuyente;
    @JoinColumn(name = "prov_ciu")
    @OneToOne(fetch = FetchType.LAZY)
    private CiudadEntity ciudad;
    @JoinColumn(name = "prov_mpio")
    @OneToOne(fetch = FetchType.LAZY)
    private DepartamentoEntity municipio;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRepresentante() {
        return representante;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getGranContribuyente() {
        return granContribuyente;
    }

    public void setGranContribuyente(String granContribuyente) {
        this.granContribuyente = granContribuyente;
    }

    public ReteFuenteEntity getRetenciones() {
        return retenciones;
    }

    public void setRetenciones(ReteFuenteEntity retenciones) {
        this.retenciones = retenciones;
    }

    public CiudadEntity getCiudad() {
        return ciudad;
    }

    public void setCiudad(CiudadEntity ciudad) {
        this.ciudad = ciudad;
    }

    public DepartamentoEntity getMunicipio() {
        return municipio;
    }

    public void setMunicipio(DepartamentoEntity municipio) {
        this.municipio = municipio;
    }

    public String getDverificacion() {
        return dverificacion;
    }

    public void setDverificacion(String dverificacion) {
        this.dverificacion = dverificacion;
    }

    public Integer getDcredito() {
        return dcredito;
    }

    public void setDcredito(Integer dcredito) {
        this.dcredito = dcredito;
    }

    public BigDecimal getPorcRetencion() {
        return porcRetencion;
    }

    public void setPorcRetencion(BigDecimal porcRetencion) {
        this.porcRetencion = porcRetencion;
    }


    

}
