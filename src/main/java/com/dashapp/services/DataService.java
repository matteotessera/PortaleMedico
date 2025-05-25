package com.dashapp.services;

import com.dashapp.model.*;
import com.google.gson.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

//    DataService dataService = new DataService();
//    try {
//    Utente utente = dataService.getUtenteById(123);
//        System.out.println("Nome: " + utente.getNome());
//            } catch (Exception e) {
//            e.printStackTrace();
//    }
public class DataService {

    private static final String API_URL = "http://3.123.253.157/api/";
    private final HttpClient httpClient;
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public DataService() {
        httpClient = HttpClient.newHttpClient();
    }

    // getter

    public Utente getUtenteById(int id) throws Exception {

        String url = API_URL + "get_utente_by_id.php?id=" + id;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());



        if (response.statusCode() == 200) {
            String json = response.body();
            return parseUtenteWithDate(json);
        } else {
            throw new RuntimeException("Errore nella chiamata HTTP: " + response.statusCode());
        }
    }

    public Utente getUtenteByEmail(String email) throws Exception {
        String url = API_URL + "get_utente_by_email.php?email=" + email;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            String json = response.body();
            return parseUtenteWithDate(json);
        } else {
            throw new RuntimeException("Errore nella chiamata HTTP: " + response.statusCode());
        }
    }

    public Utente[] getUtenti() throws Exception {

        String url = API_URL + "get_utenti.php";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            String json = response.body();
            return parseUtentiManuale(json);
        } else {
            throw new RuntimeException("Errore nella chiamata HTTP: " + response.statusCode());
        }
    }

    public Farmaco getFarmacoById(int id) throws Exception {
        String url = API_URL + "get_farmaco_by_id.php?id=" + id;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            String json = response.body();
            return parseFarmacoFromJson(json);
        } else {
            throw new RuntimeException("Errore nella chiamata HTTP: " + response.statusCode());
        }
    }

    public Farmaco[] getFarmaci() throws Exception {

        String url = API_URL + "get_farmaci.php";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            String json = response.body();
            return parseFarmaciManuale(json);
        } else {
            throw new RuntimeException("Errore nella chiamata HTTP: " + response.statusCode());
        }

    }

    public Utente getMedicoDiBase(int idPaziente) throws Exception {

        String url = API_URL + "get_medico_di_base.php?id=" + idPaziente;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            String json = response.body();
            return parseUtenteWithDate(json);
        } else {
            throw new RuntimeException("Errore nella chiamata HTTP: " + response.statusCode());
        }
    }

    public Studio getStudioMedico(int idMedico) throws Exception {

        // solo se l'utente e paziente restituisce l'oggetto studio, altrimente va in exeption

        String url = API_URL + "get_studio_medico.php?id=" + idMedico;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            String json = response.body();
            return parseStudioFromJson(json);
        } else {
            throw new RuntimeException("Errore nella chiamata HTTP: " + response.statusCode());
        }

    }

    public Sintomo[] getSintomiPaziente(int idPaziente) throws Exception {

        String url = API_URL + "get_sintomi_paziente.php?id=" + idPaziente;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            String json = response.body();
            return parseSintomiManuale(json);
        } else {
            throw new RuntimeException("Errore nella chiamata HTTP: " + response.statusCode());
        }

    }

    public Rilevazione[] getRilevazioniUtente(int idPaziente) throws Exception {

        String url = API_URL + "get_rilevazioni_paziente.php?id=" + idPaziente;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            String json = response.body();
            return parseRilevazioniManuale(json);
        } else {
            throw new RuntimeException("Errore nella chiamata HTTP: " + response.statusCode());
        }

    }

    // setter

    public void addRilevazionePaziente(int valore, String tipo, int idPaziente) throws Exception {

        String url = API_URL + "add_rilevazione.php";

        LocalDateTime now = LocalDateTime.now();
        String dataOra = now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME); // formato 2025-05-24T10:15:30

        // Costruisci JSON manualmente
        String json = String.format(
                "{\"valore\": %d, \"tipo\": \"%s\", \"id_paziente\": %d, \"data\": \"%s\"}",
                valore, tipo, idPaziente, dataOra
        );

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            System.out.println("Rilevazione inviata con successo: " + response.body());
        } else {
            throw new RuntimeException("Errore invio rilevazione: HTTP " + response.statusCode());
        }

    }

    public void addSintomoPaziente(String descrizione, int idPaziente) throws Exception {

        String url = API_URL + "add_sintomo.php";

        LocalDateTime now = LocalDateTime.now();
        String dataOra = now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME); // formato 2025-05-24T10:15:30

        // Costruisci JSON manualmente
        String json = String.format(
                "{\"descrizione\":\"%s\", \"id_paziente\":%d, \"data\":\"%s\"}",
                descrizione, idPaziente, dataOra
        );

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            System.out.println("Sintomo inviata con successo: " + response.body());
        } else {
            throw new RuntimeException("Errore invio sintomo: HTTP " + response.statusCode());
        }

    }

    // parser dati

    private Utente parseUtenteWithDate(String json) {
        JsonObject obj = JsonParser.parseString(json).getAsJsonObject();

        Utente utente = new Utente();
        utente.setId(obj.get("id").getAsInt());
        utente.setNome(obj.get("nome").getAsString());
        utente.setCognome(obj.get("cognome").getAsString());
        utente.setRuolo(obj.get("ruolo").getAsString());
        utente.setCodFiscale(obj.get("cod_fiscale").getAsString());
        utente.setTelefono(obj.get("telefono").getAsString());
        utente.setIndirizzo(obj.get("indirizzo").getAsString());
        utente.setEmail(obj.get("email").getAsString());

        // supponendo che la data si chiami "dataNascita" nel JSON e sia in formato ISO yyyy-MM-dd
        if (obj.has("dataNascita") && !obj.get("dataNascita").isJsonNull()) {
            String dataStr = obj.get("dataNascita").getAsString();
            LocalDate data = LocalDate.parse(dataStr);
            utente.setDataNascita(data);
        }

        return utente;
    }

    private Utente[] parseUtentiManuale(String json) {
        JsonArray jsonArray = JsonParser.parseString(json).getAsJsonArray();
        List<Utente> utenti = new ArrayList<>();

        for (JsonElement elem : jsonArray) {
            JsonObject obj = elem.getAsJsonObject();

            Utente utente = new Utente();
            utente.setId(obj.get("id").getAsInt());
            utente.setNome(obj.get("nome").getAsString());
            utente.setCognome(obj.get("cognome").getAsString());
            utente.setRuolo(obj.get("ruolo").getAsString());
            utente.setCodFiscale(obj.get("cod_fiscale").getAsString());
            utente.setTelefono(obj.get("telefono").getAsString());
            utente.setIndirizzo(obj.get("indirizzo").getAsString());
            utente.setEmail(obj.get("email").getAsString());

            if (obj.has("dataNascita") && !obj.get("dataNascita").isJsonNull()) {
                String dataStr = obj.get("dataNascita").getAsString();
                utente.setDataNascita(LocalDate.parse(dataStr));
            }

            utenti.add(utente);
        }

        return utenti.toArray(new Utente[0]);
    }

    private Farmaco parseFarmacoFromJson(String json) {
        JsonObject obj = JsonParser.parseString(json).getAsJsonObject();

        String id = obj.has("id") ? obj.get("id").getAsString() : null;
        String nome = obj.has("nome") ? obj.get("nome").getAsString() : null;
        String descrizione = obj.has("descrizione") ? obj.get("descrizione").getAsString() : null;

        return new Farmaco(id, nome, descrizione);
    }

    private Farmaco[] parseFarmaciManuale(String json) {
        JsonArray jsonArray = JsonParser.parseString(json).getAsJsonArray();
        List<Farmaco> farmaci = new ArrayList<>();

        for (JsonElement elem : jsonArray) {
            JsonObject obj = elem.getAsJsonObject();

            String id = obj.has("id") ? obj.get("id").getAsString() : null;
            String nome = obj.has("nome") ? obj.get("nome").getAsString() : null;
            String descrizione = obj.has("descrizione") ? obj.get("descrizione").getAsString() : null;

            farmaci.add(new Farmaco(id, nome, descrizione));
        }

        return farmaci.toArray(new Farmaco[0]);
    }

    private Studio parseStudioFromJson(String json) {
        JsonObject obj = JsonParser.parseString(json).getAsJsonObject();

        String id = obj.has("id") ? obj.get("id").getAsString() : null;
        String nome = obj.has("nome") ? obj.get("nome").getAsString() : null;
        String indirizzo = obj.has("indirizzo") ? obj.get("indirizzo").getAsString() : null;

        return new Studio(id, nome, indirizzo);
    }

    private Sintomo[] parseSintomiManuale(String json) {
        JsonArray jsonArray = JsonParser.parseString(json).getAsJsonArray();
        List<Sintomo> sintomi = new ArrayList<>();

        for (JsonElement elem : jsonArray) {
            JsonObject obj = elem.getAsJsonObject();

            String id = obj.get("id").getAsString();
            String dataStr = obj.get("data").getAsString();
            dataStr = dataStr.replace(" ", "T");
            LocalDateTime data = LocalDateTime.parse(dataStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

            String descrizione = obj.get("descrizione").getAsString();
            String idPaziente = obj.get("id_paziente").getAsString();

            sintomi.add(new Sintomo(id, data, descrizione, idPaziente));
        }

        return sintomi.toArray(new Sintomo[0]);
    }

    private Rilevazione[] parseRilevazioniManuale(String json) {
        JsonArray jsonArray = JsonParser.parseString(json).getAsJsonArray();
        List<Rilevazione> rilevazioni = new ArrayList<>();

        for (JsonElement elem : jsonArray) {
            JsonObject obj = elem.getAsJsonObject();

            String id = obj.get("id").getAsString();
            String dataStr = obj.get("data").getAsString();
            dataStr = dataStr.replace(" ", "T");
            LocalDateTime data = LocalDateTime.parse(dataStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);


            String valore = obj.get("valore").getAsString();
            String tipo = obj.get("tipo").getAsString();
            String idPaziente = obj.get("id_paziente").getAsString();

            rilevazioni.add(new Rilevazione(id, data, valore, tipo, idPaziente));
        }

        return rilevazioni.toArray(new Rilevazione[0]);
    }


}
