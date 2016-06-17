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
@Table(name = "im_tgast")
public class GastoImpoEntity implements Serializable{
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
    
    
    
}