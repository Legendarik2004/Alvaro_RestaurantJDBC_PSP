package jakarta.rest;

import common.Constantes;
import domain.model.Customer;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import services.CustomerService;

import java.util.List;

@Path(ConstantsRutas.CUSTOMERS)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestCustomer {

    private CustomerService customerService;

    @Inject
    public RestCustomer(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GET
    public List<Customer> getAll() {
        return customerService.getAll();
    }

    @GET
    @Path(ConstantsRutas.ID_LITERAL)
    public Customer get(@PathParam(Constantes.ID) int id) {
        return customerService.get(id);
    }

    @POST
    public Response add(Customer customer) {
        customerService.save(customer);
        return Response.status(Response.Status.CREATED).entity(customer + Constantes.CUSTOMER_ADDED).build();
    }

    @PUT
    public Response update(Customer customer, @QueryParam(Constantes.ID) int id) {
        customer.setId(id);
        customerService.update(customer);
        return Response.status(Response.Status.OK).entity(Constantes.CUSTOMER_UPDATED).build();
    }

    @DELETE
    public Response delete(@QueryParam(Constantes.ID) int id) {
        customerService.delete(id);
        return Response.status(Response.Status.OK).entity(Constantes.CUSTOMER_DELETED).build();
    }
}
