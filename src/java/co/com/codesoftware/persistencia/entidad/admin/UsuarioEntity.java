package co.com.codesoftware.persistencia.entidad.admin;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "us_ttius")
public class UsuarioEntity  implements Serializable {

    @Id
    @Column(name = "tius_tius")
    private Integer id;
    @Column(name = "tius_usuario")
    private String usuario;
    @Temporal(TemporalType.DATE)
    @Column(name = "tius_fecha_registro")
    private Date fecha_registro;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "tius_ultimo_ingreso")
    private Date ultimo_ingresa;
    @Column(name = "tius_contra_act")
    private String password;
    @Column(name = "tius_contra_futura")
    private String passwordFuturo;
    @Column(name = "tius_cambio_contra")
    private String cambioContra;
    @Column(name = "tius_estado")
    private String estado;
    @JoinColumn(name = "tius_pers")
    @OneToOne(fetch = FetchType.LAZY)
    private PersonaEntity persona;
    @JoinColumn(name = "tius_perf")
    @OneToOne(fetch = FetchType.LAZY)
    private PerfilEntity perfil;
    @JoinColumn(name = "tius_sede")
    @OneToOne(fetch = FetchType.LAZY)
    private SedeEntity sede;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordFuturo() {
        return passwordFuturo;
    }

    public void setPasswordFuturo(String passwordFuturo) {
        this.passwordFuturo = passwordFuturo;
    }

    public String getCambioContra() {
        return cambioContra;
    }

    public void setCambioContra(String cambioContra) {
        this.cambioContra = cambioContra;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public Date getUltimo_ingresa() {
        return ultimo_ingresa;
    }

    public void setUltimo_ingresa(Date ultimo_ingresa) {
        this.ultimo_ingresa = ultimo_ingresa;
    }

    public PersonaEntity getPersona() {
        return persona;
    }

    public void setPersona(PersonaEntity persona) {
        this.persona = persona;
    }

    public PerfilEntity getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilEntity perfil) {
        this.perfil = perfil;
    }

    public SedeEntity getSede() {
        return sede;
    }

    public void setSede(SedeEntity sede) {
        this.sede = sede;
    }

}
