/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.server.facturacion;

import co.com.codesoftware.logic.facturas.FacturaLogic;
import co.com.codesoftware.persistence.entity.administracion.MoviContableEntity;
import co.com.codesoftware.persistence.entity.facturacion.HistorialFacturaEntity;
import co.com.codesoftware.persistence.entity.facturacion.ImagenFacturaEntity;
import co.com.codesoftware.wrapperrequest.CancelaFacturaWrapRequest;
import com.sun.xml.ws.developer.SchemaValidation;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.soap.SOAPBinding;

/**
 *
 * @author john
 */
@WebService(serviceName = "FacturacionEndPoint")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
@SchemaValidation
public class FacturacionEndPoint {

    /**
     * funcion que ejecuta la cancelacion de factura, se envia nuevo estado
     *
     * @param datosCancelacion
     * @return
     */
    @WebMethod(operationName = "cancelaFactura")
    public String cancelaFactura(@WebParam(name = "DatosCancelacion") CancelaFacturaWrapRequest datosCancelacion) {
        String rta = "";
        try {
            FacturaLogic logica = new FacturaLogic();
            rta = logica.llamaProcesoCancelaFac(datosCancelacion);
        } catch (Exception e) {
            e.printStackTrace();
            rta = "Error" + e.getMessage();
        }
        return rta;
    }

    /**
     * metodo que inserta la ruta de la imagen y hace el reverso de facturación
     *
     * @param datosCancelacion
     * @param imagen
     * @return
     */
    @WebMethod(operationName = "insertaImagenCancelacion")
    public String insertaImagenCancelacion(@WebParam(name = "DatosCancelacion") CancelaFacturaWrapRequest datosCancelacion, @WebParam(name = "DatosImagen") ImagenFacturaEntity imagen) {
        String rta = "";
        try (FacturaLogic logica = new FacturaLogic()) {
            rta = logica.insertaImagenFactura(imagen);
            if ("OK".equalsIgnoreCase(rta)) {
                 rta = logica.llamaProcesoCancelaFac(datosCancelacion);
            }
        } catch (Exception e) {
            rta = "Error " + e.getMessage();
            e.printStackTrace();
        }
        return rta;
    }
    
    /**
     * metodo que consult todas las imagenes de una factura especifica
     * @param idFactura
     * @return 
     */
    @WebMethod(operationName = "consutaImagenFacturas")
    public List<ImagenFacturaEntity> consutaImagenFacturas(@WebParam(name = "idFactura") Integer idFactura){
       List<ImagenFacturaEntity> rta = null;
        try (FacturaLogic logica = new FacturaLogic()){
            rta = logica.consultaImagenesFactura(idFactura);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * metodo que consulta el historial de una factura por id
     *
     * @param idFactura
     * @return
     */
    @WebMethod(operationName = "obtenerHistorialFacXID")
    public List<HistorialFacturaEntity> obtenerHistorialFacXID(@WebParam(name = "IdFactura") Integer idFactura) {
        List<HistorialFacturaEntity> rta = null;
        try (FacturaLogic logica = new FacturaLogic()) {
            rta = logica.consultaHistorialFactura(idFactura);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * metodo que consulta los movimientos contables por determinada factura
     *
     * @param idFactura
     * @param estado
     * @return
     */
    @WebMethod(operationName = "obtenerMovimientoContableXFac")
    public List<MoviContableEntity> obtenerMovimientoContableXFac(@WebParam(name = "idFactura") Integer idFactura, @WebParam(name = "estado") String estado) {
        List<MoviContableEntity> lista = null;
        try (FacturaLogic logica = new FacturaLogic()) {
            lista = logica.consultaMovContableXFac(idFactura, estado);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    


}
