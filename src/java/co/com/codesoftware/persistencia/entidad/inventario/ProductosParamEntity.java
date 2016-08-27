/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.persistencia.entidad.inventario;

import co.com.codesoftware.persistencia.entidad.contabilidad.AuxContableEntity;
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
import org.hibernate.annotations.Formula;

/**
 *
 * @author john
 */
@Entity
@Table(name = "in_tpropar")
public class ProductosParamEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "propar_propar")
    private Integer id;
    @Column(name = "propar_dska")
    private Integer idProducto;
    @Column(name = "propar_tius")
    private Integer idUsuario;
    @JoinColumn(name = "propar_auco")
    @OneToOne(fetch = FetchType.LAZY)
    private AuxContableEntity axiliar;
    @Column(name = "propar_desc")
    private String descripcion;
    @Column(name = "propar_esta")
    private String estado;
    @Formula("(select prod.dska_desc from in_tdska prod where prod.dska_dska = propar_dska)")
    private String descripcionProducto;

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public AuxContableEntity getAxiliar() {
        return axiliar;
    }

    public void setAxiliar(AuxContableEntity axiliar) {
        this.axiliar = axiliar;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }
    
    
    
        


}
