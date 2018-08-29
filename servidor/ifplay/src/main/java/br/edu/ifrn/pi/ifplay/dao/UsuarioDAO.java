package br.edu.ifrn.pi.ifplay.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifrn.pi.ifplay.dominio.Curso;
import br.edu.ifrn.pi.ifplay.dominio.Usuario;

public class UsuarioDAO {

	public boolean inserir(Usuario usuario){

		boolean resultado = false;
		String inserir = "INSERT INTO Usuario(nome, matricula, senha, idCurso, apelidio, foto, suspenso) VALUES(?,?,?,?,?,?,?)";


		Connection con = Conexao.conectar();

		try {

			PreparedStatement comando = con.prepareStatement(inserir);

			comando.setString(1, usuario.getNome());
			comando.setString(2, usuario.getMatricula());
			comando.setString(3, usuario.getSenha());
			comando.setInt(4, usuario.getCurso().getIdCurso());
			comando.setString(5, usuario.getApelidio());
			comando.setString(6, usuario.getFoto());
			comando.setBoolean(7, usuario.isSuspenso());




			comando.execute();
			resultado = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			Conexao.desconectar();
		}


		return resultado;
	} 




	public boolean remover(String matricula){

		boolean resultado = false;
		String remover = "DELETE FROM Usuario WHERE matricula = ?";


		Connection con = Conexao.conectar();

		try {

			PreparedStatement comando = con.prepareStatement(remover);

			comando.setString(1, matricula); 

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



	public boolean atualizar(Usuario usuario){

		boolean resultado = false;

		String atualizar = "UPDATE Usuario set nome = ?, matricula = ?, senha = ?, idCurso = ?, apelidio = ?, foto = ?,suspenso = ? "
				+ "WHERE matricula = ?";


		Connection con = Conexao.conectar();

		try {



			PreparedStatement comando = con.prepareStatement(atualizar);

			comando.setString(1, usuario.getNome());
			comando.setString(2, usuario.getMatricula());
			comando.setString(3, usuario.getSenha());
			comando.setInt(4, usuario.getCurso().getIdCurso());
			comando.setString(5, usuario.getApelidio());
			comando.setString(6, usuario.getFoto());
			comando.setBoolean(7, usuario.isSuspenso());


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


	public Usuario buscarUsuario(String matricula) {	

		Usuario usuario1 = null;   

		String queryInserir = "SELECT nome, matricula, senha, idCurso, apelidio, foto, suspenso FROM Usuario WHERE matricula = ?";


		Connection con = Conexao.conectar();

		try {


			PreparedStatement comando = con.prepareStatement(queryInserir);
			comando.setString(1, matricula);

			ResultSet rSet = comando.executeQuery();

			if(rSet.next()){
				usuario1 = new Usuario();
                Curso curso = new Curso();
                curso.setIdCurso(rSet.getInt("idCurso"));
                
                
				usuario1.setNome(rSet.getString("nome"));
				usuario1.setMatricula(rSet.getString("matricula"));
				usuario1.setSenha(rSet.getString("senha"));
				usuario1.setApelidio(rSet.getString("apelidio"));
				usuario1.setFoto(rSet.getString("foto"));
				usuario1.setSuspenso(rSet.getBoolean("suspenso"));
				usuario1.setCurso(curso);



			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		finally {
			Conexao.desconectar();
		}

		return usuario1;
	}


	public List<Usuario> buscarUsuarios(int idModalidade) {

		ArrayList<Usuario> lista = new  ArrayList<Usuario>();

		String queryInserir = "select  t1.matricula,apelidio,suspenso,foto,t4.nome,t4.idCurso from Usuario t1 \n" +
				"inner join Usuario_Modalidade t2 on (t1.matricula = t2.matricula)\n" +
				"inner join Modalidade t3 on (t2.idModalidade = t3.idModalidade) \n" +
				"inner join Curso t4 on (t1.idCurso = t4.idCurso)\n" +
				"where t3.idModalidade = ? ";
         System.out.println(idModalidade);

		Connection con = Conexao.conectar();

		try {

			PreparedStatement comando = con.prepareStatement(queryInserir);
			comando.setInt(1, idModalidade);
			ResultSet rSet = comando.executeQuery();

			while(rSet.next()){


				Curso c = new Curso();
				c.setNomeCurso(rSet.getString("nome"));
				c.setIdCurso(rSet.getInt("idCurso"));

				Usuario u2 = new Usuario();
				u2.setMatricula(rSet.getString("matricula"));
				u2.setApelidio(rSet.getString("apelidio"));
				u2.setFoto(rSet.getString("foto"));
				u2.setSuspenso(rSet.getBoolean("suspenso"));
				
				u2.setCurso(c);



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
