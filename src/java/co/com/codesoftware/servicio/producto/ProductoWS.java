/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.servicio.producto;

import co.com.codesoftware.logic.ProductoLogic;
import co.com.codesoftware.logica.inventario.ProductoLogica;
import co.com.codesoftware.logica.inventario.ProductosGenericosLogica;
import co.com.codesoftware.logica.inventario.ProductosParamLogica;
import co.com.codesoftware.logica.inventario.SolicitudLogica;
import co.com.codesoftware.logica.receta.RecetaLogica;
import co.com.codesoftware.persistence.entities.simple.ProductoSimpleEntity;
import co.com.codesoftware.persistence.entity.productos.FacturaCompraEntity;
import co.com.codesoftware.persistence.entity.productos.PagoFacCompraEntity;
import co.com.codesoftware.persistence.entity.productos.PagoFacturaCompraEntity;
import co.com.codesoftware.persistencia.entidad.admin.ProductoAporte;
import co.com.codesoftware.persistencia.entidad.generico.producto.ProductoGenericoEntity;
import co.com.codesoftware.persistencia.entidad.inventario.ExistenciaXSedeEntity;
import co.com.codesoftware.persistencia.entidad.inventario.PrecioProductoEntity;
import co.com.codesoftware.persistencia.entidad.inventario.ProductosParamEntity;
import co.com.codesoftware.persistencia.entidad.inventario.PromPonderaEntity;
import co.com.codesoftware.persistencia.entidad.inventario.SolicitudEntity;
import co.com.codesoftware.persistencia.entidad.inventario.SolicitudProdEntity;
import co.com.codesoftware.persistencia.entidad.receta.PrecioRecetaEntity;
import co.com.codesoftware.persistencia.utilities.RespuestaEntity;
import java.math.BigDecimal;
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
@WebService(serviceName = "ProductoWS")
public class ProductoWS {

    /**
     * Metodo el cual busca los productos y recetas parametrizados en el sistema
     * y que esten disponibles para su venta
     *
     * @param sede_sede
     * @return
     */
    @WebMethod(operationName = "obtenerProductosYRecetas")
    @WebResult(name = "ListGeneric")
    public List<ProductoGenericoEntity> obtenerProductosYRecetas(@XmlElement(required = true) @WebParam(name = "sede_sede") Integer sede_sede) {
        try {
            ProductosGenericosLogica logica = new ProductosGenericosLogica();
            return logica.obtieneProductosYRecetas(sede_sede);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * metodo que consulta un producto generico por codigo externo
     *
     * @param codigoExterno
     * @return
     */
    @WebMethod(operationName = "obtenerProdcutoGeneriXCodExt")
    @WebResult(name = "prodGenerico")
    public ProductoGenericoEntity obtenerProdcutoGeneriXCodExt(@XmlElement(required = true) @WebParam(name = "codigoExterno") String codigoExterno) {
        ProductoGenericoEntity producto = new ProductoGenericoEntity();
        try {
            ProductosGenericosLogica logica = new ProductosGenericosLogica();
            producto = logica.buscaProductoXCodigoExt(codigoExterno);
        } catch (Exception e) {
            e.printStackTrace();
            producto = null;
        }
        return producto;

    }

    @WebMethod(operationName = "obtenerProductosXSede")
    @WebResult(name = "ListGeneric")
    public List<ProductoGenericoEntity> obtenerProductosXSedeobtenerProductoXCodExt(@XmlElement(required = true) @WebParam(name = "sede_sede") Integer sede_sede) {
        try {
            ProductosGenericosLogica logica = new ProductosGenericosLogica();
            return logica.obtieneProductosXSede(sede_sede);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @WebMethod(operationName = "obtieneRecetasXSede")
    @WebResult(name = "ListaReceta")
    public List<PrecioRecetaEntity> consultaRecetas(@WebParam(name = "idSede") Integer idSede) {
        List<PrecioRecetaEntity> rta = null;
        try (RecetaLogica logica = new RecetaLogica();) {
            rta = logica.getRecetas(idSede);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion con la cual consulto el promedio ponderado de un producto y lo
     * comparo con el precio que quieren darle
     *
     * @param idProducto
     * @param precio
     * @return
     */
    @WebMethod(operationName = "consultaPromPonderado")
    public String consultaPromPonderado(@XmlElement(required = true) @WebParam(name = "dska_dska") Integer idProducto, @XmlElement(required = true) @WebParam(name = "precio") BigDecimal precio) {
        String rta = "";
        try {
            ProductoLogica objLogica = new ProductoLogica();
            rta = objLogica.consultaPromPonderado(idProducto, precio);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion con la cual busco el promedio ponderado y las existencias en toda
     * la compañia
     *
     * @param idDska
     * @return
     */
    @WebMethod(operationName = "obtenerPromedioPonderadoProducto")
    public PromPonderaEntity obtenerPromedioPonderadoProducto(@XmlElement(required = true) @WebParam(name = "idDska") Integer idDska) {
        PromPonderaEntity rta = null;
        try (ProductoLogica objLogica = new ProductoLogica()) {
            rta = objLogica.buscaPromedioPondProd(idDska);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion con la cual obtengo las existencias que hay en cada sede de cada
     * producto
     *
     * @param idDska
     * @return
     */
    @WebMethod(operationName = "obtenerExistenciasProducto")
    public List<ExistenciaXSedeEntity> obtenerExistenciasProducto(@XmlElement(required = true) @WebParam(name = "idDska") Integer idDska) {
        List<ExistenciaXSedeEntity> rta = null;
        try (ProductoLogica objLogica = new ProductoLogica()) {
            rta = objLogica.buscoExistenciaProd(idDska);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Metodo el cual busca los productos y recetas parametrizados en el sistema
     *
     * @param sede_sede
     * @param criterio
     * @return
     */
    @WebMethod(operationName = "obtenerProductosYRecetasXCriterio")
    @WebResult(name = "ListGeneric")
    public List<ProductoGenericoEntity> obtenerProductosYRecetasXCriterio(@XmlElement(required = true) @WebParam(name = "sede_sede") Integer sede_sede,
            @XmlElement(required = true) @WebParam(name = "criterio") String criterio) {
        try {
            ProductosGenericosLogica logic = new ProductosGenericosLogica();
            return logic.buscaProductosRecetasXCriterio(sede_sede, criterio);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * metodo que consulta los productos por sede que se pueden vender
     *
     * @param sede
     * @return
     */
    @WebMethod(operationName = "obtenerProductoXSede")
    @WebResult(name = "PrecioProducto")
    public List<PrecioProductoEntity> obtenerProductoXSede(@XmlElement(required = true) @WebParam(name = "sede_sede") Integer sede) {
        List<PrecioProductoEntity> resultado = null;
        try (ProductoLogica logica = new ProductoLogica()) {
            resultado = logica.obtieneProductosConPrecioXSede(sede);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    /**
     * Metodo con el cual se obtiene un producto en especifico basandose en su
     * codigo unico y en la sede.
     *
     * @param dska_cod
     * @param sede_sede
     * @return
     */
    @WebMethod
    @WebResult(name = "ProductoTable", partName = "ProductoTable")
    public PrecioProductoEntity obtenerProductoXCodigo(@WebParam(name = "dska_cod") String dska_cod,
            @WebParam(name = "sede_sede") Integer sede_sede) {
        try (ProductoLogica logic = new ProductoLogica()) {
            return logic.buscoProductoEntityXCodigo(dska_cod, sede_sede);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @WebMethod(operationName = "obtenerRecetaXCodigo")
    @WebResult(name = "recetaEntity")
    public PrecioRecetaEntity obtenerRecetaXCodigo(@WebParam(name = "codigo") String codigo, @WebParam(name = "sede") Integer sede) {
        PrecioRecetaEntity rta = new PrecioRecetaEntity();
        try (RecetaLogica logica = new RecetaLogica()) {
            rta = logica.getRecetaXCodigo(codigo, sede);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion la cual busca un producto por medio de su codigo externo
     *
     * @param codExt
     * @param sede_sede
     * @return
     */
    @WebMethod
    @WebResult(name = "ProductoTable", partName = "ProductoTable")
    public PrecioProductoEntity obtenerProductoXCodExt(@WebParam(name = "codExt") String codExt,
            @WebParam(name = "sede_sede") Integer sede_sede) {
        try (ProductoLogica logic = new ProductoLogica()) {
            return logic.buscoProductoEntityXCodExt(codExt, sede_sede);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Funcion la cual busca un producto por medio de su codigo externo
     *
     * @param codBarras
     * @param sede_sede
     * @return
     */
    @WebMethod
    @WebResult(name = "ProductoTable", partName = "ProductoTable")
    public PrecioProductoEntity obtenerProductoXCodBarras(@WebParam(name = "codBarras") String codBarras,
            @WebParam(name = "sede_sede") Integer sede_sede) {
        try (ProductoLogica logic = new ProductoLogica()) {
            return logic.buscoProductoEntityXCodBarras(codBarras, sede_sede);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Metodo que consulta cantidades por sede
     *
     * @param sede
     * @param idProducto
     * @return
     */
    @WebMethod(operationName = "obtenerCantidadesXSede")
    @WebResult(name = "ExistenciaXSedeEntity")
    public ExistenciaXSedeEntity obtenerCantidadesXSede(@XmlElement(required = true) @WebParam(name = "sede_sede") Integer sede,
            @XmlElement(required = true) @WebParam(name = "idDska") Integer idProducto) {
        ExistenciaXSedeEntity resultado = new ExistenciaXSedeEntity();
        try (ProductoLogica logic = new ProductoLogica()) {
            resultado = logic.consultaCantidad(sede, idProducto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    /**
     * metodo que consulta las existencias en todas las sedes
     *
     * @param idProducto
     * @return
     */
    @WebMethod(operationName = "obtenerCantidadesTotales")
    @WebResult(name = "ExistenciaXSedeEntity")
    public List<ExistenciaXSedeEntity> obtenerCantidadesTotales(
            @XmlElement(required = true) @WebParam(name = "idDska") Integer idProducto) {
        List<ExistenciaXSedeEntity> resultado = new ArrayList<ExistenciaXSedeEntity>();
        try (ProductoLogica logic = new ProductoLogica()) {
            resultado = logic.consultaCantidades(idProducto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    /**
     * Funcion con la cual obtengo todas las cantidades que tiene un producto
     * dentro del sistema
     *
     * @param idProducto
     * @return
     */
    @WebMethod(operationName = "obtenerCantidadesXProducto")
    @WebResult(name = "ExistenciaXSedeEntity")
    public List<ExistenciaXSedeEntity> obtenerCantidadesXProducto(@XmlElement(required = true) @WebParam(name = "idDska") Integer idProducto) {
        List<ExistenciaXSedeEntity> resultado = null;
        try (ProductoLogica logic = new ProductoLogica()) {
            resultado = logic.consultaCantidades(idProducto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    /**
     * metodo que inserta una solicitud
     *
     * @param solicitud
     * @return
     */
    @WebMethod(operationName = "insertaSolicitud")
    @WebResult(name = "RespuestaEntity")
    public RespuestaEntity insertaSolicitud(@WebParam(name = "solicitud") SolicitudEntity solicitud) {
        RespuestaEntity respuesta = new RespuestaEntity();
        try (SolicitudLogica logica = new SolicitudLogica()) {
            respuesta = logica.insertaSolicitud(solicitud);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * metodo que consulta un prodcuto por codigo externo
     *
     * @param codigoExterno
     * @return
     */
    @WebMethod(operationName = "consultaProdXcodExterno")
    public ProductoSimpleEntity consultaProdXcodExterno(@WebParam(name = "codExterno") String codigoExterno) {
        ProductoSimpleEntity rta = new ProductoSimpleEntity();
        try (SolicitudLogica logica = new SolicitudLogica()) {
            rta = logica.consultaProductoXCodExterno(codigoExterno);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * metodo que consulta las cantidades por sede
     *
     * @param sede
     * @return
     */
    @WebMethod(operationName = "obtenerCantidadesTotalesXSede")
    @WebResult(name = "ExistenciaXSedeEntity")
    public List<ExistenciaXSedeEntity> obtenerCantidadesTotalesXSede(@XmlElement(required = true) @WebParam(name = "idSede") Integer sede) {
        List<ExistenciaXSedeEntity> resultado = null;
        try (ProductoLogica logic = new ProductoLogica()) {
            resultado = logic.consultaCantidadesXSede(sede);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    /**
     * metodo que inserta los productos a una solicitud
     *
     * @param productos
     * @return
     */
    @WebMethod(operationName = "insertaProductosSolicitud")
    @WebResult(name = "respuestaEntity")
    public RespuestaEntity insertaProductosSolicitud(@WebParam(name = "productosSolicitud") List<SolicitudProdEntity> productos) {
        RespuestaEntity rta = new RespuestaEntity();
        try {
            SolicitudLogica logica = new SolicitudLogica();
            rta = logica.insertaProductosSolicitud(productos);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * metodo que consulta las solicitudes mediante los filtros seleccionados
     *
     * @param fechaInicial
     * @param fechaFinal
     * @param sede
     * @param usuario
     * @param estado
     * @return
     */
    @WebMethod(operationName = "obtenerSolicitudesFiltros")
    @WebResult(name = "listaSolicitudes")
    public List<SolicitudEntity> obtenerSolicitudesFiltros(@WebParam(name = "fechaInicial") Date fechaInicial, @WebParam(name = "fechaFinal") Date fechaFinal, @WebParam(name = "sede") Integer sede, @WebParam(name = "usuario") Integer usuario, @WebParam(name = "estado") String estado) {
        List<SolicitudEntity> respuesta = new ArrayList<>();
        try (SolicitudLogica logica = new SolicitudLogica()) {
            respuesta = logica.consultaSolicitudesXFiltro(fechaInicial, fechaFinal, estado, usuario, sede);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * metodo el cual obtiene los productos por solicitud
     *
     * @param idSolicitud
     * @return
     */
    @WebMethod(operationName = "obtenerProductosXSolicitud")
    @WebResult(name = "listaProductos")
    public List<SolicitudProdEntity> obtenerProductosXSolicitud(@WebParam(name = "idSolicitud") Integer idSolicitud) {
        List<SolicitudProdEntity> respuesta = new ArrayList<>();
        try (SolicitudLogica logica = new SolicitudLogica();) {
            respuesta = logica.consultaProductosXSoliciud(idSolicitud);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * metodo que consulta la solicitud por filtros
     *
     * @param fecha
     * @param sede
     * @param solicitud
     * @param usuario
     * @return
     */
    @WebMethod(operationName = "obtenerSolicitudXfiltro")
    @WebResult(name = "solicitud")
    public SolicitudEntity obtenerSolicitudXfiltro(@WebParam(name = "fecha") Date fecha, @WebParam(name = "sede") Integer sede, @WebParam(name = "usuario") Integer usuario) {
        SolicitudEntity solicitudEntity = new SolicitudEntity();
        try (SolicitudLogica logica = new SolicitudLogica()) {
            solicitudEntity = logica.consultaSolicitudXFecha(fecha, sede, usuario);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return solicitudEntity;
    }

    /**
     * metodo que consulta una solicitud por id
     *
     * @param id
     * @return
     */
    @WebMethod(operationName = "obtenerSolicitudXId")
    @WebResult(name = "solicitud")
    public SolicitudEntity obtenerSolicitudXId(@WebParam(name = "id") Integer id) {
        SolicitudEntity solicitudEntity = new SolicitudEntity();
        try (SolicitudLogica logica = new SolicitudLogica()) {
            solicitudEntity = logica.consultaSolicitudXId(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return solicitudEntity;
    }

    /**
     * metodo que actualiza los productos de una solicitud
     *
     * @param idUsuario
     * @param productos
     * @return
     */
    @WebMethod(operationName = "actualizaSolicitud")
    @WebResult(name = "RespuestaEntity")
    public RespuestaEntity actualizaSolicitud(@WebParam(name = "idUsuario") Integer idUsuario, @WebParam(name = "productosSolicitud") List<SolicitudProdEntity> productos) {
        RespuestaEntity respuesta = new RespuestaEntity();
        try {
            SolicitudLogica logica = new SolicitudLogica();
            boolean rta = logica.actualizaSolicitud(productos.get(0).getSolicitud());
            if (rta) {
                logica.borraProductosSolicitud(productos.get(0).getSolicitud().getId());
                respuesta = logica.actualizaProductosSolicitud(productos, idUsuario);
            } else {
                respuesta.setCodigoRespuesta(0);
                respuesta.setDescripcionRespuesta("Error al enviar productos de la solicitud");
                respuesta.setMensajeRespuesta("Error al enviar productos de la solicitud");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * metodo que consulta los pagos de una facura de compra
     *
     * @param idFactura
     * @return
     */
    @WebMethod(operationName = "consultaPagosFactCompra")
    public List<PagoFacturaCompraEntity> consultaPagosFactCompra(@WebParam(name = "idFactura") Integer idFactura) {
        List<PagoFacturaCompraEntity> lista = new ArrayList<>();
        try (ProductoLogic logica = new ProductoLogic()) {
            lista = logica.consultaPagosFacturaCompra(idFactura);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    /**
     * metodo en el cual se consulta una factura de compra
     *
     * @param id
     * @return
     */
    @WebMethod(operationName = "consultaFactCompra")
    @WebResult(name = "facturaCompraEntity")
    public FacturaCompraEntity consultaFactCompra(@WebParam(name = "id") Integer id) {
        FacturaCompraEntity respuesta = new FacturaCompraEntity();
        try (ProductoLogic logic = new ProductoLogic()) {
            respuesta = logic.consultaFacturaCompra(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * metodo que consulta los pagos realizados cuando se registra la factura de
     * compra
     *
     * @param id
     * @return
     */
    @WebMethod(operationName = "consultaPagoFactura")
    @WebResult(name = "PagoFacturaCompra")
    public List<PagoFacCompraEntity> consultaPagoFactura(@WebParam(name = "id") Integer id) {
        List<PagoFacCompraEntity> rta = new ArrayList<PagoFacCompraEntity>();
        try (ProductoLogic logica = new ProductoLogic()) {
            rta = logica.consultaPagoAnterior(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * metodo que consulta los pagos realizados
     *
     * @param id
     * @return
     */
    @WebMethod(operationName = "consultaPagoFactReal")
    @WebResult(name = "PagoFacturaCompra")
    public List<PagoFacturaCompraEntity> consultaPagoFactReal(@WebParam(name = "id") Integer id) {
        List<PagoFacturaCompraEntity> rta = new ArrayList<>();
        try (ProductoLogic logica = new ProductoLogic()) {
            rta = logica.consultaPagosFacturaCompra(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * *
     * metodo que llama al procedimiento de insercion de pagos
     *
     * @param pago
     * @return
     */
    @WebMethod(operationName = "insertaPagoFactCompra")
    public String insertaPagoFactCompra(@WebParam(name = "pago") PagoFacturaCompraEntity pago) {
        String respuesta = "";
        try (ProductoLogic logica = new ProductoLogic()) {
            respuesta = logica.insertaPago(pago);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * Funcion con la cual inserto un producto a un aporte
     *
     * @param idAporte
     * @param productos
     * @param idTius
     * @return
     */
    @WebMethod(operationName = "insertarProductoAporte")
    public String insertarProductoAporte(@WebParam(name = "idAporte") Integer idAporte,
            @WebParam(name = "productos") List<ProductoAporte> productos,
            @WebParam(name = "idTius") Integer idTius) {
        String rta = "";
        try (ProductoLogica objLogica = new ProductoLogica()) {
            rta = objLogica.insertProdAporte(idAporte, productos, idTius);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion con la cual elimino todos los productos que tiene relacionado un
     * aporte
     *
     * @param idAporte
     * @return
     */
    @WebMethod(operationName = "eliminaProdAporte")
    public String eliminaProdAporte(@WebParam(name = "idAporte") Integer idAporte) {
        String rta = "";
        try (ProductoLogica objLogica = new ProductoLogica()) {
            rta = objLogica.borrarProductosAporte(idAporte);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion con la cual genero el proceso de aporte
     *
     * @param idAporte
     * @param idAuxCon
     * @param idTius
     * @return
     */
    @WebMethod(operationName = "generaProcesoAporte")
    public String generaProcesoAporte(@WebParam(name = "idAporte") Integer idAporte,
            @WebParam(name = "idAuxCon") Integer idAuxCon,
            @WebParam(name = "idTius") Integer idTius) {
        String rta = "";
        try {
            ProductoLogica objLogica = new ProductoLogica();
            rta = objLogica.ejecutarProcesoAporte(idAporte, idAuxCon, idTius);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * metodo que inserta un producto parametrizado
     *
     * @param entidad
     * @return
     */
    @WebMethod(operationName = "insertarProdParametrizado")
    public boolean insertarProdParametrizado(@WebParam(name = "productoParametrizado") ProductosParamEntity entidad) {
        boolean rta = false;
        try (ProductosParamLogica logica = new ProductosParamLogica()) {
            rta = logica.insertarProducto(entidad);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * metodo que inserta un producto parametrizado
     *
     * @param entidad
     * @return
     */
    @WebMethod(operationName = "actualizaProdParametrizado")
    public boolean actualizaProdParametrizado(@WebParam(name = "productoParametrizado") ProductosParamEntity entidad) {
        boolean rta = false;
        try (ProductosParamLogica logica = new ProductosParamLogica()) {
            rta = logica.actualizarProducto(entidad);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
    
    /**
     * metodo en el cual cosulta un producto parametrizado por id
     * @param id
     * @return 
     */
    @WebMethod(operationName = "obtenerProdParametrizado")
    public ProductosParamEntity obtenerProdParametrizado(@WebParam(name = "idProd")Integer id){
        ProductosParamEntity rta = new ProductosParamEntity();
        try (ProductosParamLogica logica = new ProductosParamLogica()){
            rta = logica.consultaUnicoProd(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
    
    /**
     * metodo que cosnsulta todos los productos parametrizados
     * @return 
     */
    @WebMethod(operationName = "obtenerProductosParametrizado")
    public List<ProductosParamEntity> obtenerProductosParametrizado(){
         List<ProductosParamEntity> rta = new ArrayList<>();
        try (ProductosParamLogica logica = new ProductosParamLogica()){
            rta = logica.consultaLista();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
    
    /**
     * metodo que verifica si el producto esta parametrizado en los productos no inventareables
     * @param idProducto
     * @return 
     */
   @WebMethod(operationName = "verificaProductoParametrizado")
   public String verificaProductoParametrizado(@WebParam(name = "idProducto") Integer idProducto){
       String rta = "";
       try (ProductosParamLogica logica = new ProductosParamLogica()){
           if(logica.verificaProductoParam(idProducto)>0){
               rta = "Ok";
           }else{
               rta = "Error, Producto no se encuentra parametrizado en los productos no inventariables";
           }
                   
       } catch (Exception e) {
           e.printStackTrace();
       }
       return rta;
   }
}
