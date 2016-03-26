/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.persistencia.entidad.inventario;

import co.com.codesoftware.persistencia.entidad.admin.SedeEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author nicolas
 */
@Entity
@Table(name = "in_teprs")
public class ExistenciaXSedeEntity implements Serializable{

    @Id
    @Column(name = "eprs_eprs")
    private Integer id;
    @Column(name = "eprs_dska")
    private Integer idDska;
    @Column(name = "eprs_existencia")
    private Integer existencias;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eprs_sede")
    private SedeEntity sede;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdDska() {
        return idDska;
    }

    public void setIdDska(Integer idDska) {
        this.idDska = idDska;
    }

    public Integer getExistencias() {
        return existencias;
    }

    public void setExistencias(Integer existencias) {
        this.existencias = existencias;
    }

    public SedeEntity getSede() {
        return sede;
    }

    public void setSede(SedeEntity sede) {
        this.sede = sede;
    }
}