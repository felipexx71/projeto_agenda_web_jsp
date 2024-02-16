# Projeto de Agenda Web
Este é um projeto de Agenda Web desenvolvido utilizando Java Server Pages (JSP), MySQL como banco de dados e o servidor Tomcat na versão 9.0. O projeto foi desenvolvido na IDE Eclipse.

## Pré-requisitos
Antes de começar, certifique-se de ter as seguintes ferramentas instaladas:

- Java Development Kit (JDK) - versão 8 ou superior
- MySQL Server - para armazenar os dados da agenda
- Apache Tomcat - versão 9.0
- Eclipse IDE - ou qualquer IDE de sua preferência compatível com desenvolvimento Java Web
- Configuração do Banco de Dados
- Crie um banco de dados no MySQL para a agenda:

## SQL
CREATE DATABASE agenda_db;

USE agenda_db;

Crie uma tabela para armazenar os contatos:

CREATE TABLE contatos (

    id INT AUTO_INCREMENT PRIMARY KEY,
    
    nome VARCHAR(50) NOT NULL,
    
    telefone VARCHAR(15) NOT NULL,
    
    email VARCHAR(50) NOT NULL
);

Crie a tabela de acordo com os atributos das classes dentro do pacote model.

## Configuração do Projeto no Eclipse

### BASH
Clone o repositório:

git clone https://github.com/felipexx71/projeto_agenda_web_jsp.git

Abra o Eclipse e importe o projeto:

File > Import > Existing Projects into Workspace

Configure o Tomcat no Eclipse:

Adicione o Tomcat ao Eclipse em Window > Preferences > Server > Runtime Environments

Configure o projeto para utilizar o Tomcat:

Botão direito no projeto > Properties > Targeted Runtimes > Selecione o Tomcat configurado

Executando o Projeto

Certifique-se de que o servidor Tomcat está configurado corretamente.

Inicie o servidor Tomcat no Eclipse.

## Acesse a aplicação no navegador:
[http://localhost:8080/agenda1/main](http://localhost:8080/agenda1/index.html)

Agora, você deve ter o projeto de Agenda Web em execução localmente.

# Imagem da página principal do projeto

![image](https://github.com/felipexx71/projeto_agenda_web_jsp/assets/105807936/d2d24a35-19e5-41d3-8bc5-521b74fa9db0)

