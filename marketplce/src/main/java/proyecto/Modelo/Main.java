

package proyecto.Modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

public class Main {
    public static void main(String[] args) {
        // Crear listas de vendedores y productos
        List<Vendedor> vendedores = new ArrayList<>();
        vendedores.add(new Vendedor("Juan", "Pérez", "123456", "Calle 1", Arrays.asList("P001", "P002"), Arrays.asList("Contacto 1", "Contacto 2")));
        vendedores.add(new Vendedor("María", "González", "789012", "Calle 2", Arrays.asList("P003", "P004"), Arrays.asList("Contacto 3", "Contacto 4")));

        List<Producto> productos = new ArrayList<>();
        productos.add(new Producto("P001", "Producto 1", "Descripción del producto 1", "2022-01-01"));
        productos.add(new Producto("P002", "Producto 2", "Descripción del producto 2", "2022-01-02"));
        productos.add(new Producto("P003", "Producto 3", "Descripción del producto 3", "2022-01-03"));
        productos.add(new Producto("P004", "Producto 4", "Descripción del producto 4", "2022-01-04"));

        // Crear instancia de Utilidades
        Utilidades utilidades = Utilidades.getInstance();

        // Gestionar archivos
        utilidades.gestionarArchivos(vendedores, productos);

        // Mostrar mensaje de éxito
        System.out.println("Archivos generados con éxito");

        // Serializar listas a binario
        utilidades.serializarObjeto(vendedores, "vendedores.bin", false);
        utilidades.serializarObjeto(productos, "productos.bin", false);

        // Serializar listas a XML
        utilidades.serializarObjeto(vendedores, "vendedores.xml", true);
        utilidades.serializarObjeto(productos, "productos.xml", true);

        // Deserializar listas desde binario
        List<Vendedor> deserializedVendedoresBin = (List<Vendedor>) utilidades.deserializarObjeto("vendedores.bin", false);
        List<Producto> deserializedProductosBin = (List<Producto>) utilidades.deserializarObjeto("productos.bin", false);

        // Deserializar listas desde XML
        List<Vendedor> deserializedVendedoresXML = (List<Vendedor>) utilidades.deserializarObjeto("vendedores.xml", true);
        List<Producto> deserializedProductosXML = (List<Producto>) utilidades.deserializarObjeto("productos.xml", true);

        // Print vendedores deserializados desde binario
        System.out.println("Vendedores deserializados desde binario:");
        for (Vendedor vendedor : deserializedVendedoresBin) {
            System.out.println(vendedor);
        }

        // Print productos deserializados desde binario
        System.out.println("Productos deserializados desde binario:");
        for (Producto producto : deserializedProductosBin) {
            System.out.println(producto);
        }

        // Print vendedores deserializados desde XML
        System.out.println("Vendedores deserializados desde XML:");
        for (Vendedor vendedor : deserializedVendedoresXML) {
            System.out.println(vendedor);
        }

        // Print productos deserializados desde XML
        System.out.println("Productos deserializados desde XML:");
        for (Producto producto : deserializedProductosXML) {
            System.out.println(producto);
        }

        // Probar logger
        utilidades.logInfo("Prueba de mensaje INFO");
        utilidades.logWarning("Prueba de mensaje WARNING");
        utilidades.logSevere("Prueba de mensaje SEVERE");
    }
}