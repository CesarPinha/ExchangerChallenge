import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaTipoDeCambio {
    private static final String API_KEY = "6ec74b6290a0c74f70532f9f";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    private final HttpClient client;
    private final Gson gson;

    public ConsultaTipoDeCambio() {
        this.client = HttpClient.newHttpClient();
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public CambioAPI buscarTipoDeCambio(String monedaOrigen, String monedaDestino) throws IOException, InterruptedException {
        String url = BASE_URL + API_KEY + "/pair/" + monedaOrigen + "/" + monedaDestino;
        URI direccion = URI.create(url);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Error al conectar con la API. Código de respuesta: " + response.statusCode());
        }

        CambioAPI cambio = gson.fromJson(response.body(), CambioAPI.class);

        if (!"success".equalsIgnoreCase(cambio.getResult())) {
            throw new RuntimeException("La API no pudo procesar la solicitud. Resultado: " + cambio.getResult());
        }

        if (cambio.getConversionRate() == null) {
            throw new RuntimeException("No se recibió una tasa de conversión válida.");
        }

        return cambio;
    }
}
