package br.com.wallace.Spring.Data;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.wallace.Spring.Data.Service.crudService;
import br.com.wallace.Spring.Data.Service.relatorioService;


@SpringBootApplication
public class ProjetoApplication implements CommandLineRunner {

	private boolean system = true;

	private crudService service;
	private relatorioService relatorio;

	public ProjetoApplication(crudService service, relatorioService relatorio) {
		this.service = service;
		this.relatorio = relatorio;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProjetoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Scanner sc = new Scanner(System.in);

		while (system) {
			System.out.println("0 == SAIR");
			System.out.println("1 == CRUD");
			System.out.println("2 == RELATORIO");
			int i = sc.nextInt();

			if (i == 1) {
				service.iniciar(sc);
			} else if (i == 2) {
				relatorio.iniciar(sc);
			} else
				system = false;
		}
	}
}