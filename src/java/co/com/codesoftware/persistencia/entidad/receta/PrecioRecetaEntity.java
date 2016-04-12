package co.com.codesoftware.persistencia.entidad.receta;

import java.io.Serializable;
import java.math.BigDecimal;
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

@Entity
@Table(name = "in_tprre")
public class PrecioRecetaEntity implements Serializable {

    @Id
    @Column(name = "prre_prre")
    private Integer id;
    @Column(name = "prre_precio")
    private BigDecimal precio;
    @Column(name = "prre_estado")
    private String estado;
    @Temporal(TemporalType.DATE)
    @Column(name = "prre_fecha")
    private Date fecha;
    @Column(name = "prre_sede")
    private Integer idSede;
    @JoinColumn(name = "prre_rece")
    @OneToOne(fetch = FetchType.LAZY)
    private RecetaEntity receta;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
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

 
    public Integer getIdSede() {
        return idSede;
    }

    public void setIdSede(Integer idSede) {
        this.idSede = idSede;
    }

    public RecetaEntity getReceta() {
        return receta;
    }

    public void setReceta(RecetaEntity receta) {
        this.receta = receta;
    }

}
