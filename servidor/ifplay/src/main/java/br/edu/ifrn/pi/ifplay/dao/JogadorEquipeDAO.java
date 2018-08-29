
package br.edu.ifrn.pi.ifplay.dao;

import br.edu.ifrn.pi.ifplay.dominio.Curso;
import br.edu.ifrn.pi.ifplay.dominio.JogadorEquipe;
import br.edu.ifrn.pi.ifplay.dominio.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class JogadorEquipeDAO {

	public boolean inserir(JogadorEquipe je){

		boolean resultado = false;
		String inserir = "INSERT INTO Jogador_Equipe(matricula, equipe_nome, titular) VALUES(?,?,?)";


		Connection con = Conexao.conectar();              

		try {

			PreparedStatement comando = con.prepareStatement(inserir);

			comando.setString(1, je.getMatricula());                                  
			comando.setString(2, je.getEquipeNome());                                
			comando.setBoolean(3, je.isTitular());


			comando.execute();
			resultado = true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			Conexao.desconectar();
		}


		return resultado;
	}



	public boolean remover(JogadorEquipe je){

		boolean resultado = false;
		String remover = "DELETE FROM Jogador_Equipe WHERE matricula = ? and equipe_nome = ? ";   


		Connection con = Conexao.conectar();

		try {

			PreparedStatement comando = con.prepareStatement(remover);

			comando.setString(1, je.getMatricula()); 
			comando.setString(2, je.getEquipeNome());

			comando.execute();
			resultado = true;

		} catch (SQLException e) {

			e.printStackTrace();
		}

		finally {
			Conexao.desconectar();
		}


		return resultado;
	}

	public boolean atualizar(JogadorEquipe je){

		boolean resultado = false;

		String atualizar = "UPDATE Jogador_Equipe set titular = ? WHERE matricula = ? and equipe_nome = ?";


		Connection con = Conexao.conectar();

		try {


			PreparedStatement comando = con.prepareStatement(atualizar);

			comando.setBoolean(1, je.isTitular());
			comando.setString(2, je.getMatricula());
			comando.setString(3, je.getEquipeNome());


			comando.execute();
			resultado = true;

		} catch (SQLException e) {

			e.printStackTrace();
		}

		finally {
			Conexao.desconectar();
		}


		return resultado;
	} 

	public ArrayList<Usuario> buscarJogadoresEquipe(String nomeEquipe) {

		ArrayList<Usuario> lista = new  ArrayList<Usuario>();

		String queryInserir = "select t1.nome , t1.matricula,apelidio,suspenso,foto, t2.titular,t1.idCurso, t4.nome as nomeCurso from Usuario t1 \n" +
				"inner join Jogador_Equipe t2 on (t1.matricula = t2.matricula)\n" +
				"inner join Equipe t3 on (t2.equipe_nome = t3.nome) inner join Curso t4 on (t1.idCurso = t4.idCurso)\n" +
				"where t3.nome = ?;";


		Connection con = Conexao.conectar();

		try {

			PreparedStatement comando = con.prepareStatement(queryInserir);
			comando.setString(1, nomeEquipe);

			ResultSet rSet = comando.executeQuery();

			while(rSet.next()){

				Usuario u2 = new Usuario();

				u2.setNome(rSet.getString("nome"));
				u2.setMatricula(rSet.getString("matricula"));
				u2.setApelidio(rSet.getString("apelidio"));
				u2.setFoto(rSet.getString("foto"));
				u2.setSuspenso(rSet.getBoolean("suspenso"));
				u2.setTitular(rSet.getBoolean("titular"));

                Curso curso = new Curso();
                curso.setIdCurso(rSet.getInt("idCurso"));
                curso.setNomeCurso(rSet.getString("nomeCurso"));

                u2.setCurso(curso);
				lista.add(u2);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		finally {
			Conexao.desconectar();
		}

		return lista;
	}


}
