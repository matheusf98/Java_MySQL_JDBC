package br.com.mysql.jdbc.test;
import java.sql.Connection;
import java.sql.SQLException;

import br.com.mysql.jdbc.modelo.ConnectionFactory;

public class TestaConexao {

	public static void main(String[] args) throws SQLException {
			
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.recuperaConexao();
		
		System.out.println("Fechando conexão");
		connection.close();
	}

}
