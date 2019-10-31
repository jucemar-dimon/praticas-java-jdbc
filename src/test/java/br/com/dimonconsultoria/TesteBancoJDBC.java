package br.com.dimonconsultoria;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import br.com.dimonconsultoria.dao.UserPosDAO;
import br.com.dimonconsultoria.model.BeanUserFone;
import br.com.dimonconsultoria.model.Telefone;
import br.com.dimonconsultoria.model.UserPosJava;

public class TesteBancoJDBC {

	@Test
	public void testeInserir() {
		UserPosDAO dao = new UserPosDAO();
		UserPosJava user = new UserPosJava();
		user.setNome("Juliano");
		user.setEmail("jcdeadbody@bol.com.br");
		dao.salvar(user);
	}

	@Test
	public void testeListar() {
		UserPosDAO dao = new UserPosDAO();
		try {
			List<UserPosJava> list = dao.listar();
			for (UserPosJava userPosJava : list) {
				System.out.println(userPosJava);
				System.out.println("-----------------------");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testeBuscar() {
		UserPosDAO dao = new UserPosDAO();
		try {
			UserPosJava list = dao.buscar(1L);
			System.out.println(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testeAtualizar() {
		UserPosDAO dao = new UserPosDAO();
		try {
			UserPosJava objeto = dao.buscar(1L);
			objeto.setNome("TESTE");
			dao.atualizar(objeto);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testeDeletar() {
		UserPosDAO dao = new UserPosDAO();
		dao.deletar(9L);
	}

	@Test
	public void testeSalvarTelefone() {
		Telefone telefone = new Telefone();
		telefone.setUsuario(10L);
		telefone.setNumero("(48) 33486435");
		telefone.setTipo("celular");
		UserPosDAO dao = new UserPosDAO();
		dao.salvarTelefone(telefone);
	}

	@Test
	public void testeCarregaFonesUser() {
		UserPosDAO dao = new UserPosDAO();
		List<BeanUserFone> lista =dao.listaUserFone(10L);		
		for (BeanUserFone beanUserFone : lista) {
			System.out.println(beanUserFone);			
		}
	}
	
	@Test
	public void testeDeleteUserFone() {
		UserPosDAO userPosDAO=new UserPosDAO();
		userPosDAO.deleteFonesPorUser(10L);
		
	}

}
