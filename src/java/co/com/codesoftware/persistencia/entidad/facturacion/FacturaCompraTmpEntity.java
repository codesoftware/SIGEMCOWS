/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.persistencia.entidad.facturacion;

import co.com.codesoftware.persistence.entity.administracion.ProveedoresEntity;
import co.com.codesoftware.persistence.entity.administracion.RespuestaEntity;
import co.com.codesoftware.persistencia.entidad.admin.SedeEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author john
 */
@Entity
@Table(name = "fa_tfacom_tmp")
public class FacturaCompraTmpEntity extends RespuestaEntity implements Serializable {

    @Id
    @Column(name = "facom_tmp_facom")
    private Integer id;
    @Column(name = "facom_tmp_fecha")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaFacCompra;
    @Column(name = "facom_tmp_nufac")
    private String numeroFactura;
    @Column(name = "facom_tmp_valor")
    private BigDecimal valorFacura;
    @Column(name = "facom_tmp_rtimg")
    private String rutaImagen;
    @JoinColumn(name = "facom_tmp_tprov")
    @OneToOne(fetch = FetchType.LAZY)
    private ProveedoresEntity proveedor;
    @Column(name = "facom_tmp_estad")
    private String estado;
    @Column(name = "facom_tmp_tius")
    private Integer usuario;
    @Column(name = "facom_tmp_fecre")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "facom_tmp_ferec")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaRecepcion;
    @Column(name = "facom_tmp_vliva")
    private BigDecimal valorIva;
    @Column(name = "facom_tmp_vlret")
    private BigDecimal valorRetencion;
    @JoinColumn(name = "facom_tmp_sede")
    @OneToOne(fetch = FetchType.LAZY)
    private SedeEntity sede;
    @Column(name = "facom_tmp_ajus")
    private String ajusteFactura;
    @Column(name = "facom_tmp_plaz")
    private Integer plazo;
    @Column(name = "facom_tmp_porc")
    private BigDecimal porcRetencion;
    @Column(name = "facom_tmp_vlpr")
    private BigDecimal valorProductos;
    @Column(name = "facom_tmp_vlraj")
    private BigDecimal valorAjuste;
    @Column(name = "facom_tmp_vltot")
    private BigDecimal valorTotalPagar;
    @Column(name = "facom_tmp_cbret")
    private String cobraRete;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaFacCompra() {
        return fechaFacCompra;
    }

    public void setFechaFacCompra(Date fechaFacCompra) {
        this.fechaFacCompra = fechaFacCompra;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public BigDecimal getValorFacura() {
        return valorFacura;
    }

    public void setValorFacura(BigDecimal valorFacura) {
        this.valorFacura = valorFacura;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public ProveedoresEntity getProveedor() {
        return proveedor;
    }

    public void setProveedor(ProveedoresEntity proveedor) {
        this.proveedor = proveedor;
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

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public BigDecimal getValorIva() {
        return valorIva;
    }

    public void setValorIva(BigDecimal valorIva) {
        this.valorIva = valorIva;
    }

    public BigDecimal getValorRetencion() {
        return valorRetencion;
    }

    public void setValorRetencion(BigDecimal valorRetencion) {
        this.valorRetencion = valorRetencion;
    }

    public SedeEntity getSede() {
        return sede;
    }

    public void setSede(SedeEntity sede) {
        this.sede = sede;
    }

    public String getAjusteFactura() {
        return ajusteFactura;
    }

    public void setAjusteFactura(String ajusteFactura) {
        this.ajusteFactura = ajusteFactura;
    }

    public Integer getPlazo() {
        return plazo;
    }

    public void setPlazo(Integer plazo) {
        this.plazo = plazo;
    }

    public BigDecimal getPorcRetencion() {
        return porcRetencion;
    }

    public void setPorcRetencion(BigDecimal porcRetencion) {
        this.porcRetencion = porcRetencion;
    }

    public BigDecimal getValorProductos() {
        return valorProductos;
    }

    public void setValorProductos(BigDecimal valorProductos) {
        this.valorProductos = valorProductos;
    }

    public BigDecimal getValorAjuste() {
        return valorAjuste;
    }

    public void setValorAjuste(BigDecimal valorAjuste) {
        this.valorAjuste = valorAjuste;
    }

    public BigDecimal getValorTotalPagar() {
        return valorTotalPagar;
    }

    public void setValorTotalPagar(BigDecimal valorTotalPagar) {
        this.valorTotalPagar = valorTotalPagar;
    }

    public String getCobraRete() {
        return cobraRete;
    }

    public void setCobraRete(String cobraRete) {
        this.cobraRete = cobraRete;
    }

}
