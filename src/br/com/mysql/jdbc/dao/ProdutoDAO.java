package br.com.mysql.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.mysql.jdbc.modelo.Categoria;
import br.com.mysql.jdbc.modelo.Produto;

public class ProdutoDAO {

	private Connection connection;

	public ProdutoDAO(Connection conexao) {
		this.connection = conexao;
	}

	public void salvaProduto(Produto produto) throws SQLException {
		String sql = "INSERT INTO PRODUTO (NOME, DESCRICACAO) VALUES (?, ?)";

		try (PreparedStatement stat = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			stat.setString(1, produto.getNome());
			stat.setString(2, produto.getDescricao());

			stat.execute();

			try (ResultSet res = stat.getGeneratedKeys()) {
				while (res.next()) {
					produto.setId(res.getInt(1));
				}
			}
		}
	}

	public List<Produto> listar() throws SQLException {
		List<Produto> listaDeProdutos = new ArrayList<>();

		String sql = "SELECT ID, NOME, DESCRICACAO FROM PRODUTO";

		try (PreparedStatement stat = connection.prepareStatement(sql)) {

			stat.execute();

			try (ResultSet res = stat.getResultSet()) {
				while (res.next()) {
					Produto produto = new Produto(res.getInt(1), res.getString(2), res.getString(3));

					listaDeProdutos.add(produto);
				}
			}
		}
		return listaDeProdutos;
	}

	public List<Produto> buscar(Categoria categoria) throws SQLException {
		List<Produto> listaDeProdutos = new ArrayList<>();

		System.out.println("Executando a query listar produto por categoria");
		
		String sql = "SELECT ID, NOME, DESCRICACAO FROM PRODUTO WHERE CATEGORIA_ID = ?";

		try (PreparedStatement stat = connection.prepareStatement(sql)) {
			stat.setInt(1, categoria.getId());
			stat.execute();

			try (ResultSet res = stat.getResultSet()) {
				while (res.next()) {
					Produto produto = new Produto(res.getInt(1), res.getString(2), res.getString(3));

					listaDeProdutos.add(produto);
				}
			}
		}
		return listaDeProdutos;
	}
}
