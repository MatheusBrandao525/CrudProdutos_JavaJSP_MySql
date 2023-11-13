<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Cadastro de Produtos</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="css/style.css">

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css"
	integrity="sha512-5A8nwdMOWrSz20fDsjczgUidUBR8liPYU+WymTZP1lmY9G6Oc7HlZv156XqnsgNUzTyMefFTcsFH/tnJE/+xBg=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
</head>
<body>
	<div class="container mt-4">
		<center>
			<h2>Cadastro de Produtos</h2>
		</center>
		<!-- Formulário de Cadastro -->
		<form method="post" action="ProdutoServlet">
			<div class="container forn-dlt">
				<div class="row g-3 align-items-center">
					<div class="col-auto col-lg-4">
						<label for="" class="col-form-label">Produto Nome :</label>
					</div>
					<div class="col-auto col-lg-8">
						<input type="text" id="" name="nomeproduto" class="form-control"
							aria-describedby="">
					</div>
				</div>

				<div class="row g-3 align-items-center">
					<div class="col-auto col-lg-4">
						<label for="" class="col-form-label">Descrição :</label>
					</div>
					<div class="col-auto col-lg-8">
						<input type="text" id="" name="descricao" class="form-control"
							aria-describedby="">
					</div>
				</div>

				<div class="row g-3 align-items-center">
					<div class="col-auto col-lg-4">
						<label for="" class="col-form-label">Valor de Venda :</label>
					</div>
					<div class="col-auto col-lg-8">
						<input type="text" id="" name="valorvenda" class="form-control"
							aria-describedby="">
					</div>
				</div>

				<div class="row g-3 align-items-center">
					<div class="col-auto col-lg-4">
						<label for="" class="col-form-label">Marca :</label>
					</div>
					<div class="col-auto col-lg-8">
						<select class="form-select" name="marca"
							aria-label="Default select example">
							<option selected>Selecione uma marca</option>
							<option value="Marca1">Marca1</option>
							<option value="Marca2">Marca2</option>
							<option value="Marca3">Marca3</option>
						</select>
					</div>
				</div>


				<div class="row g-3 align-items-center">
					<div class="col-auto col-lg-4">
						<label for="" class="col-form-label">Modelo :</label>
					</div>
					<div class="col-auto col-lg-8">
						<select class="form-select" name="modelo"
							aria-label="Default select example">
							<option selected>Selecione um modelo</option>
							<option value="Modelo1">Modelo1</option>
							<option value="Modelo2">Modelo2</option>
							<option value="Modelo3">Modelo3</option>
						</select>
					</div>
				</div>

				<div class="row g-3 align-items-center">
					<div class="col-auto col-lg-4">
						<label for="" class="col-form-label">Ano de Fabricação :</label>
					</div>
					<div class="col-auto col-lg-8">
						<input type="text" id="" name="anofabricacao" class="form-control"
							aria-describedby="">
					</div>
				</div>


				<div class="row g-3 align-items-centerr">
					<div class="col-auto col-lg-4">
						<label for="" class="col-form-label"></label>
					</div>
					<div class="col-auto col-lg-8">
						<input type="submit" value="Cadastrar" name="">
					</div>
				</div>

			</div>
		</form>

		<hr>

		<!-- Barra de Pesquisa -->
		<form action="ProdutoServlet" method="get">
			<div class="form-group">
				<label for="search">Buscar Produto:</label> <input type="text"
					class="form-control" id="search" name="search">
			</div>
			<button type="submit" class="btn btn-secondary">Pesquisar</button>
		</form>

		<hr>

		<!-- Exibição dos Produtos Cadastrados -->
		<h2>Produtos Cadastrados</h2>


		<table class="table">
			<thead style="color: white;">
				<tr bgcolor="#120671">
					<th scope="col">Código</th>
					<th scope="col">Nome</th>
					<th scope="col">Descição</th>
					<th scope="col">Vlr.Venda</th>
					<th scope="col">Marca</th>
					<th scope="col">Modelo</th>
					<th scope="col">Ano Fabricação</th>
					<th scope="col">Opções</th>
				</tr>
			</thead>
			<div class="red"></div>
			<tbody>

				<c:forEach items="${listaDeProdutos}" var="produto">

					<tr bgcolor="#bffef4">
						<td scope="col"><c:out value="${produto.codigo}"></c:out></td>
						<td scope="col"><c:out value="${produto.nome}"></c:out></td>
						<td scope="col"><c:out value="${produto.descricao}"></c:out>
						</td>
						<td scope="col"><c:out value="${produto.valorVenda}"></c:out></td>
						<td scope="col"><c:out value="${produto.marca}"></c:out></td>
						<td scope="col"><c:out value="${produto.modelo}"></c:out></td>
						<td scope="col"><c:out value="${produto.anoFabricacao}"></c:out></td>
						<td scope="col"><a
							href="ProdutoServlet?acao=excluir&codigo=${produto.codigo}">
								<i class="fa fa-trash" aria-hidden="true"></i>
						</a>
							<a href="#" class="edit-btn" data-toggle="modal"
							data-target="#editarProdutoModal" data-codigo="${produto.codigo}"
							data-nome="${produto.nome}" data-descricao="${produto.descricao}"
							data-valorvenda="${produto.valorVenda}"
							data-marca="${produto.marca}" data-modelo="${produto.modelo}"
							data-anofabricacao="${produto.anoFabricacao}"> <i
								class="fa fa-edit" aria-hidden="true"></i>
						</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<div class="modal fade" id="editarProdutoModal" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Editar Produto</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form id="editarProdutoForm" action="ProdutoServlet" method="post">
						<input type="hidden" name="acao" value="atualizar"> <input
							type="hidden" name="codigo" id="codigoProdutoEditar">

						<div class="form-group">
							<label for="nomeEditar">Nome do Produto:</label> <input
								type="text" class="form-control" id="nomeEditar" name="nome"
								required>
						</div>

						<div class="form-group">
							<label for="descricaoEditar">Descrição:</label>
							<textarea class="form-control" id="descricaoEditar"
								name="descricao" rows="3" required></textarea>
						</div>

						<div class="form-group">
							<label for="valorVendaEditar">Valor de Venda:</label> <input
								type="text" class="form-control" id="valorVendaEditar"
								name="valorVendaEditar" required>
						</div>

						<div class="form-group">
							<label for="marcaEditar">Marca:</label> <select
								class="form-select" id="marcaEditar" name="marca" required>
								<option></option>
								<option value="Marca1">Marca1</option>
								<option value="Marca2">Marca2</option>
								<option value="Marca3">Marca3</option>
							</select>
						</div>

						<div class="form-group">
							<label for="modeloEditar">Modelo:</label> <select
								class="form-select" id="modeloEditar" name="modelo" required>
								<option></option>
								<option value="Modelo1">Modelo1</option>
								<option value="Modelo2">Modelo2</option>
								<option value="Modelo3">Modelo3</option>
							</select>
						</div>

						<div class="form-group">
							<label for="anoFabricacaoEditar">Ano de Fabricação:</label> <input
								type="text" class="form-control" id="anoFabricacaoEditar"
								name="anoFabricacao" required>
						</div>

						<button type="submit" class="btn btn-primary">Atualizar</button>
					</form>
				</div>
			</div>
		</div>
	</div>


	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

	<script>
		$(document).ready(function() {
			$('.edit-btn').click(function() {

				// Obter os valores do produto a partir dos atributos de dados
				var codigoProduto = $(this).data('codigo');
				var nomeProduto = $(this).data('nome');
				var descricaoProduto = $(this).data('descricao');
				var valorVendaProduto = $(this).data('valorvenda');
				var marcaProduto = $(this).data('marca');
				var modeloProduto = $(this).data('modelo');
				var anoFabricacaoProduto = $(this).data('anofabricacao');

				// Preencher os campos do formulário modal
				$('#codigoProdutoEditar').val(codigoProduto);
				$('#nomeEditar').val(nomeProduto);
				$('#descricaoEditar').val(descricaoProduto);
				$('#valorVendaEditar').val(valorVendaProduto);

				$('#marcaEditar').val(marcaProduto);
				$('#modeloEditar').val(modeloProduto);
				$('#anoFabricacaoEditar').val(anoFabricacaoProduto);

				// Abrir a modal
				$('#editarProdutoModal').modal('show');

				console.log(marcaProduto);
			});
		});
	</script>

	<script>
		$(document).ready(function() {
			// Evento chamado quando a modal é fechada
			$('#editarProdutoModal').on('hidden.bs.modal', function() {
				// Remove os estilos de fundo aplicados pela modal
				$('body').removeClass('modal-open');
				$('.modal-backdrop').remove();
			});
		});
	</script>


</body>
</html>
