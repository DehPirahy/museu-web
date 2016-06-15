package br.com.fatec.museu.modelo;

import java.io.Serializable;
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
public class Obra implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	private String nome;
	private String serial;
	private double valor;
	@Temporal(TemporalType.DATE)
	private Calendar dataCriacao = Calendar.getInstance();

	@ManyToMany(fetch=FetchType.EAGER)
	private List<Responsavel> responsaveis = new ArrayList<Responsavel>();

	public List<Responsavel> getResponsaveis() {
		return responsaveis;
	}

	public void adicionaObra(Responsavel responsavel) {
		this.responsaveis.add(responsavel);
	}

	public Obra() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Calendar getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Calendar dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public void removeResponsavel(Responsavel autor) {
		this.responsaveis.remove(autor);
	}

}