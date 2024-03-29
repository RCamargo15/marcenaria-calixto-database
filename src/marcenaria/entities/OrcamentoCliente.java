package marcenaria.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class OrcamentoCliente implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer numOrcamento;
	private Cliente codCliente;
	private Cliente telefone;
	private Cliente celular;
	private Cliente email;
	private String descServico;
	private Date dataOrcamento;
	private Produto codProduto;
	private Integer quantidade;
	private Produto valor;
	private Double valorTotal;
	private String obs;

	public OrcamentoCliente() {

	}

	public OrcamentoCliente(Integer numOrcamento, Cliente codCliente, Cliente telefone, Cliente celular, Cliente email,
			String descServico, Date dataOrcamento, Produto codProduto, Integer quantidade, Produto valor, Double valorTotal, String obs) {
		super();
		this.numOrcamento = numOrcamento;
		this.codCliente = codCliente;
		this.telefone = telefone;
		this.celular = celular;
		this.email = email;
		this.descServico = descServico;
		this.dataOrcamento = dataOrcamento;
		this.codProduto = codProduto;
		this.quantidade = quantidade;
		this.valor = valor;
		this.valorTotal = realValorTotal(valor, quantidade);
		this.obs = obs;
	}

	public Integer getNumOrcamento() {
		return numOrcamento;
	}

	public void setNumOrcamento(Integer numOrcamento) {
		this.numOrcamento = numOrcamento;
	}

	public Cliente getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(Cliente codCliente) {
		this.codCliente = codCliente;
	}

	public Cliente getTelefone() {
		return telefone;
	}

	public void setTelefone(Cliente telefone) {
		this.telefone = telefone;
	}

	public Cliente getCelular() {
		return celular;
	}

	public void setCelular(Cliente celular) {
		this.celular = celular;
	}

	public Cliente getEmail() {
		return email;
	}

	public void setEmail(Cliente email) {
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

	public Produto getValor() {
		return valor;
	}

	public void setValor(Produto valor) {
		this.valor = valor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
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
	
	public Double realValorTotal(Produto prod, Integer quantidade) {
		return prod.getPrecoUnit() * quantidade;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codCliente, numOrcamento);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrcamentoCliente other = (OrcamentoCliente) obj;
		return Objects.equals(codCliente, other.codCliente) && Objects.equals(numOrcamento, other.numOrcamento);
	}

	@Override
	public String toString() {
		return "OrcamentoCliente [numOrcamento=" + numOrcamento + ", codCliente=" + codCliente + ", telefone="
				+ telefone + ", celular=" + celular + ", email=" + email + ", descServico=" + descServico
				+ ", dataOrcamento=" + dataOrcamento + ", codProduto=" + codProduto + ", quantidade=" + quantidade
				+ ", valor=" + valor + ", valorTotal=" + valorTotal + ", obs=" + obs + "]";
	}
}
