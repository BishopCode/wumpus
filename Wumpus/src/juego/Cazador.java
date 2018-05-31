package juego;

public class Cazador {
	
	int posicionX;
	int posicionY;
	private boolean vivo = true;
	private boolean ganador = false;
	private boolean tieneLingote = false;
	
	public Cazador() {
		
	}
	
	public Cazador(int posicionX, int posicionY) {
		this.posicionX = posicionX;
		this.posicionY = posicionY;
	}
	
	public void setPosicion(int posicionX, int posicionY) {
		this.posicionX = posicionX;
		this.posicionY = posicionY;
	}
	
	public int getPosicionX () {
		return posicionX;
	}
	
	public int getPosicionY () {
		return posicionY;
	}

	public boolean isVivo() {
		return vivo;
	}
	
	public void muere() {
		vivo = false;
	}

	public boolean isTieneLingote() {
		return tieneLingote;
	}

	public void consigueLingote() {
		tieneLingote = true;
	}

	public boolean isGanador() {
		return ganador;
	}

	public void setGanador(boolean ganador) {
		this.ganador = ganador;
	}
}
