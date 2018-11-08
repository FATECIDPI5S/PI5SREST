package br.com.pi5s.icomida.entity.auxiliar;

import br.com.pi5s.icomida.entity.Pedido;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Guilherme Abacherli
 */
public class PedidoAux {

    public static List<PedidoAux> formatarLista(List<Pedido> listaPedido) {
        List<PedidoAux> novaLista = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            for (Pedido pedidoAntigo : listaPedido) {
                PedidoAux novoPedido = new PedidoAux();
                if (pedidoAntigo.getPedidoId() != null) {
                    novoPedido.setPedidoId(pedidoAntigo.getPedidoId());
                }
                if (pedidoAntigo.getPedidoData() != null) {
                    novoPedido.setPedidoData(sdf.format(pedidoAntigo.getPedidoData()));
                }
                if (pedidoAntigo.getPedidoSubtotal() != null) {
                    novoPedido.setPedidoSubtotal(Double.valueOf(String.valueOf(pedidoAntigo.getPedidoSubtotal())));
                }
                if (pedidoAntigo.getPedidoTaxaGarcom() != null) {
                    novoPedido.setPedidoTaxaGarcom(Double.valueOf(String.valueOf(pedidoAntigo.getPedidoTaxaGarcom())));
                }
                if (pedidoAntigo.getPedidoValorTotal() != null) {
                    novoPedido.setPedidoValorTotal(Double.valueOf(String.valueOf(pedidoAntigo.getPedidoValorTotal())));
                }
                if (pedidoAntigo.getSttsId() != null) {
                    novoPedido.setSttsId(pedidoAntigo.getSttsId());
                }
                // if (pedidoAntigo.getPedidoitemCollection() != null) {
                // novoPedido.setPedidoitemCollection(pedidoAntigo.getPedidoitemCollection().);
                // }
                if (pedidoAntigo.getEmpresaId() != null) {
                    novoPedido.setEmpresaId(pedidoAntigo.getEmpresaId().getEmpresaId());
                }
                if (pedidoAntigo.getFuncionarioId() != null) {
                    novoPedido.setFuncionarioId(pedidoAntigo.getFuncionarioId().getFuncionarioId());
                }
                if (pedidoAntigo.getMesaId() != null) {
                    novoPedido.setMesaId(pedidoAntigo.getMesaId().getMesaId());
                }
                novaLista.add(novoPedido);
            }
            return novaLista;

        } catch (Exception e) {
            System.out.println("Erro em br.com.pi5s.icomida.entity.auxiliar.PedidoAux.formatarLista()");
            System.out.println("Mensagem de erro:\n" + e.getMessage());
            return null;
        }
    }

    private Integer pedidoId;
    private String pedidoData;
    private Double pedidoSubtotal;
    private Double pedidoTaxaGarcom;
    private Double pedidoValorTotal;
    private Integer sttsId;
    private Integer pedidoitemCollection;
    private Integer empresaId;
    private Integer funcionarioId;
    private Integer mesaId;

    public PedidoAux() {
    }

    public PedidoAux(
            Integer pedidoId,
            String pedidoData,
            Double pedidoSubtotal,
            Double pedidoTaxaGarcom,
            Double pedidoValorTotal,
            Integer sttsId,
            //Integer pedidoitemCollection,
            Integer empresaId,
            Integer funcionarioId,
            Integer mesaId) {

        this.pedidoId = pedidoId;
        this.pedidoData = pedidoData;
        this.pedidoSubtotal = pedidoSubtotal;
        this.pedidoTaxaGarcom = pedidoTaxaGarcom;
        this.pedidoValorTotal = pedidoValorTotal;
        this.sttsId = sttsId;
        //this.pedidoitemCollection = pedidoitemCollection;
        this.empresaId = empresaId;
        this.funcionarioId = funcionarioId;
        this.mesaId = mesaId;
    }

    public Integer getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Integer pedidoId) {
        this.pedidoId = pedidoId;
    }

    public String getPedidoData() {
        return pedidoData;
    }

    public void setPedidoData(String pedidoData) {
        this.pedidoData = pedidoData;
    }

    public Double getPedidoSubtotal() {
        return pedidoSubtotal;
    }

    public void setPedidoSubtotal(Double pedidoSubtotal) {
        this.pedidoSubtotal = pedidoSubtotal;
    }

    public Double getPedidoTaxaGarcom() {
        return pedidoTaxaGarcom;
    }

    public void setPedidoTaxaGarcom(Double pedidoTaxaGarcom) {
        this.pedidoTaxaGarcom = pedidoTaxaGarcom;
    }

    public Double getPedidoValorTotal() {
        return pedidoValorTotal;
    }

    public void setPedidoValorTotal(Double pedidoValorTotal) {
        this.pedidoValorTotal = pedidoValorTotal;
    }

    public Integer getSttsId() {
        return sttsId;
    }

    public void setSttsId(Integer sttsId) {
        this.sttsId = sttsId;
    }

    public Integer getPedidoitemCollection() {
        return pedidoitemCollection;
    }

    // public void setPedidoitemCollection(Integer pedidoitemCollection) {
    // this.pedidoitemCollection = pedidoitemCollection;
    // }
    //
    public Integer getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Integer empresaId) {
        this.empresaId = empresaId;
    }

    public Integer getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(Integer funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    public Integer getMesaId() {
        return mesaId;
    }

    public void setMesaId(Integer mesaId) {
        this.mesaId = mesaId;
    }
}