package jakarta.rest;

import common.Constantes;
import domain.model.Order;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import services.OrderService;

import java.util.List;

@Path(ConstantsRutas.ORDERS)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestOrder {

    private OrderService orderService;

    @Inject
    public RestOrder(OrderService orderService) {
        this.orderService = orderService;
    }

    @GET
    public List<Order> getAll() {
        return orderService.getAll();
    }

    @GET
    @Path(ConstantsRutas.ID_LITERAL)
    public Order get(@PathParam(Constantes.ID) int id) {
        return orderService.get(id);
    }

    @POST
    public Response add(Order order) {
        orderService.save(order);
        return Response.status(Response.Status.CREATED).entity(order + Constantes.ORDER_ADDED).build();
    }

    @PUT
    public Response update(Order order, @QueryParam(Constantes.ID) int id) {
        order.setOrderId(id);
        orderService.update(order);
        return Response.status(Response.Status.OK).entity(Constantes.ORDER_UPDATED).build();
    }

    @DELETE
    public Response delete(@QueryParam(Constantes.ID) int id) {
        orderService.delete(id);
        return Response.status(Response.Status.OK).entity(Constantes.ORDER_DELETED).build();
    }
}
