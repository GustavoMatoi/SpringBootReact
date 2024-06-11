package br.com.api.produtos.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.produtos.model.ProdutoModel;
import br.com.api.produtos.model.RespostaModelo;
import br.com.api.produtos.repositorio.ProdutoRepositorio;

@Service
public class ProdutoServico {
    @Autowired
    ProdutoRepositorio pr;

    @Autowired
    private RespostaModelo rm;

    public Iterable<ProdutoModel> listar() {
        return pr.findAll();
    }

    //Método para cadastrar produtos
    public ResponseEntity<?> cadastrarAlterar(ProdutoModel pm, String acao) {
        if (pm.getNome() == "") {
            rm.setMensagem("O nome do produto é um campo obrigatório.");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        } else if (pm.getMarca() == "") {
            rm.setMensagem("O nome da marca é um campo obrigatório.");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        } else {
            if(acao == "cadastrar"){
                return new ResponseEntity<ProdutoModel>(pr.save(pm), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<ProdutoModel>(pr.save(pm), HttpStatus.OK);
            }
        }
    }

    //Remover produtos
    public ResponseEntity<RespostaModelo> remover(long codigo){
        pr.deleteById(codigo);
        rm.setMensagem("O produto foi removido com sucesso!");
        return new ResponseEntity<RespostaModelo>(rm, HttpStatus.OK);
    }

}
