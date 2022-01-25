package marcenaria.entities;

import java.io.Serializable;
import java.util.Objects;

public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer codProduto;
	private String descProduto;

	public Produto() {

	}

	public Produto(Integer codProduto, String descProduto) {
		this.codProduto = codProduto;
		this.descProduto = descProduto;
	}

	public Integer getCodProduto() {
		return codProduto;
	}

	public void setCodProduto(Integer codProduto) {
		this.codProduto = codProduto;
	}

	public String getDescProduto() {
		return descProduto;
	}

	public void setDescProduto(String descProduto) {
		this.descProduto = descProduto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codProduto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		return Objects.equals(codProduto, other.codProduto);
	}

	@Override
	public String toString() {
		return "Produto [codProduto=" + codProduto + ", descProduto=" + descProduto;
	}
}
