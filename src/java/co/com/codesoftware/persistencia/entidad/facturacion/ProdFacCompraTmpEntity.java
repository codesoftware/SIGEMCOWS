/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.persistencia.entidad.facturacion;

import co.com.codesoftware.persistencia.entidad.inventario.ProductoEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "fa_tfcprd_tmp")
public class ProdFacCompraTmpEntity implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fcprd_tmp_fcprd")
    private Integer id;
    @Column(name = "fcprd_tmp_facom")
    private Integer idFacturaCompra;
    @JoinColumn(name = "fcprd_tmp_dska")
    @OneToOne(fetch = FetchType.LAZY)
    private ProductoEntity producto;
    @Column(name = "fcprd_tmp_cant")
    private Integer cantidad;
    @Column(name = "fcprd_tmp_subt")
    private BigDecimal subtotal;
    @Column(name = "fcprd_tmp_iva")
    private BigDecimal iva;
    @Column(name = "fcprd_tmp_piva")
    private BigDecimal porcentajeIva;
    @Column(name = "fcprd_tmp_tota")
    private BigDecimal total;
    @Column(name = "fcprd_tmp_esta")
    private String estado;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "fcprd_tmp_fech")
    private Date fecha;
    @Column(name = "fcprd_tmp_inve")
    private String inventariable;
    @Column(name = "fcprd_tmp_cantinve")
    private Integer cantidadInventariable;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdFacturaCompra() {
        return idFacturaCompra;
    }

    public void setIdFacturaCompra(Integer idFacturaCompra) {
        this.idFacturaCompra = idFacturaCompra;
    }

    public ProductoEntity getProducto() {
        return producto;
    }

    public void setProducto(ProductoEntity producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    public BigDecimal getPorcentajeIva() {
        return porcentajeIva;
    }

    public void setPorcentajeIva(BigDecimal porcentajeIva) {
        this.porcentajeIva = porcentajeIva;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getInventariable() {
        return inventariable;
    }

    public void setInventariable(String inventariable) {
        this.inventariable = inventariable;
    }

    public Integer getCantidadInventariable() {
        return cantidadInventariable;
    }

    public void setCantidadInventariable(Integer cantidadInventariable) {
        this.cantidadInventariable = cantidadInventariable;
    }
    
    


    
    
}
