# language: pt

Funcionalidade: Propondo lances ao leilao

Cenario: Propondo um unico lance valido
Dado Dado um lance valido
Quando propoe um leilao
Entao Entao o lance eh aceito

Cenario: Propondo varios lances validos
Dado um lance 10.0 reais do usuario "fulano"
E um lance 15.0 reais do usuario "beltrano"
Quando propoe varios lances ao leilao
Entao Entao os lances sao aceitos

Esquema do Cenario: Propondo um lance invalido
Dado um lance invalido de <valor> reais e do usuario '<nomeUsuario>'
Quando propoe um leilao
Entao o lance nao eh aceito

Exemplos:
	|valor | nomeUsuario |
	|	0  |  beltrano |
	|  -1  | cigano   |
	
Cenario: Propondo uma sequencia de lances
Dado dois lances
	|valor | nomeUsuario |
	|  10  |  beltrano   |
	|  15  |  beltrano   |
Quando propoe varios lances ao leilao
Entao o segundo lance nao eh aceito
