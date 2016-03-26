/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.persistence.entities;

import co.com.codesoftware.persistence.entity.productos.FacturaCompraEntity;
import co.com.codesoftware.persistence.entity.productos.ImagenesFacCompraEntity;
import co.com.codesoftware.persistence.entity.productos.PagoFacCompraEntity;
import co.com.codesoftware.persistence.entity.productos.ProductoFacCompraEntity;
import java.util.List;

/**
 *
 * @author john
 */
public class FacturaCompraGeneralEntity {
    private FacturaCompraEntity factura;
    private List<ImagenesFacCompraEntity> imagenes;
    private List<PagoFacCompraEntity> pagos;
    private List<ProductoFacCompraEntity> productos;

    public FacturaCompraEntity getFactura() {
        return factura;
    }

    public void setFactura(FacturaCompraEntity factura) {
        this.factura = factura;
    }

    public List<ImagenesFacCompraEntity> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<ImagenesFacCompraEntity> imagenes) {
        this.imagenes = imagenes;
    }

    public List<PagoFacCompraEntity> getPagos() {
        return pagos;
    }

    public void setPagos(List<PagoFacCompraEntity> pagos) {
        this.pagos = pagos;
    }

    public List<ProductoFacCompraEntity> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoFacCompraEntity> productos) {
        this.productos = productos;
    }


    
    
}
