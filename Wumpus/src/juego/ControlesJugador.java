package juego;

public class ControlesJugador{
	
	Juego juego;
	Cazador cazador;
	Wumpus wumpus;
	Pozo pozo;
	
	public Estados moverJugador(Juego juego, Direccion direccion) {
		switch (direccion) {
		case L:
			return moverJugador(juego, juego.cazador.getPosicionX(), juego.cazador.getPosicionY()-1);
		case R:
			return moverJugador(juego, juego.cazador.getPosicionX(), juego.cazador.getPosicionY()+1);
		case U:
			return moverJugador(juego, juego.cazador.getPosicionX()-1, juego.cazador.getPosicionY());
		case D:
			return moverJugador(juego, juego.cazador.getPosicionX()+1, juego.cazador.getPosicionY());
		default: 
            throw new IllegalArgumentException("Dirección invalida."); 
		}
	}
	
	public Estados moverJugador(Juego juego, int x, int y) {
		if(juego.wumpus != null && (x == juego.wumpus.posicionX && y == juego.wumpus.posicionY) && juego.wumpus.isVivo()) {
			juego.cazador.setPosicion(x, y);
			juego.cazador.muere();
			return new Estados(EstadosJuego.MUERTOPORWUMPUS);
		}
		
		if(isValidoMovimientoCazador(x, y)){
			if(juego.pozo != null && (juego.tablero[x][y].isCasillaConPozo())){
				juego.cazador.setPosicion(x, y);
				juego.cazador.muere();
				return new Estados(EstadosJuego.MUERTOPORPOZO);
			}
			
			Casilla[][] tablero = juego.tablero;
			juego.cazador.setPosicion(x, y);
			if(juego.cazador.getPosicionX() == MundoWumpusParams.TAMANYO_TABLERO-1 && juego.cazador.getPosicionY() == 0 && juego.cazador.isTieneLingote()) {
				juego.cazador.setGanador(true);
				return new Estados(EstadosJuego.GANADOR);
			}
			if(tablero[x][y].isCasillaConBrillo()) {
				juego.cazador.consigueLingote();
				tablero[x][y].setCasillaConBrillo(false);
				return new Estados(EstadosJuego.BRILLO);
			}
			if(tablero[x][y].isCasillaConHedor()) {
				if(tablero[x][y].isCasillaConBrisa()) {
					return new Estados(EstadosJuego.BRISAHEDOR);
				}
			} 
			if(tablero[x][y].isCasillaConHedor()) {
				return new Estados(EstadosJuego.HEDOR);
			}
			if(tablero[x][y].isCasillaConBrisa()) {
				return new Estados(EstadosJuego.BRISA);
			}
			else {
				return new Estados(EstadosJuego.NORMALIDAD);				
			}
		} else {
			return new Estados(EstadosJuego.CHOQUE);
		}
	}
	
	private boolean isValidoMovimientoCazador(int x, int y) {
		if(x < 0 || y < 0 || x >= MundoWumpusParams.TAMANYO_TABLERO || y >= MundoWumpusParams.TAMANYO_TABLERO) {
			return false;
		}
		return true;
	}
}
