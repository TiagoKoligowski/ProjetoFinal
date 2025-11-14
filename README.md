# Distribuidora do Zeh

Aplicativo Android para controle de estoque de uma distribuidora de bebidas fictícia, desenvolvido em **Kotlin** com **Jetpack Compose**, **Navigation Compose**, **Room** e arquitetura **MVVM**, como projeto final da disciplina de Desenvolvimento de Aplicativos Móveis.

---

## 📌 Objetivo do Projeto

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

- Andre Luiz Ferreira - RGM
- Conrado Rezende - RGM
- Diogo Henrique da Silva - RGM
- Tiago Reginato Koligowski - RGM

### Contribuições

- Andre Luiz – Desenvolveu as telas: inicial, cadastro de bebidas, persistência de dados com ROOM e banco de dados;
- Conrado Rezende – Desenvolveu as telas de relatório de estoque e sistema de top vendas;
- Diogo Henrique – Desenvolvimento da tela de movimentações e da documentação / diagramas;
- Tiago Reginato – Desenvolvimento da tela de listagem de bebidas, edição de bebidas e documentação;

---

## 🧱 Tecnologias Utilizadas

- **Linguagem:** Kotlin  
- **IDE:** Android Studio  
- **UI:** Jetpack Compose + Material Design 3  
- **Navegação:** Navigation Compose  
- **Persistência:** Room (SQLite)  
- **Arquitetura:** MVVM (Model–View–ViewModel)  
- **Outras bibliotecas:**
  - AndroidX Lifecycle (ViewModel, LiveData/StateFlow, etc.)

---

## 🏗 Arquitetura do Projeto (MVVM)

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

- **Camada de UI (View)**
  - Telas criadas com Jetpack Compose (`@Composable`), como:
    - Tela principal (menu)
    - Lista de bebidas
    - Detalhes da bebida
    - Cadastro/edição de bebida
    - Tela de movimentações
    - Tela de relatórios

- **Navegação**
  - Implementada com **Navigation Compose**, utilizando um `NavHost` e uma sealed class de rotas (`Screen`).

Fluxo resumido:

```text
UI (Compose) → ViewModel → Repository → DAO → Room Database
