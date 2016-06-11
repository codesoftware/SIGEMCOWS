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
 * @author nicolas
 */
@Entity
@Table(name = "im_timpo")
public class ImportacionEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "impo_impo")
    private Integer id;
    @Column(name = "impo_nombre")
    private String nombre;
    @Column(name = "impo_fecCrea")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaCrea;
    @Column(name = "impo_fecLlegada")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaLleg;
    @Column(name = "impo_pvin")
    private Integer idProv;
    @Column(name = "impo_tius")
    private Integer idtius;
    @Column(name = "impo_vlrTotal")
    private BigDecimal vlrTotal;
    @Column(name = "impo_vlrImpu")
    private BigDecimal vlrImpuestos;
    @Column(name = "impo_estado")
    private String estado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaCrea() {
        return fechaCrea;
    }

    public void setFechaCrea(Date fechaCrea) {
        this.fechaCrea = fechaCrea;
    }

    public Date getFechaLleg() {
        return fechaLleg;
    }

    public void setFechaLleg(Date fechaLleg) {
        this.fechaLleg = fechaLleg;
    }

    public Integer getIdProv() {
        return idProv;
    }

    public void setIdProv(Integer idProv) {
        this.idProv = idProv;
    }

    public Integer getIdtius() {
        return idtius;
    }

    public void setIdtius(Integer idtius) {
        this.idtius = idtius;
    }

    public BigDecimal getVlrTotal() {
        return vlrTotal;
    }

    public void setVlrTotal(BigDecimal vlrTotal) {
        this.vlrTotal = vlrTotal;
    }

    public BigDecimal getVlrImpuestos() {
        return vlrImpuestos;
    }

    public void setVlrImpuestos(BigDecimal vlrImpuestos) {
        this.vlrImpuestos = vlrImpuestos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
