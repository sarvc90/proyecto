package proyecto.Modelo.si;

import java.io.*;
import java.util.logging.*;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;

public class Utilidades {
    private static Utilidades instancia;
    private ResourceBundle mensajes;
    private Locale locale;

    private Utilidades() {

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


}
