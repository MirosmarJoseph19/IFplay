
package br.edu.ifrn.pi.ifplay.dao;

import br.edu.ifrn.pi.ifplay.dominio.Liga;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class LigaDAO {
    
    
    public ArrayList<Liga> listarLigas(){
       
      ArrayList<Liga> ligas = new ArrayList<>();
      
      String queryInserir = "SELECT nome, idLiga FROM Liga";


		Connection con = Conexao.conectar();

		try {
			
			PreparedStatement comando = con.prepareStatement(queryInserir);
		        ResultSet rSet = comando.executeQuery();

			while(rSet.next()){

				Liga l2 = new Liga();

				l2.setNome(rSet.getString("nome"));
                                l2.setIdLiga(rSet.getInt("idLiga"));
							
				ligas.add(l2);

			}

		} catch (SQLException e) {
		
			e.printStackTrace();
		}

		finally {
			Conexao.desconectar();
		}
    
      
      
      return ligas;      
       
   }
    
    
    
    
    
    
    
    
    
    
    
}
