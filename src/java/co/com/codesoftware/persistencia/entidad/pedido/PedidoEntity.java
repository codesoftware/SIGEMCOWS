/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.persistencia.entidad.pedido;

import co.com.codesoftware.persistencia.entidad.admin.ClienteEntity;
import co.com.codesoftware.persistencia.entidad.admin.SedeEntity;
import co.com.codesoftware.persistencia.entidad.admin.UsuarioEntity;
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

@Entity
@Table(name = "in_tpedi")
public class PedidoEntity implements Serializable {

    @Id
    @Column(name = "pedi_pedi")
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedi_sede")
    private SedeEntity sede;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedi_usu")
    private UsuarioEntity usuario;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "pedi_fech")
    private Date fecha;
    @Column(name = "pedi_esta")
    private String estado;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedi_clie")
    private ClienteEntity cliente;

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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public SedeEntity getSede() {
        return sede;
    }

    public void setSede(SedeEntity sede) {
        this.sede = sede;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

}
