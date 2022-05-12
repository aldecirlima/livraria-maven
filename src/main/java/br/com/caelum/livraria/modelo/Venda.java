package br.com.caelum.livraria.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Venda {

	@Id
	@GeneratedValue
	private Integer id;

	@ManyToOne
	private Livro livro;
	private Integer quantidade;

	public Venda(Integer id, Livro livro, Integer quantidade) {
		this.id = id;
		this.livro = livro;
		this.quantidade = quantidade;
	}

	public Venda() {

	}

	public Livro getLivro() {
		return livro;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

}
