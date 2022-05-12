package br.com.caelum.livraria.tx;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@Transactional
@Interceptor
public class GerenciadorDeTransacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager manager;

	@AroundInvoke
	public Object executaTX(InvocationContext context) throws Exception {
		// abre transacao
//		System.out.println("Abrindo Transacao");
		manager.getTransaction().begin();
		
		// chama os DAOs que precisam de transa��o.
		Object result = context.proceed();

//		System.out.println("Fechando Transa��o");
		// commita a transacao
		manager.getTransaction().commit();
		
		return result;
	}

}
