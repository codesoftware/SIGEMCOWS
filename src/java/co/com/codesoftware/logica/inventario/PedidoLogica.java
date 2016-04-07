/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.logica.inventario;

import co.com.codesoftware.persistencia.HibernateUtil;
import co.com.codesoftware.persistencia.ReadFunction;
import co.com.codesoftware.persistencia.entidad.pedido.PedidoEntity;
import co.com.codesoftware.persistencia.entidad.pedido.PedidoProductoEntity;
import co.com.codesoftware.persistencia.utilities.DataType;
import co.com.codesoftware.persistencia.utilities.RespuestaEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.GenericJDBCException;

/**
 *
 * @author nicolas
 */
public class PedidoLogica implements AutoCloseable {

    private Session sesion;
    private Transaction tx;
    
    /**
     * metodo en el cual se inserta productos a partir de un pedido
     *
     * @param productos
     * @param productoId
     * @return
     */
    public RespuestaEntity insertaProductos(ArrayList<PedidoProductoEntity> productos, Integer productoId) {
        RespuestaEntity respuesta = new RespuestaEntity();
        try (PedidosProductoLogica logic = new PedidosProductoLogica();) {
            respuesta = logic.insertaProductoPedido(productos, productoId);
        } catch (GenericJDBCException e) {
            e.printStackTrace();
            System.out.println("erro" + e.getCause().getMessage());

        } catch (Exception e) {
            tx.rollback();
            System.out.println("" + e.getClass());
            respuesta.setCodigoRespuesta(0);
            respuesta.setDescripcionRespuesta(e.getMessage());
            respuesta.setMensajeRespuesta("ERROR");
            e.printStackTrace();
        }
        return respuesta;
    }
    /**
     * Metodo que inserta el pedido
     *
     * @param entidad
     * @return
     */
    public RespuestaEntity insertaPedidos(PedidoEntity entidad) {
        RespuestaEntity respuesta = new RespuestaEntity();
        try {
            this.initOperation();
            entidad.setId(selectMaxPedido());
            sesion.save(entidad);
            respuesta.setCodigoRespuesta(entidad.getId());
            respuesta.setDescripcionRespuesta("OK");
            respuesta.setMensajeRespuesta("OK");
        } catch (Exception e) {
            respuesta.setCodigoRespuesta(0);
            respuesta.setDescripcionRespuesta(e.toString());
            respuesta.setMensajeRespuesta("ERROR");
            e.printStackTrace();
        }
        return respuesta;

    }
    /**
     * metodo que consulta el id de la tabla
     *
     * @return
     */
    public Integer selectMaxPedido() {
        Integer resultado = 1;
        try {
            Criteria crit = sesion.createCriteria(PedidoEntity.class)
                    .setProjection(Projections.max("id"));
            resultado = (Integer) crit.uniqueResult() + 1;
        } catch (Exception e) {
            resultado = 1;
            e.printStackTrace();
        }
        return resultado;
    }
    
    /**
     * metodo que consulta una lista de pedidos por id
     *
     * @param estado
     * @return
     */
    public List<PedidoEntity> consultaPedidoXEstado(String estado) {
        List<PedidoEntity> respuesta = null;
        try {
            initOperation();
            Criteria crit = sesion.createCriteria(PedidoEntity.class).
                    add(Restrictions.eq("estado", estado)).addOrder(Order.desc("id"));
            respuesta = crit.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }
    
    /**
     * Funcion que elimina un pedido de la base de datos
     *
     * @param idPedido
     * @return
     */
    public boolean eliminaPedidoId(Integer idPedido) {
        try {
            if (eliminaProductoPedido(idPedido)) {
                initOperation();
                Criteria crit = sesion.createCriteria(PedidoEntity.class).
                        add(Restrictions.eq("id", idPedido));
                PedidoEntity respuesta = (PedidoEntity) crit.uniqueResult();
                sesion.delete(respuesta);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * metodo que elimina los productos de un pedido
     *
     * @param idPedido
     * @return
     */
    public boolean eliminaProductoPedido(Integer idPedido) {
        try {
            initOperation();
            Criteria crit = sesion.createCriteria(PedidoProductoEntity.class).
                    add(Restrictions.eq("pedido", idPedido));
            List<PedidoProductoEntity> respuesta = crit.list();
            if (respuesta != null) {
                for (PedidoProductoEntity item : respuesta) {
                    sesion.delete(item);
                }
                tx.commit();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
        /**
     * Consulta de pedidos por los filtros de fecha, usuario y estado
     *
     * @param estado
     * @param idUsuario
     * @param fInicial
     * @param fFinal
     * @return
     */
    public List<PedidoEntity> consultaPedidoXFiltros(String estado, Integer idUsuario, Date fInicial, Date fFinal) {
        List<PedidoEntity> respuesta = null;
        try {
            initOperation();
            Criteria crit = sesion.createCriteria(PedidoEntity.class)
                    .createAlias("usuario", "us")
                    .add(Restrictions.ge("fecha", fInicial))
                    .add(Restrictions.lt("fecha", fFinal))
                    .setFetchMode("sede", FetchMode.JOIN)
                    .setFetchMode("usuario", FetchMode.JOIN)
                    .setFetchMode("cliente", FetchMode.JOIN)
                    .setFetchMode("usuario.persona", FetchMode.JOIN)
                    .setFetchMode("usuario.perfil", FetchMode.JOIN)
                    //.add(Restrictions.between("fecha", fInicial, fFinal))
                    .add(Restrictions.eq("us.id", idUsuario));
            if (!"".equalsIgnoreCase(estado)) {
                crit.add(Restrictions.eq("estado", estado));
            }
            respuesta = crit.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }
    
    /**
     * Funcion con la cual se actualiza el estado de un pedido
     *
     * @param id
     * @param estado
     * @return
     */
    public boolean actualizaEstadoPedido(Integer id, String estado) {
        boolean rta = false;
        try {
            initOperation();
            PedidoEntity pedido = (PedidoEntity) sesion.get(PedidoEntity.class, id);
            pedido.setEstado(estado.toUpperCase());
            sesion.update(pedido);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            rta = false;
        }
        return rta;
    }
    
    /**
     * Funcion con la cual busco todas las cotizaciones generadas para un
     * cliente
     *
     * @param idCliente
     * @return
     */
    public List<PedidoEntity> buscaCotizacionXCliente(Integer idCliente) {
        List<PedidoEntity> rta = null;
        try {
            initOperation();
            Criteria crit = sesion.createCriteria(PedidoEntity.class);
            crit.createAlias("cliente", "cli").add(Restrictions.eq("cli.id", idCliente)).add(Restrictions.eq("estado", "CO"));
            crit.setFetchMode("cli", FetchMode.JOIN).setFetchMode("sede", FetchMode.JOIN);
            crit.setFetchMode("usuario", FetchMode.JOIN);
            crit.addOrder(Order.desc("id"));
            rta = crit.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
    
    /**
     * Funcion con la cual busco todas las remisiones generadas para un
     * cliente
     *
     * @param idCliente
     * @return
     */
    public List<PedidoEntity> buscaRemisionXCliente(Integer idCliente) {
        List<PedidoEntity> rta = null;
        try {
            initOperation();
            Criteria crit = sesion.createCriteria(PedidoEntity.class);
            crit.createAlias("cliente", "cli").add(Restrictions.eq("cli.id", idCliente))
                    .add(Restrictions.eq("estado", "SR"));
            crit.setFetchMode("cli", FetchMode.JOIN).setFetchMode("sede", FetchMode.JOIN);
            crit.setFetchMode("usuario", FetchMode.JOIN);
            crit.addOrder(Order.desc("id"));
            rta = crit.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
    /**
     * Funcion con la cual obtengo los datos principales de un pedido 
     * @param idPedido
     * @return 
     */
    public PedidoEntity buscaDatPrincPedido(Integer idPedido){
        PedidoEntity rta = null;
        try {
            this.initOperation();
            Criteria crit= sesion.createCriteria(PedidoEntity.class);
            crit.add(Restrictions.eq("id", idPedido));
            crit.setFetchMode("cliente", FetchMode.JOIN);
            crit.setFetchMode("usuario", FetchMode.JOIN);
            crit.setFetchMode("usuario.perfil", FetchMode.JOIN);
            crit.setFetchMode("usuario.persona", FetchMode.JOIN);
            crit.setFetchMode("sede", FetchMode.JOIN);
            rta = (PedidoEntity) crit.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

    /**
     * Funcion que inicializa la clase de hibernate
     *
     * @throws HibernateException
     */
    private void initOperation() throws HibernateException {
        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
    }

    /**
     * Funcion para cerrar la sesion
     *
     * @throws Exception
     */
    @Override
    public void close() throws Exception {
        if (tx != null) {
            tx.commit();
        }
        if (sesion != null) {
            sesion.close();
        }
    }
    /**
     * Funcion con la cual obtengo los productos de un pedido
     * @param idPedido
     * @return 
     */
    public List<PedidoProductoEntity> buscaProductosPedidoXId(Integer idPedido){
        List<PedidoProductoEntity> rta =  null;
        try {
            this.initOperation();
            Criteria crit = sesion.createCriteria(PedidoProductoEntity.class);
            crit.add(Restrictions.eq("pedido", idPedido));
            rta = crit.list(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
    /**
     * Funcion con la cual llamo a la funcion de remisionar
     * @return 
     */
    public String llamaFuncionRemisionar(Integer idPedido){
        String rta = "";
        List<String> response = new ArrayList<>();
        try (ReadFunction rf = new ReadFunction()){
            rf.setNombreFuncion("IN_GENERA_REMISION");
            rf.setNumParam(1);
            rf.addParametro(""+idPedido, DataType.INT);
            rf.callFunctionJdbc();
            response = rf.getRespuestaPg();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

}
