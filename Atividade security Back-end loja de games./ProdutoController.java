package com.lojadegames.lojadegames.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.lojadegames.lojadegames.model.Produto;
import com.lojadegames.lojadegames.repository.CategoriaRepository;
import com.lojadegames.lojadegames.repository.ProdutoRepository;

@RestController
@RequestMapping("/produto")
@CrossOrigin("*")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository repository;
	
	@Autowired
	private CategoriaRepository repositoryCA;
	
	@GetMapping
	public ResponseEntity<List<Produto>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> getById(@PathVariable Long id){
		return repository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/Produto/{tipo}")
	public ResponseEntity<List<Produto>> getByNome(@PathVariable String tipo){
		return ResponseEntity.ok(repository.findAllBytipoContainingIgnoreCase(tipo));

	}
	
	@PostMapping
	public ResponseEntity<Produto> post(@Valid @RequestBody Produto produto){
		if (repositoryCA.existsById(produto.getCategoria().getId()))
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(repository.save(produto));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

	}

	@PutMapping
	public ResponseEntity<Produto> put(@Valid @RequestBody Produto produto) {
		if(repository.existsById(produto.getId())) {
			
		if(repositoryCA.existsById(produto.getCategoria().getId()))
		return ResponseEntity.status(HttpStatus. OK)
						.body(repository.save(produto));
						
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduto(@PathVariable Long id) {
		
		return repository.findById(id)
				.map(resposta -> {
					repository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.notFound().build());
	}
}
