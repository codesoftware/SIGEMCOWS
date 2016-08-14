/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.persistence.entity.productos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.Formula;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "fa_tpagof")
public class PagoFacturaCompraEntity implements Serializable {

    @Id
    @Column(name = "pagof_pagof")
    private Integer id;
    @Column(name = "pagof_facom")
    private Integer facturaCompra;
    @Column(name = "pagof_auco")
    private Integer auxContable;
    @Formula("(SELECT auco_nomb from co_tauco where auco_auco = pagof_auco)")
    private String nombreAuxContable;
    @Column(name = "pagof_valor")
    private BigDecimal valor;
    @Column(name = "pagof_tius")
    private Integer usuario;
    @Column(name = "pagof_fecha")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "pagof_estad")
    private String estado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFacturaCompra() {
        return facturaCompra;
    }

    public void setFacturaCompra(Integer facturaCompra) {
        this.facturaCompra = facturaCompra;
    }

    public Integer getAuxContable() {
        return auxContable;
    }

    public void setAuxContable(Integer auxContable) {
        this.auxContable = auxContable;
    }

    public String getNombreAuxContable() {
        return nombreAuxContable;
    }

    public void setNombreAuxContable(String nombreAuxContable) {
        this.nombreAuxContable = nombreAuxContable;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    

}
