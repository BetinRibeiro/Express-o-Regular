package br.com.Bin;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "questao")
public class Questao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	private Integer id;
	private String titulo;
	@Column(name = "enunciado",nullable=false, length=10000)
	private String enunciado;
	@Column(name = "numero_ocorrencia")
	private Integer numeroOcorrencia;
	private Integer fonte;
	@Column(name = "acertos")
	private float acertos;
	
	
	
	
	
	
	public Integer getFonte() {
		return fonte;
	}
	public void setFonte(Integer fonte) {
		this.fonte = fonte;
	}
	private float dificuldade;
	public float getDificuldade() {
		return dificuldade;
	}
	public void setDificuldade(float dificuldade) {
		this.dificuldade = dificuldade;
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
	public String getEnunciado() {
		return enunciado;
	}
	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}
	public Integer getNumeroOcorrencia() {
		return numeroOcorrencia;
	}
	public void setNumeroOcorrencia(Integer numeroOcorrencia) {
		this.numeroOcorrencia = numeroOcorrencia;
	}
	public float getAcertos() {
		return acertos;
	}
	public void setAcertos(float acertos) {
		this.acertos = acertos;
	}
	
	
}