# Challenge

## Executar

- Para executar a API utilizar a classe main ClickbusApiApplication.class

## Endpoints

- POST -> localhost:8091/v1/challenge/places
Payload:
{
	"name": "Restaurante_novo",
	"slug": "/places",
	"city": {
		"name" : "BATATAIS",
		"state": {
			"name": "SÃO PAULO"
		}
	}
}

- PUT -> localhost:8091/v1/challenge/places/1(ID do lugar)
Payload:
{
	"name": "Restaurante_tres_UPDATE",
	"slug": "/places",
	"city": {
		"name" : "ADOLFO",
		"state": {
			"name": "SÃO PAULO"
		}
	}
}

- GET -> localhost:8091/v1/challenge/places?name=Restaurante_tres_update
	- Busca lugares através do nome

- GET -> localhost:8091/v1/challenge/places
	- Busca todos os lugares
	
- GET -> localhost:8091/v1/challenge/places/1(ID do lugar)
	- Busca lugares por id

