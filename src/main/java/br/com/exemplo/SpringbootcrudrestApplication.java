package br.com.exemplo;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.exemplo.entity.Pessoa;
import br.com.exemplo.repository.PessoaRepository;

@SpringBootApplication
public class SpringbootcrudrestApplication  implements CommandLineRunner {
	
	@Autowired
	private PessoaRepository pessoaRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootcrudrestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		 
		Pessoa p1 = new Pessoa(null, "Maria", 45);
		Pessoa p2 = new Pessoa(null, "Paulo", 14);
		Pessoa p3 = new Pessoa(null, "Ana", 23);
		
		pessoaRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		
		 
		
		
	}

}
