package com.gustavo.bookstore.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gustavo.bookstore.domain.model.Categoria;
import com.gustavo.bookstore.domain.model.dto.CategoriaDTO;
import com.gustavo.bookstore.mapper.CategoriaMapper;
import com.gustavo.bookstore.service.CategoriaService;


@CrossOrigin("*")
@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private CategoriaMapper categoriaMapper;
	
	

	@GetMapping(value = "/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable Long id){
		Categoria categoria = categoriaService.findById(id);
		return ResponseEntity.ok().body(categoria);
	}
	
	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> findAll(){
		List<Categoria> listCategoria = categoriaService.findAll();
		//List<CategoriaDTO> listCategoriaDTO = listCategoria.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		//return ResponseEntity.ok().body(listCategoriaDTO);  SEM MODEL MAPPER
		
		return ResponseEntity.ok().body(categoriaMapper.toListDTO(listCategoria)); // COM MODEL MAPPER
	}
	
	
	/*@PostMapping  // CRIA UMA CATEGORIA USANDO O DTO, NEGANDO O QUE O DTO OMITE, NESTE CASO A LISTA DE LIVROS, RETORNA DTO SEM LISTA
	@ResponseStatus(HttpStatus.CREATED)    // E NÃO RETORNA A URI EM LOCATION DO BODY
	public CategoriaDTO create(@RequestBody Categoria categoria) {
		categoria = categoriaService.create(categoria);
		return categoriaMapper.toDTO(categoria);
	}*/
	
	
	@PostMapping //CRIA UMA CATEGORIA USANDO A ENTIDADE, E SEM OMITIR A LISTA QUE TEM DENTRO DELA, RETORNA A ENTIDADE COM A LISTA VAZIA
	public ResponseEntity<Categoria> create(@Valid @RequestBody Categoria categoria){  // E RETORNA TAMBÉM A URI DENTRO DE LOCATION NO BODY
		categoria = categoriaService.create(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId()).toUri();
		return ResponseEntity.created(uri).body(categoria); // .BODY(ENTIDADE); RETORNA A ENTIDADE NO CORPO DA RESPOSTA
	}														//SE USAR .BUILD(); NÃO RETORNA NADA NO CORPO DA RESPOSTA, APENAS EXECUTA	
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<CategoriaDTO> update(@PathVariable Long id, @Valid @RequestBody Categoria categoria){
		categoria = categoriaService.update(id, categoria);
		return  ResponseEntity.ok().body(categoriaMapper.toDTO(categoria));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		categoriaService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
