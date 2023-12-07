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

import cadastro.model.Perfil;
import cadastro.model.Usuario;
import cadastro.repository.Perfis;
import cadastro.repository.Usuarios;
import cadastro.service.CadastroUsuarioService;
import cadastro.util.FacesMessages;



@Named
@ViewScoped
public class GestaoUsuariosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Usuarios usuarios;

	@Inject
	private FacesMessages messages;

	@Inject
	private Perfis perfis;
	
	@Inject
	private CadastroUsuarioService cadastroUsuarioService;

	private List<Usuario> listaUsuarios;

	private String termoPesquisa;

	private Converter perfilConverter;
	
	private Usuario usuario;
	
	private Date startDate;
	private Date endDate;
	private List<Usuario> resultados;

	public void prepararNovoUsuario() {
		usuario = new Usuario();
	}

	public void prepararEdicao() {
		perfilConverter = new PerfilConverter(Arrays.asList(usuario.getPerfil()));
	}

	public void salvar() {
		cadastroUsuarioService.salvar(usuario);

		atualizarRegistros();

		messages.info("Usuário salva com sucesso!");

		RequestContext.getCurrentInstance().update(Arrays.asList("frm:usuariosDataTable", "frm:messages"));
	}

	public void excluir() {
		cadastroUsuarioService.excluir(usuario);

		usuario = null;

		atualizarRegistros();

		messages.info("Usuário excluído com sucesso!");
	}

	public void pesquisar() {
		listaUsuarios = usuarios.pesquisar(termoPesquisa);

		if (listaUsuarios.isEmpty()) {
			messages.info("Sua consulta não retornou registros.");
		}
	}

	public void todosUsuarios() {
		listaUsuarios = usuarios.todos();
	}

	public List<Perfil> completarPerfil(String termo) {
		List<Perfil> listaPerfis = perfis.pesquisar(termo);

		perfilConverter = new PerfilConverter(listaPerfis);

		return listaPerfis;
	}
	
	
	public void buscar() {
	resultados = usuarios.buscar(startDate, endDate);
	}
	
	private void atualizarRegistros() {
		if (jaHouvePesquisa()) {
			pesquisar();
		} else {
			todosUsuarios();
		}
	}

	private boolean jaHouvePesquisa() {
		return termoPesquisa != null && !"".equals(termoPesquisa);
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public String getTermoPesquisa() {
		return termoPesquisa;
	}

	public void setTermoPesquisa(String termoPesquisa) {
		this.termoPesquisa = termoPesquisa;
	}
	
	public Converter getPerfilConverter() {
		return perfilConverter;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isUsuarioSeleciona() {
		return usuario != null && usuario.getId() != null;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<Usuario> getResultados() {
		return resultados;
	}

	public void setResultados(List<Usuario> resultados) {
		this.resultados = resultados;
	}


}