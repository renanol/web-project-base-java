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
@RequestMapping(value="/clientesComMaisDeUmCartaoNoCards")
public class ListarClientesComMaisDeUmCartaoNoCardsController {
	
	@Autowired
	private ClienteService service;
		
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView list(){		
		ModelAndView mav = new ModelAndView("/cliente/listarClientesComMaisDeUmCartaoNoCards");		
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/json")
	@ResponseBody()
	public List<Object[]> listar(@RequestParam("nome") String nome, @RequestParam("cpfcnpj") String cpfcnpj, @RequestParam("numeroCartao") String numeroCartao, 
			@RequestParam("statusCliente") String statusCliente, @RequestParam("statusCartao") String statusCartao, @RequestParam("nomeDependente") String nomeDependente,
			@RequestParam("numeroCartaoDependente") String numeroCartaoDependente, @RequestParam("statusCartaoDependente") String statusCartaoDependente,
			@RequestParam("statusCartaoTitular") String statusCartaoTitular)  {
        
		return service.listarClientesComMaisDeUmCartaoNoCards(nome, cpfcnpj, numeroCartao, statusCliente, statusCartao, nomeDependente, numeroCartaoDependente,statusCartaoDependente, statusCartaoTitular);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/xls")
	@ResponseBody()
	private String gerarArquivoExportacaoXls(@RequestParam("nome") String nome, @RequestParam("cpfcnpj") String cpfcnpj, @RequestParam("numeroCartao") String numeroCartao, 
			@RequestParam("statusCliente") String statusCliente, @RequestParam("statusCartao") String statusCartao, @RequestParam("nomeDependente") String nomeDependente,
			@RequestParam("numeroCartaoDependente") String numeroCartaoDependente, @RequestParam("statusCartaoDependente") String statusCartaoDependente,
			@RequestParam("statusCartaoTitular") String statusCartaoTitular) throws Exception {
		
		String nomeArquivo = "clientesComMaisDeUmCartaoNoCards.xls";
		
		File arquivo = new File(Constantes.caminhoArquivosXLS + File.separator + nomeArquivo);
		
		String url = arquivo.getAbsolutePath(); 
		List<Object[]> lista = service.listarClientesComMaisDeUmCartaoNoCards(nome, cpfcnpj, numeroCartao, statusCliente, statusCartao, nomeDependente, numeroCartaoDependente,statusCartaoDependente, statusCartaoTitular);

		String[] colunas = new String[]{ 
				"ID_CLIENTE",
				  "NM_CLIENTE",
				  "NU_CPF",
				  "FL_STATUS_CLIENTE",
				  "DS_STATUS_CLIENTE",
				  "NU_CARTAO",
				  "TP_CARTAO",
				  "FL_STATUS_CARTAO",
				  "DS_STATUS_CARTAO",
				  "ID_TIPO_BLOQUEIO",
				  "DS_TIPO_BLOQUEIO",
				  "ID_TITULAR",
				  "NM_TITULAR",
				  "NM_CLIENTE_DEP",
				  "FL_STATUS_TITULAR_DEP",
				  "DS_STATUS_TITULAR",
				  "NU_CARTAO_DEP",
				  "TP_CARTAO_DEP",
				  "FL_STATUS_CARTAO_DEP",
				  "DS_STATUS_CARTAO_DEP",
				  "ID_TIPO_BLOQUEIO_TITULAR",
				  "DS_TIPO_BLOQUEIO_TITULAR"
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
	        row.createCell(12).setCellValue(coluna[12]+"");
	        row.createCell(13).setCellValue(coluna[13]+"");
	        row.createCell(14).setCellValue(coluna[14]+"");
	        row.createCell(15).setCellValue(coluna[15]+"");
	        row.createCell(16).setCellValue(coluna[16]+"");
	        row.createCell(17).setCellValue(coluna[17]+"");
	        row.createCell(18).setCellValue(coluna[18]+"");
	        row.createCell(19).setCellValue(coluna[19]+"");
	        row.createCell(20).setCellValue(coluna[20]+"");
	        row.createCell(21).setCellValue(coluna[21]+"");
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
