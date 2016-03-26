package co.com.codesoftware.logica.admin;

import co.com.codesoftware.persistencia.HibernateUtil;
import co.com.codesoftware.persistencia.entidad.admin.ParametrosEmpresaEntity;
import java.util.List;
import org.hibernate.Criteria;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
