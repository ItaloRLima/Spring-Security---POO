# Spring-Security---POO

Após baixar o repositório, abra a pasta no programa Intellij IDEA, baixe as dependências do maven e execute apartir do arquivo "LpooApplication.java".

Dentro da pasta, procure pelo arquivo "application.properties"(ele se encontra no caminho src->main->resources), nesse arquivo são inseridos os dados para conexão do banco de dados com a aplicação JAVA. Procure pelas seguintes propriedades, nelas são inseridas a URL com o nome do esquema de banco de dados, o username cadastrado, juntamente com a senha.
```
...
spring.datasource.url=[...]
spring.datasource.username=
spring.datasource.password=
...
```

No campo "spring.datasource.url=[...]", substitua o campo "[...]", pelo link do banco de dados. Geralmente, esse link está no seguinte padrão.
```
jdbc:BANCO_DE_DADOS://localhost:PORTA_PADRÃO/NOME_ESQUEMA
```
Onde:
-BANCO_DE_DADOS: faz referência ao gerenciador de banco de dado sendo utilizado.
-PORTA_PADRÃO: faz referência à porta definida ao criar o esquema ou banco de dados.
-NOME_ESQUEMA: faz referência ao nome do esquema ou do banco de dados.

Para finalizar, o acesso ao aplicativo será dado pelo link:
```
http://localhost:8080/
```
