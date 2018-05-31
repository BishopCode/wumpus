package juego;

import java.util.Scanner;

public class Juego{

	Casilla[][] tablero;
	Cazador cazador;
	Wumpus wumpus;
	Pozo pozo;
	Lingote lingote;
	
	public Juego() {
		this.tablero = crearTableroDeJuego();
		//Añadimos al cazador al tablero en la posicion Abajo-Izquierda
		cazador = new Cazador(MundoWumpusParams.TAMANYO_TABLERO-1, 0);
//		System.out.println("CAZADOR: " + cazador.getPosicionX() + " " + cazador.getPosicionY());
		int tamanyoTableroX = MundoWumpusParams.TAMANYO_TABLERO;
		int tamanyoTableroY = MundoWumpusParams.TAMANYO_TABLERO;
		int posicionY;
		int posicionX;
		
		//Añadimos al wumpus al tablero con su hedor, evitando la posicion del cazador
		do {
			posicionX = (int)(Math.random() * tamanyoTableroX);
			posicionY = (int)(Math.random() * tamanyoTableroY);
			
		} while ((posicionX == cazador.getPosicionX() && posicionY == cazador.getPosicionY()) ||
				(posicionX == cazador.getPosicionX()-1 && posicionY == cazador.getPosicionY()) ||
				(posicionX == cazador.getPosicionX() && posicionY == cazador.getPosicionY()+1));
		wumpus = new Wumpus(posicionX, posicionY);
//		System.out.println("WUMPUS: " + wumpus.getPosicionX() + " " + wumpus.getPosicionY());
		wumpus.hedorWumpus(this.tablero, wumpus.getPosicionX(), wumpus.getPosicionY(), wumpus.isVivo());
		
		//Añadimos el lingote de oro con su brillo
		do {
			posicionX = (int)(Math.random() * tamanyoTableroX);
			posicionY = (int)(Math.random() * tamanyoTableroY);
		} while ((posicionX == cazador.getPosicionX() && posicionY == cazador.getPosicionY()) ||
				 (posicionX == wumpus.getPosicionX() || posicionY == wumpus.getPosicionY()));
		lingote = new Lingote(posicionX, posicionY);
//		System.out.println("LINGOTE: " + lingote.getPosicionX() + " " + lingote.getPosicionY());
		tablero[posicionX][posicionY].setCasillaConBrillo(true);
		
		//Añadimos los pozos con su brisa, evitando la posicion del cazador, del wumpus y del lingote (evitamos poner pozos alrededor de la salida del cazador)
		if(MundoWumpusParams.NUMERO_POZOS <= MundoWumpusParams.TAMANYO_TABLERO) {
			for(int i = 0 ; i < MundoWumpusParams.NUMERO_POZOS ; i++) {
				do {
					posicionX = (int)(Math.random() * tamanyoTableroX);
					posicionY = (int)(Math.random() * tamanyoTableroY);
				} while ((posicionX == cazador.getPosicionX() && posicionY == cazador.getPosicionY()) ||
						(posicionX == cazador.getPosicionX()-1 && posicionY == cazador.getPosicionY()) ||
						(posicionX == cazador.getPosicionX() && posicionY == cazador.getPosicionY()+1) ||
						(posicionX == wumpus.getPosicionX() && posicionY == wumpus.getPosicionY()) ||
						(posicionX == lingote.getPosicionX() && posicionY == lingote.getPosicionY()) ||
						tablero[posicionX][posicionY].isCasillaConPozo());
				pozo = new Pozo(posicionX, posicionY);
				tablero[posicionX][posicionY].setCasillaConPozo(true);
//			System.out.println("POZO "+ i + ": " + pozo.getPosicionX() + " " + pozo.getPosicionY());
				pozo.brisaPozo(this.tablero, posicionX, posicionY);				
			}
		} else {
			System.out.println("Demasiados pozos para un tablero de este tamaño. Por favor revise la configuracion.\n\n");
			throw new Error("Error en la creación del tablero de juego.");
		}
	}
	
	@SuppressWarnings("unused")
	public Casilla[][] crearTableroDeJuego() {
		//Creamos el tablero de juego (Mundo)
		Casilla[][] tablero = new Casilla[MundoWumpusParams.TAMANYO_TABLERO][MundoWumpusParams.TAMANYO_TABLERO];
		//Creamos las casillas del tablero
		int posicionX = 0;
		int posicionY = 0;
		for(Casilla[] casillas : tablero) {
			for(Casilla casilla : casillas) {
				tablero[posicionX][posicionY] = new Casilla(posicionX, posicionY);
				posicionX++;
			}
			posicionX = 0;
			posicionY++;
		}
		return tablero;
	}
			
	public String percepcion(Estados estado) {
		String mensaje = "El cazador percibe: " + estado.estadosJuego.toString();
		
		if(estado.estadosJuego.toString().equals("BRISAHEDOR")) {
			mensaje = "El cazador percibe: BRISA Y HEDOR EN LA MISMA CASILLA";
		}
		if(estado.estadosJuego.toString().equals("MUERTOPORWUMPUS")) {
			mensaje = "El cazador muere a manos del WUMPUS! \n\nGAME OVER!";
		}
		if(estado.estadosJuego.toString().equals("MUERTOPORPOZO")) {
			mensaje = "El cazador muere tras caer al pozo. \n\nGAME OVER!";
		}
		if(estado.estadosJuego.toString().equals("BRILLO")) {
			mensaje = "El cazador percibe el brillo, ha conseguido el lingote de oro!!, vuelve al lugar de partida para escapar.";
		}
		if(estado.estadosJuego.toString().equals("GANADOR")) {
			mensaje = "El cazador ha conseguido escapar con el lingote de oro!!";
		}
		return mensaje;
	}

	@SuppressWarnings("resource")
	public static void main (String args[]) {
		ControlesJugador controles = new ControlesJugador();
		GestionArmas gestionArmas = new GestionArmas();
		Juego partida = new Juego();
		int numflechas = MundoWumpusParams.NUMERO_FLECHAS;
		
		System.out.println("Para mover al cazador, utilice las teclas:\n'W', para ir hacia arriba.\n'A', para ir hacia la izquierda.\n'S', para ir hacia abajo.\n'D', para ir hacia la derecha.");
		System.out.println("Para lanzar flechas con el arco, utilice del teclado numérico las teclas:\n'8', para lanzarla hacia arriba.\n'4', para lanzarla hacia la izquierda.\n'2', para lanzarla hacia abajo.\n'6', para lanzarla hacia la derecha.");
		System.out.println("\n----------------------------");
		System.out.println("El juego Wumpus ha empezado!");
		while(partida.cazador.isVivo() && !partida.cazador.isGanador()) {
			System.out.println("\nFlechas disponibles: " + numflechas);
			System.out.println("Posición del cazador: [" + partida.getCazador().getPosicionX() + " - " + partida.getCazador().getPosicionY() + "].");
//			System.out.println("Posición del wumpus: [" + partida.getWumpus().getPosicionX() + " - " + partida.getWumpus().getPosicionY() + "].");
			System.out.println("\nIntroduce una instrucción:\n");
			Scanner playerAction = new Scanner(System.in);
			String entradaTeclado = (playerAction.nextLine()).toLowerCase();
			
			if (!entradaTeclado.isEmpty()) {
				switch (entradaTeclado) {
				case "w":
					Estados estadoW = controles.moverJugador(partida, Direccion.U);
					String percepcionW = partida.percepcion(estadoW);
					System.out.println(percepcionW);
					break;
				case "s":
					Estados estadoS = controles.moverJugador(partida, Direccion.D);
					String percepcionS = partida.percepcion(estadoS);
					System.out.println(percepcionS);
					break;
				case "a":
					Estados estadoA = controles.moverJugador(partida, Direccion.L);
					String percepcionA = partida.percepcion(estadoA);
					System.out.println(percepcionA);
					break;
				case "d":
					Estados estadoD = controles.moverJugador(partida, Direccion.R);
					String percepcionD = partida.percepcion(estadoD);
					System.out.println(percepcionD);
					break;
				case "8":
					gestionArmas.getFlechas(partida, Direccion.U, numflechas);
					if(numflechas > 0) {
						numflechas--;						
					}
					break;						
				case "2":
					gestionArmas.getFlechas(partida, Direccion.D, numflechas);
					if(numflechas > 0) {
						numflechas--;						
					}
					break;
				case "4":
					gestionArmas.getFlechas(partida, Direccion.L, numflechas);
					if(numflechas > 0) {
						numflechas--;						
					}
					break;
				case "6":
					gestionArmas.getFlechas(partida, Direccion.R, numflechas);
					if(numflechas > 0) {
						numflechas--;						
					}
					break;
				default:
					break;
				}
			}				
		}		
	}

	public Casilla[][] getTablero() {
		return tablero;
	}

	public void setTablero(Casilla[][] tablero) {
		this.tablero = tablero;
	}

	public Cazador getCazador() {
		return cazador;
	}

	public void setCazador(Cazador cazador) {
		this.cazador = cazador;
	}

	public Wumpus getWumpus() {
		return wumpus;
	}

	public void setWumpus(Wumpus wumpus) {
		this.wumpus = wumpus;
	}

	public Pozo getPozo() {
		return pozo;
	}

	public void setPozo(Pozo pozo) {
		this.pozo = pozo;
	}

	public Lingote getLingote() {
		return lingote;
	}

	public void setLingote(Lingote lingote) {
		this.lingote = lingote;
	}
	
	public void setCazadorPosicion(int x, int y) {
		cazador.setPosicion(x, y);
	}
	
	public Wumpus setWumpusPosicion(int x, int y) { 
        wumpus = new Wumpus(x, y); 
        return wumpus; 
    } 
	
}
