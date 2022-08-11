package com.gustavo.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustavo.bookstore.domain.model.Categoria;
import com.gustavo.bookstore.domain.model.Livro;
import com.gustavo.bookstore.exception.ObjectNotFoundException;
import com.gustavo.bookstore.repository.LivroRepository;

@Service
public class LivroService {

	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private CategoriaService categoriaService;
	
	
	public Livro findById(Long id) {
		Optional<Livro> livroOptional = livroRepository.findById(id);
		return livroOptional.orElseThrow(
				()-> new ObjectNotFoundException("Objeto não encontrado ID: " +id+ " do Tipo: " + Livro.class.getSimpleName() ));
	}
	
	public List<Livro> findAll(){
		return livroRepository.findAll();
	}

	public List<Livro> findAllByCategoria(Long id_cat) {
		categoriaService.findById(id_cat);
		return livroRepository.findAllByCategoria(id_cat);
	}

	public Livro update(Long id, Livro livro) {
		Livro livroAtt = this.findById(id);
		this.updateData(livroAtt, livro);
		return livroRepository.save(livroAtt);
	}

	private void updateData(Livro livroAtt, Livro livro) {
		livroAtt.setTitulo(livro.getTitulo());
		livroAtt.setNomeAutor(livro.getNomeAutor());
		livroAtt.setTexto(livro.getTexto());
	}

	public Livro create(Long id_cat, Livro livro) {
		livro.setId(null);
		Categoria cat = categoriaService.findById(id_cat);
		livro.setCategoria(cat);
		return livroRepository.save(livro);
	}

	public void delete(Long id) {
		Livro livro = this.findById(id);
		livroRepository.delete(livro); //podemos usar o deletebyid também
	}
	
}
