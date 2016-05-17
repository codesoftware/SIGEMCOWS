/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.persistencia.entidad.facturacion;

import co.com.codesoftware.persistencia.entidad.admin.ClienteEntity;
import co.com.codesoftware.persistencia.entidad.admin.UsuarioEntity;
import java.io.Serializable;
import java.math.BigDecimal;
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

/**
 *
 * @author nicolas
 */
@Entity
@Table(name = "in_tremi")
public class RemisionEntity implements Serializable{
    @Id
    @Column(name = "remi_remi")
    private Integer id;
    @JoinColumn(name = "remi_clien")
    @OneToOne(fetch = FetchType.EAGER)
    private ClienteEntity cliente;
    @Column(name = "remi_estado")
    private String estado;
    @JoinColumn(name = "remi_tius")
    @OneToOne(fetch = FetchType.EAGER)
    private UsuarioEntity usuario;
    @Column(name = "remi_plazod")
    private Integer diasPlazo;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "remi_fplazo")
    private Date fechaVenPlazo;
    @Column(name = "remi_venci")
    private String vencida;
    @Column(name = "remi_valor")
    private BigDecimal valorTotal;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "remi_fecha")
    private Date fechaCreacion;
    @Column(name = "remi_fact")
    private Integer idFactura;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public Integer getDiasPlazo() {
        return diasPlazo;
    }

    public void setDiasPlazo(Integer diasPlazo) {
        this.diasPlazo = diasPlazo;
    }

    public Date getFechaVenPlazo() {
        return fechaVenPlazo;
    }

    public void setFechaVenPlazo(Date fechaVenPlazo) {
        this.fechaVenPlazo = fechaVenPlazo;
    }

    public String getVencida() {
        return vencida;
    }

    public void setVencida(String vencida) {
        this.vencida = vencida;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }
}
