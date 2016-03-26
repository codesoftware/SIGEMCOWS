/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.logic;

import co.com.codesoftware.persistence.entity.administracion.ParametrosEntity;
import co.com.codesoftware.utilidades.EnvioCorreo;
import java.util.List;

/**
 *
 * @author john
 */
public class EnviaCorreosLogic {

    /**
     * metodo que envia correos y consulta los parametros del correo
     *
     * @param asunto
     * @param mensaje
     * @param correo
     * @return
     */
    public String enviaCorreos(String asunto, String mensaje, String correo) {
        String rta = "";
        try (ParametrosLogic logic = new ParametrosLogic()) {
            List<ParametrosEntity> listaParam = logic.consultaParametrosCorreoEnvio();
            String host = "";
            String port = "";
            String user = "";
            String password = "";
            for (ParametrosEntity item : listaParam) {
                if ("CORREOENVIO".equalsIgnoreCase(item.getClave())) {
                    user = item.getValor();
                }
                if ("CLAVECORRENV".equalsIgnoreCase(item.getClave())) {
                    password = item.getValor();
                }
                if ("PUERTOENV".equalsIgnoreCase(item.getClave())) {
                    port = item.getValor();
                }
                if ("SERVSMTP".equalsIgnoreCase(item.getClave())) {
                    host = item.getValor();
                }
            }
            EnvioCorreo env = new EnvioCorreo(host, port, user, password);
            env.loginCorreo();
            env.setAsunto(asunto);
            env.setCorreo(correo);
            env.setMensaje(mensaje);
            rta  = env.enviaCorreo();

        } catch (Exception e) {
            e.printStackTrace();
            rta = ""+e.getMessage();
        }
        return rta;
    }
}
