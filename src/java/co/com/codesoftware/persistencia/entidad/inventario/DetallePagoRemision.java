/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.persistencia.entidad.inventario;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ACER
 */
@Entity
@Table(name = "fa_tdpgr")
public class DetallePagoRemision implements Serializable {

    @Id
    @Column(name = "dpgr_dpgr")
    private Integer id;
    @Column(name = "dpgr_pgrm")
    private Integer idPago;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dpgr_fecha")
    private Date fecha;
    @Column(name = "dpgr_estado")
    private String estado;
    @Column(name = "dpgr_tipopago")
    private String tipoPago;
    @Column(name = "dpgr_valor")
    private BigDecimal valor;
    @Column(name = "dpgr_comprobante")
    private String comprobante;
    @Column(name = "dpgr_vlrdeuda")
    private BigDecimal vlrDeuda;
    @Column(name = "dpgr_mvcot")
    private Integer idMvco;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdPago() {
        return idPago;
    }

    public void setIdPago(Integer idPago) {
        this.idPago = idPago;
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

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getComprobante() {
        return comprobante;
    }

    public void setComprobante(String comprobante) {
        this.comprobante = comprobante;
    }

    public BigDecimal getVlrDeuda() {
        return vlrDeuda;
    }

    public void setVlrDeuda(BigDecimal vlrDeuda) {
        this.vlrDeuda = vlrDeuda;
    }

    public Integer getIdMvco() {
        return idMvco;
    }

    public void setIdMvco(Integer idMvco) {
        this.idMvco = idMvco;
    }

}
