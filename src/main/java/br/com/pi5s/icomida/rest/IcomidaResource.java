package br.com.pi5s.icomida.rest;

import br.com.pi5s.icomida.dao.PedidoDAO;
import br.com.pi5s.icomida.entity.Pedido;
import br.com.pi5s.icomida.entity.auxiliar.PedidoAux;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;

/**
 * REST Web Service
 *
 * @author Guilherme Abacherli
 */
@Path("icomida")
public class IcomidaResource {

    /**
     * Creates a new instance of IcomidaResource
     */
    public IcomidaResource() {
    }

    /**
     * Retrieves representation of an instance of
     * br.com.pi5s.icomidarest.IcomidaResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of IcomidaResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }

    @GET
    @Path(value = "/pedidos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarPedidos() {
        try {

            PedidoDAO pedidoDao = new PedidoDAO();

            List<Pedido> listaPedidos = new ArrayList<>();
            listaPedidos.addAll(pedidoDao.listarPedidos());

            List<PedidoAux> listaPedidosAux = new ArrayList<>();
            listaPedidosAux.addAll(PedidoAux.formatarLista(listaPedidos));

            Gson g = new Gson();
            return Response.ok(g.toJson(listaPedidosAux), MediaType.APPLICATION_JSON).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}