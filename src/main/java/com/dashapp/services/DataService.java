package com.dashapp.services;

import com.dashapp.model.*;
import com.google.gson.*;
import org.json.JSONObject;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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



    // ---=== GETTER ===---



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

    public Utente[] getUtentiConTerapia() throws Exception {
        String url = API_URL + "get_pazienti_con_terapie.php";

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

        } else if (response.statusCode() == 404) {
            return new Utente[0];

        } else {
            throw new RuntimeException("Errore nella chiamata HTTP: " + response.statusCode());
        }

    }

    public Utente[] getPazientiAssegnati() throws Exception {

        String url = API_URL + "get_pazientiAssegnati.php";

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

    public Utente[] getPazientiByMedico(int idMedico) throws Exception {

        String url = API_URL + "get_pazienti_by_medico.php?idMedico=" + idMedico;

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

    public Utente[] GetPazientiConMedico() throws Exception {

        String url = API_URL + "get_pazienti_con_medico.php";

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

    public Farmaco getFarmacoByNome(String nome) throws Exception {
        String url = API_URL + "get_farmaco_by_nome.php?nome=" + URLEncoder.encode(nome, StandardCharsets.UTF_8);
        System.out.println("URL chiamato: " + url);


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

    public InfoPaziente getInfoPazienteByID(int idPaziente) {
        try {
            String url = API_URL + "get_info_paziente_by_id.php?id_paziente=" + idPaziente;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.body() == null || response.body().isEmpty()) {
                return null;
            }

            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(response.body(), JsonObject.class);

            if (jsonObject.has("success") && jsonObject.get("success").getAsBoolean()) {
                JsonArray dataArray = jsonObject.getAsJsonArray("data");

                if (dataArray != null && dataArray.size() > 0) {
                    JsonObject infoJson = dataArray.get(0).getAsJsonObject();

                    InfoPaziente info = new InfoPaziente();
                    info.setId(infoJson.get("id").getAsInt());
                    info.setIdPaziente(infoJson.get("id_paziente").getAsInt());

                    // Qui recuperiamo "note" come JsonElement, non come stringa
                    JsonElement noteElement = infoJson.get("note");
                    info.setNote(noteElement);  // Assumendo che tu abbia un campo JsonElement noteElement in InfoPaziente

                    return info;
                } else {
                    return null;
                }
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
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

    public Sintomo[] getSintomiById(int idPaziente) throws Exception {

        String url = API_URL + "get_sintomi_by_idPaziente.php?id=" + idPaziente;

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

    public Sintomo getSintomoById(int id) throws Exception {

        String url = API_URL + "get_sintomo_by_id.php?id=" + id;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            String json = response.body();
            return parseSintomoManuale(json);
        } else {
            throw new RuntimeException("Errore nella chiamata HTTP: " + response.statusCode());
        }
    }


    public Rilevazione[] getRilevazioniById(int idPaziente) throws Exception {

        String url = API_URL + "get_rilevazioni_by_idPaziente.php?id=" + idPaziente;

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

    public Rilevazione getRilevazioneById(int id) throws Exception {

        String url = API_URL + "get_rilevazione_by_id.php?id=" + id;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            String json = response.body();
            return parseRilevazioneManuale(json);
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

    public Terapia[] getTerapieMedico(int idMedico) throws Exception {

        String url = API_URL + "get_terapie_medico.php?id_medico=" + idMedico ;

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

    public AssociazioneFarmaco getAssociazioneFarmacoById(int id) throws Exception {

        String url = API_URL + "get_associazione_farmaco_by_id.php?id=" + id;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            String json = response.body();
            return parseAssociazioneFarmacoManuale(json);
        } else {
            throw new RuntimeException("Errore nella chiamata HTTP: " + response.statusCode());
        }
    }

    public Assunzione[] getAssunzioniPaziente(int idPaziente) throws Exception {

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

    public Assunzione getAssunzioneById(int idAssunzione) throws Exception {
        String url = API_URL + "get_assunzione_by_id.php?id=" + idAssunzione;

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

    public int getNumeroTerapieMedico(int idMedico) throws Exception {

        return getTerapieMedico(idMedico).length;
    }

    public SintomoConcomitante[] getSintomiConcomitantiByPaziente(int pazienteId) throws Exception {
        String url = API_URL + "get_sintomo_concomitante_by_idPaziente.php?paziente_id=" + pazienteId;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            String json = response.body();
            return parseSintomiConcomitantiManuale(json);
        } else {
            throw new RuntimeException("Errore nella chiamata HTTP: " + response.statusCode());
        }
    }

    public TerapiaConcomitante[] getTerapieConcomitantiByPaziente(int pazienteId) throws Exception {
        String url = API_URL + "get_terapie_concomitanti_by_idPaziente.php?paziente_id=" + pazienteId;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            String json = response.body();
            return parseTerapieConcomitantiManuale(json);
        } else {
            throw new RuntimeException("Errore nella chiamata HTTP: " + response.statusCode());
        }
    }

    public Messaggio[] getMessaggiByIdSender(int idSender) throws Exception {
        String url = API_URL + "get_messaggi_by_idSender.php?id_sender=" + idSender;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return parseMessaggiManuale(response.body());
        } else {
            throw new RuntimeException("Errore nel recupero messaggi: " + response.statusCode());
        }
    }

    public Messaggio[] getMessaggiByIdReceiver(int idReceiver) throws Exception {
        String url = API_URL + "get_messaggi_by_idReceiver.php?id_receiver=" + idReceiver;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return parseMessaggiManuale(response.body());
        } else {
            throw new RuntimeException("Errore nel recupero messaggi: " + response.statusCode());
        }
    }

    public Messaggio[] getMessaggi() throws Exception {
        String url = API_URL + "get_messaggi.php";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return parseMessaggiManuale(response.body());
        } else {
            throw new RuntimeException("Errore nel recupero messaggi: " + response.statusCode());
        }
    }

    public Patologia[] getPatologieByPaziente(int pazienteId) throws Exception {
        String url = API_URL + "get_patologie_by_idPaziente.php?paziente_id=" + pazienteId;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            String json = response.body();
            return parsePatologieManuale(json);
        } else {
            throw new RuntimeException("Errore nella chiamata HTTP: " + response.statusCode());
        }
    }

    public Accesso[] getAccessi() throws Exception {
        String url = API_URL + "get_accessi.php";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return parseAccessiManuale(response.body());
        } else {
            throw new RuntimeException("Errore nel recupero accessi: " + response.statusCode());
        }
    }

    public Accesso[] getAccessiByUtente(int idUtente) throws Exception {
        String url = API_URL + "get_accessi_by_id_utente.php?id_utente=" + idUtente;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return parseAccessiManuale(response.body());
        } else {
            throw new RuntimeException("Errore nel recupero accessi per utente " + idUtente + ": " + response.statusCode());
        }
    }




    // ---=== SETTER ===---

    public void addInfoPaziente(int idPaziente, String note) throws Exception {
        String url = API_URL + "add_info_paziente.php";

        JsonObject json = new JsonObject();
        json.add("note", JsonParser.parseString(note));  // qui note è JSON, viene parsato
        json.addProperty("id_paziente", idPaziente);

        post(url, json.toString());
    }

    public void addRilevazionePaziente(double valore, String tipo, int idPaziente, String pasto) throws Exception {

        String url = API_URL + "add_rilevazione.php";

        LocalDateTime now = LocalDateTime.now();
        String dataOra = now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME); // formato 2025-05-24T10:15:30
        String valoreFormattato = String.format(Locale.US, "%f", valore);

        // Costruisci JSON manualmente

        String json = String.format(Locale.US,
                "{\"valore\": %.6f, \"tipo\": \"%s\", \"id_paziente\": %d, \"data\": \"%s\", \"pasto\": \"%s\"}",
                valore, tipo, idPaziente, dataOra, pasto
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

    public void addFarmaco(String nome, String descrizione) throws Exception {

        String url = API_URL + "add_farmaco.php";

        String json = String.format(
                "{\"nome\":\"%s\", \"descrizione\":\"%s\"}",
                nome, descrizione
        );

        post(url, json);
    }

    public void addUtente(String password, String ruolo, String nome, String cognome, String codFiscale, LocalDate dataNascita, String email, String telefono, String indirizzo, String genere) throws Exception {
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
                        "\"genere\":\"%s\", " +
                        "\"indirizzo\":\"%s\"" +
                        "}",
                password, ruolo, nome, cognome, codFiscale, dataNascitaStr, email, telefono, genere, indirizzo
        );

        post(url, json);
    }

    public void addTerapia(
            LocalDate dataInizio,
            LocalDate dataFine,
            String note,
            int idPaziente,
            int idFarmaco,
            int numeroAssunzioni,
            int dose
    ) throws Exception {

        String url = API_URL + "add_terapia.php";

        String dataInizioStr = dataInizio.toString();
        String dataFineStr = dataFine.toString();

        // Costruisco il JSON senza il campo "indicazioni"
        String json = String.format(
                "{" +
                        "\"id_paziente\": %d, " +
                        "\"id_farmaco\": %d, " +
                        "\"numero_assunzioni\": %d, " +
                        "\"dose\": %d, " +
                        "\"data_inizio\": \"%s\", " +
                        "\"data_fine\": \"%s\", " +
                        "\"note\": \"%s\"" +
                        "}",
                idPaziente,
                idFarmaco,
                numeroAssunzioni,
                dose,
                dataInizioStr,
                dataFineStr,
                note.replace("\"", "\\\"")
        );

        post(url, json);
    }

    public int addSintomoConcomitante(int idPaziente, String descrizione, LocalDate dataInizio, String frequenza, String note) throws Exception {

        String url = API_URL + "add_sintomo_concomitante.php";

        // Formatta dataInizio in ISO_LOCAL_DATE_TIME
        String dataOra = dataInizio.toString();

        // Costruisci JSON manualmente con l'ordine corretto
        String json = String.format(
                "{\"paziente_id\":%d, \"descrizione\":\"%s\", \"data_inizio\":\"%s\", \"frequenza\":\"%s\", \"note\":\"%s\"}",
                idPaziente, descrizione, dataOra, frequenza,
                note != null ? note : ""
        );

        post(url, json);

        return idPaziente;
    }

    public void addMessaggio(int idSender, int idReceiver, LocalDate dataInvio, LocalTime oraInvio,
                             String oggetto, String corpo, char tipo, boolean letto) throws Exception {

        String url = API_URL + "add_messaggio.php";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String oraFormattata = oraInvio.format(formatter);

        JsonObject messaggio = new JsonObject();
        messaggio.addProperty("id_sender", idSender);
        messaggio.addProperty("id_receiver", idReceiver);
        messaggio.addProperty("data_invio", dataInvio.toString());
        messaggio.addProperty("ora_invio", oraFormattata);
        messaggio.addProperty("oggetto", oggetto);
        messaggio.addProperty("corpo", corpo);
        messaggio.addProperty("tipo", String.valueOf(tipo));
        messaggio.addProperty("letto", letto ? "true" : "false");

        String json = messaggio.toString();

        System.out.println(json);

        post(url, json);
    }

    public void addPatologia(int pazienteId, String nomePatologia, LocalDate dataDiagnosi, String note) throws Exception {
        String url = API_URL + "add_patologia.php";

        String dataDiagnosiStr = dataDiagnosi != null ? dataDiagnosi.toString() : "";

        String json = String.format(
                "{\"paziente_id\":%d, \"nome_patologia\":\"%s\", \"data_diagnosi\":\"%s\", \"note\":\"%s\"}",
                pazienteId, nomePatologia, dataDiagnosiStr, note != null ? note : ""
        );

        post(url, json);
    }

    public void addAccesso(int idUtente) throws Exception {
        String url = API_URL + "add_accesso.php";

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dataAccessoStr = now.format(formatter);

        String json = String.format(
                "{\"data\":\"%s\", \"id_utente\":%d}",
                dataAccessoStr, idUtente
        );

        post(url, json);
    }

    public void addTerapiaConcomitante(int pazienteId, String nomeFarmaco, LocalDate dataInizio, LocalDate dataFine, String frequenza, String dose, String indicazioni) throws Exception {

        String url = API_URL + "add_terapia_concomitante.php";

        // Converte le date in stringa formato ISO yyyy-MM-dd
        String dataInizioStr = dataInizio.toString();
        String dataFineStr = dataFine.toString();

        // Se indicazioni è null, lo mettiamo come stringa vuota nel JSON
        String indicazioniSafe = (indicazioni != null) ? indicazioni : "";

        // Costruisci JSON manualmente
        String json = String.format(
                "{\"paziente_id\":%d, \"farmaco\":\"%s\", \"data_inizio\":\"%s\", \"data_fine\":\"%s\", \"frequenza\":\"%s\", \"dose\":\"%s\", \"indicazioni\":\"%s\"}",
                pazienteId, nomeFarmaco, dataInizioStr, dataFineStr, frequenza, dose, indicazioniSafe
        );

        post(url, json);
    }



    // ---=== UPDATE ===---

    public void updateInfoPaziente(int id, int idPaziente, String noteJson) throws Exception {
        String url = API_URL + "update_info_paziente.php";

        // Costruiamo il JSON con id, id_paziente e note (noteJson è già JSON valido, senza virgolette extra)
        String json = String.format(
                "{\"id\": %d, \"id_paziente\": %d, \"note\": %s}",
                id, idPaziente, noteJson
        );

        post(url, json);
    }
    public void updatePatologia(int id, int pazienteId, String nomePatologia, LocalDate dataDiagnosi, String note) throws Exception {
        String url = API_URL + "update_patologia.php";

        String dataDiagnosiStr = dataDiagnosi != null ? dataDiagnosi.toString() : "";

        String json = String.format(
                "{\"id\":%d, \"paziente_id\":%d, \"nome_patologia\":\"%s\", \"data_diagnosi\":\"%s\", \"note\":\"%s\"}",
                id, pazienteId, nomePatologia, dataDiagnosiStr, note != null ? note : ""
        );

        post(url, json);
    }

    public void updateTerapiaConcomitante(int id, int pazienteId, String farmaco, LocalDate dataInizio, LocalDate dataFine, String frequenza, String dose, String indicazioni) throws Exception {
        String url = API_URL + "update_terapia_concomitante.php";

        String dataInizioStr = dataInizio.toString(); // yyyy-MM-dd
        String dataFineStr = dataFine.toString();

        String indicazioniSafe = (indicazioni != null) ? indicazioni : "";

        String json = String.format(
                "{\"id\":%d, \"paziente_id\":%d, \"farmaco\":\"%s\", \"data_inizio\":\"%s\", \"data_fine\":\"%s\", \"frequenza\":\"%s\", \"dose\":\"%s\", \"indicazioni\":\"%s\"}",
                id, pazienteId, farmaco, dataInizioStr, dataFineStr, frequenza, dose, indicazioniSafe
        );

        post(url, json);
    }

    public void updateSintomoConcomitante(int id, int pazienteId, String descrizione, LocalDate dataInizio, String frequenza, String note) throws Exception {
        String url = API_URL + "update_sintomo_concomitante.php";

        String dataInizioStr = dataInizio.toString(); // yyyy-MM-dd

        String json = String.format(
                "{\"id\":%d, \"paziente_id\":%d, \"descrizione\":\"%s\", \"data_inizio\":\"%s\", \"frequenza\":\"%s\", \"note\":\"%s\"}",
                id, pazienteId, descrizione, dataInizioStr, frequenza, note != null ? note : ""
        );

        post(url, json);
    }

    public void updateSintomo(int idSintomo, String descrizione, int idPaziente, LocalDateTime data) throws Exception {

        String url = API_URL + "update_sintomo.php";

        String dataOra = data.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME); // Esempio: 2025-05-24T10:15:30

        // Costruisci JSON manualmente
        String json = String.format(
                "{" +
                        "\"id\":%d, " +
                        "\"descrizione\":\"%s\", " +
                        "\"id_paziente\":%d, " +
                        "\"data\":\"%s\"" +
                        "}",
                idSintomo, descrizione, idPaziente, dataOra
        );

        post(url, json);
    }

    public void updateAssunzione(int idAssunzione, LocalDateTime data, String stato) throws Exception {
        String url = API_URL + "update_assunzione.php";
        String dataStr = data.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String json = String.format(
                "{" +
                        "\"id\":%d, " +
                        "\"data\":\"%s\", " +
                        "\"stato\":\"%s\"" +
                        "}",
                idAssunzione, dataStr, stato
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

    public void updateFarmaco(int id, String nome, String descrizione) throws Exception {
        String url = API_URL + "update_farmaco.php";
        String json = String.format(
                "{\"id\":%d, \"nome\":\"%s\", \"descrizione\":\"%s\"}",
                id, nome, descrizione
        );
        post(url, json);
    }

    public void updateRilevazione(int idRilevazione, double valore, String tipo, int idPaziente, LocalDateTime data, String pasto) throws Exception {
        String url = API_URL + "update_rilevazione.php";
        String dataOra = data.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME); // formato 2025-05-24T10:15:30

        String json = String.format(Locale.US,
                "{" +
                        "\"id\": %d, " +
                        "\"valore\": %.6f, " +
                        "\"tipo\": \"%s\", " +
                        "\"id_paziente\": %d, " +
                        "\"data\": \"%s\", " +
                        "\"pasto\": \"%s\"" +
                        "}",
                idRilevazione, valore, tipo, idPaziente, dataOra, pasto
        );
        post(url, json);
    }

    public void updateUtente(int id, String password, String ruolo, String nome, String cognome, LocalDate dataNascita, String email, String telefono, String indirizzo, String genere, String codFiscale) throws Exception {
        String url = API_URL + "update_utente.php";

        String dataNascitaStr = dataNascita.toString();

        String json = String.format(
                "{" +
                        "\"id\": %d, " +
                        "\"password\": \"%s\", " +
                        "\"ruolo\": \"%s\", " +
                        "\"nome\": \"%s\", " +
                        "\"cognome\": \"%s\", " +
                        "\"codFiscale\": \"%s\", " +
                        "\"dataNascita\": \"%s\", " +
                        "\"email\": \"%s\", " +
                        "\"telefono\": \"%s\", " +
                        "\"genere\": \"%s\", " +
                        "\"indirizzo\": \"%s\"" +
                        "}",
                id, password, ruolo, nome, cognome, codFiscale, dataNascitaStr, email, telefono, genere, indirizzo
        );

        post(url, json);
    }

    public void updateUtenteSenzaPw(int id, String ruolo, String nome, String cognome, LocalDate dataNascita, String email, String telefono, String indirizzo, String genere, String codFiscale) throws Exception {
        String url = API_URL + "update_utente.php";

        String dataNascitaStr = dataNascita.toString();

        String json = String.format(
                "{" +
                        "\"id\": %d, " +
                        "\"ruolo\": \"%s\", " +
                        "\"nome\": \"%s\", " +
                        "\"cognome\": \"%s\", " +
                        "\"codFiscale\": \"%s\", " +
                        "\"dataNascita\": \"%s\", " +
                        "\"email\": \"%s\", " +
                        "\"telefono\": \"%s\", " +
                        "\"genere\": \"%s\", " +
                        "\"indirizzo\": \"%s\"" +
                        "}",
                id, ruolo, nome, cognome, codFiscale, dataNascitaStr, email, telefono, genere, indirizzo
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
            throw new RuntimeException("Errore invio oggetto: HTTP " + response.statusCode() + json );
        }
    }




    // ---=== DELETE ===---

    public void deleteInfoPaziente(int idInfo) throws Exception {
        String url = API_URL + "delete_info_paziente.php";

        String json = String.format(
                "{\"id\":%d}",
                idInfo
        );

        post(url, json);
    }

    public void deleteTerapiaConcomitante(int id) throws Exception {
        String url = API_URL + "delete_terapia_concomitante.php";

        String json = String.format("{\"id\":%d}", id);

        post(url, json);
    }

    public void deleteMessaggio(int id) throws Exception {
        String url = API_URL + "delete_messaggio.php?id=" + id;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Errore nella cancellazione: " + response.statusCode());
        }
    }

    public void deleteFarmaco(int idFarmaco) throws Exception {

        String url = API_URL + "delete_farmaco.php";

        String json = String.format(
                "{\"id\": %d}", idFarmaco
        );

        delete(url, json);

    }

    public void deletePatologia(int id) throws Exception {
        String url = API_URL + "delete_patologia.php";

        String json = String.format("{\"id\":%d}", id);

        post(url, json);
    }

    public void deleteAssegnazioneMedico(int idPaziente) throws Exception {

        String url = API_URL + "delete_assegnazione_medico.php";

        String json = String.format(
                "{\"id_paziente\": %d}", idPaziente
        );

        delete(url, json);

    }

    public void deleteTerapia(String idTerapia) throws Exception {

        String url = API_URL + "delete_terapia.php";

        String json = String.format(
                "{\"id\": %d}", idTerapia
        );

        delete(url, json);
    }

    public void deleteSintomoConcomitante(int id) throws Exception {
        String url = API_URL + "delete_sintomo_concomitante.php";

        String json = String.format("{\"id\":%d}", id);

        post(url, json);
    }


    private void delete(String url, String json) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            System.out.println("Cancellazione avvenuta con successo: " + response.body());
        } else {
            throw new RuntimeException("Errore nella cancellazione dell'oggetto: HTTP " + response.statusCode() + json);
        }
    }

    public boolean verificaPassword(int id, String passwordAttuale) throws Exception {
        String url = API_URL + "verifica_password.php";

        // Crea JSON
        JSONObject requestJson = new JSONObject();
        requestJson.put("id", id);
        requestJson.put("passwordAttuale", passwordAttuale);

        // Costruisci richiesta POST
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestJson.toString()))
                .build();

        // Manda richiesta e ricevi risposta
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return true; // password corretta
        } else if (response.statusCode() == 401) {
            return false; // password errata
        } else {
            throw new RuntimeException("Errore nella chiamata HTTP: " + response.statusCode());
        }
    }



    // ---=== PARSER DATI ===---

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
        utente.setGenere(obj.get("genere").getAsString());

        // supponendo che la data si chiami "dataNascita" nel JSON e sia in formato ISO yyyy-MM-dd
        if (obj.has("data_nascita") && !obj.get("data_nascita").isJsonNull()) {
            String dataStr = obj.get("data_nascita").getAsString();
            LocalDate data = LocalDate.parse(dataStr);
            utente.setDataNascita(data);
        }

        return utente;
    }

    private SintomoConcomitante parseSintomoConcomitante(String json) {
        JsonObject obj = JsonParser.parseString(json).getAsJsonObject();

        SintomoConcomitante s = new SintomoConcomitante();
        s.setId(obj.get("id").getAsInt());
        s.setPaziente_id(obj.get("paziente_id").getAsInt());
        s.setDescrizione(obj.get("descrizione").getAsString());
        s.setFrequenza(obj.get("frequenza").getAsString());

        if (obj.has("data_inizio") && !obj.get("data_inizio").isJsonNull()) {
            String dataStr = obj.get("data_inizio").getAsString();
            LocalDate data = LocalDate.parse(dataStr);
            s.setDataInizio(data);
        }
        if (obj.has("note") && !obj.get("note").isJsonNull()) {
            s.setNote(obj.get("note").getAsString());
        }

        return s;
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
            utente.setGenere(obj.get("genere").getAsString());

            if (obj.has("data_nascita") && !obj.get("data_nascita").isJsonNull()) {
                String dataStr = obj.get("data_nascita").getAsString();
                utente.setDataNascita(LocalDate.parse(dataStr));
            }

            utenti.add(utente);
        }

        return utenti.toArray(new Utente[0]);
    }


    private InfoPaziente[] parseInfoPazienteManuale(String json) {
        JsonArray jsonArray = JsonParser.parseString(json).getAsJsonArray();
        List<InfoPaziente> infos = new ArrayList<>();

        for (JsonElement elem : jsonArray) {
            JsonObject obj = elem.getAsJsonObject();

            InfoPaziente info = new InfoPaziente();
            info.setId(obj.get("id").getAsInt());
            info.setIdPaziente(obj.get("id_paziente").getAsInt());
            info.setNote(obj.get("note"));

            infos.add(info);
        }
        return infos.toArray(new InfoPaziente[0]);
    }

    private InfoPaziente parseInfoPazienteSingolo(String json) {
        JsonObject obj = JsonParser.parseString(json).getAsJsonObject();

        InfoPaziente info = new InfoPaziente();
        info.setId(obj.get("id").getAsInt());
        info.setIdPaziente(obj.get("id_paziente").getAsInt());
        info.setNote(obj.get("note"));

        return info;
    }


    private SintomoConcomitante[] parseSintomiConcomitantiManuale(String json) {
        JsonArray jsonArray = JsonParser.parseString(json).getAsJsonArray();
        List<SintomoConcomitante> sintomi = new ArrayList<>();

        for (JsonElement elem : jsonArray) {
            JsonObject obj = elem.getAsJsonObject();

            SintomoConcomitante s = new SintomoConcomitante();
            s.setId(obj.get("id").getAsInt());
            s.setPaziente_id(obj.get("paziente_id").getAsInt());
            s.setDescrizione(obj.get("descrizione").getAsString());
            s.setFrequenza(obj.get("frequenza").getAsString());

            if (obj.has("data_inizio") && !obj.get("data_inizio").isJsonNull()) {
                String dataStr = obj.get("data_inizio").getAsString();
                s.setDataInizio(LocalDate.parse(dataStr));
            }

            if (obj.has("note") && !obj.get("note").isJsonNull()) {
                s.setNote(obj.get("note").getAsString());
            }

            sintomi.add(s);
        }

        return sintomi.toArray(new SintomoConcomitante[0]);
    }

    private Messaggio parseMessaggio(String json) {
        JsonObject obj = JsonParser.parseString(json).getAsJsonObject();

        Messaggio m = new Messaggio();
        m.setId(obj.get("id").getAsInt());
        m.setId_Sender(obj.get("id_sender").getAsInt());

        if (obj.has("dataInvio") && !obj.get("dataInvio").isJsonNull()) {
            m.setDataInvio(LocalDate.parse(obj.get("dataInvio").getAsString()));
        }

        if (obj.has("oraInvio") && !obj.get("oraInvio").isJsonNull()) {
            m.setOrarioInvio(LocalTime.parse(obj.get("oraInvio").getAsString()));
        }

        m.setOggetto(obj.get("oggetto").getAsString());
        m.setCorpo(obj.get("corpo").getAsString());
        m.setTipo(obj.get("tipo").getAsString().charAt(0));

        if (obj.has("letta") && !obj.get("letta").isJsonNull()) {
            m.setLetto(obj.get("letta").getAsString().equalsIgnoreCase("true"));
        }

        return m;
    }

    private Messaggio[] parseMessaggiManuale(String json) {
        JsonArray jsonArray = JsonParser.parseString(json).getAsJsonArray();
        List<Messaggio> messaggi = new ArrayList<>();

        for (JsonElement elem : jsonArray) {
            JsonObject obj = elem.getAsJsonObject();

            Messaggio m = new Messaggio();
            m.setId(obj.get("id").getAsInt());
            m.setId_Sender(obj.get("id_sender").getAsInt());
            m.setId_receiver(obj.get("id_receiver").getAsInt());


            if (obj.has("data_invio") && !obj.get("data_invio").isJsonNull()) {
                m.setDataInvio(LocalDate.parse(obj.get("data_invio").getAsString()));
            }

            if (obj.has("ora_invio") && !obj.get("ora_invio").isJsonNull()) {
                m.setOrarioInvio(LocalTime.parse(obj.get("ora_invio").getAsString()));
            }

            m.setOggetto(obj.get("oggetto").getAsString());
            m.setCorpo(obj.get("corpo").getAsString());
            m.setTipo(obj.get("tipo").getAsString().charAt(0));

            if (obj.has("letta") && !obj.get("letta").isJsonNull()) {
                m.setLetto(obj.get("letta").getAsString().equalsIgnoreCase("true"));
            }

            messaggi.add(m);
        }

        return messaggi.toArray(new Messaggio[0]);
    }

    private TerapiaConcomitante parseTerapiaConcomitante(String json) {
        JsonObject obj = JsonParser.parseString(json).getAsJsonObject();

        TerapiaConcomitante t = new TerapiaConcomitante();
        t.setId(obj.get("id").getAsInt());
        t.setPaziente_id(obj.get("paziente_id").getAsInt());
        t.setFarmaco(obj.get("farmaco").getAsString());

        if (obj.has("data_inizio") && !obj.get("data_inizio").isJsonNull()) {
            t.setDataInizio(LocalDate.parse(obj.get("data_inizio").getAsString()));
        }
        if (obj.has("data_fine") && !obj.get("data_fine").isJsonNull()) {
            t.setDataFine(LocalDate.parse(obj.get("data_fine").getAsString()));
        }
        t.setFrequenza(obj.get("frequenza").getAsString());
        t.setDose(obj.get("dose").getAsString());

        if (obj.has("indicazioni") && !obj.get("indicazioni").isJsonNull()) {
            t.setIndicazioni(obj.get("indicazioni").getAsString());
        }

        return t;
    }


    private TerapiaConcomitante[] parseTerapieConcomitantiManuale(String json) {
        JsonArray jsonArray = JsonParser.parseString(json).getAsJsonArray();
        List<TerapiaConcomitante> terapie = new ArrayList<>();

        for (JsonElement elem : jsonArray) {
            JsonObject obj = elem.getAsJsonObject();

            TerapiaConcomitante t = new TerapiaConcomitante();
            t.setId(obj.get("id").getAsInt());
            t.setPaziente_id(obj.get("paziente_id").getAsInt());
            t.setFarmaco(obj.get("farmaco").getAsString());

            if (obj.has("data_inizio") && !obj.get("data_inizio").isJsonNull()) {
                t.setDataInizio(LocalDate.parse(obj.get("data_inizio").getAsString()));
            }
            if (obj.has("data_fine") && !obj.get("data_fine").isJsonNull()) {
                t.setDataFine(LocalDate.parse(obj.get("data_fine").getAsString()));
            }
            t.setFrequenza(obj.get("frequenza").getAsString());
            t.setDose(obj.get("dose").getAsString());

            if (obj.has("indicazioni") && !obj.get("indicazioni").isJsonNull()) {
                t.setIndicazioni(obj.get("indicazioni").getAsString());
            }

            terapie.add(t);
        }

        return terapie.toArray(new TerapiaConcomitante[0]);
    }

    private Patologia[] parsePatologieManuale(String json) {
        JsonArray jsonArray = JsonParser.parseString(json).getAsJsonArray();
        List<Patologia> patologie = new ArrayList<>();

        for (JsonElement elem : jsonArray) {
            JsonObject obj = elem.getAsJsonObject();

            Patologia p = new Patologia();
            p.setId(obj.get("id").getAsInt());
            p.setPazienteId(obj.get("paziente_id").getAsInt());
            p.setNomePatologia(obj.get("nome_patologia").getAsString());

            if (obj.has("data_diagnosi") && !obj.get("data_diagnosi").isJsonNull()) {
                String dataStr = obj.get("data_diagnosi").getAsString();
                p.setDataDiagnosi(LocalDate.parse(dataStr));
            }

            if (obj.has("note") && !obj.get("note").isJsonNull()) {
                p.setNote(obj.get("note").getAsString());
            }

            patologie.add(p);
        }

        return patologie.toArray(new Patologia[0]);
    }

    private Patologia parsePatologia(String json) {
        JsonObject obj = JsonParser.parseString(json).getAsJsonObject();

        Patologia p = new Patologia();
        p.setId(obj.get("id").getAsInt());
        p.setPazienteId(obj.get("paziente_id").getAsInt());
        p.setNomePatologia(obj.get("nome_patologia").getAsString());

        if (obj.has("data_diagnosi") && !obj.get("data_diagnosi").isJsonNull()) {
            String dataStr = obj.get("data_diagnosi").getAsString();
            p.setDataDiagnosi(LocalDate.parse(dataStr));
        }

        if (obj.has("note") && !obj.get("note").isJsonNull()) {
            p.setNote(obj.get("note").getAsString());
        }

        return p;
    }


    private Farmaco parseFarmacoFromJson(String json) {
        JsonObject obj = JsonParser.parseString(json).getAsJsonObject();

        int id = obj.has("id") ? obj.get("id").getAsInt() : -1;
        String nome = obj.has("nome") ? obj.get("nome").getAsString() : null;
        String descrizione = obj.has("descrizione") ? obj.get("descrizione").getAsString() : null;

        return new Farmaco(id, nome, descrizione);
    }

    private Farmaco[] parseFarmaciManuale(String json) {
        JsonArray jsonArray = JsonParser.parseString(json).getAsJsonArray();
        List<Farmaco> farmaci = new ArrayList<>();

        for (JsonElement elem : jsonArray) {
            JsonObject obj = elem.getAsJsonObject();

            int id = obj.has("id") ? obj.get("id").getAsInt() : -1;
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

    private Sintomo parseSintomoManuale(String json) {
        JsonObject obj = JsonParser.parseString(json).getAsJsonObject();

        String id = obj.get("id").getAsString();
        String dataStr = obj.get("data").getAsString();
        dataStr = dataStr.replace(" ", "T");
        LocalDateTime data = LocalDateTime.parse(dataStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        String descrizione = obj.get("descrizione").getAsString();
        String idPaziente = obj.get("id_paziente").getAsString();

        return new Sintomo(id, data, descrizione, idPaziente);
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

            String pasto = obj.get("pasto").getAsString();

            rilevazioni.add(new Rilevazione(id, data, valore, tipo, idPaziente, pasto));
        }

        return rilevazioni.toArray(new Rilevazione[0]);
    }

    private Rilevazione parseRilevazioneManuale(String json) {
        JsonObject obj = JsonParser.parseString(json).getAsJsonObject();

        String id = obj.get("id").getAsString();
        String dataStr = obj.get("data").getAsString();
        dataStr = dataStr.replace(" ", "T");
        LocalDateTime data = LocalDateTime.parse(dataStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        String valore = obj.get("valore").getAsString();
        String tipo = obj.get("tipo").getAsString();
        String idPaziente = obj.get("id_paziente").getAsString();
        String pasto = obj.get("pasto").getAsString();

        return new Rilevazione(id, data, valore, tipo, idPaziente, pasto);
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

    private AssociazioneFarmaco parseAssociazioneFarmacoManuale(String json) {

        JsonObject obj = JsonParser.parseString(json).getAsJsonObject();

        AssociazioneFarmaco associazioneFarmaco = new AssociazioneFarmaco();

        associazioneFarmaco.setId(obj.get("id").getAsInt());
        associazioneFarmaco.setIdTerapia(obj.get("id_terapia").getAsInt());
        associazioneFarmaco.setIdFarmaco(obj.get("id_farmaco").getAsInt());
        associazioneFarmaco.setNumeroAssunzioni(obj.get("numero_assunzioni").getAsInt());
        associazioneFarmaco.setDose(obj.get("dose").getAsInt());

        return associazioneFarmaco;

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
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                assunzione.setData(LocalDateTime.parse(dataStr, formatter));
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
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            assunzione.setData(LocalDateTime.parse(dataStr, formatter));
        }
        assunzione.setIdAssociazioneFarmaco(obj.get("id_associazione_farmaco").getAsInt());
        assunzione.setStato(obj.get("stato").getAsString());
        assunzione.setDose(obj.get("dose").getAsInt());
        assunzione.setIdPaziente(obj.get("id_paziente").getAsInt());

        return assunzione;

    }

    private Accesso[] parseAccessiManuale(String json) {
        JsonArray jsonArray = JsonParser.parseString(json).getAsJsonArray();
        List<Accesso> accessi = new ArrayList<>();

        for (JsonElement elem : jsonArray) {
            JsonObject obj = elem.getAsJsonObject();

            Accesso a = new Accesso();
            a.setId(obj.get("id").getAsInt());
            a.setIdUtente(obj.get("id_utente").getAsInt());

            if (obj.has("data") && !obj.get("data").isJsonNull()) {
                String dataStr = obj.get("data").getAsString();
                // supponiamo formato "yyyy-MM-dd HH:mm:ss"
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                a.setData(LocalDateTime.parse(dataStr, formatter));
            }

            accessi.add(a);
        }

        return accessi.toArray(new Accesso[0]);
    }


}
