package br.com.mysql.jdbc.test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.management.RuntimeErrorException;

import com.mysql.cj.xdevapi.PreparableStatement;

import br.com.mysql.jdbc.modelo.ConnectionFactory;

public class TestaInsercaoComParametro {

	public static void main(String[] args) throws SQLException {

		ConnectionFactory factoty = new ConnectionFactory();
		try (Connection connection = factoty.recuperaConexao()) {
			connection.setAutoCommit(false);

			try (PreparedStatement stat = connection.prepareStatement(
					"INSERT INTO PRODUTO (nome, descricacao) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);) {

				AdicionarVariavel("TV SANSUNG", "55 POLEGADAS/ 4K", stat);
				AdicionarVariavel("GUARDA ROUPAS", "ESPELHADO", stat);

				connection.commit();

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(" ROLLBACK EXECUTANDO");
				connection.rollback();
			}
		}
	}

	private static void AdicionarVariavel(String nome, String descricao, PreparedStatement stat) throws SQLException {
		stat.setString(1, nome);
		stat.setString(2, descricao);

		if (descricao.equals("ESPELHADO")) {
			throw new RuntimeException("ERRO DE COMMIT");
		}

		stat.execute();

		try (ResultSet res = stat.getGeneratedKeys();) {
			while (res.next()) {
				Integer id = res.getInt(1);
				System.out.println("O ID criado foi: " + id);
			}
		}
	}
}
