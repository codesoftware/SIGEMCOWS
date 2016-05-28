/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.persistencia.entidad.inventario;

import co.com.codesoftware.persistence.entities.simple.ProductoSimpleEntity;
import co.com.codesoftware.persistencia.entidad.admin.SedeEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author john
 */
@Entity
@Table(name = "fa_tsopd")
public class SolicitudProdEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sopd_sopd")
    private Integer id;
    @JoinColumn(name = "sopd_dska")
    @OneToOne(fetch = FetchType.LAZY)
    private ProductoSimpleEntity producto;
    @JoinColumn(name = "sopd_soli")
    @OneToOne(fetch = FetchType.LAZY)
    private SolicitudEntity solicitud;
    @Column(name = "sopd_cant")
    private Integer cantidadSolicitada;
    @Column(name = "sopd_cenv")
    private Integer cantidadEnvidada;
    @Column(name = "sopd_cbod")
    private Integer cantidadSede;
    @JoinColumn(name = "sopd_sede")
    @OneToOne(fetch = FetchType.LAZY)
    private SedeEntity sedeProducto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProductoSimpleEntity getProducto() {
        return producto;
    }

    public void setProducto(ProductoSimpleEntity producto) {
        this.producto = producto;
    }

    public SolicitudEntity getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(SolicitudEntity solicitud) {
        this.solicitud = solicitud;
    }

    public Integer getCantidadSolicitada() {
        return cantidadSolicitada;
    }

    public void setCantidadSolicitada(Integer cantidadSolicitada) {
        this.cantidadSolicitada = cantidadSolicitada;
    }

    public Integer getCantidadEnvidada() {
        return cantidadEnvidada;
    }

    public void setCantidadEnvidada(Integer cantidadEnvidada) {
        this.cantidadEnvidada = cantidadEnvidada;
    }

    public Integer getCantidadSede() {
        return cantidadSede;
    }

    public void setCantidadSede(Integer cantidadSede) {
        this.cantidadSede = cantidadSede;
    }

    public SedeEntity getSedeProducto() {
        return sedeProducto;
    }

    public void setSedeProducto(SedeEntity sedeProducto) {
        this.sedeProducto = sedeProducto;
    }
    
    
}
