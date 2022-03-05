package br.com.lojaapi.aplicacao;

import br.com.lojaapi.cliente.ProdutoCliente;
import br.com.lojaapi.cliente.domain.Produto;

import java.text.SimpleDateFormat;
import java.util.List;

public class Aplicacao {
    public static void main(String[] args) throws Exception {

        ProdutoCliente cliente = new ProdutoCliente("http://localhost:8080",
                "adm","s3nh4");

        List<Produto> listaProduto = cliente.listar();

        for (Produto produto: listaProduto) {
            System.out.println("Prdouto: "+produto.getNome());
        }
        Produto produto = new Produto();
        produto.setNome("Java");
        produto.setEditora("Estude");
        SimpleDateFormat publicacao = new SimpleDateFormat("dd/MM/yyyy");
        produto.setPublicacao(publicacao.parse("04/04/2022"));
        produto.setResumo("Esse livro fala sobre java");

        String localizacao = cliente.salvar(produto);
        System.out.println("URI do livro saldo: "+ localizacao);

        Produto produtoBuscado = cliente.buscar(localizacao);

        System.out.println("Produto buscado: "+ produtoBuscado.getNome());
    }


}
