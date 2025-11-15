
# Distribuidora do Zeh

Aplicativo Android para controle de estoque de uma distribuidora de bebidas fictícia, desenvolvido em **Kotlin** com **Jetpack Compose**, **Navigation Compose**, **Room** e arquitetura **MVVM**, como projeto final da disciplina de Desenvolvimento de Aplicativos Móveis.

---

## Objetivo do Projeto

O objetivo do aplicativo **Distribuidora do Zeh** é permitir o gerenciamento simples e eficiente do estoque de uma distribuidora de bebidas, oferecendo:

- Cadastro de bebidas por categoria.
- Controle de **entradas** e **saídas** de estoque.
- Visualização de **relatórios** de estoque e movimentações.
- Interface intuitiva e fluida utilizando Jetpack Compose.

O projeto foi desenvolvido para atender aos requisitos da disciplina, incluindo:

- Arquitetura **MVVM**.
- Persistência de dados com **Room**.
- **Navegação entre múltiplas telas** usando Navigation Compose.
- Boas práticas de organização de código e UX.

---

Integrantes

- Andre Luiz Ferreira - 40305937
- Conrado Rezende - 40421074
- Diogo Henrique da Silva - 40720438
- Tiago Reginato Koligowski - 40316106

### Contribuições

- Andre Luiz – Desenvolveu as telas: inicial, cadastro de bebidas, persistência de dados com ROOM, banco de dados e documentação;
- Conrado Rezende – Desenvolveu as telas de relatório de estoque, sistema de top vendas e documentação;
- Diogo Henrique – Desenvolvimento da tela de movimentações e da documentação / diagramas;
- Tiago Reginato – Desenvolvimento da tela de listagem de bebidas, sistema edição e exclusão de bebidas, auxilio na tela de movimentações e documentação;

---

## Tecnologias Utilizadas

- **Linguagem:** Kotlin  
- **IDE:** Android Studio  
- **UI:** Jetpack Compose + Material Design 3  
- **Navegação:** Navigation Compose  
- **Persistência:** Room (SQLite)  
- **Arquitetura:** MVVM (Model–View–ViewModel)  
- **Outras bibliotecas:**
  - AndroidX Lifecycle (ViewModel, LiveData/StateFlow, etc.)

---

## Arquitetura do Projeto (MVVM)

O projeto segue o padrão **MVVM**, separando claramente:

- **Camada de Dados (Model)**
  - Entidades Room (por exemplo: `Categoria`, `Bebida`, `Movimentacao`).
  - DAOs para acesso ao banco.
  - Classe do banco de dados (`RoomDatabase`).
  - Repositório para encapsular o acesso aos dados.

- **Camada de ViewModel**
  - `BebidaViewModel`
  - `MovimentacaoViewModel`
  - `RelatorioViewModel`
  
  Os ViewModels expõem estados e eventos para a UI, consumindo o repositório e aplicando regras de negócio.

---

## Descrição das entidades

> ### **Categoria**
>
>  - Representa o tipo ou grupo ao qual uma bebida pertence (ex.: cerveja, vinho, refrigerante).
>  É uma tabela simples usada como referência para organizar as bebidas.
>
>  - **Campos:**
>
>     - id (Int, PK, auto-increment) — Identificador único da categoria.
>     - nome (String) — Nome da categoria.
>     
> - **Observações:**
>
>  - É referenciada pela entidade Bebida através do campo categoriaId.
>  - Exclusão em cascata: ao remover uma categoria, suas bebidas associadas também são removidas.

---

> ### **Bebida**
>
> - É a entidade principal do sistema. Representa um item de estoque com informações detalhadas de preço, volume e quantidade.
>
> - **Campos:**
>
>    - id (Int, PK, auto-increment) — Identificador único da bebida.
>    - categoriaId (Int, FK → Categoria.id) — Categoria da bebida.
>    - nome (String) — Nome da bebida.
>    - volume (String) — Medida de volume da bebida (ex.: “600ml”, “1L”).
>    - quantidadeEstoque (Int) — Quantidade atual no estoque.
>    - precoCompra (Double) — Preço de compra da unidade.
>    - precoVenda (Double) — Preço de venda da unidade.
>
> - **Observações:**
>  - Possui relacionamento muitos-para-um com Categoria.
>  - Sofre impactos diretos das Movimentações, que alteram sua quantidade em estoque.
>  - Possui índices e chave estrangeira para otimizar consultas e manter integridade referencial.
>

---

> ### **Movimentacao**
>
> - Registra entradas e saídas de estoque, permitindo acompanhar o histórico e atualizar automaticamente as quantidades da Bebida.
>
> - **Campos:**
>
>    - id (Int, PK, auto-increment) — Identificador único da movimentação.
>    - bebidaId (Int, FK → Bebida.id) — Bebida afetada pela movimentação.
>    - tipo (String) — Tipo da movimentação ("Entrada" ou "Saída").
>    - quantidade (Int) — Quantidade movimentada.
>    - data (String) — Data da movimentação (registrada pelo sistema).
>    - observacao (String) — Observação opcional (ex.: fornecedor, motivo, lote).
>
> - **Observações:**
>
>  - Cada movimentação impacta diretamente o estoque da bebida correspondente.
>  - Relacionamento muitos-para-um com Bebida.
>  - Exclusão em cascata: se a bebida for removida, suas movimentações também são excluídas.

---

> - **Resumo do Relacionamento Entre Entidades**
> 
>   -Categoria (1) → (N) Bebida
>   
>   -Bebida (1) → (N) Movimentacao
> 
> Ou seja:
> Uma categoria pode ter várias bebidas.
> Uma bebida pode ter várias movimentações.

---

## Diagrama do Banco de Dados (ERD)

A estrutura do banco de dados do aplicativo utiliza três entidades principais, com relacionamentos 1:N.  
O diagrama abaixo representa visualmente a modelagem:

<img width="1007" height="144" alt="image" src="https://github.com/user-attachments/assets/4d0b3345-638c-453b-9212-422c61ddca44" />

---

## Principais Funcionalidades

**Gerenciamento de Bebidas**
  - Cadastro de novas bebidas com nome, categoria, volume, preços e quantidade em estoque.
  - Edição completa de dados já cadastrados.
  - Exclusão de bebidas, mantendo a integridade das movimentações associadas.
  - Listagem completa com ordenação por nome e destaque para produtos com baixo estoque.
  - Busca por nome da bebida.

**Categorias**
  - Cadastro e gerenciamento de categorias.
  - Associação direta entre cada bebida e sua categoria.
  - Remoção de categorias com exclusão em cascata das bebidas relacionadas.

**Controle de Movimentações (Entrada/Saída de Estoque)**
  - Registro de entradas (compra/reabastecimento) e saídas (vendas/uso interno).
  - Atualização automática do estoque da bebida correspondente.
  - Histórico completo de movimentações por bebida.
  - Tela dedicada para adicionar novas movimentações diretamente a partir da MainScreen.
  - Ver movimentações detalhadas a partir da tela de detalhes da bebida.

**Relatórios de Estoque**
  - Cálculo do valor total de compra do estoque atual.
  - Cálculo do valor total de venda e lucro potencial.
  - Contagem de itens únicos cadastrados.
  - Total geral de unidades disponíveis no estoque.
  - Visão geral consolidada da situação atual do inventário.

**Navegação Intuitiva (Navigation Compose)**
  - Navegação organizada entre telas sem Activities adicionais.
  - Tela inicial (MainScreen) com acesso rápido às 3 principais áreas:
  - Lista de Bebidas
  - Adicionar Movimentação
  - Relatórios
  - Rotas bem definidas e fluxo consistente de retorno (backstack compatível com comportamento Android).

**Persistência de Dados com Room**
  - Armazenamento local de todas as bebidas, categorias e movimentações.
  - Banco de dados estruturado com três entidades e relacionamentos.
  - Operações completas de CRUD.
  - Consultas avançadas, incluindo:
  - listar estoques baixos
  - filtrar por categoria
  - buscar por nome
  - listar movimentações por bebida

**Interface Moderna com Jetpack Compose**
  - Componentes modernos e responsivos.
  - Uso de LazyColumn para listagens grandes.
  - Campos de entrada validados e organizados.
  - Botões e ícones padronizados.
  - Layouts limpos, intuitivos e adequados para uso em dispositivos móveis.

---

## CRUD e Buscas Específicas

-   O aplicativo Distribuidora do Zeh implementa de forma completa as operações de CRUD utilizando o Room para persistência local dos dados.

- **Criar**
  - O usuário pode cadastrar novas bebidas informando nome, categoria, estoque inicial e preço. O registro é salvo no banco via insert() do DAO.

- **Ler**
  - A listagem de bebidas é exibida com LazyColumn, carregando os dados diretamente do Room.
  - Também é possível visualizar os detalhes da bebida e o histórico de movimentações (entradas e saídas).

- **Atualizar**
  - Na tela de edição, o usuário pode modificar as informações da bebida. O Room atualiza o registro via update().

- **Excluir**
  - A bebida pode ser removida pela tela de detalhes/edição através do método delete() do DAO.

- **Buscas Específicas**
  - O projeto implementa as buscas exigidas pela disciplina:
  - Busca por nome da bebida, utilizando consultas SQL com LIKE para filtrar a lista.
  - Filtro por categoria, permitindo refinar os resultados por tipo de bebida.
  - Consultas específicas para relatórios, como estoque baixo, movimentações registradas e bebidas mais vendidas.
  - Essas buscas são executadas através de funções personalizadas no BebidaDao e MovimentacaoDao.

---

## Diagrama de Navegação Entre Telas

O diagrama a seguir representa o fluxo de navegação do aplicativo:

<img width="1101" height="537" alt="image" src="https://github.com/user-attachments/assets/aa97adbd-7034-46de-a0c3-e898a8eb9d50" />

---

## Justificativa para Não Utilizar Comunicação com API

- O projeto não implementa comunicação com API externa porque, após análise dos requisitos funcionais e do escopo do aplicativo, constatou-se que a natureza do sistema — controle interno de estoque — é mais adequadamente atendida com persistência local, sem depender de conectividade ou serviços remotos.

---

## Razões Técnicas e Funcionais
  - Operação 100% Offline
  - O aplicativo deve funcionar plenamente mesmo sem conexão à internet, visto que:
  - é destinado ao uso em pequenos comércios ou estoques privados;
  - movimentações e consultas precisam estar sempre disponíveis;
  - inconsistências de rede poderiam comprometer a confiabilidade do registro de estoque.
  - O uso de API adicionaria uma dependência externa desnecessária e poderia prejudicar a experiência do usuário.

---

## Como Executar o Projeto

  **Siga os passos abaixo para rodar o aplicativo Distribuidora do Zeh no Android Studio:**

  - Clone o repositório
  - Abra o projeto no Android Studio:
  - Abra o Android Studio
  - Vá em File -> Open
  - Selecione a pasta do projeto
  - Aguarde o Gradle sincronizar automaticamente
  - Configure um dispositivo para execução:
  - Utilize um emulador criado no AVD Manager
  - ou
  - Conecte um dispositivo físico com Depuração USB ativada
  - Execute o aplicativo:
  - Selecione o dispositivo no topo do Android Studio
  - Clique no botão Run
  - O app será instalado e iniciado automaticamente

  ---

## Camada de UI (View)
  - Telas criadas com Jetpack Compose (`@Composable`), como:
    - Tela principal (menu)
    - Lista de bebidas
    - Detalhes da bebida
    - Cadastro/edição de bebida
    - Tela de movimentações
    - Tela de relatórios

- **Navegação**
  - Implementada com **Navigation Compose**, utilizando um `NavHost` e uma sealed class de rotas (`Screen`).

  ---

## Como o Projeto Atende aos Requisitos da Disciplina

- **1. Navegação Entre Activities / Telas**

  O projeto utiliza Navigation Compose para gerenciar múltiplas telas, incluindo:
  
    -   Tela inicial (menu)
    -   Lista de bebidas
    -   Detalhes da bebida
    -   Cadastro/edição
    -   Movimentações
    -   Relatórios
    -   Assim, atende ao requisito de possuir três ou mais telas com navegação estruturada.

- **2. Persistência de Dados com Room**

  O aplicativo implementa completamente o Room, com:
  
    -   Três entidades: Categoria, Bebida e Movimentacao
    -   DAOs para cada entidade
    -   Classe AppDatabase
    -   Operações de CRUD para bebidas
    -   Registro e consulta de movimentações
    -   Buscas específicas (por nome e categoria)
    -   Atende integralmente aos requisitos de persistência e operações CRUD.

- **3. Arquitetura MVVM**

   O projeto segue a arquitetura recomendadaa pela disciplina:
    
    - Camada Model (Room: entidades, DAOs, database)
    - Camada ViewModel (gerencia estado e lógica)
    - Camada View (Jetpack Compose)
    - A separação de responsabilidades está clara e bem estruturada.

- **4. Interface do Usuário (UI/UX)**
  
   A interface utiliza:
  
    - Jetpack Compose
    - LazyColumn para listagens
    - TextField / OutlinedTextField
    - Buttons e elementos responsivos
    - Atende aos padrões de boas práticas e aos requisitos mínimos.

- **5. Documentação**
  
   O README inclui:
    - Explicação do projeto
    - Tecnologias utilizadas
    - Arquitetura MVVM
    - Instruções de execução
    - Diagrama do banco (a ser inserido)
    - Diagrama de navegação (a ser inserido)
    - Contribuições da equipe
