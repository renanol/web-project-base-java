package br.com.kamaleon.teste;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import br.com.kamaleon.model.User;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TesteJSON {

	public static void main(String[] args) throws IOException {
		
		URL url = new URL("http://localhost:8080/adm-oscar/ws/loginFL?login=sistema&senha=n3u5xj3x");
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		
		while(in.ready())
		{
			String linha = in.readLine();
			
			Gson gson = new Gson();
			List<User> videos = gson.fromJson(linha, new TypeToken<List<User>>(){}.getType());
			for(User item : videos)
			{
				System.out.println(item.getName());
			}
		}
		
	}

	
}
