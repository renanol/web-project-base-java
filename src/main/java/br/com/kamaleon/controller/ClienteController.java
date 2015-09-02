package br.com.kamaleon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.kamaleon.service.cliente.ClienteService;

@Controller
@RequestMapping(value="/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService clientesService;
		
	@RequestMapping(method=RequestMethod.GET, value="/cadastrar")
	public ModelAndView listarCliente()  {
		
		ModelAndView mav = new ModelAndView("cliente/cliente");
		mav.addObject("teste", "Hello Word");
		return mav;
	}
	
}
