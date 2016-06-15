package br.com.fatec.museu.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.com.fatec.museu.dao.ObraDAO;
import br.com.fatec.museu.modelo.Obra;
import br.com.fatec.museu.modelo.Responsavel;

@ManagedBean
@ViewScoped
public class ObraBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Obra obra = new Obra();

	private Integer responsavelId;

	private List<Obra> obras;


	public Integer getResponsavelId() {
		return responsavelId;
	}

	public void setResponsavelId(Integer responsavelId) {
		this.responsavelId = responsavelId;
	}

	public Obra getObra() {
		return obra;
	}

	public List<Obra> getObras() {
		ObraDAO dao = new ObraDAO();
		if(this.obras == null) {
			this.obras = dao.listaTodos();			
		}
		return obras;
	}

	public List<Responsavel> getResponsaveis() {
		return new ResponsavelDAO().listaTodos();
	}

	public List<Responsavel> getResponsaveisDaObra() {
		return this.obra.getResponsaveis();
	}

	public void carregarObraPelaId() {
		this.obra = new ObraDAO().buscaPorId(this.obra.getId()); 
	}
	
	public void gravarResponsavel() {
		Responsavel responsavel = new ResponsavelDAO().buscaPorId(this.responsavelId);
		this.obra.adicionaObra(responsavel);
	}

	public String gravar() {
		if (obra.getResponsaveis().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("responsavel",
					new FacesMessage("A Obra deve ter pelo menos um Responsavel."));
			return "";
		}
		if(this.obra.getId() == null) {
			new ObraDAO().adiciona(this.obra);
			this.obras = new ObraDAO().listaTodos();
		} else {
			new ObraDAO().atualiza(this.obra);
		}
		this.obra = new Obra();
		return "obra?faces-redirect=true";
	}

	public String remover(Obra obra) {
		new ObraDAO().remove(obra);
		return "obra?faces-redirect=true";
	}
	
	public void removerResponsavelDaObra(Responsavel responsavel) {
		this.obra.removeResponsavel(responsavel);
	}
	
	public void carregar(Obra obra) {
		this.obra = obra;
	}
	
	public String formResponsavel() {
		return "responsavel?faces-redirect=true";
	}

	public void comecaComDigitoUm(FacesContext fc, UIComponent component,
			Object value) throws ValidatorException {

		String valor = value.toString();
		if (!valor.startsWith("1")) {
			throw new ValidatorException(new FacesMessage(
					"Serial deveria começar com 1"));
		}

	}
}
