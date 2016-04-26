/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.persistencia.entidad.admin;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author nicolas
 */
@Entity
@Table(name = "fa_trsfa")
public class ResolucionFactEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rsfa_rsfa")
    private Integer id;
    @Column(name = "rsfa_prefij")
    private String prefijo;
    @Temporal(TemporalType.DATE)
    @Column(name = "rsfa_fechaInic")
    private Date fechaInicio;
    @Column(name = "rsfa_consec")
    private Integer consecutivo;
    @Column(name = "rsfa_inicon")
    private Integer iniConsecutivo;
    @Column(name = "rsfa_estado")
    private String estado;
    @Temporal(TemporalType.DATE)
    @Column(name = "rsfa_fecha")
    private Date fecha;
    @Column(name = "rsfa_comentario")
    private String comentario;
    @Column(name = "rsfa_numdian")
    private String noResolucion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Integer getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(Integer consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Integer getIniConsecutivo() {
        return iniConsecutivo;
    }

    public void setIniConsecutivo(Integer iniConsecutivo) {
        this.iniConsecutivo = iniConsecutivo;
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

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }    

    public String getNoResolucion() {
        return noResolucion;
    }

    public void setNoResolucion(String noResolucion) {
        this.noResolucion = noResolucion;
    }
    
    
}
