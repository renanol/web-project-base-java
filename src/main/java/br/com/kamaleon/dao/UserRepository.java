package br.com.kamaleon.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.kamaleon.model.User;
import br.com.kamaleon.util.EnviarEmail;
import br.com.kamaleon.util.ValidadorUniversal;

@Repository
public class UserRepository {
	
	@PersistenceContext
	protected EntityManager entityManager;

	public List<User> findAll(String nome, String login){

		List<User> listaUser = new ArrayList();
		
		User usuario = null;
		
		String sql = "SELECT U.IDUSUARIO AS ID_USUARIO, U.NOME AS NM_USUARIO, U.LOGIN AS NM_LOGIN, M.CODIGO AS NU_SENHA "
				+ " FROM   T_METAIDENTIFICADORPORTAL M,"
				+ " T_USUARIO U"
				+ " WHERE  "
				+ " U.IDUSUARIO = M.IDUSUARIO AND"
				+ " U.STATUS = 'A' ";
		
		if(nome != null && nome.length() > 0)
		{
			sql += " and upper(u.nome) like upper('" + nome + "%') ";
		}
		if(login != null && login.length() > 0)
		{
			sql += " and upper(u.login) like upper('" + login + "%') ";
		}
				
		sql += "order by u.nome";
		
		
		List<Object[]> lista = entityManager.createNativeQuery(sql).getResultList();
		
		for (Object[] objeto : lista)
		{
			User user = new User();
			user.setId(((BigDecimal)objeto[0]).intValue());
			user.setName((String) objeto[1]);
			user.setUsername((String) objeto[2]);
			user.setPassword((String) objeto[3]);
			
			listaUser.add(user);
		}
		return listaUser;
	}
	
	public List<Object[]> listarFuncionariosSemUsuarios(String nome, String cpf){
		
		String sql = "select * from Vw_rel_funcionario_sem_usuario where 1 = 1 ";
		
		if(nome != null && nome.length() > 0)
		{
			sql += " and upper(nm_funcionario) like upper('" + nome + "%') ";
		}
		if(cpf != null && cpf.length() > 0)
		{
			sql += " and nu_cpf = '" + cpf + "' ";
		}
		
		List<Object[]> lista = entityManager.createNativeQuery(sql).getResultList();
		
		return lista;
	}
	
	public User getUser(User user){
		
		User usuario = null;
		
		List<Object[]> lista = entityManager.createNativeQuery("SELECT U.IDUSUARIO AS ID_USUARIO, U.NOME AS NM_USUARIO, U.LOGIN AS NM_LOGIN, M.CODIGO AS NU_SENHA "
				+ " FROM   T_METAIDENTIFICADORPORTAL M,"
				+ " T_USUARIO U, T_GRUPOUSUARIO G"
				+ " WHERE  "
				+ " U.IDUSUARIO = M.IDUSUARIO AND"
				+ " U.IDUSUARIO = G.IDUSUARIO AND"
				+ " G.IDGRUPO IN (1,109) AND"
				+ " U.STATUS = 'A' AND"
				+ " U.LOGIN = '"+user.getUsername()+"' AND"
				+ " m.CODIGO = '"+ user.getPassword()+"' ").getResultList();
		
		for (Object[] objeto : lista)
		{
			System.out.println(objeto[0] + "\t" + objeto[1] + "\t" + objeto[2] + "\t" + objeto[3]);
			user.setId(((BigDecimal)objeto[0]).intValue());
			return user;
		}
		
		return null;
	}
	
	public Object[] getUser(String login, String senha){
		
		List<Object[]> lista = entityManager.createNativeQuery("SELECT U.IDUSUARIO AS ID_USUARIO, U.NOME AS NM_USUARIO, U.LOGIN AS NM_LOGIN, U.SENHA "
				+ " FROM   T_METAIDENTIFICADORPORTAL M,"
				+ " T_USUARIO U, T_GRUPOUSUARIO G"
				+ " WHERE  "
				+ " U.IDUSUARIO = M.IDUSUARIO AND"
				+ " U.IDUSUARIO = G.IDUSUARIO AND"
				+ " G.IDGRUPO IN (1,109) AND"
				+ " U.STATUS = 'A' AND"
				+ " U.LOGIN = '"+login+"' AND"
				+ " m.CODIGO = '"+ senha +"' ").getResultList();
		
		for (Object[] objeto : lista)
		{
			System.out.println(objeto[0] + "\t" + objeto[1] + "\t" + objeto[2] + "\t" + objeto[3]);
			
			return objeto;
		}
		
		return null;
	}

	@Transactional
	public User save(User user){
		entityManager.persist(user);
		return user;
	}

	/**
	 * Neste caso a conexao é manual, já que precisamos cadastrar no banco de produção, sem ser o full
	 * 
	 * @param cpf
	 * @return
	 * @throws SQLException 
	 */
	public String cadastrarUsuario(String cpf) throws SQLException {
//
//CREATE OR REPLACE PROCEDURE PR_ADM_CADASTRAR_USUARIO_V2 (XNU_CPF VARCHAR2, XDS_USUARIO OUT VARCHAR2) AS
//
//  --XNU_CPF      T_FUNCIONARIO.CPF%TYPE := '25224250803';
//  XID_USUARIO  T_USUARIO.IDUSUARIO%TYPE;
//  XNM_LOGIN    T_USUARIO.LOGIN%TYPE;
//  XIS_VALIDO   NUMBER := 0;
//  XNU_CONT     NUMBER := 0;
//
//  XDS_MENSAGEM VARCHAR2(2000);
//  CRLF         VARCHAR2(2):= CHR(13)||CHR(10);
//
//  CURSOR C_USUARIO IS
//  SELECT F.NOME                                                                                                       AS NOME,
//         F.CPF                                                                                                        AS CPF,
//         REPLACE(REPLACE(NVL(SUBSTR(F.NOME, 1, INSTR(F.NOME, ' ')- 1), F.NOME) || 'L' ||E.SIGLA, '?', 'E'), '?', 'A') AS LOGIN,
//         E.NOMEFANTASIA                                                                                               AS VALOREXIBIDO,
//         DECODE(A.IDATIVIDADE, 148, 9, 146, 122, 1, 31, 131, 9, 139, 4)                                               AS GRUPO,
//         E.IDESTABELECIMENTO                                                                                          AS IDESTABELECIMENTO,
//         F.IDFUNCIONARIO                                                                                              AS IDFUNCIONARIO
//  FROM   T_FUNCIONARIO_PROD        F,
//         T_ESTABELECIMENTO_PROD    E,
//         T_ATIVIDADE_PROD          A
//  WHERE  F.IDESTABELECIMENTO  = E.IDESTABELECIMENTO
//  AND    F.IDATIVIDADE        = A.IDATIVIDADE
//  AND    A.IDATIVIDADE   NOT IN (153, 12)
//  AND    F.CPF                = XNU_CPF;
//
//  CURSOR C_LOGIN IS
//  SELECT COUNT(*) AS CONT
//  FROM   T_USUARIO_PROD U
//  WHERE  U.LOGIN   = XNM_LOGIN;
//
//BEGIN
//
//  FOR C1 IN C_USUARIO LOOP
//
//    XNM_LOGIN    := C1.LOGIN;
//    XIS_VALIDO   := 1;
//    XNU_CONT     := 1;
//    XDS_MENSAGEM := '';
//
//    LOOP
//      FOR C2 IN C_LOGIN LOOP
//        XIS_VALIDO := C2.CONT;
//      END LOOP;
//    EXIT WHEN XIS_VALIDO = 0;
//      IF XIS_VALIDO > 0 THEN
//        XNM_LOGIN := NVL(SUBSTR(C1.NOME, 1, INSTR(C1.NOME, ' ')- 1), C1.NOME) || TO_CHAR(XNU_CONT) || 'L' || SUBSTR(C1.VALOREXIBIDO, 1, 3);
//        XNU_CONT  := XNU_CONT + 1;
//      END IF;
//    END LOOP;
//
//    SELECT S_USUARIO_PROD.NEXTVAL INTO XID_USUARIO FROM DUAL;
//
//    INSERT INTO T_USUARIO_PROD (IDUSUARIO,NOME,LOGIN,SENHA,STATUS,TENTATIVASLOGIN,SENHAEXPIRADA,CODIGOAB)
//      VALUES (XID_USUARIO, C1.NOME, XNM_LOGIN, 'C/D8zuzLWIg=', 'A', 0, 'N', 1);
//
//    INSERT INTO T_ATRIBUTOUSUARIO_PROD (IDATRIBUTOUSUARIO,VALOR,IDPERMISSAOUSUARIO,IDUSUARIO,VALOREXIBIDO,CODIGOAB)
//      VALUES (S_ATRIBUTOUSUARIO_PROD.NEXTVAL, C1.IDESTABELECIMENTO, 1, XID_USUARIO, C1.VALOREXIBIDO, 1);
//
//    INSERT INTO T_GRUPOUSUARIO_PROD (IDUSUARIO,IDGRUPO,CODIGOAB)
//      VALUES (XID_USUARIO, C1.GRUPO, 1);
//
//    UPDATE T_FUNCIONARIO_PROD F SET F.IDUSUARIO = XID_USUARIO WHERE F.IDFUNCIONARIO = C1.IDFUNCIONARIO;
//
//    XDS_USUARIO := '_'||C1.IDESTABELECIMENTO||'_'||C1.NOME||'_'||XNU_CPF||'_'||XNM_LOGIN||'_'||C1.VALOREXIBIDO||'_';
//
//  END LOOP;
//
//END;
///
		Connection conn_Oracle = null;
		try 
		{
            Class.forName("oracle.jdbc.OracleDriver");
            
            conn_Oracle = DriverManager.getConnection("jdbc:oracle:thin:@201.28.44.202:1521:OSCAR1", "mstore", "mstore_123_orcl");
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		PreparedStatement ps = null;
		
		String sql = "SELECT F.NOME                                                                                                       AS NOME,"
				+ "         F.CPF                                                                                                        AS CPF,"
				+ "         REPLACE(REPLACE(NVL(SUBSTR(F.NOME, 1, INSTR(F.NOME, ' ')- 1), F.NOME) || 'L' ||E.SIGLA, '?', 'E'), '?', 'A') AS LOGIN,"
				+ "         E.NOMEFANTASIA                                                                                               AS VALOREXIBIDO,"
				+ "         DECODE(A.IDATIVIDADE, 148, 9, 146, 122, 1, 31, 131, 9, 139, 4)                                               AS GRUPO,"
				+ "         E.IDESTABELECIMENTO                                                                                          AS IDESTABELECIMENTO,"
				+ "         F.IDFUNCIONARIO                                                                                              AS IDFUNCIONARIO,"
				+ "			(select count(*) from t_usuario where login like NVL(SUBSTR(F.NOME, 1, INSTR(F.NOME, ' ')- 1), F.NOME) || '%L' || E.SIGLA) as quantidade,"
				+ "			NVL(SUBSTR(F.NOME, 1, INSTR(F.NOME, ' ')- 1), F.NOME) AS primeironome,"
				+ "         E.SIGLA                                                                                               AS sigla"
				+ "  FROM   T_FUNCIONARIO        F,"
				+ "         T_ESTABELECIMENTO    E,"
				+ "         T_ATIVIDADE          A"
				+ "  WHERE  F.IDESTABELECIMENTO  = E.IDESTABELECIMENTO"
				+ "  AND    F.IDATIVIDADE        = A.IDATIVIDADE"
				+ "  AND    A.IDATIVIDADE   NOT IN (153, 12)"
				+ "  AND    F.CPF                = '"+cpf+"'" ;

		ps = conn_Oracle.prepareStatement(sql);	

		ResultSet rs = ps.executeQuery();
		int i = 0;
		HashMap hash = null;
		
		while(rs.next())
		{
			hash = new HashMap();
			hash.put("nome", rs.getString(1));
			hash.put("cpf", rs.getString(2));
			hash.put("login", rs.getString(3));
			hash.put("valorExibido", rs.getString(4));
			hash.put("grupo", rs.getInt(5));
			hash.put("idestabelecimento", rs.getInt(6));
			hash.put("idfuncionario", rs.getInt(7));
			hash.put("quantidade", rs.getInt(8));
			hash.put("primeiroNome", rs.getString(9));
			hash.put("sigla", rs.getString(10));
		}			
						
		ps.close();
		
//		verificando se já existe algum login igual ao criado dinamicamente
		
		if(hash != null)
		{
			int codigoGrupo = (Integer) hash.get("grupo");
			
			if(codigoGrupo == 0)
			{
				return "Usuario nao pôde ser cadastrado!";
			}
			
			int quantidade = (Integer) hash.get("quantidade");
			
			if(quantidade > 0)
			{
				quantidade++;
				
				hash.put("login",(String) hash.get("primeiroNome") + (quantidade) +  "L" + hash.get("sigla"));
			}
			
//			pegando a sequencia
			ps = conn_Oracle.prepareStatement("SELECT S_USUARIO.NEXTVAL FROM DUAL ");	

			rs = ps.executeQuery();
			int idUsuario = 0;
			
			if(rs.next())
			{
				idUsuario = rs.getInt(1);
			}
			ps.close();
			
//			mandando inserir no banco
			
			ps = conn_Oracle.prepareStatement("INSERT INTO T_USUARIO (IDUSUARIO,NOME,LOGIN,SENHA,STATUS,TENTATIVASLOGIN,SENHAEXPIRADA,CODIGOAB) VALUES ("+idUsuario +", '"+hash.get("nome")+"', '"+hash.get("login")+"', 'C/D8zuzLWIg=', 'A', 0, 'N', 1)");	
			ps.executeUpdate();
			ps = conn_Oracle.prepareStatement("INSERT INTO T_ATRIBUTOUSUARIO (IDATRIBUTOUSUARIO,VALOR,IDPERMISSAOUSUARIO,IDUSUARIO,VALOREXIBIDO,CODIGOAB) VALUES (S_ATRIBUTOUSUARIO.NEXTVAL, "+hash.get("idestabelecimento")+", 1, "+idUsuario+", '"+hash.get("valorExibido")+"', 1)");	
			ps.executeUpdate();
			ps = conn_Oracle.prepareStatement("INSERT INTO T_GRUPOUSUARIO (IDUSUARIO,IDGRUPO,CODIGOAB) VALUES ("+idUsuario+", "+hash.get("grupo")+", 1)");	
			ps.executeUpdate();
			ps = conn_Oracle.prepareStatement("INSERT INTO T_METAIDENTIFICADORPORTAL (IDMETAIDENTIFICADORPORTAL, IDUSUARIO,CODIGO,CODIGOAB) VALUES (S_METAIDENTIFICADORPORTAL.NEXTVAL, "+idUsuario+", '1234', 1)");	
			ps.executeUpdate();
			ps = conn_Oracle.prepareStatement("UPDATE T_FUNCIONARIO F SET F.IDUSUARIO = "+idUsuario + " WHERE F.IDFUNCIONARIO = " + hash.get("idfuncionario"));	
			ps.executeUpdate();

			String XDS_MENSAGEM = "Seguem os Dados do Funcionario:\r\n\n";
		    XDS_MENSAGEM += "Funcionario: " + hash.get("nome") + "\r\n";
		    XDS_MENSAGEM += "CPF: " + hash.get("cpf") + "\r\n";
		    XDS_MENSAGEM += "Usuario: " + hash.get("login") + "\r\n";
		    XDS_MENSAGEM += "Senha: 1234\r\n\n";
		    XDS_MENSAGEM += "ATENCAO:\r\n";
		    XDS_MENSAGEM += "ESTE EMAIL NAO DEVE SER RESPONDIDO!\r\n";
		    XDS_MENSAGEM += "EM CASO DE DUVIDAS, ENTRAR EM CONTATO COM centralti@oscarcalcados.com.br\r\n";
		    System.out.println(XDS_MENSAGEM);

		    ps = conn_Oracle.prepareStatement("select descricao from t_contatoestabelecimento where idestabelecimento = " + hash.get("idestabelecimento") + " and idtipocontato = 5");	

			rs = ps.executeQuery();

			ArrayList<String> emails = new ArrayList();
			emails.add("centralti@oscarcalcados.com.br");
			
			while(rs.next())
			{
				emails.add(rs.getString(1));
			}
			ps.close();
		    
			for(String email : emails)
			{
				EnviarEmail teste = new EnviarEmail();
				teste.setLogin("info.oscarcalcados@gmail.com");
				teste.setSenha("nelsonCazarini");

				try {
					teste.sendMail("info.oscarcalcados@gmail.com", email, "Cadastro de Usuario Realizado com Sucesso", XDS_MENSAGEM, null);
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			conn_Oracle.close();
			
			return "Usuario cadastrado com sucesso!\n\n"
					+ "Nome:" + hash.get("nome") + "\n" 
					+ "Login:" + hash.get("login") + "\n";
			
		}
		else
		{
			conn_Oracle.close();
			return null;
		}
	}

	public List<Object[]> listarUsuarioFuncionario(String nome, String login,
			String atividade) {
		
		String sql = "SELECT * "
				+ " FROM   VW_REL_USUARIOS_FUNCIONARIOS"
				+ " WHERE 1=1 ";
				
		if(ValidadorUniversal.check(login))
		{
			sql += " and NM_USUARIO like '" + login.toUpperCase() + "%'";  
		}
		if(ValidadorUniversal.check(nome))
		{
			sql += " and NM_FUNCIONARIO like '" + nome.toUpperCase() + "%'";  
		}
		if(ValidadorUniversal.check(atividade))
		{
			sql += " and DS_ATIVIDADE like '" + atividade.toUpperCase() + "%'";  
		}
		
		List<Object[]> lista = entityManager.createNativeQuery(sql).getResultList();
		
		return lista;
	}

	public List<Object[]> listarUsuarioGrupo(String nome, String login, String grupo) {
		
		String sql = "SELECT * "
				+ " FROM   vw_rel_usuarios_grupos"
				+ " WHERE 1=1 ";
		
		if(ValidadorUniversal.check(login))
		{
			sql += " and NM_LOGIN_UPPER like '" + login.toUpperCase() + "%'";  
		}
		if(ValidadorUniversal.check(nome))
		{
			sql += " and NM_usuario like '" + nome.toUpperCase() + "%'";  
		}
		if(ValidadorUniversal.check(grupo))
		{
			sql += " and nm_grupo like '" + grupo.toUpperCase() + "%'";  
		}
		
		List<Object[]> lista = entityManager.createNativeQuery(sql).getResultList();
		
		return lista;
	}
	
	public List<Object[]> listarUsuarioGrupoCards(String nome, String login, String grupo) {
		
		String sql = "SELECT * "
				+ " FROM   vw_rel_usuarios_grupos_cards"
				+ " WHERE 1=1 ";
		
		if(ValidadorUniversal.check(login))
		{
			sql += " and NM_LOGIN_UPPER like '" + login.toUpperCase() + "%'";  
		}
		if(ValidadorUniversal.check(nome))
		{
			sql += " and NM_usuario like '" + nome.toUpperCase() + "%'";  
		}
		if(ValidadorUniversal.check(grupo))
		{
			sql += " and nm_grupo like '" + grupo.toUpperCase() + "%'";  
		}
		
		List<Object[]> lista = entityManager.createNativeQuery(sql).getResultList();
		
		return lista;
	}

}