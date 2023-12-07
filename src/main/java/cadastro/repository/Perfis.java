package cadastro.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import cadastro.model.Perfil;

public class Perfis implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	public Perfis() {

	}

	public Perfis(EntityManager manager) {
		this.manager = manager;
	}
	
	public List<Perfil> pesquisar(String descricao) {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		
		CriteriaQuery<Perfil> criteriaQuery = criteriaBuilder.createQuery(Perfil.class);		
		Root<Perfil> root = criteriaQuery.from(Perfil.class);			
		criteriaQuery.select(root);				
		criteriaQuery.where(criteriaBuilder.like(root.get("descricao"), descricao + "%"));		
		
		TypedQuery<Perfil> query = manager.createQuery(criteriaQuery);
		
		return query.getResultList();
	}
}

