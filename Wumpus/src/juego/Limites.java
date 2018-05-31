package juego;

public class Limites {
	
	public boolean esMuro(Cazador cazador, Direccion direccion) {
		switch (direccion) {
			case U:
				if(cazador.posicionX == 0) {
					return true;
				}
				return false;
			case D:
				if(cazador.posicionX == MundoWumpusParams.TAMANYO_TABLERO-1) {
					return true;
				}
				return false;
			case L:
				if(cazador.posicionY == 0) {
					return true;
				}
				return false;
			case R:
				if(cazador.posicionY == MundoWumpusParams.TAMANYO_TABLERO-1) {
					return true;
				}
				return false;
			default:
				return false;
		}
	}

}
