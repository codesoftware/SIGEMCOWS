/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.utilidades;

/**
 *
 * @author root
 */
public class Validaciones {
    /**
     * Funcion que compara si lo que se envia es un texto o un numero
     * @param filtro
     * @return 
     */
    public boolean validaNumero(String filtro){
        try {
           int respuesta = Integer.parseInt(filtro);
           return true;
        } catch (Exception e) {
            return false;
        }
    }
}
