@ApplicationTest
Feature: Teste das funcionalidades da Pessoa

  Scenario: Adicionar uma pessoa com sucesso
    Given a inserção de uma pessoa com os seguintes dados
    |nome|João|
    |sobrenome|Silva|
    |dataNascimento|1990-01-01|
    When o endpoint pessoas/adicionar é chamado
    Then essa pessoa deve ser adicionada

  Scenario: Editar uma pessoa com sucesso
    Given a inserção de uma pessoa que será editada com os seguintes dados
    |nome|João|
    |sobrenome|Silva|
    |dataNascimento|1990-01-01|
    And a edição dessa pessoa com os seguintes dados
    |nome|João|
    |sobrenome|Santos|
    |dataNascimento|1990-05-11|
    When o endpoint pessoas/editar/id é chamado
    Then essa pessoa deve ser editada

  Scenario: Buscar uma pessoa
    Given o usuário busca uma pessoa pelo id
    | nome     |Maria   |
    | sobrenome|Oliveira|
    | dataNascimento | 1988-03-23 |
    When o endpoint /pessoas/id é chamado
    Then essa pessoa deve ser buscada


  Scenario: Buscar várias pessoas
    Given o usuário deseja buscar uma lista de pessoas
    | nome         | sobrenome    | dataNascimento|
    | João         | Silva        | 1990-01-01    |
    | Maria        | Oliveira     | 1992-02-12    |
    | Carlos       | Andrade      | 1988-03-23    |
    | Fernanda     | Costa        | 1995-04-04    |
    | Lucas        | Moraes       | 1993-05-15    |
    When o endpoint /pessoas/getall é chamado
    Then a lista de pessoas adicionadas deve ser chamada

Scenario: Buscar um endereço de uma pessoa
  Given o usuário deseja buscar um endereço de uma pessoa pelo id da pessoa e do endereço
  When o endpoint enderecos/buscar/id é chamado
  Then o endereço dessa pessoa deve ser buscado



Scenario: Buscar os endereços de uma pessoa
  Given o usuário deseja buscar os endereços de uma pessoa pelo id da pessoa
  When o endpoint enderecos/buscar/getall/id é chamado
  Then os endereços dessa pessoa devem ser buscados


Scenario: Determinar um endereço como principal para uma pessoa
  Given o usuário definir um endereço de uma pessoa como principal
  When o endpoint enderecos/principal/id/pessoaId é chamado
  Then o endereço principal dessa pessoa é alterado