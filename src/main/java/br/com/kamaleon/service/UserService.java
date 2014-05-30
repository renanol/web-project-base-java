package br.com.kamaleon.service;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kamaleon.dao.IUsersDao;
import br.com.kamaleon.dao.UserRepository;
import br.com.kamaleon.model.User;
import br.com.kamaleon.model.Users;

/**
 * Created by @author Renan Oliveira on 08/05/14
 */
@Service
public class UserService {

    @Resource
    private IUsersDao usersDao;
    
    @Autowired
    private UserRepository userRepository;


    public Users findByUsername(String username) {
        return usersDao.findByUsername(username);
    }
    
    public List<User> listAll(String name, String username){
		return userRepository.findAll(name, username);
	}
    
    public User getUser(User user){
    	return userRepository.getUser(user);
    }
    
    public List<Object[]> listarFuncionariosSemUsuarios(String nome, String cpf){
    	return userRepository.listarFuncionariosSemUsuarios(nome, cpf);
    }
    
    public String cadastrarUsuario(String cpf) throws SQLException{
    	return userRepository.cadastrarUsuario( cpf);
    }

	public List<Object[]> listarUsuarioFuncionario(String nome, String login,
			String atividade) {
		// TODO Auto-generated method stub
		return userRepository.listarUsuarioFuncionario(nome, login, atividade);
	}

	public List<Object[]> listarUsuarioGrupo(String nome, String login,
			String grupo) {
		// TODO Auto-generated method stub
		return userRepository.listarUsuarioGrupo(nome, login, grupo);
	}
	
	public List<Object[]> listarUsuarioGrupoCards(String nome, String login,
			String grupo) {
		// TODO Auto-generated method stub
		return userRepository.listarUsuarioGrupoCards(nome, login, grupo);
	}
	
}
