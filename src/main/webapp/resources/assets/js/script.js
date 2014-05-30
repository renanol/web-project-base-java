//detectar o browser
var strUserAgent = navigator.userAgent.toLowerCase(); 
var isIE = strUserAgent.indexOf("msie") > -1; 
var isNS6 = strUserAgent.indexOf("netscape6") > -1; 
var isNS4 = !isIE && !isNS6  && parseFloat(navigator.appVersion) < 5; 

/***
* Descrição.: formata um campo do formulário de
* acordo com a máscara informada...
* Parâmetros: - objForm (o Objeto Form)
* - strField (string contendo o nome
* do textbox)
* - sMask (mascara que define o
* formato que o dado será apresentado,
* usando o algarismo "9" para
* definir números e o símbolo "!" para
* qualquer caracter...
* - evtKeyPress (evento)
* Uso.......: <input type="textbox"
* name="xxx".....
* onkeypress="return txtBoxFormat(document.rcfDownload, 'str_cep', '99999-999', event);">
* Observação: As máscaras podem ser representadas como os exemplos abaixo:
* CEP -> 99.999-999
* CPF -> 999.999.999-99
* CNPJ -> 99.999.999/9999-99
* Data -> 99/99/9999
* hora -> 99:99
* Tel Resid -> (99) 999-9999
* Tel Cel -> (99) 9999-9999
* Processo -> 99.999999999/999-99
* C/C -> 999999-!
* E por aí vai...
***/

function txtBoxFormat(strField, sMask, evtKeyPress) {
    var i, nCount, sValue, fldLen, mskLen,bolMask, sCod, nTecla;

    if(window.event) { // Internet Explorer
      nTecla = evtKeyPress.keyCode; }
    else if(evtKeyPress.which) { // Nestcape / firefox
      nTecla = evtKeyPress.which;
    }
	//se for backspace não faz nada
	if (nTecla != 8){
		sValue = document.getElementById(strField).value;
	// alert(sValue);
	
		// Limpa todos os caracteres de formatação que
		// já estiverem no campo.
		sValue = sValue.toString().replace( "-", "" );
		sValue = sValue.toString().replace( "-", "" );
		sValue = sValue.toString().replace( ".", "" );
		sValue = sValue.toString().replace( ".", "" );
		sValue = sValue.toString().replace( "/", "" );
		sValue = sValue.toString().replace( "/", "" );
		sValue = sValue.toString().replace( "(", "" );
		sValue = sValue.toString().replace( "(", "" );
		sValue = sValue.toString().replace( ")", "" );
		sValue = sValue.toString().replace( ")", "" );
		sValue = sValue.toString().replace( " ", "" );
		sValue = sValue.toString().replace( " ", "" );
		fldLen = sValue.length;
		mskLen = sMask.length;
	
		i = 0;
		nCount = 0;
		sCod = "";
		mskLen = fldLen;
	
		while (i <= mskLen) {
		  bolMask = ((sMask.charAt(i) == "-") || (sMask.charAt(i) == ".") || (sMask.charAt(i) == "/"))
		  bolMask = bolMask || ((sMask.charAt(i) == "(") || (sMask.charAt(i) == ")") || (sMask.charAt(i) == " "))
	
		  if (bolMask) {
			sCod += sMask.charAt(i);
			mskLen++; }
		  else {
			sCod += sValue.charAt(nCount);
			nCount++;
		  }
	
		  i++;
		}
	
		document.getElementById(strField).value = sCod;
	
		if (nTecla != 8) { // backspace
		  if (sMask.charAt(i-1) == "9") { // apenas números...
			return ((nTecla > 47) && (nTecla < 58)); } // números de 0 a 9
		  else { // qualquer caracter...
			return true;
		  } }
		else {
		  return true;
		}
	}//fim do if que verifica se é backspace
}
/************************************/
/** Fim da Função Máscaras Gerais ***/
/************************************/


// muda o action do form e submete
function submeterRelatorio(action)
{
    document.form1.action = action;
    document.form1.submit();
}

// faz do radio readonly no evento colocar a palavra return ex.: onclick="return radioReadOnly()"
function radioReadOnly()
{
	return false;
}

//conta caracteres digitados pelo usuario    
function textCounter(field, countfield, maxlimit)
{
    if (field.value.length > maxlimit) // if too long...trim it!
            field.value = field.value.substring(0, maxlimit);
    // otherwise, update 'characters left' counter
    else 
            countfield.value = maxlimit - field.value.length;
}

// muda o action do form e passa o codigo do item a ser removido
function removerEntidade(entidade, codigo)
{
    messagem = "Deseja realmente remover este item?"
    if(confirm(messagem))
    { 
        document.form1.action = "remover." + entidade + ".do";
        document.form1.codigoItem.value = codigo;
        document.form1.submit();
    }
}

// muda o action do form e passa o codigo do item a ser removido
function remover(entidade, codigo)
{
    messagem = "Deseja realmente remover este item?"
    if(confirm(messagem))
    { 
        document.form1.action = "listar." + entidade + ".do?metodo=remover";
        document.form1.codigoItem.value = codigo;
        document.form1.submit();
    }
}

// muda o action do form e passa o codigo do item a ser ativado
function ativarEntidade(entidade, codigo)
{
	mensagem = realmenteDesejaAtivarMsg;
    if(confirm(mensagem))
    {
    	document.form1.action = "listar." + entidade + ".do?metodo=ativar";
	    document.form1.codigoItem.value = codigo;
	    document.form1.submit();
    }
}

// muda o action do form e passa o codigo do item a ser ativado
function ativar(entidade, codigo)
{
	mensagem = realmenteDesejaAtivarMsg;
    if(confirm(mensagem))
    {
    	document.form1.action = "listar." + entidade + ".do?metodo=ativar";
	    document.form1.codigoItem.value = codigo;
	    document.form1.hashItem.value = codigo;
	    document.form1.submit();
    }
    
}

// muda o action do form e passa o codigo do item a ser cancelado
function cancelarEntidade(entidade, codigo)
{
	mensagem = realmenteDesejaCancelarMsg;
    if(confirm(mensagem))
    {
    	document.form1.action = "listar." + entidade + ".do?metodo=cancelar";
	    document.form1.codigoItem.value = codigo;
	    document.form1.submit();
    }
} 

function cancelar(entidade, codigo)
{
	mensagem = realmenteDesejaCancelarMsg;
    if(confirm(mensagem))
    {
    	document.form1.action = "listar." + entidade + ".do?metodo=cancelar";
	    document.form1.codigoItem.value = codigo;
   	    document.form1.hashItem.value = codigo;
	    document.form1.submit();
    }
} 

// muda o action do form e passa o codigo e o tipo da pessoa a ser removido
function removerPessoa(entidade, codigo, tipo)
{
    messagem = "Deseja realmente remover este item?"
    if(confirm(messagem))
    { 
        document.form1.action = "remover." + entidade + ".do";
        document.form1.codigoItem.value = codigo;
        document.form1.tipo.value = tipo;
        document.form1.submit();
    }
}

// muda o action do form
function mudarAction(valor)
{
    document.form1.action = valor;
}
   
// funcoes relacionadas a tela de preenchimento do endereco atraves do CEP
function show_cepResidencial() {

	var vWinCal = window.open("listar.endereco.do?metodo=listarGet&isSemMenu=S&tela=residencial", "CEP", "width=860,height=530,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,copyhistory=no,top=100,left=175");
	vWinCal.opener = self;
	/*var calc_doc = vWinCal.document;
	calc_doc.write (str_buffer);
	calc_doc.close();*/
}

function show_cepComercial() {

	var vWinCal = window.open("listar.endereco.do?metodo=listarGet&isSemMenu=S&tela=comercial", "CEP", "width=860,height=580,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,copyhistory=no,top=100,left=175");
	vWinCal.opener = self;
/*	var calc_doc = vWinCal.document;
	calc_doc.write (str_buffer);
	calc_doc.close();*/
}

function show_ResidencialSemCep()
{
	var vWinCal = window.open("listar.enderecoSemCep.do?metodo=listarGet&isSemMenu=S&tela=residencial", "CEP", "width=300,height=200,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,copyhistory=no,top=100,left=175");
	vWinCal.opener = self.opener;
}

function show_ComercialSemCep()
{
	var vWinCal = window.open("listar.enderecoSemCep.do?metodo=listarGet&isSemMenu=S&tela=comercial", "CEP", "width=300,height=200,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,copyhistory=no,top=100,left=175");
	vWinCal.opener = self.opener;
}


function close_cepResidencial(cep, logradouro, bairro, localidade, estado) {

         window.opener.document.form1.cep.value=cep;
         window.opener.document.form1.logradouro.value=logradouro;
         window.opener.document.form1.bairro.value=bairro;
         window.opener.document.form1.cidade.value=localidade;
         window.opener.document.form1.uf.value=estado;
         window.close();
}

function close_cepComercial(cep, logradouro, bairro, localidade, estado) {

         window.opener.document.form1.cep.value=cep;
         window.opener.document.form1.logradouro.value=logradouro;
         window.opener.document.form1.bairro.value=bairro;
         window.opener.document.form1.cidade.value=localidade;
         window.opener.document.form1.uf.value=estado;
         window.close();
}

function close_cep(cep, logradouro, bairro, localidade, estado) {

         window.opener.document.form1.cep.value=cep;
         window.opener.document.form1.logradouro.value=logradouro;
         window.opener.document.form1.bairro.value=bairro;
         window.opener.document.form1.cidade.value=localidade;
         window.opener.document.form1.uf.value=estado;
         window.close();
}

// mascara que formata os valores relacionados a data
function FormataData(campo,teclapres) {
	var tecla = teclapres.keyCode;
	vr = document.form1[campo].value;
	vr = vr.replace( ".", "" );
	vr = vr.replace( "/", "" );
	vr = vr.replace( "/", "" );
	tam = vr.length + 1;

	if ( tecla != 9 && tecla != 8 ){
		if ( tam > 2 && tam < 5 )
			document.form1[campo].value = vr.substr( 0, tam - 2  ) + '/' + vr.substr( tam - 2, tam );
		if ( tam >= 5 && tam <= 10 )
			document.form1[campo].value = vr.substr( 0, 2 ) + '/' + vr.substr( 2, 2 ) + '/' + vr.substr( 4, 4 ); 
	}
}

function FormataHora(campo,teclapres) {
	var tecla = teclapres.keyCode;
	vr = document.form1[campo].value;
	vr = vr.replace( ":", "" );
	tam = vr.length ;
	
	if ( tecla != 9 && tecla != 8 ){
		if ( tam == 3 )
		{
			document.form1[campo].value = vr.substr( 0, 2 ) + ':' + vr.substr( 2, 1);
		}
		if ( tam >= 4 )
		{
			document.form1[campo].value = vr.substr( 0, 2 ) + ':' + vr.substr( 2, 2);
		}
	}
}

// mascara que formata os valores relacionados ao cpf
function FormataCpf(campo,tammax,teclapres) {
	var tecla = teclapres.keyCode;
	vr = document.form1[campo].value;
	vr = vr.replace( "/", "" );
	vr = vr.replace( "/", "" );
	vr = vr.replace( ",", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( "-", "" );
	vr = vr.replace( "-", "" );
	vr = vr.replace( "-", "" );
	vr = vr.replace( "-", "" );
	vr = vr.replace( "-", "" );
	tam = vr.length;
	
	if (tam < tammax && tecla != 8){ tam = vr.length + 1 ; }

	if (tecla == 8 ){	tam = tam - 1 ; }
		
	if ( tecla == 8 || tecla >= 48 && tecla <= 57 || tecla >= 96 && tecla <= 105 ){
		if ( tam <= 2 ){ 
	 		document.form1[campo].value = vr ; }
	 	if ( (tam > 2) && (tam <= 5) ){
	 		document.form1[campo].value = vr.substr( 0, tam - 2 ) + '-' + vr.substr( tam - 2, tam ) ; }
	 	if ( (tam >= 6) && (tam <= 8) ){
	 		document.form1[campo].value = vr.substr( 0, tam - 5 ) + '.' + vr.substr( tam - 5, 3 ) + '-' + vr.substr( tam - 2, tam ) ; }
	 	if ( (tam >= 9) && (tam <= 11) ){
	 		document.form1[campo].value = vr.substr( 0, tam - 8 ) + '.' + vr.substr( tam - 8, 3 ) + '.' + vr.substr( tam - 5, 3 ) + '-' + vr.substr( tam - 2, tam ) ; }
	 	if ( (tam >= 12) && (tam <= 14) ){
	 		document.form1[campo].value = vr.substr( 0, tam - 11 ) + '.' + vr.substr( tam - 11, 3 ) + '.' + vr.substr( tam - 8, 3 ) + '.' + vr.substr( tam - 5, 3 ) + '-' + vr.substr( tam - 2, tam ) ; }
	 	if ( (tam >= 15) && (tam <= 17) ){
	 		document.form1[campo].value = vr.substr( 0, tam - 14 ) + '.' + vr.substr( tam - 14, 3 ) + '.' + vr.substr( tam - 11, 3 ) + '.' + vr.substr( tam - 8, 3 ) + '.' + vr.substr( tam - 5, 3 ) + '-' + vr.substr( tam - 2, tam ) ;}
	}		
}

// mascara que formata os valores relacionados ao CNPJ
function FormataCnpj(campo,tammax,teclapres) {
	var tecla = teclapres.keyCode;
	vr = document.form1[campo].value;
	vr = vr.replace( "/", "" );
	vr = vr.replace( "/", "" );
	vr = vr.replace( "/", "" );
	vr = vr.replace( ",", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( "-", "" );
	vr = vr.replace( "-", "" );
	vr = vr.replace( "-", "" );
	vr = vr.replace( "-", "" );
	vr = vr.replace( "-", "" );
	tam = vr.length;

	if (tam < tammax && tecla != 8){ tam = vr.length + 1 ; }

	if (tecla == 8 ){	tam = tam - 1 ; }
		
	if ( tecla == 8 || tecla >= 48 && tecla <= 57 || tecla >= 96 && tecla <= 105 ){
		if ( tam <= 2 ){ 
	 		document.form1[campo].value = vr ; }
	 	if ( (tam > 2) && (tam <= 6) ){
	 		document.form1[campo].value = vr.substr( 0, tam - 2 ) + '-' + vr.substr( tam - 2, tam ) ; }
	 	if ( (tam >= 7) && (tam <= 9) ){
	 		document.form1[campo].value = vr.substr( 0, tam - 6 ) + '/' + vr.substr( tam - 6, 4 ) + '-' + vr.substr( tam - 2, tam ) ; }
	 	if ( (tam >= 10) && (tam <= 12) ){
	 		document.form1[campo].value = vr.substr( 0, tam - 9 ) + '.' + vr.substr( tam - 9, 3 ) + '/' + vr.substr( tam - 6, 4 ) + '-' + vr.substr( tam - 2, tam ) ; }
	 	if ( (tam >= 13) && (tam <= 14) ){
	 		document.form1[campo].value = vr.substr( 0, tam - 12 ) + '.' + vr.substr( tam - 12, 3 ) + '.' + vr.substr( tam - 9, 3 ) + '/' + vr.substr( tam - 6, 4 ) + '-' + vr.substr( tam - 2, tam ) ; }
	 	if ( (tam >= 15) && (tam <= 17) ){
	 		document.form1[campo].value = vr.substr( 0, tam - 14 ) + '.' + vr.substr( tam - 14, 3 ) + '.' + vr.substr( tam - 11, 3 ) + '.' + vr.substr( tam - 8, 3 ) + '.' + vr.substr( tam - 5, 3 ) + '-' + vr.substr( tam - 2, tam ) ;}
	}		
}

// mascara para o cep
function FormataCep(campo,tammax,pos,teclapres){
	var tecla = teclapres.keyCode;
	vr = document.form1[campo].value;
	vr = vr.replace( "-", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( "/", "" );
	tam = vr.length ;

	if (tam < tammax && tecla != 8){ tam = vr.length + 1 ; }

	if (tecla == 8 ){ tam = tam - 1 ; }
			
	if ( tecla == 8 || tecla == 88 || tecla >= 48 && tecla <= 57 || tecla >= 96 && tecla <= 105 ){
		if ( tam <= 2 ){
	 		document.form1[campo].value = vr ;}
		if ( tam > pos && tam <= tammax ){
			document.form1[campo].value = vr.substr( 0, tam - pos ) + '-' + vr.substr( tam - pos, tam );}
	}
	
}

// formatar dados - cnpj, cep, etc
function FormataDado(campo,tammax,pos,teclapres){
	var tecla = teclapres.keyCode;
	//	vr = document.form[campo].value;
	// ao inv�s de recuperar valor do campo utilizadno o form, vou
	// pegar pelo evento
	vr = event.srcElement.value;
	vr = vr.replace( "-", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( "/", "" );
	tam = vr.length ;

	if (tam < tammax && tecla != 8){ tam = vr.length + 1 ; }

	if (tecla == 8 ){ tam = tam - 1 ; }
			
	if ( tecla == 8 || tecla == 88 || tecla >= 48 && tecla <= 57 || tecla >= 96 && tecla <= 105 ){
		if ( tam <= 2 ){
	 		// document.form[campo].value = vr ;
			event.srcElement.value = vr;

		}
		if ( tam > pos && tam <= tammax ){
			// document.form[campo].value = vr.substr( 0, tam - pos ) + '-' + vr.substr( tam - pos, tam );}
			event.srcElement.value = vr.substr( 0, tam - pos ) + '-' + vr.substr( tam - pos, tam );
		}
			
	}
}

function SaltaCampo (campo,prox,tammax,teclapres){
	var tecla = teclapres.keyCode;
	vr = event.srcElement.value;
	if( tecla == 109 || tecla == 188 || tecla == 110 || tecla == 111 || tecla == 223 || tecla == 108 ){
		recuperaPai(event.srcElement.parentElement, "FORM")[campo].value = vr.substr( 0, vr.length - 1 ); }
	else{
	 	vr = vr.replace( "-", "" );
	 	vr = vr.replace( "/", "" );
	 	vr = vr.replace( "/", "" );
	 	vr = vr.replace( ",", "" );
	 	vr = vr.replace( ".", "" );
	 	vr = vr.replace( ".", "" );
	 	vr = vr.replace( ".", "" );
	 	vr = vr.replace( ".", "" );
	 	tam = vr.length;	
		
	 	if (tecla != 0 && tecla != 9 && tecla != 16 )
			if ( tam == tammax )	
			{
				//document.form[prox].focus() ;	
				recuperaPai(event.srcElement.parentElement, "form")[prox].focus();
			}
	}
}



function recuperaPai(elemento, nomeDoPai)
{
   while(elemento.tagName != "FORM") 
	   elemento = elemento.parentElement;
   return elemento;
   
}

//apagar registros. pede confirma��o do usu�rio
function apagar(codigo)
{
    if(confirm("Deseja realmente apagar este registro?"))
    { 
        document.form1.codigo.value = codigo;
        document.form1.submit();
    } 
}

// efetuar logoff. fecha a janela e volta para a tela de login
function logoff () 
{
    if(confirm("Deseja realmente efetuar logoff?")) 
    { 
        document.location = "login.do?metodo=logoff";
    } 
    else 
    { 
    } 
}

// voltar para a tela de login se houver o erro "Sess�o Expirada"
function voltaLogin()
 {
    document.location = "index.html";
}

// n�o permitir que o usu�rio mude o estado de um checkbox
function naodesabilita(elemento)
{
    elemento.checked = true;
}

function naohabilita(elemento)
{
    elemento.checked = false;
}

function fechaJanela()
{	
    var janela = '';

    if (!window.janela) {
    }	
    else if (window.janela && !window.janela.closed)	{
            window.janela.close();
    }
}

// para abrir os relatorios em outra janela
function janelaRelatorio()
{
    document.form1.submit();
}

// para abrir a janela de relat�rio
function abreRelatorio(url)
{    
    window.open(url,"Relatorio","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,copyhistory=no,width=900,height=500");
    history.go(-1);  
}

function abreHelp(url)
{    
   window.open(url,"Help","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,copyhistory=no,width=90,height=400");

}

function abreJanela(titulo, url)
{    
    window.open(url,titulo,"toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,copyhistory=no,width=900,height=500,top=0,left=0");
}

function abreGrafico(url)
{    
    window.open(url,"Grafico","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=yes,copyhistory=no,width=900,height=500,top=0,left=0");
    history.go(-1);  
}

// para abrir a impressao de Pedido e Cargas em outra janela
function janelaImprimir(url)
{
    window.open(url,"Relatorio","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,copyhistory=no,width=860,height=500");
}

// diz que o comando a ser executado e o de voltar para a tela anterior
function voltar()
{
    document.form1.comando.value = "voltar";
    document.form1.submit();
}

// verificar se o arquivo que o usu�rio escolheu para a foto do Produto � adequado (jpg ou gif)
function check() 
{
    var ext = document.fotoProduto.arquivo.value;
    ext = ext.substring(ext.length-3,ext.length);
    ext = ext.toLowerCase();
    if(ext != 'jpg' || 'gif' || 'bmp')
    {
        alert('Voce escolheu um arquivo .'+ext+'. Por favor escolha um arquivo de imagem (.jpg , .gif , .bmp)');
        return false;
    }
    else
    {
        return true; 
    }
}

// Transforma um n�mero de formato brasileiro para formato americano
function BrazilToUSA(val) 
{
    val = '' + val;
    resultado = '';

    for (contadorSepToDec=val.length -1; contadorSepToDec >= 0 ; contadorSepToDec--) 
    {
        if (val.charAt(contadorSepToDec) == ',')
            resultado = '.' + resultado;
        else if (val.charAt(contadorSepToDec) != '.')
            resultado = val.charAt(contadorSepToDec) + resultado;
    }
    
    return resultado;
}

// Transforma um n�mero de formato americano para formato brasileiro
function USAToBrazil(val) 
{
    val = '' + val;
    contadorUSAToBrazil = val.indexOf('.');
    if (contadorUSAToBrazil > 0) 
    {
        resultado = val.substring(0,contadorUSAToBrazil) + ',' + val.substring(contadorUSAToBrazil+1);
    }
    else
    {
        resultado = val;
        contadorUSAToBrazil = val.length;
    }

    for (contadorUSAToBrazil -= 3; contadorUSAToBrazil > 0 ; contadorUSAToBrazil -= 3)
    {
        resultado = resultado.substring(0,contadorUSAToBrazil) + '.' + resultado.substring(contadorUSAToBrazil);
    }
    
    contadorTemVirgula = resultado.indexOf(',');
    if(contadorTemVirgula <= 0)
	{
    	resultado = resultado + ',00';
	}
    return resultado;
}

// Normaliza o n�mero dado com o n�mero de casas decimais fornecidas
function normalizar(val, CasasDecimais)
{
    numeroCasasDecimais = parseInt(CasasDecimais);
    val = val + '';
    indiceNormalizar = val.indexOf(',');
    if (indiceNormalizar > 0)
    {
        diferencaCasasDecimais = val.length - indiceNormalizar - numeroCasasDecimais -1;
        if (diferencaCasasDecimais > 0) 
        {
            return val.substring(0, indiceNormalizar + 3);
        }
        else if (diferencaCasasDecimais == 0)
        {
            return val;
        }
        else 
        {
            diferencaCasasDecimais = -1 * diferencaCasasDecimais;
            
            for (contadorNormalizar = 0; contadorNormalizar < diferencaCasasDecimais; contadorNormalizar++)
            {
                val = val + '0';
            }
            return val;
        }
    }
    else return val + ',00';
}

// calcular os valores dos produtos adicionados a um pedido
function calculaTotal()
{
    var n = parseInt(document.form1.nLinhas.value);
    valorPreco = '0';
    valorPeso = '0';

    for (i=1; i <= n ; i++) 
    {
        tempPreco = document.getElementById("precoTotal_" + i + "");
        var blaPreco = parseFloat(BrazilToUSA(tempPreco.value));
        valorPreco = parseFloat(valorPreco) + parseFloat(blaPreco);

        tempPeso = document.getElementById("pesoTotal_" + i + "");
        var blaPeso = parseFloat(BrazilToUSA(tempPeso.value));
        valorPeso = parseFloat(valorPeso) + parseFloat(blaPeso);
    }
    document.form1.precoTotal.value = normalizar(USAToBrazil(valorPreco),'2');
    document.form1.pesoTotal.value = normalizar(USAToBrazil(valorPeso),'2');	   
}

// verificar o tamanho maximo de caracteres no campo observacao
function ok(maxchars)
{
    if(document.form1.observacao.value.length > maxchars)
    {
        alert('Limite de caracteres ultrapassado! Por favor remova '+
        (document.form1.observacao.value.length - maxchars)+ ' caracteres');
        return false;
    }
    else
    {
        return true;
    }
}

// selecionar todos os emails
function selecionaEmails()
{
    var n = parseInt(document.form1.nLinhas.value);
    
    for (i=1; i <= n ; i++) {
        cliente = document.getElementById("codigo_" + i + "");
        cliente.setAttribute("checked","true");


    }
}

// mascara que formata os valores relacionados a dinheiro
function FormataValor(obj,tammax,teclapres) {
	var tecla = teclapres.keyCode;
	vr = obj.value;
	vr = vr.replace( "/", "" );
	vr = vr.replace( "/", "" );
	vr = vr.replace( ",", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );
	tam = vr.length;

	if (tam < tammax && tecla != 8){ tam = vr.length + 1 ; }

	if (tecla == 8 ){	tam = tam - 1 ; vr = vr.substr(0, tam-1);}
		
	if ( tecla == 8 || tecla >= 48 && tecla <= 57 || tecla >= 96 && tecla <= 105 ){
		if ( tam <= 2 ){ 
	 		obj.value = vr ; }
	 	if ( (tam > 2) && (tam <= 5) ){
	 		obj.value = vr.substr( 0, tam - 2 ) + ',' + vr.substr( tam - 2, tam ) ; }
	 	if ( (tam >= 6) && (tam <= 8) ){
	 		obj.value = vr.substr( 0, tam - 5 ) + '.' + vr.substr( tam - 5, 3 ) + ',' + vr.substr( tam - 2, tam ) ; }
	 	if ( (tam >= 9) && (tam <= 11) ){
	 		obj.value = vr.substr( 0, tam - 8 ) + '.' + vr.substr( tam - 8, 3 ) + '.' + vr.substr( tam - 5, 3 ) + ',' + vr.substr( tam - 2, tam ) ; }
	 	if ( (tam >= 12) && (tam <= 14) ){
	 		obj.value = vr.substr( 0, tam - 11 ) + '.' + vr.substr( tam - 11, 3 ) + '.' + vr.substr( tam - 8, 3 ) + '.' + vr.substr( tam - 5, 3 ) + ',' + vr.substr( tam - 2, tam ) ; }
	 	if ( (tam >= 15) && (tam <= 17) ){
	 		obj.value = vr.substr( 0, tam - 14 ) + '.' + vr.substr( tam - 14, 3 ) + '.' + vr.substr( tam - 11, 3 ) + '.' + vr.substr( tam - 8, 3 ) + '.' + vr.substr( tam - 5, 3 ) + ',' + vr.substr( tam - 2, tam ) ;}
	}
		
}
// mascara que formata os valores relacionados a dinheiro
function FormataValor(obj,tammax,teclapres) {
	var tecla = teclapres.keyCode;
	vr = obj.value;
	vr = vr.replace( "/", "" );
	vr = vr.replace( "/", "" );
	vr = vr.replace( "/", "" );
	vr = vr.replace( "/", "" );
	vr = vr.replace( "/", "" );
	vr = vr.replace( "/", "" );
	vr = vr.replace( "/", "" );
	vr = vr.replace( ",", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );
	tam = vr.length;
	
	if (tam < tammax && tecla != 8){ tam = vr.length + 1 ; }
	
	if (tecla == 8 ){	tam = tam - 1 ; vr = vr.substr(0, tam-1);}
	
	if ( tecla == 8 || tecla >= 48 && tecla <= 57 || tecla >= 96 && tecla <= 105 ){
		if ( tam <= 2 ){ 
			obj.value = vr ; }
		if ( (tam > 2) && (tam <= 5) ){
			obj.value = vr.substr( 0, tam - 2 ) + ',' + vr.substr( tam - 2, tam ) ; }
		if ( (tam >= 6) && (tam <= 8) ){
			obj.value = vr.substr( 0, tam - 5 ) + '.' + vr.substr( tam - 5, 3 ) + ',' + vr.substr( tam - 2, tam ) ; }
		if ( (tam >= 9) && (tam <= 11) ){
			obj.value = vr.substr( 0, tam - 8 ) + '.' + vr.substr( tam - 8, 3 ) + '.' + vr.substr( tam - 5, 3 ) + ',' + vr.substr( tam - 2, tam ) ; }
		if ( (tam >= 12) && (tam <= 14) ){
			obj.value = vr.substr( 0, tam - 11 ) + '.' + vr.substr( tam - 11, 3 ) + '.' + vr.substr( tam - 8, 3 ) + '.' + vr.substr( tam - 5, 3 ) + ',' + vr.substr( tam - 2, tam ) ; }
		if ( (tam >= 15) && (tam <= 17) ){
			obj.value = vr.substr( 0, tam - 14 ) + '.' + vr.substr( tam - 14, 3 ) + '.' + vr.substr( tam - 11, 3 ) + '.' + vr.substr( tam - 8, 3 ) + '.' + vr.substr( tam - 5, 3 ) + ',' + vr.substr( tam - 2, tam ) ;}
	}
	
}
// mascara que formata os valores relacionados a dinheiro
function FormataNumeroCasaDecimal(obj,tammax,teclapres,casasDecimais) {
	var tecla = teclapres.keyCode;
	vr = obj.value;
	vr = vr.replace( "/", "" );
	vr = vr.replace( "/", "" );
	vr = vr.replace( ",", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );
	tam = vr.length;
	
	if (tam < tammax && tecla != 8){ tam = vr.length + 1 ; }
	
	if (tecla == 8 ){	tam = tam - 1 ; vr = vr.substr(0, tam-1);}
	
	// se 頢ackspace, numerico do teclado alfa ou numero do teclado numerico
	if ( tecla == 8 || tecla >= 48 && tecla <= 57 || tecla >= 96 && tecla <= 105 ){
		if ( tam <= casasDecimais ){ 
			obj.value = vr ; }
		if ( (tam > casasDecimais) && (tam <= casasDecimais +3) ){
			obj.value = vr.substr( 0, tam - casasDecimais ) + ',' + vr.substr( tam - casasDecimais, tam ) ; }
		if ( (tam >= casasDecimais + 4) && (tam <= casasDecimais +6) ){
			obj.value = vr.substr( 0, tam - casasDecimais +3 ) + '.' + vr.substr( tam - casasDecimais +3, 5 ) + ',' + vr.substr( tam - casasDecimais, tam ) ; }
		if ( (tam >= casasDecimais + 7 ) && (tam <= casasDecimais + 9) ){
			obj.value = vr.substr( 0, tam - casasDecimais +6 ) + '.' + vr.substr( tam - casasDecimais +6, casasDecimais +1 ) + '.' + vr.substr( tam - casasDecimais +3, casasDecimais +1 ) + ',' + vr.substr( tam - casasDecimais, tam ) ; }
		if ( (tam >= casasDecimais + 10) && (tam <= casasDecimais +12) ){
			obj.value = vr.substr( 0, tam - casasDecimais +9 ) + '.' + vr.substr( tam - casasDecimais +9, casasDecimais +1 ) + '.' + vr.substr( tam - casasDecimais +6, casasDecimais +1 ) + '.' + vr.substr( tam - casasDecimais +3, casasDecimais +1 ) + ',' + vr.substr( tam - casasDecimais, tam ) ; }
		if ( (tam >= casasDecimais + 13) && (tam <= casasDecimais +15) ){
			obj.value = vr.substr( 0, tam - casasDecimais +12 ) + '.' + vr.substr( tam - casasDecimais +12, casasDecimais +1 ) + '.' + vr.substr( tam - casasDecimais +9, casasDecimais +1 ) + '.' + vr.substr( tam - casasDecimais +6, casasDecimais +1 ) + '.' + vr.substr( tam - casasDecimais +3, casasDecimais +1 ) + ',' + vr.substr( tam - casasDecimais, tam ) ;}
	}
	
}


//mascara para o campo so aceitar numeros

function verificaNumero(e){
    var tecla=(window.event)?event.keyCode:e.which;   
    if((tecla>47 && tecla<58)) return true;
    else{
    	if (tecla==8 || tecla==0) return true;
	else  return false;
    }
}	
   
   // mascara que formata os valores relacionados ao CNPJ
function FormataCnpjNew(obj,tammax,teclapres) {
	var tecla = teclapres.keyCode;
	vr = obj.value;
	vr = vr.replace( "/", "" );
	vr = vr.replace( "/", "" );
	vr = vr.replace( "/", "" );
	vr = vr.replace( ",", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( "-", "" );
	vr = vr.replace( "-", "" );
	vr = vr.replace( "-", "" );
	vr = vr.replace( "-", "" );
	vr = vr.replace( "-", "" );
	tam = vr.length;

	if (tam < tammax && tecla != 8){ tam = vr.length + 1 ; }

	if (tecla == 8 ){	tam = tam - 1 ; }
		
	if ( tecla == 8 || tecla >= 48 && tecla <= 57 || tecla >= 96 && tecla <= 105 ){
		if ( tam <= 2 ){ 
	 		obj.value = vr ; }
	 	if ( (tam > 2) && (tam <= 6) ){
	 		obj.value = vr.substr( 0, tam - 2 ) + '-' + vr.substr( tam - 2, tam ) ; }
	 	if ( (tam >= 7) && (tam <= 9) ){
	 		obj.value = vr.substr( 0, tam - 6 ) + '/' + vr.substr( tam - 6, 4 ) + '-' + vr.substr( tam - 2, tam ) ; }
	 	if ( (tam >= 10) && (tam <= 12) ){
	 		obj.value = vr.substr( 0, tam - 9 ) + '.' + vr.substr( tam - 9, 3 ) + '/' + vr.substr( tam - 6, 4 ) + '-' + vr.substr( tam - 2, tam ) ; }
	 	if ( (tam >= 13) && (tam <= 14) ){
	 		obj.value = vr.substr( 0, tam - 12 ) + '.' + vr.substr( tam - 12, 3 ) + '.' + vr.substr( tam - 9, 3 ) + '/' + vr.substr( tam - 6, 4 ) + '-' + vr.substr( tam - 2, tam ) ; }
	 	if ( (tam >= 15) && (tam <= 17) ){
	 		obj.value = vr.substr( 0, tam - 14 ) + '.' + vr.substr( tam - 14, 3 ) + '.' + vr.substr( tam - 11, 3 ) + '.' + vr.substr( tam - 8, 3 ) + '.' + vr.substr( tam - 5, 3 ) + '-' + vr.substr( tam - 2, tam ) ;}
	}		
}

// mascara que formata os valores relacionados a data
function FormataDataNew(obj,teclapres) {
	var tecla = teclapres.keyCode;
	vr = obj.value;
	vr = vr.replace( ".", "" );
	vr = vr.replace( "/", "" );
	vr = vr.replace( "/", "" );
	tam = vr.length + 1;

	if ( tecla != 9 && tecla != 8 ){
		if ( tam > 2 && tam < 5 )
			obj.value = vr.substr( 0, tam - 2  ) + '/' + vr.substr( tam - 2, tam );
		if ( tam >= 5 && tam <= 10 )
			obj.value = vr.substr( 0, 2 ) + '/' + vr.substr( 2, 2 ) + '/' + vr.substr( 4, 4 ); 
	}
}


// mascara que formata os valores relacionados ao cpf
function FormataCpfNew(obj,tammax,teclapres) {
	var tecla = teclapres.keyCode;
	vr = obj.value;
	vr = vr.replace( "/", "" );
	vr = vr.replace( "/", "" );
	vr = vr.replace( ",", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( "-", "" );
	vr = vr.replace( "-", "" );
	vr = vr.replace( "-", "" );
	vr = vr.replace( "-", "" );
	vr = vr.replace( "-", "" );
	tam = vr.length;
	
	if (tam < tammax && tecla != 8){ tam = vr.length + 1 ; }

	if (tecla == 8 ){	tam = tam - 1 ; }
		
	if ( tecla == 8 || tecla >= 48 && tecla <= 57 || tecla >= 96 && tecla <= 105 ){
		if ( tam <= 2 ){ 
	 		obj.value = vr ; }
	 	if ( (tam > 2) && (tam <= 5) ){
	 		obj.value = vr.substr( 0, tam - 2 ) + '-' + vr.substr( tam - 2, tam ) ; }
	 	if ( (tam >= 6) && (tam <= 8) ){
	 		obj.value = vr.substr( 0, tam - 5 ) + '.' + vr.substr( tam - 5, 3 ) + '-' + vr.substr( tam - 2, tam ) ; }
	 	if ( (tam >= 9) && (tam <= 11) ){
	 		obj.value = vr.substr( 0, tam - 8 ) + '.' + vr.substr( tam - 8, 3 ) + '.' + vr.substr( tam - 5, 3 ) + '-' + vr.substr( tam - 2, tam ) ; }
	 	if ( (tam >= 12) && (tam <= 14) ){
	 		obj.value = vr.substr( 0, tam - 11 ) + '.' + vr.substr( tam - 11, 3 ) + '.' + vr.substr( tam - 8, 3 ) + '.' + vr.substr( tam - 5, 3 ) + '-' + vr.substr( tam - 2, tam ) ; }
	 	if ( (tam >= 15) && (tam <= 17) ){
	 		obj.value = vr.substr( 0, tam - 14 ) + '.' + vr.substr( tam - 14, 3 ) + '.' + vr.substr( tam - 11, 3 ) + '.' + vr.substr( tam - 8, 3 ) + '.' + vr.substr( tam - 5, 3 ) + '-' + vr.substr( tam - 2, tam ) ;}
	}		
}


function pegaPrimeiroCampo() {
	for (var i=0; i++; i< document.form1.elements) {
		if ((document.form1[i].disabled) && (document.form1[i].visible)) {
			alert(document.form1[i].name);
		}
	}

}
function abreSobre() {  
	window.open("sobre.do","Sobre","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=yes,copyhistory=no,width=460,height=450");
}
function exibirAjuda(url) {
	window.open("ajuda/"+url,"Ajuda","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,copyhistory=no,width=800,height=600");
}

function FormataValorNew(obj,tammax,teclapres) {
	var tecla = teclapres.keyCode;
	vr = obj.value;
	vr = vr.replace( "/", "" );
	vr = vr.replace( "/", "" );
	vr = vr.replace( ",", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );
	tam = vr.length;

	if (tam < tammax && tecla != 8){ tam = vr.length + 1 ; }

	if (tecla == 8 ){	tam = tam - 1 ; }
		
	if ( tecla == 8 || tecla >= 48 && tecla <= 57 || tecla >= 96 && tecla <= 105 ){
		if ( tam <= 2 ){ 
	 		obj.value = vr ; }
	 	if ( (tam > 2) && (tam <= 5) ){
	 		obj.value = vr.substr( 0, tam - 2 ) + ',' + vr.substr( tam - 2, tam ) ; }
	 	if ( (tam >= 6) && (tam <= 8) ){
	 		obj.value = vr.substr( 0, tam - 5 ) + '.' + vr.substr( tam - 5, 3 ) + ',' + vr.substr( tam - 2, tam ) ; }
	 	if ( (tam >= 9) && (tam <= 11) ){
	 		obj.value = vr.substr( 0, tam - 8 ) + '.' + vr.substr( tam - 8, 3 ) + '.' + vr.substr( tam - 5, 3 ) + ',' + vr.substr( tam - 2, tam ) ; }
	 	if ( (tam >= 12) && (tam <= 14) ){
	 		obj.value = vr.substr( 0, tam - 11 ) + '.' + vr.substr( tam - 11, 3 ) + '.' + vr.substr( tam - 8, 3 ) + '.' + vr.substr( tam - 5, 3 ) + ',' + vr.substr( tam - 2, tam ) ; }
	 	if ( (tam >= 15) && (tam <= 17) ){
	 		obj.value = vr.substr( 0, tam - 14 ) + '.' + vr.substr( tam - 14, 3 ) + '.' + vr.substr( tam - 11, 3 ) + '.' + vr.substr( tam - 8, 3 ) + '.' + vr.substr( tam - 5, 3 ) + ',' + vr.substr( tam - 2, tam ) ;}
	}
}

function limparCampo(nomeCampo)
{
    var campo = document.getElementById(nomeCampo);
    campo.value = '';
}

function showJanelaListarSemMenu(entidade, width, height, complementoURL)
{
	 var esquerda = (screen.width - width)/2;
     var topo = (screen.height - height)/2;
     
    var vWinCal = window.open(
        "listar."+entidade+".do?metodo=listarGet&isSemMenu=S"+complementoURL,
        ""+entidade, 
        "width="+width+",height="+height+",toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,copyhistory=no,top="+topo+",left="+esquerda);
    vWinCal.opener = self;
    
    
}

function showJanelaVisualizarSemMenu(entidade, passo, width, height, complementoURL)
{
	if (passo == "")
	{
		var vWinCal = window.open(
		    "visualizar."+entidade+".do?metodo=visualizarGet&isSemMenu=S"+complementoURL,
		    ""+entidade, 
		    "width="+width+",height="+height+",toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,copyhistory=no,top=100,left=175");
	}
	else
	{
	    var vWinCal = window.open(
	        "visualizarPasso" + passo + "."+entidade+".do?metodo=visualizarGet&isSemMenu=S"+complementoURL,
	        ""+entidade, 
	        "width="+width+",height="+height+",toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,copyhistory=no,top=100,left=175");
	}
    vWinCal.opener = self;
}

function showJanelaVisualizarSemMenuMetodoDiferente(entidade, passo, width, height, complementoURL,metodo)
{
	if (passo == "")
	{
		var vWinCal = window.open(
		    "visualizar."+entidade+".do?metodo="+metodo+"&isSemMenu=S"+complementoURL,
		    ""+entidade, 
		    "width="+width+",height="+height+",toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,copyhistory=no,top=100,left=175");
	}
	else
	{
	    var vWinCal = window.open(
	        "visualizarPasso" + passo + "."+entidade+".do?metodo=visualizarGet&isSemMenu=S"+complementoURL,
	        ""+entidade, 
	        "width="+width+",height="+height+",toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,copyhistory=no,top=100,left=175");
	}
    vWinCal.opener = self;
}

function showJanelaIncluirSemMenuMetodoDiferente(entidade, passo, width, height, complementoURL,metodo)
{
	if (passo == "")
	{
		var vWinCal = window.open(
		    "incluirAlterar."+entidade+".do?metodo="+metodo+"&isSemMenu=S"+complementoURL,
		    ""+entidade, 
		    "width="+width+",height="+height+",toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,copyhistory=no,top=100,left=175");
	}
	else
	{
	    var vWinCal = window.open(
	        "incluirAlterarPasso" + passo + "."+entidade+".do?metodo=visualizarGet&isSemMenu=S"+complementoURL,
	        ""+entidade, 
	        "width="+width+",height="+height+",toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,copyhistory=no,top=100,left=175");
	}
    vWinCal.opener = self;
}

function showJanelaSemMenu(link, width, height)
{
    var vWinCal = window.open(link, "", 
        "width="+width+",height="+height+",toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,copyhistory=no,top=100,left=175");
    vWinCal.opener = self;
}

function aguardar()
{
    document.form1.aguarde.value=  "AGUARDE...";
}

function limparAguardar()
{
    document.form1.aguarde.value=  "";
}

//function submeter(novoAction)
//{
//    document.form1.action = novoAction;
//    document.form1.submit();
//}

//bloquear o duplo submit
    function confirmarAcao()
    {
	  if (document.form1.campo.value == "false")
        {
            if (confirm("Deseja confirmar a acao?"))
            {
                document.form1.campo.value = "true";
                document.form1.submit();
            }
        }
    }
    
//Marcar todos os checkboxes
function selecionarTudo(check, nomeChecks)
{	
	for (var i = 0; i < document.form1.elements.length; i++)
	{ 
	    var x = document.form1.elements[i];
	    if (x.name == nomeChecks) 
	    {
			x.checked = check.checked;
		}
	} 
}


// Verifica se myNum eh um numero inteiro
function isInteiro(myNum) 
{
     var myMod = myNum % 1;

     if (myMod == 0) 
     {
         return true;
     } 
     else 
     {
         return false;
     }
}


////////////////////////////////////
/* FUNCOES RELACIONADAS A COOKIES */
////////////////////////////////////

function setCookie(name, value, expires, path, domain, secure) {
	document.cookie= name + "=" + escape(value) +
		((expires) ? "; expires=" + expires.toGMTString() : "") +
		((path) ? "; path=" + path : "") +
		((domain) ? "; domain=" + domain : "") +
		((secure) ? "; secure" : "");
}

function getCookie(name) {
		var dc = document.cookie;
		var prefix = name + "=";
		var begin = dc.indexOf("; " + prefix);
		if (begin == -1) {
			begin = dc.indexOf(prefix);
			if (begin != 0) return null;
		} else {
			begin += 2;
		}
		var end = document.cookie.indexOf(";", begin);
		if (end == -1) {
			end = dc.length;
		}
		return unescape(dc.substring(begin + prefix.length, end));
	}

function eraseCookie(name, path, domain) {
		if (getCookie(name)) {
			document.cookie = name + "=" +
				((path) ? "; path=" + path : "") +
				((domain) ? "; domain=" + domain : "") +
				"; expires=Thu, 01-Jan-70 00:00:01 GMT";
		}
	}

function eraseCookie2(name)	{
	setCookie(name,"");
}

function RequisicaoAjax(url, parametros, funcaoSucesso){

	jQuery(document).ready(function(event){
		
		jQuery.ajax({		
	    	url: url,	    	
			data: parametros,
	    	type: 'GET',
	    	beforeSend: function(){
	    	
		    	jQuery.blockUI({ css: { 
			            border: 'none', 
			            padding: '15px', 
			            backgroundColor: '#000', 
			            '-webkit-border-radius': '10px', 
			            '-moz-border-radius': '10px', 
			            opacity: .5, 
			            color: '#fff' 
		        	}
		        }); 
		        
	    	},
	    	success: function(data){
	    		jQuery.unblockUI();
	        	preencherListaResultado(data);
	    	}
		});		     
	});		
		

}

   /* function RequisicaoAjax(url, parametros, funcaoSucesso, async, funcaoException) {
    
            limparErro($('errorAjax')); 
    
            var valor_async = true;
            
            if (async == true || async == false) {
                valor_async = async;
            }

            new Ajax.Request(url,
                {
                    asynchronous: valor_async,
                    parameters: parametros,
                    onSuccess: function (transport) {
                        $('errorMarkup').style.display = "none";
                        changeClass('vermelho', 'blefe');
                        funcaoSucesso(transport);
                        
                        //$('loading').setStyle({display: 'none'});
                        jQuery.unblockUI(); 

                        
                    },
                    onFailure: function (req, exception) {
                        var err = req.getHeader("kamaleon-exception");
                        alert(err);
                        if (err) {
                            popularError(req.transport.getResponseHeader("kamaleon-exception"));
                        } else {
                            alert("Ocorreu uma falha na comunica磯 com o servidor. Tente novamente.");
                            alert("Failure: " + exception);
                        }

                        //$('loading').setStyle({display: 'none'});
                        
                        jQuery.unblockUI(); 

                    },
                    onLoading: function () {
                    	
                        //$('loading').setStyle({display: 'inline'});
                        
                        jQuery.blockUI({ message: '<img src="images/aguarde2.gif" />',  css: {
               			 
                            border: 'none', 
                            padding: '15px', 
                            backgroundColor: '#fff', 
                            '-webkit-border-radius': '10px', 
                            '-moz-border-radius': '10px', 
                            opacity: .5, 
                            color: '#fff'
                             
                        } });
                        
                    },
                    onLoaded: function (transport) {
                        //$('loading').setStyle({display: 'none'});                       
                    },
                    onComplete: function () {
                        //setTimeout("$('loading').setStyle({display: 'none'})", 500);
                    },
                    onException: function (req, exception) {                     
                        if (req.transport.status >= 200 && req.transport.status < 300) {                     
                            document.documentElement.innerHTML = req.transport.response;
                            funcaoException(req, exception);
                            $('loading').setStyle({display: 'none'});
                        } else {
                            alert("Favor tentar a operacao novamente! Caso o problema persista entre em contato com o administrador.");
                        }
                    }
                });

                $('aguarde').value = "";
    }*/

    // Muda o class de todos os elementos que usem a class "from" para a class "to"
    function changeClass(from, to) {
        var elements = getByClass(from);
        
        if (elements) {
            for (var i = 0; i < elements.length; i++) {
                elements[i].className = to;
            }
        }
    }
    
    function getByClass(matchClass) {
        var elems = document.getElementsByTagName('*');
        var elemsByClass = [];
        
        for (var i = 0; i < elems.length; i++) {
            var currentClass = elems[i].className;
            if (currentClass == matchClass) {
                elemsByClass.push(elems[i]);
            }
        }
        
        return elemsByClass;
    }

	function limparCombo(obj, codigo, descricao) {
		while (obj.childNodes[0]) {
		    obj.removeChild(obj.childNodes[0]);
		}

		if (arguments.length == 3) {
			var opt = document.createElement("OPTION");
			opt.setAttribute('value', codigo);
			opt.innerHTML =  descricao;
			
			obj.appendChild(opt);
		}
	}
	
	function excluirLinha(child, parent)
	{
		for(var i = 0; i < parent.childNodes.length; i++)
		{
						
			parent.removeChild(parent.childNodes[i]);
			
		}
		
	}
	
	function limparErro(obj)
	{
		obj.innerHTML = "";
	}

	function popularCombo(obj, jsonText) {
		var json = eval('(' + jsonText + ')');
		
		limparCombo(obj);
		
		for (var i = 0; i < json.length; i++) {
			var codigo = json[i].codigo;
			var descricao = json[i].descricao;
			
			var opt = document.createElement("OPTION");
			opt.setAttribute('value', codigo);
			opt.innerHTML =  descricao;
			
			obj.appendChild(opt);
		}
	}
	
	function popularComboEstabelecimento(obj, jsonText) {
		var json = eval('(' + jsonText + ')');
		
		limparCombo(obj);
		
		for (var i = 0; i < json.length; i++) {
			var codigo = json[i].codigo;
			var descricao = json[i].nomeFantasia;
			
			var opt = document.createElement("OPTION");
			opt.setAttribute('value', codigo);
			opt.innerHTML =  descricao;
			
			obj.appendChild(opt);
		}
	}
	
	function popularError(jsonText) 
	{
		$('errorAjax').innerHTML =  "&nbsp;&nbsp;" + jsonText + "<br><br>";
	}
	
	function postAjax( url, data, callback, type )
	{
		$.post( url, data, callback, type );
	}
	
	function popularTable(obj, jsonText) {
		
		
		var json = eval('(' + jsonText + ')');
				

		var conteudo = "";
		
		for (var i = 0; i < json.length; i++) {
			
			conteudo += json[i].descricao;			
		}
		obj.innerHTML =  conteudo;
	}
	
	function verificaTamanhoTelefone(event)
    {
    	if (document.form1.tipoContato.value == 1 || document.form1.tipoContato.value == 2
    	    || document.form1.tipoContato.value == 3 || document.form1.tipoContato.value == 4)
    	{
			telefone = document.form1.descricaoContato.value;
			if(telefone.length >= 15)
			{
			    telefone = telefone.substr(0,14);
				document.form1.descricaoContato.value = telefone;
			    return false;
			}
			else
			{
			    return verificaNumero(event);
			}
		}      


    }
    
    function getDiaOuMes(numero)
    {
    	if(numero < 10)
		{
			return '0' + numero;
		}
		return numero;
    }
    
    function getDataFormatada(data)
	{
		var d = data;
		var dia = d.getDate();
		var mes = d.getMonth()+1;
		var ano = d.getFullYear();
					
		var conteudo = getDiaOuMes(dia) + "/" + getDiaOuMes(mes) + "/" + ano;
	
		return conteudo;
	}
	
	// Adds an element from 1 JSON array to another using a matcher Field.
	// If no matcher field is provided the element is copied to all elements
	// in the array.
	//
	// var original = 
    //     [
    //         {'desc':'Joao','status':'A'},
    //         {'desc':'Thiago','status':'A'},
    //         {'desc':'Manuel','status':'B'}
    //      ];
	//
	// var values = 
    //     [
    //         {'status':'A','salary':'100000','age':'28'}
    //     ];
	//
	// concatToJSON(original, values, "salary", "status")
	//
	// The var "original" will be:
	//
	//     [
    //         {'desc':'Joao','status':'A','salary':'100000'},
    //         {'desc':'Thiago','status':'A','salary':'100000'},
    //         {'desc':'Manuel','status':'B'}
    //      ]
    //
    // If the matcher "status" is ommited then:
    // 
    // concatToJSON(original, values, "salary")
    //
    //     [
    //         {'desc':'Joao','status':'A','salary':'100000'},
    //         {'desc':'Thiago','status':'A','salary':'100000'},
    //         {'desc':'Manuel','status':'B','salary':'100000'}
    //      ]
    //
    // Using the same objects to add the "age" element to the previous one, is as simple as:
    // 
    // concatToJSON(original, values, "age")
    //
    //     [
    //         {'desc':'Joao','status':'A','salary':'100000','age':'28'},
    //         {'desc':'Thiago','status':'A','salary':'100000','age':'28'},
    //         {'desc':'Manuel','status':'B','salary':'100000','age':'28'}
    //      ]
	function concatToJSON(jsonObj, jsonNew, addField, matcherField) {
        for (var i = 0; i < jsonObj.length; i++) {
            for (var j = 0; j < jsonNew.length; j++) {
                if (matcherField) {
                    if (jsonObj[i][matcherField] == jsonNew[j][matcherField]) {
                        jsonObj[i][addField] = jsonNew[j][addField];
                    }
                } else {
                    jsonObj[i][addField] = jsonNew[j][addField];
                }
            }
        }       
    }

    // Adds an element called "cor" to each JSON objects in an array
    // using a matcher field. Every other element in the array will have
    // alternate color (1 of two). This is good for listing.
    //
    // Eg.:
    //
    // var original = 
    //     [
    //         {'desc':'Object 1','status':'A'},
    //         {'desc':'Object 2','status':'A'},
    //         {'desc':'Object 3','status':'A'},
    //         {'desc':'Object 4','status':'A'},
    //         {'desc':'Object 5','status':'C'},
    //         {'desc':'Object 6','status':'C'},
    //         {'desc':'Object 7','status':'C'},
    //         {'desc':'Object 8','status':'B'},
    //         {'desc':'Object 9','status':'B'},
    //         {'desc':'Object 10','status':'B'},
    //         {'desc':'Object 11','status':'B'}
    //      ];
    //
    // var cores = 
    //     [
    //         {'status':'A','cor':['green1','green2']},
    //         {'status':'B','cor':['yellow1','yellow2']},
    //         {'status':'C','cor':['red1','red2']}
    //      ];
    //
    // setAlternateColorJSON(original, cores, "status");
    //
    // The var "original" will be:
    //
    //     [
    //         {'desc':'Object 1','status':'A','cor':'green1'},
    //         {'desc':'Object 2','status':'A','cor':'green2'},
    //         {'desc':'Object 3','status':'A','cor':'green1'},
    //         {'desc':'Object 4','status':'A','cor':'green2'},
    //         {'desc':'Object 5','status':'C','cor':'red1'},
    //         {'desc':'Object 6','status':'C','cor':'red2'},
    //         {'desc':'Object 7','status':'C','cor':'red1'},
    //         {'desc':'Object 8','status':'B','cor':'yellow2'},
    //         {'desc':'Object 9','status':'B','cor':'yellow1'},
    //         {'desc':'Object 10','status':'B','cor':'yellow2'},
    //         {'desc':'Object 11','status':'B','cor':'yellow1'}
    //      ];
    function setAlternateColorJSON(jsonObj, jsonColor, matcherField) {
        if (!matcherField) {
            matcherField = "status";
        }
        
        for (var i = 0; i < jsonObj.length; i++) {
            for (var j = 0; j < jsonColor.length; j++) {
                if (jsonObj[i][matcherField] == jsonColor[j][matcherField]) {
                    if ((i % 2) == 0) {
                        jsonObj[i].cor = jsonColor[j].cor[0];
                    } else {
                        jsonObj[i].cor = jsonColor[j].cor[1];
                    }
                }
            }
        }       
    }

    // Adds an element called "cor" to each JSON objects in an array
    // using a matcher field. 
    //
    // Eg.:
    //
    // var original = 
    //     [
    //         {'desc':'Object 1','status':'A'},
    //         {'desc':'Object 2','status':'A'},
    //         {'desc':'Object 3','status':'B'},
    //         {'desc':'Object 4','status':'C'},
    //         {'desc':'Object 5','status':'C'}
    //      ];
    //
    // var cores = 
    //     [
    //         {'status':'A','cor':'green'},
    //         {'status':'B','cor':'yellow'},
    //         {'status':'C','cor':'red'}
    //      ];
    //
    // setColorJSON(original, cores, "status");
    //
    // The var "original" will be:
    //
    //     [
    //         {'desc':'Object 1','status':'A','cor':'green'},
    //         {'desc':'Object 2','status':'A','cor':'green'},
    //         {'desc':'Object 3','status':'B','cor':'yellow'},
    //         {'desc':'Object 4','status':'C','cor':'red'},
    //         {'desc':'Object 5','status':'C','cor':'red'}
    //      ];
    function setColorJSON(jsonObj, jsonColor, matcherField) {
        if (!matcherField) {
            matcherField = "status";
        }
        
        for (var i = 0; i < jsonObj.length; i++) {
            for (var j = 0; j < jsonColor.length; j++) {
                if (jsonObj[i][matcherField] == jsonColor[j][matcherField]) {
                    jsonObj[i].cor = jsonColor[j].cor;
                }
            }
        }       
    }
    
    // Sums up specific elements from a array of JSON objects into
    // a single JSON object called here "accumulator". The accumulator must
    // contain the element named equal as in the array.
    // Eg.:
    // var data = 
    //     [
    //         {'id':'001','v1':'10.2','v2':'1'},
    //         {'id':'002','v1':'10.2','v2':'1'},
    //         {'id':'003','v1':'10.2','v2':'1'},
    //         {'id':'004','v1':'10.2','v2':'1'},
    //         {'id':'005','v1':'10.2','v2':'1'}
    //      ];
    //
    // var accum = {'v1':'0','v2':'0'};
    //
    // sumJSON(accum, data);
    //
    // After this accum will be:
    //
    // {'v1':'51','v2':'5'};
    function sumJSON(jsonAccumulator, jsonDataSet) {
        for (var i = 0; i < jsonDataSet.length; i++) {
            for (var j in jsonAccumulator) {
                jsonAccumulator[j] = parseFloat(jsonAccumulator[j]) + parseFloat(jsonDataSet[i][j]);
            }
        }
    }
    
    // Only allow numeric digits to be typed.
    // Should be used onkeypress="return checkerOnlyNumbers(event)"
    function checkerOnlyNumbers(event){
		
        var v1 = String.fromCharCode(event.keyCode);
        
		alert(v1);

		var v2 = v1.replace(/[^0-9]/g,"")
        alert(v2);
        if (v1 != v2) {
            return false;
        }
    
        return true;
    }
    
    // Formats the number using the formatCurrencyMulti() function handling the keys
    // typed, common keys like alt, ctrl, arrows and scape are handled properly so the
    // user experience is better.
    function formatCurrencyTyping(obj, dec, event, locale) {

        //alert(event.keyCode)

        var key = event.keyCode;

        // Alt Gr, Alt, Ctrl, Capslock, arrows when pressed will simple be ignored
        if (key == 0 || (key >= 18 && key <= 20) || (key >= 37 && key <= 40)) {
            return;
        }

        var str = obj.value;

        str = formatCurrencyMulti(str, dec, locale);

        obj.value = str;
    }

    // Formats double values considering the decimals. Eg.: 10000.3 will be 10,000.30
    // or 10.000,30 depending on the locales and assuming the decimal is 2.
    function formatCurrencyFromDouble(doubleString, dec, locale) {
        var doubleValue = parseFloat(doubleString).toFixed(2);
        //doubleValue = (Math.round(doubleValue * 10)/10).toFixed(2);
        var str = doubleValue.toString();
        var decimals = parseInt(dec);
        var index = str.indexOf(".");
        
        if (index == -1) {
            str = str + "00";
        } else if (index == (str.length - 2)) {
            str = str + "0";
        }
        
        return formatCurrencyMulti(str, decimals, locale);
    }

    // Formats a string number assuming the number will be splitted in chiliad and decimal
    // Eg.: 12345678 will be 123,456.78 or 123.456,78 depending on the locale and assuming
    // Decimals is 2. Any non-numeric character in the string number will be ignored.
    // Eg.: 123.456 will be 1,234.56 or 1.234,56 depending on the locale and assuming decimals is 2.
    function formatCurrencyMulti(stringValue, dec, locale) {
    
        var decimals = parseInt(dec);
        var decDelimChar = ".";
        var milDelimChar = "";
        
        if (!locale) {
            // Global var ERP_LOCALE is defined in the cabecalhoUtil.vm
            locale = ERP_LOCALE;
        }
        
        if (locale == 'es_MX') {
            decDelimChar = ".",
            milDelimChar = ",";
        } else if (locale == 'pt_BR') {
            decDelimChar = ",";
            milDelimChar = ".";
        }
        
        var str = stringValue.toString();
        
        // Removing all non-numeric chars       
        str = str.replace(/[^0-9$]+/g, "");
    
        // Starts formatting only if the amount of chars applies
        if (str.length >= decimals + 1) {
    
            // Removing leading zeros
            if (!/^[0]+$/.test(str)) {          
                str = str.replace(/^0+/g, "");
            }
    
            // If quantitiy of chars applies only for the decimal sign
            // eg: if decimal is 2, then max number of characters that only will receive
            // decimal treatment is 5 like in 12345 -> 123,45
            // If greater than 5 in this case will need to be handled for the chiliad algorithm
            // eg: 123456 -> 1.234,56
            if (str.length <= (decimals + 3)) {
                var cutIndex = str.length;
    
                if (cutIndex <= decimals + 1) {
                    cutIndex = decimals + 1;        
                }
    
                // Handles the cases where the significant number has less digits than the decimal
                // amount. Eg: 1 -> 0,01 or 12 -> 0,12
                // String has leading 0's concatenated, Let's assume from here the number to be formated is 23
                // So, 0000000000000000000023
                str = "00000000000000000000" + str;
                // Number is put in reverse order 3200000000000000000000
                str = str.split("").reverse().join("");
                // Number is cut based on the decimal values that is already calculated and stored in cutIndex var
                // so if decimal is 2 then, the above string should be 320. See if (cutIndex <= decimals + 1) above.
                str = str.substring(0, cutIndex);
                // Putting the decimal sign for the string 320, it will basically put the sign, let's say "," at the position 
                // defined by the decimal amount, let's say the decimal is 2, then 320 -> 32,0
                var re = new RegExp("(\\S{" + decimals + "})");
                str = str.replace(re,"$1" + decDelimChar);
                // To avoid decimal sign at the end of the string we remove them in case they exist. Eg. 12, -> 12
                var re2 = new RegExp("\\" + decDelimChar + "$");
                str = str.replace(re2, "");
                // Now back to the 32,0 example, now the number is reversed back -> 0,23 Now it is formatted correctly based
                // on the decimal number that is 2 for this example.
                str = str.split("").reverse().join("");
            } else {
                // This part handles bigger numbers, it simple separates the ammount that will have the chiliad part from
                // the number that will be the decimals eg: 1234567890.
                // If the decimal ammount is 2 then we know that:
                // 12345678 is the chiliad part and 90 is the decimal.
                var mil = str.substring(0, str.length - decimals);
                var cent = str.substring(str.length - decimals, str.length);
                // Handling the chiliad part similar to the one explained above
                // Firts the number is put in reverse order -> 87654321     
                mil = mil.split("").reverse().join("");
                // Chiliad sign is put for every 3 characters, let's assume chiliad sign is ".", then
                // 876.543.21
                mil = mil.replace(/(\S{3})/g,"$1" + milDelimChar);
                // In case the chiliad sign goes in the final, eg: 333.333.333. the last "." is removed 333.333.333
                var re2 = new RegExp("\\" + milDelimChar + "$");
                mil = mil.replace(re2, "");
                // Back to our example now, the order is set back to the original order
                // 12.345.678
                mil = mil.split("").reverse().join("");
                // Finally we concatenate the chiliad part with the decimal part using the decimal sign, let's assume it is ",", then
                // We'll have it properly fomatted -> 12.345.678,90
                str = mil + decDelimChar + cent;
            }
        }
        
        return str;
    }
    
    
function gerarRelatorio(obj){
		
		document.form1.action = obj;
		document.form1.submit();
	
}


function submitConfirmar(){
	
	 
    jQuery.blockUI({ message: '<img src="images/aguarde2.gif" />',  css: {
		 
        border: 'none', 
        padding: '15px', 
        backgroundColor: '#fff', 
        '-webkit-border-radius': '10px', 
        '-moz-border-radius': '10px', 
        opacity: .5, 
        color: '#fff'
         
    } });
    
    document.form1.submit();
	
}

function getFloatFromCurrency(currencyString) {	
	// Clean the currency, leaving only numbers, dots and commas
	// Eg.1: "R$ 1.234.567,89" -> "1.234.567,89"
	// Eg.2: "US$ 1,234,567.89" -> "1,234,567.89"
	currencyString = currencyString.replace(/[^0-9\.,]+/g, '');
	
	// String to mount the regexp
	var searchFor = '';
	
	// If thousands are represented by a dot, we need a \ backslash added
	// The thousandsSign global variable is defined in the cabecalhoUtil.vm based on locale
	if (thousandsSign == '.') {
		searchFor = searchFor.concat('\\');
	}
	
	searchFor = searchFor.concat(thousandsSign);
	
	// Removing all thousands signs
	var reg = new RegExp(searchFor, "g");
	currencyString = currencyString.replace(reg, '');
	
	// If decimal sign is a comma, we need to ensure it is a dot
	// If we assume "R$ 1.234.567,89" as initial input.
	// At this point we should have something like 1234567,89
	// After fixing decimals we would have "1234567.89". Now it is ready to be converted to float.
	currencyString = currencyString.replace(/,/g, '.');
	
	return parseFloat(currencyString);
}

// This function replaces the USAtoBrazil function with currency handling based on app configuration pt_BR or es_MX.
function formatFloatWithMaskMoney(currencyFloat) {
	$("#kashMaskMoney").val(currencyFloat);
    $("#kashMaskMoney").maskMoney('mask');
	var precoVenda = $("#kashMaskMoney").val();
	$("#kashMaskMoney").val("");
	
	return precoVenda;
}

// This function handles the Message Resources like messages replacing the string place holders {x}. Being able to use
// whatever messsage that uses that systanx in the ErpMessageResources*.properties file.
//
// Example:
// var result = parseMessageResource("Hi, my name is {0}. Nice to meet you {1}!", ['Thiago', 'Vesley']);
// result variable will be "Hi, my name is Thiago. Nice to meet you Vesley!"
function parseMessageResource(str, arr) {
	return str.replace(/{(\d+)}/g, function(_,m) {
		return arr[m--];
	});
}
    
function moed2a(valor, casas, separdor_decimal, separador_milhar){
	/*
	var casas = 2;
	var separador_decimal = ',';
	var separador_milhar = '.';
	
	var valor_total = parseInt(valor * (Math.pow(10,casas)));
	var inteiros =  parseInt(parseInt(valor * (Math.pow(10,casas))) / parseFloat(Math.pow(10,casas)));
	var centavos = parseInt(parseInt(valor * (Math.pow(10,casas))) % parseFloat(Math.pow(10,casas)));


	if(centavos%10 == 0 && centavos+"".length<2 ){
	centavos = centavos+"0";
	}else if(centavos<10){
	centavos = "0"+centavos;
	}

	var milhares = parseInt(inteiros/1000);
	inteiros = inteiros % 1000;
	
	

	var retorno = "";

	if(milhares>0){
		retorno = milhares+""+separador_milhar+""+retorno
	
		if(inteiros == 0){
		inteiros = "000";
		} else if(inteiros < 10){
		inteiros = "00"+inteiros;
		} else if(inteiros < 100){
		inteiros = "0"+inteiros;
		}
	}
	retorno += inteiros+""+separdor_decimal+""+centavos;
	 */
	var retorno = "" + valor;
	
	var split = retorno.split(".");
	
	var centavos = split[1];
	var inteiro = split[0];
	
	retorno = inteiro + "," + centavos;
	
	return retorno;
}
function moeda(valor, casas, separdor_decimal, separador_milhar){

	var retorno = "" + valor;
	
	var split[] = retorno.split(".");
	
	var centavos = split[1];
	var inteiro = split[0];
	
	retorno = inteiro + "," + centavos;
	
	return retorno;
}

function formatarDataDDMMYYYY(dateObject) {
    
	var d = new Date(dateObject);
    var day = d.getDate();
    var month = d.getMonth() + 1;
    var year = d.getFullYear();
    if (day < 10) {
        day = "0" + day;
    }
    if (month < 10) {
        month = "0" + month;
    }
    var date = day + "/" + month + "/" + year;

    return date;
}


    