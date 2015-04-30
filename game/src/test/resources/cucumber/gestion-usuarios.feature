# language: es
Característica: Gestion usuarios

Escenario: Entrar en el sistema; Exito
	Dada la lista de usuarios del sistema
    Cuando introduzco el nombre "pepe" y la clave "pepe"
    Entonces puedo entrar en el sistema 
   
Escenario: Entrar en el sistema; Fracaso
	Dada la lista de usuarios del sistema
    Cuando introduzco el nombre "pepe" y la clave "wiiii"
    Entonces no puedo entrar en el sistema 
    
Escenario: Registrarse en el sistema; Exito
	Dada la lista de usuarios del sistema
    Cuando introduzco el nombre "manolo" y la clave "manolo"
    Entonces el usuario con el nombre "manolo" y la clave "manolo" registrado con exito
    
Escenario: Registrarse en el sistema; Fracaso
	Dada la lista de usuarios del sistema
    Cuando introduzco el nombre "pepe" y la clave "pepe"
    Entonces usuario no registrado 