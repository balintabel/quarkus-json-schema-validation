package org.acme;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/isvalid")
public class ExampleResource {

    @Inject
    JsonValidator jsonValidator;

    @Inject
    ObjectMapper objectMapper;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response isValid(final Object object) {
        // convert object to json
        final JsonNode jsonNode = objectMapper.valueToTree(object);
        jsonValidator.validate(jsonNode);
        return Response.ok("Json is VALID!").build();
    }
}
