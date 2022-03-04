package marcenaria.dao.implementacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Db.Db;
import Db.DbException;
import marcenaria.dao.OrcamentoClienteDao;
import marcenaria.entities.Cliente;
import marcenaria.entities.OrcamentoCliente;
import marcenaria.entities.Produto;

public class OrcamentoClienteDaoJDBC implements OrcamentoClienteDao {

	private Connection conn;
	
	public OrcamentoClienteDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(OrcamentoCliente obj) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement(
					"INSERT INTO MARCENARIA.ORCAMENTO_CLIENTE(COD_CLIENTE, TELEFONE, CELULAR, EMAIL, DESC_SERVICO, DATA_ORCAMENTO, "
					+"COD_PRODUTO, QUANTIDADE, VALOR, VALOR_TOTAL, OBS) VALUES(?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			
			Integer quantidade = obj.getQuantidade();
			Double valor = obj.getValor().getPrecoUnit();
			Double valorTotal = quantidade * valor;
			
			st.setInt(1, obj.getCodCliente().getCodCliente());
			st.setString(2, obj.getTelefone().getTelefone());
			st.setString(3, obj.getCelular().getCelular());
			st.setString(4, obj.getEmail().getEmail());
			st.setString(5, obj.getDescServico());
			st.setDate(6, new java.sql.Date(obj.getDataOrcamento().getTime()));
			st.setInt(7, obj.getCodProduto().getCodProduto());
			st.setInt(8, obj.getQuantidade());
			st.setDouble(9, obj.getValor().getPrecoUnit());
			st.setDouble(10, valorTotal);
			st.setString(11, obj.getObs());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int numOrcamentoGerado = rs.getInt(1);
					obj.setNumOrcamento(numOrcamentoGerado);
				}
				else {
					throw new DbException("Nenhum orcamento foi gerado");
					}
				}
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			Db.closeStatement(st);
		}
	}

	@Override
	public void update(OrcamentoCliente obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE MARCENARIA.ORCAMENTO_CLIENTE SET COD_CLIENTE = ?, TELEFONE = ?, CELULAR = ?, EMAIL = ?, "
					+"DESC_SERVICO = ?, DATA_ORCAMENTO = ?, COD_PRODUTO = ?, QUANTIDADE = ?, VALOR = ?, VALOR_TOTAL = ?, OBS = ? "
					+" WHERE NUM_ORCAMENTO = ?"
					);
			
			int quantidade = obj.getQuantidade();
			double valor = obj.getValor().getPrecoUnit();
			double valorTotal = valor*quantidade;
			
			st.setInt(1, obj.getCodCliente().getCodCliente());
			st.setString(2, obj.getTelefone().getTelefone());
			st.setString(3, obj.getCelular().getCelular());
			st.setString(4, obj.getEmail().getEmail());
			st.setString(5, obj.getDescServico());
			st.setDate(6, new java.sql.Date(obj.getDataOrcamento().getTime()));
			st.setInt(7, obj.getCodProduto().getCodProduto());
			st.setInt(8, obj.getQuantidade());
			st.setDouble(9, obj.getValor().getPrecoUnit());
			st.setDouble(10, valorTotal);
			st.setString(11, obj.getObs());
			st.setInt(12, obj.getNumOrcamento());
			
			st.executeUpdate();
					
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			Db.closeStatement(st);
		}
	}

	@Override
	public void deleteByNumOrcamento(Integer numOrcamento) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM MARCENARIA.ORCAMENTO_CLIENTE WHERE ORCAMENTO_CLIENTE.NUM_ORCAMENTO = ?");
			
			st.setInt(1, numOrcamento);
			st.executeUpdate();
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			Db.closeStatement(st);
		}
	}

	@Override
	public OrcamentoCliente findByNumOrcamento(Integer numOrcamento) {
		PreparedStatement st = null;
		ResultSet rs = null;
			try {
				st = conn.prepareStatement("SELECT * FROM MARCENARIA.ORCAMENTO_CLIENTE "
						+ "INNER JOIN CLIENTE ON CLIENTE.COD_CLIENTE = ORCAMENTO_CLIENTE.COD_CLIENTE "
						+ "INNER JOIN PRODUTO ON PRODUTO.COD_PRODUTO = ORCAMENTO_CLIENTE.COD_PRODUTO WHERE NUM_ORCAMENTO = ?");
				
				st.setInt(1, numOrcamento);
				
				rs = st.executeQuery();
				
				Map<Integer, Cliente> clienteMap = new HashMap<>();
				Map<Integer, Produto> produtoMap = new HashMap<>();
				
				if(rs.next()) {
					
					Cliente cliente = clienteMap.get(rs.getInt("COD_CLIENTE"));
					if(cliente == null) {
						cliente = criarCliente(rs);
						clienteMap.put(rs.getInt("COD_CLIENTE"), cliente);
					}
					
					Produto produto = produtoMap.get(rs.getInt("COD_PRODUTO"));
					if(produto == null) {
						produto = criarProduto(rs);
						produtoMap.put(rs.getInt("COD_PRODUTO"), produto);
					}
					
					OrcamentoCliente obj = criarOrcamentoCliente(rs, cliente, produto);
					return obj;
				}
				return null;
			}
			catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
			finally {
				Db.closeResultSet(rs);
				Db.closeStatement(st);
			}
		}

	@Override
	public List<OrcamentoCliente> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT * FROM MARCENARIA.ORCAMENTO_CLIENTE "
					+"INNER JOIN CLIENTE ON CLIENTE.COD_CLIENTE = ORCAMENTO_CLIENTE.COD_CLIENTE "
					+"INNER JOIN PRODUTO ON PRODUTO.COD_PRODUTO = ORCAMENTO_CLIENTE.COD_PRODUTO GROUPD BY NUM_ORCAMENTO"
					);
			
				rs = st.executeQuery();
				
				Map<Integer, Cliente> clienteMap = new HashMap<>();
				Map<Integer, Produto> produtoMap = new HashMap<>();
				List<OrcamentoCliente> listOrcamento = new ArrayList<>();
				
				while(rs.next()) {
					
					Cliente cliente = clienteMap.get(rs.getInt("COD_CLIENTE"));
					if(cliente == null) {
						cliente = criarCliente(rs);
						clienteMap.put(rs.getInt("COD_CLIENTE"), cliente);
					}
					
					Produto produto = produtoMap.get(rs.getInt("COD_PRODUTO"));
					if(produto == null) {
						produto = criarProduto(rs);
						produtoMap.put(rs.getInt("COD_PRODUTO"), produto);
					}
					
					OrcamentoCliente obj = criarOrcamentoCliente(rs, cliente, produto);
					listOrcamento.add(obj);	
				}
			
				return listOrcamento;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			Db.closeResultSet(rs);
			Db.closeStatement(st);
		}
	}
	
	private OrcamentoCliente criarOrcamentoCliente(ResultSet rs, Cliente cliente, Produto prod) throws SQLException {
		OrcamentoCliente obj = new OrcamentoCliente();
		obj.setNumOrcamento(rs.getInt("NUM_ORCAMENTO"));
		obj.setCodCliente(cliente);
		obj.setTelefone(cliente);
		obj.setCelular(cliente);
		obj.setEmail(cliente);
		obj.setDescServico(rs.getString("DESC_SERVICO"));
		obj.setDataOrcamento(new java.util.Date(rs.getTimestamp("DATA_ORCAMENTO").getTime()));
		obj.setCodProduto(prod);
		obj.setQuantidade(rs.getInt("QUANTIDADE"));
		obj.setValor(prod);
		obj.setValorTotal(rs.getDouble("VALOR_TOTAL"));
		obj.setObs(rs.getString("OBS"));
		return obj;
	}
	
	private Cliente criarCliente(ResultSet rs) throws SQLException {
		Cliente obj = new Cliente();
		obj.setCodCliente(rs.getInt("COD_CLIENTE"));
		obj.setNome(rs.getString("NOME"));
		obj.setRg(rs.getString("RG"));
		obj.setCpf(rs.getString("CPF"));
		obj.setRua(rs.getString("RUA"));
		obj.setNumero(rs.getInt("NUMERO"));
		obj.setComplemento(rs.getString("COMPLEMENTO"));
		obj.setBairro(rs.getString("BAIRRO"));
		obj.setCep(rs.getString("CEP"));
		obj.setCidade(rs.getString("CIDADE"));
		obj.setEstado(rs.getString("ESTADO"));
		obj.setUf(rs.getString("UF"));
		obj.setDdd(rs.getInt("DDD"));
		obj.setTelefone(rs.getString("TELEFONE"));
		obj.setCelular(rs.getString("CELULAR"));
		obj.setEmail(rs.getString("EMAIL"));
		obj.setDataCadastro(new java.util.Date(rs.getTimestamp("DATA_CADASTRO").getTime()));
		obj.setObs(rs.getString("OBS"));
		return obj;
	}
	
	private Produto criarProduto(ResultSet rs) throws SQLException {
		Produto obj = new Produto();
		obj.setCodProduto(rs.getInt("COD_PRODUTO"));
		obj.setDescProduto(rs.getString("DESC_PRODUTO"));
		obj.setPrecoUnit(rs.getDouble("PRECO_UNIT"));
		return obj;
	}

}
