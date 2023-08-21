package org.btg.client;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path(value = "/v1/q/orders")
@Tag(name = "Client Order Information", description = "Provide client order information")
public interface CustomOrderAPI {

    @GET
    @Path("/{clientId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get Client Order Information")
    @APIResponse(responseCode = "200", description = "Orders returned successfully")
    @APIResponse(responseCode = "500", description = "Unable to return information due to an internal error")
    default Response getClientOrders(@PathParam("clientId") Integer clientId){
        throw new ServiceUnavailableException("Endpoint not implemented yet");
    }
}
