package co.com.codesoftware.persistencia.entidad.admin;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "us_tpers")
public class PersonaEntity implements Serializable {

    @Id
    @Column(name = "pers_pers")
    private Integer id;
    @Column(name = "pers_apellido")
    private String apellido;
    @Column(name = "pers_nombre")
    private String nombre;
    @Column(name = "pers_cedula")
    private String cedula;
    @Column(name = "pers_email")
    private String correo;
    @Temporal(TemporalType.DATE)
    @Column(name = "pers_fecha_nac")
    private Date fecha_nac;
    @Column(name = "pers_tel")
    private String telefono;
    @Column(name = "pers_cel")
    private String celular;
    @Column(name = "pers_dir")
    private String direccion;
    @Column(name = "pers_dept_resi")
    private String departamento;
    @Column(name = "pers_ciudad_resi")
    private String ciudad;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Date getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(Date fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

}
