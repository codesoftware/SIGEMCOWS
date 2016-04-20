package co.com.codesoftware.persistencia.entidad.admin;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "em_tsede")
public class SedeEntity implements Serializable {

    @Id
    @Column(name = "sede_sede")
    private Integer id;
    @Column(name = "sede_nombre")
    private String name;
    @Column(name = "sede_direccion")
    private String direccion;
    @Column(name = "sede_telefono")
    private String telefono;
    @Column(name = "sede_bodega")
    private String sedeBodega;
    @Column(name = "sede_rsfa")
    private Integer resolucion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getSedeBodega() {
        return sedeBodega;
    }

    public void setSedeBodega(String sedeBodega) {
        this.sedeBodega = sedeBodega;
    }

    public Integer getResolucion() {
        return resolucion;
    }

    public void setResolucion(Integer resolucion) {
        this.resolucion = resolucion;
    }
}
