package com.esed.challenge.api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.esed.challenge.application.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RestController
public class ApplicationController {

    @GetMapping("/get/{versio}") // Es un get al localhost -> http://localhost:8080/get/v3 o v2 o v1
    public JsonNode get(@PathVariable String versio) {

        ArrayList<Element> yourList = new ArrayList<>();
        Gson gson = new Gson();
        versio = versio.toLowerCase();
        HttpResponse<String> getResponse = null;

        // comprovem que sigui una de les versions que acceptem
        if (!(versio.equals("v3") || versio.equals("v2") || versio.equals("v1")))
            return creatErrorJsonN("Versió donada erronia, només acceptem V3, V2 and V1, i has donat: " + versio);

        // intentem fer la crida a l'API
        try {
            HttpRequest getRequest = HttpRequest.newBuilder()
                    .uri(new URI("https://3gxdus4fe2.execute-api.eu-west-3.amazonaws.com/" + versio))
                    .GET()
                    .build();

            HttpClient httpClient = HttpClient.newHttpClient();
            getResponse = httpClient.send(getRequest, BodyHandlers.ofString());

        } catch (Exception e) {
            return creatErrorJsonN(
                    "Error inesperat en la 'request' amb la url: https://3gxdus4fe2.execute-api.eu-west-3.amazonaws.com/"
                            + versio);
        }

        // comprovem que la resposta no sigui un error
        if (getResponse.body().toLowerCase().contains("error")) {
            // agafem el missatge que savem que i serà en format Json
            JsonNode jsEr = convertStrToJsonN(getResponse.body());
            String missatge = jsEr.get("message").toString();
            return creatErrorJsonN("Error detectat en respota a l'API: " + missatge);
        }

        // depenent de la versió la traducció sera en un o altre objecte, tot i que tots
        // siguin "Element"
        if (versio.equals("v3")) {
            yourList = gson.fromJson(getResponse.body(), new TypeToken<List<ElementV3>>() {
            }.getType());
        } else if (versio.equals("v2")) {
            yourList = gson.fromJson(getResponse.body(), new TypeToken<List<ElementV2>>() {
            }.getType());
        } else {
            yourList = gson.fromJson(getResponse.body(), new TypeToken<List<ElementV1>>() {
            }.getType());
        }

        // ara anem a fer la traduccio i conversio per a que tots siguin format "V3"
        // i ho convertim com una "llista en String" per despres poder fer el JsonNode
        StringBuilder strb = new StringBuilder();
        strb.append("[");

        for (Element element : yourList) {
            strb.append(element.toJson().toString());
            strb.append(",");
        }
        strb.replace(strb.length() - 1, strb.length(), "");// eliminem la ultima ","
        strb.append("]");
        String str = strb.toString();

        // convertin en format JsonNode, aixi podrem imprimir pel navegador en format
        // Json
        return convertStrToJsonN(str);
    }

    private JsonNode convertStrToJsonN(String str) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            return mapper.readTree(str);
        } catch (Exception e) {
            return creatErrorJsonN("Error al convertir el String a JsonNode");
        }
    }

    private JsonNode creatErrorJsonN(String error) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        JsonNode jsonNode = mapper.createObjectNode();
        ((ObjectNode) jsonNode).put("ERROR: ", error);
        return jsonNode;
    }
}
