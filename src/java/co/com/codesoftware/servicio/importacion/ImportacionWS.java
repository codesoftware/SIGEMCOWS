/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.servicio.importacion;

import co.com.codesoftware.logica.importacion.ImportacionLogica;
import co.com.codesoftware.logica.importacion.ProveedorIntLogica;
import co.com.codesoftware.persistencia.entidad.importacion.DetalleGastoEntity;
import co.com.codesoftware.persistencia.entidad.importacion.GastoImpoEntity;
import co.com.codesoftware.persistencia.entidad.importacion.ImportacionEntity;
import co.com.codesoftware.persistencia.entidad.importacion.ProductoImportacionEntity;
import co.com.codesoftware.persistencia.entidad.importacion.ProveedorInterEntity;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.ws.handler.MessageContext;

/**
 *
 * @author nicolas
 */
@WebService(serviceName = "ImportacionWS")
public class ImportacionWS {
    /**
     * Funcion con la cual obtengo una importacion por medio de su id
     * @param idImpo
     * @return 
     */
    @WebMethod(operationName = "obtenerImportacion")
    public ImportacionEntity obtenerImportacion(@WebParam(name = "idImpo")Integer idImpo){
        ImportacionEntity objEntity = null;
        try (ImportacionLogica objLogica = new ImportacionLogica()){
            objEntity = objLogica.obtenerImportacion(idImpo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objEntity;
    }
    /**
     * Metodo con el cual se inserta un proveedor internacional
     *
     * @param objEntity
     * @return
     */
    @WebMethod(operationName = "insertarProveedorInternacional")
    public String insertarProveedorInternacional(@WebParam(name = "provInter") ProveedorInterEntity objEntity) {
        String rta = "";
        try (ProveedorIntLogica objLogica = new ProveedorIntLogica()) {
            rta = objLogica.insertaProveedor(objEntity);
        } catch (Exception e) {
            e.printStackTrace();
            rta = "Error " + e;
        }
        return rta;
    }

    /**
     * Funcion con la cual realizo la obtencion de proveedores internacionales
     *
     * @return
     */
    @WebMethod(operationName = "obtenerProveedoresInter")
    public List<ProveedorInterEntity> obtenerProveedoresInter() {
        try (ProveedorIntLogica objLogica = new ProveedorIntLogica()) {
            return objLogica.consultaProveedoresInterna();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Funcion con la cual inserto una importacion en el sistema
     *
     * @param objEntity
     * @return
     */
    @WebMethod(operationName = "insertaImportacion")
    public String insertaImportacion(@WebParam(name = "importEntity") ImportacionEntity objEntity) {
        String rta = "Ok";
        try (ImportacionLogica objLogica = new ImportacionLogica()) {
            rta = objLogica.insertaImportacion(objEntity);
        } catch (Exception e) {
            e.printStackTrace();
            rta = "Error " + e;
        }
        return rta;
    }

    /**
     * metodo que se usa para consultar las importaciones dependiendo de la
     * fecha
     *
     * @param fechaInicial
     * @param fechaFinal
     * @return
     */
    @WebMethod(operationName = "obtenerImportaciones")
    public List<ImportacionEntity> obtenerImportaciones(@WebParam(name = "fechaInicial") Date fechaInicial, @WebParam(name = "fechaFinal") Date fechaFinal) {
        List<ImportacionEntity> respuesta = null;
        try (ImportacionLogica objLogica = new ImportacionLogica()) {
            respuesta = objLogica.consultaImportaciones(fechaInicial, fechaFinal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     *
     * @param idImpo
     * @param codExterno
     * @param cantidad
     * @param precio
     * @return
     */
    public String insertarProductoImportacion(@WebParam(name = "idImpo") Integer idImpo,
            @WebParam(name = "codExterno") String codExterno,
            @WebParam(name = "cantidad") Integer cantidad,
            @WebParam(name = "precio") BigDecimal precio) {
        String rta = "";
        try {
            ImportacionLogica objogica = new ImportacionLogica();
            rta = objogica.insertarProductoImportacion(idImpo, codExterno, cantidad, precio);
        } catch (Exception e) {
            e.printStackTrace();
            rta = "Error " + e;
        }
        return rta;
    }

    /**
     * Funcion con la cual consulto los productos que tiene una importacion
     *
     * @param idImpo
     * @return
     */
    public List<ProductoImportacionEntity> obtenerProductosImportacion(@WebParam(name = "idImpo") Integer idImpo) {
        List<ProductoImportacionEntity> rta = null;
        try (ImportacionLogica objLogica = new ImportacionLogica()) {
            rta = objLogica.obtenerProductosImportacion(idImpo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion con la cual le da valores a los productos que tiene la
     * importacion
     *
     * @param idImpo
     * @param trm
     * @param tazaProm
     * @return
     */
    public String insertaValorDolaresProd(@WebParam(name = "idImpo") Integer idImpo, @WebParam(name = "trm") BigDecimal trm, @WebParam(name = "tazaProm") BigDecimal tazaProm) {
        String rta = "";
        try {
            ImportacionLogica objLogica = new ImportacionLogica();
            rta = objLogica.insertarTazasCambio(idImpo, trm, tazaProm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion con la cual inserto un gasto a una importacion
     *
     * @param gasto
     * @return
     */
    public String insertaGastoImportacion(@WebParam(name = "gasto") GastoImpoEntity gasto) {
        String rta = "";
        try (ImportacionLogica objLogica = new ImportacionLogica()) {
            rta = objLogica.insertaGastoImportacion(gasto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion con la cual obtengo los gastos de una importacion
     *
     * @param idImpo
     * @returnobtenerProductosImportacion
     */
    public List<GastoImpoEntity> obtenerGastosImpo(@WebParam(name = "idImpo") Integer idImpo) {
        List<GastoImpoEntity> rta = null;
        try (ImportacionLogica objLogica = new ImportacionLogica()) {
            rta = objLogica.obtenerGastosImportacion(idImpo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion con la cual borro los productos que tiene asociada una
     * importacion(Todos)
     *
     * @return
     */
    public String borrarProductosImportacion(@WebParam(name = "idImpo") Integer idImpo) {
        String rta = "";
        try (ImportacionLogica objLogica = new ImportacionLogica()) {
            rta = objLogica.borrarProductosImportacion(idImpo);
        } catch (Exception e) {
            e.printStackTrace();
            rta = "Error " + e;
        }
        return rta;
    }

    /**
     * Funcion con la cual inserta un detalle del gasto
     *
     * @param detalle
     * @return
     */
    public String insertarDetalleGasto(@WebParam(name = "detalle") DetalleGastoEntity detalle) {
        String rta = "";
        try (ImportacionLogica objLogica = new ImportacionLogica()) {
            rta = objLogica.insertaDetalleGasto(detalle);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion con la cual obtengo el detalle de un gasto
     *
     * @param idGasto
     * @return
     */
    public List<DetalleGastoEntity> obtenerDetalleGasto(@WebParam(name = "idGasto") Integer idGasto) {
        List<DetalleGastoEntity> rta = null;
        try (ImportacionLogica objLogica = new ImportacionLogica()) {
            rta = objLogica.obtenerDetalleGasto(idGasto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion con la cual ejecuto el proceso de importacion dentro del sistema
     *
     * @param idtius
     * @param idImpo
     * @return
     */
    public String ejecutaProcesoImportacion(@WebParam(name = "idtius") Integer idtius,
            @WebParam(name = "idImpo") Integer idImpo,
            @WebParam(name = "idSede") Integer idSede) {
        String rta = "";
        try {
            ImportacionLogica objLogica = new ImportacionLogica();
            rta = objLogica.ejecutaProcesoImportacion(idtius, idImpo,idSede);
        } catch (Exception e) {
            e.printStackTrace();
            rta = "Error " + e;
        }
        return rta;
    }
    /**
     * Funcion con la cual puedo eliminar un detalle de un gasto
     * @param idImpo
     * @param idDet
     * @return 
     */
    public String eliminarDetalleGasto(@WebParam(name = "idDet")Integer idDet){
        String rta = "";
        try (ImportacionLogica objLogica = new ImportacionLogica()){
            rta = objLogica.eliminarDetalleGasto(idDet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
    /**
     * Funcion con la cual elimino un gasto con sus detalles
     * @param idGast
     * @return 
     */
    public String eliminarGastoImportacion(@WebParam(name = "idGast")Integer idGast){
        String rta = "";
        try(ImportacionLogica objLogica = new ImportacionLogica()) {
            rta = objLogica.eliminarGasto(idGast);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
            

}
