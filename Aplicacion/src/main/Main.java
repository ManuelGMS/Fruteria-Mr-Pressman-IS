package main;

import java.awt.Dimension;

import presentacion.Controlador;
import presentacion.Events;

public class Main {
	public static Dimension WINDOW_DIM = new Dimension(1000, 75);
	public static void main(String[] args) {
		Controlador.getInstance().accion(Events.GUI_MOSTRAR, null);
	}
}
