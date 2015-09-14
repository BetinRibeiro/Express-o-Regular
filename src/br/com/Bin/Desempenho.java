package br.com.Bin;



import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "desempenho")
public class Desempenho {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	private Integer id;
	private float nivel;
	private Date data;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public float getNiveis() {
		return nivel;
	}
	public void setNiveis(float nivel) {
		this.nivel = nivel;
	}
	public Date getDate() {
		return data;
	}
	public void setDate(Date data) {
		this.data = data;
	}

}