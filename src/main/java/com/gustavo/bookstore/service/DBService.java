package com.gustavo.bookstore.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustavo.bookstore.domain.model.Categoria;
import com.gustavo.bookstore.domain.model.Livro;
import com.gustavo.bookstore.repository.CategoriaRepository;
import com.gustavo.bookstore.repository.LivroRepository;

@Service
public class DBService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private LivroRepository livroRepository;
	
	public void instanciaBaseDeDados() {
		
		Categoria cat1 = new Categoria(null, "Informática", "Livros de informatica");
		Categoria cat2 = new Categoria(null, "Matemática", "Livros de calculo");
		
		Livro l1 = new Livro(null, "Clean Code", "Fulano de tal", "lorem impsum ase ljasdf lasdf ", cat1);
		Livro l2 = new Livro(null, "Spring", "Fulano de tal", "spring spring boot boot m ase ljasdf lasdf ", cat1);
		Livro l3 = new Livro(null, "Calculos avançados", "Fulano de tal", "lorem impsum ase ljasdf lasdf ", cat2);
		
		cat1.getListLivro().addAll(Arrays.asList(l1,l2));
		cat2.getListLivro().addAll(Arrays.asList(l3));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		livroRepository.saveAll(Arrays.asList(l1,l2,l3));
		
	}

}
