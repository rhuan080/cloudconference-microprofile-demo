package net.rhuanrocha.servicebook.author;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class AuthorService {

    @Inject
    @ConfigProperty(name = "net.rhuanrocha.servicebook.author.AuthorService.url")
    private String url;

    public AuthorDto findById(String id){
        Client client = ClientBuilder.newClient();
        Response response = client
                .target(url)
                .path(id)
                .request(MediaType.APPLICATION_JSON)
                .get();
        if(response.getStatus() == Response.Status.OK.getStatusCode()){
            return response.readEntity(AuthorDto.class);
        }
        return null;
    }
}
