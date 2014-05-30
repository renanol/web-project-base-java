package br.com.kamaleon.controller.fidelidade;

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

import br.com.kamaleon.service.fidelidade.FidelidadeService;
import br.com.kamaleon.util.Constantes;

@Controller
@RequestMapping(value="/pontuacaoFidelidadePorFaixa")
public class ListarPontuacaoFidelidadePorFaixaController {
	
	@Autowired
	private FidelidadeService service;
	
		
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView list(){		
		ModelAndView mav = new ModelAndView("/fidelidade/listarPontuacaoFidelidadePorFaixa");		
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/json")
	@ResponseBody()
	public List<Object[]> listar(@RequestParam("codigoPromocao") String codigoPromocao)  {
        
		return service.listarPontuacaoFidelidadePorFaixa(codigoPromocao);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/xls")
	@ResponseBody()
	private String gerarArquivoExportacaoXls(@RequestParam("codigoPromocao") String codigoPromocao) throws Exception {
		
		String nomeArquivo = "pontuacaoFidelidadePorFaixa.xls";
		
		File arquivo = new File(Constantes.caminhoArquivosXLS + File.separator + nomeArquivo);
		
		String url = arquivo.getAbsolutePath(); 
		List<Object[]> lista = service.listarPontuacaoFidelidadePorFaixa(codigoPromocao);

		String[] colunas = new String[]{ "Promocao",
	      "Ate 50",
	      "51 a 70",
	      "71 a 100",
	      "101 a 130",
	      "131 a 150",
	      "151 a 200",
	      "201 a 230",
	      "231 a 250",
	      "251 a 300",
	      "301 a 370",
	      "371 a 400",
	      "401 a 500",
	      "501 a 600",
	      "601 a 700",
	      "701 a 800",
	      "801 a 870",
	      "871 a 900",
	      "901 a 1000",
	      "1001 a 1500",
	      "1501 a 2000",
	      "2001 a 3000",
	      "3001 a 4000",
	      "> 4000"};
		
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
	        row.createCell(22).setCellValue(coluna[22]+"");
	        row.createCell(23).setCellValue(coluna[23]+"");
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
