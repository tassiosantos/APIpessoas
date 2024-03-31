@EnderecoTest
Feature: Teste das funcionalidades do Endereco

Scenario: Adicionar um endereço para uma pessoa
  Given a existencia de uma pessoa com os seguintes dados
    | nome          | João       |
    | sobrenome     | Silva      |
    | dataNascimento| 1990-01-01 |
  And a inserção de um endereço com os seguintes dados
    | numero       |   45       |
    | logradouro   | Rua A      |
    | cidade       | São Paulo  |
    | estado       | SP         |
    | cep          | 01000-000  |
  When o endpoint /enderecos/adicionar/{id} é chamado
  Then esse endereco deve ser adicionado a essa pessoa

Scenario: Editar um endereço de uma pessoa
  Given a existencia de alguma pessoa com os seguintes dados
    | nome          | João       |
    | sobrenome     | Silva      |
    | dataNascimento| 1990-01-01 |
  And a existência de um endereço dessa pessoa com as informações
    | numero       |   45       |
    | logradouro   | Rua A      |
    | cidade       | São Paulo  |
    | estado       | SP         |
    | cep          | 01000-000  |
  And a edição do endereço dessa pessoa com os seguintes dados
    | numero       |   48       |
    | logradouro   | Rua C      |
    | cidade       | São Paulo  |
    | estado       | SP         |
    | cep          | 01000-100  |
  When o endpoint enderecos/editar/id é chamado
  Then o endereço dessa pessoa deve ser editado com sucesso

Scenario: Buscar um endereço de uma pessoa
  Given o usuário deseja buscar um endereço de uma pessoa pelo id da pessoa e do endereço
  | nome          | Maria      |
  | sobrenome     | Oliveira   |
  | dataNascimento| 1988-03-23 |
  When o endpoint /enderecos/buscar/getall/id é chamado
  Then os endereços dessa pessoa devem ser buscados




