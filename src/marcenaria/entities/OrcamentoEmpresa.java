package marcenaria.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class OrcamentoEmpresa implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer numOrcamento;
	private Empresa codEmpresa;
	private String nomeResponsavel;
	private String telefone;
	private String celular;
	private String email;
	private String descServico;
	private Date dataOrcamento;
	private Produto codProduto;
	private Integer quantidade;
	private Double valor;
	private Double valorTotal;
	private String obs;

	public OrcamentoEmpresa() {

	}

	public OrcamentoEmpresa(Integer numOrcamento, Empresa codEmpresa, String nomeResponsavel, String telefone,
			String celular, String email, String descServico, Date dataOrcamento, Produto codProduto,
			Integer quantidade, Double valor, Double valorTotal, String obs) {
		super();
		this.numOrcamento = numOrcamento;
		this.codEmpresa = codEmpresa;
		this.nomeResponsavel = nomeResponsavel;
		this.telefone = telefone;
		this.celular = celular;
		this.email = email;
		this.descServico = descServico;
		this.dataOrcamento = dataOrcamento;
		this.codProduto = codProduto;
		this.quantidade = quantidade;
		this.valor = valor;
		this.valorTotal = valorTotal;
	}

	public Integer getNumOrcamento() {
		return numOrcamento;
	}

	public void setNumOrcamento(Integer numOrcamento) {
		this.numOrcamento = numOrcamento;
	}

	public Empresa getCodEmpresa() {
		return codEmpresa;
	}

	public void setCodEmpresa(Empresa codEmpresa) {
		this.codEmpresa = codEmpresa;
	}

	public String getNomeResponsavel() {
		return nomeResponsavel;
	}

	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescServico() {
		return descServico;
	}

	public void setDescServico(String descServico) {
		this.descServico = descServico;
	}

	public Date getDataOrcamento() {
		return dataOrcamento;
	}

	public void setDataOrcamento(Date dataOrcamento) {
		this.dataOrcamento = dataOrcamento;
	}

	public Produto getCodProduto() {
		return codProduto;
	}

	public void setCodProduto(Produto codProduto) {
		this.codProduto = codProduto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codEmpresa, numOrcamento);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrcamentoEmpresa other = (OrcamentoEmpresa) obj;
		return Objects.equals(codEmpresa, other.codEmpresa) && Objects.equals(numOrcamento, other.numOrcamento);
	}

	@Override
	public String toString() {
		return "OrcamentoEmpresa [numOrcamento=" + numOrcamento + ", codEmpresa=" + codEmpresa + ", nomeResponsavel="
				+ nomeResponsavel + ", telefone=" + telefone + ", celular=" + celular + ", email=" + email
				+ ", descServico=" + descServico + ", dataOrcamento=" + dataOrcamento + ", codProduto=" + codProduto
				+ ", quantidade=" + quantidade + ", valor=" + valor + ", valorTotal=" + valorTotal + ", obs=" + obs
				+ "]";
	}

}
