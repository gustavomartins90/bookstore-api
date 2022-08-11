package com.gustavo.bookstore.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.gustavo.bookstore.domain.model.Categoria;
import com.gustavo.bookstore.domain.model.dto.CategoriaDTO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class CategoriaMapper {
	
	private ModelMapper modelMapper;
	
	public CategoriaDTO toDTO(Categoria categoria) {
		return modelMapper.map(categoria, CategoriaDTO.class);
	}
	
	public List<CategoriaDTO> toListDTO(List<Categoria> listcCategoria){
		return listcCategoria.stream()
				.map(this::toDTO)
				.collect(Collectors.toList());
	}
	
}
