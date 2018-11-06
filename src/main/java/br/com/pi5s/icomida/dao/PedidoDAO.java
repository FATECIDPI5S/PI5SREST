package br.com.pi5s.icomida.dao;

import br.com.pi5s.icomida.entity.Pedido;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Guilherme Abacherli
 */
public class PedidoDAO {

    public List<Pedido> listarPedidos() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("iComidaRESTPU");
        EntityManager em = emf.createEntityManager();

        try {
            List<Pedido> listaPedidos = new ArrayList<>();

            em.getTransaction().begin();
            listaPedidos = em.createQuery("SELECT p FROM Pedido p").getResultList();
            em.getTransaction().commit();

            return listaPedidos;

        } catch (Exception e) {
            System.out.println("Erro ao listar pedidos (REST).\nMensagem de erro:\n" + e.getMessage());
            em.getTransaction().rollback();
            return null;
        }
    }
}