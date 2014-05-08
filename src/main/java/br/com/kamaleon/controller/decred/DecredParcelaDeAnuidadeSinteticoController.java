package br.com.kamaleon.controller.decred;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.kamaleon.service.decred.DecredService;

@Controller
@RequestMapping(value="/decredParcelaDeAnuidadeSintetico")
public class DecredParcelaDeAnuidadeSinteticoController {
	
	@Autowired
	private DecredService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView list(){		
		ModelAndView mav = new ModelAndView("/decred/decredParcelaDeAnuidadeSintetico");		
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/json")
	@ResponseBody()
	public List<Object[]> decredTotalEncargosAtrasoSintetico(@RequestParam("dataInicial") String dataInicial, @RequestParam("dataFinal") String dataFinal)  {
        
		return service.decredParcelaDeAnuidadeSintetico(dataInicial, dataFinal);
	}
	
}
