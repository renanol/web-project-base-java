package br.com.kamaleon.service;

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
	
}
