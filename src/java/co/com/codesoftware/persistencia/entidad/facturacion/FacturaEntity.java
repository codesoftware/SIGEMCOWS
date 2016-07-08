package co.com.codesoftware.persistencia.entidad.facturacion;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import co.com.codesoftware.persistencia.entidad.admin.ClienteEntity;
import co.com.codesoftware.persistencia.entidad.admin.SedeEntity;
import java.io.Serializable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Table(name = "fa_tfact")
public class FacturaEntity implements Serializable {

    @Id
    @Column(name = "fact_fact")
    private Integer id;
    @Column(name = "fact_tius")
    private Integer idTius;
    @Temporal(TemporalType.DATE)
    @Column(name = "fact_fec_ini")
    private Date fecha;
    @Column(name = "fact_vlr_total")
    private BigDecimal valor;
    @Column(name = "fact_vlr_iva")
    private BigDecimal vlrIva;
    @Column(name = "fact_tipo_pago")
    private String tipoPago;
    @Column(name = "fact_id_voucher")
    private String idVaucher;
    @Column(name = "fact_cometarios")
    private String comentarios;
    @Column(name = "fact_estado")
    private String estado;
    @Column(name = "fact_naturaleza")
    private String naturaleza;
    @Column(name = "fact_devolucion")
    private String devolucion;
    @Column(name = "fact_original")
    private Integer original;
    @Column(name = "fact_vlr_dcto")
    private BigDecimal descuento;
    @Column(name = "fact_vlr_efectivo")
    private BigDecimal efectivo;
    @Column(name = "fact_vlr_tarjeta")
    private BigDecimal tarjeta;
    @Column(name = "fact_cierre")
    private Integer idCierre;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fact_sede")
    private SedeEntity idSede;
    @Transient
    private List<DetProduFacturaEntity> detalleProductos;
    @Transient
    private List<DetReceFacturaEntity> detalleRecetas;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fact_clien")
    private ClienteEntity cliente;
    @Transient
    private Date fechaExacta;
    @Column(name = "fact_cons")
    private Integer consecutivo;
    @Column(name = "fact_rsfa")
    private Integer resolucion;
    @Column(name = "fact_vlr_acobrar")
    private BigDecimal valorCobrar;
    @Column(name = "FACT_VLRRTFU")
    private BigDecimal vlrReteFu;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdTius() {
        return idTius;
    }

    public void setIdTius(Integer idTius) {
        this.idTius = idTius;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getVlrIva() {
        return vlrIva;
    }

    public void setVlrIva(BigDecimal vlrIva) {
        this.vlrIva = vlrIva;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public String getIdVaucher() {
        return idVaucher;
    }

    public void setIdVaucher(String idVaucher) {
        this.idVaucher = idVaucher;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNaturaleza() {
        return naturaleza;
    }

    public void setNaturaleza(String naturaleza) {
        this.naturaleza = naturaleza;
    }

    public String getDevolucion() {
        return devolucion;
    }

    public void setDevolucion(String devolucion) {
        this.devolucion = devolucion;
    }

    public Integer getOriginal() {
        return original;
    }

    public void setOriginal(Integer original) {
        this.original = original;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public BigDecimal getEfectivo() {
        return efectivo;
    }

    public void setEfectivo(BigDecimal efectivo) {
        this.efectivo = efectivo;
    }

    public BigDecimal getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(BigDecimal tarjeta) {
        this.tarjeta = tarjeta;
    }

    public Integer getIdCierre() {
        return idCierre;
    }

    public void setIdCierre(Integer idCierre) {
        this.idCierre = idCierre;
    }

    public SedeEntity getIdSede() {
        return idSede;
    }

    public void setIdSede(SedeEntity idSede) {
        this.idSede = idSede;
    }

    public Date getFechaExacta() {
        return fechaExacta;
    }

    public void setFechaExacta(Date fechaExacta) {
        this.fechaExacta = fechaExacta;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public List<DetProduFacturaEntity> getDetalleProductos() {
        return detalleProductos;
    }

    public void setDetalleProductos(List<DetProduFacturaEntity> detalleProductos) {
        this.detalleProductos = detalleProductos;
    }

    public List<DetReceFacturaEntity> getDetalleRecetas() {
        return detalleRecetas;
    }

    public void setDetalleRecetas(List<DetReceFacturaEntity> detalleRecetas) {
        this.detalleRecetas = detalleRecetas;
    }

    public Integer getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(Integer consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Integer getResolucion() {
        return resolucion;
    }

    public void setResolucion(Integer resolucion) {
        this.resolucion = resolucion;
    }

    public BigDecimal getValorCobrar() {
        return valorCobrar;
    }

    public void setValorCobrar(BigDecimal valorCobrar) {
        this.valorCobrar = valorCobrar;
    }

    public BigDecimal getVlrReteFu() {
        return vlrReteFu;
    }

    public void setVlrReteFu(BigDecimal vlrReteFu) {
        this.vlrReteFu = vlrReteFu;
    }

}
