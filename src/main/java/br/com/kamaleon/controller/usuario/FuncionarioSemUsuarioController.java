package br.com.kamaleon.controller.usuario;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
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

import br.com.kamaleon.service.UserService;
import br.com.kamaleon.util.Constantes;

@Controller
@RequestMapping(value="/funcionarioSemUsuario")
public class FuncionarioSemUsuarioController {
	
	@Autowired
	private UserService userService;
	
		
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView list(){		
		ModelAndView mav = new ModelAndView("/user/listarFuncionarioSemUsuario");		
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/json")
	@ResponseBody()
	public List<Object[]> listarFuncionariosSemUsuariosj(@RequestParam("nome") String nome, @RequestParam("cpf") String cpf)  {
        
		return userService.listarFuncionariosSemUsuarios(nome, cpf);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/cadastrarUsuario")
	@ResponseBody()
	public String cadastrarUsuario( @RequestParam("cpf") String cpf)  {
		
		try {
			return userService.cadastrarUsuario(cpf);
		} catch (SQLException e) {
			e.printStackTrace();
			return "Ocorreu um erro no cadastro do usuario";
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/xls")
	@ResponseBody()
	private String gerarArquivoExportacaoXls(@RequestParam("nome") String nome, @RequestParam("cpf") String cpf) throws Exception {
		
		String nomeArquivo = "funcionariosSemUsuario.xls";
		
		File arquivo = new File(Constantes.caminhoArquivosXLS + File.separator + nomeArquivo);
		
		String url = arquivo.getAbsolutePath(); 
		List<Object[]> lista = userService.listarFuncionariosSemUsuarios(nome, cpf);
		
		String[] colunas = new String[]{"ID_USUARIO", "NM_USUARIO", "ID_FUNCIONARIO", "NM_FUNCIONARIO", "ID_ATIVIDADE", "DS_ATIVIDADE", "NU_CPF", "NM_LOJA_FUNCIONARIO", "NM_LOJA_PERMISSAO"};
		
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
	        row.createCell(0).setCellValue((String)coluna[0]);
	        row.createCell(1).setCellValue((String)coluna[1]);
	        row.createCell(2).setCellValue((String)coluna[2]);
	        row.createCell(3).setCellValue((String)coluna[3]);
	        row.createCell(4).setCellValue((String)coluna[4]);
	        row.createCell(5).setCellValue((String)coluna[5]);
	        row.createCell(6).setCellValue((String)coluna[6]);
	        row.createCell(7).setCellValue((String)coluna[7]);
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
