
package br.edu.ifrn.pi.ifplay.dao;

import br.edu.ifrn.pi.ifplay.dominio.Modalidade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ModalidadeDAO {
    
    
    
   public ArrayList<Modalidade> listarModalidades(){
       
      ArrayList<Modalidade> modalidades = new ArrayList<>();
      
      String queryInserir = "SELECT nome, idModalidade FROM Modalidade";


		Connection con = Conexao.conectar();

		try {
			
			PreparedStatement comando = con.prepareStatement(queryInserir);
		        ResultSet rSet = comando.executeQuery();

			while(rSet.next()){

				Modalidade m2 = new Modalidade();

				m2.setNome(rSet.getString("nome"));
                                m2.setIdModalidade(rSet.getInt("idModalidade"));
							
				modalidades.add(m2);

			}

		} catch (SQLException e) {
		
			e.printStackTrace();
		}

		finally {
			Conexao.desconectar();
		}
    
      
      
      return modalidades;      
       
   }
    
    
    
    
    
}
