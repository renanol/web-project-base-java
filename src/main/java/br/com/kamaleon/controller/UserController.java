package br.com.kamaleon.controller;

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

import br.com.kamaleon.model.User;
import br.com.kamaleon.service.UserService;
import br.com.kamaleon.util.Constantes;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
		
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView list(){		
		ModelAndView mav = new ModelAndView("/user/listUser");		
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/json")
	@ResponseBody()
	public List<User> login(@RequestParam("name") String name, @RequestParam("username") String username)  {
        
		return userService.listAll(name, username);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/xls")
	@ResponseBody()
	private String gerarArquivoExportacaoXls(@RequestParam("name") String name, @RequestParam("username") String username) throws Exception {
		
		String nomeArquivo = "usuarios.xls";
		
		File arquivo = new File(Constantes.caminhoArquivosXLS + File.separator + nomeArquivo);
		
		String url = arquivo.getAbsolutePath(); 
		List<User> lista = userService.listAll(name, username); 
		
		String[] colunas = new String[]{"Nome", "Login", "Senha"};
		
	    HSSFWorkbook wb = new HSSFWorkbook();
	    HSSFSheet plan1 = null;
	    FileOutputStream file = null;
	    
	    try {

	      file = new FileOutputStream(new File(url));
	      plan1 = wb.createSheet("Plan1");
	      HSSFRow row = null;
	      int linha = 0;
	      boolean imprimiuColunas = false;
	      for (User coluna : lista) {
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
	        row.createCell(0).setCellValue(coluna.getName());
	        row.createCell(1).setCellValue(coluna.getUsername());
	        row.createCell(2).setCellValue(coluna.getPassword());
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
