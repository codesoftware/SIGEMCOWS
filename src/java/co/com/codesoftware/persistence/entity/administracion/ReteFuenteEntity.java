/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.persistence.entity.administracion;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author john
 */
@Entity
@Table(name = "co_tretde")
public class ReteFuenteEntity implements Serializable {

    @Id
    @Column(name = "retde_retde")
    private Integer id;
    @Column(name = "retde_veret")
    private Integer idVersion;
    @Column(name = "retde_codig")
    private String codigo;
    @Column(name = "retde_conce")
    private String concepto;
    @Column(name = "retde_bauvt")
    private BigDecimal baseUvt;
    @Column(name = "retde_bpeso")
    private BigDecimal basePesos;
    @Column(name = "retde_tarif")
    private BigDecimal tarifa;
    @Column(name = "retde_estad")
    private String estado;
    @Column(name = "retde_fecha")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fecha;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdVersion() {
        return idVersion;
    }

    public void setIdVersion(Integer idVersion) {
        this.idVersion = idVersion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public BigDecimal getBaseUvt() {
        return baseUvt;
    }

    public void setBaseUvt(BigDecimal baseUvt) {
        this.baseUvt = baseUvt;
    }

    public BigDecimal getBasePesos() {
        return basePesos;
    }

    public void setBasePesos(BigDecimal basePesos) {
        this.basePesos = basePesos;
    }

    public BigDecimal getTarifa() {
        return tarifa;
    }

    public void setTarifa(BigDecimal tarifa) {
        this.tarifa = tarifa;
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
    
    
}
