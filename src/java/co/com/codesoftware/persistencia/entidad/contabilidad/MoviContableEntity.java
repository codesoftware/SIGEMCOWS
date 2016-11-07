/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.persistencia.entidad.contabilidad;

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
 * MO-001 se añade el mapeo del tercero sin la relacion jmorenor1986 07/11/2016
 * ----------------------------------------------------
 */
@Entity
@Table(name = "co_tmvco")
public class MoviContableEntity implements Serializable {

    @Id
    @Column(name = "mvco_mvco")
    private Integer id;
    @Column(name = "mvco_trans")
    private Integer idMovimiento;
    @JoinColumn(name = "mvco_sbcu")
    @OneToOne(fetch = FetchType.LAZY)
    private SubCuentaEntity subcuenta;
    @Column(name = "mvco_naturaleza")
    private String naturaleza;
    @JoinColumn(name = "mvco_tido")
    @OneToOne(fetch = FetchType.LAZY)
    private TipoDocumentoEntity tipoDocumento;
    @Column(name = "mvco_valor")
    private BigDecimal valor;
    @Column(name = "mvco_lladetalle")
    private String llave;
    @Column(name = "mvco_id_llave")
    private Integer idLlave;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mvco_auco")
    private AuxContableEntity auxiliar;
    @Column(name = "mvco_fecha")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "mvco_tercero")
    private Integer tercero;
    @Column(name = "mvco_tipo")
    private Integer tipoTercero;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(Integer idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public SubCuentaEntity getSubcuenta() {
        return subcuenta;
    }

    public void setSubcuenta(SubCuentaEntity subcuenta) {
        this.subcuenta = subcuenta;
    }

    public String getNaturaleza() {
        if ("C".equalsIgnoreCase(naturaleza)) {
            return "CREDITO";
        } else {
            return "DEBITO";
        }
    }

    public void setNaturaleza(String naturaleza) {
        this.naturaleza = naturaleza;
    }

    public TipoDocumentoEntity getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumentoEntity tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getLlave() {
        return llave;
    }

    public void setLlave(String llave) {
        this.llave = llave;
    }

    public Integer getIdLlave() {
        return idLlave;
    }

    public void setIdLlave(Integer idLlave) {
        this.idLlave = idLlave;
    }

    public AuxContableEntity getAuxiliar() {
        return auxiliar;
    }

    public void setAuxiliar(AuxContableEntity auxiliar) {
        this.auxiliar = auxiliar;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getTercero() {
        return tercero;
    }

    public void setTercero(Integer tercero) {
        this.tercero = tercero;
    }

    public Integer getTipoTercero() {
        return tipoTercero;
    }

    public void setTipoTercero(Integer tipoTercero) {
        this.tipoTercero = tipoTercero;
    }

}
