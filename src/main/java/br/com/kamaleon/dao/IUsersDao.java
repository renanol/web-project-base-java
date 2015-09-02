package br.com.kamaleon.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.kamaleon.model.Users;

/**
 * Created by @author Renan Oliveira on 08/05/14
 */
public interface IUsersDao extends PagingAndSortingRepository<Users, Integer> {

    Users findByUsername(String username);
}
