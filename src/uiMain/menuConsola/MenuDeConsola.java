    /**
 * Punto 2 en https://minaslap.net/pluginfile.php/71528/mod_resource/content/1/practica1-Definitiva.pdf
 */
package uiMain.menuConsola;


import java.util.ArrayList;
import java.util.Scanner;

import java.io.IOException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import gestorAplicacion.Obras.Obra;
import uiMain.menuConsola.opciones.OpcionSalir;
import uiMain.menuConsola.opciones.OpcionSerAdministrador;
import uiMain.menuConsola.opciones.OpcionSerInvitado;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * @author Andor
 *
 */
public class MenuDeConsola {

	private Scanner in = new Scanner(System.in);
		
	
	
	//Atributos

	
	private static MenuDeConsola menuActual ;
	private String nombre; //Este será una especie de titulo
	private MenuDeConsola atras; //Este será el menu anterior
	private MenuDeConsola siguiente;   //Este será el menú siguiente
	private ArrayList<Obra> aux; //Objeto auxiliar
	private ArrayList<OpcionDeMenu> listaOp = new ArrayList<>();//Lista de opciones de cada menú

	
	/*
Crear un metodo lanzarMenu que, dentro de un bucle, muestre (por pantalla) todas las opciones
disponibles más la opcion Salir y que solicite una opción al usuario. Cuando el usuario introduzca la
opcion deseada se debera llamar al metodo ejecutar del objeto OpcionDeMenu correspondiente, de la lista.
	 */
	/* Devuelve una instancia de tipo MenuDeConsola para que en la sig. iteracion del while del main
	 * se muestren las opciones de menuInv o menuAdm según corresponda.
	 */
	public MenuDeConsola lanzarMenu() throws JsonGenerationException, JsonMappingException, IOException {
		System.out.println("\nOpciones disponibles:\n");
		int i = 0;
		for (OpcionDeMenu op : listaOp) {
			System.out.println(i + ". " + op);
			i++;
		}
		System.out.println("\nIngrese lo que desea ejecutar:");
		int ent = Math.min(in.nextInt(), listaOp.size()-1);
		MenuDeConsola hola = listaOp.get(ent).ejecutar();
		listaOp.get(ent).asignar(this,hola);
		return this.siguiente;
	}
		
	public static MenuDeConsola inicializar() {
		ArrayList<OpcionDeMenu> opcionesIniciales = new ArrayList<OpcionDeMenu>();
		opcionesIniciales.add(new OpcionSerInvitado());
		opcionesIniciales.add(new OpcionSerAdministrador());
		opcionesIniciales.add(new OpcionSalir());
		menuActual= new MenuDeConsola("\u00A1Bienvenido a M\u0171vez\u00E1rt!",opcionesIniciales);
		
		return menuActual;
	}
	
	
	//Constructores
	private MenuDeConsola(String nombre,ArrayList<OpcionDeMenu> opciones) {
		this.nombre = nombre;
		this.listaOp = opciones;
	}
	public MenuDeConsola(String nombre) {
		new MenuDeConsola(nombre, new ArrayList<>());
	}
	@Override
	public String toString() {
		return this.nombre;
	}

	
	
	//Getter y setters
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public MenuDeConsola getAtras() {
		return atras;
	}
	public void setAtras(MenuDeConsola atras) {
		this.atras = atras;
	}
	public ArrayList<Obra> getAux() {
		return aux;
	}
	public void setAux(ArrayList<Obra> aux) {
		this.aux = aux;
	}
	public ArrayList<OpcionDeMenu> getListaOp() {
		return listaOp;
	}
	public void añadirOpcion(OpcionDeMenu op) {
		this.listaOp.add(op);
	}

	public MenuDeConsola getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(MenuDeConsola siguiente) {
		this.siguiente = siguiente;
	}

	public static MenuDeConsola getMenuActual() {
		return menuActual;
	}

	public static void setMenuActual(MenuDeConsola menuActual) {
		MenuDeConsola.menuActual = menuActual;
	}

	

}
