/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.persistence.entities.simple;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author nicolas
 */
@Entity
@Table(name = "in_tprpr")
public class PrecioProdSimpleEntity implements Serializable {

    @Id
    @Column(name = "prpr_prpr")
    private Integer id;
    @Column(name = "prpr_dska")
    private Integer idDska;
    @Column(name = "prpr_precio")
    private BigDecimal precio;
    @Column(name = "prpr_premsiva")
    private BigDecimal precioConIva;
    @Column(name = "prpr_tius_crea")
    private Integer idTius;
    @Column(name = "prpr_tius_update")
    private Integer idTiusUpd;
    @Column(name = "prpr_estado")
    private String estado;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "prpr_fecha")
    private Date fecha;
    @Column(name = "prpr_sede")
    private Integer idSede;
    @Column(name = "prpr_preu")
    private BigDecimal precioUnidad;
    @Column(name = "prpr_prec")
    private BigDecimal precioCentena;
    @Column(name = "prpr_prem")
    private BigDecimal precioMillar;

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

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public BigDecimal getPrecioConIva() {
        return precioConIva;
    }

    public void setPrecioConIva(BigDecimal precioConIva) {
        this.precioConIva = precioConIva;
    }

    public Integer getIdTius() {
        return idTius;
    }

    public void setIdTius(Integer idTius) {
        this.idTius = idTius;
    }

    public Integer getIdTiusUpd() {
        return idTiusUpd;
    }

    public void setIdTiusUpd(Integer idTiusUpd) {
        this.idTiusUpd = idTiusUpd;
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

    public Integer getIdSede() {
        return idSede;
    }

    public void setIdSede(Integer idSede) {
        this.idSede = idSede;
    }

    public BigDecimal getPrecioUnidad() {
        return precioUnidad;
    }

    public void setPrecioUnidad(BigDecimal precioUnidad) {
        this.precioUnidad = precioUnidad;
    }

    public BigDecimal getPrecioCentena() {
        return precioCentena;
    }

    public void setPrecioCentena(BigDecimal precioCentena) {
        this.precioCentena = precioCentena;
    }

    public BigDecimal getPrecioMillar() {
        return precioMillar;
    }

    public void setPrecioMillar(BigDecimal precioMillar) {
        this.precioMillar = precioMillar;
    }

}
