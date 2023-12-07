package cadastro.service;

import java.io.Serializable;

import javax.inject.Inject;

import cadastro.model.Endereco;
import cadastro.model.Usuario;
import cadastro.repository.Enderecos;
import cadastro.repository.Usuarios;
import cadastro.util.Transacional;

public class CadastroEnderecoService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Enderecos enderecos;    

    @Transacional
    public void salvar(Endereco endereco) {
        enderecos.guardar(endereco);
    }

    @Transacional
    public void excluir(Endereco endereco) {
    	enderecos.remover(endereco);
    }

}