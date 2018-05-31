package juego;

public class GestionArmas {

	Limites limites = new Limites();
	
	public void getFlechas(Juego partida, Direccion direccion, int flechas) {
		if (flechas > 0) {
			lanzarFlecha(partida, direccion);
		} else {
			System.out.println("NO QUEDAN FLECHAS!!");
		}
	}
	
	public void lanzarFlecha(Juego partida, Direccion direccion) {
		if(limites.esMuro(partida.cazador, direccion)) {
			isPared();
		} else {
			switch (direccion) {
			case U:
				if(partida.cazador.posicionY == partida.wumpus.posicionY && partida.wumpus.posicionX < partida.cazador.posicionX) {
					partida.wumpus.muere();
					partida.wumpus.hedorWumpus(partida.tablero, partida.wumpus.getPosicionX(), partida.wumpus.getPosicionY(), partida.wumpus.isVivo());
					System.out.println("El cazador percibe un grito...el wumpus está muerto!");
					break;
				} 
				isPared();
				break;
			case D:
				if(partida.cazador.posicionY == partida.wumpus.posicionY && partida.wumpus.posicionX > partida.cazador.posicionX) {
					partida.wumpus.muere();
					partida.wumpus.hedorWumpus(partida.tablero, partida.wumpus.getPosicionX(), partida.wumpus.getPosicionY(), partida.wumpus.isVivo());
					System.out.println("El cazador percibe un grito...el wumpus está muerto!");
					break;
				}
				isPared();
				break;
			case L:
				if(partida.wumpus.posicionY < partida.cazador.posicionY && partida.cazador.posicionX == partida.wumpus.posicionX) {
					partida.wumpus.muere();
					partida.wumpus.hedorWumpus(partida.tablero, partida.wumpus.getPosicionX(), partida.wumpus.getPosicionY(), partida.wumpus.isVivo());
					System.out.println("El cazador percibe un grito...el wumpus está muerto!");
					break;
				}
				isPared();
				break;
			case R:
				if(partida.cazador.posicionY < partida.wumpus.posicionY  && partida.cazador.posicionX == partida.wumpus.posicionX) {
					partida.wumpus.muere();
					partida.wumpus.hedorWumpus(partida.tablero, partida.wumpus.getPosicionX(), partida.wumpus.getPosicionY(), partida.wumpus.isVivo());
					System.out.println("El cazador percibe un grito...el wumpus está muerto!");
					break;
				}
				isPared();
				break;
			default:
				isPared();
			}			
		}	
	}
	
	private void isPared() {
		System.out.println("LA FLECHA LE HA DADO A LA PARED.");
	}
	
}
