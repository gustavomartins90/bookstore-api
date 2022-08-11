package com.gustavo.bookstore.domain.model.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.gustavo.bookstore.domain.model.Categoria;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotEmpty(message = "Campo NOME é requerido")
	@Length(min = 3, max = 100, message = "O Campo NOME deve ter entre 3 e 100 caracteres")
	private String nome;
	
	@NotEmpty(message = "Campo DESCRIÇÃO é requerido")
	@Length(min = 10, max = 200, message = "O Campo DESCRIÇÃO deve ter entre 10 e 200 caracteres")
	private String descricao;
	
	//@OneToMany(mappedBy = "categoria")  TRAZ OS LIVROS CONTIDOS NESTA CATEGORIA DENTRO DO DTO
	//private List<Livro> listLivro = new ArrayList<>(); 
	
	public CategoriaDTO() {
		super();
	}
	
	public CategoriaDTO(Categoria obj) {  //se usar model mapper, não precisa deste construtor
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.descricao = obj.getDescricao();
	}
	
	
}
