package br.com.wallace.Spring.Data.Service;

import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.wallace.Spring.Data.ORM.Produto;
import br.com.wallace.Spring.Data.Repository.cadastroRepository;

@Service
public class relatorioService {

	private boolean systen = true;

	private cadastroRepository repository;

	public relatorioService(cadastroRepository repository) {
		this.repository = repository;
	}

	public void iniciar(Scanner sc) {
		while (systen == true) {
			System.out.println("Qual operaçao deseja: ");
			System.out.println("0 == SAIR");
			System.out.println("1 == BUSCAR POR NOME");
			System.out.println("2 == BUSCAR POR NOME E QUANTIDADE");
			System.out.println("3 == BUSCAR POR PRECO");
			int op = sc.nextInt();

			switch (op) {
			case 1:
				buscarPorNome(sc);
				break;
			case 2:
				buscaQuantidadeNome(sc);
				break;
			case 3:
				buscarPorPreco(sc);
				break;
			default:
				systen = false;
				break;
			}
		}
	}

	// derived Query
	public void buscarPorNome(Scanner sc) {
		System.out.print("Qual o nome de deseja Buscar: ");
		String nome = sc.next();
		List<Produto> list = repository.findByNome(nome);
		list.forEach(p -> System.out.println(p));
	}
	
	//JPQL
	private void buscaQuantidadeNome (Scanner sc) {
		System.out.print("digite o nome que deseja buscar: ");
		String nome = sc.next();
		
		System.out.print("Digite a quantidade: ");
		Integer quantidade = sc.nextInt();
		
		List<Produto> list = repository.buscaQuantidadeNome(nome, quantidade);
		list.forEach(p -> System.out.println(p));
		
	}
	
	//Native Query
	public void buscarPorPreco(Scanner sc) {
		System.out.print("digite o valor que deseja buscar: ");
		Double preco = sc.nextDouble();
		
		List<Produto> list = repository.buscarPorPreco(preco);
		list.forEach(p -> System.out.println(p));
	}
}