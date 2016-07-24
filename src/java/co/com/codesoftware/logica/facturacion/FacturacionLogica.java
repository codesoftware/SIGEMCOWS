/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.logica.facturacion;

import co.com.codesoftware.logica.admin.ClienteLogica;
import co.com.codesoftware.logica.admin.UsuarioLogica;
import co.com.codesoftware.persistencia.HibernateUtil;
import co.com.codesoftware.persistencia.ReadFunction;
import co.com.codesoftware.persistencia.entidad.admin.ClienteEntity;
import co.com.codesoftware.persistencia.entidad.admin.ParametrosEmpresaEntity;
import co.com.codesoftware.persistencia.entidad.admin.UsuarioEntity;
import co.com.codesoftware.persistencia.entidad.contabilidad.MoviContableEntity;
import co.com.codesoftware.persistencia.entidad.facturacion.DetProduFacturaEntity;
import co.com.codesoftware.persistencia.entidad.facturacion.DetReceFacturaEntity;
import co.com.codesoftware.persistencia.entidad.facturacion.FacturaEntity;
import co.com.codesoftware.persistencia.entidad.facturacion.TemporalProdTable;
import co.com.codesoftware.persistencia.entidad.facturacion.TemporalRecTable;
import co.com.codesoftware.persistencia.entidad.generico.facturacion.FacturacionGenEntity;
import co.com.codesoftware.persistencia.utilities.DataType;
import co.com.codesoftware.persistencia.utilities.RespuestaFacturacion;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author nicolas
 */
public class FacturacionLogica implements AutoCloseable {

    private Session sesion;
    private Transaction tx;
    private String llamadoFunction;
    private String idFactura;
    private String mensaje;

    /**
     * metodo que consulta las facturas por fecha y por sede
     *
     * @param fechaInicial
     * @param fechaFinal
     * @param sede
     * @param estado
     * @return
     */
    public List<FacturaEntity> obtieneFacturasXSede(Date fechaInicial,
            Date fechaFinal,
            Integer sede, 
            String estado) {
        List<FacturaEntity> rta = null;
        try {
            initOperation();
            Criteria crit = this.sesion.createCriteria(FacturaEntity.class);
            crit.setFetchMode("idSede", FetchMode.JOIN)
                    .setFetchMode("cliente", FetchMode.JOIN)
                    .createAlias("idSede", "sed").add(Restrictions.eq("sed.id", sede))
                    .add(Restrictions.between("fecha", fechaInicial, fechaFinal)).addOrder(Order.desc("id"));
            if(estado == null){
                estado = "";
            }
            if(!"".equalsIgnoreCase(estado.trim())){
                if("R".equalsIgnoreCase(estado.trim())){
                    crit.add(Restrictions.sqlRestriction("fact_fact in (select remi_fact from in_tremi)"));
                }else if("PC".equalsIgnoreCase(estado.trim())){
                    String []valores = new String[] { "S", "A","R"};
                    crit.add(Restrictions.in("estado", valores));
                }else if("P".equalsIgnoreCase(estado.trim())){
                    crit.add(Restrictions.eq("estado", estado.trim()));
                }else if("C".equalsIgnoreCase(estado.trim())){
                    crit.add(Restrictions.eq("estado", estado.trim()));
                }
            }
            rta = crit.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;

    }

    /**
     * Funcion con la cual obtengo facturas por medio de una serie de filtros
     *
     * @param fechaInicial
     * @param fechaFinal
     * @param idFactura
     * @param idCliente
     * @param codExterno
     * @return
     */
    public List<FacturaEntity> obtieneFacturasXFiltros(Date fechaInicial,
            Date fechaFinal,
            Integer idFactura,
            Integer idCliente,
            String codExterno) {
        List<FacturaEntity> facturas = null;
        try {
            this.initOperation();
            Criteria crit = sesion.createCriteria(FacturaEntity.class);
            crit.setFetchMode("idSede", FetchMode.JOIN);
            crit.setFetchMode("cliente", FetchMode.JOIN);
            //Numero desde el cual se inicio la facturacion
            Integer iniFact = this.buscaConcecutivoFactura();
            if (idFactura == null) {
                idFactura = 0;
            }
           //idFactura = idFactura - iniFact;
            if (idFactura > 0) {
                crit.add(Restrictions.eq("id", idFactura));
            } else {
                if (idCliente != null && idCliente > 0) {
                    crit.createAlias("cliente", "cli");
                    crit.add(Restrictions.eq("cli.id", idCliente));
                }
                if (codExterno != null && !"".equalsIgnoreCase(codExterno)) {
                    Integer idProducto = this.buscaIdProductoXCodigoExterno(codExterno);
                    if (idProducto != null && idProducto != 0) {
                        ArrayList<Integer> idFacturas = this.obtieneFacturasXCodigoProducto(idProducto);
                        if (idFacturas != null) {
                            crit.add(Restrictions.in("id", idFacturas));
                        }
                    }
                }
                if (fechaInicial != null && fechaFinal != null) {
                    fechaFinal.setHours(23);
                    fechaFinal.setMinutes(59);
                    fechaFinal.setSeconds(59);
                    crit.add(Restrictions.between("fecha", fechaInicial, fechaFinal));
                }
            }
            crit.addOrder(Order.desc("id"));
            facturas = crit.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return facturas;
    }

    /**
     * Funcion con la cual busca dentro de las facturas un producto en
     * especifico
     *
     * @param idDska
     * @return
     */
    public ArrayList<Integer> obtieneFacturasXCodigoProducto(Integer idDska) {
        ArrayList<Integer> lista = null;
        try {
            Query query = sesion.createQuery("select det.idFactura from DetProduFacturaEntity det where producto.id = :idProducto ");
            query.setParameter("idProducto", idDska);
            lista = (ArrayList<Integer>) query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    /**
     * Funcion con la cual busco el id de un producto por medio de su codigo
     * Externo
     *
     * @param codigoExterno
     * @return
     */
    public Integer buscaIdProductoXCodigoExterno(String codigoExterno) {
        Integer idDska = 0;
        try {
            Query query = sesion.createQuery("select p.id from ProductoEntity p where codigoExt = :codExterno ");
            query.setParameter("codExterno", codigoExterno);
            idDska = (Integer) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idDska;
    }

    /**
     * Funcion con la cual se obtiene una factura por medio de su id
     *
     * @param id
     * @return
     */
    public FacturaEntity getFacturaForId(Integer id) {
        FacturaEntity factura = null;
        boolean validaFecha = true;
        try {
            initOperation();
            Criteria criteria = sesion.createCriteria(FacturaEntity.class);
            criteria.add(Restrictions.eq("id", id));
            criteria.setFetchMode("cliente", FetchMode.JOIN);
            criteria.setFetchMode("idSede", FetchMode.JOIN);
            factura = (FacturaEntity) criteria.uniqueResult();
            if (factura != null) {
                Criteria crit = sesion.createCriteria(DetProduFacturaEntity.class);
                crit.add(Restrictions.eq("idFactura", id));
                crit.setFetchMode("producto", FetchMode.JOIN);
                crit.setFetchMode("producto.referencia", FetchMode.JOIN);
                crit.setFetchMode("producto.marca", FetchMode.JOIN);
                crit.setFetchMode("producto.categoria", FetchMode.JOIN);
                crit.setFetchMode("producto.subcuenta", FetchMode.JOIN);
                List<DetProduFacturaEntity> aux = crit.list();
                if (aux != null) {
                    if (!aux.isEmpty()) {
                        if (validaFecha) {
                            validaFecha = false;
                            factura.setFechaExacta(aux.get(0).getFecha());
                        }
//                        for (DetProdFacturaTable detalle : aux) {
//                            Query query3 = sesion.createQuery("from ProductoTable where id = :idDska ");
//                            query3.setParameter("idDska", detalle.getIdProducto());
//                            detalle.setProducto((ProductoTable) query3.uniqueResult());
//                        }
                        factura.setDetalleProductos(aux);
                    }
                }
                //Obtengo las recetas

                factura.setDetalleRecetas(sesion.createCriteria(DetReceFacturaEntity.class).
                        add(Restrictions.eq("factura", id)).list());
//                Query query2 = sesion.createQuery("from DetReceFacturacionTable where idFact = :idFact ");
//                query2.setParameter("idFact", id);
//                List<DetReceFacturacionTable> aux2 = query2.list();
//                if (aux2 != null) {
//                    if (!aux2.isEmpty()) {
//                        if (validaFecha) {
//                            validaFecha = false;
//                            factura.setFechaExacta(aux2.get(0).getFecha());
//                        }
//                        for (DetReceFacturacionTable receta : aux2) {
//                            Query query4 = sesion.createQuery("from RecetaTable where id = :idRece ");
//                            query4.setParameter("idRece", receta.getIdRece());
//                            receta.setReceta((RecetaTable) query4.uniqueResult());
//                        }
//                        factura.setDetalleRecetas(aux2);
//                    }
//                }
            }
//            if (factura != null) {
//                Query query2 = sesion.createQuery("from Cliente WHERE id = :idCliente ");
//                query2.setParameter("idCliente", factura.getIdCliente());
//                factura.setCliente((Cliente) query2.uniqueResult());
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return factura;
    }

    /**
     * Funcion con la cual obtengo la suma de facturas que existen en el dia
     *
     * @param sede
     * @return
     */
    public BigDecimal validaValorCaja(Integer sede) {
        BigDecimal rta = null;
        try {
            initOperation();
            rta = (BigDecimal) sesion.createQuery("SELECT (SUM(valor) + sum(vlrIva))- sum(vlrReteFu) FROM FacturaEntity where fecha = current_date and idSede.id =:sede").
                    setParameter("sede", sede).uniqueResult();
            if (rta == null) {
                rta = new BigDecimal(0);
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

    /**
     * Funcion con la cual busco el consecutivo de factura que se encuentra en
     * los parametros de la aplicacion
     *
     * @return
     */
    public Integer buscaConcecutivoFactura() {
        Integer id = 0;
        try {
            Criteria crit = sesion.createCriteria(ParametrosEmpresaEntity.class);
            crit.add(Restrictions.eq("clave", "FACTURA"));
            ParametrosEmpresaEntity tabla = (ParametrosEmpresaEntity) crit.uniqueResult();
            if (tabla != null) {
                id = Integer.parseInt(tabla.getValor());
            } else {
                id = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    /**
     * Funcion encargada de realizar la logica para la realizacion de la factura
     * en el sistema
     *
     * @param facturacion
     * @return
     */
    public RespuestaFacturacion generaFacturacion(FacturacionGenEntity facturacion) {
        RespuestaFacturacion rta = new RespuestaFacturacion();
        Integer idTrans = 0;
        String msn = "Validacion de datos incorrecta";
        int iterator = 0;
        try {
            if (this.validaListasRecibidad(facturacion)) {
                boolean valida = this.validaInformacionPreFacturacion(facturacion, rta);
                if (valida) {
                    initOperation();
                    idTrans = this.getSecunceTransId();
                    if (facturacion.getProductos() != null) {
                        for (TemporalProdTable producto : facturacion.getProductos()) {
                            boolean validaItem = validaItemProducto(producto);
                            if (validaItem) {
                                Integer id = this.getMaxId();
                                producto.setId(id + 1);
                                producto.setIdTrans(idTrans);
                                sesion.save(producto);
                                tx.commit();
                                iterator++;
                            }
                        }
                    }
                    if (facturacion.getRecetas() != null) {
                        for (TemporalRecTable receta : facturacion.getRecetas()) {
                            boolean validaItem = validaItemReceta(receta);
                            if (validaItem) {
                                receta.setIdTrans(idTrans);
                                sesion.save(receta);
                                tx.commit();
                                iterator++;
                            }
                        }

                    }
                    if (iterator > 0) {
                        if (facturacion.isDomicilio()) {
                            //Logica para enviar que la factura es domicilio
                        }
                        msn = this.llamaFuncionFacturacion(facturacion, idTrans);
                        rta.setRespuesta(llamadoFunction);
                        rta.setIdFacturacion(this.idFactura);
                        rta.setTrazaExcepcion(msn);
                    } else {
                        rta.setRespuesta("Error");
                        rta.setTrazaExcepcion("No se registro ningun producto valido por facturar");
                    }
                }
            } else {
                rta.setRespuesta("Error");
                rta.setTrazaExcepcion("Las dos listas no pueden estar vacias");
            }
        } catch (Exception e) {
            e.printStackTrace();
            rta.setRespuesta("Error");
            rta.setTrazaExcepcion(e.toString());
        }
        return rta;
    }

    /**
     * Funcion con la cual validamos si las dos listas estan nulas
     *
     * @param factura
     * @return
     */
    public boolean validaListasRecibidad(FacturacionGenEntity factura) {
        if (factura.getProductos() == null & factura.getRecetas() == null) {
            return false;
        }
        return true;
    }

    /**
     * Funcion la cual se encargara de validar la informacion enviada en la
     * facturacion antes de realizar la facturacion
     *
     * @param facturacion
     * @return
     */
    private boolean validaInformacionPreFacturacion(FacturacionGenEntity facturacion, RespuestaFacturacion rta) {
        try {
            if (facturacion.getIdTius() == null) {
                rta.setRespuesta("Error");
                rta.setTrazaExcepcion("Debe registrar el id del usuario que desea realizar la operación");
                return false;
            }
            if (facturacion.getIdSede() == null) {
                rta.setRespuesta("Error");
                rta.setTrazaExcepcion("Debe registrar el id del usuario que desea realizar la operación");
                return false;
            }
            if (facturacion.getIdCliente() == null) {
                rta.setRespuesta("Error");
                rta.setTrazaExcepcion("El id del cliente llega nulo imposible procesar la solicitud");
            }
            boolean valida = this.validaCliente(facturacion.getIdCliente());
            if (!valida) {
                rta.setRespuesta("Error");
                rta.setTrazaExcepcion("El cliente al cual le desea realizar la facturacion no se encuentra en la base de datos del sistema");
                return false;
            }

            valida = this.validaUsuario(facturacion.getIdTius());
            if (!valida) {
                rta.setRespuesta("Error");
                rta.setTrazaExcepcion("El usuario que intenta hacer la operacion de facturar no se encuentra en el sistema");
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Funcion encargada de validar si un cliente existe basandose en su id
     *
     * @param clien_clien
     * @return
     */
    private boolean validaCliente(Integer clien_clien) {
        try (ClienteLogica clienteLogic = new ClienteLogica()) {
            ClienteEntity cliente = clienteLogic.getCliente(clien_clien);
            if (cliente == null) {
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
     * Funcion la cual obtiene el valor unico de transaccion el cual tendran
     * todos los items de la transaccion
     *
     * @return
     */
    public Integer getSecunceTransId() {
        Integer result = null;
        try {
            this.initOperation();
            result = (Integer) sesion.createSQLQuery("select cast(nextval('co_temp_tran_factu_sec')as int) ").uniqueResult();
            this.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }

    /**
     * Funcion encargada de validar si existe un usuario basado en id del
     * usuario
     *
     * @param tius_tius
     * @return
     */
    private boolean validaUsuario(Integer tius_tius) {
        try (UsuarioLogica usuaLogic = new UsuarioLogica()) {
            UsuarioEntity usuario = usuaLogic.obtieneUsuarioXId(tius_tius);
            if (usuario == null) {
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
     * Funcion la cual valida si un objeto producto de facturacion viene con los
     * datos correctos
     *
     * @return
     */
    public boolean validaItemProducto(TemporalProdTable producto) {
        try {
            if (producto.getCantidad() == null) {
                this.mensaje = "La cantidad del producto no puede ser nula";
                return false;
            }
            if (producto.getCantidad() <= 0) {
                this.mensaje = "La cantidad del producto debe ser mayor a uno";
                return false;
            }

            if (producto.getIdDska() == null) {
                this.mensaje = "El id del producto no puede ser nulo";
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Funcion la cual obtiene el id unico de la tabla temporal de facturacion
     * de productos
     *
     * @return
     */
    public Integer getMaxId() {
        Integer result = null;
        try {
            initOperation();
            result = (Integer) sesion.createQuery("select coalesce(max(id),0) from TemporalProdTable").uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Funcion la cual obtiene el id unico de la tabla temporal de facturacion
     * de recetas
     *
     * @return
     */
    public Integer getMaxIdReceta() {
        Integer result = null;
        try {
            initOperation();
            result = (Integer) sesion.createQuery("select coalesce(max(id),0) from TemporalRecTable").uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Funcion la cual valida si un objeto receta de facturacion viene con los
     * datos correctos
     *
     * @return
     */
    public boolean validaItemReceta(TemporalRecTable receta) {
        try {
            if (receta.getCantidad() == null) {
                this.mensaje = "La cantidad de una receta no puede ser nula";
                return false;
            }

            if (receta.getCantidad() <= 0) {
                this.mensaje = "La cantidad del producto no puede ser menor o igual a cero";
                return false;
            }

            if (receta.getIdReceta() == null) {
                this.mensaje = "El id de la receta no puede ser nulo";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Funcion encargada de realizar el llamado a la funcion que realiza toda la
     * facturacion
     *
     * @param objFactura
     * @param idTrans
     * @return
     */
    public String llamaFuncionFacturacion(FacturacionGenEntity objFactura, Integer idTrans) {
        String rta = "";
        List<String> response = new ArrayList<>();
        try (ReadFunction rf = new ReadFunction()) {
            rf.setNombreFuncion("FA_FACTURACION");
            rf.setNumParam(7);
            rf.addParametro("" + objFactura.getIdTius(), DataType.INT);
            rf.addParametro("" + objFactura.getIdCliente(), DataType.INT);
            rf.addParametro("" + idTrans, DataType.INT);
            rf.addParametro("" + objFactura.getIdSede(), DataType.INT);
            rf.addParametro("N/A", DataType.TEXT);
            rf.addParametro("0", DataType.BIGDECIMAL);
            rf.addParametro("0", DataType.BIGDECIMAL);
            rf.callFunctionJdbc();
            response = rf.getRespuestaPg();
            String respuesta = response.get(0);
            if (respuesta.indexOf("Error") >= 0) {
                respuesta = respuesta.replaceAll("Error", "");
                rta = respuesta;
                llamadoFunction = "error";
            } else {
                String[] aux = respuesta.split("-");
                llamadoFunction = aux[0];
                idFactura = aux[1];
            }
        } catch (Exception e) {
            llamadoFunction = "error";
            rta = e.toString();
            e.printStackTrace();
        }
        return rta;
    }

    public String getLlamadoFunction() {
        return llamadoFunction;
    }

    public void setLlamadoFunction(String llamadoFunction) {
        this.llamadoFunction = llamadoFunction;
    }

    public String getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(String idFactura) {
        this.idFactura = idFactura;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * Funcion encargada de realizar la logica para la realizacion de la
     * facturas avanzadas en el sistema
     *
     * @param facturacion
     * @return
     */
    public RespuestaFacturacion generaFacturacionAvanzada(FacturacionGenEntity facturacion) {
        RespuestaFacturacion rta = new RespuestaFacturacion();
        Integer idTrans = 0;
        String msn = "Validacion de datos incorrecta";
        int iterator = 0;
        try {
            if (this.validaListasRecibidad(facturacion)) {
                boolean valida = this.validaInformacionPreFacturacion(facturacion, rta);
                if (valida) {
                    idTrans = this.getSecunceTransId();
                    this.close();
                    if (facturacion.getProductos() != null) {
                        for (TemporalProdTable producto : facturacion.getProductos()) {
                            boolean validaItem = validaItemProducto(producto);
                            if (validaItem) {
                                this.initOperation();
                                Integer id = this.getMaxId();
                                producto.setId(id + 1);
                                producto.setIdTrans(idTrans);
                                sesion.save(producto);
                                iterator++;
                                this.close();
                            }
                        }
                    }
                    if (facturacion.getRecetas() != null) {
                        for (TemporalRecTable receta : facturacion.getRecetas()) {
                            boolean validaItem = validaItemReceta(receta);
                            if (validaItem) {
                                this.initOperation();
                                receta.setIdTrans(idTrans);
                                sesion.save(receta);
                                iterator++;
                                this.close();
                            }
                        }

                    }
                    if (iterator > 0) {
                        if (facturacion.isDomicilio()) {
                            //Logica para enviar que la factura es domicilio
                        }
                        msn = this.llamaFuncionFacturacionAvanzada(facturacion, idTrans);
                        rta.setRespuesta(llamadoFunction);
                        rta.setIdFacturacion(this.idFactura);
                        rta.setTrazaExcepcion(msn);
                    } else {
                        rta.setRespuesta("Error");
                        rta.setTrazaExcepcion("No se registro ningun producto valido por facturar");
                    }
                }
            } else {
                rta.setRespuesta("Error");
                rta.setTrazaExcepcion("Las dos listas no pueden estar vacias");
            }
        } catch (Exception e) {
            e.printStackTrace();
            rta.setRespuesta("Error");
            rta.setTrazaExcepcion(e.toString());
        }
        return rta;
    }

    /**
     * Funcion encargada de realizar el llamado a la funcion que realiza toda la
     * facturacion
     *
     * @return
     */
    public String llamaFuncionFacturacionAvanzada(FacturacionGenEntity objFactura, Integer idTrans) {
        String rta = "";
        List<String> response = new ArrayList<>();
        try (ReadFunction rf = new ReadFunction()) {
            rf.setNombreFuncion("FA_FACTURACION_X_PRECIO");
            rf.setNumParam(9);
            rf.addParametro("" + objFactura.getIdTius(), DataType.INT);
            rf.addParametro("" + objFactura.getIdCliente(), DataType.INT);
            rf.addParametro("" + idTrans, DataType.INT);
            rf.addParametro("" + objFactura.getIdSede(), DataType.INT);
            rf.addParametro("N/A", DataType.TEXT);
            rf.addParametro("0", DataType.INT);
            rf.addParametro("0", DataType.INT);
            rf.addParametro("" + objFactura.getIdPedido(), DataType.INT);
            rf.addParametro(objFactura.getReteFuente(), DataType.TEXT);

            rf.callFunctionJdbc();
            response = rf.getRespuestaPg();
            String respuesta = response.get(0);
            if (respuesta.indexOf("Error") >= 0) {
                respuesta = respuesta.replaceAll("Error", "");
                rta = respuesta;
                llamadoFunction = "error";
            } else {
                String[] aux = respuesta.split("-");
                llamadoFunction = aux[0];
                idFactura = aux[1];
            }
        } catch (Exception e) {
            llamadoFunction = "error";
            rta = e.toString();
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion que consulta los movimientos contables de una factura especifica
     *
     * @param idFactura
     * @param estado
     * @return
     */
    public List<MoviContableEntity> consultaMovContableXFac(Integer idFactura, String estado) {
        List<MoviContableEntity> rta = null;
        try {
            Integer idF = idFactura;
            if ("notcr".equalsIgnoreCase(estado)) {
                idFactura = llamaProcesoIdCancelacion(idFactura);
                if (idFactura == -1) {
                    idFactura = idF;
                    idF = -1;
                }
            }
            initOperation();
            Criteria crit = sesion.createCriteria(MoviContableEntity.class)
                    .add(Restrictions.eq("idLlave", idFactura));
            if (idF != -1) {
                crit.add(Restrictions.eq("llave", estado));
            }
            rta = crit.addOrder(Order.asc("naturaleza"))
                    .setFetchMode("subcuenta", FetchMode.JOIN)
                    .setFetchMode("tipoDocumento", FetchMode.JOIN)
                    .setFetchMode("auxiliar", FetchMode.JOIN)
                    .list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion con
     *
     * @param idFactura
     * @return
     */
    public Integer llamaProcesoIdCancelacion(Integer idFactura) {
        Integer rta = -1;
        try (ReadFunction rf = new ReadFunction()) {
            rf.setNombreFuncion("FA_ID_CONSULTA_NOTA");
            rf.setNumParam(1);
            rf.addParametro(idFactura.toString(), DataType.INT);
            boolean valida = rf.callFunctionJdbc();
            if (valida) {
                rta = Integer.parseInt(rf.getRespuestaPg().get(0));
            } else {
                rta = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
    /**
     * Funcion con la cual obtengo el valor de 
     * @param valor
     * @return 
     */
    public BigDecimal obtieneValorFacturasMes(Integer valor){
        BigDecimal valorTotal = null;
        System.out.println("Valor " + valor);
        try {
            this.initOperation();
            Criteria crit = this.sesion.createCriteria(FacturaEntity.class);
            crit.setProjection(Projections.sum("valor"));
            if(valor == 0){
                crit.add(Restrictions.sqlRestriction("to_char(fact_fec_ini , 'mm/yyyy') = to_char(now(), 'mm/yyyy')"));
            }else{
                crit.add(Restrictions.sqlRestriction("to_char(fact_fec_ini , 'mm/yyyy') =  to_char(current_date + interval '-"+valor+" month', 'mm/yyyy')"));
            }
            valorTotal = (BigDecimal) crit.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return valorTotal;
    }

}
