package br.com.corretoraImovel.services;

import br.com.corretoraImovel.model.Pessoa;
import br.com.corretoraImovel.repository.VisitanteRepository;

public class ProprietarioService {
	
	static VisitanteRepository visitanteRepository = new VisitanteRepository();
	
	public static boolean existe(Long id) {
		return VisitanteRepository.findById(id) != null ? true : false;
	}

	public static Pessoa findById(Long id) {
		return VisitanteRepository.findById(id);
	}

	public static Pessoa save(Pessoa visitante) {
	
		Pessoa visitanteRepository = null;
		try {
			if(validacaoVisitante(visitante) == false) {
				return null;
			}
			else {
				visitanteRepository = VisitanteRepository.save(visitante);
				return visitanteRepository;
				}
		}
		catch (RuntimeException ex) {
	           System.out.println("Exceção de runtime ao salvar visitante: "+ ex);
	    } catch (Exception ex) {
	    	System.out.println("Exceção ao salvar visitante: "+ex);
	    }	
		
		return visitanteRepository;
		
	}

	public static Pessoa update(Long id, Pessoa visitante) {

		Pessoa velho = VisitanteRepository.findById(id);
		Pessoa novo = null;
		if (velho == null || velho.getId() != visitante.getId()) {
			return VisitanteRepository.save(visitante);
		}
		novo = VisitanteRepository.update(visitante);
		return novo;
	}

	public static boolean delete(Long coffeeId) {
		if (existe(coffeeId)) {
			return VisitanteRepository.delete(coffeeId);
		} else {
			return false;
		}

	}
	
	 public static boolean validacaoVisitante(Pessoa visitante) {
	        boolean retorno = false;

	        if(visitante.getDocumento().equals("") || visitante.getNome().equals("") || visitante.getTelefone().equals("")) {
	            return retorno = false;
	        }
	        else if (!validarCPF(visitante.getDocumento())){
	        	 System.out.println("CPF inválido.");
	        	retorno = false;
	        }
	        else 
	        	retorno = true;
	        
	        return retorno;
	    }
	 
	 public static boolean validarCPF(String cpf) {
	        // Remove caracteres não numéricos do CPF
	        cpf = cpf.replaceAll("[^0-9]", "");

	        // Verifica se o CPF tem 11 dígitos
	        if (cpf.length() != 11) 	        	
	            return false;
	   
	        // Verifica se todos os dígitos são iguais
	        boolean todosDigitosIguais = true;
	        for (int i = 1; i < 11; i++) {
	            if (cpf.charAt(i) != cpf.charAt(0)) {
	                todosDigitosIguais = false;
	                break;
	            }
	        }
	        if (todosDigitosIguais) 	        	
	            return false;
	        
	        // Calcula e compara os dígitos verificadores
	        int soma = 0;
	        int peso = 10;
	        for (int i = 0; i < 9; i++) {
	            soma += (cpf.charAt(i) - '0') * peso;
	            peso--;
	        }
	        int digito1 = 11 - (soma % 11);
	        if (digito1 > 9)
	            digito1 = 0;

	        soma = 0;
	        peso = 11;
	        for (int i = 0; i < 10; i++) {
	            soma += (cpf.charAt(i) - '0') * peso;
	            peso--;
	        }
	        int digito2 = 11 - (soma % 11);
	        if (digito2 > 9)
	            digito2 = 0;

	        // Verifica se os dígitos verificadores calculados são iguais aos fornecidos no CPF
	        return (digito1 == cpf.charAt(9) - '0' && digito2 == cpf.charAt(10) - '0');
	    }

}
