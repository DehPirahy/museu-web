package br.com.fatec.museu.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.fatec.museu.modelo.Responsavel;

@ManagedBean
@ViewScoped
public class ResponsavelBean {

	private Responsavel responsavel = new Responsavel();
	
	private Integer responsavelId;
	

	public void carregarResponsavelPelaId() {
		this.responsavel = new ResponsavelDAO().buscaPorId(responsavelId);
	}

	public String gravar() {
		if(this.responsavel.getId() == null) {
			new ResponsavelDAO().adiciona(this.responsavel);
		} else {
			new ResponsavelDAO().atualiza(this.responsavel);
		}

		this.responsavel = new Responsavel();

		return "obra?faces-redirect=true";
	}
	
	public void remover(Responsavel responsavel) {
		new ResponsavelDAO().remove(responsavel);
	}
	
	public List<Responsavel> getResponsaveis() {
		return new ResponsavelDAO().listaTodos();
	}

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	public Integer getResponsavelId() {
		return responsavelId;
	}

	public void setResponsavelId(Integer responsavelId) {
		this.responsavelId = responsavelId;
	}
	
	
}
