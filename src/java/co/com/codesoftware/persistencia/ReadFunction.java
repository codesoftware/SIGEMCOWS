package co.com.codesoftware.persistencia;

import co.com.codesoftware.persistencia.utilities.ParamFunction;
import co.com.codesoftware.persistencia.utilities.DataType;
import co.com.codesoftware.persistencia.utilities.Parametro;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadFunction implements AutoCloseable {

    private static Connection con;
    private String base;
    private int numParam;
    private String nombreFuncion;
    private ArrayList<ParamFunction> parameters;
    private ArrayList<Parametro> parametros;
    private String respuesta;
    private String rtaPg;
    private List<String> respuestaPg;
    private ConexionJdbc conexionJdbc;

    /**
     * Funcion que toma el objeto conexion de Hibernate y lo utiliza como un
     * objeto conexion de jdbc
     *
     * @return
     */
    public boolean conectionJDBC() {
        try {
            this.conexionJdbc = ConexionJdbc.getInstance();
            con = conexionJdbc.conexion();
            if (con == null) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Funcion en la cual se adicionan parametros a la lista
     *
     * @param value
     * @param dataType
     * @return
     */
    public boolean addParametro(String value, DataType dataType) {
        ParamFunction param = new ParamFunction();
        param.setDataType(dataType);
        param.setName(value);
        if (parameters == null) {
            parameters = new ArrayList<ParamFunction>();
        }
        parameters.add(param);
        return true;
    }

    /**
     * Funcion con la cual inserto un parametro para llamar un proceso
     *
     * @param value
     * @param dataType
     * @return
     */
    public boolean adicionarParametro(Object value, DataType dataType) {
        Parametro param = new Parametro();
        param.setObjeto(value);
        param.setDataType(dataType);
        if (parametros == null) {
            this.parametros = new ArrayList<>();
        }
        this.parametros.add(param);
        return true;
    }
    
    public void vaciaParametros(){
        this.parametros = null;
        this.parameters = null;                
    }

    /**
     * Funcion con el cual creo el String base para hacer el llamado de la base
     * de datos
     *
     * @return
     */
    public boolean createBase() {
        try {
            this.base = "select ";
            this.base += this.nombreFuncion;
            this.base += "(";
            for (int i = 0; i < numParam; i++) {
                if (i == 0) {
                    this.base += " ? ";
                } else {
                    this.base += ", ? ";
                }
            }
            this.base += ")";
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Funcion con la cual se llama un procedimiento de postgres
     *
     * @return
     */
    public boolean llamarFuncion() {
        try (ConexionJdbc objConexion = ConexionJdbc.getInstance()) {
            this.createBase();
            Connection conn = objConexion.conexion();
            if (conn != null) {
                PreparedStatement ps = conn.prepareStatement(this.base);
                int i = 1;
                if (this.parametros != null) {
                    for (Parametro parametro : this.parametros) {
                        if (parametro.getDataType().toString().equalsIgnoreCase("TEXT")) {
                            String aux = (String) parametro.getObjeto();
                            if(aux== null){
                                ps.setNull(i, Types.VARCHAR);
                            }else{
                                ps.setString(i, aux);
                            }
                        } else if (parametro.getDataType().toString().equalsIgnoreCase("INT")) {
                            Integer aux =  (Integer) parametro.getObjeto();
                            if(aux== null){
                                ps.setNull(i, Types.VARCHAR);
                            }else{
                                ps.setInt(i, aux);
                            }
                        } else if(parametro.getDataType().toString().equalsIgnoreCase("BIGDECIMAL")){
                            BigDecimal aux = (BigDecimal) parametro.getObjeto();
                            if(aux== null){
                                ps.setNull(i, Types.NUMERIC);
                            }else{
                                ps.setBigDecimal(i, aux);
                            }
                        }
                        i++;
                    }
                }
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    rtaPg = rs.getString(1);
                }
                this.ListResponsePg();
                ps.close();
                conn.close();
            }else{
                respuesta = "Error al crear la conexion ";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Funcion con la cual se llama un procedimiento de postgres con la conexion
     * del pool
     *
     * @return
     */
    public boolean callFunctionJdbc() {
        try (ConexionJdbc objConexion = ConexionJdbc.getInstance()) {
            this.createBase();
            Connection conn = objConexion.conexion();
            if (conn != null) {
                PreparedStatement ps = conn.prepareStatement(this.base);
                int i = 1;
                if (this.parameters != null) {
                    for (ParamFunction parametro : this.parameters) {
                        if (parametro.getDataType().toString().equalsIgnoreCase("TEXT")) {
                            ps.setString(i, parametro.getName());
                        } else if (parametro.getDataType().toString().equalsIgnoreCase("INT")) {
                            ps.setInt(i, Integer.parseInt(parametro.getName()));
                        } else if (parametro.getDataType().toString().equalsIgnoreCase("NUMERIC")) {
                            ps.setDouble(i, Double.parseDouble(parametro.getName()));
                        } else if (parametro.getDataType().toString().equalsIgnoreCase("BIGDECIMAL")) {
                            ps.setBigDecimal(i, new BigDecimal(parametro.getName()));
                        } else if (parametro.getDataType().toString().equalsIgnoreCase("DATE")) {
                            long auxFecha = Long.parseLong(parametro.getName());
                            Date fecha = new Date(auxFecha);
                            ps.setDate(i, fecha);
                            //ps.setNull(i, Types.DATE);
                        }
                        i++;
                    }
                }
                System.out.println("Sql : " + ps.toString());
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    rtaPg = rs.getString(1);
                }
                this.ListResponsePg();
                ps.close();
                conn.close();
            } else {
                respuesta = "Error al crear la conexion ";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Funcion con la cual se ejecuta un query directo a la base de datos
     *
     * @param query
     * @return
     */
    public ResultSet enviaQuery(String query) {
        ResultSet rs = null;
        try {
            ConexionJdbc objConexion = ConexionJdbc.getInstance();
            Connection conexion = objConexion.conexion();
            Statement st = conexion.createStatement();
            rs = st.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void ListResponsePg() {
        try {
            if (rtaPg.indexOf("Error") >= 0) {
                System.out.println("Postgres envio un error: " + rtaPg);
                if (respuestaPg == null) {
                    respuestaPg = new ArrayList<>();
                }
                respuestaPg.add(rtaPg);
            } else {
                rtaPg = rtaPg.replaceAll("\\(", "");
                rtaPg = rtaPg.replaceAll("\\)", "");
                respuestaPg = Arrays.asList(rtaPg.split(","));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para cerrar las conexiones
     */
    public void close() throws Exception {
        try {
            if (conexionJdbc != null) {
                conexionJdbc.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public int getNumParam() {
        return numParam;
    }

    public void setNumParam(int numParam) {
        this.numParam = numParam;
    }

    public String getNombreFuncion() {
        return nombreFuncion;
    }

    public void setNombreFuncion(String nombreFuncion) {
        this.nombreFuncion = nombreFuncion;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public List<String> getRespuestaPg() {
        return respuestaPg;
    }

    public void setRespuestaPg(List<String> respuestaPg) {
        this.respuestaPg = respuestaPg;
    }

}
