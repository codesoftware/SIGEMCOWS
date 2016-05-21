package co.com.codesoftware.logica.admin;

import co.com.codesoftware.persistencia.HibernateUtil;
import co.com.codesoftware.persistencia.entidad.admin.ParametrosEmpresaEntity;
import java.util.List;
import org.hibernate.Criteria;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class ParametrosEmpresaLogic implements AutoCloseable {

    private Session sesion;
    private Transaction tx;

    /**
     * Funcion con la cual obtengo todos los parametros empresariales de la
     * empresa
     *
     * @return
     */
    public List<ParametrosEmpresaEntity> obtienePrametrosEmpresa() {
        List<ParametrosEmpresaEntity> rta = null;
        try {
            this.initOperation();
            Criteria crit = sesion.createCriteria(ParametrosEmpresaEntity.class);
            rta = crit.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
    /**
     * Funcion con la cual actualizo un parametro en el sistema
     * @param id
     * @param clave
     * @param valor
     * @return 
     */
    public String actualizaParametros(String clave ,String valor){
        String rta = "Ok";
        try {
            this.initOperation();
            ParametrosEmpresaEntity parametro =(ParametrosEmpresaEntity) sesion.createCriteria(ParametrosEmpresaEntity.class).add(Restrictions.eq("clave", clave).ignoreCase()).uniqueResult();
            if(parametro != null){
                if(parametro.getClave().equalsIgnoreCase(clave)){
                    parametro.setValor(valor);
                    sesion.update(parametro);
                }else{
                    rta = "Error El parametro no coincide con la clave";
                }
            }else{
                rta = "Error El parametro que desea actualizar no existe";
            }
        } catch (Exception e) {
            e.printStackTrace();
            rta = "Error " + e;
        }
        return rta;
    }

    private void initOperation() throws HibernateException {
        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
    }

    @Override
    public void close() throws Exception {
        tx.commit();
        sesion.close();
    }

}
