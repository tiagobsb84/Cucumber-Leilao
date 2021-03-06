package br.com.alura.leilao.acceptance.steps;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

public class PropondoLanceSteps {

	private Lance lance;
	private Leilao leilao;
	
	private ArrayList<Lance> lista;
	
	@Before
	public void setup() {
		this.lista = new ArrayList<Lance>();
		leilao = new Leilao("Tablet XPTO");
	}
	
	@Dado("Dado um lance valido")
	public void dado_um_lance_valido() {
	  Usuario usuario = new Usuario("fulano");
	  lance = new Lance(usuario, BigDecimal.TEN);
	}

	@Quando("propoe um leilao")
	public void quando_propoe_um_lance() {
		leilao.propoe(lance);
	}
	
	@Entao("Entao o lance eh aceito")
	public void entao_o_lance_eh_aceito() {
		Assert.assertEquals(1, leilao.getLances().size());
		Assert.assertEquals(BigDecimal.TEN, leilao.getLances().get(0).getValor());
	}
	
//	@Dado("Dados varios lances validos")
//	public void dados_varios_lances_validos() {
//		Usuario usuario = new Usuario("fulano");
//		Usuario usuario1 = new Usuario("beltrano");
//		lance10 = new Lance(usuario, BigDecimal.TEN);
//		lance15 = new Lance(usuario1, new BigDecimal("15.0"));
//		leilao = new Leilao("Tablet XPTO");
//	}
	
	@Dado("um lance {double} reais do usuario {string}")
	public void um_lance_reais_do_usuario(Double valor, String nomeUsuario) {
	    Lance lance = new Lance(new Usuario(nomeUsuario), new BigDecimal(valor));
	    lista.add(lance);
	}

	@Quando("propoe varios lances ao leilao")
	public void quando_propoe_varios_lances_ao_leilao() {
		lista.forEach(Lance -> leilao.propoe(Lance));
	}
	
	@Entao("Entao os lances sao aceitos")
	public void entao_os_lances_sao_aceitos() {
		Assert.assertEquals(this.lista.size(), leilao.getLances().size());
		Assert.assertEquals(this.lista.get(0).getValor(), leilao.getLances().get(0).getValor());
		Assert.assertEquals(this.lista.get(1).getValor(), leilao.getLances().get(1).getValor());
	}
	
	@Dado("um lance invalido de {double} reais e do usuario {string}")
	public void um_lance_invalido_de_reais(Double valor, String nomeUsuario) {
	    this.lance = new Lance(new BigDecimal(valor));
	}
	
	@Entao("o lance nao eh aceito")
	public void o_lance_nao_eh_aceito() {
		Assert.assertEquals(0, leilao.getLances().size());
	}
	
	@Dado("dois lances")
	public void dois_lances(DataTable dataTable) {
		List<Map<String, String>> valores = dataTable.asMaps();
		for(Map<String, String> mapa : valores) {
			
			String nome = mapa.get("nomeUsuario");
			String valor = mapa.get("valor");
			
			Lance lance = new Lance(new Usuario(nome), new BigDecimal(valor));
			lista.add(lance);
		}
	}
	
	@Entao("o segundo lance nao eh aceito")
	public void o_segundo_lance_nao_eh_aceito() {
		Assert.assertEquals(1, leilao.getLances().size());
		Assert.assertEquals(this.lista.get(0).getValor(), leilao.getLances().get(0).getValor());
	}
}
