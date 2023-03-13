package com.webflux.filmes.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.webflux.filmes.model.Filme;

public interface FilmeRepository extends ReactiveMongoRepository<Filme, String>{

}
