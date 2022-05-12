package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.dao.AutorDao;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.tx.Transactional;

@Named
@ViewScoped // javax.faces.view.ViewScoped;
public class AutorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Autor autor = new Autor();
	
	@Inject
	private AutorDao dao;

	private Integer autorId;

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public List<Autor> getAutores() {
		return this.dao.listaTodos();
	}
//	public List<Autor> getAutores() {
//		return this.dao.listaTodos();
//	}

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public void carregarAutorPelaId() {
		this.autor = this.dao.buscaPorId(autorId);
	}

	@Transactional
	public void gravar() {
		if (this.autor.getId() == null) {
			this.dao.adiciona(this.autor);
		} else {
			this.dao.atualiza(this.autor);
		}

		this.autor = new Autor();
	}

	@Transactional
	public void remover(Autor autor) {
		this.dao.remove(autor);
	}

}
