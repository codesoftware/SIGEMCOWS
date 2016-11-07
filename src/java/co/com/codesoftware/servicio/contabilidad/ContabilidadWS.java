/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.servicio.contabilidad;

import co.com.codesoftware.logica.contabilidad.ContabilidadLogica;
import co.com.codesoftware.logica.contabilidad.PucLogica;
import co.com.codesoftware.persistencia.entidad.contabilidad.AuxContableEntity;
import co.com.codesoftware.persistencia.entidad.contabilidad.ClaseEntity;
import co.com.codesoftware.persistencia.entidad.contabilidad.CuentaEntity;
import co.com.codesoftware.persistencia.entidad.contabilidad.GrupoEntity;
import co.com.codesoftware.persistencia.entidad.contabilidad.MoviContableEntity;
import co.com.codesoftware.persistencia.entidad.contabilidad.SubCuentaEntity;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.xml.bind.annotation.XmlElement;

/**
 * MO-001 Consulta movimientos contables terceros: se modifica la consulta para
 * que reciba el id del tercero y el tipo 
 * jmorenor1986 
 * 07/11/2016
 */
@WebService(serviceName = "ContabilidadWS")
public class ContabilidadWS {
    /**
     * Funcion con la cual obtengo todas las clases
     * @return 
     */
    @WebMethod(operationName = "obtenerClases")
    @WebResult(name = "listaClases")
    public List<ClaseEntity> obtenerClases(){
        List<ClaseEntity> rta = null;
        try (PucLogica objLogica = new PucLogica()){
            rta = objLogica.obtieneClases();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
    /**
     * Funcion con la cual obtengo los grupos por medio del id de la clase
     * @param idClase
     * @return 
     */
    @WebMethod(operationName = "obtenerGruposXClase")
    @WebResult(name = "listaGrupos")
    public List<GrupoEntity> obtenerGruposXClase(@WebParam(name = "idClase")Integer idClase){
        List<GrupoEntity> rta = null;
        try(PucLogica objLogica = new PucLogica()) {
            rta = objLogica.obtieneGruposXClase(idClase);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
    /**
     * Funcion con la cual obtiene las cuentas por medio de grupos
     * @param idGrupo
     * @return 
     */
    @WebMethod(operationName = "obtenerCuentaXGrupo")
    @WebResult(name = "listaCuentas")
    public List<CuentaEntity> obtenerCuentaXGrupo(@WebParam(name = "idGrupo")Integer idGrupo){
        List<CuentaEntity> rta = null;
        try(PucLogica objLogica = new PucLogica()) {
            rta = objLogica.obtieneCuenteXGrupo(idGrupo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
    /**
     * Funcion con la cual obtiene las subcuentas apartir de una cuenta
     * @param idCuenta
     * @return 
     */
    @WebMethod(operationName = "obtenerSubCuentaXCuenta")
    @WebResult(name = "listaSubCuentas")
    public List<SubCuentaEntity> obtenerSubCuentaXCuenta(Integer idCuenta){
        List<SubCuentaEntity> rta = null;
        try(PucLogica objLogica = new PucLogica()) {
            rta = objLogica.obtieneSubCuenteXCuenta(idCuenta);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
    /**
     * Funcion con la cual inserto auxiliares contables
     * @param objEntity
     * @return 
     */
    @WebMethod(operationName = "insertaAuxiliarCont")
    @WebResult(name = "rta")
    public String insertaAuxiliarCont(AuxContableEntity objEntity){
        String rta = "";
        try (PucLogica objLogica = new PucLogica()){
            rta = objLogica.insertarAuxContable(objEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
    /**
     * Funcion con la cual inserto una sub cuenta
     * @param objEntity
     * @return 
     */
    public String insertaSubCuenta(SubCuentaEntity objEntity){
        String rta = "";
        try (PucLogica objLogica = new PucLogica()){
            rta = objLogica.insertarSubCuenta(objEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
    /**
     * Funcion con la cual obtengo todos los auxiliares contables
     * @param idSubCuenta
     * @return 
     */
    @WebMethod(operationName = "obtenerAuxContableXSubCuenta")
    @WebResult(name = "listaAuxConta")
    public List<AuxContableEntity> obtenerAuxContableXSubCuenta(Integer idSubCuenta){
        List<AuxContableEntity> rta = null;
        try (PucLogica objLogica = new PucLogica()){
            rta = objLogica.obtenerAuxiliaresConXSubCuenta(idSubCuenta);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
    
    /**
     * Funcion con la cual obtengo todos los auxiliares contables por medio de algun criterio
     * @param criterio
     * @return 
     */
    @WebMethod(operationName = "obtenerAuxContableXCrit")
    @WebResult(name = "listaAuxConta")
    public List<AuxContableEntity> obtenerAuxContableXCrit(String criterio){
        List<AuxContableEntity> rta = null;
        try (PucLogica objLogica = new PucLogica()){
            rta = objLogica.obtenerAuxiliaresConXCriterio(criterio);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
    /**
     * Funcion con la cual obtengo un auxiliar por su id
     * @return 
     */
    @WebMethod(operationName = "obtenerAuxContableXId")
    @WebResult(name = "auxiliarContable")
    public AuxContableEntity obtenerAuxContableXId(Integer idAux){
        AuxContableEntity aux = null;
        try (PucLogica objLogica = new PucLogica()){
            aux = objLogica.obtenerAuxiliarContXId(idAux);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aux;
    }
    /**
     * Funcion con la cual obtengo todo el asiento contable por medio de su id de transaccion
     * @param idTransCont
     * @return 
     */
    public List<MoviContableEntity> obtenerAsientoContable(@XmlElement(required = true) @WebParam(name = "idTransCont")Integer idTransCont){
        List<MoviContableEntity> rta = null;
        try(PucLogica objLogica = new PucLogica()) {
            rta = objLogica.obtenerAsientoContable(idTransCont);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
    /**
     * Funcion con la cual obtengo los movimientos contables de acuerdo a las fechas y a el tipo de docuemnto
     * @param fechaIn
     * @param fechaFi
     * @param tipoDoc
     * @return 
     */
    @WebMethod(operationName = "obtenerMovimientosContables")
    @WebResult(name = "listaMovimientos")
    public List<MoviContableEntity> obtenerMovimientosContables(@XmlElement(required = true) @WebParam(name = "fechaIn")Date fechaIn, 
            @XmlElement(required = true) @WebParam(name = "fechaFi")Date fechaFi,
            @XmlElement(required = true) @WebParam(name = "tipoDoc")String tipoDoc ){
        List<MoviContableEntity> rta = null;
        try (ContabilidadLogica objLogica = new ContabilidadLogica()){
            rta = objLogica.consultarMovimientoscontable(fechaIn, fechaFi, tipoDoc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  rta;
    }
    /**
     * FUncion con la cual obtengo los movimientos contables por medio de la cuenta
     * @param fechaIn
     * @param fechaFi
     * @param cuenta
     * @param tipo
     * @param tercero
     * 
     * @return 
     */
    @WebMethod(operationName = "obtenerMoviContCuenta")
    @WebResult(name = "listaMovimientos")
    public List<MoviContableEntity> obtenerMoviContCuenta(@XmlElement(required = true) @WebParam(name = "fechaIn")Date fechaIn, 
            @XmlElement(required = true) @WebParam(name = "fechaFi")Date fechaFi,
            @XmlElement(required = true) @WebParam(name = "cuenta")String cuenta,
            @XmlElement(required = true) @WebParam(name = "tipo")String tipo,
            @XmlElement(required = true) @WebParam(name = "tercero")Integer tercero){
        List<MoviContableEntity> rta = null;
        try (ContabilidadLogica objLogica = new ContabilidadLogica()){
            //MO-001
            rta = objLogica.consultarMovContXCuenta(fechaIn, fechaFi, cuenta,tipo,tercero);
            //MO-001
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  rta;
    }
}
