package br.com.mysql.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.mysql.jdbc.modelo.Categoria;
import br.com.mysql.jdbc.modelo.Produto;

public class CategoriaDAO {

	private Connection connection;

	public CategoriaDAO(Connection conexao) {
		this.connection = conexao;
	}

	public List<Categoria> listar() throws SQLException {
		List<Categoria> listaDeCategoria = new ArrayList<>();

		System.out.println("Executando a query listar categoria");

		String sql = "SELECT ID, NOME FROM CATEGORIA";

		try (PreparedStatement stat = connection.prepareStatement(sql)) {

			stat.execute();

			try (ResultSet res = stat.getResultSet()) {
				while (res.next()) {
					Categoria categoria = new Categoria(res.getInt(1), res.getString(2));

					listaDeCategoria.add(categoria);
				}
			}
		}
		return listaDeCategoria;
	}

	public List<Categoria> listarComProduto() throws SQLException {
		Categoria ultima = null;
		List<Categoria> listaDeCategoria = new ArrayList<>();

		System.out.println("Executando a query listar categoria");

		String sql = "SELECT C.ID, C.NOME, P.ID, P.NOME, P.DESCRICACAO FROM CATEGORIA C INNER JOIN"
				+ " PRODUTO P ON C.ID = P.CATEGORIA_ID";

		try (PreparedStatement stat = connection.prepareStatement(sql)) {

			stat.execute();

			try (ResultSet res = stat.getResultSet()) {
				while (res.next()) {
					if (ultima.equals(null) || !ultima.equals(res.getString(2))) {
						Categoria categoria = new Categoria(res.getInt(1), res.getString(2));

						ultima = categoria;
						listaDeCategoria.add(categoria);
					}
					Produto produto = new Produto(res.getInt(3), res.getString(4), res.getString(5));
					ultima.adiciona(produto);
				}
			}
		}
		return listaDeCategoria;
	}
}
