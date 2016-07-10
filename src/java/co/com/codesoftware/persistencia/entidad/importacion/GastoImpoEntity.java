/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.persistencia.entidad.importacion;

import co.com.codesoftware.persistence.entity.administracion.ProveedoresEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author ACER
 */
@Entity
@Table(name = "im_tgast")
public class GastoImpoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gast_gast")
    private Integer id;
    @Column(name = "gast_impo")
    private Integer idImpo;
    @Column(name = "gast_desc")
    private String descrip;
    @Column(name = "gast_tius")
    private Integer idTius;
    @Column(name = "gast_valor")
    private BigDecimal valorGasto;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "gast_fecha")
    private Date fecha;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "gast_fechaRegi")
    private Date fechaRegistro;
    @JoinColumn(name = "gast_prov")
    @OneToOne(fetch = FetchType.LAZY)
    private ProveedoresEntity proveedor;
    @Column(name = "gast_tran_mvco")
    private Integer idTransCo;
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

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public Integer getIdTius() {
        return idTius;
    }

    public void setIdTius(Integer idTius) {
        this.idTius = idTius;
    }

    public BigDecimal getValorGasto() {
        return valorGasto;
    }

    public void setValorGasto(BigDecimal valorGasto) {
        this.valorGasto = valorGasto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public ProveedoresEntity getProveedor() {
        return proveedor;
    }

    public void setProveedor(ProveedoresEntity proveedor) {
        this.proveedor = proveedor;
    }

    public Integer getIdTransCo() {
        return idTransCo;
    }

    public void setIdTransCo(Integer idTransCo) {
        this.idTransCo = idTransCo;
    }

    public Integer getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(Integer consecutivo) {
        this.consecutivo = consecutivo;
    }

}
