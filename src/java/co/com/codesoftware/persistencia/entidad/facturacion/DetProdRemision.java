/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.persistencia.entidad.facturacion;

import co.com.codesoftware.persistencia.entidad.inventario.ProductoEntity;
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
 * @author ACER
 */
@Entity
@Table(name = "in_tdrem")
public class DetProdRemision implements Serializable {

    @Id
    @Column(name = "drem_drem")
    private Integer id;
    @Column(name = "drem_remi")
    private Integer idRemi;
    @JoinColumn(name = "drem_dska")
    @OneToOne(fetch = FetchType.EAGER)
    private ProductoEntity producto;
    @Column(name = "drem_precio")
    private BigDecimal precio;
    @Column(name = "drem_cantid")
    private Integer cantidad;
    @Column(name = "drem_estado")
    private String estado;
    @Column(name = "drem_codext")
    private String codigoExterno;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdRemi() {
        return idRemi;
    }

    public void setIdRemi(Integer idRemi) {
        this.idRemi = idRemi;
    }

    public ProductoEntity getProducto() {
        return producto;
    }

    public void setProducto(ProductoEntity producto) {
        this.producto = producto;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCodigoExterno() {
        return codigoExterno;
    }

    public void setCodigoExterno(String codigoExterno) {
        this.codigoExterno = codigoExterno;
    }
}
