package br.com.mysql.jdbc.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.mysql.jdbc.dao.CategoriaDAO;
import br.com.mysql.jdbc.modelo.Categoria;
import br.com.mysql.jdbc.modelo.ConnectionFactory;
import br.com.mysql.jdbc.modelo.Produto;

public class TestaListagemCategoria {

	public static void main(String[] args) throws SQLException {

		try (Connection connection = new ConnectionFactory().recuperaConexao()) {
			CategoriaDAO categoriaDao = new CategoriaDAO(connection);
			List<Categoria> listaCategoria = categoriaDao.listarComProduto();
			listaCategoria.stream().forEach(categoria -> {
				System.out.println(categoria.getNome());
				for (Produto produto : categoria.getProdutos()) {
					System.out.println(categoria.getNome() + " - " + produto.getNome());
				}

			});
		}

	}

}
