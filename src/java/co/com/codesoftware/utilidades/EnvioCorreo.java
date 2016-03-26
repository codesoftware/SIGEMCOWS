/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.utilidades;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author john
 */
public class EnvioCorreo {

    private Properties config;
    private Session session;
    private String mensaje;
    private String Asunto;
    private String correo;
    private final String host;
    private final String port;
    private final String user;
    private final String password;

    public EnvioCorreo(String host, String port, String user, String password) {
        this.host = host;
        this.port = port;
        this.user = user;
        this.password = password;
        this.config = new Properties();
        this.config.put("mail.smtp.host", this.host);
        this.config.put("mail.smtp.socketFactory.port", this.port);
        //this.config.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        this.config.put("mail.smtp.auth", "true");
        this.config.put("mail.smtp.port", this.port);

    }

    public void loginCorreo() {

        this.session = Session.getDefaultInstance(this.config,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user, password);
                    }
                });
    }

    public static void main(String arg[]) {
        EnvioCorreo env = new EnvioCorreo("mail.tornillos7777777.com", "587", "gestion@tornillos7777777.com", "pruebas123,");
        env.loginCorreo();
        env.setAsunto("hola");
        env.setMensaje("hola");
        env.setCorreo("johnmorenoing@gmail.com");
        System.out.println(env.enviaCorreo());
    }

    /**
     * metodo que realiza el envio del correo
     *
     * @return
     */
    public String enviaCorreo() {
        String rta = "OK Correo enviado correctamente";
        try {
            Message message = new MimeMessage(this.session);
            message.setFrom(new InternetAddress(this.user));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correo));
            message.setSubject(this.Asunto);
            message.setText(this.mensaje);
            Transport.send(message);
            System.out.println("Mensaje enviado");
        } catch (MessagingException e) {
            rta = "Error al enviar correo-Err";
            throw new RuntimeException(e);
        }
        return rta;

    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getAsunto() {
        return Asunto;
    }

    public void setAsunto(String Asunto) {
        this.Asunto = Asunto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

}
