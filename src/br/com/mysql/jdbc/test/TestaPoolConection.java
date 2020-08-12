package br.com.mysql.jdbc.test;

import java.sql.SQLException;

import br.com.mysql.jdbc.modelo.ConnectionFactory;

public class TestaPoolConection {

	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory factory = new ConnectionFactory();
		
		for (int i = 1; i < 20; i++) {
			factory.recuperaConexao();
			System.out.println("Conexão numero: " + i);
		}
	}

}
