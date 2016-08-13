/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.persistencia.entidad.admin;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Formula;

/**
 *
 * @author ACER
 */
@Entity
@Table(name = "IN_TPRAP")
public class ProductoAporte implements Serializable{
    @Id
    @Column(name = "PRAP_PRAP")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; 
    @Column(name = "PRAP_APOR")
    private Integer idAporte;
    @Column(name = "PRAP_DSKA")
    private Integer idDska;
    @Column(name = "PRAP_CANT")
    private Integer cantidad;
    @Column(name = "PRAP_COSTO")
    private BigDecimal costo;
    @Formula("(select prod.dska_cod_ext from in_tdska prod where prod.dska_dska = prap_dska)")
    private String codExterno;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdAporte() {
        return idAporte;
    }

    public void setIdAporte(Integer idAporte) {
        this.idAporte = idAporte;
    }

    public Integer getIdDska() {
        return idDska;
    }

    public void setIdDska(Integer idDska) {
        this.idDska = idDska;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public String getCodExterno() {
        return codExterno;
    }

    public void setCodExterno(String codExterno) {
        this.codExterno = codExterno;
    }
    
    
}
