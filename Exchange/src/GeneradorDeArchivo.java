import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class GeneradorDeArchivo {
    private final Gson gson;

    public GeneradorDeArchivo() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void guardarJson(CambioAPI cambio, String origen, String destino, double cantidad, double resultado) throws IOException {
        String nombreArchivo = "conversion_" + origen + "_a_" + destino + "_" + System.currentTimeMillis() + ".json";
        Conversion conversion = new Conversion(origen, destino, cantidad, resultado, cambio.getConversionRate());

        try (FileWriter escritura = new FileWriter(nombreArchivo)) {
            escritura.write(gson.toJson(conversion));
        }
    }

    // Clase interna para estructurar la conversión
    private static class Conversion {
        private String monedaOrigen;
        private String monedaDestino;
        private double cantidadOrigen;
        private double cantidadDestino;
        private double tasaCambio;

        public Conversion(String monedaOrigen, String monedaDestino, double cantidadOrigen, double cantidadDestino, double tasaCambio) {
            this.monedaOrigen = monedaOrigen;
            this.monedaDestino = monedaDestino;
            this.cantidadOrigen = cantidadOrigen;
            this.cantidadDestino = cantidadDestino;
            this.tasaCambio = tasaCambio;
        }

    }
}
