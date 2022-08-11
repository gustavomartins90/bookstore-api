package com.gustavo.bookstore.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gustavo.bookstore.domain.model.Livro;
import com.gustavo.bookstore.domain.model.dto.LivroDTO;
import com.gustavo.bookstore.mapper.LivroMapper;
import com.gustavo.bookstore.service.LivroService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/livros")
public class LivroController {
	
	@Autowired
	private LivroService livroService;
	
	@Autowired
	private LivroMapper livroMapper;
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<LivroDTO> findById(@PathVariable Long id){
		Livro livro = livroService.findById(id);
		return ResponseEntity.ok().body(livroMapper.toDTO(livro));
	}
	
	@GetMapping
	public ResponseEntity<List<LivroDTO>> findAll(){
		List<Livro> listLivro = livroService.findAll();
		return ResponseEntity.ok().body(livroMapper.toListDTO(listLivro));
	}
	
	@GetMapping(value = "/porCategoria")
	public ResponseEntity<List<LivroDTO>> findAllByCategoria(@RequestParam(value = "categoria", defaultValue = "0") Long id_cat){
		List<Livro> listLivro = livroService.findAllByCategoria(id_cat);
		return ResponseEntity.ok().body(livroMapper.toListDTO(listLivro));
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<LivroDTO> update(@PathVariable Long id, @Valid @RequestBody Livro livro){
		Livro livroAtt = livroService.update(id, livro);
		return ResponseEntity.ok().body(livroMapper.toDTO(livroAtt));
	}
	
	@PatchMapping(value = "/{id}")
	public ResponseEntity<LivroDTO> updatePatch(@PathVariable Long id, @Valid @RequestBody Livro livro){
		Livro livroAtt = livroService.update(id, livro);
		return ResponseEntity.ok().body(livroMapper.toDTO(livroAtt));
	}
	
	@PostMapping
	public ResponseEntity<Livro> create(@RequestParam(value = "categoria", defaultValue = "0") Long id_cat, @Valid @RequestBody Livro livro){
		Livro livreCreated = livroService.create(id_cat, livro);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/livros/{id}").buildAndExpand(livreCreated.getId()).toUri();
		return ResponseEntity.created(uri).body(livreCreated);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		livroService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
