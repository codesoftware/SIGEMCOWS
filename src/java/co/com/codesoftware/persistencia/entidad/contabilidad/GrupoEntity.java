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
@Table(name = "CO_TGRUP")
public class GrupoEntity implements Serializable{
    @Id
    @Column(name = "GRUP_GRUP")
    private Integer id;
    @Column(name = "GRUP_CLAS")
    private Integer idClase;
    @Column(name = "GRUP_ESTADO")
    private String estado;
    @Column(name = "GRUP_NOMBRE")
    private String nombre;
    @Column(name = "GRUP_CODIGO")
    private String codigo;
    @Column(name = "GRUP_DESCRIPCION")
    private String descripcion;
    @Column(name = "GRUP_NATURALEZA")
    private String naturaleza;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdClase() {
        return idClase;
    }

    public void setIdClase(Integer idClase) {
        this.idClase = idClase;
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
