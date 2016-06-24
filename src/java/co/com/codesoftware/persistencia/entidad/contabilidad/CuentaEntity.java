/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.persistencia.entidad.contabilidad;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ACER
 */
@Entity
@Table(name = "CO_TCUEN")
public class CuentaEntity implements Serializable {
    @Id
    @Column(name = "CUEN_CUEN")
    private Integer id;
    @Column(name = "CUEN_GRUP")
    private Integer idGrupo;
    @Column(name = "CUEN_ESTADO")
    private String estado;
    @Column(name = "CUEN_NOMBRE")
    private String nombre;
    @Column(name = "CUEN_CODIGO")
    private String codigo;
    @Column(name = "CUEN_DESCRIPCION")
    private String descripcion;
    @Column(name = "CUEN_NATURALEZA")
    private String naturaleza;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNaturaleza() {
        return naturaleza;
    }

    public void setNaturaleza(String naturaleza) {
        this.naturaleza = naturaleza;
    }

}
