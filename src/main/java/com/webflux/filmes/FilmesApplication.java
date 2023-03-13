package com.webflux.filmes;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import com.webflux.filmes.repository.FilmeRepository;
import com.webflux.filmes.model.Filme;

import reactor.core.publisher.Flux;


@SpringBootApplication
public class FilmesApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilmesApplication.class, args);}
		
		
		@Bean
		CommandLineRunner init (ReactiveMongoOperations operations, FilmeRepository repository) {
			return args ->{
				Flux<Filme> filmeFlux = Flux.just(
						new Filme(null, "Tudo ao mesmo tempo em todo lugar", "acao","filmasso bom demais", 7.0),
						new Filme(null, "whiplash", "bateria","filme bonzao", 10.0))
						.flatMap(repository::save);
					
				filmeFlux
					.thenMany(repository.findAll())
					.subscribe(System.out::println);
				
			};
			
		}
	}


