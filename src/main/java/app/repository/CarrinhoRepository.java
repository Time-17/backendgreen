package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.auth.Usuario;
import app.entity.Carrinho;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {
	
    public List<Carrinho> findByItemCarrinhoProdutoNomeProduto(String nomeProduto);
	
	@Query("FROM Carrinho c WHERE c.valorCarrinho >= :valorCarrinho")
	public List<Carrinho> buscarVendaAcimaValor(double valorCarrinho);
	
	@Query("FROM Carrinho c WHERE c.valorCarrinho <= :valorCarrinho")
	public List<Carrinho> buscarVendaAbaixoValor(double valorCarrinho);

	@Query(value = "FROM Carrinho c WHERE c.usuario = :usuario AND c.status = 'Em aberto'")
	public Carrinho getCarrinhoAbertoDoUsuario(Usuario usuario);
	
	 @Query(value = "SELECT DATE_FORMAT(c.data_carrinho, '%Y-%m') AS mes, SUM(c.valor_carrinho) AS valorTotal " +
             "FROM carrinho c " +
             "WHERE c.data_carrinho >= DATE_SUB(CURDATE(), INTERVAL 12 MONTH) " +
             "AND c.status = 'Encerrado' " +
             "GROUP BY mes " +
             "ORDER BY mes", 
             nativeQuery = true)
	 List<Object[]> findTotalValorCarrinhoByMonthForLast12Months();
	 
	@Query(value = "FROM Carrinho c WHERE c.status = 'Encerrado'")
	public List<Carrinho> getVendasFinalizadas();
	
}
