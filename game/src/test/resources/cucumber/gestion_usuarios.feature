# language: es
Característica: Gestión de usuarios

  Escenario: Crear el primer usuario
    Dado un nuevo juego
    Cuando creo un usuario de nombre "Pepe" y clave "Pepe12"
    Entonces el número de usuarios es 1

  Escenario: registro de usuario
    Dado un nuevo juego
    Cuando me registro con nombre "Pepe" y clave "Pepe12"
    Entonces me añade al juego
    

