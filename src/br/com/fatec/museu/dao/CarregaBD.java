package br.com.fatec.museu.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;

import br.com.fatec.museu.modelo.Responsavel;
import br.com.fatec.museu.modelo.Obra;

public class CarregaBD {

	public static void main(String[] args) {

		EntityManager em = JPAUtil.getEntityManager();

		em.getTransaction().begin();

		Responsavel maria = geraResponsavel("Maria Machado");
		em.persist(maria);

		Responsavel jorge = geraResponsavel("Jorge Farias");
		em.persist(jorge);

		Responsavel paulo = geraResponsavel("Paulo Souza");
		em.persist(paulo);

		Responsavel lobato = geraResponsavel("Renata Lobato");
		em.persist(lobato);

		Obra pintura = geraObra("157-8-32-873950-7", "Pintura Íntima",
				"02/05/1845", 24445.90, maria);
		Obra memorias = geraObra("100-8-32-453950-5",
				"Memorias Postumas de Bras Cubas", "01/01/1881", 255519.90, maria);
		Obra espada = geraObra("199-8-32-827490-7", "Espada da Morte",
				"10/01/1845", 4555516.00, maria);

		em.persist(pintura);
		em.persist(memorias);
		em.persist(espada);

		Obra tela = geraObra("188-8-32-678390-0", "Tela para Dois",
				"01/07/1688", 190000.00, paulo);
		Obra monalisa = geraObra("165-8-32-946738-1", "Monalisa", "01/01/1990",
				12.90, paulo);
		Obra ragnarok = geraObra("124-8-32-765123-3", "Ragnarok",
				"22/09/1500", 290000.00, paulo);
		Obra esfinge = geraObra("132-8-32-176324-2", "Mini Esfinge",
				"01/03/1789", 999990.00, paulo);

		em.persist(tela);
		em.persist(monalisa);
		em.persist(ragnarok);
		em.persist(esfinge);

		Obra torre = geraObra("178-8-50-823469-0", "Torre",
				"31/12/1900", 600000.90, jorge);
		Obra bronze = geraObra("111-8-53-432569-7",
				"Estatua de Bronze", "01/01/1930", 333318.90, jorge);

		em.persist(torre);
		em.persist(bronze);

		em.getTransaction().commit();
		em.close();

	}

	private static Responsavel geraResponsavel(String nome) {
		Responsavel autor = new Responsavel();
		autor.setNome(nome);
		return autor;
	}

	private static Obra geraObra(String serial, String nome, String data,
			double valor, Responsavel responsavel) {
		Obra livro = new Obra();
		livro.setSerial(serial);
		livro.setNome(nome);
		livro.setDataCriacao(parseData(data));
		livro.setValor(valor);
		livro.adicionaObra(responsavel);
		return livro;
	}

	private static Calendar parseData(String data) {
		try {
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(data);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return calendar;
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
	}

}
