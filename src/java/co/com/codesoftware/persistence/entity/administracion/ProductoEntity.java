/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.persistence.entity.administracion;

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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dska_refe")
    private ReferenciaEntity referencia;
    @Column(name = "dska_cod")
    private String codigo;
    @Column(name = "dska_nom_prod")
    private String nombre;
    @Column(name = "dska_desc")
    private String descripcion;
    @Column(name = "dska_iva")
    private String iva;
    @Column(name = "dska_porc_iva")
    private Integer porcentajeIva;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dska_marca")
    private MarcaEntity marca;
    @Column(name = "dska_estado")
    private String estado;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dska_fec_ingreso")
    private Date fechaIngreso;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dska_cate")
    private CategoriaEntity categoria;
    @Column(name = "dska_sbcu")
    private Integer subcuenta;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dska_prov")
    private ProveedoresEntity proveedor;
    @Column(name = "dska_cod_ext")
    private String codigoExterno;
    @Column(name = "dska_ubicacion")
    private String ubicacion;
    @Column(name = "dska_barcod")
    private String codigoBarras;
    

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

    public Integer getPorcentajeIva() {
        return porcentajeIva;
    }

    public void setPorcentajeIva(Integer porcentajeIva) {
        this.porcentajeIva = porcentajeIva;
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

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public CategoriaEntity getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEntity categoria) {
        this.categoria = categoria;
    }

    public Integer getSubcuenta() {
        return subcuenta;
    }

    public void setSubcuenta(Integer subcuenta) {
        this.subcuenta = subcuenta;
    }

    public ProveedoresEntity getProveedor() {
        return proveedor;
    }

    public void setProveedor(ProveedoresEntity proveedor) {
        this.proveedor = proveedor;
    }

    public String getCodigoExterno() {
        return codigoExterno;
    }

    public void setCodigoExterno(String codigoExterno) {
        this.codigoExterno = codigoExterno;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }
    

}
