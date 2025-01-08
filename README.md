Esse projeto é um encurtador de URLs! Utilizando Java e ferramentas da AWS, como AWS Lambda, Amazon S3 e API Gateway, criei uma solução escalável e eficiente para gerar e gerenciar links curtos.

O projeto se baseou em: 
- ✅ AWS Lambda para criar uma arquitetura serverless, garantindo que o sistema pudesse escalar automaticamente conforme o uso;
- ✅ Amazon S3 para armazenar de forma segura os dados e recursos relacionados aos links encurtados;
- ✅ API Gateway para expor a API do encurtador de URLs de maneira segura e otimizada, permitindo acesso fácil e integração com outros sistemas;
- ✅ A integração com Java para construir uma lógica robusta e de alto desempenho.
Essa experiência foi muito boa porque me permitiu mergulhar no mundo das arquiteturas serverless e no uso de serviços em nuvem para criar soluções modernas e práticas.
Além disso, foi uma oportunidade de aprimorar minhas habilidades em desenvolvimento backend e cloud computing.

Este repositório contém duas funções lambdas, uma para criar o link encurtado e outra para o redirecionamento para o link original.
Para a lambda de criar o link encurtado, foi usado a seguinte lógica:
- Conectamos ao S3 da Amazon
- Recebemos uma request, extraimos o conteúdo do body e armazenamos isso em um Map
- Fazemos a conversão do body para o Map usando o ObjectMapper, ficando devidamente armazenado em Chave Valor
- Geramos um código UUID limitado até 8 caracteres para não ser muito extenso
- Criamos um objeto que representa o link encurtado contendo a url original e o tempo de expiração do encurtador
- Usando o ObjectMapper novamente, dessa vez usamos para transformar o objeto que representa o link encurtado em uma String
- Criamos uma request de inserir objetos em um Bucket usando o código UUID gerado como key para esse objeto
- Usamos a conexão do S3 para persistir esse objeto no bucket
- E no fim retornamos um Map que contém uma chave "Code" com o valor do código UUID

Para a lambda de redirecionamento, o processo é:
- Conectamos ao S3 da Amazon
- Recebemos uma request e extraimos dela o código UUID usado para representar um link encurtado
- Criamos uma request para obtermos um objeto lá do S3, informamos o bucket e a key do objeto é o código UUID
- Tentamos obter esse objeto, caso não consiga lançamos uma excessão
- Após obter esse objeto, convertemos ele em uma classe Java usando o ObjectMapper, caso não consiga converter lançamos uma excessão
- Após a conversão, fazemos uma validação para ver se o link não está expirado
- Caso não esteja expirado, criamos um Map onde informamos o statusCode: 302. Criamos outro map que será os headers, colocamos uma chave "Location" com o valor sendo a url original do site e inserimos esse Map no outro
- Retornamos esse Map
- Caso esteja expirado, criamos uma Map com o objeto statusCode: 410 e o body informando que o link estava expirado.

>[OBS]
>Conseguimos capturar essas request porque implementamos a interface RequestHandler da AWS
>Sobreescrevemos o método requisitado e retornamos nossas responses em Map pois assim é solicitado
>Para conseguir implementar essa interface, necessário dependencias da AmazonSDK, AWS Lambda Java Core, AWS Lambda Java Log4j2
>Usamos a biblioteca Jackson também para a conversão de objetos Java  
