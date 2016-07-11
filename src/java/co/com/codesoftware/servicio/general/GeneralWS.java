/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.servicio.general;

import co.com.codesoftware.logic.EnviaCorreosLogic;
import co.com.codesoftware.logica.admin.ParametrosEmpresaLogic;
import co.com.codesoftware.logica.admin.ResolucionFactLogica;
import co.com.codesoftware.logica.admin.SedesLogica;
import co.com.codesoftware.logica.admin.UbicacionLogica;
import co.com.codesoftware.logica.admin.UsuarioLogica;
import co.com.codesoftware.logica.facturacion.RemisionLogica;
import co.com.codesoftware.logica.inventario.PagoRemisionLogica;
import co.com.codesoftware.logica.reportes.ReporteLogica;
import co.com.codesoftware.persistence.entities.MapaEntity;
import co.com.codesoftware.persistencia.entidad.admin.CiudadEntity;
import co.com.codesoftware.persistencia.entidad.admin.DepartamentoEntity;
import co.com.codesoftware.persistencia.entidad.admin.ParametrosEmpresaEntity;
import co.com.codesoftware.persistencia.entidad.admin.ResolucionFactEntity;
import co.com.codesoftware.persistencia.entidad.admin.SedeEntity;
import co.com.codesoftware.persistencia.entidad.admin.UsuarioEntity;
import co.com.codesoftware.persistencia.entidad.facturacion.DetProdRemision;
import co.com.codesoftware.persistencia.entidad.facturacion.RemisionEntity;
import co.com.codesoftware.persistencia.entidad.generico.facturacion.RelFacRemiGenEntity;
import co.com.codesoftware.persistencia.entidad.inventario.DetallePagoRemision;
import co.com.codesoftware.persistencia.entidad.inventario.PagoRemisionEntity;
import co.com.codesoftware.wrapperrequest.CorreoWrapperRequest;
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
@WebService(serviceName = "GeneralWS")
public class GeneralWS {

    /**
     * Metodo con el cual obtengo todos los parametros empresariales de la
     * compañia
     *
     * @return
     */
    @WebMethod(operationName = "obtenerParametrosEmpresa")
    @WebResult(name = "parametros")
    public List<ParametrosEmpresaEntity> obtenerParametrosEmpresa() {
        try (ParametrosEmpresaLogic objLogic = new ParametrosEmpresaLogic()) {
            return objLogic.obtienePrametrosEmpresa();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Metodo que consulta todas las sedes que hay en el sistema
     *
     * @return
     */
    @WebMethod(operationName = "obtenerSedes")
    public List<SedeEntity> obtenerSedes() {
        try (SedesLogica logic = new SedesLogica()) {
            return logic.sedeList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * Metodo con el cual se insertan las resoluciones de facturacion compañia
     *
     * @param objEntity
     * @return
     */
    @WebMethod(operationName = "insertarResolucion")
    @WebResult(name = "respuesta")
    public String insertarResolucion(@XmlElement(required = true) @WebParam(name = "objEntity") ResolucionFactEntity objEntity) {
        try (ResolucionFactLogica objLogica = new ResolucionFactLogica()) {
            return objLogica.insertaResolucion(objEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @WebMethod(operationName = "actualizaResolucion")
    @WebResult(name = "respuesta")
    public String actualizaResolucion(@XmlElement(required = true) @WebParam(name = "objEntity") ResolucionFactEntity objEntity) {
        try (ResolucionFactLogica objLogica = new ResolucionFactLogica()) {
            return objLogica.actualizarResolucion(objEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Metodo con el cual se insertan las resoluciones de facturacion compañia
     *
     * @return
     */
    @WebMethod(operationName = "obtenerResolucionesFact")
    @WebResult(name = "resoluciones")
    public List<ResolucionFactEntity> obtenerResolucionesFact() {
        try (ResolucionFactLogica objLogica = new ResolucionFactLogica()) {
            return objLogica.obtieneResolucionesFacturacion();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Metodo con el cual obtienen las remisiones por medio del cliente compañia
     *
     * @param idCliente
     * @param fechaIni
     * @param fechafin
     * @return
     */
    @WebMethod(operationName = "obtenerRemisionesXCliente")
    @WebResult(name = "remisiones")
    public List<RemisionEntity> obtenerRemisionesXCliente(@XmlElement(required = true) @WebParam(name = "idCliente") Integer idCliente,
            @WebParam(name = "fechaIni") Date fechaIni,
            @WebParam(name = "fechafin") Date fechafin) {
        try (RemisionLogica objLogica = new RemisionLogica()) {
            return objLogica.obtieneRemisionesXCliente(idCliente, fechaIni, fechafin);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Metodo con el cual obtienen el detalle de productos compañia
     *
     * @param idRemision
     * @return
     */
    @WebMethod(operationName = "obtenerDetalleRemision")
    @WebResult(name = "detalles")
    public List<DetProdRemision> obtenerDetalleRemision(@XmlElement(required = true) @WebParam(name = "idRemision") Integer idRemision) {
        try (RemisionLogica objLogica = new RemisionLogica()) {
            return objLogica.buscaDetallesRemision(idRemision);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Metodo con el cual obtienen el detalle de productos compañia
     *
     * @param idRemision
     * @param idTius
     * @param idRsfa
     * @param diasPlazo
     * @param retefuente
     * @return
     */
    @WebMethod(operationName = "realizarFacturaXRemision")
    @WebResult(name = "respuesta")
    public String realizarFacturaXRemision(@XmlElement(required = true) @WebParam(name = "idRemision") Integer idRemision,
            @XmlElement(required = true) @WebParam(name = "idTius") Integer idTius,
            @XmlElement(required = true) @WebParam(name = "idRsfa") Integer idRsfa,
            @XmlElement(required = true) @WebParam(name = "diasPlazo") Integer diasPlazo,
            @XmlElement(required = true) @WebParam(name = "retefuente") String retefuente) {
        String rta = "";
        try {
            RemisionLogica objLogica = new RemisionLogica();
            rta = objLogica.realizarFacturaXRemision(idRemision, idTius, idRsfa, diasPlazo, retefuente);
        } catch (Exception e) {
            return null;
        }
        return rta;
    }

    /**
     * metodo que devuelve la ruta del reporte
     *
     * @param parametros
     * @param datosReporte
     * @return
     */
    @WebMethod(operationName = "generaReportes")
    public String generaReportes(List<MapaEntity> parametros, List<MapaEntity> datosReporte) {
        String rta = "";
        try (ReporteLogica logica = new ReporteLogica()) {
            rta = logica.generaReporteBase64(parametros, datosReporte);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     *
     * @param sede
     * @return
     */
    @WebMethod(operationName = "actualizarSede")
    public String actualizarSede(@XmlElement(required = true) @WebParam(name = "sede") SedeEntity sede) {
        String rta = "";
        try (SedesLogica objLogica = new SedesLogica()) {
            rta = objLogica.actualizaSede(sede);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion con la cual obtengo los dos principales identificadores de una
     * remision
     *
     * @param tipoDoc
     * @param id
     * @return
     */
    @WebMethod(operationName = "buscaDocumentosPagosRemi")
    public RelFacRemiGenEntity buscaDocumentosPagosRemi(@XmlElement(required = true) @WebParam(name = "tipoDoc") String tipoDoc,
            @XmlElement(required = true) @WebParam(name = "idDocumento") Integer id) {
        RelFacRemiGenEntity rta = null;
        try (RemisionLogica objLogica = new RemisionLogica()) {
            rta = objLogica.buscaRemisionXTipoDoc(tipoDoc, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion con la cual registro un abono a una remision
     *
     * @param idTius
     * @param idFact
     * @param valorPago
     * @param tipoPago
     * @param pagoTotal
     * @return
     */
    @WebMethod(operationName = "ejecutaPagoRemision")
    public String ejecutaPagoRemision(@XmlElement(required = true) @WebParam(name = "idTius") Integer idTius,
            @XmlElement(required = true) @WebParam(name = "idFact") Integer idFact,
            @XmlElement(required = true) @WebParam(name = "valorPago") BigDecimal valorPago,
            @XmlElement(required = true) @WebParam(name = "tipoPago") String tipoPago,
            @XmlElement(required = true) @WebParam(name = "pagoTotal") String pagoTotal) {
        String rta = "";
        try {
            RemisionLogica objLogica = new RemisionLogica();
            rta = objLogica.realizaPagoRemision(idTius, idFact, valorPago, tipoPago, pagoTotal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion con la cual obtengo los departamentos parametrizados en el
     * sistema
     *
     * @return
     */
    @WebMethod(operationName = "obtenerDepartamentos")
    @WebResult(name = "listaDepartamentos")
    public List<DepartamentoEntity> obtenerDepartamentos() {
        List<DepartamentoEntity> rta = null;
        try (UbicacionLogica objLogica = new UbicacionLogica()) {
            rta = objLogica.obtieneDepartamentos();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion con la cual obtengo la ciudad parametrizadas en el sistema
     *
     * @return
     */
    @WebMethod(operationName = "obtenerCiudades")
    @WebResult(name = "listaCiudades")
    public List<CiudadEntity> obtenerCiudades() {
        List<CiudadEntity> rta = null;
        try (UbicacionLogica objLogica = new UbicacionLogica()) {
            rta = objLogica.obtieneCiudad();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * metodo que consulta las ciudades por departamento
     *
     * @param idDepto
     * @return
     */
    @WebMethod(operationName = "obtenerCiudadesXDepartamento")
    @WebResult(name = "listaCiudades")
    public List<CiudadEntity> obtenerCiudadesXDepto(@WebParam(name = "idDepto") Integer idDepto) {
        List<CiudadEntity> rta = null;
        try (UbicacionLogica objLogica = new UbicacionLogica()) {
            rta = objLogica.obtieneCiudadXDepto(idDepto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;

    }

    /**
     * Funcion con la cual actualizo un parametro del sistema
     *
     * @param clave
     * @param nuevoValor
     * @return
     */
    @WebMethod(operationName = "actualizaParametro")
    @WebResult(name = "listaCiudades")
    public String actualizaParametro(@XmlElement(required = true) @WebParam(name = "clave") String clave,
            @XmlElement(required = true) @WebParam(name = "nuevoValor") String nuevoValor) {
        String rta = null;
        try (ParametrosEmpresaLogic objLogica = new ParametrosEmpresaLogic()) {
            rta = objLogica.actualizaParametros(clave, nuevoValor);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion con la cual obtengo la informacion principal del pago de una
     * remision
     *
     * @param idRemision
     * @param idFactura
     * @return
     */
    @WebMethod(operationName = "obtenerPrincPago")
    @WebResult(name = "pagoRemision")
    public PagoRemisionEntity obtenerPrincPago(@XmlElement(required = true) @WebParam(name = "idRemision") Integer idRemision,
            @XmlElement(required = true) @WebParam(name = "idFactura") Integer idFactura) {
        PagoRemisionEntity rta = null;
        try (PagoRemisionLogica objLogica = new PagoRemisionLogica()) {
            rta = objLogica.obtieneRemison(idRemision, idFactura);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion con la cual obtiene el detalle de los pagos
     *
     * @param idPago
     * @return
     */
    @WebMethod(operationName = "obtenerDetallePagos")
    @WebResult(name = "detallePago")
    public List<DetallePagoRemision> obtenerDetallePagos(@XmlElement(required = true) @WebParam(name = "idPago") Integer idPago) {
        List<DetallePagoRemision> rta = null;
        try (PagoRemisionLogica objLogica = new PagoRemisionLogica()) {
            rta = objLogica.obtieneDetallePago(idPago);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * metodo que consulta los usuarios por permiso
     *
     * @param permiso
     * @return
     */
    @WebMethod(operationName = "obtenerUsuariosXPermiso")
    @WebResult(name = "listaUsuarios")
    public List<UsuarioEntity> obtenerUsuariosXPermiso(@WebParam(name = "permiso") String permiso) {
        List<UsuarioEntity> respuesta = new ArrayList<>();
        try (UsuarioLogica logica = new UsuarioLogica()) {
            respuesta = logica.consultaUsuariosXPermiso(permiso);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * metodo que envia correos
     * @param mensajeCorreo
     * @return 
     */
    @WebMethod(operationName = "enviaCorreo")
    public String enviaCorreo(@WebParam(name = "mensajeCorreo") CorreoWrapperRequest mensajeCorreo) {
        String rta = "";
        try {
            EnviaCorreosLogic logic = new EnviaCorreosLogic();
            rta = logic.enviaCorreos(mensajeCorreo.getAsunto(), mensajeCorreo.getMensaje(), mensajeCorreo.getCorreo());
        } catch (Exception e) {
            rta = e.toString();
            e.printStackTrace();
        }
        return rta;
    }

}
