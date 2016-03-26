/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.persistencia.entidad.pedido;

import co.com.codesoftware.persistencia.entidad.admin.ClienteEntity;
import co.com.codesoftware.persistencia.entidad.generico.producto.ProductoGenEntity;
import co.com.codesoftware.persistencia.utilities.RespuestaEntity;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author root
 */
public class RespuestaPedidoEntity implements Serializable {

    private RespuestaEntity respuesta;
    private List<ProductoGenEntity> listaProductos;
    private ClienteEntity cliente;

    public RespuestaEntity getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(RespuestaEntity respuesta) {
        this.respuesta = respuesta;
    }

    public List<ProductoGenEntity> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<ProductoGenEntity> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

}
