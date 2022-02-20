package br.com.wallace.Spring.Data.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.wallace.Spring.Data.ORM.Produto;

@Repository
public interface cadastroRepository extends PagingAndSortingRepository<Produto, Integer> {

	// Derived query
	List<Produto> findByNome(String nome);
	
	//JPQL
	@Query("SELECT p FROM Produto p WHERE p.nome = :nome AND p.quantidade = :quantidade")
	List<Produto> buscaQuantidadeNome(String nome, Integer quantidade);
	
	//Native Query
	@Query(value = "SELECT * FROM Produtos p WHERE p.preco = :preco", nativeQuery = true)
	List<Produto> buscarPorPreco(Double preco);
}
