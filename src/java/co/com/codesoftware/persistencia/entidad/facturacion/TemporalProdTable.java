package co.com.codesoftware.persistencia.entidad.facturacion;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Entity
@Table(name = "CO_TTEM_FACT")
public class TemporalProdTable implements Serializable {
    @Id
    @Column(name = "TEM_FACT")    
    private Integer id;
    @Column(name = "tem_fact_trans")
    private Integer idTrans;
    @Column(name = "tem_fact_dska")
    private Integer idDska;
    @Column(name = "tem_fact_cant")
    private Integer cantidad;
    @Column(name = "tem_fact_dcto")
    private Integer descuento;
    @Column(name = "tem_fact_pruni")
    private BigDecimal precio;

    @Generated(GenerationTime.INSERT)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdTrans() {
        return idTrans;
    }

    public void setIdTrans(Integer idTrans) {
        this.idTrans = idTrans;
    }

    public Integer getIdDska() {
        return idDska;
    }

    public void setIdDska(Integer idDska) {
        this.idDska = idDska;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getDescuento() {
        return descuento;
    }

    public void setDescuento(Integer descuento) {
        this.descuento = descuento;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

}
