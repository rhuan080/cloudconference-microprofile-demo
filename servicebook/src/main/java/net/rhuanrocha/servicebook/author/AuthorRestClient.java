package net.rhuanrocha.servicebook.author;

import org.eclipse.microprofile.faulttolerance.*;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.temporal.ChronoUnit;

@Path("/authors")
@RegisterRestClient
public interface AuthorRestClient {

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Fallback(AuthorFallBack.class)
    @Retry(maxRetries = 3)
    @Timeout(value = 5, unit = ChronoUnit.SECONDS)
    @CircuitBreaker(requestVolumeThreshold = 4, failureRatio = 0.75, delay = 1000, successThreshold = 5)
    public AuthorDto findById(@PathParam("id") String id);
}

class AuthorFallBack implements FallbackHandler<AuthorDto>{

    public AuthorDto handle(ExecutionContext var1){
       return null;
    }
}
