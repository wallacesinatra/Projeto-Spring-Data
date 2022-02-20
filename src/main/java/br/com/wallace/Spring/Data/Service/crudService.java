package br.com.wallace.Spring.Data.Service;

import java.util.Scanner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.wallace.Spring.Data.ORM.Produto;
import br.com.wallace.Spring.Data.Repository.cadastroRepository;

@Service
public class crudService {

	Scanner sc = new Scanner(System.in);

	private cadastroRepository repository;

	public crudService(cadastroRepository repository) {
		this.repository = repository;
	}

	public void iniciar(Scanner sc) {

		System.out.println("Qual operaçao deseja ? ");
		System.out.println("1 - INSERIR");
		System.out.println("2 - DELETAR");
		System.out.println("3 - LISTAR");
		System.out.println("4 - ATUALIZAR");

		int op = sc.nextInt();

		switch (op) {
		case 1:
			inserir(sc);
			break;
		case 2:
			deletar(sc);
			break;
		case 3:
			lista(sc);
			break;
		case 4:
			atualizar(sc);
			break;
		default:
			break;
		}
	}

	public void inserir(Scanner sc) {
		System.out.println("\nInserindo produtos");
		System.out.print("\nDigite o nome do Produto: ");
		String nome = sc.next();

		System.out.print("Digite a Dercricao do produto: ");
		sc.nextLine();
		String descricao = sc.nextLine();

		System.out.print("Digite o Preco do Produto: ");
		Double preco = sc.nextDouble();

		System.out.print("Digite a Quantidade do Produto:  ");
		Integer quantidade = sc.nextInt();

		Produto produto = new Produto(quantidade, nome, descricao, preco, quantidade);
		repository.save(produto);
		System.out.println("\nProduto INSERIDO");

	}

	public void deletar(Scanner sc) {
		System.out.print("Digite o ID de quem deseja deletar: ");
		Integer id = sc.nextInt();
		repository.deleteById(id);
		System.out.print("\nProduto deletado");
	}

	public void lista(Scanner sc) {
		System.out.print("Qual pagina deseja vizualizar: ");
		Integer page = sc.nextInt();
		Pageable pageable = PageRequest.of(page, 5,Sort.by(Sort.Direction.ASC,"nome"));
		Page<Produto> produto = repository.findAll(pageable);
		System.out.println(produto);
		System.out.println("Pagnia atual e: " + produto.getNumber() + " Total de itens: " + produto.getTotalElements());
		produto.forEach(p -> System.out.println(p));
		
//		Iterable<Produto> list = repository.findAll();
//		list.forEach(p -> System.out.println(p));
	}

	public void atualizar(Scanner sc) {
		System.out.print("Digite o ID de quem deseja atualizar: ");
		Integer id = sc.nextInt();

		System.out.println("Atualizar Produto");
		System.out.print("Novo nome Produto: ");
		String nome = sc.next();

		System.out.print("Nova descricao do Produto: ");
		String descricao = sc.next();

		System.out.print("Novo preco do Produto: ");
		Double preco = sc.nextDouble();

		System.out.print("Nova quantidade do produto: ");
		Integer quantidade = sc.nextInt();

		Produto produto = new Produto();
		produto.setId(id);
		produto.setNome(nome);
		produto.setDescricao(descricao);
		produto.setPreco(preco);
		produto.setQuantidade(quantidade);
		repository.save(produto);
		System.out.println("\nProduto Atualizado");
	}
}
