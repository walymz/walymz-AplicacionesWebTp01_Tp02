package Client;

import ar.org.centro8.curso.java.aplicaciones.entities.Articulo;
import ar.org.centro8.curso.java.aplicaciones.entities.Cliente;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest;
import java.net.URI;
import java.net.URL;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;

public class Client {

    public static void main(String[] args) throws Exception {
        String url; 
        String puerto = "http://localhost:8089/server/"; 
         //////////// ARTÍCULO //////////////
    /*     
        url = "http://localhost:8089/Clase06/ArticuloAlta?descripcion=Vaso_Vidrio&costo=90";
        System.out.println("****************************************************");
        System.out.println("Servicio Alta Articulos.");
        System.out.println(responseBody(url));

        url = "http://localhost:8089/Clase06/ArticuloAlta?descripcion=Plato_Ceramica&costo=90";
        System.out.println("****************************************************");
        System.out.println("Servicio Alta Articulos.");
        System.out.println(responseBody(url));

        url = "http://localhost:8089/Clase06/ArticuloAlta?descripcion=Cuchillo_Cocina&costo=990";
        System.out.println("****************************************************");
        System.out.println("Servicio Alta Articulos.");
        System.out.println(responseBody(url));

        url = "http://localhost:8089/Clase06/ArticuloAll";
        System.out.println("****************************************************");
        System.out.println("Servicio ArticuloAll.");
        System.out.println(responseBody(url));

        url = "http://localhost:8089/Clase06/ArticuloLikeDescripcion?descripcion=san";
        System.out.println("****************************************************");
        System.out.println("Servicio ArticuloLikeDescripcion.");
        System.out.println(responseBody(url));

        // Armar List JSon
        Type listType=new TypeToken<List<Articulo>>(){}.getType();
        url = "http://localhost:8089/Clase06/ArticuloAll";
        System.out.println("****************************************************");
        System.out.println("List<Articulo>");
        List<Articulo> list = new Gson()
                .fromJson(responseBody(url), new TypeToken<List<Articulo>>() {
                }.getType());
        list.forEach(System.out::println);

        url = "http://localhost:8089/Clase06/Login";
        System.out.println("****************************************************");
        String user = "root";
        String pass = "123";
        System.out.println(session(url, user, pass));

        url = "http://localhost:8089/Clase06/ArticuloBaja?id=34";
        System.out.println("****************************************************");
        System.out.println("Servicio Baja Articulos.");
        System.out.println(responseBody(url));
*/
    
    //////////// CLIENTE //////////////
        
        url = puerto+"ClienteAlta?nombre=Karla&apellido=Urbina&edad=18&tipoDocumento=DNI&numeroDocumento=22112222";
        System.out.println(url);
        System.out.println("****************************************************");
        System.out.println("Servicio Alta Cliente.");
        System.out.println(responseBody(url));

        url = puerto+"ClienteAlta?nombre=Sonia&apellido=Gomez&edad=27&tipoDocumento=DNI&numeroDocumento=22223333";
        System.out.println(url);
        System.out.println("****************************************************");
        System.out.println("Servicio Alta Cliente.");
        System.out.println(responseBody(url));

        url = puerto+"ClienteAlta?nombre=Luis&apellido=larez&edad=31&tipoDocumento=DNI&numeroDocumento=33334444";
        System.out.println(url);
        System.out.println("****************************************************");
        System.out.println("Servicio Alta Cliente.");
        System.out.println(responseBody(url));

        url = puerto+"ClienteAll";
        System.out.println(url);
        System.out.println("****************************************************");
        System.out.println("Servicio ClienteAll.");
        System.out.println(responseBody(url));
        
        url = puerto+"ClienteLikeApellido?apellido=Ma";
        System.out.println(url);
        System.out.println("****************************************************");
        System.out.println("Servicio ClienteByApellido.");
        System.out.println(responseBody(url));

       // Armar List JSon
        Type listType=new TypeToken<List<Cliente>>(){}.getType();
        url = puerto+"ClienteAll";
        System.out.println("****************************************************");
        System.out.println("List<Cliente>");
        List<Cliente> list = new Gson()
                .fromJson(responseBody(url), new TypeToken<List<Cliente>>() {
                }.getType());
        list.forEach(System.out::println);

//        url = puerto+"Login";
//        System.out.println("****************************************************");
//        String user = "root";
//        String pass = "123";
//        System.out.println(session(url, user, pass));

        url = puerto+"ClienteBaja?id=83";
        System.out.println(url);
        System.out.println("****************************************************");
        System.out.println("Servicio Baja Cliente.");
        System.out.println(responseBody(url));
        
    
    }

    private static String session(String url, String user, String pass) {
        //create the http client
        // https://mkyong.com/java/how-to-send-http-request-getpost-in-java/
        String resp = "";
        try {

            HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();

            //add reuqest header
            httpClient.setRequestMethod("POST");
            httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");
            httpClient.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            String urlParameters = "user=" + user + "&pass=" + pass;

            // Send post request
            httpClient.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(httpClient.getOutputStream())) {
                wr.writeBytes(urlParameters);
                wr.flush();
            }

            int responseCode = httpClient.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + urlParameters);
            System.out.println("Response Code : " + responseCode);

            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(httpClient.getInputStream()))) {

                String line;
                StringBuilder response = new StringBuilder();

                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
                System.out.println("-----------------------------------------");
                System.out.println(response.toString());
                System.out.println("-----------------------------------------");
                resp = response.toString();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return resp;
    }

    private static String responseBody(String url) throws InterruptedException, IOException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        response.headers().map().forEach((k, v) -> System.out.println(k + " " + v));
        return response.body();
    }

}
