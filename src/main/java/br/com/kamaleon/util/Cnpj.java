package br.com.kamaleon.util;

public class Cnpj
{
    private String numero;
    private String message;

    public Cnpj() { }

    public Cnpj(String novoNumero) 
    { 
        setNumero(novoNumero);
    }

    public void setNumero(String novoNumero) 
    {
        numero = novoNumero;
    }

    /** Metodo estatico que retorna a notaçao do cnpj: 00.000.000/0000-00 */
    public String getCnpj() 
    {
        return numero.substring(0,2) + "." + numero.substring(2,5) + "." +
               numero.substring(5,8) + "/" + numero.substring(8,12) + "-" + numero.substring(12,14);
    }
    
    public String tirarFormatacaoCnpj()
    {
        String resultado = new String(numero);
        resultado = FuncoesString.replace(resultado, "-", "");
        resultado = FuncoesString.replace(resultado, "/", "");
        resultado = FuncoesString.replace(resultado, ".", "");
        
        return resultado;
    }
    
    public boolean isValido() 
    {
        int soma = 0;
        String resultado = tirarFormatacaoCnpj();
        message = "";
        try 
        {
            Long.parseLong(resultado);
        } 
        catch (Exception e) 
        {
            message = "Somente numeros são permitidos";
            return false;
        }

        // CNPJ
        if(resultado.length() == 14) 
        {
            for (int i=0, j = 5; i < 12; i++) 
            {
                soma += j-- * (resultado.charAt(i) - '0');
                if (j < 2) j = 9;
            }
            soma = 11 - (soma % 11);
            if (soma > 9) soma = 0;
            if (soma == (resultado.charAt(12) - '0')) 
            {
                soma = 0;
                for (int i=0, j = 6; i < 13; i++) 
                {
                    soma += j-- * (resultado.charAt(i) - '0');
                    if (j < 2) j = 9;
                }
                soma = 11 - (soma % 11);
                if (soma > 9) soma = 0;
                if (soma == (resultado.charAt(13) - '0')) 
                {
                    message = "CNPJ Válido";
                    return true;
                }
            }
        }
        message = "CNPJ Inválido";
        return false;
    }

    public String getMessage() 
    {
        return message;
    }
    
}