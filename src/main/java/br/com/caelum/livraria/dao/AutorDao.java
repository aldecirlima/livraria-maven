package br.com.caelum.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.tx.Log;

public class AutorDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager em;
	
	private DAO<Autor> dao;

	@PostConstruct
	void init() {
		this.dao = new DAO<Autor>(this.em, Autor.class);
	}

	public void adiciona(Autor t) {
		dao.adiciona(t);
	}

	public void remove(Autor t) {
		dao.remove(t);
	}

	public void atualiza(Autor t) {
		dao.atualiza(t);
	}

	@Log
	public List<Autor> listaTodos() {
		return dao.listaTodos();
	}

	public Autor buscaPorId(Integer id) {
		return dao.buscaPorId(id);
	}

	public int contaTodos() {
		return dao.contaTodos();
	}

	public List<Autor> listaTodosPaginada(int firstResult, int maxResults) {
		return dao.listaTodosPaginada(firstResult, maxResults);
	}
	
	public List<Autor> findAutor() {
		CriteriaBuilder critBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Autor> critQuery = critBuilder.createQuery(Autor.class);
		
		Root<Autor> autorRoot = critQuery.from(Autor.class);
		critQuery.multiselect(autorRoot.get("nome"));
		critQuery.distinct(true);
		critQuery.orderBy(critBuilder.asc(autorRoot.get("nome")));
		
//		Expression<String> exp = autorRoot.get("nome");
//		Predicate in = exp.in("Sergio Lopes");
//		critQuery.where(in);
		
		
		CriteriaQuery<Autor> select = critQuery.select(autorRoot);
		TypedQuery<Autor> query = em.createQuery(select);
		
		return query.getResultList();
	} 
	
	@SuppressWarnings("unchecked")
	public List<Autor> selecionaAutores () {
		Session session = em.unwrap(Session.class);
		Criteria criteria1 = session.createCriteria(Autor.class);
		Disjunction dis = Restrictions.disjunction();
		dis.add(Restrictions.eq("nome", "Aldecir Neves de Lima"));
		dis.add(Restrictions.eq("email", "ana.celia@alura.com.br"));
		criteria1.add(dis);
		criteria1.add(Restrictions.eq("email", "aldecirlima@gmail.com.b"));
		
		List<Autor> lista1 = criteria1.list();
		
		if (lista1.size() == 0) {
			Criteria criteria2 = session.createCriteria(Autor.class);
			Disjunction dis2 = Restrictions.disjunction();
			dis2.add(Restrictions.eq("nome", "Aldecir Neves de Lima"));
			dis2.add(Restrictions.eq("email", "ana.celia@alura.com.br"));
			criteria2.add(dis2);
			System.out.println("Crit 2");
			return criteria2.list();
		}
		System.out.println("Crit 1");
		return criteria1.list();
	}

}
