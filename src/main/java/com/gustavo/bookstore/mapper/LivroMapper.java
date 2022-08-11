package com.gustavo.bookstore.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.gustavo.bookstore.domain.model.Livro;
import com.gustavo.bookstore.domain.model.dto.LivroDTO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class LivroMapper {
	
	private ModelMapper modelMapper;
	
	public LivroDTO toDTO(Livro livro) {
		return modelMapper.map(livro, LivroDTO.class);
	}
	
	public List<LivroDTO> toListDTO(List<Livro> listLivro){
		return listLivro.stream()
				.map(this::toDTO)
				.collect(Collectors.toList());
	}
	
}
