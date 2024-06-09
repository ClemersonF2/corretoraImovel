package br.com.corretoraImovel.services;

import br.com.corretoraImovel.model.Agendamento;
import br.com.corretoraImovel.model.Imovel;
import br.com.corretoraImovel.model.Pessoa;
import br.com.corretoraImovel.repository.AgendamentoRepository;
import br.com.corretoraImovel.utils.Utils;

import java.sql.SQLException;

public class AgendamentoService {

    static AgendamentoRepository agendamentoRepository = new AgendamentoRepository();
    static Utils validarCPF = new Utils();

    public static boolean existe(Long id) throws SQLException {
        return AgendamentoRepository.findById(id) != null ? true : false;
    }

    public static Agendamento findById(Long id) throws SQLException {
        return AgendamentoRepository.findById(id);
    }

    public static Agendamento save(Agendamento agendamento) {

        Agendamento agendamentoRepository = null;
        try {
            if(validacaoAgendamento(agendamento) == false) {
                return null;
            }
            else {
                agendamentoRepository = AgendamentoRepository.save(agendamento);
                return agendamentoRepository;
            }
        }
        catch (RuntimeException ex) {
            System.out.println("Exceção de runtime ao salvar agendamento: "+ ex);
        } catch (Exception ex) {
            System.out.println("Exceção ao salvar agendamento: "+ex);
        }

        return agendamentoRepository;

    }

    public static Agendamento update(Long id, Agendamento agendamento) throws SQLException {

        Agendamento velho = AgendamentoRepository.findById(id);
        Agendamento novo = null;
        if (velho == null || velho.getId() != agendamento.getId()) {

            return AgendamentoRepository.save(agendamento);
        }
        novo = AgendamentoRepository.update(agendamento);
        return novo;
    }

    public static boolean delete(Long agendamentoId) throws SQLException {
        if (existe(agendamentoId)) {
            return AgendamentoRepository.delete(agendamentoId);
        } else {
            return false;
        }

    }

    public static boolean validacaoAgendamento(Agendamento agendamento) {
        boolean retorno = false;

        if(validacaoVisitante(agendamento.getPessoa())) {
            if(agendamento.getData().equals("") || agendamento.getHora().equals("")) {
                retorno = false;
            } else
                retorno = true;
        }
        return retorno;
    }

    public static boolean validacaoVisitante(Pessoa visitante) {
        boolean retorno = false;

        if(visitante.getDocumento().equals("") || visitante.getNome().equals("") || visitante.getTelefone().equals("")) {
            return retorno = false;
        }
        else if (!validarCPF.validarCPF(visitante.getDocumento())){
            System.out.println("CPF inválido.");
            retorno = false;
        }
        else
            retorno = true;

        return retorno;
    }



    public static boolean validacaoImovel(Imovel imovel) {
        boolean retorno = false;

        if(imovel.getDormitorios() <= 0 || imovel.getValorAluguel() <= 0 || imovel.getValorCondominio() <= 0 || imovel.getValorIptu() <= 0) {
            return retorno = false;
        }
        else{
            retorno = true;

        return retorno;}
    }

}



