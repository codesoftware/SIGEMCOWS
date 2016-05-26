/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.logica.admin;

import co.com.codesoftware.persistencia.HibernateUtil;
import co.com.codesoftware.persistencia.ReadFunction;
import co.com.codesoftware.persistencia.entidad.admin.PerfilEntity;
import co.com.codesoftware.persistencia.entidad.admin.UsuarioEntity;
import co.com.codesoftware.persistencia.utilities.DataType;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author nicolas
 */
public class UsuarioLogica implements AutoCloseable {

    private Session sesion;
    private Transaction tx;

    /**
     * Funcion encargada de realizar el llamado a la funcion de autenticacion y
     * mediante su resultado poder hacer las validaciones pertinentes
     *
     * @param name String Usuario que se desea autenticar
     * @param pass String Contraseña de acceso del usuario
     * @return
     */
    public List<String> login(String name, String pass) {
        List<String> response = new ArrayList<>();
        try (ReadFunction rf = new ReadFunction()) {
            rf.setNombreFuncion("US_FAUTENTICAR_USUA");
            rf.setNumParam(2);
            rf.addParametro(name, DataType.TEXT);
            rf.addParametro(pass, DataType.TEXT);
            rf.callFunctionJdbc();
            response = rf.getRespuestaPg();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * Funcion con la cual obtenemos un usuario por medio de su usuario
     *
     * @param user String usuario asignado para el sistema
     * @return
     */
    public UsuarioEntity obtieneUsuarioXUser(String user) {
        UsuarioEntity usuario = null;
        try {
            this.initOperation();
            Criteria crit = sesion.createCriteria(UsuarioEntity.class);
            crit.add(Restrictions.eq("usuario", user));
            crit.setFetchMode("persona", FetchMode.JOIN); 
            crit.setFetchMode("perfil", FetchMode.JOIN); 
            crit.setFetchMode("sede", FetchMode.JOIN); 
            usuario = (UsuarioEntity) crit.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }

    @Override
    public void close() throws Exception {
        if (tx != null) {
            tx.commit();
        }
        if (sesion != null) {
            sesion.close();
        }
    }

    /**
     * Funcion con la cual inicia la transaccion hibernate
     */
    private void initOperation() {
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            if (tx == null) {
                tx = sesion.beginTransaction();
            }
        } catch (Exception e) {
            e.printStackTrace();
            HibernateUtil.generaNuloSesion();
            try {
                sesion = HibernateUtil.getSessionFactory().openSession();
                if (tx == null) {
                    tx = sesion.beginTransaction();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    /**
     * Funcion con la cual se puede cambiar la contraseña del usuario
     *
     * @param usuario
     * @return
     */
    public boolean cambioContrasenaObligatorio(UsuarioEntity usuario) {
        boolean rta = true;
        List<String> response = new ArrayList<>();
        try (ReadFunction rf = new ReadFunction()) {
            rf.setNombreFuncion("US_FCAMBIO_CLAVE");
            rf.setNumParam(2);
            rf.addParametro(usuario.getUsuario(), DataType.TEXT);
            rf.addParametro(usuario.getPassword(), DataType.TEXT);
            rf.callFunctionJdbc();
            response = rf.getRespuestaPg();
            if ("Ok".equalsIgnoreCase(response.get(0))) {
                rta = true;
            } else {
                rta = false;
            }
        } catch (Exception e) {
            rta = false;
            e.printStackTrace();
        }
        return rta;
    }
    
    /**
     * Funcion con la cual obtenemos el usuario por medio de su id
     *
     * @param tius_tius
     * @return
     */
    public UsuarioEntity obtieneUsuarioXId(Integer tius_tius) {
        UsuarioEntity usuario = null;
        try {
            this.initOperation();
            usuario = (UsuarioEntity) sesion.get(UsuarioEntity.class, tius_tius);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }
    /**
     * Funcion con la cual obtengo todos los usuarios de la aplicacion
     * @return 
     */
    public List<UsuarioEntity> obtenerUsuarios(){
        List<UsuarioEntity> rta = null;
        try {
            this.initOperation();
            Criteria crit = this.sesion.createCriteria(UsuarioEntity.class);
            rta = crit.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
    /**
     * Funcion con el cual obtengo los perfiles del sistema
     * @return 
     */
    public List<PerfilEntity> obtenerPerfiles(){
        List<PerfilEntity> rta = null;
        try {
            this.initOperation();
            rta = this.sesion.createCriteria(PerfilEntity.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
    /**
     * Funcion con la cual actualizo el usuario
     * @param usuario
     * @return 
     */
    public String actualizaUsuarioEntity(UsuarioEntity usuario){
        String rta = "";
        List<String> response = new ArrayList<>();
        try (ReadFunction rf = new ReadFunction()){
            rf.setNombreFuncion("US_FACTUALIZA_USUARIO");
            rf.setNumParam(9);
            rf.addParametro(usuario.getPersona().getNombre(), DataType.TEXT);
            rf.addParametro(usuario.getPersona().getApellido(), DataType.TEXT);
            rf.addParametro(usuario.getPersona().getCedula(), DataType.TEXT);
            rf.addParametro(usuario.getPersona().getCorreo(), DataType.TEXT);
            rf.addParametro(usuario.getPersona().getFecha_nac().getTime()+"", DataType.DATE);
            rf.addParametro(usuario.getPerfil().getId().toString(), DataType.INT);
            rf.addParametro(usuario.getEstado(), DataType.TEXT);
            rf.addParametro(usuario.getUsuario(), DataType.TEXT);
            rf.addParametro(usuario.getSede().getId().toString(), DataType.INT);
            rf.callFunctionJdbc();
            response = rf.getRespuestaPg();
            rta = response.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
    
    /**
     * Funcion con la cual inserto el usuario
     * @param usuario
     * @return 
     */
    public String insertarUsuarioEntity(UsuarioEntity usuario){
        String rta = "";
        List<String> response = new ArrayList<>();
        try (ReadFunction rf = new ReadFunction()){
            rf.setNombreFuncion("US_FINSERTA_USUA");
            rf.setNumParam(9);
            rf.addParametro(usuario.getPersona().getNombre(), DataType.TEXT);
            rf.addParametro(usuario.getPersona().getApellido(), DataType.TEXT);
            rf.addParametro(usuario.getPersona().getCedula(), DataType.TEXT);
            rf.addParametro(usuario.getPersona().getCorreo(), DataType.TEXT);
            rf.addParametro(usuario.getPersona().getFecha_nac().getTime()+"", DataType.DATE);
            rf.addParametro(usuario.getUsuario(), DataType.TEXT);
            rf.addParametro("1234", DataType.TEXT);
            rf.addParametro(usuario.getPerfil().getId().toString(), DataType.INT);
            rf.addParametro(usuario.getSede().getId().toString(), DataType.INT);
            rf.callFunctionJdbc();
            response = rf.getRespuestaPg();
            rta = response.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
    
}
