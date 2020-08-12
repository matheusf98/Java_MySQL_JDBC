package br.com.mysql.jdbc.test;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.mysql.jdbc.dao.ProdutoDAO;
import br.com.mysql.jdbc.modelo.ConnectionFactory;
import br.com.mysql.jdbc.modelo.Produto;

public class TesteInsercaoDeProduto {

	public static void main(String[] args) throws SQLException {
		
		Produto cama = new Produto("CAMA DE CASAL", "BOX-12-MOLAS");
		
		try(Connection connection = new ConnectionFactory().recuperaConexao()){
			ProdutoDAO ProdutoDao = new ProdutoDAO(connection);
			ProdutoDao.salvaProduto(cama);
			List<Produto> listaDeProdutos = ProdutoDao.listar();
			listaDeProdutos.stream().forEach(lista ->{
				System.out.println(lista);
			});
		}
	}
}
