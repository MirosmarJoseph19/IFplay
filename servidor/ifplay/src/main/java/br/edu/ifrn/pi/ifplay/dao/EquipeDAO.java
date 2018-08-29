
package br.edu.ifrn.pi.ifplay.dao;

import br.edu.ifrn.pi.ifplay.dominio.Equipe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class EquipeDAO {
    
    	public boolean inserir(Equipe e){

		boolean resultado = false;
		String inserir = "INSERT INTO Equipe(nome,idModalidade) VALUES(?,?)";


		Connection con = Conexao.conectar();

		try {
			
			PreparedStatement comando = con.prepareStatement(inserir);
			
                        comando.setString(1, e.getNome());
                        comando.setInt(2, e.getIdModalidade());
						
			comando.execute();
			resultado = true;

		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}

		finally {
			Conexao.desconectar();
		}


		return resultado;
	}
        
        
        public boolean remover(Equipe e){

		boolean resultado = false;
		String remover = "DELETE FROM Equipe WHERE nome = ?";


		Connection con = Conexao.conectar();

		try {
			
			PreparedStatement comando = con.prepareStatement(remover);
			
			comando.setString(1, e.getNome()); 

			comando.execute();
			resultado = true;

		} catch (SQLException ex) {
			
			ex.printStackTrace();
		}

		finally {
			Conexao.desconectar();
		}


		return resultado;
	}
        
      
    public ArrayList<Equipe> listarEquipes(){
       
      ArrayList<Equipe> equipe = new ArrayList<>();
      
      String queryInserir = "SELECT nome, idModalidade FROM Equipe";


		Connection con = Conexao.conectar();

		try {
			
			PreparedStatement comando = con.prepareStatement(queryInserir);
		        ResultSet rSet = comando.executeQuery();

			while(rSet.next()){

				Equipe m2 = new Equipe();

				m2.setNome(rSet.getString("nome"));
                                m2.setIdModalidade(rSet.getInt("idModalidade"));
							
				equipe.add(m2);

			}

		} catch (SQLException e) {
		
			e.printStackTrace();
		}

		finally {
			Conexao.desconectar();
		}
    
      
      
      return equipe;      
       
   }
  
	


}
