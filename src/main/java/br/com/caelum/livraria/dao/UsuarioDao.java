package br.com.caelum.livraria.dao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.caelum.livraria.modelo.Usuario;

public class UsuarioDao implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	EntityManager manager;

	public boolean existe(Usuario usuario) {

		TypedQuery<Usuario> query = manager.createQuery(
				"SELECT u FROM Usuario u " + "WHERE u.email = :pEmail AND u.senha = :pSenha", Usuario.class);
		query.setParameter("pEmail", usuario.getEmail());
		query.setParameter("pSenha", usuario.getSenha());

		Usuario result;

		try {
			result = query.getSingleResult();
		} catch (NoResultException ex) {
			return false;
		}

//		Se resultado diferente de null devolve verdadeiro, senão devolve falso.
		return result != null;

	}

}
