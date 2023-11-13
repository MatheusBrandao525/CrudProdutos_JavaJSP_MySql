package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.xdevapi.Statement;

import db.DbConnect;
import model.Produto;

public class ProdutoDao {
	
    Connection connection;
    
    public ProdutoDao() throws ClassNotFoundException, SQLException {
        connection = DbConnect.getConnection();
    }
	
	public void addProduto(Produto produto) {
	    try (PreparedStatement pst = connection.prepareStatement(
	            "INSERT INTO produto(nome, descricao, valorVenda, marca, modelo, anoFabricacao)" +
	            " values(?,?,?,?,?,?)")) {

	        pst.setString(1, produto.getNome());
	        pst.setString(2, produto.getDescricao());
	        pst.setString(3, produto.getValorVenda());
	        pst.setString(4, produto.getMarca());
	        pst.setString(5, produto.getModelo());
	        pst.setString(6, produto.getAnoFabricacao());

	        pst.executeUpdate();
	        
	        System.out.println("Produto Inserido com Sucesso!");

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public List<Produto> getAllProdutos(){
		
		List<Produto> produtos = new ArrayList<Produto>();
		
		try {
			
			java.sql.Statement statement = connection.createStatement();
			
			ResultSet rs = statement.executeQuery("SELECT * FROM produto");
			
			while(rs.next()) {
				
				Produto produto = new Produto();
				
				produto.setCodigo(rs.getInt("codigo"));
				produto.setNome(rs.getString("nome"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setValorVenda(rs.getString("valorVenda"));
				produto.setMarca(rs.getString("marca"));
				produto.setModelo(rs.getString("modelo"));
				produto.setAnoFabricacao(rs.getString("anoFabricacao"));
				
				produtos.add(produto);
			}
			
			System.out.println(produtos);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return produtos;
		
	}

}
