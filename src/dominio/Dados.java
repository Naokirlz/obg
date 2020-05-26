/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Admin
 */
public class Dados {

    public ArrayList<Jugador> listaJugadores;
    public ArrayList<Partida> listaPartidasReales;
    public ArrayList<Partida> listaPartidasTest;

    public Dados() {

        listaJugadores = new ArrayList<Jugador>();
        listaPartidasReales = new ArrayList<Partida>();
        listaPartidasTest = new ArrayList<Partida>();

        datosPrueba();

    }

    public void datosPrueba() {

        Jugador jugadorUno = new Jugador(32, "EL FEDE", "FEDERICO");
        Jugador jugadorDos = new Jugador(32, "ALPHA", "CRISTIAN");
        Jugador jugadorTres = new Jugador(32, "JULITO", "JULIO");
        Jugador jugadorCuatro = new Jugador(32, "FER", "FERNANDO");

        listaJugadores.add(jugadorUno);
        listaJugadores.add(jugadorDos);
        listaJugadores.add(jugadorTres);
        listaJugadores.add(jugadorCuatro);

        Real partida1 = new Real(jugadorUno, jugadorTres, "F", "J");
        partida1.setGanador(jugadorTres);
        listaPartidasReales.add(partida1);

        Real partida2 = new Real(jugadorCuatro, jugadorTres, "F", "J");
        partida2.setGanador(jugadorTres);
        listaPartidasReales.add(partida2);

        Real partida3 = new Real(jugadorUno, jugadorDos, "F", "J");
        partida3.setGanador(jugadorDos);
        listaPartidasReales.add(partida3);

    }

    public ArrayList<Jugador> getListaJugadores() {
        return this.listaJugadores;

    }

    public ArrayList<Jugador> verRanking() {

        actualizarRanking();
        Collections.sort(listaJugadores);
        return this.listaJugadores;

    }

    public boolean altaJugador(String nombre, int edad, String alias) {

        boolean pude = true;

        Jugador jugador = new Jugador();
        jugador.setNombre(nombre);
        jugador.setEdad(edad);
        jugador.setAlias(alias);

        //verifico que alias no sea repetida
        pude = verificarAlias(alias);

        if (pude) {
            listaJugadores.add(jugador);
        }

        return pude;

    }

    public boolean verificarAlias(String alias) {

        boolean pude = true;

        Jugador jugador = new Jugador();
        jugador.setAlias(alias);

        int i = 0;

        //VERIFICAMOS QUE EL ALIAS NO SE REPITA
        while (i < listaJugadores.size() && pude) {

            Jugador jug = listaJugadores.get(i);

            if (jug.equals(jugador)) {

                pude = false;

            }
            i++;

        }

        return pude;
    }

    public Jugador devolverJugadorActual(Partida partida) {

        return partida.devolverJugadorActual();

    }

    public boolean abandonarPartida(Partida partida) {

        boolean pude = false;
        pude = partida.abandonarPartida();
        if (pude) {
            if (partida instanceof Real) {
                pude = listaPartidasReales.add(partida);
            } else {
                pude = listaPartidasTest.add(partida);
            }
        }
        actualizarRanking();
        return pude;

    }
    
    public boolean tableroCompleto(Partida partida){
        return partida.tableroCompleto();
    }

    public Partida crearPartida(Jugador uno, Jugador dos, String cUno, String cDos, boolean tipo) {

        Partida retorno = new Partida();

        if (tipo) {

            retorno = crearPartidaReal(uno, dos, cUno, cDos);

        } else {

            retorno = crearPartidaTest(uno, dos, cUno, cDos);

        }

        return retorno;

    }

    public Real crearPartidaReal(Jugador uno, Jugador dos, String cUno, String cDos) {

        Real nuevaPartida = new Real(uno, dos, cUno, cDos);

        return nuevaPartida;

    }

    public Test crearPartidaTest(Jugador uno, Jugador dos, String cUno, String cDos) {

        Test nuevaPartida = new Test(uno, dos, cUno, cDos);

        return nuevaPartida;

    }

    public String getLetraJugadorUno(Partida partida) {

        return partida.getLetraJugadorUno();

    }

    public String getLetraJugadorDos(Partida partida) {

        return partida.getLetraJugadorDos();

    }

    public Tablero getTablero(Partida partida) {

        return partida.getTablero();

    }

    public int[] tirarDados(Partida partida, int valor, int posicion) {

        return partida.tirarDados(valor, posicion);

    }

    public int[] calcularPuntaje(Partida partida) {

        return partida.calcularPuntaje();

    }

    public boolean cargarJugada(int posicion, Partida partida) {
        boolean finaliza = false;

        finaliza = partida.cargarJugada(posicion);

        return finaliza;
    }

    public boolean[] solicitarAyuda(int[] dados, Partida partida) {

        return partida.solicitarAyuda(dados, false);

    }

    public boolean[] complicarAyuda(int[] dados, Partida partida) {

        return partida.solicitarAyuda(dados, true);

    }

    public boolean pasarTurno(Partida partida) {

        return partida.pasarTurno();

    }
    
    public void completarTablero(Partida partida){
    
        for(int i=1; i<21; i++){
        partida.cargarJugada(i);
        }
    
    }
    

    public int ganadorPartida(Partida partida){
        return partida.ganadorPartida();
    }
    
    public boolean finalizarPartida(Partida juego) {

        boolean pude = false;

        pude = juego.finalizarPartida();

        if (pude) {
            if (juego instanceof Real) {
                pude = listaPartidasReales.add(juego);
            } else {
                pude = listaPartidasTest.add(juego);
            }
        }

        actualizarRanking();
        return pude;

    }

    public void actualizarRanking() {
        HashMap<Jugador, Integer> mapaGanadas = new HashMap<>();
        HashMap<Jugador, Integer> mapaJugadas = new HashMap<>();

        for (Partida partida : listaPartidasReales) {

            if (mapaGanadas.containsKey(partida.getGanador())) {

                mapaGanadas.put(partida.getGanador(), mapaGanadas.get(partida.getGanador()) + 1);

            } else {
                mapaGanadas.put(partida.getGanador(), 1);
            }
            if (mapaJugadas.containsKey(partida.getJugadorUno())) {

                mapaJugadas.put(partida.getJugadorUno(), mapaJugadas.get(partida.getJugadorUno()) + 1);

            } else {
                mapaJugadas.put(partida.getJugadorUno(), 1);
            }

            if (mapaJugadas.containsKey(partida.getJugadorDos())) {

                mapaJugadas.put(partida.getJugadorDos(), mapaJugadas.get(partida.getJugadorDos()) + 1);

            } else {
                mapaJugadas.put(partida.getJugadorDos(), 1);
            }
        }

        Iterator<Jugador> iterator = mapaGanadas.keySet().iterator();

        while (iterator.hasNext()) {
            Jugador jugador = iterator.next();
            int cantidadGanadas = mapaGanadas.get(jugador);
            jugador.setPartidasGanadas(cantidadGanadas);

        }

        Iterator<Jugador> iterator2 = mapaJugadas.keySet().iterator();

        while (iterator2.hasNext()) {
            Jugador jugador = iterator2.next();
            int cantidadJugadas = mapaJugadas.get(jugador);
            jugador.setPartidasJugadas(cantidadJugadas);

        }
    }

    public boolean inalcanzable(Partida partida) {
        return partida.inalcanzable();
    }

}
