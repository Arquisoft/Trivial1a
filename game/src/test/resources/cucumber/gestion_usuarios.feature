# language: es
Característica: Gestión de usuarios

Escenario: Crear el primer usuario

    Dada una lista vacía de usuarios
    Cuando creo un usuario de nombre "Pepe" y clave "Pepe12"
    Entonces el número de usuarios es 1

Escenario: Entrar en el sistema
	Dada la siguiente lista de usuarios:
      | nombre | clave   |
      | pepe   | pepe12  |
      | luis   | siul    |
      | mari   | 2mmm2   |
    Cuando introduzco el nombre "pepe" y la clave "pepe12"
    Entonces puedo entrar en el sistema 