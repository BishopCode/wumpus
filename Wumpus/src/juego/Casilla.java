package juego;

public class Casilla {
	
	int posicionX;
	int posicionY;
	
	boolean casillaConFlecha 	= false;
	boolean casillaConHedor 	= false;
	boolean casillaConBrisa 	= false;
	boolean casillaConBrillo 	= false;
	boolean casillaConPozo      = false;
	
	
	public Casilla(int posicionX, int posicionY) {
		this.posicionX = posicionX;
		this.posicionY = posicionY;
	}

	public void setCasillaConFlecha(boolean casillaConFlecha) {
		this.casillaConFlecha = casillaConFlecha;
	}
	
	public void setCasillaConHedor(boolean casillaConHedor) {
		this.casillaConHedor = casillaConHedor;
	}
	
	public void setCasillaConBrisa(boolean casillaConBrisa) {
		this.casillaConBrisa = casillaConBrisa;
	}
	
	public void setCasillaConBrillo(boolean casillaConBrillo) {
		this.casillaConBrillo = casillaConBrillo;
	}
	
	public void setCasillaConPozo(boolean casillaConPozo) {
		this.casillaConPozo = casillaConPozo;
	}

	public boolean isCasillaConFlecha() {
		return casillaConFlecha;
	}

	public boolean isCasillaConHedor() {
		return casillaConHedor;
	}

	public boolean isCasillaConBrisa() {
		return casillaConBrisa;
	}
	
	public boolean isCasillaConBrillo() {
		return casillaConBrillo;
	}

	public boolean isCasillaConPozo() {
		return casillaConPozo;
	}
	
	
	
	
	

}
