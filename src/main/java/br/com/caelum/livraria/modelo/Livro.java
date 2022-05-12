package br.com.caelum.livraria.modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Livro {

	@Id
	@GeneratedValue
	private Integer id;

	private String titulo;
	private String isbn;
	private Double preco;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataLancamento = Calendar.getInstance();

	// Lazy inicialization = preguiçosamente inicializado. Para corrigir adicionar o
	// fetch, conforme abaixo.
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Autor> autores = new ArrayList<Autor>();

	public Livro(Integer id, String titulo, String isbn, Double preco, Calendar dataLancamento) {
		this.id = id;
		this.titulo = titulo;
		this.isbn = isbn;
		this.preco = preco;
		this.dataLancamento = dataLancamento;
	}

	public Livro() {
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void adicionaAutor(Autor autor) {
		this.autores.add(autor);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Calendar getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Calendar dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public void removeAutor(Autor autor) {
		this.autores.remove(autor);

	}

}