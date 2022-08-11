package com.gustavo.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.gustavo.bookstore.domain.model.Categoria;
import com.gustavo.bookstore.exception.ObjectNotFoundException;
import com.gustavo.bookstore.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired CategoriaRepository categoriaRepository;
	
	
	public Categoria findById(Long id) {
		Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
		return categoriaOptional.orElseThrow(
				()-> new ObjectNotFoundException("Objeto não encontrado ID: " +id+ " do Tipo: " + Categoria.class.getSimpleName() ));
	}
	
	public List<Categoria> findAll(){
		return categoriaRepository.findAll();
	}
	
	public Categoria create(Categoria categoria) {
		categoria.setId(null);
		return categoriaRepository.save(categoria);
	}

	public Categoria update(Long id, Categoria categoria) {
		Categoria categoriaAtt = this.findById(id);
		categoriaAtt.setNome(categoria.getNome());
		categoriaAtt.setDescricao(categoria.getDescricao());
		return categoriaRepository.save(categoriaAtt);
	}

	public void delete(Long id) {
		this.findById(id);
		try {
			categoriaRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new com.gustavo.bookstore.exception.DataIntegrityViolationException
			("Categoria ID: " +id+ " não pode ser deletada! Possui livros associados");
		}
	}
	
}
