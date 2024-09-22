package proyecto.Modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        // Print vendedores
        for (Vendedor vendedor : vendedores) {
            System.out.println(vendedor.toString());
        }

        // Print productos
        for (Producto producto : productos) {
            System.out.println(producto.toString());
        }
    }
}