package br.com.ProjetoTabelaFipe.Benito.Moran;

import br.com.ProjetoTabelaFipe.main.Main;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BenitoMoranApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BenitoMoranApplication.class, args);
	}
	//aqui vamos implementar o menu junto com a classe Main
	@Override
	public void run(String... args) throws Exception {
		Main main = new Main();
		main.exebirMenu();
	}
}
