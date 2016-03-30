/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.persistence.entities;

import java.io.Serializable;

/**
 *
 * @author john
 */
public class PucEntity implements Serializable{
    private String clave;
    private Integer[] valor;

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Integer[] getValor() {
        return valor;
    }

    public void setValor(Integer[] valor) {
        this.valor = valor;
    }
    
}
