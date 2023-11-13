package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DbConnect;
import model.Produto;

@WebServlet("/ProdutoServlet")
public class ProdutoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Sempre obter todos os produtos na primeira vez que a página é acessada
        List<Produto> produtos = obterProdutosDoBanco();

        // Verifica se há um parâmetro de busca no URL
        String searchTerm = request.getParameter("search");

        if (searchTerm != null && !searchTerm.isEmpty()) {
            // Se houver um termo de pesquisa, chama o método de busca por nome
            produtos = buscarProdutosPorNome(searchTerm);
        }


        // Define a lista de produtos no request e encaminha para o JSP
        request.setAttribute("listaDeProdutos", produtos);
        request.getRequestDispatcher("/ProdutoForm.jsp").forward(request, response);
    }

    private void excluirProduto(int codigo) {
        try (Connection connection = DbConnect.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM produto WHERE codigo = ?")) {
            preparedStatement.setInt(1, codigo);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Método para obter um produto pelo código
    private Produto obterProdutoPorCodigo(int codigo) {
        Produto produto = null;
        try (Connection connection = DbConnect.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM produto WHERE codigo = ?")) {
            preparedStatement.setInt(1, codigo);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                produto = new Produto();
                produto.setCodigo(resultSet.getInt("codigo"));
                produto.setNome(resultSet.getString("nome"));
                produto.setDescricao(resultSet.getString("descricao"));
                produto.setValorVenda(resultSet.getDouble("valorVenda"));
                produto.setMarca(resultSet.getString("marca"));
                produto.setModelo(resultSet.getString("modelo"));
                produto.setAnoFabricacao(resultSet.getString("anoFabricacao"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return produto;
    }

    // Método para buscar produtos por nome no banco de dados
    private List<Produto> buscarProdutosPorNome(String nome) {
        List<Produto> produtos = new ArrayList<>();
        try (Connection connection = DbConnect.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM produto WHERE nome LIKE ?");) {
            preparedStatement.setString(1, "%" + nome + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Produto produto = new Produto();
                produto.setCodigo(resultSet.getInt("codigo"));
                produto.setNome(resultSet.getString("nome"));
                produto.setDescricao(resultSet.getString("descricao"));
                produto.setValorVenda(resultSet.getDouble("valorVenda"));
                produto.setMarca(resultSet.getString("marca"));
                produto.setModelo(resultSet.getString("modelo"));
                produto.setAnoFabricacao(resultSet.getString("anoFabricacao"));
                produtos.add(produto);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return produtos;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    // Processamento dos dados do formulário e inserção no banco de dados
    String acao = request.getParameter("acao");

    if ("atualizar".equals(acao)) {
        // Se a ação for atualizar, obtém os parâmetros do formulário de edição
        String codigoProduto = request.getParameter("codigo");
        String nomeProduto = request.getParameter("nome");
        String descricaoProduto = request.getParameter("descricao");
        String valorVendaProduto = request.getParameter("valorVendaEditar");
        String marcaProduto = request.getParameter("marca");
        String modeloProduto = request.getParameter("modelo");
        String anoFabricacaoProduto = request.getParameter("anoFabricacao");

        if (codigoProduto != null && !codigoProduto.isEmpty() && nomeProduto != null) {
            // Converte o código para um inteiro
            int codigo = Integer.parseInt(codigoProduto);

            // Chama o método para atualizar o produto
            atualizarProduto(codigo, nomeProduto, descricaoProduto, valorVendaProduto, marcaProduto,
                    modeloProduto, anoFabricacaoProduto);

            // Feche a modal após a atualização bem-sucedida
            response.getWriter().write("<script>window.close();</script>");
            return;
        }
    } else {
        // Se não for atualizar, insere um novo produto
        inserirNovoProduto(request.getParameter("nomeproduto"), request.getParameter("descricao"),
                request.getParameter("valorvenda"), request.getParameter("marca"), request.getParameter("modelo"),
                request.getParameter("anofabricacao"));
    }

    // Obtém todos os produtos do banco de dados
    List<Produto> produtos = obterProdutosDoBanco();

    // Define a lista de produtos no request e encaminha para o JSP
    request.setAttribute("listaDeProdutos", produtos);
    request.getRequestDispatcher("/ProdutoForm.jsp").forward(request, response);
}

    // Método para atualizar um produto
    private void atualizarProduto(int codigo, String nome, String descricao, String valorVendaEditar, String marca,
            String modelo, String anoFabricacao) {
        try (Connection connection = DbConnect.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "UPDATE produto SET nome = ?, descricao = ?, valorVenda = ?, marca = ?, modelo = ?, anoFabricacao = ? WHERE codigo = ?")) {

        	
            // Preenche os parâmetros no PreparedStatement
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, descricao);
            preparedStatement.setDouble(3, (valorVendaEditar != null && !valorVendaEditar.trim().isEmpty()) ? Double.parseDouble(valorVendaEditar.trim()) : 0.0);
            preparedStatement.setString(4, marca);
            preparedStatement.setString(5, modelo);
            preparedStatement.setString(6, anoFabricacao);
            preparedStatement.setInt(7, codigo);

            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Método para obter todos os produtos do banco de dados
    private List<Produto> obterProdutosDoBanco() {
        List<Produto> produtos = new ArrayList<>();
        try (Connection connection = DbConnect.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM produto");
                ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Produto produto = new Produto();
                produto.setCodigo(resultSet.getInt("codigo"));
                produto.setNome(resultSet.getString("nome"));
                produto.setDescricao(resultSet.getString("descricao"));
                produto.setValorVenda(resultSet.getDouble("valorVenda"));
                produto.setMarca(resultSet.getString("marca"));
                produto.setModelo(resultSet.getString("modelo"));
                produto.setAnoFabricacao(resultSet.getString("anoFabricacao"));
                produtos.add(produto);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return produtos;
    }

    // Método para inserir um novo produto no banco de dados
    private void inserirNovoProduto(String nome, String descricao, String valorVenda, String marca, String modelo,
            String anoFabricacao) {
        try (Connection connection = DbConnect.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO produto (nome, descricao, valorVenda, marca, modelo, anoFabricacao) VALUES (?, ?, ?, ?, ?, ?)")) {

            // Verifica se as variáveis não são nulas antes de usar
            if (nome != null) {
                preparedStatement.setString(1, nome);
            }

            if (descricao != null) {
                preparedStatement.setString(2, descricao);
            }

            if (valorVenda != null) {
                preparedStatement.setDouble(3, Double.parseDouble(valorVenda));
            }

            if (marca != null) {
                preparedStatement.setString(4, marca);
            }

            if (modelo != null) {
                preparedStatement.setString(5, modelo);
            }

            if (anoFabricacao != null) {
                preparedStatement.setString(6, anoFabricacao);
            }

            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
