package juego;

public class Wumpus {
	
	int posicionX;
	int posicionY;
	
	private boolean vivo = true;
	
	public Wumpus(int posicionX, int posicionY) {
		this.posicionX = posicionX;
		this.posicionY = posicionY;
	}
	
	public void hedorWumpus (Casilla[][] tablero, int x, int y, boolean isVivo) {		
		//Parte de arriba del Wumpus
		x--;
		if(x >= 0) {
			if(isVivo) {
				tablero[x][y].setCasillaConHedor(true);				
			} else {
				tablero[x][y].setCasillaConHedor(false);
			}
		}
		//Parte de abajo del Wumpus
		x = x + 2;
		if(x <= 3) {
			if(isVivo) {
				tablero[x][y].setCasillaConHedor(true);				
			} else {
				tablero[x][y].setCasillaConHedor(false);
			}
		}
		//Parte izquierda del Wumpus
		x--;
		y--;
		if(y >= 0) {
			if(isVivo) {
				tablero[x][y].setCasillaConHedor(true);				
			} else {
				tablero[x][y].setCasillaConHedor(false);
			}
		}
		//Parte derecha del Wumpus
		y=y+2;
		if(y <= 3) {
			if(isVivo) {
				tablero[x][y].setCasillaConHedor(true);				
			} else {
				tablero[x][y].setCasillaConHedor(false);
			}
		}
	}
	
	public boolean isVivo() {
		return vivo;
	}

	public void muere() {
		vivo = false;
	}

	public int getPosicionX() {
		return posicionX;
	}

	public int getPosicionY() {
		return posicionY;
	}

	
	
	
	
}
