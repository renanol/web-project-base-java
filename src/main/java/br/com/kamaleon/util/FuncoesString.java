package br.com.kamaleon.util;

import java.io.CharArrayWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.StringTokenizer;


/**
 * Conjunto de funcoes comuns usadas na manipulação de strings
 *
 * @author hermano@kamaleon.com.br Rômulo
 * @version 1.0, 29/07/99
 */
public class FuncoesString 
{
	
	public static String removeAcentos(String str) {
 
		str = Normalizer.normalize(str, Normalizer.Form.NFD);
		str = str.replaceAll("[^\\p{ASCII}]", "");
		return str;
		
	}
    
    public static String separarCamposArrayListPorVirgulas(ArrayList list)
    {
        String str = "";
        Iterator it = list.iterator();
        while (it.hasNext())
        {
            str += it.next().toString();
            if (it.hasNext())
            {
                str += ",";
            }
        }
        
        return str;
    }
    
    public static String getString(String str) {
    	if (str == null) {
    		return "";
    	}
    	
    	return str;
    }   
    
    // retorna true se o caracter eh de palavra(letra, digito, hifen ou "*")
    public static boolean char_ok(char ch) 
    {
        boolean result;
        if (Character.isLetterOrDigit(ch)) 
        {
            result = true;
        }
        else if (ch == '-') 
        {
            result = true;
        }
        else if (ch == '*') 
        {
            result = true;
        }
        else 
        {
            result = false;
        }
        return result;
    }

   /**
     * @param str       String original
     * @param strOld    A String que deve ser substituida
     * @param strNew    A nova string
     * @return          Retorna a string original substituido strOld por strNew
     */
    public static String replace(String str,String strOld,String strNew)
    {
        int iAnt=0,iPos=0;
        StringBuffer  strChanged=new StringBuffer();
        iPos =  str.indexOf(strOld,iAnt);
        while(iPos!=-1)
        {
            strChanged.append(str.substring(iAnt,iPos)+strNew);
            iAnt=iPos+strOld.length();
            iPos =  str.indexOf(strOld,iAnt);
        }
        return strChanged.append(str.substring(iAnt,str.length())).toString();
    }

    public static boolean in(char[] array, char c){
    	for(int i=array.length-1;i > -1 ; --i)
        {
            if(array[i]==c) return true;
     	}
      	return false;
    }
    
    public static String tirarEspacosBranco(String str)
    {
        StringTokenizer strTok = new StringTokenizer(str, " ");
        String strSemEspacos = "";
        
        while (strTok.hasMoreTokens())
        {
            strSemEspacos += strTok.nextToken();
        }
        return strSemEspacos.trim();
    }
    
    public static String reduzirAdicionarPontosFim(String str, int tamanhoFinal)
    {
        if (str.length() < tamanhoFinal)
        {
            return str + "...";
        }
        else
        {
            str = str.substring(0, tamanhoFinal);
            return str + "...";
        }
    }
    
    
    public static void setPreparedStatementUpperCasePodeSerNull(PreparedStatement ps, int numPS, String s)
        throws SQLException
    {
        if (s != null)
        {
            ps.setString(numPS, s.toUpperCase());
        }
        else
        {
            ps.setNull(numPS, java.sql.Types.VARCHAR);
        }
        
    }
    
    /**
     *
     * Método que gera a pilha de erro da execução do Job
     *
     * @return Uma String com a pilha de erro
     *
     **/
    public static String getStackTraceAsString(Exception erro)
    {
        //GERANDO A PILHA
        CharArrayWriter pilha = new CharArrayWriter();
        PrintWriter printWriter = new PrintWriter(pilha);
        erro.printStackTrace(printWriter);
        
        String strPilha = pilha.toString();
        
        //FECHANDO O PRINT WRITER
        printWriter.close();
        pilha.close();
        
        return strPilha;
        
    }
    
    public static String fillStringRightOrTrunc(String s, String c, int tam)
    {
    	if(s == null)
    	{
    		s = "";
    	}
    
    	
        if (s.length() > tam)
        {
            s = truncIfNecessary(s, tam);
        }
        else
        {
            s = fillStringRight(s, c, tam);
        }

        return s;
    }
    
    public static String fillStringLeftOrTrunc(String s, String c, int tam)
    {
    	if(s == null)
    	{
    		s = "";
    	}
        if (s.length() > tam)
        {
            s = truncIfNecessary(s, tam);
        }
        else
        {
            s = fillStringLeft(s, c, tam);
        }

        return s;
    }
    
    public static String fillStringRight(String s, String c, int tam)
    {
    	if(s == null)
    	{
    		s = "";
    	}
        while (s.length() < tam)
        {
            s =  s + c;
        }
        
        return s;
    }
    
    public static String fillStringLeft(String s, String c, int tam)
    {
    	if(s == null)
    	{
    		s = "";
    	}
        while (s.length() < tam)
        {
            s = c + s;
        }
        
        return s;
    }
    
    public static String fillZerosRight(String s, int tam)
    {
    	if(s == null)
    	{
    		s = "";
    	}
        while (s.length() < tam)
        {
            s =  s + "0";
        }
        
        return s;
    }
    
    public static String fillEspacosRight(String s, int tam)
    {
    	if(s == null)
    	{
    		s = "";
    	}
    	while (s.length() < tam)
    	{
    		s =  s + " ";
    	}
    	
    	return s;
    }
    
    
    public static String fillZerosLeft(String s, int tam)
    {
    	if(s == null)
    	{
    		s = "";
    	}
        while (s.length() < tam)
        {
            s = "0" + s;
        }
        
        return s;
    }
    
    public static String fillEspacosLeft(String s, int tam)
    {
    	if(s == null)
    	{
    		s = "";
    	}
    	while (s.length() < tam)
    	{
    		s = " " + s;
    	}
    	
    	return s;
    }
    
    public static String truncIfNecessary(String s, int tam)
    {
    	if(s == null)
    	{
    		s = "";
    	}
        if (s.length() > tam)
        {
            s = s.substring(0, tam);
        }
        
        return s;
    }
    
    /**
     *  Return <code>true</code> if the specified object is a String or a <code>null</code>
     *  value.
     *
     * @param o Object to be tested
     * @return The string value
     */
    public static boolean isString(Object o) {
        return (o == null) ? true : String.class.isInstance(o);
    }
    
    public static String primeiraLetraMaiuscula(String s)
    {
        return s.substring(0, 1).toUpperCase()
            +  s.substring(1, s.length());
    }
    
    public static String primeiraLetraMinuscula(String s)
    {
        return s.substring(0, 1).toLowerCase()
            +  s.substring(1, s.length());
    }
    
    
    public static String getNomeClasseSemPacote(Object o)
    {
        return getNomeClasseSemPacote(o.getClass());
    }

    public static String getNomeClasseSemPacote(Class objClass)
    {
        String nomeClasse = objClass.getName();
        if (nomeClasse.lastIndexOf(".") >= 0)
        {
            nomeClasse = nomeClasse.substring(
                nomeClasse.lastIndexOf(".") + 1);
        }
        if (nomeClasse.lastIndexOf(";") >= 0)
        {
            nomeClasse = nomeClasse.substring(0,
                nomeClasse.lastIndexOf(";"));
        }
        
        return nomeClasse;
    }

    /**
     * Método responsável por retornar um data por extenso
     * no formato:
     *  TRINTA DIAS DO MÊS DE JANEIRO DO ANO DE DOIS MIL E QUATRO
     *
     * @param data A data que será escrita por extenso
     * @return Uma string contendo a data por extenso
     */
    public static String getDataPorExtenso(Date data)
    {
        String dataExtenso = "";
        
        Calendar calendar = FuncoesData.getCalendar(data);

        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        int mes = calendar.get(Calendar.MONTH);
        int ano = calendar.get(Calendar.YEAR);

        dataExtenso += getIntPorExtenso(dia) + " dia(s) ";
        dataExtenso += "do mês de " + getMesPorExtenso(mes) + " ";
        dataExtenso += "do ano de " + getIntPorExtenso(ano);

        return dataExtenso;
    }

    /**
     * Método responsável por retornar um mes passado como
     * parametro por extenso.
     *
     * @param mes O int da classe calendar conrrespondendo ao mes a ser
     *            retornado por extenso
     * @return Uma String contendo o mes por extenso
     */
    public static String getMesPorExtenso(int mes)
    {
        String mesString = "";

        switch(mes)
        { 
            case Calendar.JANUARY :
                mesString = "janeiro";
                break; 
            case Calendar.FEBRUARY : 
                mesString = "fevereiro"; 
                break; 
            case Calendar.MARCH : 
                mesString = "março"; 
                break; 
            case Calendar.APRIL : 
                mesString = "abril"; 
                break; 
            case Calendar.MAY : 
                mesString = "maio"; 
                break; 
            case Calendar.JUNE : 
                mesString = "junho"; 
                break; 
            case Calendar.JULY :
                mesString = "julho";
                break; 
            case Calendar.AUGUST :
                mesString = "agosto"; 
                break; 
            case Calendar.SEPTEMBER : 
                mesString = "setembro"; 
                break; 
            case Calendar.OCTOBER : 
                mesString = "outubro"; 
                break; 
            case Calendar.NOVEMBER : 
                mesString = "novembro"; 
                break; 
            case Calendar.DECEMBER : 
                mesString = "dezembro"; 
                break;
        } 

        return mesString;
    }

    /**
     * Método responsável por retornar um float passado como
     * parametro por extenso.
     *
     * @param numero O float a ser escrito por extenso
     * @return Uma String contendo o float por extenso
     */
    public static String getDoublePorExtenso(double numero)
    { 
        String numeroExtenso = "";
        int parteInteiraNumero = (int) numero;
        int parteDecimalNumero;
        
        //////////////////////////////
        // Escrever a parte inteira //
        //////////////////////////////
        if (parteInteiraNumero > 0)
        {
            String numeroStr = numero+"";
            numeroStr = numeroStr.substring(
                    numeroStr.indexOf('.')+1, numeroStr.length());
            
            if (numeroStr.length() >= 2)
            {
            	numeroStr = numeroStr.substring(0 , 2);
            }
            else
            {
            	numeroStr = numeroStr.substring(0, 1) + "0";
            }

            Integer integer = new Integer(numeroStr);
            parteDecimalNumero = integer.intValue();
            
            if (parteDecimalNumero == 1)
            {
                numeroExtenso += getIntPorExtenso(parteInteiraNumero) + " real";
            }
            else
            {
                numeroExtenso += getIntPorExtenso(parteInteiraNumero) + " reais";
            }
        }
        else
        {
            parteDecimalNumero = (int) (numero * 100); // parte decimal com 2 casa depois da virgula
        }
        
        //////////////////////////////
        // Escrever a parte decimal //
        //////////////////////////////
        if (parteDecimalNumero > 0)
        {
            if (!numeroExtenso.equals(""))
            {
                numeroExtenso += " e ";
            }
            if (parteDecimalNumero == 1)
            {
                numeroExtenso += getIntPorExtenso(parteDecimalNumero) + " centavo";
            }
            else
            {
                numeroExtenso += getIntPorExtenso(parteDecimalNumero) + " centavos";
            }
        }
        return numeroExtenso;
    }

    /**
     * Método responsável por retornar um int passado como
     * parametro por extenso.
     *
     * @param numero O int a ser escrito por extenso
     * @return Uma String contendo o float por extenso
     */
    public static String getIntPorExtenso(int numero)
    {
        String numeroExtenso = "";
        if (numero > 1000)
        {
            int milhares = numero / 1000;
            int resto = numero % 1000;
            
            numeroExtenso = getNumeroPorExtenso(milhares) + " mil e ";
            numeroExtenso += getNumeroPorExtenso(resto);
        }
        else
        {
            numeroExtenso = getNumeroPorExtenso(numero);
        }
        return numeroExtenso;
    }

    /**
     * Método responsável por retornar um numero passado como
     * parametro por extenso. No momento o método só retorna
     * numeros até 999.999.
     *
     * @param numero O numero a ser escrito por extenso
     * @return Uma String contendo o float por extenso
     */
    public static String getNumeroPorExtenso(int n)
    {
        String numeroExtenso = "";
        int resto1,resto2,div1,div2;
        String centena="",dezena="",unidade=""; 
        
        if (n<=1000 && n!=-1)
        { 
            resto1 = n%100; 
            div1 = n/100; 
            resto2 = resto1%10; 
            div2 = resto1/10; 

            switch(div1)
            { 
                case 1 : centena = "cento"; break; 
                case 2 : centena = "duzentos"; break; 
                case 3 : centena = "trezentos"; break; 
                case 4 : centena = "quatrocentos"; break; 
                case 5 : centena = "quinhentos"; break; 
                case 6 : centena = "seiscentos"; break; 
                case 7 : centena = "setecentos"; break; 
                case 8 : centena = "oitocentos"; break; 
                case 9 : centena = "novecentos"; break; 
            } 

            //verifica os numeros com a terminacao 11,12,13,... 
            //(ex.215,14,911) 
            if(resto1 !=0 && resto1>10 && resto1<20) 
            { 
                switch(resto1)
                { 
                    case 11: dezena = "onze"; break; 
                    case 12: dezena = "doze"; break; 
                    case 13: dezena = "treze"; break; 
                    case 14: dezena = "quatorze"; break; 
                    case 15: dezena = "quinze"; break; 
                    case 16: dezena = "dezesseis"; break; 
                    case 17: dezena = "dezessete"; break; 
                    case 18: dezena = "dezoito"; break; 
                    case 19: dezena = "dezenove"; break; 
                } 
            } 
            else
            { 
                //caso normal de um numero(ex. 40,290,241) 
                switch(div2)
                { 
                    case 1 : dezena = "dez"; break; 
                    case 2 : dezena = "vinte"; break; 
                    case 3 : dezena = "trinta"; break; 
                    case 4 : dezena = "quarenta"; break; 
                    case 5 : dezena = "cinquenta"; break; 
                    case 6 : dezena = "sessenta"; break; 
                    case 7 : dezena = "setenta"; break; 
                    case 8 : dezena = "oitenta"; break; 
                    case 9 : dezena = "noventa"; break; 
                } 
            } 

            //verifica todos os casos da unidade(ex. 201,356,5) 
            if(resto1>=20 || n<10 || resto1<10)
            { 
                switch(resto2)
                { 
                    case 1 : unidade = "um"; break; 
                    case 2 : unidade = "dois"; break; 
                    case 3 : unidade = "três"; break; 
                    case 4 : unidade = "quatro"; break; 
                    case 5 : unidade = "cinco"; break; 
                    case 6 : unidade = "seis"; break; 
                    case 7 : unidade = "sete"; break; 
                    case 8 : unidade = "oito"; break; 
                    case 9 : unidade = "nove"; break; 
                } 
            } 

            //daqui pra baixo eh q tah a brincadeira... 
            if(n==0) 
            numeroExtenso += "zero"; //imprime "zero" 
            else
            { 
                if(n==1000) 
                numeroExtenso +="mil"; //imprime "mil" 
                else
                { 
                    if(n==100) 
                    numeroExtenso +="cem"; //imprime "cem" 
                    else
                    { 
                        if(n>100 && resto1<10 && resto1!=0) 
                        //imprime alguns numeros(ex. 101,209) 
                        numeroExtenso += centena + " e " + unidade; 
                        else
                        { 
                            if(n>100 && resto1 ==0) 
                            //imprime numeros inteiros(ex. 100,200,...) 
                            numeroExtenso += centena; 
                            else 
                            { 
                                if(n>100 && resto1 !=0 && resto2 !=0 && resto1>=20) 
                                //imprime numeros na maioria dos casos(ex. 256,999,721) 
                                numeroExtenso += centena + " e " + dezena + " e " + unidade; 
                                else
                                { 
                                    if(n>100 && resto1 !=0 && resto2 ==0) 
                                    //imprime numeros inteiros(ex. 210,550,930) 
                                    numeroExtenso += centena + " e " + dezena; 
                                    else
                                    { 
                                        if(n>100 && resto1 !=0) 
                                        //imprime alguns casos(ex. 211,212,...219,915) 
                                        numeroExtenso += centena + " e "+ dezena; 
                                        else
                                        { 
                                            if(n<100 && resto1 !=0 && resto2 ==0) 
                                            //imprime so a dezena de alguns casos 
                                            //(ex. 11,12,13,14,...19) 
                                            numeroExtenso += dezena; 
                                            else
                                            { 
                                                if(n>20 && resto1 !=0 && resto2 !=0) 
                                                //imprime alguns casos(ex. 41,39,99) 
                                                numeroExtenso += dezena + " e " + unidade; 
                                                else
                                                { 
                                                    if(n<10) 
                                                    //imprime a unidade(1,2,3,...9) 
                                                    numeroExtenso += unidade; 
                                                    else 
                                                    //imprime a dezena(10,20,30...90) 
                                                    numeroExtenso += dezena; 
                                                } 
                                            } 
                                        } 
                                    } 
                                } 
                            } 
                        } 
                    } 
                } 
            } 
        }
        return numeroExtenso;
    }

    /**
     * Remove o caracter do String
     * @param s o String inicial
     * @param c o caracter a ser removido
     * @return o String sem o caracter
     */
    public static String removeChar(String s, char c)
    {
    	String r = "";
    	
    	for (int i = 0; i < s.length(); i ++)
    	{
    		if (s.charAt(i) != c)
    		{
    			r += s.charAt(i);
    		}
    	}
    	
    	return r;
    }
    
    /**
     * Remove a ocorrência do caracter do String, se o parâmetro apenasPrimeiraOcorrencia for
     * <code>true</code> será removido apenas a primeira ocorrência do caracter no String.
     * @param s o String a ser manipulado
     * @param c o caracter a ser removido
     * @param apenasPrimeiraOcorrencia <code>true</code> para remover apenas a primeira ocorrência
     * @return o String manipulado
     */
    public static String removeChar(String s, char c, boolean apenasPrimeiraOcorrencia)
    {
    	String r = "";
    	boolean jaOcorreu = false;
    	
    	for (int i = 0; i < s.length(); i ++)
    	{
    		if (s.charAt(i) != c)
    		{
    			r += s.charAt(i);
    		}
    		else
    		{    			    		
    			if (apenasPrimeiraOcorrencia)
    			{
    				if (!jaOcorreu)
    				{    					    				
    					jaOcorreu = true;
    				}
    				else
    				{
    					r += s.charAt(i);
    				}
    			}    			    			
    		}
    	}
    	
    	return r;
    }
    
    public static String[] quebrarStringEmVariasMesmoTamanhoOuStringVazia(String s, int numeroStrings, int tam)
    {
        String[] arrayString = quebrarStringEmVariasMesmoTamanho(s, numeroStrings, tam);
        
        for (int i = 0; i < arrayString.length; i++)
        {
            if (arrayString[i].trim().length() == 0)
            {
                arrayString[i] = "";
            }
        }
        
        
        return arrayString;
    }
    
    public static String[] quebrarStringEmVariasMesmoTamanho(String s, int numeroStrings, int tam)
    {
        String[] arrayRetorno = new String[numeroStrings];
        
        if (s == null)
        {
            s = "";
        }
        
        String strAux = "";
        for (int i = 0; i < numeroStrings; i++)
        {
            if (s.length() < (i * tam))
            {
                strAux = "";
            }
            else
            {
                if (s.length() < (i*tam) + tam)
                {
                    strAux = s.substring((i*tam));
                }
                else
                {
                    strAux = s.substring((i*tam), (i*tam) + tam);
                }
            }
            
            arrayRetorno[i] = fillStringRightOrTrunc(strAux, " ", tam);
        }
        
        return arrayRetorno;
    }    
    
    
    public static String tirarCaracteresEspeciaisEAcentos(String str)
    {
        String strSemAcentos = "";

        if (str == null)
        {
            return strSemAcentos;
        }

        for (int i = 0; i < str.length(); i++)
        {
            char caracter = str.charAt(i);
            if ((caracter >= 'à') && (caracter <= 'ä'))
            {
                strSemAcentos += "a";
            }
            else if ((caracter >= 'À') && (caracter <= 'Ä'))
            {
                strSemAcentos += "A";
            }
            else if ((caracter >= 'è') && (caracter <= 'ë'))
            {
                strSemAcentos += "e";
            }
            else if ((caracter >= 'È') && (caracter <= 'Ë'))
            {
                strSemAcentos += "E";
            }
            else if ((caracter >= 'ì') && (caracter <= 'ï'))
            {
                strSemAcentos += "i";
            }
            else if ((caracter >= 'Ì') && (caracter <= 'Ï'))
            {
                strSemAcentos += "I";
            }
            else if ((caracter >= 'ò') && (caracter <= 'ö'))
            {
                strSemAcentos += "o";
            }
            else if ((caracter >= 'Ò') && (caracter <= 'Ö'))
            {
                strSemAcentos += "O";
            }
            else if ((caracter >= 'ù') && (caracter <= 'ü'))
            {
                strSemAcentos += "u";
            }
            else if ((caracter >= 'Ù') && (caracter <= 'Ü'))
            {
                strSemAcentos += "U";
            }
//            +-*/!@#$%&(){}[]?/\|
            else if ((caracter == 'º') || (caracter == 'ª'))
            {
                strSemAcentos += ".";
            }
            else if ((caracter == '+') || (caracter == '-') || (caracter == '*')  || (caracter == '!') || 
            		(caracter == '@')|| (caracter == '#') || (caracter == '¢')|| (caracter == '¨') || 
            		(caracter == '§')|| (caracter == '\\')|| (caracter == '|')|| (caracter == '{')|| (caracter == '}') || 
            		(caracter == '&')|| (caracter == '(')|| (caracter == ')')|| (caracter == '_')|| (caracter == '=')|| (caracter == '\''))
            {
            	strSemAcentos += "";
            }
            else if (caracter == 'ç')
            {
                strSemAcentos += "c";               
            }
            else if (caracter == 'Ç') 
            {
            	strSemAcentos += "C";
			}
            else
            {
                strSemAcentos += caracter;
            }
        }

        return strSemAcentos.trim();
    }

    public static String tirarCaracteresEspeciais(String str)
    {
    	String strSemAcentos = "";
    	
    	if (str == null)
    	{
    		return strSemAcentos;
    	}
    	
    	for (int i = 0; i < str.length(); i++)
    	{
    		char caracter = str.charAt(i);
    		if ((caracter >= 'à') && (caracter <= 'ä'))
    		{
    			strSemAcentos += "a";
    		}
    		else if ((caracter >= 'À') && (caracter <= 'Ä'))
    		{
    			strSemAcentos += "A";
    		}
    		else if ((caracter >= 'è') && (caracter <= 'ë'))
    		{
    			strSemAcentos += "e";
    		}
    		else if ((caracter >= 'È') && (caracter <= 'Ë'))
    		{
    			strSemAcentos += "E";
    		}
    		else if ((caracter >= 'ì') && (caracter <= 'ï'))
    		{
    			strSemAcentos += "i";
    		}
    		else if ((caracter >= 'Ì') && (caracter <= 'Ï'))
    		{
    			strSemAcentos += "I";
    		}
    		else if ((caracter >= 'ò') && (caracter <= 'ö'))
    		{
    			strSemAcentos += "o";
    		}
    		else if ((caracter >= 'Ò') && (caracter <= 'Ö'))
    		{
    			strSemAcentos += "O";
    		}
    		else if ((caracter >= 'ù') && (caracter <= 'ü'))
    		{
    			strSemAcentos += "u";
    		}
    		else if ((caracter >= 'Ù') && (caracter <= 'Ü'))
    		{
    			strSemAcentos += "U";
    		}
    		else if ((caracter == 'º') || (caracter == 'ª'))
    		{
    			strSemAcentos += ".";
    		}
    		else if (caracter == 'ç')
    		{
    			strSemAcentos += "c";               
    		}
    		else if (caracter == 'Ç') 
    		{
    			strSemAcentos += "C";
    		}
    		else if (caracter == '/') 
    		{
    			strSemAcentos += "";
    		}
    		else
    		{
    			strSemAcentos += caracter;
    		}
    	}
    	
    	return strSemAcentos.trim();
    }
    
    public static String tirarAcentos(String str)
    {
    	String strSemAcentos = "";
    	
    	if (str == null)
    	{
    		return strSemAcentos;
    	}
    	
    	for (int i = 0; i < str.length(); i++)
    	{
    		char caracter = str.charAt(i);
    		if ((caracter >= 'à') && (caracter <= 'ä'))
    		{
    			strSemAcentos += "a";
    		}
    		else if ((caracter >= 'À') && (caracter <= 'Ä'))
    		{
    			strSemAcentos += "A";
    		}
    		else if ((caracter >= 'è') && (caracter <= 'ë'))
    		{
    			strSemAcentos += "e";
    		}
    		else if ((caracter >= 'È') && (caracter <= 'Ë'))
    		{
    			strSemAcentos += "E";
    		}
    		else if ((caracter >= 'ì') && (caracter <= 'ï'))
    		{
    			strSemAcentos += "i";
    		}
    		else if ((caracter >= 'Ì') && (caracter <= 'Ï'))
    		{
    			strSemAcentos += "I";
    		}
    		else if ((caracter >= 'ò') && (caracter <= 'ö'))
    		{
    			strSemAcentos += "o";
    		}
    		else if ((caracter >= 'Ò') && (caracter <= 'Ö'))
    		{
    			strSemAcentos += "O";
    		}
    		else if ((caracter >= 'ù') && (caracter <= 'ü'))
    		{
    			strSemAcentos += "u";
    		}
    		else if ((caracter >= 'Ù') && (caracter <= 'Ü'))
    		{
    			strSemAcentos += "U";
    		}
    		else if (caracter == 'ç')
    		{
    			strSemAcentos += "c";               
    		}
    		else if (caracter == 'Ç') 
    		{
    			strSemAcentos += "C";
    		}
    		else
    		{
    			strSemAcentos += caracter;
    		}
    	}
    	
    	return strSemAcentos.trim();
    }
    
    public static String tirarMascaraCpfCnpj(String str)
    {
    	String strSemAcentos = "";
    	
    	if (str == null)
    	{
    		return strSemAcentos;
    	}
    	
    	for (int i = 0; i < str.length(); i++)
    	{
    		char caracter = str.charAt(i);
    		if ((caracter == '.') || (caracter == '/') || (caracter == '-') )
    		{
    			strSemAcentos += "";
    		}
    		else
    		{
    			strSemAcentos += caracter;
    		}
    	}
    	
    	return strSemAcentos.trim();
    }
    
    public static String centralizarString(String string, int tamanhoStringCentralizada)
    {
    	String stringCentralizada = "";
    	
    	if (string == null || string.length() == 0)
    		return string;
    	
    	if (string.length() > tamanhoStringCentralizada)
    		truncIfNecessary(string, tamanhoStringCentralizada);
    	
    	int espacosEmBranco = (tamanhoStringCentralizada - string.length()) / 2;
    	
    	for (int k = 0; k < espacosEmBranco; k++)
    		stringCentralizada += " ";
    	
    	stringCentralizada += string;
    	
    	for (int k = 0; k < espacosEmBranco; k++)
    		stringCentralizada += " ";
    	
    	return stringCentralizada;
    }
    
    public static String getCodigoBarraCheque(String str)
    {
    	String codigo = "";
    	
    	for (int i = 0; i < str.length(); i++)
    	{
    		char caracter = str.charAt(i);
    		
    		try
    		{
    			int letra = Integer.parseInt(caracter+"");
    			codigo += caracter;
    		}
    		catch(Exception e)
    		{
    			
    		}
    	}
    	return codigo;
    }
    
    public static String toUTF8(String s) throws UnsupportedEncodingException  
    {  
      byte[] utf8 = s.getBytes("UTF-8");  
      return new String(utf8);  
    }
    
    
    public static String inverterString(String s) 
    {  
    	String a = "";
    	
    	for(int i = s.length()-1; i >= 0; i--)
    	{
    		a += s.charAt(i);
    	}
    	  
    	return a;  
    }
    
    /** Metodo estatico que retorna a notacao do cpf: 000.000.000-00 */
    public static String getCpf(String numero) 
    {
        return numero.substring(0,3) + "." + numero.substring(3,6) + "." +
               numero.substring(6,9) + "-" + numero.substring(9,11);
    }
    
    /** Metodo estatico que retorna a notacao do cpf: 000.000.000-00 */
    public static String getCnpj(String numero) 
    {
    	return numero.substring(0,2) + "." + numero.substring(2,5) + "." +
                numero.substring(5,8) + "/" + numero.substring(8,12) + "-" + numero.substring(12,14);
    }
    
    public static String getExtensaoArquivo(String nomeArquivo){
    	    	
         String ext[] = nomeArquivo.split("\\.");   
         int i = ext.length;  
   
         if(i > 1) { 
           return ext[i-1];
         }else{
           return null;
         }
    	
    }
    
    public static String gerarHash(String codigo){ 	

    	MessageDigest md = null;

    	try {

    		md = MessageDigest.getInstance("SHA-256");
    		md.update(codigo.getBytes());

    	} catch (NoSuchAlgorithmException e) {

    		System.out.println(e.getMessage());
    		
    	}

    	byte[] bytes =  md.digest(); 		  	

    	StringBuilder s = new StringBuilder();

    	for (int i = 0; i < bytes.length; i++) {

    		int parteAlta = ((bytes[i] >> 4) & 0xf) << 4;
    		int parteBaixa = bytes[i] & 0xf;
    		if (parteAlta == 0){ 
    			s.append('0'); 
    		}
    		s.append(Integer.toHexString(parteAlta | parteBaixa));

    	}

    	return s.toString();

    }

    
}