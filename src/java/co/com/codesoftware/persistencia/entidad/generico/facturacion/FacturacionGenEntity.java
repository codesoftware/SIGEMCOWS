package co.com.codesoftware.persistencia.entidad.generico.facturacion;

import java.util.List;

import co.com.codesoftware.persistencia.entidad.facturacion.TemporalProdTable;
import co.com.codesoftware.persistencia.entidad.facturacion.TemporalRecTable;
import java.io.Serializable;
import java.math.BigDecimal;

public class FacturacionGenEntity implements Serializable {

    private Integer idCliente;
    private Integer idTius;
    private Integer idSede;
    private List<TemporalProdTable> productos;
    private List<TemporalRecTable> recetas;
    private boolean domicilio;
    private BigDecimal descuento;
    private Integer idPedido;
    //Parametro en el cual me indica si cobra retefuente o no
    private String reteFuente;
    //Informacion para generar los pagos con tarjeta de credito
    private String tipoPago;
    private String idVoucher;
    private BigDecimal pagoTarjeta;

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdTius() {
        return idTius;
    }

    public void setIdTius(Integer idTius) {
        this.idTius = idTius;
    }

    public Integer getIdSede() {
        return idSede;
    }

    public void setIdSede(Integer idSede) {
        this.idSede = idSede;
    }

    public List<TemporalProdTable> getProductos() {
        return productos;
    }

    public void setProductos(List<TemporalProdTable> productos) {
        this.productos = productos;
    }

    public List<TemporalRecTable> getRecetas() {
        return recetas;
    }

    public void setRecetas(List<TemporalRecTable> recetas) {
        this.recetas = recetas;
    }

    public boolean isDomicilio() {
        return domicilio;
    }

    public void setDomicilio(boolean domicilio) {
        this.domicilio = domicilio;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public String getReteFuente() {
        return reteFuente;
    }

    public void setReteFuente(String reteFuente) {
        this.reteFuente = reteFuente;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public String getIdVoucher() {
        return idVoucher;
    }

    public void setIdVoucher(String idVoucher) {
        this.idVoucher = idVoucher;
    }

    public BigDecimal getPagoTarjeta() {
        return pagoTarjeta;
    }

    public void setPagoTarjeta(BigDecimal pagoTarjeta) {
        this.pagoTarjeta = pagoTarjeta;
    }

}
