package br.com.kamaleon.controller.cliente;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.kamaleon.service.cliente.ClienteService;
import br.com.kamaleon.util.Constantes;

@Controller
@RequestMapping(value="/cadastroClientesCardsMega")
public class ListarCadastroClientesCardsMegaController {
	
	@Autowired
	private ClienteService service;
		
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView list(){		
		ModelAndView mav = new ModelAndView("/cliente/listarCadastroClientesCardsMega");		
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/json")
	@ResponseBody()
	public List<Object[]> listar(@RequestParam("nome") String nome, @RequestParam("cpfcnpj") String cpfcnpj, @RequestParam("existeMega") String existeMega, @RequestParam("existeCards") String existeCards)  {
        
		return service.listarCadastroClientesCardsMega(nome, cpfcnpj, existeMega, existeCards);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/xls")
	@ResponseBody()
	private String gerarArquivoExportacaoXls(@RequestParam("nome") String nome, @RequestParam("cpfcnpj") String cpfcnpj, @RequestParam("existeMega") String existeMega, @RequestParam("existeCards") String existeCards) throws Exception {
		
		String nomeArquivo = "CadastroClientesMegaCards.xls";
		
		File arquivo = new File(Constantes.caminhoArquivosXLS + File.separator + nomeArquivo);
		
		String url = arquivo.getAbsolutePath(); 
		List<Object[]> lista = service.listarCadastroClientesCardsMega(nome, cpfcnpj, existeMega, existeCards);

		String[] colunas = new String[]{ 
				"ID_CLIENTE",
			      "NOME_CLIENTE",
			      "FL_STATUS",			      
			      "DS_STATUS",	      	      
			      "NU_CPF",	      	      
			      "NU_CARTAO",	      	      
			      "FL_STATUS_CARTAO",	      	      
			      "DS_STATUS_CARTAO",	      	      
			      "DATA CADASTRO",	      	      
			      "SISTEMA",	      	      
			      "EXISTE MEGA",
			      "EXISTE CARDS"
	      };
		
	    HSSFWorkbook wb = new HSSFWorkbook();
	    HSSFSheet plan1 = null;
	    FileOutputStream file = null;
	    
	    try {

	      file = new FileOutputStream(new File(url));
	      plan1 = wb.createSheet("Plan1");
	      HSSFRow row = null;
	      int linha = 0;
	      boolean imprimiuColunas = false;
	      for (Object[] coluna : lista) {
	        row = plan1.createRow(linha);
	        if (!imprimiuColunas) {
	          for (int i = 0; i < colunas.length; i++) {
	            row.createCell(i).setCellValue(colunas[i]);
	            imprimiuColunas = true;
	          }
	          linha++;
	          row = plan1.createRow(linha);
	        }
	        linha++;
	        row.createCell(0).setCellValue(coluna[0]+"");
	        row.createCell(1).setCellValue(coluna[1]+"");
	        row.createCell(2).setCellValue(coluna[2]+"");
	        row.createCell(3).setCellValue(coluna[3]+"");
	        row.createCell(4).setCellValue(coluna[4]+"");
	        row.createCell(5).setCellValue(coluna[5]+"");
	        row.createCell(6).setCellValue(coluna[6]+"");
	        row.createCell(7).setCellValue(coluna[7]+"");
	        row.createCell(8).setCellValue(coluna[8]+"");
	        row.createCell(9).setCellValue(coluna[9]+"");
	        row.createCell(10).setCellValue(coluna[10]+"");
	        row.createCell(11).setCellValue(coluna[11]+"");
	      }
	      wb.write(file);

	    } catch (Exception e) {
	      // TODO: handle exception
	    } finally {
	      if (file != null)
	        file.close();
	    }

	    return "/adm-oscar/xls/"+nomeArquivo;
	  }
}
