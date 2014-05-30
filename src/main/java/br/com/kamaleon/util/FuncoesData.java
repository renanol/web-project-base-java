package br.com.kamaleon.util;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class FuncoesData implements Serializable {
	
	private static final long serialVersionUID = 307916231994742425L;

	public static SimpleDateFormat sdfDataNotaFiscalEletronica = new SimpleDateFormat("yyyy-MM-dd");
	
	public static SimpleDateFormat sdfDataParaNomeArquivo = new SimpleDateFormat("yyyyMMdd");

	public static SimpleDateFormat sdfDataHoraParaNomeArquivo = new SimpleDateFormat("yyyyMMdd_mmHH");

	public static SimpleDateFormat sdfDataPadraoUniversal = new SimpleDateFormat("yyMMdd");

	public static SimpleDateFormat sdfHoraMinutoSegundo = new SimpleDateFormat("HHmmss");

	public static SimpleDateFormat sdfData = new SimpleDateFormat("dd/MM/yyyy");

	public static SimpleDateFormat sdfDataHora = new SimpleDateFormat("dd/MM/yyyy ',' HH:mm");
	public static SimpleDateFormat sdfDataHoraT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	
	public static SimpleDateFormat sdfDataHoraMinutoSegundo = new SimpleDateFormat("dd/MM/yyyy ',' HH:mm:ss");

	public static SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm");

	public static SimpleDateFormat sdfHora24 = new SimpleDateFormat("HH:mm");

	public static SimpleDateFormat sdfAAMM = new SimpleDateFormat("yyMM");
	public static SimpleDateFormat sdfAAAAMM = new SimpleDateFormat("yyyyMM");
	
	public static SimpleDateFormat sdfDDMMYY = new SimpleDateFormat("ddMMyy");
	public static SimpleDateFormat sdfDD_MM_YY = new SimpleDateFormat("dd/MM/yy");

	public static SimpleDateFormat sdfDDMMYYYYHHMMSS = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	public static SimpleDateFormat sdfDDMMYYYYHHMMSS_v2 = new SimpleDateFormat("ddMMyyyyHHmmss");
	
	public static SimpleDateFormat sdfDDMMYYHHMMSS = new SimpleDateFormat("ddMMyyHHmmss");
	public static SimpleDateFormat sdfDDMMYYYYHHMMSS_EPSON = new SimpleDateFormat("ddMMyyyyHHmmss");
	
	public static SimpleDateFormat ddMMyyyy = new SimpleDateFormat("ddMMyyyy");
	public static SimpleDateFormat yyyyMM = new SimpleDateFormat("yyyyMM");

	public static SimpleDateFormat sdfMM_yyyy = new SimpleDateFormat("MM/yyyy");

	public static final long MILIS_POR_DIA = 86400000;

    public static String getDataComFormatacaoEspecifica(Date data, String formatacao)        
    {
        SimpleDateFormat sdfEspecifico = new SimpleDateFormat(formatacao);
        return sdfEspecifico.format(data);
    }
	
    
    public static int getDaysBetween(Date d1, Date d2)
    {
        long millis1 = d1.getTime();
        long millis2 = d2.getTime();
        long millis = millis2 - millis1;
        int dias = (int)(millis/MILIS_POR_DIA);
        
        return dias;
    }//getDaysBetween()

    public static int getDaysBetween(Calendar c1, Calendar c2)
    {
        return getDaysBetween(c1.getTime(), c2.getTime());
    }
    
    
    public static int getYearsBetween(Date dataInicial, Date dataFinal)
    {
        Calendar c1 = getCalendar(dataInicial);
        Calendar c2 = getCalendar(dataFinal);
        int anos = (c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR));
        if(c1.get(Calendar.MONTH) <= c2.get(Calendar.MONTH))
        {
           if(c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH))
           {
               if(c1.get(Calendar.DAY_OF_MONTH) > c2.get(Calendar.DAY_OF_MONTH))
               {
                   anos--;
               }
           }
        }
        else
        {
            anos--;
        }
        
         
        return anos;
    }
    
	
    /**
     * Obter a quantidade de meses entre 2 datas. A funcao leva em consideracao apenas o mes e o ano das datas, 
     * desconsiderando completamente os dias. 
     * Exemplos: 
     * _____________________________________
     * |Data Inici | Data Final | Resposta |  
     * |21/02/2009 | 01/03/2009 | 1        |
     * |29/12/2009 | 07/01/2007 | 1        |
     * |01/01/2009 | 07/01/2007 | 12       |
     * |08/01/2007 | 01/01/2007 | 0        |
     * |20/02/2007 | 07/01/2007 | -1       |
     * |___________|____________|__________|
     * 
     * @return a quantidade de meses entre as duas datas
     */
    public static int getMonthsBetween(Date dataInicial, Date dataFinal)
    {
    	Calendar calendarIni = getCalendar(dataInicial);
    	Calendar calendarFinal = getCalendar(dataFinal);
    	return  calendarFinal.get(Calendar.MONTH) - calendarIni.get(Calendar.MONTH) 
    			+ (calendarFinal.get(Calendar.YEAR) - calendarIni.get(Calendar.YEAR)) * 12;
    }
    
    
    public static int getNumeroDiasDoMes(Date data)
    {
        Calendar cData = getCalendar();
        cData.setTime(data);
        return cData.getActualMaximum(Calendar.DAY_OF_MONTH);
    }//getNumeroDiasDoMes()

    
    public static int getNumeroDiasDoMes(Calendar data)
    {        
        return data.getActualMaximum(Calendar.DAY_OF_MONTH);
        
    }//getNumeroDiasDoMes()
    
    
    public static Calendar getCalendar()
    {
        return Calendar.getInstance(Locale.ITALY);
    }
    
    public static Calendar getCalendar(Date data)
    {
        Calendar calendar = Calendar.getInstance(Locale.ITALY);
        calendar.setTime(data);
        
        return calendar;
    }
    
    public static Calendar getCortePorVencimento(Calendar dataVencimento, int diaCorte)
    {
        int diaVencimento = dataVencimento.get(Calendar.DAY_OF_MONTH);
        
        Calendar dataCorte = getCalendar(dataVencimento.getTime());
        dataCorte.set(Calendar.DAY_OF_MONTH, diaCorte);
        
        if (diaCorte > diaVencimento)
        {
            dataCorte.add(Calendar.MONTH, -1);
        }
        
        return dataCorte;
    }
    
    public static String getDataFormatadaParaArquivoNFE(Date data)
    {
    	return sdfDataParaNomeArquivo.format(data) + "T" + sdfHoraMinutoSegundo.format(data);
    }
    

    public static Calendar getVencimentoPorCorte(Calendar dataCorte, int diaVencimento)
    {
        int diaCorte = dataCorte.get(Calendar.DAY_OF_MONTH);
        
        Calendar dataVencimento = getCalendar(dataCorte.getTime());
        dataVencimento.set(Calendar.DAY_OF_MONTH, diaVencimento);
        
        if (diaCorte > diaVencimento)
        {
            dataVencimento.add(Calendar.MONTH, 1);
        }
        
        return dataVencimento;
    }
    
    public static Calendar[] getVencimentosInternos(Calendar data1, Calendar data2, 
                                             int diaVencimento)
    {
        Calendar[] datasVencimento = new Calendar[2];
        Calendar vencimento1 = data1;
        Calendar vencimento2 = data2;
        
        // SETANDO OS DIAS DO VENCIMENTO
        vencimento1.set(Calendar.DAY_OF_MONTH, diaVencimento);
        vencimento2.set(Calendar.DAY_OF_MONTH, diaVencimento);
        
        if (diaVencimento < data1.get(Calendar.DAY_OF_MONTH))
        {
            vencimento1.add(Calendar.MONTH, 1);
        }
        
        if (diaVencimento > data2.get(Calendar.DAY_OF_MONTH))
        {
            vencimento2.add(Calendar.MONTH, -1);
        }
        
        
        // SE NAO FOR 'AFTER',SIGNIFICA QUE EXISTE VENCIMENTO ENTRE AS DATAS
        if (!(vencimento1.after(vencimento2)))
        {
            datasVencimento[0] = vencimento1;
            datasVencimento[1] = vencimento2;
            return datasVencimento;
        }
        else
        {
            return null;
        }
        
    }
    
    public static Date tirarHoras(Date dataComHoras)
    {
        Date dataSemHoras = null;
        try
        {   
            String strDataComHoras = sdfData.format(dataComHoras);
            dataSemHoras = sdfData.parse(strDataComHoras);
        }
        catch(ParseException erro)
        {
            erro.printStackTrace();
        }
        
        return dataSemHoras;
    }
    
    public static String formatarDataSemHora(Date data)
    {
        return sdfData.format(data);
    }
    
    public static String formatarDataArquivo(Date data)
    {
    	return sdfDataParaNomeArquivo.format(data);
    }
    

    public static String formatarDataComHora(Date data)
    {
        return sdfDataHora.format(data);
    }
    
    public static String formatarDataComHoraMinutoSegundo(Date data)
    {
    	return sdfDataHoraMinutoSegundo.format(data);
    }

    public static String formatarHora(Date data)
    {
        return sdfHora.format(data);
    }
    
    public static String formatarAAMM(Date data)
    {
    	return sdfAAMM.format(data);
    }
    
    public static String formatarYYYY_MM_AA(Date data)
    {
    	return sdfDataNotaFiscalEletronica.format(data);
    }
    
    public static String formatarMM_yyyy(Date data)
    {
    	return sdfMM_yyyy.format(data);
    }

    public static Date getData3Horas(String data)
        throws ParseException
    {
    	Calendar dR = new GregorianCalendar();
    	dR.setTime(sdfData.parse(data));
    	dR.add(Calendar.HOUR_OF_DAY, 3);
        return dR.getTime();
    }
    
    public static Date getData3Horas(Date data)
    		throws ParseException
    {
    	Calendar dR = new GregorianCalendar();
    	dR.setTime(data);
    	dR.add(Calendar.HOUR_OF_DAY, 3);
    	return dR.getTime();
    }
    
    public static Date getData(String data)
	throws ParseException
	{
    	return sdfData.parse(data);
	}
    
    public static Date getDataImpressora(String data)
    throws ParseException
    {
    	return sdfDDMMYYHHMMSS.parse(data);
    }
    public static Date getDataImpressoraSemHora(String data)
	throws ParseException
	{
    	return sdfDDMMYY.parse(data);
	}

    public static Date getDataHora(String data)
        throws ParseException
    {
        return sdfDataHora.parse(data);
    }
    public static Date getDataHoraCompleta(String data)
    throws ParseException
    {
    	return sdfDDMMYYYYHHMMSS.parse(data);
    }
    public static Date getDataHoraCompletaSemMascara(String data)
    throws ParseException
    {
    	return sdfDDMMYYYYHHMMSS_v2.parse(data);
    }

    public static void println(String s, Date data)    
    {
        System.out.println(s + " - " + sdfData.format(data));
    }

    /**
     * Testa se o proprietário de uma data de nascimento já tem a idade passada
     * por parâmetro
     *
     */
    public static boolean testarAtingiuIdade(Date dataNascimento, int idade)
    {
        boolean jaTemIdade = true;
        Calendar dataAtualCalendar = FuncoesData.getCalendar();
        Calendar dataNascimentoCalendar = FuncoesData.getCalendar(dataNascimento);
        dataNascimentoCalendar.add(Calendar.YEAR, idade);
        if (dataNascimentoCalendar.after(dataAtualCalendar))
        {
            jaTemIdade = false;
        }
        return jaTemIdade;
    }


    /**
     * Método utilizado para pegar as datas no intervalo de dia especificado pela data
     *
     * @param filtro Filtroda consulta.
     * @param campo Campo que será adicionado na consulta.
     * @param data Data utilizada para a consulta.
     *
     */
    public static Date[] to_char(Date data)
    {
        Calendar calendar = getCalendar(data);
        return to_char(calendar.get(Calendar.DAY_OF_MONTH), 
                       calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.YEAR));
    }
    
    /**
     * Método utilizado para pegar as datas no intervalo de dia especificado
     *
     * @param filtro Filtroda consulta.
     * @param campo Campo que será adicionado na consulta.
     * @param data Data utilizada para a consulta.
     *
     */
    public static Date[] to_char(int dia, int mes, int ano)
    {
        return to_char(dia, mes, dia, mes, ano);
    }
    
    /**
     * Método utilizado para pegar as datas no intervalo de dias especificado
     *
     * @param filtro Filtroda consulta.
     * @param campo Campo que será adicionado na consulta.
     * @param data Data utilizada para a consulta.
     *
     */
    public static Date[] to_char(int diaInicial, int mesInicial, 
                                 int diaFinal, int mesFinal,
                                 int ano)
    {
        Date[] resultado = new Date[2];
        Calendar calendar = getCalendar(new java.util.Date());        
        calendar.set(Calendar.YEAR, ano);
        
        calendar.set(Calendar.MONTH, mesInicial);
        calendar.set(Calendar.DAY_OF_MONTH, diaInicial);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getMinimum(Calendar.SECOND));
        resultado[0] = calendar.getTime();

        calendar.set(Calendar.MONTH, mesFinal);
        calendar.set(Calendar.DAY_OF_MONTH, diaFinal);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getMaximum(Calendar.SECOND));
        resultado[1] = calendar.getTime();
        
        return resultado;
    }

    /**
     * Método utilizado para pegar as datas no intervalo de dias especificado.
     *
     * @param diaInicial Valor referente ao dia do primeiro intervalo de datas.
     * @param mesInicial Valor referente ao mes do primeiro intervalo de datas.
     * @param anoInicial Valor referente ao ano do primeiro intervalo de datas.
     * @param diaFinal Valor referente ao dia do segundo intervalo de datas.
     * @param mesFinal Valor referente ao mes do segundo intervalo de datas.
     * @param anoFinal Valor referente ao ano do segundo intervalo de datas.
     * @return Um array de datas contendo as datas inicial e final que serao
     * utilizadas para serem filtradas.
     *
     */
    public static Date[] to_char(int diaInicial, int mesInicial, int anoInicial,
                                 int diaFinal, int mesFinal, int anoFinal)
    {
        Date[] resultado = new Date[2];
        Calendar calendar = getCalendar(new java.util.Date());        

        // setando o primeiro filtro de datas
        calendar.set(Calendar.YEAR, anoInicial);
        calendar.set(Calendar.MONTH, mesInicial);
        calendar.set(Calendar.DAY_OF_MONTH, diaInicial);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getMinimum(Calendar.SECOND));
        resultado[0] = calendar.getTime();

        // setando o segundo filtro de datas
        calendar.set(Calendar.YEAR, anoFinal);
        calendar.set(Calendar.MONTH, mesFinal);
        calendar.set(Calendar.DAY_OF_MONTH, diaFinal);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getMaximum(Calendar.SECOND));
        resultado[1] = calendar.getTime();
        
        return resultado;
    }
    
    public static boolean datasIguais(Date data1, Date data2)
    {
        Calendar calendar1 = getCalendar(data1);
        Calendar calendar2 = getCalendar(data2);
        return 
        (calendar1.get(Calendar.YEAR) == 
        	calendar2.get(Calendar.YEAR))
        	&& 
        	(calendar1.get(Calendar.MONTH) == 
        		calendar2.get(Calendar.MONTH))
        		&& 
        		(calendar1.get(Calendar.DAY_OF_MONTH) == 
        			calendar2.get(Calendar.DAY_OF_MONTH));        
    }
    
    /**
     * Devolve um Date modificado de acordo com os parametros passados.
     * Ex.: somarData(data, Calendar.MONTH, -3); supondo que a data 
     * seja 12/03/2009. Sera devolvido um Date como sendo 12/12/2008.
     * @param data
     * @param diaMesAno
     * @param quantidade
     * @return
     */
    public static Date addData(Date data, int diaMesAno, int quantidade)
    {
    	Calendar calendar = getCalendar(data);
    	calendar.add(diaMesAno, quantidade);
    	return calendar.getTime();
    }
    
    
    public static Date getDataComHoraMinima(Date data)
    {
        Calendar calendar = getCalendar(data);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getMinimum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getMinimum(Calendar.MILLISECOND));
        
        return calendar.getTime();        
    }
    
    public static Date getDataComHoraMaxima(Date data)
    {
        Calendar calendar = getCalendar(data);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getMaximum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getMaximum(Calendar.MILLISECOND));
        
        return calendar.getTime();
    }
    
    
    /**
     * Transforma um Date em um String que representa a data por extenso no formato:
     * 25 de Outubro de 1983.
     * @param data
     * @return
     */
    public static String getDataEmExtenso(Date data)
    {
    	String resultado = "";
    	
    	Calendar calendar = getCalendar(data);
    	
    	int dia = calendar.get(Calendar.DAY_OF_MONTH);
    	int mes = calendar.get(Calendar.MONTH);
    	int ano = calendar.get(Calendar.YEAR);
    	
    	resultado += Integer.toString(dia) + " de ";
    	resultado += getMesExtenso(mes) + " de ";
    	resultado += ano;
    	
    	return resultado;
    }
    
    public static String getMesExtenso(int mes)
    {
    	String mesExtenso = "";
    	
    	switch (mes)
		{
    		case 0:
    		{
    			mesExtenso = "Janeiro";
    			break;
    		}
    		
    		case 1:
    		{
    			mesExtenso = "Fevereiro";
    			break;
    		}
    		
    		case 2:
    		{
    			mesExtenso = "Março";
    			break;
    		}
    		
    		case 3:
    		{
    			mesExtenso = "Abril";
    			break;
    		}
    		
    		case 4:
    		{
    			mesExtenso = "Maio";
    			break;
    		}
    		
    		case 5:
    		{
    			mesExtenso = "Junho";
    			break;
    		}
    		
    		case 6:
    		{
    			mesExtenso = "Julho";
    			break;
    		}
    		
    		case 7:
    		{
    			mesExtenso = "Agosto";
    			break;
    		}
    		
    		case 8:
    		{
    			mesExtenso = "Setembro";
    			break;
    		}
    		
    		case 9:
    		{
    			mesExtenso = "Outubro";
    			break;
    		}
    		
    		case 10:
    		{
    			mesExtenso = "Novembro";
    			break;
    		}
    		
    		case 11:
    		{
    			mesExtenso = "Dezembro";
    			break;
    		}
		}
    	
    	return mesExtenso;
    }
    
    public static String getMesExtensoBrasil(int mes)
    {
    	String mesExtenso = "";
    	
    	switch (mes)
		{
    		case 1:
    		{
    			mesExtenso = "Janeiro";
    			break;
    		}
    		
    		case 2:
    		{
    			mesExtenso = "Fevereiro";
    			break;
    		}
    		
    		case 3:
    		{
    			mesExtenso = "Março";
    			break;
    		}
    		
    		case 4:
    		{
    			mesExtenso = "Abril";
    			break;
    		}
    		
    		case 5:
    		{
    			mesExtenso = "Maio";
    			break;
    		}
    		
    		case 6:
    		{
    			mesExtenso = "Junho";
    			break;
    		}
    		
    		case 7:
    		{
    			mesExtenso = "Julho";
    			break;
    		}
    		
    		case 8:
    		{
    			mesExtenso = "Agosto";
    			break;
    		}
    		
    		case 9:
    		{
    			mesExtenso = "Setembro";
    			break;
    		}
    		
    		case 10:
    		{
    			mesExtenso = "Outubro";
    			break;
    		}
    		
    		case 11:
    		{
    			mesExtenso = "Novembro";
    			break;
    		}
    		
    		case 12:
    		{
    			mesExtenso = "Dezembro";
    			break;
    		}
		}
    	
    	return mesExtenso;
    }
    
    public static String getNomeDiaDaSemanaPorExtensoBrasil(Date data)
    {
    	String diaExtenso = "";
    	Calendar calendar = FuncoesData.getCalendar(data);
    	
    	switch (calendar.get(Calendar.DAY_OF_WEEK))
		{
    		case Calendar.SUNDAY:
    		{
    			diaExtenso = "Domingo";
    			break;
    		}
    		
    		case Calendar.MONDAY:
    		{
    			diaExtenso = "Segunda-feira";
    			break;
    		}
    		
    		case Calendar.TUESDAY:
    		{
    			diaExtenso = "Terça-feira";
    			break;
    		}
    		
    		case Calendar.WEDNESDAY:
    		{
    			diaExtenso = "Quarta-feira";
    			break;
    		}
    		
    		case Calendar.THURSDAY:
    		{
    			diaExtenso = "Quinta-feira";
    			break;
    		}
    		
    		case Calendar.FRIDAY:
    		{
    			diaExtenso = "Sexta-feira";
    			break;
    		}
    		
    		case Calendar.SATURDAY:
    		{
    			diaExtenso = "Sábado";
    			break;
    		}
    		
		}
    	
    	return diaExtenso;
    }
    
    public static void main(String[] args) 
	{
//		TESTE DA NOVA FUNCAO getMonthsBetween
		try 
		{
			Date dataIni = getData3Horas("21/02/2009");
			Date dataAtual = getData("01/03/2009");
			
//			System.out.println(dataIni);
//			System.out.println(formatarDataSemHora(dataIni) +" -- "+ formatarDataSemHora(dataAtual) +" -- "+ getMonthsBetween(dataIni, dataAtual));
//			
//			dataIni = getData("01/01/2007");
//			dataAtual = getData("07/01/2007");
//			System.out.println(formatarDataSemHora(dataIni) +" -- "+ formatarDataSemHora(dataAtual) +" -- "+ getMonthsBetween(dataIni, dataAtual));
//			
//			dataIni = getData("29/12/2009");
//			dataAtual = getData("07/01/2007");
//			System.out.println(formatarDataSemHora(dataIni) +" -- "+ formatarDataSemHora(dataAtual) +" -- "+ getMonthsBetween(dataIni, dataAtual));
//			
//			dataIni = getData("01/01/2009");
//			dataAtual = getData("07/01/2007");
//			System.out.println(formatarDataSemHora(dataIni) +" -- "+ formatarDataSemHora(dataAtual) +" -- "+ getMonthsBetween(dataIni, dataAtual));
//			
//			dataIni = getData("08/01/2007");
//			dataAtual = getData("01/01/2007");
//			System.out.println(formatarDataSemHora(dataIni) +" -- "+ formatarDataSemHora(dataAtual) +" -- "+ getMonthsBetween(dataIni, dataAtual));
//			
//			dataIni = getData("20/02/2007");
//			dataAtual = getData("07/01/2007");
//			System.out.println(formatarDataSemHora(dataIni) +" -- "+ formatarDataSemHora(dataAtual) +" -- "+ getMonthsBetween(dataIni, dataAtual));
		} 
		catch (ParseException e) { e.printStackTrace(); }
	}

    
    public static Date getDataDoMes(int mes, int ano)
	{
		String mesEAno = "/" + mes + "/" + ano;
		try
		{
			Date data = FuncoesData.getData("01" + mesEAno);
			return data;
		} 
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		return null;
	}
    
    /**
     * Retorna a semana referente a data passada.
     * Caso a necessidade seja a quantidade de semanas em um mês, será necessário passar
     * a data com o ultimo dia do mês.
     * 
     * @param dataDoMes
     * @return
     */
    public static int getSemanaDoMes(Date dataDoMes)
	{
		ArrayList[] listas = getListasDiasInicioFimESemanaDoMes(dataDoMes);
		ArrayList primeirosDiaSemana = listas[0];
		ArrayList ultimosDiaSemana = listas[1];
		Calendar calendar = FuncoesData.getCalendar(dataDoMes);
		int dia = calendar.get(Calendar.DAY_OF_MONTH);
		for(int i=0;i<primeirosDiaSemana.size() ;i++)
		{
			int primeiroDia = Integer.parseInt((String)primeirosDiaSemana.get(i));
			int ultimoDia = Integer.parseInt((String)ultimosDiaSemana.get(i));
			if( (dia == primeiroDia) || (dia == ultimoDia) )
			{
				return i+1;
			}
			else if ( (dia > primeiroDia) && (dia < ultimoDia) )
			{
				return i+1;
			}
		}
		return -1;
	}

    /**
     * Método que auxilia o método getSemanaDoMes 
     * @param dataDoMes
     * @return
     */
    public static ArrayList[] getListasDiasInicioFimESemanaDoMes(Date dataDoMes)
	{
		int DIAS_EM_UMA_SEMANA = 7;
		int MAX_SEMANAS_MES = 6;

		Calendar calendario = FuncoesData.getCalendar(dataDoMes);
		calendario.set(Calendar.DAY_OF_MONTH, 1);
		
		ArrayList<String> listaPrimeiroDiaSemana = new ArrayList<String>();
		ArrayList<String> listaUltimoDiaSemana = new ArrayList<String>();
		
		int diaDaSemanaDoDiaUm = calendario.get(Calendar.DAY_OF_WEEK);
		int diasRestantesPrimeiraSemana = DIAS_EM_UMA_SEMANA - diaDaSemanaDoDiaUm;
		String primeiraDiaSemana = 1 + "";
		listaPrimeiroDiaSemana.add(primeiraDiaSemana);
		String ultimoDiaSemanaStr = (1 + diasRestantesPrimeiraSemana) + "";
		listaUltimoDiaSemana.add(ultimoDiaSemanaStr);
		int maximoDiaMes = calendario.getActualMaximum(Calendar.DAY_OF_MONTH);
		for (int i = 1; i < MAX_SEMANAS_MES; i++) 
		{
			Integer primeiroDiaSemana = Integer.valueOf((String) (listaPrimeiroDiaSemana.get(i - 1)));
			Integer ultimoDiaSemana = Integer.valueOf((String) (listaUltimoDiaSemana.get(i - 1)));

			if (i == 1) 
			{
				primeiroDiaSemana += (diasRestantesPrimeiraSemana + 1);
				ultimoDiaSemana += (DIAS_EM_UMA_SEMANA);
			} 
			else 
			{
				primeiroDiaSemana += (DIAS_EM_UMA_SEMANA);
				ultimoDiaSemana += (DIAS_EM_UMA_SEMANA);
			}

			if (primeiroDiaSemana >= maximoDiaMes) 
			{
				listaPrimeiroDiaSemana.add(maximoDiaMes + "");
				listaUltimoDiaSemana.add(maximoDiaMes + "");
				break;
			} 
			else 
			{
				listaPrimeiroDiaSemana.add(primeiroDiaSemana + "");
			}

			if (ultimoDiaSemana >= maximoDiaMes) 
			{
				listaUltimoDiaSemana.add(maximoDiaMes + "");
				break;
			} 
			else 
			{
				listaUltimoDiaSemana.add(ultimoDiaSemana + "");
			}
		}
		return new ArrayList[] { listaPrimeiroDiaSemana, listaUltimoDiaSemana };
	}
    
    /**
     * yyyy-MM-dd'T'HH:mm:ss
     * @param data
     * @return
     */
    public static Date getDateTimeT(String data)
    {
    	try {
			return sdfDataHoraT.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
    }
    
    /**
     * Recebe uma data (String) no formato YYYY-MM-DD e converte em data dd/mm/yyyy
     * @param data
     * @return
     * @throws ParseException
     */
    public static Date getDataYYYYMMDD(String data)
    {
    	try {
			return sdfDataNotaFiscalEletronica.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
    }

    public static Date getDataYYYYMMDDsemBarrasEhifens(String data)
    {
    	try {
    		return sdfDataParaNomeArquivo.parse(data);
    	} catch (ParseException e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    
    public static Date getDataYYYYMMDsemBarraEsemHifen(String data)
    {
    	try {
    		return sdfDataParaNomeArquivo.parse(data);
    	} catch (ParseException e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    
    public static String getDataYYYYMM(Date data)
    {
    	return sdfAAAAMM.format(data);
    }
    
    public static String getDataParaPAF(String data)
    {
    	return data.substring(6, 10) +  data.substring(3, 5) + data.substring(0, 2);
    }
    
    public static String getHoraParaPAF(String data)
    {
    	return data.substring(11, 13) +  data.substring(14, 16) + data.substring(17,19);
    }
    
    public static Date getDataMenosUmAno(Date data)
    {
    	Calendar cal = getCalendar(data);
    	cal.add(Calendar.YEAR, -1);
    	
    	return cal.getTime();
    }
    
    public static Integer getAno(Date data){
    	Calendar cal = getCalendar(data);
    	return cal.get(Calendar.YEAR);
    }
}