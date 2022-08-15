package com.gustavo.bookstore.domain.model.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroDTO  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotEmpty(message = "Campo TITULO é requerido")
	@Length(min = 3, max = 50, message = "O Campo TITULO deve ter entre 3 e 50 caracteres")
	private String titulo;
	
	@NotEmpty(message = "Campo NOME DO AUTOR é requerido")
	@Length(min = 3, max = 50, message = "O Campo NOME DO AUTOR deve ter entre 3 e 50 caracteres")
	private String nomeAutor;
	
	@NotEmpty(message = "Campo TEXTO é requerido")
	@Length(min = 10, max = 2000000, message = "O Campo TEXTO deve ter entre 10 e 2 milhões caracteres")
	private String texto;
	
	//private CategoriaDTO categoria; // Traz os dados da categoria no momento da busca
	
	public LivroDTO() {
		super();
	}
	
}
