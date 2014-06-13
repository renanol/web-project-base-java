package br.com.kamaleon.util;

public class Cpf
{
    private String  numero;
    private String  message;
    
    public Cpf() { }

    public Cpf(String novoNumero)
    {
        setNumero(novoNumero);
    }    
    
    public void setNumero(String novoNumero) 
    {
        numero = novoNumero;
    }

    /** Metodo estatico que retorna a notacao do cpf: 000.000.000-00 */
    public String getCpf() 
    {
        return numero.substring(0,3) + "." + numero.substring(3,6) + "." +
               numero.substring(6,9) + "-" + numero.substring(9,11);
    }
    
    /** Metodo estatico que retorna o cpf como sendo uma string do tipo 00000000000(modo que e salvo no banco) a partir do cpf na notação: 000.000.000-00 */
    public String getCpfBanco() 
    {
        return numero.substring(0,3) + numero.substring(4,7) + 
               numero.substring(8,11) + numero.substring(12,14);
    }
    
    public boolean isValido() 
    {
        int soma = 0;
        String resultado = retirarMascara();

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

        // CPF CURTO
        if(resultado.length() == 11) 
        {
            for (int i=0; i < 9; i++)
            {
                soma += (10 - i) * (resultado.charAt(i) - '0');
            }
            soma = 11 - (soma % 11);
            if (soma > 9) soma = 0;
            if (soma == (resultado.charAt(9) - '0')) 
            {
                soma = 0;
                for (int i=0; i < 10; i++)
                {
                    soma += (11 - i) * (resultado.charAt(i) - '0');
                }
                soma = 11 - (soma % 11);
                if (soma > 9) soma = 0;
                if (soma == (resultado.charAt(10) - '0')) 
                {
                    message = "CPF Válido";
                    return true;
                }
            }
        }
        // CPF LONGO
        else if(resultado.length() == 16) 
        {
            for (int i=0, j = 7; i < 14; i++) 
            {
                soma += j-- * (resultado.charAt(i) - '0');
                if (j < 2) j = 9;
            }
            soma = 11 - (soma % 11);
            if (soma > 9) soma = 0;
            if (soma == (resultado.charAt(14) - '0')) 
            {
                soma = 0;
                for (int i=0, j = 8; i < 15; i++) 
                {
                    soma += j-- * (resultado.charAt(i) - '0');
                    if (j < 2) j = 9;
                }
                soma = 11 - (soma % 11);
                if (soma > 9) soma = 0;
                if (soma == (resultado.charAt(15) - '0')) 
                {
                    message = "CARD Válido";
                    return true;
                }
            }
        }

        message = "CPF Inválido";
        return false;
    }

    public String getMessage() 
    {
        return message;
    }
    
    public String retirarMascara()
    {
        String aux        = FuncoesString.replace(numero, ".", "");
        String cpfCliente = FuncoesString.replace(aux, "-", "");                            
        
        return cpfCliente;
    }

}