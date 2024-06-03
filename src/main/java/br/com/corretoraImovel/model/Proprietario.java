package br.com.corretoraImovel.model;

import java.util.Objects;

import jakarta.validation.constraints.NotBlank;

public class Proprietario extends Pessoa{
	
	private Long id;
	
	private Imovel imovel;
	 	 
	public Proprietario() {
		
	}

	 
	public Proprietario(Long id, @NotBlank(message = "O nome não pode estar em branco.") Imovel imovel) {
		super();
		this.id = id;
		this.imovel = imovel;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}


	@Override
	public String toString() {
		return "Proprietario [id=" + id + ", imovel=" + imovel + "]";
	}

	
}

