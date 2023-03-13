package com.webflux.filmes.controller;

import java.time.Duration;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.webflux.filmes.model.Filme;
import com.webflux.filmes.model.FilmeEvent;
import com.webflux.filmes.repository.FilmeRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("filmes")
public class FilmeController {
	
	private FilmeRepository repository;
	
	public FilmeController(FilmeRepository repository) {
		this.repository = repository;
	}
	
	
	@GetMapping
	public Flux<Filme> getAllFilmes(){
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public Mono<ResponseEntity<Filme>> getFilme(@PathVariable String id){
		return repository.findById(id) //if else funcional 
				.map(filme -> ResponseEntity.ok(filme))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Filme> saveFilme(@RequestBody Filme filme){
		return repository.save(filme);
	}
	
	
	
	@PutMapping("{id}")
	public Mono<ResponseEntity<Filme>> updateFilme(@PathVariable(value= "id") String id,
												   @RequestBody Filme filme){
		
		return repository.findById(id)
				.flatMap(existingFilme -> {
					existingFilme.setNome(filme.getNome());
					existingFilme.setCategoria(filme.getCategoria());
					existingFilme.setDescricao(filme.getDescricao());
					existingFilme.setNota(filme.getNota());
					
					return repository.save(existingFilme);
				})
				.map(updateFilme -> ResponseEntity.ok(updateFilme))
				.defaultIfEmpty(ResponseEntity.notFound().build());
		
	}
	
	@DeleteMapping("{id}")
	public Mono<ResponseEntity<Void>> deleteFilme(@PathVariable(value = "id") String id){
		return repository.findById(id)
				.flatMap(existingFilme ->
						repository.delete(existingFilme)
							.then(Mono.just(ResponseEntity.ok().<Void>build()))
						)
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@DeleteMapping
	public Mono<Void> deleteAllFilmes(){
		return repository.deleteAll();
	}
	
	@GetMapping(value ="/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<FilmeEvent> getFilmeEvents(){
		return Flux.interval(Duration.ofSeconds(5))
				.map(val ->
					new FilmeEvent(val, "filmes"));
		
	}
	
	
	
}
