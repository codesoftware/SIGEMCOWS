/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.persistence.entity.productos;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author john
 */
@Entity
@Table(name = "fa_timfac")
public class ImagenesFacCompraEntity implements Serializable {

    @Id
    @Column(name = "imfac_imfac")
    private Integer id;
    @Column(name = "imfac_fecha")
    private Date fecha;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "imfac_facom")
    private FacturaCompraEntity facturaCompra;
    @Column(name = "imfac_nombr")
    private String nombreImagen;
    @Column(name = "imfac_rutai")
    private String rutaImagen;
    @Column(name = "imfac_estad")
    private String estado;
    @Column(name = "imfac_ubica")
    private String ubicacion;

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

    public FacturaCompraEntity getFacturaCompra() {
        return facturaCompra;
    }

    public void setFacturaCompra(FacturaCompraEntity facturaCompra) {
        this.facturaCompra = facturaCompra;
    }

    public String getNombreImagen() {
        return nombreImagen;
    }

    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    

}
