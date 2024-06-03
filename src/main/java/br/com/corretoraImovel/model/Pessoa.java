package br.com.corretoraImovel.model;

import java.util.Objects;

import jakarta.validation.constraints.NotBlank;

public class Pessoa {
	
	private Long id;
	
//	 @NotBlank(message = "O nome não pode estar em branco.")
	 private String nome;
	 
//	 @NotBlank(message = "O documento não pode estar em branco.")
	 private String documento;
	 
//	 @NotBlank(message = "O telefone não pode estar em branco.")
	 private String telefone;
	 
//	 @NotBlank(message = "O E-mail não pode estar em branco.")
	 private String email;
	 	 
	 // tipo 1 = locatorio/visitante tipo = proprietario
	 private String tipo; 
	 
	 
	 public Pessoa() {
		
	}

	public Pessoa (String nome,String documento, String telefone,String email, String tipo) {
		super();
		this.nome = nome;
		this.documento = documento;
		this.telefone = telefone;
		this.email = email;
		this.tipo = tipo;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
	        return tipo;
	    }

	    public void setTipo(String tipo) {
	        this.tipo = tipo;
	    }

	    public String getNome() {
	        return nome;
	    }

	    public void setNome(String nome) {
	        this.nome = nome;
	    }

	    public String getDocumento() {return documento;
	    }

	    public void setDocumento(String documento) {
	        this.documento = documento;
	    }

	    public String getTelefone() {
	        return telefone;
	    }

	    public void setTelefone(String telefone) {
	        this.telefone = telefone;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

		@Override
		public int hashCode() {
			return Objects.hash(documento, email, nome, telefone, tipo);
		}


		@Override
		public String toString() {
			return "Pessoa [nome=" + nome + ", cpf=" + documento + ", telefone=" + telefone + ", email=" + email + ", tipo="
					+ tipo + "]";
		}

	
}

