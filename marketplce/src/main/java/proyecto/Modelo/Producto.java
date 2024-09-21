package proyecto.Modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Producto {
    private String id;
    private String nombre;
    private String descripcion;
    private String fechaPublicacion;

    // Constructor
    public Producto() {
        this.id = "";
        this.nombre = "";
        this.descripcion = "";
        this.fechaPublicacion = "";
    }

    // Métodos get
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    // Métodos set
    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    // Método toString
    @Override
    public String toString() {
        return id + "," + nombre + "," + descripcion + "," + fechaPublicacion;
    }

    private static final String ARCHIVO_PRODUCTOS = "productos.txt";

    public static void createProducto(Producto producto) throws IOException {
        System.out.println("Creando producto: " + producto.toString());
        File archivo = new File(ARCHIVO_PRODUCTOS);
        if (!archivo.exists()) {
            System.out.println("El archivo no existe, creando nuevo archivo...");
            archivo.createNewFile();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_PRODUCTOS, true));
        try {
            writer.write(producto.toString() + "\n");
        } finally {
            writer.close(); // Cierra el escritor explícitamente
        }
    }

    public static List<Producto> readProductos() throws IOException {
        List<Producto> productos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_PRODUCTOS))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] campos = linea.split(",");
                Producto producto = new Producto();
                producto.setId(campos[0]);
                producto.setNombre(campos[1]);
                producto.setDescripcion(campos[2]);
                producto.setFechaPublicacion(campos[3]);
                productos.add(producto);
            }
        }
        return productos;
    }

    public static void updateProducto(Producto producto) throws IOException {
        List<Producto> productos = readProductos();
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId().equals(producto.getId())) {
                productos.set(i, producto);
                break;
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_PRODUCTOS))) {
            for (Producto p : productos) {
                writer.write(p.toString() + "\n");
            }
        }
    }

    public static void deleteProducto(String id) throws IOException {
        List<Producto> productos = readProductos();
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId().equals(id)) {
                productos.remove(i);
                break;
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_PRODUCTOS))) {
            for (Producto p : productos) {
                writer.write(p.toString() + "\n");
            }
        }
    }
}
