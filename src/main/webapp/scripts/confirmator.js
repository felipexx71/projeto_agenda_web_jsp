/**
 * confirmacao de exlcusao de um contato
 * @author Luiz Felipe Nunes 
 */

 function confirmar (idcon) {
	 let resposta = confirm("Confirmar exclusão desse contato?")
	 
	 if (resposta === true) {
		 window.location.href = "delete?idcon=" + idcon
	 }
 }