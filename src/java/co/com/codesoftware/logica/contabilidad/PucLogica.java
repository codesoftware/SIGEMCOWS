/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.logica.contabilidad;

import co.com.codesoftware.persistencia.HibernateUtil;
import co.com.codesoftware.persistencia.entidad.contabilidad.AuxContableEntity;
import co.com.codesoftware.persistencia.entidad.contabilidad.ClaseEntity;
import co.com.codesoftware.persistencia.entidad.contabilidad.CuentaEntity;
import co.com.codesoftware.persistencia.entidad.contabilidad.GrupoEntity;
import co.com.codesoftware.persistencia.entidad.contabilidad.SubCuentaEntity;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ACER
 */
public class PucLogica implements AutoCloseable {

    private Session sesion;
    private Transaction tx;

    /**
     * Funcion con la cual obtengo las clases
     *
     * @return
     */
    public List<ClaseEntity> obtieneClases() {
        List<ClaseEntity> rta = null;
        try {
            this.initOperation();
            Criteria crit = this.sesion.createCriteria(ClaseEntity.class);
            rta = crit.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion con la cual obtengo los grupos por medio del id de la clase
     *
     * @param idClase
     * @return
     */
    public List<GrupoEntity> obtieneGruposXClase(Integer idClase) {
        List<GrupoEntity> rta = null;
        try {
            this.initOperation();
            Criteria crit = this.sesion.createCriteria(GrupoEntity.class);
            crit.add(Restrictions.eq("idClase", idClase));
            rta = crit.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion con la cual obtiene las cuentas por medio del id del grupo
     *
     * @param idGrupo
     * @return
     */
    public List<CuentaEntity> obtieneCuenteXGrupo(Integer idGrupo) {
        List<CuentaEntity> rta = null;
        try {
            this.initOperation();
            Criteria crit = this.sesion.createCriteria(CuentaEntity.class);
            crit.add(Restrictions.eq("idGrupo", idGrupo));
            rta = crit.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
    /**
     * Funcion con la cual obtiene subcuentas apartir de una cuenta
     * @param idCuenta
     * @return 
     */
    public List<SubCuentaEntity> obtieneSubCuenteXCuenta(Integer idCuenta) {
        List<SubCuentaEntity> rta = null;
        try {
            this.initOperation();
            Criteria crit = this.sesion.createCriteria(SubCuentaEntity.class);
            crit.add(Restrictions.eq("cuenta", idCuenta));
            rta = crit.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
    /**
     * Funcion con la cual inserto un auxiliar contable
     * @return 
     */
    public String insertarAuxContable(AuxContableEntity objEntity){
        String rta = "Ok";
        try {
            this.initOperation();
            sesion.save(objEntity);
        } catch (Exception e) {
            rta = "Error " + e;
            e.printStackTrace();
        }
        return rta;                
    }
    /**
     * Funcion con la cual obtengo los auxiliares contables por medio de una subcuenta
     * @param idSubCuenta
     * @return 
     */
    public List<AuxContableEntity> obtenerAuxiliaresConXSubCuenta(Integer idSubCuenta){
        List<AuxContableEntity> rta = null;
        try {
            this.initOperation();
            Criteria crit = this.sesion.createCriteria(AuxContableEntity.class);
            crit.add(Restrictions.eq("idSbcu", idSubCuenta));
            crit.addOrder(Order.asc("id"));
            rta = crit.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
    /**
     * Funcion con la cual obtengo el auxiliar contable
     * @param idAuxCont
     * @return 
     */
    public AuxContableEntity obtenerAuxiliarContXId(Integer idAuxCont){
        AuxContableEntity rta = null;
        try {
            this.initOperation();
            Criteria crit = this.sesion.createCriteria(AuxContableEntity.class);
            crit.add(Restrictions.eq("id", idAuxCont));
            crit.addOrder(Order.asc("id"));
            rta = (AuxContableEntity) crit.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
    
    /**
     * Funcion con la cual obtengo los auxiliares contables por medio de algun criterio
     * @param criterio
     * @return 
     */
    public List<AuxContableEntity> obtenerAuxiliaresConXCriterio(String criterio){
        List<AuxContableEntity> rta = null;
        try {
            this.initOperation();
            Criteria crit = this.sesion.createCriteria(AuxContableEntity.class);
            Criterion uno = Restrictions.like("codigo", "%"+criterio+"%").ignoreCase();
            Criterion dos = Restrictions.like("nombre", "%"+criterio+"%").ignoreCase();
            crit.add(Restrictions.or(uno,dos));
            crit.addOrder(Order.asc("id"));
            rta = crit.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    private void initOperation() {
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            tx = sesion.beginTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() throws Exception {
        try {
            if (tx != null) {
                tx.commit();
            }
            if (sesion != null) {
                sesion.close();
            }
        } catch (Exception e) {
            System.err.println("Error al cerrar la sesion del cliente hibernate " + e);
        }

    }

}
