package br.com.mysql.jdbc.test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.mysql.jdbc.modelo.ConnectionFactory;

public class TestaRemocao {

	public static void main(String[] args) throws SQLException {

		ConnectionFactory factoty = new ConnectionFactory();
		Connection connection = factoty.recuperaConexao();
		
		PreparedStatement stat = connection.prepareStatement("DELETE FROM PRODUTO WHERE ID > ?");
		stat.setInt(1, 2);
		stat.execute();
		
		int quantidadesDeLinhas = stat.getUpdateCount();
		
		System.out.println(quantidadesDeLinhas);
	}
}
