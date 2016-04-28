/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.persistencia.entidad.inventario;

import co.com.codesoftware.persistencia.entidad.admin.SedeEntity;
import co.com.codesoftware.persistencia.entidad.admin.UsuarioEntity;
import java.io.Serializable;
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
import javax.persistence.TemporalType;
import org.hibernate.annotations.Fetch;

/**
 *
 * @author john
 */
@Entity
@Table(name = "fa_tsoli")
public class SolicitudEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "soli_soli")
    private Integer id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "soli_fech")
    private Date fecha;
    @JoinColumn(name = "soli_tius")
    @OneToOne(fetch = FetchType.LAZY)
    private UsuarioEntity usuario;
    @Column(name = "soli_esta")
    private String estado;
    @Column(name = "soli_come")
    private String comentario;
    @JoinColumn(name = "soli_sede")
    @OneToOne(fetch = FetchType.LAZY)
    private SedeEntity sede;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public SedeEntity getSede() {
        return sede;
    }

    public void setSede(SedeEntity sede) {
        this.sede = sede;
    }
    
    
    
    
}
