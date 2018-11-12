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
    @Path(value = "/listarPedidos")
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

    @GET
    @Path(value = "/pedidos")
    @Produces(MediaType.TEXT_HTML)
    public String paginaPedidos() {
        try {
            return ""
                    + "<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">\n"
                    + "<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js\" integrity=\"sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy\" crossorigin=\"anonymous\"></script>\n"
                    + "<script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\" crossorigin=\"anonymous\"></script>\n"
                    + "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js\" integrity=\"sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49\" crossorigin=\"anonymous\"></script>\n"
                    + "<table>"
                    + "     <tr>"
                    + "         <th>"
                    + "              ColUm"
                    + "         </th>"
                    + "         <th>"
                    + "               ColDois"
                    + "         </th>"
                    + "     </tr>"
                    + "     <tr>"
                    + "         <td>"
                    + "              ColUm"
                    + "         </td>"
                    + "         <td>"
                    + "               ColDois"
                    + "         </td>"
                    + "     </tr>"
                    + "</table>";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @PUT
    @Path(value = "/pedidoConcluido/{id}")
    @Produces(MediaType.TEXT_HTML)
    public String paginaPedidoConcluido() {
        try {
            return "";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}