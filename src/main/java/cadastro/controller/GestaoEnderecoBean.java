package cadastro.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import cadastro.model.Endereco;
import cadastro.model.Perfil;
import cadastro.model.Usuario;
import cadastro.repository.Enderecos;
import cadastro.repository.Perfis;
import cadastro.repository.Usuarios;
import cadastro.service.CadastroEnderecoService;
import cadastro.service.CadastroUsuarioService;
import cadastro.util.FacesMessages;



@Named
@ViewScoped
public class GestaoEnderecoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Usuario usuario;

	@Inject
	private FacesMessages messages;

	@Inject
	private Enderecos enderecos;
	
	@Inject
	private CadastroUsuarioService cadastroUsuarioService;
	
	@Inject
	private CadastroEnderecoService cadastroEnderecoService;		

	private List<Usuario> listaUsuarios;
	
	private List<Endereco> listaEnds;

	private String termoPesquisa;
	
	private Converter enderecoConverter;
	
	private Endereco endereco;		

	public void prepararNovoEndereco() {
			endereco = new Endereco();
	}

	public void prepararEdicao() {
		enderecoConverter = new EnderecoConverter(Arrays.asList(usuario.getEnderecos()));
	}

	public void salvar() {
		cadastroEnderecoService.salvar(endereco);

		messages.info("Endereco salvo com sucesso!");

	//	RequestContext.getCurrentInstance().update(Arrays.asList("frm:enderecosDataTable", "frm:messages"));
	}

	public void excluir() {
		cadastroEnderecoService.excluir(endereco);

		endereco = null;

		messages.info("Endereco excluído com sucesso!");
	}

	public void todosEnderecos() {
		listaEnds = enderecos.todos();
	}

	private boolean jaHouvePesquisa() {
		return termoPesquisa != null && !"".equals(termoPesquisa);
	}

	public List<Endereco> getListaEnderecos() {
		return listaEnds;
	}

	public String getTermoPesquisa() {
		return termoPesquisa;
	}

	public void setTermoPesquisa(String termoPesquisa) {
		this.termoPesquisa = termoPesquisa;
	}
	

	public boolean isUsuarioSeleciona() {
		return usuario != null && usuario.getId() != null;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}


}