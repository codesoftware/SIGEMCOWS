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
@Table(name = "fa_tpgrm")
public class PagoRemisionEntity implements Serializable {
    @Id
    @Column(name = "pgrm_pgrm")
    private Integer id;
    @Column(name = "pgrm_clien")
    private Integer idCliente;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "pgrm_fecha")
    private Date fecha;
    @Column(name = "pgrm_fact")
    private Integer idFactu;
    @Column(name = "pgrm_remi")
    private Integer idRemision;
    @Column(name = "pgrm_estado")
    private String estado;
    @Column(name = "pgrm_comprobante")
    private String comprobante;
    @Column(name = "pgrm_vlrdeuda")
    private BigDecimal valorDeuda;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getIdFactu() {
        return idFactu;
    }

    public void setIdFactu(Integer idFactu) {
        this.idFactu = idFactu;
    }

    public Integer getIdRemision() {
        return idRemision;
    }

    public void setIdRemision(Integer idRemision) {
        this.idRemision = idRemision;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getComprobante() {
        return comprobante;
    }

    public void setComprobante(String comprobante) {
        this.comprobante = comprobante;
    }

    public BigDecimal getValorDeuda() {
        return valorDeuda;
    }

    public void setValorDeuda(BigDecimal valorDeuda) {
        this.valorDeuda = valorDeuda;
    }

}
