package br.com.pi5s.icomida.dao;

import br.com.pi5s.icomida.entity.Stts;
import br.com.pi5s.icomida.view.PedidoPendenteCozinha;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Guilherme Abacherli
 */
public class PedidoDAO {

    public List<PedidoPendenteCozinha> listarPedidos() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("iComidaRESTPU");
        EntityManager em = emf.createEntityManager();

        try {
            //List<Pedido> listaPedidos = new ArrayList<>();
            List<PedidoPendenteCozinha> listaPedidosPendentes = new ArrayList<>();

            em.getTransaction().begin();

            //listaPedidos = em.createQuery(""
            //+ "SELECT p "
            //+ "FROM Pedido p")
            //.getResultList();
            //
            listaPedidosPendentes
                    = em.createQuery(""
                            + "SELECT p FROM PedidoPendenteCozinha AS p")
                            .getResultList();

            em.getTransaction().commit();
            return listaPedidosPendentes;

        } catch (Exception e) {
            System.out.println("Erro ao listar pedidos (REST).\nMensagem de erro:\n" + e.getMessage());
            em.getTransaction().rollback();
            return null;

        } finally {
            em.close();
        }
    }

    /**
     * Altera o status de um pedidoitem.
     *
     * @param pedidoitemId Chave primária (código) do pedidoitem.
     * @param sttsId Novo status do pedidoitem.
     * @return Mensagem (String) com resposta em caso de sucesso ou falha.
     */
    public String alterarSttsPedidoitem(Integer pedidoitemId, Integer sttsId) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("iComidaRESTPU");
        EntityManager em = emf.createEntityManager();

        try {

            Stts status = new Stts(sttsId);
            
            em.getTransaction().begin();

            em.createQuery(""
                    + "UPDATE Pedidoitem p "
                    + "SET p.sttsId = :sttsId "
                    + "WHERE p.pedidoitemId = :pedidoitemId")
                    .setParameter("sttsId", status)
                    .setParameter("pedidoitemId", pedidoitemId)
                    .executeUpdate();

            em.getTransaction().commit();

            return "Pedido: " + pedidoitemId + " - Status: " + sttsId;

        } catch (Exception e) {
            System.out.println("Erro ao atualizar status do pedido (alterarSttsPedidoitem) (REST).\nMensagem de erro:\n" + e.getMessage());
            em.getTransaction().rollback();
            return "Erro ao alterar status do pedidoitem!";

        } finally {
            em.close();
        }
    }
}