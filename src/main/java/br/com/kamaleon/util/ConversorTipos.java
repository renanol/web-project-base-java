package br.com.kamaleon.util;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * Conversor de tipos primitivos
 * @author  Hermano (hermano@kamaleon.com.br)
 */
public class ConversorTipos implements Serializable {

    
    /**
     * Retorna o valor inteiro do parametro s no request.
     * Caso o parametro seja nulo, retorna 0
     */
    public static int getInt(HttpServletRequest request, String s)
    {
        s = request.getParameter(s);
        if ((s == null) || (s.equals(""))) 
            return 0;
        else return Integer.parseInt(s);
    }

    
    /** Faz o arredondamento de um float para que este fique
     * com precisao de duas casa decimais
     * @param f o float que sera arredondado
     * @return Retorna o parametro arredondado
     */
    public static float getFloatPrecisaoDoisDigitos(float f)
    {

        float floatRetorno = 0;
        NumberFormat nf2digitosDecimais = NumberFormat.getNumberInstance(Locale.ITALY);
        
        nf2digitosDecimais.setMaximumFractionDigits(2);
        nf2digitosDecimais.setMinimumFractionDigits(2);       
        
        String aux = nf2digitosDecimais.format(f);
        
        try
        {
            floatRetorno = (nf2digitosDecimais.parse(aux)).floatValue();
        }
        catch(ParseException erro)
        {
            erro.printStackTrace();
        }

        return floatRetorno;
        

    }
       
    
    
    /** Retorna os elementos de um iterator em forma de ArrayList.
     * @param it Iterator que tera os seus elemetos transferidos para um ArrayList
     * @return Retorna o ArrayList contendo os elementos do Iterator
     */    
    public static <T extends Object> ArrayList<T> getArrayList(Iterator<T> it)
    {
        ArrayList<T> al = new ArrayList<T>();
        
        while (it.hasNext())
        {
            al.add(it.next());
        }
        
        return al;
    }
    
    
    /*private static <T extends Object> ArrayList<T> getDistinctList(
    		ArrayList<T> list)
    {
    	ArrayList<T> distinctList = new ArrayList<T>();
    	
    	for (T obj : list)
    	{
    		if (!distinctList.contains(obj))
    		{
    			distinctList.add(obj);
    		}
    	}
    	
    	return distinctList;
    }*/
    
    /** Retorna os elementos de um array em forma de ArrayList.
     * @param array Array que tera os seus elemetos transferidos para um ArrayList
     * @return Retorna o ArrayList contendo os elementos do array
     */
    public static <T> ArrayList<T> getArrayList(T[] array)
    {
        ArrayList<T> al = new ArrayList<T>();
        for(int i=0;i<array.length;i++)
        {
            al.add(array[i]);
        }
        return al;
    }
    
    public static <T> List<T> getList(T[] array)
    {
        List<T> al = new ArrayList<T>();
        if (array != null) {
        	for (int i = 0; i < array.length; i++) {
				al.add(array[i]);
			}
        }
        return al;
    }
    
    /** Retorna os elementos de um Map em forma de ArrayList.
     * @param map Map que tera os seus elementos transferidos para um ArrayList
     * @return Retorna o ArrayList contendo os elementos do array
     */
    public static <T,M> ArrayList<M> getArrayList(Map<T,M> map)
    {
        ArrayList<M> al = new ArrayList<M>();
        if ((map != null) && (!map.isEmpty()))
        {
        	Iterator<T> it = map.keySet().iterator();
            while (it.hasNext())
            {
            	al.add(map.get(it.next()));
            }
        }
        return al;
    }
    
    public static String byte2string(byte b[])
    {
        int len;
        for(len = 0; b[len] != 0; len++);
        return new String(b, 0, len);
    }


    
    
    
    
}
