/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.persistencia.entidad.importacion;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author ACER
 */
@Entity
@Table(name = "im_tdgas")
public class DetalleGastoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dgas_dgas")
    private Integer id;
    @Column(name = "dgas_gast")
    private Integer idGasto;
    @Column(name = "dgas_desc")
    private String descr;
    @Column(name = "dgas_tius")
    private Integer idTius;
    @Column(name = "dgas_valor")
    private BigDecimal valor;
    @Column(name = "dgas_fechaRegi")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaRegi;
    @Column(name = "dgas_auco")
    private Integer idAuxconta;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdGasto() {
        return idGasto;
    }

    public void setIdGasto(Integer idGasto) {
        this.idGasto = idGasto;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Integer getIdTius() {
        return idTius;
    }

    public void setIdTius(Integer idTius) {
        this.idTius = idTius;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getFechaRegi() {
        return fechaRegi;
    }

    public void setFechaRegi(Date fechaRegi) {
        this.fechaRegi = fechaRegi;
    }

    public Integer getIdAuxconta() {
        return idAuxconta;
    }

    public void setIdAuxconta(Integer idAuxconta) {
        this.idAuxconta = idAuxconta;
    }
}
