package br.com.kamaleon.controller.financeiro;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.kamaleon.service.UtilService;
import br.com.kamaleon.service.financeiro.FinanceiroService;
import br.com.kamaleon.util.Constantes;

@Controller
@RequestMapping(value="/listarRateiosDespesaDivergentes")
public class ListarRateiosDespesaDivergentesController {
	
	@Autowired
	private FinanceiroService service;
	
	@Autowired
	private UtilService utilService;
	
	@ModelAttribute(value="listaTipoDespesa")
	public List<Object[]> getListaTipoDespesa() {
						
		return utilService.listarTipoDespesa();
	}

	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView list(){		
		ModelAndView mav = new ModelAndView("/financeiro/listarRateiosDespesaDivergentes");		
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/jsonComboGrupoDespesa")
	@ResponseBody()
	public List<Object[]> preencherComboGrupoDespesa(@RequestParam("codigoTipoDespesa") int codigoTipoDespesa)  {
		
		return utilService.listarGrupoDespesa(codigoTipoDespesa);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/jsonComboSubGrupoDespesa")
	@ResponseBody()
	public List<Object[]> preencherComboSubGrupoDespesa(@RequestParam("codigoGrupoDespesa") int codigoGrupoDespesa)  {
		
		return utilService.listarSubGrupoDespesa(codigoGrupoDespesa);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/json")
	@ResponseBody()
	public List<Object[]> listar(@RequestParam("codigoTipoDespesa") int codigoTipoDespesa, @RequestParam("codigoGrupoDespesa") int codigoGrupoDespesa,
			@RequestParam("codigoSubGrupoDespesa") int codigoSubGrupoDespesa,
			@RequestParam("dataInicial") String dataInicial, @RequestParam("dataFinal") String dataFinal)  {
        
		return service.listarRateiosDespesasDivergentes(codigoTipoDespesa, codigoGrupoDespesa, codigoSubGrupoDespesa, dataInicial, dataFinal);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/xls")
	@ResponseBody()
	private String gerarArquivoExportacaoXls(@RequestParam("codigoTipoDespesa") int codigoTipoDespesa, @RequestParam("codigoGrupoDespesa") int codigoGrupoDespesa,
			@RequestParam("codigoSubGrupoDespesa") int codigoSubGrupoDespesa,
			@RequestParam("dataInicial") String dataInicial, @RequestParam("dataFinal") String dataFinal)
			throws Exception {
		
		String nomeArquivo = "RateiosDespesaDivergentes.xls";
		
		File arquivo = new File(Constantes.caminhoArquivosXLS + File.separator + nomeArquivo);
		
		String url = arquivo.getAbsolutePath(); 
		List<Object[]> lista = service.listarRateiosDespesasDivergentes(codigoTipoDespesa, codigoGrupoDespesa, codigoSubGrupoDespesa, dataInicial, dataFinal);

		String[] colunas = new String[]{ 
				   "ID_DESPESA",
				   "DS_DESPESA",
				   "ID_TIPO_DESPESA",
				   "DS_TIPO_DESPESA",
				   "ID_GRUPO_DESPESA",
				   "DS_GRUPO_DESPESA",
				   "ID_SUB_GRUPO_DESPESA",
				   "DS_SUB_GRUPO_DESPESA",
				   "DT_VENCIMENTO",
				   "VL_DESPESA",
				   "VL_RATEIO",
				   "VL_DIFERENCA",
				   "VL_PERCENTUAL_RATEIO"
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
