/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.logic;

import co.com.codesoftware.persistence.entity.administracion.UsuarioEntity;
import co.com.codesoftware.persistencia.HibernateUtil;
import co.com.codesoftware.persistencia.ReadFunction;
import co.com.codesoftware.persistencia.utilities.DataType;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ACER
 */
public class UsuarioLogic implements AutoCloseable {

    private Session sesion;
    private Transaction tx;

    /**
     * Funcion la cual busca el usuario
     *
     * @param usuario
     * @return
     */
    public UsuarioEntity obtieneUsuario(String usuario) {
        UsuarioEntity usuarioEntity = null;
        try {
            this.initOperation();
            Criteria crit = sesion.createCriteria(UsuarioEntity.class);
            crit.add(Restrictions.eq("usuario", usuario.toUpperCase()));
            usuarioEntity = (UsuarioEntity) crit.uniqueResult();
        } catch (Exception e) {
            usuarioEntity = new UsuarioEntity();
            usuarioEntity.setDescripcionRespuesta(e.getMessage());
            usuarioEntity.setCodigoRespuesta(0);
            usuarioEntity.setMensajeRespuesta("ERROR");
        }
        return usuarioEntity;
    }

    /**
     * Funcion encargada de realizar el login de un usuario basandose en su
     * usuario y contraseña
     *
     * @param usuario
     * @param contrasena
     * @return
     */
    public List<String> loginUsuario(String usuario, String contrasena) {
        List<String> response = new ArrayList<>();
        try (ReadFunction rf = new ReadFunction()){
            rf.setNombreFuncion("US_FAUTENTICAR_USUA");
            rf.setNumParam(2);
            rf.addParametro(usuario, DataType.TEXT);
            rf.addParametro(contrasena, DataType.TEXT);
            boolean valida = rf.callFunctionJdbc();
            if(valida){
                response = rf.getRespuestaPg();
            }else{
                response.add("Error");
                response.add("Error al llamar a la funcion de login");
            }            
        } catch (Exception e) {
            response.add("rta");
            response.add("Exception " + e);
            e.printStackTrace();
        }
        return response;
    }

    private void initOperation() throws HibernateException {
        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
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

}
