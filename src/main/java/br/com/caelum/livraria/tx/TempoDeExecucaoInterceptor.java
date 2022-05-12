package br.com.caelum.livraria.tx;

import java.io.Serializable;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@Log
public class TempoDeExecucaoInterceptor implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@AroundInvoke
	public Object calculaTempoTX(InvocationContext context) throws Exception {
		long antes = System.currentTimeMillis();
		System.out.println("Antes: " + antes);

		//execute aqui o codigo que usa o EntityManager
		Object result = context.proceed();

		long depois = System.currentTimeMillis();
		System.out.println("Depois: " + depois);
		System.out.println("Final: " + (depois - antes));
		
		return result;
	}
	

}
