/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.reportes.logica;

import co.com.codesoftware.persistence.entities.MapaEntity;
import co.com.codesoftware.persistencia.HibernateUtil;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.Context;
import javax.naming.InitialContext;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import org.hibernate.Session;
import org.hibernate.engine.spi.SessionImplementor;

/**
 *
 * @author john
 */
public class ReportesLogic implements AutoCloseable {

    private Connection con;
    private Session session;
    private String rutaRepoServ;

    public ReportesLogic() {
        try {
            Context initCtx = new InitialContext();
            this.rutaRepoServ = (String) initCtx.lookup("java:comp/env/rutaReportsNSIGEMCO");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * metodo que consulta la conexion de los reportes
     *
     * @return
     */
    private boolean conectionJDBC() {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            SessionImplementor miSessionImplementor = (SessionImplementor) session;
            this.con = miSessionImplementor.connection();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }   
    }

    /**
     * funcion que genera los reportes
     *
     * @param datos
     * @param parametrosReporte
     * @return
     */
    public String generaReporte(List<MapaEntity> datos, List<MapaEntity> parametrosReporte) {
        String ruta = "";
        try {

            Map<String, Object> parametros = convierteList((ArrayList<MapaEntity>) datos);
            Map<String, Object> datosReporte = convierteList((ArrayList<MapaEntity>) parametrosReporte);
            parametros.put("RUTALOGO", rutaRepoServ);
            if (conectionJDBC()) {
                JasperReport jasperReport = (JasperReport) JRLoader.loadObject(rutaRepoServ + datosReporte.get("nombreReporte") + ".jasper");

                if ("pdf".equalsIgnoreCase(datosReporte.get("tipoReporte").toString())) {
                    JasperPrint print = JasperFillManager.fillReport(jasperReport, parametros, con);
                    ruta = rutaRepoServ + datosReporte.get("nombreReporte") + ".pdf";
                    JasperExportManager.exportReportToPdfFile(print, ruta);
                } else {
                    String print = JasperFillManager.fillReportToFile(rutaRepoServ + datosReporte.get("nombreReporte") + ".jasper", parametros, con);
                    ruta = rutaRepoServ + datosReporte.get("nombreReporte") + ".xls";
                    JRXlsExporter exporter = new JRXlsExporter();
                    exporter.setParameter(JRExporterParameter.INPUT_FILE_NAME,
                            print);
                    exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
                            ruta);
                    exporter.exportReport();
                }
            } else {
                ruta = "error";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ruta;
    }

    /**
     * metodo que convierte la lista en mapa
     *
     * @param lista
     * @return
     */
    public HashMap<String, Object> convierteList(ArrayList<MapaEntity> lista) {
        HashMap<String, Object> mapa = new HashMap<String, Object>();
        try {
            for (MapaEntity item : lista) {
                if (item.getClave().equalsIgnoreCase("fechaInicial")) {
                    mapa.put(item.getClave(), item.getValor().toString());
                } else if (item.getClave().equalsIgnoreCase("fechaFinal")) {
                    mapa.put(item.getClave(), item.getValor().toString());
                } else {
                    mapa.put(item.getClave(), item.getValor());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapa;
    }

    public Date stringToDate(String date) {
        Date respuesta = new Date();
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            respuesta = formatter.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    /**
     * metodo que cierra la sesion de conexion de los servicios Web
     *
     * @throws Exception
     */
    @Override
    public void close() throws Exception {
        try {
            this.session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
