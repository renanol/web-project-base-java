package br.com.kamaleon.frenteDeLoja;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import br.com.kamaleon.model.User;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

public class Fachada {

	public static User login(String login, String senha) throws Exception
	{
		URL url = new URL("http://localhost:8080/adm-oscar/ws/loginFL?login="+login+"&senha="+senha);
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		
		User usuario = null;
		String linhas = "";
		while(in.ready())
		{
			linhas += in.readLine();
		}
		
		Gson gson = new Gson();
		
		Object[] objeto = gson.fromJson(linhas, Object[].class);
		
		if(objeto != null)
		{
			usuario = new User();
			usuario.convertUser(objeto);
		}
		else
		{
			throw new Exception("Usuario n\u00e3o encontrado!");
		}
		
		return usuario;
	}
	
	public static List<Object[]> listarCliente(String nome, String cpfcnpj, String status, int tipo) throws JsonSyntaxException, IOException
	{
		URL url = new URL("http://localhost:8080/adm-oscar/ws/listarCliente?nome="+URLEncoder.encode(nome,"UTF-8")+"&cpfCnpj="+cpfcnpj+"&status="+status+"&tipo="+tipo);
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		
		String linhas = "";
		while(in.ready())
		{
			linhas += in.readLine();
		}
		System.out.println(linhas);
		
		Gson gson = new Gson();
		List<Object[]> lista = gson.fromJson(linhas, new TypeToken<List<Object[]>>(){}.getType());
		
		return lista;
	}
	
}
