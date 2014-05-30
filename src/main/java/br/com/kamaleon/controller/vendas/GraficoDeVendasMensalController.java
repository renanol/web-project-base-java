package br.com.kamaleon.controller.vendas;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.kamaleon.service.UtilService;
import br.com.kamaleon.service.vendas.VendasService;

@Controller
@RequestMapping(value="/graficoDeVendasMensal")
public class GraficoDeVendasMensalController {
	
	@Autowired
	private VendasService service;
		
	@Autowired
	private UtilService utilService;
	
	@ModelAttribute(value="listaEstabelecimento")
	public List<Object[]> getListaEstabelecimento() {
						
		return utilService.listarEstabelecimentosAtivos();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView list(){		
		ModelAndView mav = new ModelAndView("/vendas/graficoDeVendasMensal");		
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/json")
	@ResponseBody()
	public HashMap listar(@RequestParam("loja") int loja, @RequestParam("dataInicial") String dataInicial, @RequestParam("dataFinal") String dataFinal)  {
        
		return service.getDadosGraficoVendaMensal(loja, dataInicial, dataFinal);
	}
	
}
