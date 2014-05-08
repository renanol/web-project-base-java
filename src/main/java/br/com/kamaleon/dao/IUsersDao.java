package br.com.kamaleon.dao;

import br.com.kamaleon.model.Users;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by @author Renan Oliveira on 08/05/14
 */
public interface IUsersDao extends PagingAndSortingRepository<Users, Integer> {

    Users findByUsername(String username);
}
