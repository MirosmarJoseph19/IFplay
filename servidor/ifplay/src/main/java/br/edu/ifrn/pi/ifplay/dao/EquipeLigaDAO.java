
package br.edu.ifrn.pi.ifplay.dao;

import br.edu.ifrn.pi.ifplay.dominio.EquipeLiga;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class EquipeLigaDAO {
     
    public boolean inserir(EquipeLiga el){

		boolean resultado = false;
		String inserir = "INSERT INTO Equipe_Liga(idLiga, equipe_nome) VALUES(?,?)"; 


		Connection con = Conexao.conectar();            

		try {
			
			PreparedStatement comando = con.prepareStatement(inserir);
			
			comando.setInt(1, el.getIdLiga());                                 
			comando.setString(2, el.getEquipeNome());                                
                     

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


	
	public boolean remover(EquipeLiga el){

		boolean resultado = false;
		String remover = "DELETE FROM Equipe_Liga WHERE idLiga = ? and equipe_nome = ?";  


		Connection con = Conexao.conectar();

		try {
			
			PreparedStatement comando = con.prepareStatement(remover);
			
			
                        comando.setInt(1, el.getIdLiga());                                  
			comando.setString(2, el.getEquipeNome());  

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

}