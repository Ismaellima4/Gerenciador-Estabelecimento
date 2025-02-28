package com.gereciador.estabelecimento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.gereciador.estabelecimento")
public class GerenciadorEstabelecimentoApplication {

	public static void main(String[] args) {

		SpringApplication.run(GerenciadorEstabelecimentoApplication.class, args);
	}

}
