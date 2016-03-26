package co.com.codesoftware.persistencia.entidad.receta;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "in_trece", uniqueConstraints = {
    @UniqueConstraint(columnNames = "rece_codigo")})
public class RecetaEntity implements Serializable {
    @Id
    @Column(name = "rece_rece", nullable = false)
    private Integer id;
    @Column(name = "rece_codigo", nullable = false)
    private String codigo;
    @Column(name = "rece_nombre")
    private String nombre;
    @Column(name = "rece_desc")
    private String descripcion;
    @Column(name = "rece_iva")
    private String iva;
    @Column(name = "rece_estado")
    private String estado;
    @Temporal(TemporalType.DATE)
    @Column(name = "rece_fec_ingreso")
    private Date fechaIngreso;
    @Column(name = "rece_promedio")
    private BigDecimal promedio;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public BigDecimal getPromedio() {
        return promedio;
    }

    public void setPromedio(BigDecimal promedio) {
        this.promedio = promedio;
    }
}
