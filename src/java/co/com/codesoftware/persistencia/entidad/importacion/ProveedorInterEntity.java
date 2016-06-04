/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.persistencia.entidad.importacion;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author nicolas
 */
@Entity
@Table(name = "im_tpvin")
public class ProveedorInterEntity implements Serializable {
    @Id
    @Column(name = "pvin_pvin")
    private Integer id;
    @Column(name = "pvin_id")
    private String idProv;
    @Column(name = "pvin_nombre")
    private String nombre;
    @Column(name = "pvin_contacto")
    private String contacto;
    @Column(name = "pvin_ubicacion")
    private String ubicacion;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdProv() {
        return idProv;
    }

    public void setIdProv(String idProv) {
        this.idProv = idProv;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

}
