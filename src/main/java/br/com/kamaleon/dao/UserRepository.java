package br.com.kamaleon.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.kamaleon.model.User;

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
			user.setId(((BigDecimal)objeto[0]).longValue());
			user.setName((String) objeto[1]);
			user.setUsername((String) objeto[2]);
			user.setPassword((String) objeto[3]);
			
			listaUser.add(user);
		}
		return listaUser;
	}
	
	public User getUser(User user){
		
		User usuario = null;
		
		List<Object[]> lista = entityManager.createNativeQuery("SELECT U.IDUSUARIO AS ID_USUARIO, U.NOME AS NM_USUARIO, U.LOGIN AS NM_LOGIN, M.CODIGO AS NU_SENHA "
				+ " FROM   T_METAIDENTIFICADORPORTAL M,"
				+ " T_USUARIO U, T_GRUPOUSUARIO G"
				+ " WHERE  "
				+ " U.IDUSUARIO = M.IDUSUARIO AND"
				+ " U.IDUSUARIO = G.IDUSUARIO AND"
				+ " G.IDGRUPO IN (1,109) AND"
				+ " U.STATUS = 'A' AND"
				+ " U.LOGIN = '"+user.getUsername()+"' AND"
				+ " m.CODIGO = '"+ user.getPassword()+"' ").getResultList();
		
		for (Object[] objeto : lista)
		{
			System.out.println(objeto[0] + "\t" + objeto[1] + "\t" + objeto[2] + "\t" + objeto[3]);
			user.setId(((BigDecimal)objeto[0]).longValue());
			return user;
		}
		
		return null;
	}

	@Transactional
	public User save(User user){
		entityManager.persist(user);
		return user;
	}

}