/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.servicio.pedido;

import co.com.codesoftware.logica.inventario.PedidoLogica;
import co.com.codesoftware.logica.inventario.PedidosProductoLogica;
import co.com.codesoftware.logica.reportes.ReporteLogica;
import co.com.codesoftware.persistencia.entidad.pedido.PedidoEntity;
import co.com.codesoftware.persistencia.entidad.pedido.PedidoProductoEntity;
import co.com.codesoftware.persistencia.entidad.pedido.RespuestaPedidoEntity;
import co.com.codesoftware.persistencia.utilities.RespuestaEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author nicolas
 */
@WebService(serviceName = "PedidoWS")
public class PedidoWS {

    /**
     * metodo que inserta los pedidos de un producto
     *
     * @param productos
     * @param idPedido
     * @return
     */
    @WebMethod(operationName = "insertaProductosXPedido")
    public RespuestaEntity insertaProductoXPedido(@XmlElement(required = true) @WebParam(name = "pedido") ArrayList<PedidoProductoEntity> productos,
            @XmlElement(required = true) @WebParam(name = "idPedido") Integer idPedido) {
        RespuestaEntity respuesta = new RespuestaEntity();
        try (PedidoLogica logic = new PedidoLogica()) {
            respuesta = logic.insertaProductos(productos, idPedido);
        } catch (Exception e) {
            e.printStackTrace();
            respuesta.setCodigoRespuesta(0);
            respuesta.setDescripcionRespuesta(e.getMessage());
            respuesta.setMensajeRespuesta("ERROR" + e.getMessage());
        }
        return respuesta;
    }

    /**
     * metodo que inserta un pedido al sistema
     *
     * @param parametros
     * @return
     */
    @WebMethod(operationName = "insertaPedidosProducto")
    public RespuestaEntity insertaPedidosProducto(@XmlElement(required = true) @WebParam(name = "pedido") PedidoEntity parametros) {
        RespuestaEntity respuesta = new RespuestaEntity();
        try (PedidoLogica logic = new PedidoLogica()) {
            respuesta = logic.insertaPedidos(parametros);

        } catch (Exception e) {
            e.printStackTrace();
            respuesta.setCodigoRespuesta(0);
            respuesta.setDescripcionRespuesta(e.getMessage());
            respuesta.setMensajeRespuesta("ERROR" + e.getMessage());
        }
        return respuesta;
    }

    /**
     * Funcion con la cual se genera un pdf con el pedido a partir de su id
     *
     * @param pedi_pedi
     * @return
     */
    @WebMethod(operationName = "generaCodigoPedido")
    @WebResult(name = "imagen")
    public String generaCodigoPedido(@XmlElement(required = true) @WebParam(name = "pedi_pedi") Integer pedi_pedi) {
        String imagen = null;
        try (ReporteLogica logica = new ReporteLogica()) {
            imagen = logica.generaPdfPedidos(pedi_pedi);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imagen;
    }

    /**
     * Metodo que consulta todo el detalle de un pedido
     *
     * @param idPedido
     * @return
     */
    @WebMethod(operationName = "obtenerPedidoXId")
    @WebResult(name = "RespuestaPedidoEntity")
    public RespuestaPedidoEntity obtenerPedidoXId(@XmlElement(required = true) @WebParam(name = "pedi_pedi") Integer idPedido) {
        RespuestaPedidoEntity respuesta = new RespuestaPedidoEntity();
        try (PedidosProductoLogica logica = new PedidosProductoLogica()) {
            respuesta = logica.consultaProductosXPedido(idPedido);
        } catch (Exception e) {
            RespuestaEntity res = new RespuestaEntity();
            res.setCodigoRespuesta(0);
            res.setDescripcionRespuesta(e.toString());
            res.setMensajeRespuesta(e.getLocalizedMessage());
            respuesta.setRespuesta(res);
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * Funcion que consulta los pedidos dependiendo del estado ingresado
     *
     * @param estado
     * @return
     */
    @WebMethod(operationName = "consultaPedidosXEstado")
    @WebResult(name = "PedidoEntity")
    public List<PedidoEntity> consultaPedidoXEstado(@XmlElement(required = true) @WebParam(name = "pedi_estado") String estado) {
        List<PedidoEntity> respuesta = new ArrayList<>();
        try (PedidoLogica logica = new PedidoLogica()) {
            respuesta = logica.consultaPedidoXEstado(estado);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * metodo que elimina un pedido por el id
     *
     * @param idPedido
     * @return
     */
    @WebMethod(operationName = "eliminaPedido")
    @WebResult(name = "resultado")
    public boolean eliminaPedidoXId(@XmlElement(required = true) @WebParam(name = "pedi_estado") Integer idPedido) {
        boolean respuesta = false;
        try (PedidoLogica logic = new PedidoLogica()) {
            respuesta = logic.eliminaPedidoId(idPedido);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * Funcion con la cual se realiza la busqueda de pedidos por medio de una
     * serie de filtros
     *
     * @param estado
     * @param fInicial
     * @param fFinal
     * @param idUsuario
     * @return
     */
    @WebMethod(operationName = "consultaPedidosXFiltros")
    @WebResult(name = "PedidoEntity")
    public List<PedidoEntity> consultaPedidoXFiltros(@XmlElement(required = true) @WebParam(name = "pedi_estado")String estado, 
            @XmlElement(required = true) @WebParam(name = "fechaIni")Date fInicial, 
            @XmlElement(required = true) @WebParam(name = "fechaFin")Date fFinal, 
            @XmlElement(required = true) @WebParam(name = "idTius")Integer idUsuario) {
        List<PedidoEntity> respuesta = null;
        try (PedidoLogica logica = new PedidoLogica()) {
            respuesta = logica.consultaPedidoXFiltros(estado, idUsuario, fInicial, fFinal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }
    
    /**
     * Funcion con la cual se actualiza el estado de un pedido
     *
     * @param idPedido
     * @param estado
     * @return
     */
    @WebMethod(operationName = "cambiaEstadoPedido")
    @WebResult(name = "respuesta")
    public boolean cambiaEstadoPedido(@XmlElement(required = true) @WebParam(name = "idPedido") Integer idPedido, 
            @XmlElement(required = true) @WebParam(name = "estado") String estado) {
        boolean rta = false;
        try (PedidoLogica objLogic = new PedidoLogica()) {
            rta = objLogic.actualizaEstadoPedido(idPedido, estado);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
    
    /**
     * Funcion con la cual se genera el pdf con el pedido como cotizacion
     *
     * @param idPedido
     * @return
     */
    @WebMethod(operationName = "generaPdfCotizacion")
    @WebResult(name = "imagen")
    public String generaPdfCotizacion(@XmlElement(required = true) @WebParam(name = "idPedido") Integer idPedido) {
        String rta = "";
        try (ReporteLogica objLogica = new ReporteLogica()) {
            rta = objLogica.generaJasperCotizacion(idPedido);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
    
    /**
     * Funcion con la cual busco cotizaciones generadas por un cliente
     *
     * @param idCliente
     * @return
     */
    @WebMethod(operationName = "buscaCotizacionPorCliente")
    @WebResult(name = "listaPedidos")
    public List<PedidoEntity> buscaCotizacionPorCliente(@XmlElement(required = true) @WebParam(name = "idCliente") Integer idCliente) {
        List<PedidoEntity> rta = null;
        try (PedidoLogica objLogic = new PedidoLogica()) {
            rta = objLogic.buscaCotizacionXCliente(idCliente);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
    
    /**
     * Funcion con la cual busco las remisiones que se encuentran solicitadas por un cliente
     *
     * @param idCliente
     * @return
     */
    @WebMethod(operationName = "obtenerRemisionesPorCliente")
    @WebResult(name = "listaPedidos")
    public List<PedidoEntity> obtenerRemisionesPorCliente(@XmlElement(required = true) @WebParam(name = "idCliente") Integer idCliente) {
        List<PedidoEntity> rta = null;
        try (PedidoLogica objLogic = new PedidoLogica()) {
            rta = objLogic.buscaRemisionXCliente(idCliente);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

}
