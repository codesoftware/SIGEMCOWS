/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.persistence.entity.facturacion;

import co.com.codesoftware.persistencia.entidad.admin.ClienteEntity;
import co.com.codesoftware.persistencia.entidad.admin.PersonaEntity;
import java.io.Serializable;
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
 * @author john
 */
@Entity
@Table(name = "fa_thfac")
public class HistorialFacturaEntity implements Serializable {

    @Id
    @Column(name = "hfac_hfac")
    private Integer id;
    @JoinColumn(name = "hfac_tius")
    @OneToOne(fetch = FetchType.LAZY)
    private PersonaEntity idUsuario;
    @Column(name = "hfac_fech")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "hfac_come")
    private String comentarios;
    @JoinColumn(name = "hfac_clie")
    @OneToOne(fetch = FetchType.LAZY)
    private ClienteEntity idCliente;
    @Column(name = "hfac_fact")
    private Integer idFactura;
    @Column(name = "hfac_esta")
    private String estado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PersonaEntity getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(PersonaEntity idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public ClienteEntity getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(ClienteEntity idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    

}
