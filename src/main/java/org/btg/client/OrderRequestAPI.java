package org.btg.client;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.btg.client.dto.OrderRequest;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/v1/orders")
@Tag(name = "Produce Orders", description = "Send order requests to order-request queue")
public interface OrderRequestAPI {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponse(responseCode = "204", description = "Order sent successfully")
    @APIResponse(responseCode = "400", description = "Missing some important information")
    @APIResponse(responseCode = "500", description = "Unable to create order due to an internal error")
    default void createOrder(@RequestBody OrderRequest order){
        throw new ServiceUnavailableException("Endpoint not implemented yet");
    }
}
