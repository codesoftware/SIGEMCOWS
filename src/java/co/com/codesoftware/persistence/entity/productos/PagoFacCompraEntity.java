/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.persistence.entity.productos;

import co.com.codesoftware.persistencia.entidad.contabilidad.SubCuentaEntity;
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
import javax.persistence.Temporal;

/**
 *
 * @author john
 */
@Entity
@Table(name = "fa_tfpago")
public class PagoFacCompraEntity implements Serializable{
    @Id
    @Column(name="fpago_fpago")
    private Integer id;
    @Column(name="fpago_facom")
    private Integer idFacturaCompra;
    @Column(name="fpago_tpago")
    private String tipoPago;
    @Column(name="fpago_ivauc")
    private String  idVaucher;
    @Column(name="fpago_valor")
    private BigDecimal valor;
    @JoinColumn(name="fpago_tsbcu")
    @ManyToOne(fetch = FetchType.LAZY)
    private SubCuentaEntity idSubcuenta;
    @Column(name="fpago_fecha")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaPago;
    @Column(name = "fpago_estad")
    private String estado;
    @Column(name = "fa_dpago")
    private Integer diasPago;

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

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public String getIdVaucher() {
        return idVaucher;
    }

    public void setIdVaucher(String idVaucher) {
        this.idVaucher = idVaucher;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public SubCuentaEntity getIdSubcuenta() {
        return idSubcuenta;
    }

    public void setIdSubcuenta(SubCuentaEntity idSubcuenta) {
        this.idSubcuenta = idSubcuenta;
    }


    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getDiasPago() {
        return diasPago;
    }

    public void setDiasPago(Integer diasPago) {
        this.diasPago = diasPago;
    }
    
    

 
    
}
