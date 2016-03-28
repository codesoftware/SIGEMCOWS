/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.logic;


import co.com.codesoftware.persistence.entities.PucEntity;
import co.com.codesoftware.persistencia.entidad.contabilidad.SubCuentaEntity;
import co.com.codesoftware.persistencia.HibernateUtil;
import co.com.codesoftware.utilidades.Validaciones;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author root
 */
public class SubCuentaLogic implements AutoCloseable {

    private Session sesion;
    private Transaction tx;

    /**
     * Funcion que consulta las subcuentas por un filtro en particular ya sea codigo
     * o nombre
     * @param filtro
     * @return 
     */
    public List<SubCuentaEntity> consultaSubCuentaFiltro(String filtro){
        List<SubCuentaEntity>  resultado = null;
        try {
            this.initOperation();
            Criteria crit = sesion.createCriteria(SubCuentaEntity.class);
            Validaciones val = new Validaciones();
            if(val.validaNumero(filtro)){
                crit.add(Restrictions.like("codigo",filtro,MatchMode.ANYWHERE));
            }else{
                crit.add(Restrictions.like("nombre",filtro,MatchMode.ANYWHERE));
            }
            resultado = crit.list();
        } catch (Exception e){
            e.printStackTrace();
        }
        return resultado;
    }
    
    /**
     * metodo quee consulta las subcuentas sin filtro
     * @return 
     */
    public List<SubCuentaEntity> consultaSubcuentas(){
        List<SubCuentaEntity> resultado = null;
        try {
            initOperation();
            resultado = sesion.createCriteria(SubCuentaEntity.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }
    
    /**
     * metodo en el cual se consulta las subcuentas mediante unos filtros y el campo en especifico
     * @param nombreFiltro: campo con el cual se desea filtrar
     * @param datos: datos de tipo Entero
     * @return lista de tipo subcuenta
     */
    public List<SubCuentaEntity> consultaSubCuentaFiltros(ArrayList<PucEntity> datos){
        List<SubCuentaEntity> resultado = new ArrayList<>();
        try {
            initOperation();
            Criteria crit = sesion.createCriteria(SubCuentaEntity.class);
            for(PucEntity item:datos){
                crit.add(Restrictions.in(item.getClave(),item.getValor()));
            }
            resultado = crit.list();
                    
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
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
