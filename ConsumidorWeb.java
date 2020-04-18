package com.macgarcia;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.util.Optional;

/**
 *
 * @author macgarcia
 *
 */
public final class ConsumidorWeb {

    /* UTILIZAR ESSA BIBLIOTECA
    <!-- https://mvnrepository.com/artifact/com.sun.jersey/jersey-bundle -->
        <dependency>
            <groupId>com.sun.jersey</groupId>
            <artifactId>jersey-bundle</artifactId>
            <version>1.19</version>
        </dependency>

     */
    private Client client;
    private WebResource web;

    public ConsumidorWeb() {
        this.client = Client.create();
    }

    public Optional<String> getDados(String endPonit) {
        this.web = client.resource(endPonit);
        ClientResponse response = this.web.type("application/json").get(ClientResponse.class);
        if (response.getStatus() == 200) {
            String result = response.getEntity(String.class);
            return Optional.of(result);
        }
        return Optional.empty();
    }

    public Optional<String> putDados(String endPoint, String json, boolean existeRetorno) {
        this.web = client.resource(endPoint);
        ClientResponse response = this.web.type("application/json").put(ClientResponse.class, json);
        if (response.getStatus() > 200 || response.getStatus() == 204 ) {
            if (existeRetorno) {
                String result = response.getEntity(String.class);
                return Optional.of(result);
            }
        }
        return Optional.empty();
    }

    public Optional<String> postDados(String endPoint, String json, boolean existeRetorno) {
        this.web = client.resource(endPoint);
        ClientResponse response = this.web.type("application/json").post(ClientResponse.class, json);
        if (response.getStatus() == 201) {
            if (existeRetorno) {
                String result = response.getEntity(String.class);
                return Optional.of(result);
            }
        }
        return Optional.empty();
    }

    public void deleteDados(String endPoint) {
        this.web = client.resource(endPoint);
        this.web.delete();
    }
}
