

![WhatsApp Image 2024-11-25 at 10 52 07 PM (1)](https://github.com/user-attachments/assets/c1f6cf59-9475-402c-b19a-b9d4cd2416c1)


===================================================================================================================================================================================================================

#🍽️ RANGO EXPRESS
Bem-vindo ao Rango Express, seu sistema para facilitar o gerenciamento de pedidos de marmitas!

Com este guia, vou te ensinar, passo a passo, como baixar, configurar e rodar o Rango Express. Seja você iniciante ou expert!
O Rango Express é um sistema web para facilitar o gerenciamento de pedidos de marmitas! Com ele, você pode:
✅ Cadastrar usuários e pedidos.
✅ Gerenciar rotas de entrega.

Seja para uso pessoal ou comercial.

===================================================================================================================================================================================================================
  
#🎯 OO QUE VOCÊ VAI APRENDER AQUI?
Como instalar a aplicação na sua máquina.
Quais ferramentas você precisa para tudo funcionar.
Como configurar Angular e PrimeNG corretamente.
Como fazer um fork e colaborar com o projeto.

===================================================================================================================================================================================================================  
#🚀 iNSTALANDO O RANGO EXPRESS
📋 Pré-requisitos
Antes de começar, certifique-se de ter estas ferramentas instaladas:

Java JDK 17 – Necessário para rodar o backend.
Node.js e npm – Para gerenciar o frontend.
Git – Para clonar o projeto e colaborar.
PostgreSQL – Banco de dados para armazenar os dados.
IntelliJ IDEA – (ou sua IDE favorita).

===================================================================================================================================================================================================================
    
#🔧 PASSO A PASSO PARA CONFIGURAR E INSTALAR.
1º Passo: Clonando o repositório
Abra o terminal e execute os comandos:

    git clone https://github.com/Iot-Health-Br/SpringBoot_RangoExpress.git
 cd SpringBoot_RangoExpress
 
2º Passo: Configurando o Angular CLI
O frontend do Rango Express usa Angular, então precisamos configurar o ambiente:

Instale o Angular CLI (a ferramenta oficial):

    npm install -g @angular/cli

Nota: Caso esteja usando a versão 17 do Angular:

    npm install -g @angular/cli@17
    
Crie o projeto Angular (se necessário):

    ng new NOME-DO-PROJETO
    
3º Passo: Instalando PrimeNG
O PrimeNG é uma biblioteca essencial para estilização. Vamos verificar e instalar:

Verifique se já está instalado:

    npm ls primeng
    
Caso não esteja, instale:

    npm install primeng
    
Configure o arquivo angular.json e adicione as fontes de estilização necessárias para o PrimeNG.

4º Passo: Instalando PrimeIcons
Os PrimeIcons são ícones usados no projeto. Configure-os assim:
Verifique se estão instalados:

    npm ls primeicons
    
Caso não estejam, instale:

    npm install primeicons
No arquivo styles.css, importe as bibliotecas de estilização necessárias para os ícones.

5º Passo: Configurando o backend e o banco de dados
Navegue para a pasta do backend:

    cd backend
    
Compile o projeto:

    ./mvnw clean install
Configure o banco de dados:

Crie um banco chamado rango_express no PostgreSQL.
Atualize o arquivo application.properties com as credenciais do banco.
Inicie o backend:

    ./mvnw spring-boot:run
    
6º Passo: Iniciando o frontend
Navegue até a pasta do frontend:

    cd frontend
Instale as dependências:

    npm install
    
Inicie o servidor:

    npm start
Pronto! 🎉 Acesse a aplicação em http://localhost:4200.

===================================================================================================================================================================================================================

#🍴 COMO FAZER O FORK
Faça o fork do repositório:
Na página do projeto no GitHub, clique em Fork.

Clone o repositório forkado:

    git clone https://github.com/seu-usuario/SpringBoot_RangoExpress.git
    cd SpringBoot_RangoExpress
    
Crie uma branch para sua feature:

    git checkout -b minha-nova-feature
    
Faça suas alterações e commit:

    git add .
    git commit -m "Descrição das mudanças"
    
Envie suas mudanças para o repositório:

    git push origin minha-nova-feature
Abra um Pull Request:
Vá até o repositório original e clique em New Pull Request.

===================================================================================================================================================================================================================

#🛠️ Ferramentas usadas no projeto
Backend:

💻 Spring Boot – Framework para APIs. 
🔒 Spring Security – Autenticação.
Frontend:

🖼️ Angular – Interface moderna.
🎨 PrimeNG – Componentes visuais.
Banco de Dados:

🛢️ H2 – Banco relacional.
Versionamento:

🧰 Git – Controle de versões.

===================================================================================================================================================================================================================

### 🛠️ Construído com

   Ferramentas usadas para criar o projeto.

   * Programa criado usando a ferrameta [Intelij] - https://www.jetbrains.com/pt-br/idea/ - IDE usada
   * Programa criado usando o framework [Spring Boot] - https://spring.io/ - Framework usado
   * Programa criado usando a ferrameta [GIT] - https://git-scm.com/ - Versionamento
   * Programa criado usando o banco de dados [H2] -  - Banco de Dados usado
===================================================================================================================================================================================================================
   
# ✨ Autores

   * **Desenvolvedor** - *Trabalho Inicial* / *Documentação* - [Igor Leonor Macedo](https://github.com/Iot-Health-Br)
   * **Desenvolvedor** - *Trabalho Inicial* / *Documentação* - [Luidy Tavares](https://github.com/LuidyTT)
   * **Desenvolvedor** - *Trabalho Inicial* / *Documentação* - [Kauan Farias Lima](https://github.com/lKauanF)
   * 
===================================================================================================================================================================================================================

#🎁 Agradecimentos
Obrigado por conferir este projeto! 💖
Se achou útil:

Deixe uma ⭐ no repositório.
Compartilhe 🍺 com seus amigos.

===================================================================================================================================================================================================================

  
 
