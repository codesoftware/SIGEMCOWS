/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.persistencia.entidad.admin;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ACER
 */
@Entity
@Table(name = "em_tapor")
public class AporteSocioEntity implements Serializable {
    @Id
    @Column(name = "apor_apor")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "apor_codigo")
    private String codigo;
    @Column(name = "apor_desc")
    private String descripcion;
    @Column(name = "apor_tius")
    private Integer usuario;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "apor_fecha")
    private Date fecha;
    @Column(name = "apor_valor")
    private BigDecimal valor;
    @Column(name = "apor_soci")
    private Integer socio;
    @Column(name = "apor_tran_mvco")
    private Integer idTransMvco;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Integer getSocio() {
        return socio;
    }

    public void setSocio(Integer socio) {
        this.socio = socio;
    }

    public Integer getIdTransMvco() {
        return idTransMvco;
    }

    public void setIdTransMvco(Integer idTransMvco) {
        this.idTransMvco = idTransMvco;
    }

}
