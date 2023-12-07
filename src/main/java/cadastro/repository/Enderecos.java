package cadastro.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import cadastro.model.Endereco;

public class Enderecos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Enderecos() {

	}

	public Enderecos(EntityManager manager) {
		this.manager = manager;
	}

	public Endereco porId(Long id) {
		return manager.find(Endereco.class, id);
	}

	public List<Endereco> pesquisar(String logradouro) {
		String jpql = "from Endereco where logradouro like :logradouro";
		
		TypedQuery<Endereco> query = manager
				.createQuery(jpql, Endereco.class);
		
		query.setParameter("logradouro", logradouro + "%");
		//query.setParameter("cpf", nome + "%");
		return query.getResultList();
	}
	
	public List<Endereco> todos() {
         return manager.createQuery("from Endereco", Endereco.class).getResultList();
    }

	public Endereco guardar(Endereco endereco) {
		return manager.merge(endereco);
	}

	public void remover(Endereco endereco) {
		endereco = porId(endereco.getId());
		manager.remove(endereco);
	}
}