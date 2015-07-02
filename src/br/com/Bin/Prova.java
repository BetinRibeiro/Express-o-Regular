package br.com.Bin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "prova")
public class Prova {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	Integer id;
	@Column(name = "texto_verificacao")
	String textoVerificacao;
	@Column(name = "descricao")
	String descricao;
	@Column(name = "materia")
	String materia;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTextoVerificacao() {
		return textoVerificacao;
	}
	public void setTextoVerificacao(String textoVerificacao) {
		this.textoVerificacao = textoVerificacao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getMateria() {
		return materia;
	}
	public void setMateria(String materia) {
		this.materia = materia;
	}
	
	
	

}
