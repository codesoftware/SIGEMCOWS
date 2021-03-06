/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.persistencia.entidad.inventario;

import co.com.codesoftware.persistencia.entidad.contabilidad.SubCuentaEntity;
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
 * @author root
 */
@Entity
@Table(name = "in_tdska")
public class ProductoEntity implements Serializable {

    @Id
    @Column(name = "dska_dska")
    private Integer id;
    @JoinColumn(name = "dska_refe")
    @OneToOne(fetch = FetchType.LAZY)
    private ReferenciaEntity referencia;
    @Column(name = "dska_cod")
    private String codigo;
    @Column(name = "dska_nom_prod")
    private String nombre;
    @Column(name = "dska_desc")
    private String descripcion;
    @Column(name = "dska_iva")
    private String iva;
    @JoinColumn(name = "dska_marca")
    @OneToOne(fetch = FetchType.LAZY)
    private MarcaEntity marca;
    @Column(name = "dska_estado")
    private String estado;
    @JoinColumn(name = "dska_cate")
    @OneToOne(fetch = FetchType.LAZY)
    private CategoriaEntity categoria;
    @JoinColumn(name = "dska_sbcu")
    @OneToOne(fetch = FetchType.LAZY)
    private SubCuentaEntity subcuenta;
    @Column(name = "dska_ubicacion")
    private String ubicacion;
    @Column(name="dska_cod_ext")
    private String codigoExt;
    @Column(name="dska_barcod")
    private String codigoBarras;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dska_fec_ingreso")
    private Date fechaIngreso;
    @Column(name = "dska_porc_iva")
    private Integer porcentajeIva;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ReferenciaEntity getReferencia() {
        return referencia;
    }

    public void setReferencia(ReferenciaEntity referencia) {
        this.referencia = referencia;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIva() {
        return iva;
    }

    public void setIva(String iva) {
        this.iva = iva;
    }


    public MarcaEntity getMarca() {
        return marca;
    }

    public void setMarca(MarcaEntity marca) {
        this.marca = marca;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }



    public CategoriaEntity getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEntity categoria) {
        this.categoria = categoria;
    }

    public SubCuentaEntity getSubcuenta() {
        return subcuenta;
    }

    public void setSubcuenta(SubCuentaEntity subcuenta) {
        this.subcuenta = subcuenta;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getCodigoExt() {
        return codigoExt;
    }

    public void setCodigoExt(String codigoExt) {
        this.codigoExt = codigoExt;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Integer getPorcentajeIva() {
        return porcentajeIva;
    }

    public void setPorcentajeIva(Integer porcentajeIva) {
        this.porcentajeIva = porcentajeIva;
    }
    
    
}
