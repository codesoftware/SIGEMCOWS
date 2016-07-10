/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.persistencia.entidad.importacion;

import co.com.codesoftware.persistence.entities.simple.ProductoSimpleEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author ACER
 */
@Entity
@Table(name = "im_tprim")
public class ProductoImportacionEntity implements Serializable {

    @Id
    @Column(name = "prim_prim")
    private Integer id;
    @Column(name = "prim_impo")
    private Integer idImpo;
    @JoinColumn(name = "prim_dska")
    @OneToOne(fetch = FetchType.LAZY)
    private ProductoSimpleEntity producto;
    @Column(name = "prim_cant")
    private Integer cantidad;
    @Column(name = "prim_vlrDolar")
    private BigDecimal vlrDolar;
    @Column(name = "prim_vlrPesTRM")
    private BigDecimal vlrPesTRM;
    @Column(name = "prim_vlrPesTzProm")
    private BigDecimal vlrPesTzProm;
    @Transient
    private Integer consecutivo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdImpo() {
        return idImpo;
    }

    public void setIdImpo(Integer idImpo) {
        this.idImpo = idImpo;
    }

    public ProductoSimpleEntity getProducto() {
        return producto;
    }

    public void setProducto(ProductoSimpleEntity producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getVlrDolar() {
        return vlrDolar;
    }

    public void setVlrDolar(BigDecimal vlrDolar) {
        this.vlrDolar = vlrDolar;
    }

    public BigDecimal getVlrPesTRM() {
        return vlrPesTRM;
    }

    public void setVlrPesTRM(BigDecimal vlrPesTRM) {
        this.vlrPesTRM = vlrPesTRM;
    }

    public BigDecimal getVlrPesTzProm() {
        return vlrPesTzProm;
    }

    public void setVlrPesTzProm(BigDecimal vlrPesTzProm) {
        this.vlrPesTzProm = vlrPesTzProm;
    }

    public Integer getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(Integer consecutivo) {
        this.consecutivo = consecutivo;
    }

}
