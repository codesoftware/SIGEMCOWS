/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.logica.excel;

import co.com.codesoftware.logica.reportes.CodificaBase64;
import co.com.codesoftware.persistencia.ConexionJdbc;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.naming.Context;
import javax.naming.InitialContext;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Font;

/**
 *
 * @author Codesoftware
 */
public class ExcelLogica {

    private HSSFFont titulo;
    private HSSFWorkbook libro;
    private HSSFSheet hoja;
    private String ruta;

    public ExcelLogica() {
        try {
            Context initCtx = new InitialContext();
            this.ruta = (String) initCtx.lookup("java:comp/env/RutaBaseApp");
            this.libro = new HSSFWorkbook();
            hoja = libro.createSheet();
            this.crearfuente();
            this.ruta += "excel.xls";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String generarExcel(String sql) {
        String rta;
        try {
            try (ConexionJdbc conexion = ConexionJdbc.getInstance()) {
                ResultSetMetaData rsmt = null;
                Connection con = conexion.conexion();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);
                rsmt = rs.getMetaData();
                ArrayList<Map> nombres = null;
                HSSFRow fila = null;
                if (rsmt.getColumnCount() > 0) {
                    fila = hoja.createRow(0);
                }
                for (int i = 1; i <= rsmt.getColumnCount(); i++) {
                    HSSFCell celda = fila.createCell(i - 1);
                    String nombreCol = rsmt.getColumnName(i);
                    int tipoColumna = rsmt.getColumnType(i);
                    Map<String, String> item = new HashMap();
                    item.put("nombre", nombreCol);
                    item.put("tipo", "" + tipoColumna);
                    HSSFRichTextString texto = new HSSFRichTextString(nombreCol.toUpperCase());
                    texto.applyFont(this.titulo);
                    celda.setCellValue(texto);
                    if (nombres == null) {
                        nombres = new ArrayList();
                    }
                    nombres.add(item);
                }
                int filas = 1;
                while (rs.next()) {
                    fila = hoja.createRow(filas);
                    int celdaNum = 0;
                    for (Map columna : nombres) {
                        HSSFCell celda = fila.createCell(celdaNum);
                        if ("-5".equalsIgnoreCase((String) columna.get("tipo")) || "4".equalsIgnoreCase((String) columna.get("tipo")) || "2".equalsIgnoreCase((String) columna.get("tipo"))) {
                            Double valorCelda = rs.getDouble((String) columna.get("nombre"));
                            celda.setCellValue(valorCelda);
                        } else {
                            String valorCelda = rs.getString((String) columna.get("nombre"));
                            celda.setCellValue(valorCelda);
                        }
                        celdaNum++;
                    }
                    filas++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            FileOutputStream fos = new FileOutputStream(ruta);
            libro.write(fos);
            fos.close();
            rta = "Ok";
        } catch (Exception e) {
            e.printStackTrace();
            rta = "Error " + e;
        }
        return rta;
    }

    /**
     * Funcion con la cual genero un excel a un string en base 64
     *
     * @return
     */
    public String transformaExcelBase64() {
        String rta = "";
        CodificaBase64 codifica64 = new CodificaBase64();
        try {
            boolean codifico = codifica64.codificacionDocumento(this.ruta);
            if (codifico) {
                rta = codifica64.getDocumento();
                codifica64.setDocumento(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion con la cual creo una fuente
     */
    public void crearfuente() {
        this.titulo = libro.createFont();
        this.titulo.setColor(Font.COLOR_RED);
        this.titulo.setBoldweight(Font.BOLDWEIGHT_BOLD);
        this.titulo.setItalic(true);
    }

    public static void main(String... args) {
        try {
            ExcelLogica excel = new ExcelLogica();
            excel.setRuta("D:/Prueba.xls");
            //String rta = excel.generarExcel();
            excel.transformaExcelBase64();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

}
