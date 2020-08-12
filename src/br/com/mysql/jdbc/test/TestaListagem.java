package br.com.mysql.jdbc.test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.mysql.jdbc.modelo.ConnectionFactory;

public class TestaListagem {

	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.recuperaConexao();
		
		PreparedStatement stat = connection.prepareStatement("SELECT ID, NOME, DESCRICACAO FROM PRODUTO");
		stat.execute();
		
		ResultSet resu = stat.getResultSet();
		
		while(resu.next()) {
			Integer id = resu.getInt("ID");
			System.out.println(id);
			String nome = resu.getString("NOME");
			System.out.println(nome);
			String descricao = resu.getString("DESCRICACAO");
			System.out.println(descricao);
		}
	}
}
