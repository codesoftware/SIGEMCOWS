/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.persistence.entity.productos;

import co.com.codesoftware.persistence.entities.simple.ProductoSimpleEntity;
import co.com.codesoftware.persistencia.entidad.admin.SedeEntity;
import co.com.codesoftware.persistencia.entidad.admin.UsuarioEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author ACER
 */
@Entity
@Table(name = "in_tkapr")
public class KardexProductoEntity implements Serializable {

    @Id
    @Column(name = "KAPR_KAPR")
    private Integer id;
    @Column(name = "KAPR_CONS_PRO")
    private Integer idConsProd;
    @JoinColumn(name = "KAPR_DSKA")
    @ManyToOne(fetch = FetchType.LAZY)
    private ProductoSimpleEntity producto;
    @Column(name = "KAPR_FECHA")
    private Date fecha;
    @JoinColumn(name = "KAPR_MVIN")
    @ManyToOne(fetch = FetchType.LAZY)
    private MovimientoInventarioEntity movInv;
    @Column(name = "KAPR_CANT_MVTO")
    private Integer cantMvto;
    @Column(name = "KAPR_COST_MVTO_UNI")
    private BigDecimal costoMvtoUni;
    @Column(name = "KAPR_COST_MVTO_TOT")
    private BigDecimal costoMvtoTot;
    @Column(name = "KAPR_COST_SALDO_UNI")
    private BigDecimal costoSaldoUni;
    @Column(name = "KAPR_COST_SALDO_TOT")
    private BigDecimal costoSaldoTot;
    @Column(name = "KAPR_CANT_SALDO")
    private Integer cantidadTot;
    @Column(name = "KAPR_PROV")
    private Integer idProv;
    @JoinColumn(name = "kapr_tius")
    @ManyToOne(fetch = FetchType.LAZY)
    private UsuarioEntity usuario;
    @JoinColumn(name = "KAPR_SEDE")
    @ManyToOne(fetch = FetchType.LAZY)
    private SedeEntity sede;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdConsProd() {
        return idConsProd;
    }

    public void setIdConsProd(Integer idConsProd) {
        this.idConsProd = idConsProd;
    }

    public ProductoSimpleEntity getProducto() {
        return producto;
    }

    public void setProducto(ProductoSimpleEntity producto) {
        this.producto = producto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public MovimientoInventarioEntity getMovInv() {
        return movInv;
    }

    public void setMovInv(MovimientoInventarioEntity movInv) {
        this.movInv = movInv;
    }

    public Integer getCantMvto() {
        return cantMvto;
    }

    public void setCantMvto(Integer cantMvto) {
        this.cantMvto = cantMvto;
    }

    public BigDecimal getCostoMvtoUni() {
        return costoMvtoUni;
    }

    public void setCostoMvtoUni(BigDecimal costoMvtoUni) {
        this.costoMvtoUni = costoMvtoUni;
    }

    public BigDecimal getCostoMvtoTot() {
        return costoMvtoTot;
    }

    public void setCostoMvtoTot(BigDecimal costoMvtoTot) {
        this.costoMvtoTot = costoMvtoTot;
    }

    public BigDecimal getCostoSaldoUni() {
        return costoSaldoUni;
    }

    public void setCostoSaldoUni(BigDecimal costoSaldoUni) {
        this.costoSaldoUni = costoSaldoUni;
    }

    public BigDecimal getCostoSaldoTot() {
        return costoSaldoTot;
    }

    public void setCostoSaldoTot(BigDecimal costoSaldoTot) {
        this.costoSaldoTot = costoSaldoTot;
    }

    public Integer getCantidadTot() {
        return cantidadTot;
    }

    public void setCantidadTot(Integer cantidadTot) {
        this.cantidadTot = cantidadTot;
    }

    public Integer getIdProv() {
        return idProv;
    }

    public void setIdProv(Integer idProv) {
        this.idProv = idProv;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public SedeEntity getSede() {
        return sede;
    }

    public void setSede(SedeEntity sede) {
        this.sede = sede;
    }

}
