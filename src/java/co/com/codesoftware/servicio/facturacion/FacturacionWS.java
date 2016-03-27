/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.servicio.facturacion;

import co.com.codesoftware.logica.facturacion.FacturacionLogica;
import co.com.codesoftware.logica.reportes.ReporteLogica;
import co.com.codesoftware.persistencia.entidad.facturacion.FacturaEntity;
import co.com.codesoftware.persistencia.entidad.generico.facturacion.FacturacionGenEntity;
import co.com.codesoftware.persistencia.utilities.RespuestaFacturacion;
import java.math.BigDecimal;
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
@WebService(serviceName = "FacturacionWS")
public class FacturacionWS {

    /**
     * Funcion con la cual obtengo las facturas de una sede y ademas de esto las
     * filtro para encontrarlas mas facil
     *
     * @param fInicial
     * @param fFinal
     * @param idFactura
     * @param IdCliente
     * @param codExterno
     * @return
     */
    @WebMethod(operationName = "obtenerFacturasConFiltros")
    @WebResult(name = "listaFacturas")
    public List<FacturaEntity> obtenerFacturasConFiltros(@XmlElement(required = true) @WebParam(name = "fInicial") Date fInicial,
            @XmlElement(required = true) @WebParam(name = "fFinal") Date fFinal,
            @WebParam(name = "idFactura") Integer idFactura,
            @WebParam(name = "idCliente") Integer IdCliente,
            @WebParam(name = "codExterno") String codExterno) {
        List<FacturaEntity> rta = null;
        try (FacturacionLogica objLogic = new FacturacionLogica()) {
            rta = objLogic.obtieneFacturasXFiltros(fInicial, fFinal, idFactura, IdCliente, codExterno);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Metodo con el cual obtengo una factura teniendo como referencia su id
     * unico
     *
     * @param idFactura
     * @return
     */
    @WebMethod(operationName = "obtenerFacturaXId")
    @WebResult(name = "FacturaEntity")
    public FacturaEntity obtenerFacturaXId(@XmlElement(required = true) @WebParam(name = "idFactura") Integer idFactura) {
        try (FacturacionLogica objLogica = new FacturacionLogica()) {
            return objLogica.getFacturaForId(idFactura);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Metodo que consulta el valor de la facturacion por sede
     *
     * @param sede
     * @return
     */
    @WebMethod(operationName = "obtenerValorCaja")
    @WebResult(name = "cantidad")
    public BigDecimal obtenerValorCaja(@XmlElement(required = true) @WebParam(name = "sede") Integer sede) {
        BigDecimal rta = null;
        try (FacturacionLogica logic = new FacturacionLogica()) {
            rta = logic.validaValorCaja(sede);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion en la cual se crea la facturacion basica del sistema
     *
     * @param facturacion
     * @return
     */
    @WebMethod(operationName = "facturar")
    @WebResult(name = "respuestaFacturacion")
    public RespuestaFacturacion facturar(@XmlElement(required = true) @WebParam(name = "facturacion") FacturacionGenEntity facturacion) {
        RespuestaFacturacion rta = null;
        try (FacturacionLogica objLogic = new FacturacionLogica()) {
            rta = objLogic.generaFacturacion(facturacion);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion con la cual se realiza la facturacion de los productos avanzados
     *
     * @param facturacion
     * @return
     */
    @WebMethod(operationName = "facturarAvanzada")
    @WebResult(name = "respuestaFacturacion")
    public RespuestaFacturacion facturarAvanzado(@XmlElement(required = true) @WebParam(name = "Facturacion") FacturacionGenEntity facturacion) {
        RespuestaFacturacion rta = null;
        try (FacturacionLogica objLogic = new FacturacionLogica()) {
            rta = objLogic.generaFacturacionAvanzada(facturacion);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion con la cual se genera un pdf con la factura apartir de su id
     *
     * @param fact_fact
     * @return
     */
    @WebMethod(operationName = "obtenerImagenXIdFactura")
    @WebResult(name = "imagen")
    public String obtenerImagenXIdFactura(@XmlElement(required = true) @WebParam(name = "fact_fact") String fact_fact) {
        String imagen = null;
        try (ReporteLogica logica = new ReporteLogica()) {
            imagen = logica.generaPdfFactura(fact_fact);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imagen;
    }

    /**
     * metodo que consulta las facturas por sede  
     * @param fInicial
     * @param fFinal
     * @param idSede
     * @return 
     */
    @WebMethod(operationName = "obtenerFacturasSede")
    @WebResult(name = "listaFacturas")
    public List<FacturaEntity> obtenerFacturasSede(@XmlElement(required = true) @WebParam(name = "fInicial") Date fInicial,@XmlElement(required = true) @WebParam(name = "fFinal") Date fFinal,
    @XmlElement(required = true) @WebParam(name = "idSede") Integer idSede) {
        List<FacturaEntity> rta = null;
        try(FacturacionLogica logica = new FacturacionLogica()) {
            rta = logica.obtieneFacturasXSede(fInicial, fFinal, idSede);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
}
