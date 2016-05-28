/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.servicio.usuario;

import co.com.codesoftware.logica.admin.ClienteLogica;
import co.com.codesoftware.logica.admin.UsuarioLogica;
import co.com.codesoftware.persistencia.entidad.admin.ClienteEntity;
import co.com.codesoftware.persistencia.entidad.admin.PerfilEntity;
import co.com.codesoftware.persistencia.entidad.admin.UsuarioEntity;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.ws.ResponseWrapper;

/**
 *
 * @author nicolas
 */
@WebService(serviceName = "UsuarioWS")
public class UsuarioWS {

    /**
     * Metodo del webservice encargado de invocar la logica para el logueo de
     * los usuarios
     *
     * @param nombre
     * @param password
     * @return
     */
    @WebMethod
    @WebResult(name = "loginResponse")
    @ResponseWrapper(localName = "loginResponse", 
            targetNamespace = "http://respuesta.codesoftware.com.co/", 
            className = "co.com.codesoftware.respuesta")
    public List<String> login(@XmlElement(required = true) @WebParam(name = "nombre") String nombre, @XmlElement(required = true) @WebParam(name = "password") String password) {
        UsuarioLogica objLogic = new UsuarioLogica();
        List<String> rta = null;
        try {
            rta = objLogic.login(nombre, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
     /**
     * Funcion con la cual obtenemos los datos de un usuario en especifico por
     * medio de su usuarioF||
     *
     * @param tius_usuario
     * @return
     */
    @WebMethod
    @WebResult(name = "UsuarioEntity", partName = "UsuarioEntity")
    public UsuarioEntity obtenerUsuarioXUser(@XmlElement(required = true) @WebParam(name = "tius_usuario") String tius_usuario) {
        try (UsuarioLogica logic = new UsuarioLogica()) {
            return logic.obtieneUsuarioXUser(tius_usuario.toUpperCase());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Funcion con la cual obtengo un cliente por su cedula
     *
     * @param cedula
     * @return
     */
    @WebMethod
    @WebResult(name = "Cliente", partName = "Cliente")
    public ClienteEntity obtenerClienteXCedula(@XmlElement(required = true) @WebParam(name = "cedula") String cedula) {
        try(ClienteLogica logic = new ClienteLogica()) {
            return logic.obtieneclienteXCedula(cedula);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Funcion con la cual actualizo un cliente en especifico
     *
     * @param cliente
     * @return
     */
    @WebMethod
    @WebResult(name = "valida")
    public boolean updateCliente(@XmlElement(required = true) @WebParam(name = "Cliente") ClienteEntity cliente) {
        try (ClienteLogica logic = new ClienteLogica()) {
            return logic.updateCliente(cliente);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Metodo encargado de realizar el cambio obligatorio de contraseña
     *
     * @param usuario
     * @return
     */
    @WebMethod(operationName = "cambiaClaveUsuario")
    @WebResult(name = "respuesta")
    public boolean cambiaClaveUsuario(@XmlElement(required = true) @WebParam(name = "usuario")UsuarioEntity usuario) {
        try (UsuarioLogica logica = new UsuarioLogica()) {
            boolean rta = logica.cambioContrasenaObligatorio(usuario);
            return rta;
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Metodo con el cual consulto todos los clientes del sistema
     *
     * @return
     */
    @WebMethod(operationName = "obtenerClientes")
    @WebResult(name = "Cliente", partName = "Cliente")
    public List<ClienteEntity> obtenerClientes() {
        try (ClienteLogica logic = new ClienteLogica()) {
            return logic.getListCliente();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Metodo con el cual adiciono un cliente al sistema
     *
     * @param cliente
     * @return
     */
    @WebMethod(operationName = "insertaCliente" )
    public Integer insertaCliente(@XmlElement(required = true)@WebParam(name = "Cliente") ClienteEntity cliente) {
        try (ClienteLogica logica = new ClienteLogica()) {
            return logica.createCliente(cliente);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Funcion con la cual obtengo los usuarios de la aplicacion
     * @return 
     */
    @WebMethod(operationName = "obtenerUsuarios")
    @WebResult(name = "listaUsuarios")
    public List<UsuarioEntity> obtenerUsuarios(){
        List<UsuarioEntity> rta = null;
        try (UsuarioLogica objLogica = new UsuarioLogica()){
            rta = objLogica.obtenerUsuarios();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
    /**
     * Funcion con la cual obtengo los perfiles de la aplicacion
     * @return 
     */
    @WebMethod(operationName = "obtenerPerfiles")
    @WebResult(name = "listaPerfiles")
    public List<PerfilEntity> obtenerPerfiles(){
        List<PerfilEntity> rta = null;
        try (UsuarioLogica objLogica = new UsuarioLogica()){
            rta = objLogica.obtenerPerfiles();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
    /**
     * Funcion con la cual actualizo un usuario
     * @param usuario
     * @return 
     */
    @WebMethod(operationName = "actualizarUsuario")
    @WebResult(name = "respuesta")
    public String actualizarUsuario(@XmlElement(required = true)@WebParam(name = "usuario") UsuarioEntity usuario){
        String rta = "";
        try {
            UsuarioLogica objLogica = new UsuarioLogica();
            rta = objLogica.actualizaUsuarioEntity(usuario);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
    /**
     * Funcion con la cual inserto un usuario en el sistema
     * @param usuario
     * @return 
     */
    @WebMethod(operationName = "insertaUsuario")
    @WebResult(name = "respuesta")
    public String insertaUsuario(@XmlElement(required = true)@WebParam(name = "usuario") UsuarioEntity usuario){;
        String rta = "";
        try {
            UsuarioLogica objLogica = new UsuarioLogica();
            rta = objLogica.insertarUsuarioEntity(usuario);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
}
