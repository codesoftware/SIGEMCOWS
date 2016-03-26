/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.persistence.entity.productos;

import co.com.codesoftware.persistencia.entidad.inventario.CategoriaEntity;
import co.com.codesoftware.persistencia.entidad.inventario.MarcaEntity;
import co.com.codesoftware.persistencia.entidad.inventario.ReferenciaEntity;
import co.com.codesoftware.persistencia.entidad.admin.SedeEntity;
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
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author nicolas
 */
@Entity
@Table(name = "in_tpops")
public class PorcentajePrecioEntity implements Serializable {

    @Id
    @Column(name = "pops_pops")
    private Integer id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "pops_fecha")
    private Date fecha;
    @JoinColumn(name = "pops_sede")
    @ManyToOne(fetch = FetchType.LAZY)
    private SedeEntity sede;
    @JoinColumn(name = "pops_cate")
    @ManyToOne(fetch = FetchType.LAZY)
    private CategoriaEntity categoria;
    @JoinColumn(name = "pops_refe")
    @ManyToOne(fetch = FetchType.LAZY)
    private ReferenciaEntity referencia;
    @JoinColumn(name = "pops_marca")
    @ManyToOne(fetch = FetchType.LAZY)
    private MarcaEntity marca;
    @Column(name = "pops_tius")
    private Integer idTius;
    @Column(name = "pops_estado")
    private String estado;
    @Column(name = "pops_porc_base")
    private Integer porcBase;
    @Column(name = "pops_porc_uni")
    private Integer porcUni;
    @Column(name = "pops_porc_cent")
    private Integer porcDec;
    @Column(name = "pops_porc_mill")
    private Integer porcMill;

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

    public SedeEntity getSede() {
        return sede;
    }

    public void setSede(SedeEntity sede) {
        this.sede = sede;
    }

    public Integer getIdTius() {
        return idTius;
    }

    public void setIdTius(Integer idTius) {
        this.idTius = idTius;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getPorcBase() {
        return porcBase;
    }

    public void setPorcBase(Integer porcBase) {
        this.porcBase = porcBase;
    }

    public Integer getPorcUni() {
        return porcUni;
    }

    public void setPorcUni(Integer porcUni) {
        this.porcUni = porcUni;
    }

    public Integer getPorcDec() {
        return porcDec;
    }

    public void setPorcDec(Integer porcDec) {
        this.porcDec = porcDec;
    }

    public Integer getPorcMill() {
        return porcMill;
    }

    public void setPorcMill(Integer porcMill) {
        this.porcMill = porcMill;
    }

    public CategoriaEntity getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEntity categoria) {
        this.categoria = categoria;
    }

    public ReferenciaEntity getReferencia() {
        return referencia;
    }

    public void setReferencia(ReferenciaEntity referencia) {
        this.referencia = referencia;
    }

    public MarcaEntity getMarca() {
        return marca;
    }

    public void setMarca(MarcaEntity marca) {
        this.marca = marca;
    }

}
