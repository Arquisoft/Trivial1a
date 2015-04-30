# language: es
Característica: Jugar partida un jugador

Escenario: Añadir jugador
	Dada una aplicacion iniciada
    Cuando añado un jugador
    Entonces tendre otro jugador mas en la partida
 
Escenario: Borrar jugador
	Dada una aplicacion iniciada
    Cuando borro un jugador que existe en la partida
    Entonces tendre otro jugador menos en la partida    
    
Escenario: Mover ficha desde la casilla 6 con un 2
	Dada un tablero del trivial y estoy en la casilla 6 y me sale un 2
    Entonces podre ir a las posiciones 19, 31, 43, 55, 67 y 4
    
Escenario: Mover ficha desde la casilla 1 con un 2
	Dada un tablero del trivial y estoy en la casilla 1 y me sale un 2
    Entonces podre ir a las posiciones 9, 72, 3
    
Escenario: Mover ficha desde la casilla 15 con un 5
	Dada un tablero del trivial y estoy en la casilla 15 y me sale un 5
    Entonces podre ir a las posiciones 7, 23, 10  
    
Escenario: Jugador acierta pregunta sin quesitos 
	Dada un jugador sin quesitos que cae en la casilla 1
	Entonces acierta la pregunta y se le asigna un quesito    

Escenario: Jugador acierta pregunta con quesitos pero no se le asigna
	Dada un jugador con el quesito de la casilla 1 que cae en una casilla 1
	Entonces acierta la pregunta y no se le asigna el quesito al tenerlo ya 

Escenario: Jugador acierta pregunta con quesitos y se le asigna
	Dada un jugador con el quesito de la casilla 1 que cae en la casilla 14
	Entonces acierta la pregunta y se le asigna el quesito 