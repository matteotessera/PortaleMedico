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

    public Utente[] getPazientiSenzaMedico() throws Exception {

        String url = API_URL + "get_pazienti_senza_medico.php";

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

    public Utente[] getPazienti() throws Exception {

        String url = API_URL + "get_pazienti.php";

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

    public Utente[] getMedici() throws Exception {

        String url = API_URL + "get_medici.php";

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

    public Terapia[] getTerapie() throws Exception {

        String url = API_URL + "get_terapie.php";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            String json = response.body();
            return parseTerapieManuale(json);
        } else {
            throw new RuntimeException("Errore nella chiamata HTTP: " + response.statusCode());
        }

    }

    public Terapia[] getTerapiePaziente(int idPaziente) throws Exception {

        String url = API_URL + "get_terapie_paziente.php?idPaziente=" + idPaziente ;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            String json = response.body();
            return parseTerapieManuale(json);
        } else {
            throw new RuntimeException("Errore nella chiamata HTTP: " + response.statusCode());
        }

    }

    public Terapia getTerapiaById(int id) throws Exception {

        String url = API_URL + "get_terapia_by_id.php?id=" + id ;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            String json = response.body();
            return parseTerapiaSingolaManuale(json);
        } else {
            throw new RuntimeException("Errore nella chiamata HTTP: " + response.statusCode());
        }

    }

    public AssociazioneFarmaco[] getAssociazioniFarmaciByTerapia(int idTerapia) throws Exception {

        String url = API_URL + "get_associazioni_farmaci_by_id_terapia.php?idTerapia=" + idTerapia ;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            String json = response.body();
            return parseAssociazioniFarmacoManuale(json);
        } else {
            throw new RuntimeException("Errore nella chiamata HTTP: " + response.statusCode());
        }

    }

    public Assunzione[] getAssunizoniPaziente(int idPaziente) throws Exception {

        String url = API_URL + "get_assunzioni_paziente.php?idPaziente=" + idPaziente ;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            String json = response.body();
            return parseAssunzioniManuale(json);
        } else {
            throw new RuntimeException("Errore nella chiamata HTTP: " + response.statusCode());
        }

    }

    public Assunzione[] getAssunzioniTerapia(int idTerapia) throws Exception {

        String url = API_URL + "get_assunzioni_terapia.php?idTerapia=" + idTerapia ;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            String json = response.body();
            return parseAssunzioniManuale(json);
        } else {
            throw new RuntimeException("Errore nella chiamata HTTP: " + response.statusCode());
        }

    }

    public Assunzione[] getAssunzioniAssociazioneFarmaco(int idAssociazioneFarmaco) throws Exception {

        String url = API_URL + "get_assunzioni_associazione_farmaco.php?idAssociazioneFarmaco=" + idAssociazioneFarmaco ;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            String json = response.body();
            return parseAssunzioniManuale(json);
        } else {
            throw new RuntimeException("Errore nella chiamata HTTP: " + response.statusCode());
        }

    }

    public Assunzione getAssunzioneById(int id) throws Exception {

        String url = API_URL + "get_assunzione_by_id.php?id=" + id ;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            String json = response.body();
            return parseAssunzioneManuale(json);
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

        post(url, json);
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

        post(url, json);

    }

    public void addFarmaco(String nome, String descrizione) throws Exception {

        String url = API_URL + "add_farmaco.php";

        String json = String.format(
                "{\"nome\":\"%s\", \"descrizione\":\"%s\"}",
                nome, descrizione
        );

        post(url, json);

    }

    public void addUtente(String password, String ruolo, String nome, String cognome, String codFiscale, LocalDate dataNascita, String email, String telefono, String indirizzo) throws Exception {
        String url = API_URL + "add_utente.php";

        // Converto la data di nascita in stringa (formato ISO yyyy-MM-dd)
        String dataNascitaStr = dataNascita.toString();

        String json = String.format(
                "{" +
                        "\"password\":\"%s\", " +
                        "\"ruolo\":\"%s\", " +
                        "\"nome\":\"%s\", " +
                        "\"cognome\":\"%s\", " +
                        "\"codFiscale\":\"%s\", " +
                        "\"dataNascita\":\"%s\", " +
                        "\"email\":\"%s\", " +
                        "\"telefono\":\"%s\", " +
                        "\"indirizzo\":\"%s\"" +
                        "}",
                password, ruolo, nome, cognome, codFiscale, dataNascitaStr, email, telefono, indirizzo
        );

        post(url, json);

    }

    public void addTerapia(LocalDate dataInizio, LocalDate dataFine, String note, int id_paziente) throws Exception {

        String url = API_URL + "add_terapia.php";

        String dataInizioStr = dataInizio.toString();

        String dataFineStr = dataFine.toString();

        String json = String.format(
                "{" +
                        "\"data_inizio\":\"%s\", " +
                        "\"data_fine\":\"%s\", " +
                        "\"note\":\"%s\", " +
                        "\"id_paziente\":%d" +
                        "}",
                dataInizioStr, dataFineStr, note, id_paziente
        );

        post(url, json);

    }

    public void updatePassword(int id, String password) throws Exception {

        String url = API_URL + "update_password.php";

        String json = String.format(
                "{\"id\":%d, \"password\":\"%s\"}",
                id, password
        );

        post(url, json);

    }

    public void assegnazioneMedico(int idMedico, int idPaziente) throws Exception {

        String url = API_URL + "assegnazione_medico.php";

        String json = String.format(
                "{\"idMedico\":%d, \"idPaziente\":%d}",
                idMedico, idPaziente
        );

        post(url, json);

    }

    public void associazioneFarmacoTerapia(int idTerapia, int idFarmaco, int numeroAssunzioni, int dose) throws Exception {

        String url = API_URL + "associazione_farmaco_terapia.php";

        String json = String.format(
                "{" +
                        "\"id_terapia\":%d, " +
                        "\"id_farmaco\":%d, " +
                        "\"numero_assunzioni\":%d, " +
                        "\"dose\":%d" +
                        "}",
                idTerapia, idFarmaco, numeroAssunzioni, dose
        );

        post(url, json);

    }

    public void addAssunzione(int idAssunzioneFarmaco, LocalDateTime data, String stato) throws Exception {

        String url = API_URL + "add_assunzione.php";

        String dataStr = data.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        String json = String.format(
                "{" +
                        "\"id_assunzione_farmaco\":%d, " +
                        "\"data\":\"%s\", " +
                        "\"stato\":\"%s\"" +
                        "}",
                idAssunzioneFarmaco, data, stato
        );

        post(url, json);

    }

    private void post(String url, String json) throws Exception {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            System.out.println("Oggetto inviato con successo: " + response.body());
        } else {
            throw new RuntimeException("Errore invio oggetto: HTTP " + response.statusCode());
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
        if (obj.has("data_nascita") && !obj.get("data_nascita").isJsonNull()) {
            String dataStr = obj.get("data_nascita").getAsString();
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

            if (obj.has("data_nascita") && !obj.get("data_nascita").isJsonNull()) {
                String dataStr = obj.get("data_nascita").getAsString();
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

    private Terapia[] parseTerapieManuale(String json) {

        JsonArray jsonArray = JsonParser.parseString(json).getAsJsonArray();
        List<Terapia> terapie = new ArrayList<>();

        for (JsonElement elem : jsonArray) {
            JsonObject obj = elem.getAsJsonObject();

            Terapia terapia = new Terapia();

            terapia.setId(obj.get("id").getAsInt());
            if (obj.has("data_inizio") && !obj.get("data_inizio").isJsonNull()) {
                String dataStr = obj.get("data_inizio").getAsString();
                terapia.setDataInizio(LocalDate.parse(dataStr));
            }
            if (obj.has("data_fine") && !obj.get("data_fine").isJsonNull()) {
                String dataStr = obj.get("data_fine").getAsString();
                terapia.setDataFine(LocalDate.parse(dataStr));
            }
            terapia.setNote(obj.get("note").getAsString());
            terapia.setIdPaziente(obj.get("id_paziente").getAsInt());

            terapie.add(terapia);
        }

        return terapie.toArray(new Terapia[0]);
    }

    private Terapia parseTerapiaSingolaManuale(String json) {

        JsonObject obj = JsonParser.parseString(json).getAsJsonObject();

        Terapia terapia = new Terapia();

        terapia.setId(obj.get("id").getAsInt());
        if (obj.has("data_inizio") && !obj.get("data_inizio").isJsonNull()) {
            String dataStr = obj.get("data_inizio").getAsString();
            terapia.setDataInizio(LocalDate.parse(dataStr));
        }
        if (obj.has("data_fine") && !obj.get("data_fine").isJsonNull()) {
            String dataStr = obj.get("data_fine").getAsString();
            terapia.setDataFine(LocalDate.parse(dataStr));
        }
        terapia.setNote(obj.get("note").getAsString());
        terapia.setIdPaziente(obj.get("id_paziente").getAsInt());

        return terapia;

    }

    private AssociazioneFarmaco[] parseAssociazioniFarmacoManuale(String json) {

        JsonArray jsonArray = JsonParser.parseString(json).getAsJsonArray();
        List<AssociazioneFarmaco> associazioniFarmaci = new ArrayList<>();

        for (JsonElement elem : jsonArray) {
            JsonObject obj = elem.getAsJsonObject();

            AssociazioneFarmaco associazioneFarmaco = new AssociazioneFarmaco();

            associazioneFarmaco.setId(obj.get("id").getAsInt());
            associazioneFarmaco.setIdTerapia(obj.get("id_terapia").getAsInt());
            associazioneFarmaco.setIdFarmaco(obj.get("id_farmaco").getAsInt());
            associazioneFarmaco.setNumeroAssunzioni(obj.get("numero_assunzioni").getAsInt());
            associazioneFarmaco.setDose(obj.get("dose").getAsInt());

            associazioniFarmaci.add(associazioneFarmaco);
        }

        return associazioniFarmaci.toArray(new AssociazioneFarmaco[0]);
    }

    private Assunzione[] parseAssunzioniManuale(String json) {

        JsonArray jsonArray = JsonParser.parseString(json).getAsJsonArray();
        List<Assunzione> assunzioni = new ArrayList<>();

        for (JsonElement elem : jsonArray) {
            JsonObject obj = elem.getAsJsonObject();

            Assunzione assunzione = new Assunzione();

            assunzione.setId(obj.get("id").getAsInt());
            if (obj.has("data") && !obj.get("data").isJsonNull()) {
                String dataStr = obj.get("data").getAsString();
                assunzione.setData(LocalDateTime.parse(dataStr));
            }
            assunzione.setIdAssociazioneFarmaco(obj.get("id_associazione_farmaco").getAsInt());
            assunzione.setStato(obj.get("stato").getAsString());
            assunzione.setDose(obj.get("dose").getAsInt());
            assunzione.setIdPaziente(obj.get("id_paziente").getAsInt());

            assunzioni.add(assunzione);
        }

        return assunzioni.toArray(new Assunzione[0]);

    }

    private Assunzione parseAssunzioneManuale(String json) {

        JsonObject obj = JsonParser.parseString(json).getAsJsonObject();

        Assunzione assunzione = new Assunzione();

        assunzione.setId(obj.get("id").getAsInt());
        if (obj.has("data") && !obj.get("data").isJsonNull()) {
            String dataStr = obj.get("data").getAsString();
            assunzione.setData(LocalDateTime.parse(dataStr));
        }
        assunzione.setIdAssociazioneFarmaco(obj.get("id_associazione_farmaco").getAsInt());
        assunzione.setStato(obj.get("stato").getAsString());
        assunzione.setDose(obj.get("dose").getAsInt());
        assunzione.setIdPaziente(obj.get("id_paziente").getAsInt());

        return assunzione;

    }

}
