/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.persistence.entity.administracion;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ACER
 */
@Entity
@Table(name = "us_ttius")
public class UsuarioEntity extends RespuestaEntity implements Serializable {

    @Id
    @Column(name = "tius_tius")
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tius_pers")
    private PersonaEntity persona;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tius_perf")
    private PerfilEntity idPerfil;
    @Column(name = "tius_usuario")
    private String usuario;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "tius_ultimo_ingreso")
    private Date ultimoIngreso;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tius_sede")
    private SedeEntity sede;
    @Column(name = "tius_estado")
    private String estado;
    @Column(name = "tius_contra_act")
    private String contrasena;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PerfilEntity getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(PerfilEntity idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Date getUltimoIngreso() {
        return ultimoIngreso;
    }

    public void setUltimoIngreso(Date ultimoIngreso) {
        this.ultimoIngreso = ultimoIngreso;
    }

    public SedeEntity getSede() {
        return sede;
    }

    public void setSede(SedeEntity sede) {
        this.sede = sede;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public PersonaEntity getPersona() {
        return persona;
    }

    public void setPersona(PersonaEntity persona) {
        this.persona = persona;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

}
