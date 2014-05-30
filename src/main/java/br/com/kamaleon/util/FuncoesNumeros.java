package br.com.kamaleon.util;

import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;

public class FuncoesNumeros 
{
	
	private static Locale locale;
	
	static {
			locale = Locale.ITALY;
		}
	
	public static NumberFormat nf0digitosDecimais = NumberFormat.getNumberInstance(locale);

	public static NumberFormat nf2digitosDecimais = NumberFormat.getNumberInstance(locale);

	public static NumberFormat nf3digitosDecimais = NumberFormat.getNumberInstance(locale);

	public static NumberFormat nf4digitosDecimais = NumberFormat.getNumberInstance(locale);

	public static NumberFormat nfNdigitosDecimais = NumberFormat.getNumberInstance(locale);

	public static double TOLERANCIA = 0.001;

	static
	{
		nf0digitosDecimais.setMaximumFractionDigits(0);
		nf0digitosDecimais.setMinimumFractionDigits(0);

		nf2digitosDecimais.setMaximumFractionDigits(2);
		nf2digitosDecimais.setMinimumFractionDigits(2);

		nf3digitosDecimais.setMaximumFractionDigits(3);
		nf3digitosDecimais.setMinimumFractionDigits(3);

		nf4digitosDecimais.setMaximumFractionDigits(4);
		nf4digitosDecimais.setMinimumFractionDigits(4);

		nfNdigitosDecimais.setMaximumFractionDigits(30);
		nfNdigitosDecimais.setMinimumFractionDigits(0);

	}


	public static String formatarInt(int i)
	{
		return nf0digitosDecimais.format(i);
	}


	public static String formatar2DigitosDecimais(double i)
	{
		return nf2digitosDecimais.format(i);
	}

	public static String formatar2DigitosDecimais(BigDecimal i)
	{
		return nf2digitosDecimais.format(i);
	}

	public static String formatar2DigitosDecimais(float i)
	{
		return nf2digitosDecimais.format(i);
	}

	public static String formatar3DigitosDecimais(double i)
	{
		return nf3digitosDecimais.format(i);
	}

	public static String formatar3DigitosDecimais(float i)
	{
		return nf3digitosDecimais.format(i);
	}

	public static String formatar4DigitosDecimais(double i)
	{
		return nf4digitosDecimais.format(i);
	}

	public static String formatar4DigitosDecimais(float i)
	{
		return nf4digitosDecimais.format(i);
	}

	public static double getNumero2DigitosDecimais(String i)
	throws ParseException
	{
		if(!ValidadorUniversal.check(i))
		{
			return 0d;
		}

		return round(nf2digitosDecimais.parse(i).doubleValue(),2);
	}

	public static double getNumero2DigitosDecimaisComPonto(String i)
	throws ParseException
	{
		if(!ValidadorUniversal.check(i))
		{
			return 0;
		}

		if (locale == Locale.ITALY) {
			i = i.replace(".", ",");
		}
		
		return nf2digitosDecimais.parse(i).doubleValue();
	}

	public static double getNumero3DigitosDecimais(String i)
	throws ParseException
	{
		if(!ValidadorUniversal.check(i))
		{
			return 0d;
		}
		
		return nf3digitosDecimais.parse(i).doubleValue();
	}

	public static double getNumero4DigitosDecimais(String i)
	throws ParseException
	{
		if(!ValidadorUniversal.check(i))
		{
			return 0d;
		}
		
		return nf4digitosDecimais.parse(i).doubleValue();
	}

	public static String fillZerosLeft(int num, int tamanho)
	{
		String retorno = "" + num;

		while (retorno.length() < tamanho)
		{
			retorno = "0" + retorno;
		}

		return retorno;
	}
	public static String fillZerosLeft(long num, int tamanho)
	{
		String retorno = "" + num;

		while (retorno.length() < tamanho)
		{
			retorno = "0" + retorno;
		}

		return retorno;
	}

	public static int lerInteiro(byte[] buffer, int posicaoInicial)
	{
		int resultado = buffer[posicaoInicial];
		resultado += buffer[posicaoInicial+1] * 128;
		resultado += buffer[posicaoInicial+2] * 128 * 128;
		resultado += buffer[posicaoInicial+3] * 128 * 128 * 128;
		resultado += buffer[posicaoInicial+4] * 128 * 128 * 128 * 16;
		return resultado;
	}

	public static void gravarInteiro(byte[] buffer, int posicaoInicial, int valor)
	{
		int inteiro  = valor % 128;
		buffer[posicaoInicial] = (byte) inteiro;
		inteiro = ((valor / 128) % 128);
		buffer[posicaoInicial+1] = (byte) inteiro;
		inteiro =  ((valor / (128 * 128)) % 128);
		buffer[posicaoInicial+2] = (byte) inteiro;
		inteiro = ((valor / (128 * 128 * 128)) % 128);
		buffer[posicaoInicial+3] = (byte) inteiro;
		inteiro = ((valor / (128 * 128 * 128 * 16)) % 128);
		buffer[posicaoInicial+4] = (byte) inteiro;
	}


	public static final int RESULTADO_COMP_2_DIGITOS_PRIMEIRO_MAIOR = -1;
	public static final int RESULTADO_COMP_2_DIGITOS_IGUAIS = 0;
	public static final int RESULTADO_COMP_2_DIGITOS_SEGUNDO_MAIOR = 1;

	/** Compara dois numeros float levando em consideracao apenas
	 * dois digitos decimais.
	 * @return Retorna uma das constantes da classe:
	 *   RESULTADO_COMP_2_DIGITOS_PRIMEIRO_MAIOR
	 *   RESULTADO_COMP_2_DIGITOS_IGUAIS
	 *   RESULTADO_COMP_2_DIGITOS_SEGUNDO_MAIOR
	 */    
	public static int comparar2DigitosDecimais(float n1, float n2)
	{
		int resultado = 0;

		n1 = ConversorTipos.getFloatPrecisaoDoisDigitos(n1);
		n2 = ConversorTipos.getFloatPrecisaoDoisDigitos(n2);

		if (n1 > n2)
		{
			resultado = RESULTADO_COMP_2_DIGITOS_PRIMEIRO_MAIOR;
		}
		else if (n1 == n2)
		{
			resultado = RESULTADO_COMP_2_DIGITOS_IGUAIS;
		}
		else if (n1 < n2)
		{
			resultado = RESULTADO_COMP_2_DIGITOS_SEGUNDO_MAIOR;
		}

		return resultado;

	}

	//Retorna o inteiro imediatamente apos um real
	//se o parametro ja eh inteiro, o retorno eh o proprio 
	//parametro
	public static int arredondaFloatParaCima(double valor)
	{
		BigDecimal bd = new BigDecimal(valor);

		bd = bd.setScale(0,BigDecimal.ROUND_UP);

		return bd.intValue();   
	}


	public static String formatarSemPonto(double i, int casasDecimais)
	{
		String s = "";
		
		if(casasDecimais == 2)
		{
			s = formatar2DigitosDecimais(i);
		}
		if(casasDecimais == 3)
		{
			s = formatar3DigitosDecimais(i);
		}
		if(casasDecimais == 4)
		{
			s = formatar4DigitosDecimais(i);
		}
		return FuncoesString.replace(s, ".", "");
	}
	
	public static String formatar2DigitosDecimaisSemPontosSeparacaoMilhares(double i)
	{
		String s = formatar2DigitosDecimais(i);
		s = FuncoesString.replace(s, ",", "");
		return FuncoesString.replace(s, ".", "");
	}

	public static String formatar3DigitosDecimaisSemPontosSeparacaoMilhares(double i)
	{
		String s = formatar3DigitosDecimais(i);
		s = FuncoesString.replace(s, ",", "");
		return FuncoesString.replace(s, ".", "");
	}

	public static String formatar2DigitosDecimaisSemPonto(double i)
	{
		String s = formatar2DigitosDecimais(i);
		return FuncoesString.replace(s, ".", "");
	}

	public static String formatar3DigitosDecimaisSemPonto(double i)
	{
		String s = formatar3DigitosDecimais(i);
		return FuncoesString.replace(s, ".", "");
	}

	/**
	 * Arredonda um numero de ponto flutuante para determinado numero
	 * de casas decimais.
	 *
	 * @param val Valor a ser arredondado.
	 * @param places Numero de casas decimais.
	 * @return val Valor arredondado.
	 */
	public static double round(double val, int places) {
		long factor = (long)Math.pow(10,places);

		// Shift the decimal the correct number of places
		// to the right.
		val = val * factor;

		// Round to the nearest integer.
		long tmp = Math.round(val);

		// Shift the decimal the correct number of places
		// back to the left.
		return (double)tmp / factor;
	}

	/**
	 * Round a float value to a specified number of decimal 
	 * places.
	 *
	 * @param val the value to be rounded.
	 * @param places the number of decimal places to round to.
	 * @return val rounded to places decimal places.
	 */
	public static float round(float val, int places) {
		return (float)round((double)val, places);
	}

	/** 
	 * Valida se um String pode ser transformado em um float.
	 * 
	 * @param String vaalor 
	 * @return Boolean
	 */
	public static boolean isFloatValido(String valor)
	{
		
		if (valor.indexOf(",") < 0)
			valor += ",00";
		
		Pattern modelo = Pattern.compile("\\d+,\\d+");

		boolean resultado = false;

		Matcher token = modelo.matcher(valor);
		resultado = token.matches();

		return resultado;		

	}

	/**
	 * Cria as parcelas referente a um valor total de acordo com os parametros passados
	 * e coloca o resto da divisao inteira das parcelas na primeira parcela ou
	 * na ultima 
	 * @throws ParseException
	 */
	private static double[] transferirCentavos(double total, int numeroParcelas, boolean transferirParaPrimeiraParcela) 
	throws ParseException
	{	    	   	    
		double[] parcelas = new double[numeroParcelas];

		/** PEGA O VALOR INTEIRO DAS PARCELAS  **/
		int valorParcela = (int) (total / numeroParcelas);	    

		/** ATRIBUI O VALOR DAS PARCELAS NO ARRAY, **/
		/** CALCULA O VALOR TOTAL DAS PARCELAS     **/
		float totalSemCentavos = 0;	    
		for (int i = 0; i < numeroParcelas; i++)
		{
			parcelas[i] = valorParcela;
			totalSemCentavos += parcelas[i];
		}

		double valorRestante = getNumero2DigitosDecimais(formatar2DigitosDecimais(total - totalSemCentavos));
		if (transferirParaPrimeiraParcela)
		{
			// tranfere o valor restante para a primeira parcela                  
			parcelas[0] = parcelas[0] + valorRestante;
		}
		else
		{
			// tranfere o valor restante para a ultima parcela
			parcelas[numeroParcelas - 1] = parcelas[numeroParcelas - 1] + valorRestante;
		}

		return parcelas;
	}

	public static double[] transferirCentavosParaPrimeiraParcela(double total, int numeroParcelas) 
	throws ParseException
	{	    	   	    
		return transferirCentavos(total, numeroParcelas, true);
	}

	public static double[] transferirCentavosParaUltimaParcela(float total, int numeroParcelas) 
	throws ParseException
	{	    	   	    
		return transferirCentavos(total, numeroParcelas, false);
	}

	public static float[] dividirEmParcelasEquilibradasComPerdaNaUltima(float total, 
			int numeroParcelas) 
	{
		return dividirEmParcelasEquilibradas(total, numeroParcelas, true);
	}

	public static float[] dividirEmParcelasEquilibradasComPerdaNaPrimeira(float total, 
			int numeroParcelas) 
	{
		return dividirEmParcelasEquilibradas(total, numeroParcelas, false);
	}

	private static float[] dividirEmParcelasEquilibradas(float total, 
			int numeroParcelas, boolean jogarPerdaNaUltimaParcela) 
	{
		float parcela = round((total / numeroParcelas), 2);
		float totalParcelas = parcela * numeroParcelas;
		float resto = total - totalParcelas; 				

		float parcelas[] = new float[numeroParcelas];

		for (int i = 0; i < parcelas.length; i++)
		{
			if (!jogarPerdaNaUltimaParcela && (i == 0))
			{
				parcelas[i] = parcela + resto;
			}
			else if (jogarPerdaNaUltimaParcela && (i == (parcelas.length - 1)))
			{
				parcelas[i] = (parcela + resto);
			}
			else
			{
				parcelas[i] = parcela;
			}
		}

		return parcelas;
	}

	/**
	 * Calcula a porcentagem de um determinado valor em relacao a um valor total.
	 * Ex.: O usuario deseja saber qual a porcentagem de 2100.0 em relacao a 7000.0
	 * entao: getPorcentagem(7000.0, 2100.0) retornara 30.0 %;
	 * @author Thiago Henrique Barbosa Felisberto
	 * @param valorTotal o valor total
	 * @param valorParcial o valor do qual se quer saber a porcentagem em relacao
	 * ao valor total
	 * @return o valor da porcentagem
	 */
	public static double getPorcentagem(double valorTotal, double valorParcial)
	{
		return (valorParcial * 100) / valorTotal;
	}

	/**
	 * Calcula o valor de acordo com uma porcentagem passada com relacao a um
	 * valor total.
	 * Ex.: O usuario deseja saber o resultado de 60% de 250.0, entao:
	 * getValorPelaPorcentagem(250.0, 60.0) retornara 150.0
	 * @author Thiago Henrique Barbosa Felisberto
	 * @param valorTotal o valor total
	 * @param valorPorcentagem a porcentagem
	 * @return o valor de acordo com a porcentagem em relacao ao valor total
	 */
	public static double getValorPelaPorcentagem(double valorTotal, double valorPorcentagem)
	{
		return (valorTotal * valorPorcentagem) / 100;
	}

	/**
	 * Compara dois valores <code>double</code> com a tolerancia padrao
	 * MARGEM_ERRO_PADRAO.
	 * @author Thiago Henrique Barbosa Felisberto
	 * @param valor1
	 * @param valor2
	 * @return <code>true</code> caso os valores forem considerados iguais.
	 */
	public static boolean equalsWithTolerance(double valor1, double valor2)
	{
		return equalsWithTolerance(valor1, valor2, TOLERANCIA);
	}

	/**
	 * Compara dois valores <code>double</code> com uma tolerancia na diferenca
	 * entre eles.
	 * Ex.: 2.000001 e 2.000000 com uma tolerancia de 0.00001 serao considerados
	 * iguais.
	 * @author Thiago Henrique Barbosa Felisberto
	 * @param valor1
	 * @param valor2
	 * @param tolerance
	 * @return <code>true</code> caso os valores forem considerados iguais.
	 */    
	//    public static boolean equalsWithTolerance(double valor1, double valor2, 
	//    		double tolerance)
	//    {  
	//    	double resto = 0;   
	//    	
	//    	if (valor1 > valor2)
	//    	{
	//    		resto = valor1 / valor2;
	//    	}
	//    	else
	//    	{
	//    		resto = valor2 / valor1;
	//    	}
	//    	
	//    	double resultado = 0;
	//    	
	//    	if (resto > 1d)
	//    	{
	//    		resultado = resto - 1d;
	//    	}
	//    	else
	//    	{
	//    		resultado = 1d - resto;
	//    	}
	//    	
	//    	if (resultado <= tolerance)
	//    	{
	//    		return true;
	//    	}
	//    	
	//    	return false;
	//    } 

	public static boolean equalsWithTolerance(double valor1, double valor2, double tolerance)
	{      	    
		double resto = 0;

		if(valor1 >= 0 && valor2 >= 0)
		{    	
			if (valor1 > valor2)
			{
				resto = valor1 - valor2;
			}
			else
			{
				resto = valor2 - valor1;
			}
		}
		else if (valor1 < 0 && valor2 >= 0)    		
		{
			resto = valor2 + valor1;    		
		}
		else if (valor1 >= 0 && valor2 < 0)
		{
			resto = valor1 + valor2;
		}
		else
		{
			if (valor1 > valor2)
			{
				resto = valor1 - valor2;
			}
			else
			{
				resto = valor2 - valor1;
			}
		}

		if (resto <= tolerance)
		{
			return true;
		}

		return false;
	}    

	public static void main(String[] args) throws Exception 
	{    	
		System.out.println(FuncoesNumeros.formatar2DigitosDecimais(1.4568f));
		System.out.println(FuncoesNumeros.formatar2DigitosDecimais(1.4555f));
		System.out.println(FuncoesNumeros.formatar2DigitosDecimais(1.4525f));

		//    	System.out.println(FuncoesNumeros.equalsWithTolerance(-164.06, 164.07, 0.01));
		//    	System.out.println(FuncoesNumeros.equalsWithTolerance(-5.01, -5.02, 0.01));
		//    	System.out.println(FuncoesNumeros.equalsWithTolerance(-4.399999, -4.12, 0.4));
		//		System.out.println(FuncoesNumeros.equalsWithTolerance(164.06, 164.07, 0.01));
		//		System.out.println(FuncoesNumeros.equalsWithTolerance(164.07, 164.07, 0.01));
		//		System.out.println(FuncoesNumeros.equalsWithTolerance(164.07, 164.06, 0.01));		
		//		System.out.println(FuncoesNumeros.equalsWithTolerance(164.06999, 164.07, 0.01));
		//		System.out.println(FuncoesNumeros.equalsWithTolerance(164.06999, 164.07100, 0.01));
		//		System.out.println(FuncoesNumeros.equalsWithTolerance(164.061, 164.062, 0.01));
		//		System.out.println(FuncoesNumeros.equalsWithTolerance(164.0610001, 164.0620001, 0.01));		
		//		System.out.println(FuncoesNumeros.equalsWithTolerance(164.06, 164.07, 0.02));
		//		System.out.println(FuncoesNumeros.equalsWithTolerance(164.05, 164.07, 0.02));
		//		System.out.println(FuncoesNumeros.equalsWithTolerance(164.05, 164.09, 0.2));
		//		System.out.println(FuncoesNumeros.equalsWithTolerance(164.07, 164.05, 0.01)); //false
		//		System.out.println(FuncoesNumeros.equalsWithTolerance(164.04, 164.07, 0.02)); //false				
	}
	
	public static void formatarValorInteiro(java.awt.event.KeyEvent evt, int numeroMaxDigitos) {
        JTextField text = (JTextField) evt.getSource();
        String data = text.getText();

        try {

            Long.parseLong(data);
        } catch (Exception e) {
            data = "";
        }

        if (data.length() > numeroMaxDigitos) {
            data = data.substring(0, numeroMaxDigitos);
        }

        //123.123.123,11

        if (data.length() > 0 && data.length() < numeroMaxDigitos + 1) {
            if (isNumero(data.charAt(data.length() - 1))) {
                data = replace(data, ".", "");
                data = replace(data, ",", "");
            } else if ((evt.getKeyCode() == KeyEvent.VK_BACK_SPACE)) {
                data = data.substring(0, data.length() - 1);
            } else {
                if (data.length() >= numeroMaxDigitos + 1) {
                    data = data.substring(0, numeroMaxDigitos);
                }
            }
        }

        // para visualizar no visualEditor, comentar as duas linhas abaixo
        text.setText(data);
        text.setCaretPosition(data.length());

    }

    public static boolean isNumero(char caracter) {
        if ((caracter >= '0') && (caracter <= '9')) {
            return true;
        } else {
            return false;
        }
    }

    public static String replace(String str, String strOld, String strNew) {
        int iAnt = 0, iPos = 0;
        StringBuffer strChanged = new StringBuffer();
        iPos = str.indexOf(strOld, iAnt);
        while (iPos != -1) {
            strChanged.append(str.substring(iAnt, iPos) + strNew);
            iAnt = iPos + strOld.length();
            iPos = str.indexOf(strOld, iAnt);
        }
        return strChanged.append(str.substring(iAnt, str.length())).toString();
    }

    public static void formatarData(KeyEvent e) {
        JTextField text = (JTextField) e.getSource();
        String data = text.getText();

        if (data.length() > 10) {
            data = data.substring(0, 10);
        }

        if (data.length() > 0) {
            if (isNumero(data.charAt(data.length() - 1))) {
                data = replace(data, "/", "");
                if ((data.length() > 2) && (data.length() < 5)) {
                    data = data.substring(0, 2) + "/" + data.substring(2, data.length());
                } else if ((data.length() >= 5) && (data.length() <= 10)) {
                    data = data.substring(0, 2) + "/" + data.substring(2, 4) + "/" + data.substring(4, data.length());
                } else if (data.length() > 10) {
                    data = data.substring(0, 2) + "/" + data.substring(2, 4) + "/" + data.substring(4, 10);
                }
            } else if ((e.getKeyCode() != KeyEvent.VK_BACK_SPACE) && !(e.getKeyCode() == KeyEvent.VK_ENTER)) {
                data = data.substring(0, data.length() - 1);
            }
        }

        // se for editar pelo visual editor comente essas duas linhas abaixo
        text.setText(data);
        text.setCaretPosition(data.length());
    }
    
    public static String getNumeroNFe(double valorFloat, String valorString, int numeroCasasDecimais) throws ParseException
	{
		String retorno = "";
		double valor = valorFloat;
		
		if(ValidadorUniversal.check(valorString))
		{
			valor = FuncoesNumeros.getNumero2DigitosDecimais(valorString); 
		}
		
		if(numeroCasasDecimais == 2)
		{
			retorno = FuncoesNumeros.formatar2DigitosDecimais(valor);
		}
		else if(numeroCasasDecimais == 3)
		{
			retorno = FuncoesNumeros.formatar3DigitosDecimais(valor);			
		}
		else
		{
			retorno = FuncoesNumeros.formatar4DigitosDecimais(valor);			
		}
		
		retorno = FuncoesString.replace(retorno, ".", "");
		retorno = FuncoesString.replace(retorno, ",", ".");
		
		return retorno;
		
	}
    
}