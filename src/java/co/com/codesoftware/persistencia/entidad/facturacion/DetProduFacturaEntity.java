package co.com.codesoftware.persistencia.entidad.facturacion;

import co.com.codesoftware.persistencia.entidad.inventario.ProductoEntity;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.io.Serializable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Table(name = "fa_tdtpr")
public class DetProduFacturaEntity implements Serializable {
    @Id
    @Column(name = "dtpr_dtpr")
    private Integer id;
    @Column(name = "dtpr_fact")
    private Integer idFactura;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dtpr_fecha")
    private Date fecha;
    @Column(name = "dtpr_num_prod")
    private Integer numProd;
    @Column(name = "dtpr_cant")
    private Integer cantidad;
    @Column(name = "dtpr_vlr_pr_tot")
    private BigDecimal valorTotal;
    @Column(name = "dtpr_vlr_uni_prod")
    private BigDecimal valorUnidad;
    @Column(name = "dtpr_vlr_iva_tot")
    private BigDecimal valorIvaTotal;
    @Column(name = "dtpr_vlr_iva_uni")
    private BigDecimal valorIvaUnidad;
    @Column(name = "dtpr_vlr_venta_uni")
    private BigDecimal valorVentaUnidad;
    @Column(name = "dtpr_vlr_total")
    private BigDecimal valorVentaTotal;
    @Column(name = "dtpr_desc")
    private String aplicaDescuento;
    @Column(name = "dtpr_con_desc")
    private String descuento;
    @Column(name = "dtpr_estado")
    private String estado;
    @Column(name = "dtpr_kapr")
    private Integer idKardex;
    @Column(name = "dtpr_dev_kapr")
    private Integer idKardexDevolucion;
    @Column(name = "dtpr_utilidad")
    private BigDecimal utilidad;
    @JoinColumn(name = "dtpr_dska")
    @OneToOne(fetch = FetchType.LAZY)
    private ProductoEntity producto;

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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getNumProd() {
        return numProd;
    }

    public void setNumProd(Integer numProd) {
        this.numProd = numProd;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getValorUnidad() {
        return valorUnidad;
    }

    public void setValorUnidad(BigDecimal valorUnidad) {
        this.valorUnidad = valorUnidad;
    }

    public BigDecimal getValorIvaTotal() {
        return valorIvaTotal;
    }

    public void setValorIvaTotal(BigDecimal valorIvaTotal) {
        this.valorIvaTotal = valorIvaTotal;
    }

    public BigDecimal getValorIvaUnidad() {
        return valorIvaUnidad;
    }

    public void setValorIvaUnidad(BigDecimal valorIvaUnidad) {
        this.valorIvaUnidad = valorIvaUnidad;
    }

    public BigDecimal getValorVentaUnidad() {
        return valorVentaUnidad;
    }

    public void setValorVentaUnidad(BigDecimal valorVentaUnidad) {
        this.valorVentaUnidad = valorVentaUnidad;
    }

    public BigDecimal getValorVentaTotal() {
        return valorVentaTotal;
    }

    public void setValorVentaTotal(BigDecimal valorVentaTotal) {
        this.valorVentaTotal = valorVentaTotal;
    }

    public String getAplicaDescuento() {
        return aplicaDescuento;
    }

    public void setAplicaDescuento(String aplicaDescuento) {
        this.aplicaDescuento = aplicaDescuento;
    }

    public String getDescuento() {
        return descuento;
    }

    public void setDescuento(String descuento) {
        this.descuento = descuento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getIdKardex() {
        return idKardex;
    }

    public void setIdKardex(Integer idKardex) {
        this.idKardex = idKardex;
    }

    public Integer getIdKardexDevolucion() {
        return idKardexDevolucion;
    }

    public void setIdKardexDevolucion(Integer idKardexDevolucion) {
        this.idKardexDevolucion = idKardexDevolucion;
    }

    public BigDecimal getUtilidad() {
        return utilidad;
    }

    public void setUtilidad(BigDecimal utilidad) {
        this.utilidad = utilidad;
    }

    public ProductoEntity getProducto() {
        return producto;
    }

    public void setProducto(ProductoEntity producto) {
        this.producto = producto;
    }
}
