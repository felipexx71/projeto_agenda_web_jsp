/**
 * @author Luiz Cavalcante
 */

 function validate() {
	let name = frmContact.nome.value
	let phone = frmContact.telefone.value
	let email = frmContact.email.value
	
	if (name === "") {
		alert('Campo nome vazio!')
		frmContact.nome.focus()
		return false
	} else if (phone === "") {
		alert('Campo telefone vazio!')
		frmContact.telefone.focus()
		return false
	} else if (email === "") {
		alert('Campo email vazio!')
		frmContact.email.focus()
		return false
	} else {
		document.forms["frmContact"].submit()
	}
 }