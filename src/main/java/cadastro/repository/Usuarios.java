package cadastro.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import cadastro.model.Usuario;

public class Usuarios implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Usuarios() {

	}

	public Usuarios(EntityManager manager) {
		this.manager = manager;
	}

	public Usuario porId(Long id) {
		return manager.find(Usuario.class, id);
	}

	public List<Usuario> pesquisar(String nome) {
		String jpql = "from Usuario where nome like :nome";
		
		TypedQuery<Usuario> query = manager
				.createQuery(jpql, Usuario.class);
		
		query.setParameter("nome", nome + "%");
		//query.setParameter("cpf", nome + "%");
		return query.getResultList();
	}
	
	public List<Usuario> buscar(Date startDate, Date endDate) {
		String jpql = "FROM Usuario u WHERE u.dataCadastro BETWEEN :startDate AND :endDate";
		
		TypedQuery<Usuario> query = manager
				.createQuery(jpql, Usuario.class);
		
		query.setParameter("startDate", startDate );
		query.setParameter("endDate", endDate);
		return query.getResultList();
	}
	
	public List<Usuario> todos() {
         return manager.createQuery("from Usuario", Usuario.class).getResultList();
    }

	public Usuario guardar(Usuario usuario) {
		return manager.merge(usuario);
	}

	public void remover(Usuario usuario) {
		usuario = porId(usuario.getId());
		manager.remove(usuario);
	}
}