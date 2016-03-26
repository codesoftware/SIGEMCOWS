/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.persistencia.entidad.generico.producto;

import co.com.codesoftware.persistencia.utilities.TypeProduct;
import java.math.BigDecimal;

/**
 * Clase con la cual se representara la entidad generica entre precios y recetas
 *
 * @author ACER
 */
public class ProductoGenericoEntity {

    private Integer id;
    private TypeProduct tipoProducto;
    private String codigo;
    private String codigoExterno;
    private String codigoBarras;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TypeProduct getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(TypeProduct tipoProducto) {
        this.tipoProducto = tipoProducto;
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

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getCodigoExterno() {
        return codigoExterno;
    }

    public void setCodigoExterno(String codigoExterno) {
        this.codigoExterno = codigoExterno;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
