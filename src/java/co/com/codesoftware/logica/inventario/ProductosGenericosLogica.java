/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.logica.inventario;

import co.com.codesoftware.logica.receta.RecetaLogica;
import co.com.codesoftware.persistencia.entidad.generico.producto.ProductoGenericoEntity;
import co.com.codesoftware.persistencia.entidad.inventario.PrecioProductoEntity;
import co.com.codesoftware.persistencia.entidad.inventario.ProductoEntity;
import co.com.codesoftware.persistencia.entidad.receta.PrecioRecetaEntity;
import co.com.codesoftware.persistencia.utilities.TypeProduct;
import java.math.BigDecimal;
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
        try (ProductoLogica productsLogic = new ProductoLogica();) {
            RecetaLogica recetaLogic = new RecetaLogica();
            List<PrecioRecetaEntity> recetas = recetaLogic.getRecetas(sede_sede);
            List<PrecioProductoEntity> productos = productsLogic.obtieneProductosConPrecioXSede(sede_sede);
            if (recetas != null) {
                if (recetas != null & recetas.size() > 0) {
                    if (rta == null) {
                        rta = new ArrayList<>();
                    }
                    for (PrecioRecetaEntity receta : recetas) {
                        rta.add(this.mapeaGenericObjectReceta(receta));
                    }
                }
            }
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
     * metoo que consulta los productos por sede en particular
     *
     * @param sede_sede
     * @return
     */
    public List<ProductoGenericoEntity> obtieneProductosXSede(Integer sede_sede) {
        List<ProductoGenericoEntity> rta = null;
        try (ProductoLogica productsLogic = new ProductoLogica();) {
            List<ProductoEntity> productos = productsLogic.obtieneProductosXSede(sede_sede);
            if (productos != null & productos.size() > 0) {
                if (rta == null) {
                    rta = new ArrayList<>();
                }
                for (ProductoEntity producto : productos) {
                    rta.add(this.mapeaGenericObjectProductoEntity(producto));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }
    /**
     * funcion que consulta un producto por el codigo externo
     * @param codigoExt
     * @return 
     */
    public ProductoGenericoEntity buscaProductoXCodigoExt(String codigoExt){
        ProductoGenericoEntity producto = new ProductoGenericoEntity();
        try (ProductoLogica logica = new ProductoLogica()){
            ProductoEntity productoEntitiy = new ProductoEntity();

            productoEntitiy = logica.obtieneProductoXCodigo(codigoExt);
            if(productoEntitiy!=null)
            producto = mapeaGenericObjectProductoEntity(productoEntitiy);
        } catch (Exception e) {
            e.printStackTrace();
            producto=null;
        }
        return producto;
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
        try (ProductoLogica productsLogic = new ProductoLogica();) {
            RecetaLogica recetaLogica = new RecetaLogica();
            List<PrecioRecetaEntity> recetas = recetaLogica.getRecetasXCriterio(sede_sede, criterio);
            List<PrecioProductoEntity> productos = productsLogic.obtieneProductosConPrecioXSedeYCrit(sede_sede, criterio);
            if (recetas != null) {
                if (recetas != null & recetas.size() > 0) {
                    if (rta == null) {
                        rta = new ArrayList<>();
                    }
                    for (PrecioRecetaEntity receta : recetas) {
                        rta.add(this.mapeaGenericObjectReceta(receta));
                    }
                }
            }
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
     * Funcion con la cual mapeo de un objeto RecetaTable y lo convierto en un
     * objeto Generico
     *
     * @param receta
     * @return
     */
    private ProductoGenericoEntity mapeaGenericObjectReceta(PrecioRecetaEntity receta) {
        ProductoGenericoEntity rta = new ProductoGenericoEntity();
        try {
            rta.setCodigo(receta.getReceta().getCodigo());
            rta.setId(receta.getReceta().getId());
            rta.setNombre(receta.getReceta().getNombre());
            rta.setPrecio(receta.getPrecio());
            rta.setTipoProducto(TypeProduct.RECETA);
            rta.setCodigoExterno(receta.getReceta().getCodigo());
            rta.setDescripcion(receta.getReceta().getDescripcion());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rta;
    }

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
    
      private ProductoGenericoEntity mapeaGenericObjectProductoEntity(ProductoEntity precioProductoEntity) {
        ProductoGenericoEntity rta = new ProductoGenericoEntity();
        try {
            rta.setCodigo(precioProductoEntity.getCodigo());
            rta.setCodigoExterno(precioProductoEntity.getCodigoExt());
            rta.setCodigoBarras(precioProductoEntity.getCodigoBarras());
            rta.setId(precioProductoEntity.getId());
            rta.setNombre(precioProductoEntity.getNombre());
            rta.setDescripcion(precioProductoEntity.getDescripcion());
            rta.setPrecio(new BigDecimal(0));
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
