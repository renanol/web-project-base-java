package br.com.kamaleon.dao;

import br.com.kamaleon.model.Authorities;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by @author Renan Oliveira on 08/05/14
 */
public interface IAuthoritiesDAO extends PagingAndSortingRepository<Authorities, Integer> {
}
