package br.com.lojaapi.cliente;

import br.com.lojaapi.cliente.domain.Produto;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public class ProdutoCliente {

    private RestTemplate restTemplate;
    private String URI_BASE;
    private String URN_BASE = "/produtos";
    private String credencial;
    public ProdutoCliente(String url,String usuario, String senha){
        restTemplate = new RestTemplate();

        URI_BASE = url.concat(URN_BASE);

        String credencialAux = usuario + ":" + senha;

        credencial = "Basic " + Base64.getEncoder()
                .encodeToString(credencialAux.getBytes());
    }
    public List<Produto> listar() {

        RequestEntity<Void> request = RequestEntity
                .get(URI.create(URI_BASE))
                .header("Authorization",credencial).build();

        ResponseEntity<Produto[]> response = restTemplate.exchange(request, Produto[].class);

        return Arrays.asList(response.getBody());
    }
    public String salvar(Produto produto) {

        RequestEntity<Produto> request = RequestEntity
                .post(URI.create(URI_BASE))
                .header("Authorization",credencial)
                .body(produto);

        ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

        return response.getHeaders().getLocation().toString();
    }

    public Produto buscar(String uri) {
        RestTemplate restTemplate = new RestTemplate();

        RequestEntity<Void> request = RequestEntity
                .get(URI.create(uri))
                .header("Authorization",credencial)
                .build();

        ResponseEntity<Produto> response = restTemplate.exchange(request, Produto.class);

        return response.getBody();
    }
}
