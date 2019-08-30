# Challenge

## Executar

- Para executar a API utilizar a classe main ClickbusApiApplication.class

## Endpoints

- POST -> https://futema-clickbus-challenge.herokuapp.com/v1/challenge/places
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

- PUT -> https://futema-clickbus-challenge.herokuapp.com/v1/challenge/places/1(ID do lugar)
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

- GET -> https://futema-clickbus-challenge.herokuapp.com/v1/challenge/places?name=Restaurante_tres_update
	- Busca lugares através do nome

- GET -> https://futema-clickbus-challenge.herokuapp.com/v1/challenge/places
	- Busca todos os lugares
	
- GET -> https://futema-clickbus-challenge.herokuapp.com/v1/challenge/places/1(ID do lugar)
	- Busca lugares por id

