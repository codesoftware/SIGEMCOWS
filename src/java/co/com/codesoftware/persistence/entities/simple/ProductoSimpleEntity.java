/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.persistence.entities.simple;

import co.com.codesoftware.persistencia.entidad.inventario.CategoriaEntity;
import co.com.codesoftware.persistencia.entidad.inventario.MarcaEntity;
import co.com.codesoftware.persistencia.entidad.inventario.ReferenciaEntity;
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
@Table(name = "in_tdska")
public class ProductoSimpleEntity implements Serializable {

    @Id
    @Column(name = "dska_dska")
    private Integer id;
    @Column(name = "dska_refe")
    private Integer referencia;
    @Column(name = "dska_cod")
    private String codigo;
    @Column(name = "dska_nom_prod")
    private String nombre;
    @Column(name = "dska_desc")
    private String descripcion;
    @Column(name = "dska_iva")
    private String iva;
    @Column(name = "dska_porc_iva")
    private Integer ivaPorc;
    @Column(name = "dska_marca")
    private Integer marca;
    @Column(name = "dska_estado")
    private String estado;
    @Column(name = "dska_fec_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "dska_cate")
    private Integer categoria;
    @Column(name = "dska_sbcu")
    private Integer subcuenta;
    @Column(name = "dska_ubicacion")
    private String ubicacion;
    @Column(name = "dska_cod_ext")
    private String codigoExt;
    @Column(name = "dska_barcod")
    private String codigoBarras;
    @Column(name = "dska_inicont")
    private String iniciaConteo;
    @JoinColumn(name = "dska_marca", insertable = false, updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    private MarcaEntity marcaObj;
    @JoinColumn(name = "dska_cate", insertable = false, updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    private CategoriaEntity categoriaObj;
    @JoinColumn(name = "dska_refe", insertable = false, updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    private ReferenciaEntity referenciaObj;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReferencia() {
        return referencia;
    }

    public void setReferencia(Integer referencia) {
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

    public Integer getIvaPorc() {
        return ivaPorc;
    }

    public void setIvaPorc(Integer ivaPorc) {
        this.ivaPorc = ivaPorc;
    }

    public Integer getMarca() {
        return marca;
    }

    public void setMarca(Integer marca) {
        this.marca = marca;
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

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    public Integer getSubcuenta() {
        return subcuenta;
    }

    public void setSubcuenta(Integer subcuenta) {
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

    public String getIniciaConteo() {
        return iniciaConteo;
    }

    public void setIniciaConteo(String iniciaConteo) {
        this.iniciaConteo = iniciaConteo;
    }

    public MarcaEntity getMarcaObj() {
        return marcaObj;
    }

    public void setMarcaObj(MarcaEntity marcaObj) {
        this.marcaObj = marcaObj;
    }

    public CategoriaEntity getCategoriaObj() {
        return categoriaObj;
    }

    public void setCategoriaObj(CategoriaEntity categoriaObj) {
        this.categoriaObj = categoriaObj;
    }

    public ReferenciaEntity getReferenciaObj() {
        return referenciaObj;
    }

    public void setReferenciaObj(ReferenciaEntity referenciaObj) {
        this.referenciaObj = referenciaObj;
    }

}
