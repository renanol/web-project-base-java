package br.com.kamaleon.controller.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.kamaleon.service.cliente.ClienteService;

@Controller
@RequestMapping(value="/ws")
public class Webservices {
	
	@Autowired
	private ClienteService clientesService;
		
	@RequestMapping(method=RequestMethod.GET, value="/listarCliente")
	@ResponseBody()
	public List<Object[]> listarCliente(@RequestParam("nome") String nome, @RequestParam("cpfCnpj") String cpfCnpj, @RequestParam("status") String status, @RequestParam("tipo") int tipo)  {
		
		return clientesService.listarCliente(nome, cpfCnpj, status, tipo);
	}
	
}
