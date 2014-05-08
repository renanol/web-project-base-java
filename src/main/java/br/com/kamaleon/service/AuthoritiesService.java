package br.com.kamaleon.service;

import br.com.kamaleon.dao.IAuthoritiesDAO;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by @author Renan Oliveira on 08/05/14
 */
@Service
public class AuthoritiesService {

    @Resource
    private IAuthoritiesDAO authoritiesDAO;

}
