package br.com.kamaleon.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.kamaleon.model.User;
import br.com.kamaleon.util.EnviarEmail;
import br.com.kamaleon.util.ValidadorUniversal;

@Repository
public class UserRepository {
	
	@PersistenceContext
	protected EntityManager entityManager;

	public List<User> findAll(String nome, String login){

		List<User> listaUser = new ArrayList();
		
		User usuario = null;
		
		String sql = "SELECT U.IDUSUARIO AS ID_USUARIO, U.NOME AS NM_USUARIO, U.LOGIN AS NM_LOGIN, M.CODIGO AS NU_SENHA "
				+ " FROM   T_METAIDENTIFICADORPORTAL M,"
				+ " T_USUARIO U"
				+ " WHERE  "
				+ " U.IDUSUARIO = M.IDUSUARIO AND"
				+ " U.STATUS = 'A' ";
		
		if(nome != null && nome.length() > 0)
		{
			sql += " and upper(u.nome) like upper('" + nome + "%') ";
		}
		if(login != null && login.length() > 0)
		{
			sql += " and upper(u.login) like upper('" + login + "%') ";
		}
				
		sql += "order by u.nome";
		
		
		List<Object[]> lista = entityManager.createNativeQuery(sql).getResultList();
		
		for (Object[] objeto : lista)
		{
			User user = new User();
			user.setId(((BigDecimal)objeto[0]).intValue());
			user.setName((String) objeto[1]);
			user.setUsername((String) objeto[2]);
			user.setPassword((String) objeto[3]);
			
			listaUser.add(user);
		}
		return listaUser;
	}
	
	public List<Object[]> listarFuncionariosSemUsuarios(String nome, String cpf){
		
		String sql = "select * from Vw_rel_funcionario_sem_usuario where 1 = 1 ";
		
		if(nome != null && nome.length() > 0)
		{
			sql += " and upper(nm_funcionario) like upper('" + nome + "%') ";
		}
		if(cpf != null && cpf.length() > 0)
		{
			sql += " and nu_cpf = '" + cpf + "' ";
		}
		
		List<Object[]> lista = entityManager.createNativeQuery(sql).getResultList();
		
		return lista;
	}
	
	public User getUser(User user){
		
		User usuario = new User();
		usuario.setName("Renan Oliveira");
		
		return usuario;
	}
	

	@Transactional
	public User save(User user){
		entityManager.persist(user);
		return user;
	}

	/**
	 * Neste caso a conexao � manual, j� que precisamos cadastrar no banco de produ��o, sem ser o full
	 * 
	 * @param cpf
	 * @return
	 * @throws SQLException 
	 */
	public String cadastrarUsuario(String cpf) throws SQLException {
		return null;
	}

	public List<Object[]> listarUsuarioFuncionario(String nome, String login,
			String atividade) {
		
		String sql = "SELECT * "
				+ " FROM   VW_REL_USUARIOS_FUNCIONARIOS"
				+ " WHERE 1=1 ";
				
		if(ValidadorUniversal.check(login))
		{
			sql += " and NM_USUARIO like '" + login.toUpperCase() + "%'";  
		}
		if(ValidadorUniversal.check(nome))
		{
			sql += " and NM_FUNCIONARIO like '" + nome.toUpperCase() + "%'";  
		}
		if(ValidadorUniversal.check(atividade))
		{
			sql += " and DS_ATIVIDADE like '" + atividade.toUpperCase() + "%'";  
		}
		
		List<Object[]> lista = entityManager.createNativeQuery(sql).getResultList();
		
		return lista;
	}

	public List<Object[]> listarUsuarioGrupo(String nome, String login, String grupo) {
		
		String sql = "SELECT * "
				+ " FROM   vw_rel_usuarios_grupos"
				+ " WHERE 1=1 ";
		
		if(ValidadorUniversal.check(login))
		{
			sql += " and NM_LOGIN_UPPER like '" + login.toUpperCase() + "%'";  
		}
		if(ValidadorUniversal.check(nome))
		{
			sql += " and NM_usuario like '" + nome.toUpperCase() + "%'";  
		}
		if(ValidadorUniversal.check(grupo))
		{
			sql += " and nm_grupo like '" + grupo.toUpperCase() + "%'";  
		}
		
		List<Object[]> lista = entityManager.createNativeQuery(sql).getResultList();
		
		return lista;
	}
	
	public List<Object[]> listarUsuarioGrupoCards(String nome, String login, String grupo) {
		
		String sql = "SELECT * "
				+ " FROM   vw_rel_usuarios_grupos_cards"
				+ " WHERE 1=1 ";
		
		if(ValidadorUniversal.check(login))
		{
			sql += " and NM_LOGIN_UPPER like '" + login.toUpperCase() + "%'";  
		}
		if(ValidadorUniversal.check(nome))
		{
			sql += " and NM_usuario like '" + nome.toUpperCase() + "%'";  
		}
		if(ValidadorUniversal.check(grupo))
		{
			sql += " and nm_grupo like '" + grupo.toUpperCase() + "%'";  
		}
		
		List<Object[]> lista = entityManager.createNativeQuery(sql).getResultList();
		
		return lista;
	}

}