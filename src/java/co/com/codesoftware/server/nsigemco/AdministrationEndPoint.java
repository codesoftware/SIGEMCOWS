/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.server.nsigemco;

import co.com.codesoftware.logic.CategoriaLogic;
import co.com.codesoftware.logic.MarcaLogic;
import co.com.codesoftware.logic.ProductoLogic;
import co.com.codesoftware.logic.ProductoTmpLogic;
import co.com.codesoftware.logic.ProveedoresLogic;
import co.com.codesoftware.logic.ReferenciaLogic;
import co.com.codesoftware.logic.RetencionLogic;
import co.com.codesoftware.logic.SubCuentaLogic;
import co.com.codesoftware.logic.UsuarioLogic;
import co.com.codesoftware.logic.productos.FacturaCompraLogic;
import co.com.codesoftware.logic.productos.PedidosLogic;
import co.com.codesoftware.logica.admin.AporteSocioLogica;
import co.com.codesoftware.logica.admin.SocioLogica;
import co.com.codesoftware.persistence.entities.MapaEntity;
import co.com.codesoftware.persistence.entities.PucEntity;
import co.com.codesoftware.persistence.entity.administracion.ProveedoresEntity;
import co.com.codesoftware.persistence.entity.administracion.RespuestaEntity;
import co.com.codesoftware.persistence.entity.administracion.ReteFuenteEntity;
import co.com.codesoftware.persistence.entity.productos.FacturaCompraEntity;
import co.com.codesoftware.persistence.entity.productos.PagoFacCompraEntity;
import co.com.codesoftware.persistence.entity.productos.ProductoFacCompraEntity;
import co.com.codesoftware.persistence.entity.productos.ProductoTmpEntity;
import co.com.codesoftware.persistencia.entidad.admin.AporteSocioEntity;
import co.com.codesoftware.persistencia.entidad.admin.PerfilEntity;
import co.com.codesoftware.persistencia.entidad.admin.ProductoAporte;
import co.com.codesoftware.persistencia.entidad.admin.SocioEntity;
import co.com.codesoftware.persistencia.entidad.inventario.CategoriaEntity;
import co.com.codesoftware.persistencia.entidad.inventario.MarcaEntity;
import co.com.codesoftware.persistencia.entidad.inventario.ProductoEntity;
import co.com.codesoftware.persistencia.entidad.inventario.ReferenciaEntity;
import co.com.codesoftware.persistencia.entidad.contabilidad.SubCuentaEntity;
import co.com.codesoftware.persistencia.entidad.pedido.PedidoEntity;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;

/**
 *
 * @author ACER
 */
@WebService(serviceName = "AdministrationEndPoint")
public class AdministrationEndPoint {

    /**
     * Metodo el cual consulta los proveedores activos en el sistema
     *
     * @return
     */
    @WebMethod(operationName = "obtieneProveedores")
    @WebResult(name = "listaProveedores")
    public List<ProveedoresEntity> obtieneProveedores() {
        try (ProveedoresLogic logic = new ProveedoresLogic()) {
            return logic.consultaProveedores();
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    /**
     * metodo que permite insertar un proveedor
     *
     * @param entidad
     * @return
     */
    @WebMethod(operationName = "insertaProveedor")
    @WebResult(name = "RespuestaEntity")
    public RespuestaEntity insertarProveedor(ProveedoresEntity entidad) {
        RespuestaEntity entidadRespuesta = new RespuestaEntity();
        try (ProveedoresLogic logic = new ProveedoresLogic()) {
            entidadRespuesta = logic.insertaProveedores(entidad);
        } catch (Exception e) {
            entidadRespuesta.setCodigoRespuesta(0);
            entidadRespuesta.setDescripcionRespuesta(e.toString());
            entidadRespuesta.setMensajeRespuesta("ERROR");
            e.printStackTrace();
        }
        return entidadRespuesta;

    }

    /**
     * metodo que actualiza un proveedor
     *
     * @param proveedor
     * @return
     */
    @WebMethod(operationName = "actualizarProveedor")
    @WebResult(name = "RespuestaEntity")
    public RespuestaEntity actualizarProveedor(@WebParam(name = "proveedor") ProveedoresEntity proveedor) {
        RespuestaEntity respuesta = new RespuestaEntity();
        try (ProveedoresLogic logic = new ProveedoresLogic()) {
            respuesta = logic.actualizaProveedor(proveedor);
        } catch (Exception e) {
            respuesta.setCodigoRespuesta(0);
            respuesta.setDescripcionRespuesta(e.toString());
            respuesta.setMensajeRespuesta(e.toString());
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * Funcion que consulta las marcas activas en el sistema
     *
     * @return
     */
    @WebMethod(operationName = "obtieneMarcas")
    @WebResult(name = "listaMarcas")
    public List<MarcaEntity> obtieneMarcas() {
        try (MarcaLogic logic = new MarcaLogic()) {
            return logic.consultaMarcas();
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    /**
     * Funcion que consulta las marcas que tienen una serie de productos que
     * tienen determinadas categorias
     *
     * @param idCate
     * @return
     */
    @WebMethod(operationName = "obtieneMarcasXCate")
    @WebResult(name = "listaMarcas")
    public List<MarcaEntity> obtieneMarcasXCate(@WebParam(name = "idCate") Integer idCate) {
        try (MarcaLogic logic = new MarcaLogic()) {
            return logic.consultaMarcasRelCat(idCate);
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    /**
     * Metodo que permite insertar una marca
     *
     * @param entidad
     * @return
     */
    @WebMethod(operationName = "insertaMarca")
    @WebResult(name = "RespuestaEntity")
    public RespuestaEntity insertarMarca(MarcaEntity entidad) {
        RespuestaEntity entidadRespuesta = new RespuestaEntity();
        try (MarcaLogic logic = new MarcaLogic()) {
            entidadRespuesta = logic.insertaMarcas(entidad);
        } catch (Exception e) {
            entidadRespuesta.setCodigoRespuesta(0);
            entidadRespuesta.setDescripcionRespuesta(e.toString());
            entidadRespuesta.setMensajeRespuesta("ERROR");
            e.printStackTrace();
        }
        return entidadRespuesta;

    }

    /**
     * Funcion que consulta las referencias activas en el sistema
     *
     * @return
     */
    @WebMethod(operationName = "obtieneReferencias")
    @WebResult(name = "listaReferencias")
    public List<ReferenciaEntity> obtieneReferencias() {
        try (ReferenciaLogic logic = new ReferenciaLogic()) {
            return logic.consultaReferencias();
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    /**
     * Metodo que permite insertar una referencia
     *
     * @param entidad
     * @return
     */
    @WebMethod(operationName = "insertaReferencia")
    @WebResult(name = "RespuestaEntity")
    public RespuestaEntity insertarReferencia(ReferenciaEntity entidad) {
        RespuestaEntity entidadRespuesta = new RespuestaEntity();
        try (ReferenciaLogic logic = new ReferenciaLogic()) {
            entidadRespuesta = logic.insertaReferencias(entidad);
        } catch (Exception e) {
            entidadRespuesta.setCodigoRespuesta(0);
            entidadRespuesta.setDescripcionRespuesta(e.toString());
            entidadRespuesta.setMensajeRespuesta("ERROR");
            e.printStackTrace();
        }
        return entidadRespuesta;

    }

    /**
     * Funcion que consulta las categorias activas en el sistema
     *
     * @return
     */
    @WebMethod(operationName = "obtieneCategorias")
    @WebResult(name = "listaCategorias")
    public List<CategoriaEntity> obtieneCategorias() {
        try (CategoriaLogic logic = new CategoriaLogic()) {
            return logic.consultaCategorias();
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    /**
     * Metodo el cual retorna la consulta de los productos de acuerdo a los
     * filtros
     *
     * @param codigo
     * @param nombre
     * @param descripcion
     * @return
     */
    @WebMethod(operationName = "obtieneProductos")
    public List<ProductoEntity> obtieneProductos(String codigo, String nombre, String descripcion) {

        try (ProductoLogic logic = new ProductoLogic();) {
            return logic.consultaProductos(codigo, nombre, descripcion);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Metodo que consulta los productos por categoria, subcategoria y marca
     *
     * @param categoria
     * @param subcategoria
     * @param marca
     * @return
     */
    @WebMethod(operationName = "obtieneProductosCategoria")
    public List<ProductoEntity> obtieneProductosCategoria(Integer categoria, String subcategoria, String marca) {
        try (ProductoLogic logic = new ProductoLogic()) {
            return logic.consultaProductosCatSubMarc(categoria, subcategoria, marca);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Funcion la cual busca el producto por su codigo externo directamente
     *
     * @return
     */
    @WebMethod(operationName = "consultaProductoCodigoExterno")
    public List<ProductoEntity> consultaProductoCodigoExterno(String codExterno) {
        try (ProductoLogic logic = new ProductoLogic()) {
            return logic.consultaProductosXCodigoExt(codExterno);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Metodo en el cual puedo consultar subcuentas ya sea por nombre o por
     * codigo filtradas
     *
     * @param filtro
     * @return
     */
    @WebMethod(operationName = "obtieneSubCuentasFiltro")
    public List<SubCuentaEntity> obtieneSubCuentasFiltro(String filtro) {
        try (SubCuentaLogic logic = new SubCuentaLogic()) {
            return logic.consultaSubCuentaFiltro(filtro);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * metodo que consulta las subcuentas por filtros
     *
     * @param campo
     * @param datos
     * @return
     */
    @WebMethod(operationName = "obtieneSubCuentasFiltros")
    public List<SubCuentaEntity> obtieneSubCuentasFiltros(@WebParam(name = "datos") ArrayList<PucEntity> datos) {
        List<SubCuentaEntity> respuesta = new ArrayList<SubCuentaEntity>();
        try (SubCuentaLogic logic = new SubCuentaLogic()) {
            respuesta = logic.consultaSubCuentaFiltros(datos);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * Funcion que registra un producto a la base de datos
     *
     * @param producto
     * @return
     */
    @WebMethod(operationName = "insertaRegistroProducto")
    @WebResult(name = "RespuestaEntity")
    public RespuestaEntity insertaRegistroProducto(ProductoEntity producto) {
        RespuestaEntity respuesta = new RespuestaEntity();
        try (ProductoLogic logic = new ProductoLogic()) {
            respuesta = logic.registraProductoFunc(producto);
        } catch (Exception e) {
            respuesta.setCodigoRespuesta(0);
            respuesta.setDescripcionRespuesta(e.toString());
            respuesta.setMensajeRespuesta(e.getMessage());
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * Metodo con el cual realizo una actualizacion a una referencia
     *
     * @param referencia
     * @return
     */
    @WebMethod(operationName = "actualizaReferencia")
    @WebResult(name = "RespuestaEntity")
    public RespuestaEntity actualizaReferencia(ReferenciaEntity referencia) {
        RespuestaEntity respuesta = new RespuestaEntity();
        try (ReferenciaLogic logic = new ReferenciaLogic()) {
            String rta = logic.actualizaReferencia(referencia);
            respuesta.setCodigoRespuesta(1);
            respuesta.setDescripcionRespuesta("Ok");
            respuesta.setMensajeRespuesta("Referencia actualizada correctamente");
        } catch (Exception e) {
            respuesta.setCodigoRespuesta(0);
            respuesta.setDescripcionRespuesta(e.toString());
            respuesta.setMensajeRespuesta(e.getMessage());
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * Funcion con la cual obtengo todas las referencias que estan atadas a una
     * subcategoria
     *
     * @param idCategoria
     * @return
     */
    @WebMethod(operationName = "obtieneReferenciasXIdCate")
    @WebResult(name = "obtieneReferencias")
    public List<ReferenciaEntity> obtieneReferenciasXIdCate(Integer idCategoria) {
        List<ReferenciaEntity> rta = null;
        try {
            ReferenciaLogic logic = new ReferenciaLogic();
            rta = logic.consultaReferenciaXCategoria(idCategoria);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion que inserta en la tabla temporal los datos subidos en excel
     *
     * @param listaProducto
     * @return
     */
    @WebMethod(operationName = "insertarProductoTemporal")
    public RespuestaEntity insertarProductoTemporal(@WebParam(name = "listaProductos") List<ProductoTmpEntity> listaProducto) {
        RespuestaEntity respuesta = new RespuestaEntity();
        try (ProductoTmpLogic logic = new ProductoTmpLogic()) {
            respuesta = logic.insertaProductoTmp(listaProducto);
        } catch (Exception e) {
            e.printStackTrace();
            respuesta.setCodigoRespuesta(0);
            respuesta.setDescripcionRespuesta(e.getMessage());
            respuesta.setMensajeRespuesta("ERROR" + e.getMessage());
        }
        return respuesta;
    }

    /**
     * Funcion que consulta los datos insertados en la tabla temporal
     *
     * @return
     */
    @WebMethod(operationName = "consultaProductosTemporal")
    public List<ProductoTmpEntity> consultaProductosTemporal() {
        List<ProductoTmpEntity> respuesta = null;
        try (ProductoTmpLogic logic = new ProductoTmpLogic()) {
            respuesta = logic.consultaTablaTemporal();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    @WebMethod(operationName = "insertarProductoExcel")
    public RespuestaEntity insertarProductoExcel() {
        RespuestaEntity respuesta = new RespuestaEntity();
        try (ProductoLogic logic = new ProductoLogic()) {
            respuesta = logic.registraDatosExcelFunc();
        } catch (Exception e) {
            e.printStackTrace();
            respuesta.setCodigoRespuesta(0);
            respuesta.setDescripcionRespuesta(e.getMessage());
            respuesta.setMensajeRespuesta("ERROR" + e.getMessage());
        }
        return respuesta;

    }

    /**
     * Funcion con la cual registro porductos en el excel
     *
     * @return
     */
    @WebMethod(operationName = "registroProductosExcel")
    public RespuestaEntity registroProductosExcel() {
        RespuestaEntity respuesta = new RespuestaEntity();
        try (ProductoLogic logic = new ProductoLogic()) {
            respuesta = logic.registroProductos();
        } catch (Exception e) {
            e.printStackTrace();
            respuesta.setCodigoRespuesta(0);
            respuesta.setDescripcionRespuesta(e.getMessage());
            respuesta.setMensajeRespuesta("ERROR" + e.getMessage());
        }
        return respuesta;

    }

    /**
     * Metodo con el cual se realiza el login de un usuario
     *
     * @param usuario
     * @param contrasena
     * @return
     */
    @WebMethod(operationName = "loginUsuario")
    public List loginUsuario(@WebParam(name = "usuario") String usuario, @WebParam(name = "contrasena") String contrasena) {
        List rta = null;
        try (UsuarioLogic logic = new UsuarioLogic()) {
            rta = logic.loginUsuario(usuario, contrasena);
        } catch (Exception e) {
        }
        return rta;
    }

    /**
     * metodo que inserta un pedido al sistema
     *
     * @param parametros
     * @return
     */
    @WebMethod(operationName = "insertaPedidosProducto")
    public RespuestaEntity insertaPedidosProducto(PedidoEntity parametros) {
        RespuestaEntity respuesta = new RespuestaEntity();
        try (PedidosLogic logic = new PedidosLogic()) {
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
     * Funcion con la cual cambio el precio del producto
     *
     * @param usuario
     * @param sede
     * @param dska
     * @param precio
     * @return
     */
    @WebMethod(operationName = "cambioPrecioProducto")
    public RespuestaEntity cambioPrecioProducto(@WebParam(name = "tius_tius") Integer usuario, @WebParam(name = "sede_sede") Integer sede, @WebParam(name = "dska_dska") Integer dska, @WebParam(name = "precio") BigDecimal precio,
            @WebParam(name = "precioUni") BigDecimal precioUni, @WebParam(name = "precioDec") BigDecimal precioDec, @WebParam(name = "precioMil") BigDecimal precioMil, @WebParam(name = "precioEstatic") String precioEstatic) {
        RespuestaEntity rta = null;
        try (ProductoLogic objLogic = new ProductoLogic()) {
            rta = objLogic.cambioPrecioProducto(usuario, sede, dska, precio, precioUni, precioDec, precioMil, precioEstatic);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * metodo que consulta todas las subcuentas
     *
     * @return
     */
    @WebMethod(operationName = "consultaSubcuentas")
    public List<SubCuentaEntity> consultaSubCuentas() {
        List<SubCuentaEntity> respuesta = null;
        try (SubCuentaLogic logica = new SubCuentaLogic()) {
            respuesta = logica.consultaSubcuentas();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * metodo que inserta una factura de compra
     *
     * @param factura
     * @param pago
     * @param productos
     * @param ajustePeso
     * @return
     */
    @WebMethod(operationName = "insertaFacturaCompra")
    public RespuestaEntity insertaFacturaCompra(@WebParam(name = "factura") FacturaCompraEntity factura, @WebParam(name = "pagoFactura") List<PagoFacCompraEntity> pago, @WebParam(name = "productosFactura") List<ProductoFacCompraEntity> productos) {
        RespuestaEntity respuesta = new RespuestaEntity();
        try {
            FacturaCompraLogic logica = new FacturaCompraLogic();
            respuesta = logica.insertaFactura(factura, pago, productos);

        } catch (Exception e) {
//            respuesta.setCodigoRespuesta(0);
//            respuesta.setDescripcionRespuesta(e.getCause().toString());
//            respuesta.setMensajeRespuesta(e.toString());
            e.printStackTrace();

        }
        return respuesta;
    }

    /**
     * metodo qu consulta las tablas de retenciones por versión
     *
     * @param idVersion
     * @return
     */
    @WebMethod(operationName = "consultaTablasRetencion")
    public List<ReteFuenteEntity> consultaTablaReteFuente(@WebParam(name = "idVersion") Integer idVersion) {
        List<ReteFuenteEntity> respuesta = null;
        try (RetencionLogic logic = new RetencionLogic()) {
            respuesta = logic.consultaTablaRetencion(idVersion);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * Funcion con la cual actualiza los permisos de los perfiles
     *
     * @param entity
     * @return
     */
    @WebMethod(operationName = "actualizarPerfiles")
    public String actualizarPerfiles(PerfilEntity entity) {
        String rta = "";
        try (UsuarioLogic objLogic = new UsuarioLogic()) {
            rta = objLogic.actualizarPerfil(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * metodo que consulta los socios sin filro
     *
     * @return
     */
    @WebMethod(operationName = "obtenerSocios")
    public List<SocioEntity> obtenerSocios() {
        List<SocioEntity> respuesta = new ArrayList<>();
        try (SocioLogica logica = new SocioLogica()) {
            respuesta = logica.consultaSocios();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * metodo que consulta un socio por id
     *
     * @param idSocio
     * @return
     */
    @WebMethod(operationName = "obtenerSocio")
    public SocioEntity obtenerSocio(@WebParam(name = "idSocio") Integer idSocio) {
        SocioEntity respuesta = new SocioEntity();
        try (SocioLogica socio = new SocioLogica()) {
            respuesta = socio.consultaSocio(idSocio);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * metodo que actualiza un socio
     *
     * @param socio
     * @return
     */
    @WebMethod(operationName = "actualizaSocio")
    public String actualizaSocio(@WebParam(name = "socio") SocioEntity socio) {
        String respuesta = "";
        try (SocioLogica logica = new SocioLogica()) {
            respuesta = logica.actualizaSocio(socio);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * metodo con el cual se inserta un socio
     *
     * @param socio
     * @return
     */
    @WebMethod(operationName = "insertaSocio")
    public String insertaSocio(@WebParam(name = "socio") SocioEntity socio) {
        String respuesta = "";
        try (SocioLogica logica = new SocioLogica()) {
            respuesta = logica.insertarSocio(socio);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * Funcion con la cual inserto un aporte
     *
     * @param objEntity
     * @return
     */
    @WebMethod(operationName = "insertaAporte")
    public String insertaAporte(AporteSocioEntity objEntity) {
        String rta = "";
        try (AporteSocioLogica objLogica = new AporteSocioLogica()) {
            rta = objLogica.insertaAporte(objEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * funcion con la cual consulto un aporte por id
     *
     * @param id
     * @return
     */
    @WebMethod(operationName = "obtenerAporte")
    public AporteSocioEntity obtenerAporte(@WebParam(name = "id") Integer id) {
        AporteSocioEntity rta = new AporteSocioEntity();
        try (AporteSocioLogica logica = new AporteSocioLogica()) {
            rta = logica.consultaAporte(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * metodo con el cual obtengo todos los aportes sin filtros
     *
     * @return
     */
    @WebMethod(operationName = "obtenerAportes")
    public List<AporteSocioEntity> obtenerAportes() {
        List<AporteSocioEntity> rta = new ArrayList<>();
        try (AporteSocioLogica logica = new AporteSocioLogica()) {
            rta = logica.consultaAportes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * metodod que actualiza un
     *
     * @param entidad
     * @return
     */
    @WebMethod(operationName = "actualizarAportes")
    public String actualizarAportes(@WebParam(name = "Aporte") AporteSocioEntity entidad) {
        String rta;
        try (AporteSocioLogica logica = new AporteSocioLogica()) {
            rta = logica.actualizaAporte(entidad);
        } catch (Exception e) {
            rta = "Error " + e.toString();
        }
        return rta;
    }

    /**
     * Funcion con la cual obtengo los productos de un aporte
     *
     * @param idAporte
     * @return
     */
    @WebMethod(operationName = "obtenerPoductosAporte")
    public List<ProductoAporte> obtenerPoductosAporte(@WebParam(name = "idAporte") Integer idAporte) {
        List<ProductoAporte> rta = null;
        try(AporteSocioLogica objLogica = new AporteSocioLogica()) {
            rta = objLogica.consultaProductosAporte(idAporte);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

}
