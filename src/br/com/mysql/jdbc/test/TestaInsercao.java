package br.com.mysql.jdbc.test;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.mysql.jdbc.modelo.ConnectionFactory;

public class TestaInsercao {
	
	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory factoty = new ConnectionFactory();
		Connection connection = factoty.recuperaConexao();
		
		Statement stat = connection.createStatement();
		boolean resultado = 
				stat.execute("INSERT INTO PRODUTO (nome, descricacao) VALUES ('MICROONDAS', 'MICROONDAS INOX')", Statement.RETURN_GENERATED_KEYS);
		System.out.println(resultado);
		
		ResultSet res = stat.getGeneratedKeys();
		while(res.next()) {
			Integer id = res.getInt(1);
			System.out.println("O ID criado foi: " + id);
		}
	}
	
}
