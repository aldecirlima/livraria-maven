package br.com.caelum.livraria.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.dao.UsuarioDao;
import br.com.caelum.livraria.modelo.Usuario;
import br.com.caelum.livraria.util.RedirectView;

@Named
@ViewScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Usuario usuario;

	@Inject
	UsuarioDao dao;

	@Inject
	FacesContext context;

	public String efetuaLogin() {
//		System.out.println("Fazendo login do usu�rio: " + this.usuario.getEmail());

		boolean existe = dao.existe(this.usuario);
		if (existe) {
			context.getExternalContext().getSessionMap().put("usuarioLogado", this.usuario);
			return new RedirectView("livro").toString();
		}

//		O escopo de flash mant�m o componente "Messages" ativo por duas requisi��es,
//			sendo assim, a mensagem estar� ativa nesta e na pr�xima requisi��o.
		context.getExternalContext().getFlash().setKeepMessages(true);
		;
		context.addMessage(null, new FacesMessage("Usu�rio ou senha inv�lidos"));

//		Caso o usu�rio erre a senha, redirecionar de volta para login para limpar o cache de usu�rio e senha. 
		return new RedirectView("login").toString();
	}

	public String deslogar() {

		context.getExternalContext().getSessionMap().remove("usuarioLogado");

		return new RedirectView("login").toString();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
