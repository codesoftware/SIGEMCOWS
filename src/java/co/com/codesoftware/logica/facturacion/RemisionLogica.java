/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.logica.facturacion;

import co.com.codesoftware.persistencia.HibernateUtil;
import co.com.codesoftware.persistencia.ReadFunction;
import co.com.codesoftware.persistencia.entidad.facturacion.DetProdRemision;
import co.com.codesoftware.persistencia.entidad.facturacion.RemisionEntity;
import co.com.codesoftware.persistencia.entidad.generico.facturacion.RelFacRemiGenEntity;
import co.com.codesoftware.persistencia.utilities.DataType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author nicolas
 */
public class RemisionLogica implements AutoCloseable {

    private Session sesion;
    private Transaction tx;

    /**
     * Funcion con la cual obtengo las remisiones basandome en el id del cliente
     *
     * @param idCliente
     * @return
     */
    public List<RemisionEntity> obtieneRemisionesXCliente(Integer idCliente, Date fechaIni, Date fechaFin) {
        List<RemisionEntity> rta = null;
        try {
            this.initOperation();
            Criteria crit = sesion.createCriteria(RemisionEntity.class);
            crit.setFetchMode("usuario.perfil", FetchMode.JOIN);
            crit.setFetchMode("usuario.persona", FetchMode.JOIN);
            crit.setFetchMode("usuario.sede", FetchMode.JOIN);
            if (fechaIni != null && fechaFin != null) {
                crit.add(Restrictions.between("fechaCreacion", fechaIni, fechaFin));
            }
            rta = crit.list();
        } catch (Exception e) {
            e.printStackTrace();
            rta = null;
        }
        return rta;
    }

    /**
     * Funcion con la cual obtengo los detalles de la remision teniendo en
     * cuenta su id
     *
     * @param idRemision
     * @return
     */
    public List<DetProdRemision> buscaDetallesRemision(Integer idRemision) {
        List<DetProdRemision> rta = null;
        try {
            this.initOperation();
            Criteria crit = sesion.createCriteria(DetProdRemision.class);
            crit.add(Restrictions.eq("idRemi", idRemision));
            crit.setFetchMode("producto.categoria", FetchMode.JOIN);
            crit.setFetchMode("producto.marca", FetchMode.JOIN);
            crit.setFetchMode("producto.referencia", FetchMode.JOIN);
            crit.setFetchMode("producto.subcuenta", FetchMode.JOIN);
            rta = crit.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion la cual llama al procedimiento para convertir una remision en
     * factura
     *
     * @param idRemision
     * @return
     */
    public String realizarFacturaXRemision(Integer idRemision, Integer idTius, Integer idRsfa, Integer diasPlazo) {
        String rta = "";
        List<String> response = new ArrayList<>();
        try (ReadFunction rf = new ReadFunction()) {
            rf.setNombreFuncion("FA_REMISION_FACTURA");
            rf.setNumParam(4);
            rf.addParametro("" + idTius, DataType.INT);
            rf.addParametro("" + idRemision, DataType.INT);
            rf.addParametro("" + idRemision, DataType.INT);
            rf.addParametro("" + diasPlazo, DataType.INT);
            rf.callFunctionJdbc();
            response = rf.getRespuestaPg();
            rta = response.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion con la cual busco los dos principales id's para la realizaccion
     * de los pagos de remisiones
     *
     * @param tipoDoc
     * @param idDocumento
     * @return
     */
    public RelFacRemiGenEntity buscaRemisionXTipoDoc(String tipoDoc, Integer idDocumento) {
        RelFacRemiGenEntity rta = null;
        try {
            this.initOperation();
            Criteria crit = sesion.createCriteria(RemisionEntity.class);
            if("FA".equalsIgnoreCase(tipoDoc)){
                crit.add(Restrictions.eq("idFactura", idDocumento));
            }else{
                crit.add(Restrictions.eq("id", idDocumento));
            }
            RemisionEntity respuesta = (RemisionEntity) crit.uniqueResult();
            rta = new RelFacRemiGenEntity();
            if(respuesta == null){
                rta.setMensaje("Error la factura no existe o no tiene una remision asociada");
            }else{
                rta.setMensaje("Ok");
                rta.setEstado(respuesta.getEstado());
                rta.setIdFactura(respuesta.getIdFactura());
                rta.setIdRemision(respuesta.getId());
            }
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

    @Override
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
