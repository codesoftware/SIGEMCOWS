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
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ACER
 */
@Entity
@Table(name = "us_tpers")
public class PersonaEntity implements Serializable{

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
    private String email;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "pers_fecha_nac")
    private Date fechaNac;
    @Column(name = "pers_tel")
    private String telefono;
    @Column(name = "pers_cel")
    private String celular;
    @Column(name = "pers_dir")
    private String direccion;
    @Column(name = "pers_dept_resi")
    private String departResi;
    @Column(name = "pers_ciudad_resi")
    private String ciudadResi;
    

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
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

    public String getDepartResi() {
        return departResi;
    }

    public void setDepartResi(String departResi) {
        this.departResi = departResi;
    }

    public String getCiudadResi() {
        return ciudadResi;
    }

    public void setCiudadResi(String ciudadResi) {
        this.ciudadResi = ciudadResi;
    }

}
