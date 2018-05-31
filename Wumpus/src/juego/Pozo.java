package juego;

public class Pozo {
	
	int posicionX;
	int posicionY;
	
	public Pozo(int posicionX, int posicionY) {
		this.posicionX = posicionX;
		this.posicionY = posicionY;
	}
	
	public void brisaPozo (Casilla[][] tablero, int x, int y) {
		//Parte de arriba del Pozo
		x--;
		if(x >= 0) {
			tablero[x][y].setCasillaConBrisa(true);
		}
		//Parte de abajo del Pozo
		x = x + 2;
		if(x <= 3) {
			tablero[x][y].setCasillaConBrisa(true);
		}
		//Parte izquierda del Pozo
		x--;
		y--;
		if(y >= 0) {
			tablero[x][y].setCasillaConBrisa(true);
		}
		//Parte derecha del Pozo
		y=y+2;
		if(y <= 3) {
			tablero[x][y].setCasillaConBrisa(true);
		}
	}
	
	public int getPosicionX() {
		return posicionX;
	}

	public int getPosicionY() {
		return posicionY;
	}

}
