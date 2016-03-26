package co.com.codesoftware.logica.admin;

import co.com.codesoftware.persistencia.HibernateUtil;
import co.com.codesoftware.persistencia.entidad.admin.SedeEntity;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class SedesLogica implements AutoCloseable {

    private Session sesion;
    private Transaction tx;

    /**
     * Metodo que mapea la lista de todas las sedes
     *
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<SedeEntity> sedeList() throws Exception {
        List<SedeEntity> sedes = null;
        try {
            initOperation();
            sedes = sesion.createQuery("from SedeEntity").list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return sedes;
    }

    /**
     * Funcion que incializa la sesion
     *
     * @throws HibernateException
     */
    private void initOperation() throws HibernateException {
        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
    }

    @Override
    public void close() throws Exception {
        // TODO Auto-generated method stub
        tx.commit();
        sesion.close();
    }

}
