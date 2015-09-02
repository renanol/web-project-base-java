package br.com.kamaleon.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.com.kamaleon.dao.IAuthoritiesDAO;

/**
 * Created by @author Renan Oliveira on 08/05/14
 */
@Service
public class AuthoritiesService {

    @Resource
    private IAuthoritiesDAO authoritiesDAO;

}
