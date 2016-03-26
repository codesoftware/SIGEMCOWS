package co.com.codesoftware.persistencia.entidad.inventario;

import co.com.codesoftware.persistencia.entidad.admin.SedeEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "in_tprpr")
public class PrecioProductoEntity implements Serializable {

    @Id
    @Column(name = "prpr_prpr")
    private Integer id;
    @Column(name = "prpr_precio")
    private BigDecimal precio;
    @Column(name = "prpr_tius_crea")
    private Integer usuarioCrea;
    @Column(name = "prpr_estado")
    private String estado;
    @JoinColumn(name = "prpr_sede")
    @ManyToOne(fetch = FetchType.LAZY)
    private SedeEntity idSede;
    @Column(name = "prpr_premsiva")
    private BigDecimal precioIva;
    @Column(name = "prpr_preu")
    private BigDecimal precioXUnidad;
    @Column(name = "prpr_prec")
    private BigDecimal precioXCien;
    @Column(name = "prpr_prem")
    private BigDecimal precioXMil;
    @JoinColumn(name = "prpr_dska")
    @ManyToOne(fetch = FetchType.LAZY)
    private ProductoEntity producto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Integer getUsuarioCrea() {
        return usuarioCrea;
    }

    public void setUsuarioCrea(Integer usuarioCrea) {
        this.usuarioCrea = usuarioCrea;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigDecimal getPrecioIva() {
        return precioIva;
    }

    public void setPrecioIva(BigDecimal precioIva) {
        this.precioIva = precioIva;
    }

    public BigDecimal getPrecioXUnidad() {
        return precioXUnidad;
    }

    public void setPrecioXUnidad(BigDecimal precioXUnidad) {
        this.precioXUnidad = precioXUnidad;
    }

    public BigDecimal getPrecioXCien() {
        return precioXCien;
    }

    public void setPrecioXCien(BigDecimal precioXCien) {
        this.precioXCien = precioXCien;
    }

    public BigDecimal getPrecioXMil() {
        return precioXMil;
    }

    public void setPrecioXMil(BigDecimal precioXMil) {
        this.precioXMil = precioXMil;
    }

    public SedeEntity getIdSede() {
        return idSede;
    }

    public void setIdSede(SedeEntity idSede) {
        this.idSede = idSede;
    }

    public ProductoEntity getProducto() {
        return producto;
    }

    public void setProducto(ProductoEntity producto) {
        this.producto = producto;
    }

}
