/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.logica.inventario;

import co.com.codesoftware.persistencia.entidad.generico.producto.ProductoGenericoEntity;
import co.com.codesoftware.persistencia.entidad.inventario.PrecioProductoEntity;
import co.com.codesoftware.persistencia.entidad.inventario.ProductoEntity;
import co.com.codesoftware.persistencia.utilities.TypeProduct;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ACER
 */
public class ProductosGenericosLogica {

    /**
     * Funcion encargada de realizar la busqueda de productos y recetas aptos
     * para su facturacion
     *
     * @param sede_sede
     * @return
     */
    public List<ProductoGenericoEntity> obtieneProductosYRecetas(Integer sede_sede) {
        List<ProductoGenericoEntity> rta = null;
        try (ProductoLogica productsLogic = new ProductoLogica();){
            //RecetaLogic recetaLogic = new RecetaLogic();
            //List<RecetaTable> recetas = recetaLogic.getRecetas(null, sede_sede);            
            List<PrecioProductoEntity> productos = productsLogic.obtieneProductosConPrecioXSede(sede_sede);
//            if (recetas != null) {
//                if (recetas != null & recetas.size() > 0) {
//                    if (rta == null) {
//                        rta = new ArrayList<>();
//                    }
//                    for (RecetaTable receta : recetas) {
//                        rta.add(this.mapeaGenericObjectReceta(receta));
//                    }
//                }
//            }
            if (productos != null & productos.size() > 0) {
                if (rta == null) {
                    rta = new ArrayList<>();
                }
                for (PrecioProductoEntity producto : productos) {
                    rta.add(this.mapeaGenericObjectProducto(producto));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
    
    /**
     * Funcion encargada de realizar la busqueda de productos y recetas aptos
     * para su facturacion
     *
     * @param sede_sede
     * @param criterio
     * @return
     */
    public List<ProductoGenericoEntity> buscaProductosRecetasXCriterio(Integer sede_sede, String criterio) {
        List<ProductoGenericoEntity> rta = null;
        try (ProductoLogica productsLogic = new ProductoLogica();){
            //RecetaLogic recetaLogic = new RecetaLogic();
            //List<RecetaTable> recetas = recetaLogic.getRecetasXCriterio(null, sede_sede,criterio);
            List<PrecioProductoEntity> productos = productsLogic.obtieneProductosConPrecioXSedeYCrit(sede_sede, criterio);
//            if (recetas != null) {
//                if (recetas != null & recetas.size() > 0) {
//                    if (rta == null) {
//                        rta = new ArrayList<>();
//                    }
//                    for (RecetaTable receta : recetas) {
//                        rta.add(this.mapeaGenericObjectReceta(receta));
//                    }
//                }
//            }
             if (productos != null & productos.size() > 0) {
                if (rta == null) {
                    rta = new ArrayList<>();
                }
                for (PrecioProductoEntity producto : productos) {
                    rta.add(this.mapeaGenericObjectProducto(producto));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

//    /**
//     * Funcion con la cual mapeo de un objeto RecetaTable y lo convierto en un
//     * objeto Generico
//     *
//     * @param receta
//     * @return
//     */
//    private ProductoGenericoEntity mapeaGenericObjectReceta(RecetaTable receta) {
//        ProductoGenericoEntity rta = new ProductoGenericoEntity();
//        try {
//            rta.setCodigo(receta.getCodigo());
//            rta.setId(receta.getId());
//            rta.setNombre(receta.getDescripcion());
//            rta.setPrecio(receta.getPrecios().get(0).getPrecio());
//            rta.setTipoProducto(TypeProduct.RECETA);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return rta;
//    }

    /**
     * Funcion con la cual mapeo de un objeto ProductoTable y lo convierto en un
     * objeto Generico
     *
     * @param receta
     * @return
     */
    private ProductoGenericoEntity mapeaGenericObjectProducto(PrecioProductoEntity precioProductoEntity) {
        ProductoGenericoEntity rta = new ProductoGenericoEntity();
        try {
            rta.setCodigo(precioProductoEntity.getProducto().getCodigo());
            rta.setCodigoExterno(precioProductoEntity.getProducto().getCodigoExt());
            rta.setCodigoBarras(precioProductoEntity.getProducto().getCodigoBarras());
            rta.setId(precioProductoEntity.getProducto().getId());
            rta.setNombre(precioProductoEntity.getProducto().getNombre());
            rta.setDescripcion(precioProductoEntity.getProducto().getDescripcion());
            rta.setPrecio(precioProductoEntity.getPrecio());
            rta.setTipoProducto(TypeProduct.PRODUCTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
    
//    /**
//     * Funcion encargada de realizar la busqueda de productos y recetas aptos
//     * para su facturacion
//     *
//     * @param sede_sede
//     * @return
//     */
//    public List<ProductoGenericoEntity> buscaProductosRecetasXCriterio(Integer sede_sede, String criterio) {
//        List<ProductoGenericoEntity> rta = null;
//        try {
//            RecetaLogic recetaLogic = new RecetaLogic();
//            List<RecetaTable> recetas = recetaLogic.getRecetasXCriterio(null, sede_sede,criterio);
//            ProductsLogic productsLogic = new ProductsLogic();
//            List<ProductoTable> productos = productsLogic.buscaProductosXCriterio(sede_sede, criterio);
//            if (recetas != null) {
//                if (recetas != null & recetas.size() > 0) {
//                    if (rta == null) {
//                        rta = new ArrayList<>();
//                    }
//                    for (RecetaTable receta : recetas) {
//                        rta.add(this.mapeaGenericObjectReceta(receta));
//                    }
//                }
//            }
//            if (productos != null) {
//                if (rta == null) {
//                    rta = new ArrayList<>();
//                }
//                for (ProductoTable producto : productos) {
//                    rta.add(this.mapeaGenericObjectProducto(producto));
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return rta;
//    }

}
