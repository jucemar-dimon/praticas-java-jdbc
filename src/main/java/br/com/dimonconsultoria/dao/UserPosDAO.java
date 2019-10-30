package br.com.dimonconsultoria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dimonconsultoria.jdbc.SingleConnection;
import br.com.dimonconsultoria.model.BeanUserFone;
import br.com.dimonconsultoria.model.Telefone;
import br.com.dimonconsultoria.model.UserPosJava;

public class UserPosDAO {

	private Connection connection;

	public UserPosDAO() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(UserPosJava user) {
		try {
			String sql = "insert into userposjava (nome,email) values (?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, user.getNome());
			insert.setString(2, user.getEmail());
			insert.execute();
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();

		}
	}

	public void salvarTelefone(Telefone telefone) {
		try {
			String sql = "INSERT INTO telefoneuser (numero,tipo,usuariopessoa) VALUES (?,?,?);";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, telefone.getNumero());
			insert.setString(2, telefone.getTipo());
			insert.setLong(3, telefone.getUsuario());
			insert.execute();
			connection.commit();
			try {
				connection.rollback();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<UserPosJava> listar() throws SQLException {
		List<UserPosJava> list = new ArrayList<UserPosJava>();
		String sql = "select * from userposjava";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		while (resultado.next()) {
			UserPosJava user = new UserPosJava();
			user.setId(resultado.getLong("id"));
			user.setNome(resultado.getString("nome"));
			user.setEmail(resultado.getString("email"));
			list.add(user);
		}

		return list;
	}

	public UserPosJava buscar(Long id) throws SQLException {
		UserPosJava retorno = new UserPosJava();
		String sql = "select * from userposjava where id=" + id;

		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {
			retorno.setId(resultado.getLong("id"));
			retorno.setNome(resultado.getString("nome"));
			retorno.setEmail(resultado.getString("email"));

		}
		return retorno;
	}

	public void atualizar(UserPosJava userPosJava) {
		String sql = "update userposjava set nome=? where id=" + userPosJava.getId();
		try {
			PreparedStatement update = connection.prepareStatement(sql);
			update.setString(1, userPosJava.getNome());
			update.execute();
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void deletar(Long id) {
		try {
			String sql = "delete from userposjava where id=" + id;
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.execute();
			connection.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public List<BeanUserFone> listaUserFone(Long idUser) {
		List<BeanUserFone> resultado = new ArrayList<BeanUserFone>();
		String sql = "SELECT nome,numero,email from telefoneuser as fone ";
		sql += "inner join userposjava as userp ";
		sql += "on fone.usuariopessoa=userp.id ";
		sql += "where userp.id=" + idUser;
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet result=statement.executeQuery();
			while(result.next()) {
				BeanUserFone beanUserFone=new BeanUserFone();
				beanUserFone.setNome(result.getString("nome"));
				beanUserFone.setEmail(result.getString("email"));
				beanUserFone.setNumero(result.getString("numero"));
				resultado.add(beanUserFone);
			}			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return resultado;
	}
	
	public void deleteFonesPorUser(Long id) {
		String sqlTelefone="DELETE * FROM telefoneuser WHERE id="+id;
		String sqlUsers="DELETE * FROM userposjava WHERE id="+id;
		try{
			PreparedStatement preparedStatement=connection.prepareStatement(sqlUsers);
			preparedStatement.executeUpdate();			
		}catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}