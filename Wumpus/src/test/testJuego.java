package test;


import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.hamcrest.Matcher;
import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;

import juego.Casilla;
import juego.Cazador;
import juego.ControlesJugador;
import juego.Direccion;
import juego.GestionArmas;
import juego.Juego;
import juego.MundoWumpusParams;
import juego.Wumpus;

public class testJuego {
	
	private Juego juego;
	private Casilla[][] tablero;
	private GestionArmas gestionArmas;
	private ControlesJugador controlesJugador;
	
	@Before	
	public void setUp() {
		juego = new Juego();
		tablero = juego.getTablero();
		gestionArmas = new GestionArmas();
		controlesJugador = new ControlesJugador();
	}

	@Test
	public void testLanzarFlechaArriba() {
		juego.setCazadorPosicion(MundoWumpusParams.TAMANYO_TABLERO-1, 0);
		Wumpus wumpus = juego.setWumpusPosicion(0, 0);
		
		Cazador cazador = juego.getCazador();
		gestionArmas.getFlechas(juego, Direccion.U, 1);
		assertTrue(wumpus.isVivo());
	}
	
	@Test
	public void testLanzarFlechaAbajo() {
		juego.setCazadorPosicion(0, 0);
		Wumpus wumpus = juego.setWumpusPosicion(MundoWumpusParams.TAMANYO_TABLERO-1, 0);
		
		Cazador cazador = juego.getCazador();
		gestionArmas.getFlechas(juego, Direccion.D, 1);
		assertTrue(wumpus.isVivo());
	}
	
	@Test
	public void testLanzarFlechaIzquierda() {
		juego.setCazadorPosicion(MundoWumpusParams.TAMANYO_TABLERO-1, MundoWumpusParams.TAMANYO_TABLERO-1);
		Wumpus wumpus = juego.setWumpusPosicion(MundoWumpusParams.TAMANYO_TABLERO-1, 0);
		
		Cazador cazador = juego.getCazador();
		gestionArmas.getFlechas(juego, Direccion.L, 1);
		assertTrue(wumpus.isVivo());
	}
	
	@Test
	public void testLanzarFlechaDerecha() {
		juego.setCazadorPosicion(MundoWumpusParams.TAMANYO_TABLERO-1, 0);
		Wumpus wumpus = juego.setWumpusPosicion(MundoWumpusParams.TAMANYO_TABLERO-1, MundoWumpusParams.TAMANYO_TABLERO-1);
		
		Cazador cazador = juego.getCazador();
		gestionArmas.getFlechas(juego, Direccion.R, 1);
		assertTrue(wumpus.isVivo());
	}
	
	@Test
	public void testMoverCazadorArriba() {
		juego.setCazadorPosicion(MundoWumpusParams.TAMANYO_TABLERO-1, 0);
		
		Cazador cazador = juego.getCazador();
		
		controlesJugador.moverJugador(juego, Direccion.U);
		int[] posicionPartida = new int[] {MundoWumpusParams.TAMANYO_TABLERO-1, 0};
		int[] posicionActual = new int[] {cazador.getPosicionX(), cazador.getPosicionY()};
		assertNotEquals(posicionPartida, posicionActual);
	}
	
	@Test
	public void testMoverCazadorAbajo() {
		juego.setCazadorPosicion(0, 0);
		
		Cazador cazador = juego.getCazador();
		
		controlesJugador.moverJugador(juego, Direccion.D);
		int[] posicionPartida = new int[] {0, 0};
		int[] posicionActual = new int[] {cazador.getPosicionX(), cazador.getPosicionY()};
		assertNotEquals(posicionPartida, posicionActual);
	}
	
	@Test
	public void testMoverCazadorIzquierda() {
		juego.setCazadorPosicion(MundoWumpusParams.TAMANYO_TABLERO-1, MundoWumpusParams.TAMANYO_TABLERO-1);
		
		Cazador cazador = juego.getCazador();
		
		controlesJugador.moverJugador(juego, Direccion.L);
		int[] posicionPartida = new int[] {MundoWumpusParams.TAMANYO_TABLERO-1,MundoWumpusParams.TAMANYO_TABLERO-1};
		int[] posicionActual = new int[] {cazador.getPosicionX(), cazador.getPosicionY()};
		assertNotEquals(posicionPartida, posicionActual);
	}
	
	@Test
	public void testMoverCazadorDerecha() {
		juego.setCazadorPosicion(MundoWumpusParams.TAMANYO_TABLERO-1, 0);
		
		Cazador cazador = juego.getCazador();
		
		controlesJugador.moverJugador(juego, Direccion.R);
		int[] posicionPartida = new int[] {MundoWumpusParams.TAMANYO_TABLERO-1,0};
		int[] posicionActual = new int[] {cazador.getPosicionX(), cazador.getPosicionY()};
		assertNotEquals(posicionPartida, posicionActual);
	}


}
