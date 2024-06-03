package br.com.corretoraImovel.model;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

public class Agendamento {
	
	private Long id;

	private LocalDate data;

    private LocalDate hora;

    private Imovel imovel;
    
    private Pessoa pessoa;

	public Agendamento() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Agendamento(Long id,
			 LocalDate data,
			 LocalDate hora, Imovel imovel,
			 Pessoa pessoa) {
		super();
		this.id = id;
		this.data = data;
		this.hora = hora;
		this.imovel = imovel;
		this.pessoa = pessoa;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public LocalDate getData() {
		return data;
	}


	public void setData(LocalDate data) {
		this.data = data;
	}


	public LocalDate getHora() {
		return hora;
	}


	public void setHora(LocalDate hora) {
		this.hora = hora;
	}


	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@Override
	public String toString() {
		return "agendamento [id=" + id + ", data=" + data + ", hora=" + hora + ", imovel=" + imovel + ", pessoa="
				+ pessoa + "]";
	}
    
    

}
