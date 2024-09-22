package proyecto.Modelo;

import java.io.*;
import java.util.logging.*;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;

public class Utilidades {
    private static Utilidades instancia;
    private static final Logger logger = Logger.getLogger(Utilidades.class.getName());
    private ResourceBundle mensajes;
    private Locale locale;

    private Utilidades() {
        // Configuración inicial del logger
        try {
            FileHandler fileHandler = new FileHandler("marketplace.log", true);
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error al configurar el logger", e);
        }

        // Configuración inicial de internacionalización
        setLocale(new Locale("es", "ES")); // Idioma por defecto
    }

    public static Utilidades getInstance() {
        if (instancia == null) {
            instancia = new Utilidades();
        }
        return instancia;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
        mensajes = ResourceBundle.getBundle("Mensajes", locale);
    }

    public Locale getLocale() {
        return locale;
    }

    public String getMensaje(String clave) {
        return mensajes.getString(clave);
    }

    public void escribirLog(String mensaje, Level nivel) {
        logger.log(nivel, mensaje);
    }

    public void gestionarArchivos(List<Vendedor> listaVendedores, List<Producto> listaProductos) {
        File directorio = new File("Marketplce/Reportes_Java");
        if (!directorio.exists()) {
            directorio.mkdirs();
        }
    
        escribirListaEnArchivo(new File(directorio, "vendedores.txt"), listaVendedores);
        escribirListaEnArchivo(new File(directorio, "productos.txt"), listaProductos);
    }
    
    private void escribirListaEnArchivo(File archivo, List<?> lista) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            for (int i = 0; i < lista.size(); i++) {
                writer.write(lista.get(i).toString());
                writer.newLine();
                if ((i + 1) % 10 == 0) {
                    writer.flush();
                }
            }
        } catch (IOException e) {
            escribirLog("Error al escribir en el archivo: " + archivo.getName(), Level.SEVERE);
        }
    }
    public void guardarVendedorEnArchivo(Vendedor vendedor) {
        File directorio = new File("Marketplce/Reportes_Java");
        if (!directorio.exists()) {
            directorio.mkdirs();
        }
    
        File archivo = new File(directorio, "vendedores.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            writer.write(vendedor.toString());
            writer.newLine();
            escribirLog("Vendedor guardado exitosamente: " + vendedor, Level.INFO);
        } catch (IOException e) {
            escribirLog("Error al guardar el vendedor: " + vendedor, Level.SEVERE);
        }
    }

    public void serializarObjeto(Object obj, String ruta, boolean esXML) {
        try (FileOutputStream fos = new FileOutputStream(ruta)) {
            if (esXML) {
                XMLEncoder encoder = new XMLEncoder(fos);
                encoder.writeObject(obj);
            } else {
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(obj);
            }
            escribirLog("Objeto serializado exitosamente a " + ruta, Level.INFO);
        } catch (IOException e) {
            escribirLog("Error al serializar el objeto: " + ruta, Level.SEVERE);
        }
    }

    public Object deserializarObjeto(String ruta, boolean esXML) {
        try (FileInputStream fis = new FileInputStream(ruta)) {
            if (esXML) {
                XMLDecoder decoder = new XMLDecoder(fis);
                return decoder.readObject();
            } else {
                ObjectInputStream ois = new ObjectInputStream(fis);
                return ois.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            escribirLog("Error al deserializar el objeto: " + ruta, Level.SEVERE);
            return null;
        }
    }
       // Métodos para registrar mensajes en diferentes niveles de severidad
       public void logSevere(String mensaje) {
        escribirLog(mensaje, Level.SEVERE);
    }

    public void logWarning(String mensaje) {
        escribirLog(mensaje, Level.WARNING);
    }

    public void logInfo(String mensaje) {
        escribirLog(mensaje, Level.INFO);
    }

    public void logConfig(String mensaje) {
        escribirLog(mensaje, Level.CONFIG);
    }

    public void logFine(String mensaje) {
        escribirLog(mensaje, Level.FINE);
    }

    public void logFiner(String mensaje) {
        escribirLog(mensaje, Level.FINER);
    }

    public void logFinest(String mensaje) {
        escribirLog(mensaje, Level.FINEST);
    }
}

