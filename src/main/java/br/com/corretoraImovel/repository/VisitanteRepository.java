package br.com.corretoraImovel.repository;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import br.com.corretoraImovel.model.Pessoa;
import jakarta.validation.Valid;

public class VisitanteRepository extends Repository{
	
	private static List<Pessoa> visitantes = null;
	
	
	static {
		
		visitantes = new ArrayList<>();
		
		Pessoa p1 = new Pessoa("Clemerson","17011845602","999999990","email1@google.com","1");
		Pessoa p2 = new Pessoa("Lucas","19011545602","999999990","email2@google.com","1");
		
		visitantes.add(p1);
		visitantes.add(p2);
		
	}

	public static List<Pessoa> findAll(){
		return visitantes;
	}
	
	//Consulta que chama banco de dados
	public static List<Pessoa> consultaTodosVisitantes(){
		
			String sql = "SELECT * FROM PESSOA";

			PreparedStatement ps = null;

			ResultSet rs = null;

			List<Pessoa> visitantes = new ArrayList<>();

			try {
				ps = getConnection().prepareStatement(sql);

				rs = ps.executeQuery();

				if (rs.isBeforeFirst()) {
					while (rs.next()) {

						Pessoa pessoaVisitante = new Pessoa();

						pessoaVisitante.setId(rs.getLong("ID"));
						pessoaVisitante.setNome(rs.getString("NOME"));
						pessoaVisitante.setDocumento(rs.getString("CPF"));
						pessoaVisitante.setTelefone(rs.getString("TELEFONE"));
						pessoaVisitante.setEmail(rs.getString("EMAIL"));
						pessoaVisitante.setTipo(rs.getString("TIPO"));


						visitantes.add(pessoaVisitante);
					}
				} else {
					System.out.println("Não foram encontrados registros na tabela do banco de dados.");
				}

			} catch (SQLException e) {
				System.out.println("Não foi possível consultar a listagem de visitantes: " + e.getMessage());
			}

			return visitantes;
	}
	
	public static Pessoa save(Pessoa visitante) {

		String sql1 = "SELECT MAX (PESSOA.ID) FROM PESSOA";
		// @formatter:off
 		String sql = "INSERT INTO pessoa ("
 				+ "    id,"
				+ "    nome,"
				+ "    cpf,"
				+ "    telefone,"
				+ "    email,"
				+ "    tipo"
				+ ") VALUES ("
				+ "    ?,"
				+ "    ?,"
				+ "    ?,"
				+ "    ?,"
				+ "    ?,"
				+ "    ?"
				+ ")";
 		// @formatter:on
 		
 		Long nextID_from_seq = null;
		CallableStatement cs = null;
		PreparedStatement pstmt = null; 
	
		try {
			pstmt = getConnection().prepareStatement(sql1);

			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				nextID_from_seq = rs.getLong(1);
			
			}
			
			pstmt = getConnection().prepareStatement(sql);
			pstmt.setLong(1, nextID_from_seq+1);
			pstmt.setString(2, visitante.getNome());
			pstmt.setString(3, visitante.getDocumento());
			pstmt.setString(4, visitante.getTelefone());
			pstmt.setString(5, visitante.getEmail());
			pstmt.setString(6, visitante.getTipo());
			
			pstmt.executeUpdate();
			
			visitante.setId((long) nextID_from_seq+1);
			
			return visitante;

		} catch (SQLException e) {
			System.out.println("Erro para salvar o visitante no banco de dados: " + e.getMessage());
		} finally {
			if (cs != null)
				try {
					cs.close();
				} catch (SQLException e) {
					System.out.println("Não foi possível fechar o Callable Statment: " + e.getMessage());
				}
		}

		return null;

	}

	public static boolean delete(Long VisitanteId) {

		Pessoa Pessoa = null;
		String sql = "DELETE FROM Pessoa where ID = ?";
		PreparedStatement ps = null;

		Pessoa = findById(VisitanteId);

		if (Pessoa == null) {
			return false;
		}

		try {
			ps = getConnection().prepareStatement(sql);

			ps.setLong(1, VisitanteId);

			ps.executeUpdate();

			return true;

		} catch (SQLException e) {
			System.out.println("Erro para deletar o visitante no banco de dados: " + e.getMessage());
		} finally {

			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					System.out.println("Erro ao fechar o Prepared Statment: " + e.getMessage());
				}
		}

		return false;
	}

	public static Pessoa findById(Long id) {
		String sql = "SELECT * FROM Pessoa where id = ?";

		PreparedStatement ps = null;

		ResultSet rs = null;

		try {

			ps = getConnection().prepareStatement(sql);

			ps.setLong(1, id);

			rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
				Pessoa visitante = new Pessoa();
				while (rs.next()) {
					visitante.setId(rs.getLong("ID"));
					visitante.setNome(rs.getString("NOME"));
					visitante.setTelefone(rs.getString("TELEFONE"));
					visitante.setEmail(rs.getString("EMAIL"));
					visitante.setTipo(rs.getString("TIPO"));
				}

				return visitante;
			}

		} catch (SQLException e) {
			System.out.println("Erro para consultar o visitante no banco de dados: " + e.getMessage());
		} finally {

			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					System.out.println("Erro ao fechar o Prepared Statment: " + e.getMessage());
				}

			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					System.out.println("Erro ao fechar o ResultSet: " + e.getMessage());
				}

		}

		return null;

	}

	public static Pessoa update(@Valid Pessoa visitante) {

		String sql = "UPDATE Pessoa set NOME=?, CPF =? , TELEFONE= ?, EMAIL= ? , TIPO= ? where id = ?";

		CallableStatement cs = null;

		try {
			cs = getConnection().prepareCall(sql);

			cs.setString(1, visitante.getNome());
			cs.setString(2, visitante.getDocumento());
			cs.setString(3, visitante.getTelefone());
			cs.setString(4, visitante.getEmail());
			cs.setString(5, visitante.getTipo());
			cs.setLong(6, visitante.getId());
			cs.executeUpdate();

			return visitante;

		} catch (SQLException e) {
			System.out.println("Erro ao atualizar o visitante no banco de dados: " + e.getMessage());
			if (cs != null)
				try {
					cs.close();
				} catch (SQLException ex) {
					System.out.println("Não foi possível fechar o Callable Statment: " + ex.getMessage());
				}
		}

		return null;
	}
}
