/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Program;

import dominio.Dados;
import dominio.Tablero;
import dominio.Partida;
import dominio.Jugador;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class Program {

    Scanner in = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        Dados juego = new Dados();

        boolean bandera = false;

        while (!bandera) {

            int eleccion = Menuprincipal();

            switch (eleccion) {
                case 1:

                    // <editor-fold desc="REGISTRAR JUGADOR"> 
                    boolean pude = false;

                    while (!pude) {

                        String nombre = readString("INGRESE NOMBRE DEL JUGADOR", "DEBE INGRESAR DATOS DEL TIPO STRING");
                        //alta en la lista?
                        boolean correcto = false;
                        String alias = "";
                        while (!correcto) {
                            alias = readString("INGRESE ALIAS DEL JUGADOR", "DEBE INGRESAR DATOS DEL TIPO STRING");
                            correcto = validarAlias(alias, juego);
                        }
                        int edad = readInteger("INGRESE EDAD DEL JUGADOR", "", 1, 100);

                        pude = juego.altaJugador(nombre, edad, alias);

                        if (pude) {
                            System.out.println("ALTA EXITOSA, PRESIONE ENTER PARA CONTINUAR");
                        } else {
                            System.out.println("EL ALIAS ES REPETIDA");

                        }

                        System.in.read();
                    }
                    break;
                // </editor-fold>

                case 2:

                    // <editor-fold desc="JUGAR DADOS"> 
                    Jugador uno = new Jugador();
                    Jugador dos = new Jugador();

                    //SELECCIONO JUGADOR 1
                    uno = seleccionarJugadorDeLista(juego);

                    //SELECCIONO JUGADOR 2 (CONTROLO QUE NO SEA IGUAL A JUGADOR 1)
                    boolean repetido = true;
                    while (repetido) {

                        dos = seleccionarJugadorDeLista(juego);

                        if (dos.equals(uno)) {
                            System.out.println("JUGADOR DOS NO PUEDE SER IGUAL QUE JUGADOR 1");

                        } else {
                            repetido = false;

                        }

                    }

                    //SELECCIONO LETRA JUGADOR 1
                    String letraUno = seleccionarLetra("Seleccione la letra para jugar", "Seleccione caracteres correctos");

                    String letraDos = "";

                    //SELECCIONO LETRA JUGADOR 2 (CONTROLO QUE NO SEA IGUAL A JUGADOR 1)
                    repetido = true;

                    while (repetido) {

                        letraDos = seleccionarLetra("Seleccione la letra para jugar", "Seleccione caracteres correctos");

                        if (letraDos.equals(letraUno)) {

                            System.out.println("JUGADOR DOS NO PUEDE TENER IGUAL LETRA QUE JUGADOR 1");

                        } else {
                            repetido = false;

                        }

                    }

                    //SELECCIONO PARTIDA REAL O PARTIDA TEST
                    boolean partidaReal = partidaReal();

                    //TENGO QUE CREAR UNA PARTIDA Y CONTINUAR CON EL JUEGO =(
                    Partida partida = juego.crearPartida(uno, dos, letraUno, letraDos, partidaReal);

                    //EMPIEZO A JUGAR
                    boolean jugando = jugandoDados(juego, partida, partidaReal);

                    System.out.println(partida);

                    System.in.read();

                    break;
                // </editor-fold>

                case 3:

                    // <editor-fold desc="VER RANKING"> 
                    System.out.println("RANKING DE JUGADORES");

                    //OBTENGO EL RANKING Y LUEGO LO IMPRIMO
                    ArrayList<Jugador> ranking = juego.verRanking();

                    for (int i = 0; i < ranking.size(); i++) {

                        System.out.println(i + 1 + "-" + ranking.get(i));

                    }

                    System.out.println("PRESIONE ENTER PARA CONTINUAR...");
                    System.in.read();

                    break;
                // </editor-fold>

                case 4:

                    //<editor-fold desc="PARA SALIR DEL PROGRAMA"> 
                    System.out.println("FIN DEL PROGRAMA");
                    bandera = true;
                    break;
                // </editor-fold> 

            }
        }

    }

    public static int Menuprincipal() {
        Scanner in = new Scanner(System.in);
        String dados = "";
        dados = "******************************************************\n"
                + "******************************************************\n"
                + "      __\n"
                + "    /\\  \\\n"
                + "   / *\\ *\\   _____           _____    ___    ____ \n"
                + "  /    \\__\\  |    \\    /\\    |    \\  /   \\  /\n"
                + "  \\*  */* /  |    |   /  \\   |    |  |   |  \\___\n"
                + "   \\  / */   |    |  /____\\  |    |  |   |      \\\n"
                + "    \\/_*/    |____/ /      \\ |____/  \\___/  ____/\n"
                + "\n"
                + "******************************************************\n"
                + "******************************************************\n";

        System.out.println(dados);
        int eleccion = -1;
        boolean bandera = false;

        while (!bandera) {

            String mensaje = "=========================================================" + "\n";
            mensaje += "BIENVENID@ AL JUEGO DE DADOS" + "\n";
            mensaje += "=========================================================" + "\n";
            mensaje += "1. REGISTRAR JUGADOR" + "\n";
            mensaje += "2. JUGAR A 'SUMAS'" + "\n";
            mensaje += "3. VER RANKING DE JUGADORES" + "\n";
            mensaje += "4. TERMINAR" + "\n";
            mensaje += "==========================================================" + "\n";

            //System.out.println(mensaje);
            //eleccion = validarEleccion(in.nextInt(), 4, mensaje);
            eleccion = readInteger(mensaje, "", 1, 4);
            bandera = true;
        }

        return eleccion;

    }

    private static int validarEleccion(int eleccion, int max, String mensaje) {
        Scanner in = new Scanner(System.in);
        System.out.println(mensaje);
        boolean pude = false;
        int retorno = eleccion;
        do {
            if (retorno > 0 && retorno <= max) {
                pude = true;
            }

            if (!pude) {
                System.out.println("\u001B[35m           DATO INGRESADO NO VÁLIDO, INGRESE NUEVAMENTE           ");
                System.out.println("\u001B[35m==================================================================");
                retorno = validarEleccion(in.nextInt(), max, mensaje);
                System.out.println("\u001B[35m==================================================================");
            }
        } while (!pude);
        return retorno;

    }

    public static Jugador seleccionarJugadorDeLista(Dados juego) {
        Scanner in = new Scanner(System.in);
        ArrayList<Jugador> jugadores = juego.getListaJugadores();
        Jugador seleccionado = new Jugador();

        int cont = 1;

        for (Jugador jugador : jugadores) {

            System.out.println(cont + "- " + jugador.getNombre());

            cont++;
        }

        int eleccion = readInteger("Seleccione un Jugador", "FORMATO DE DATOS INCORRECTO", 1, jugadores.size());

        seleccionado = jugadores.get(eleccion - 1);

        System.out.println(seleccionado.getNombre());

        return seleccionado;

    }

    public static String seleccionarLetra(String message, String errorMessage) {
        Scanner input = new Scanner(System.in);
        String selectedString = "";
        String letra = "";
        boolean isDone = false;
        while (!isDone) {
            try {
                System.out.println(message);
                selectedString = input.nextLine();
                selectedString = selectedString.trim().toUpperCase();
                letra += selectedString.charAt(0);
                if (letra.charAt(0) < 65 || selectedString.charAt(0) > 90) {
                    errorMessage = "Valor Incorrecto, no nombre no puede contener caracteres numericos";
                    throw new Exception(errorMessage);
                }
                isDone = true;
            } catch (Exception e) {
                System.out.println("\u001B[35mDATO INGRESADO NO VÁLIDO, PRESIONE ENTER PARA CONTINUAR       ");
                System.out.println("\u001B[35m==================================================================");
                System.out.println("\u001B[35m" + errorMessage);
                System.out.println("\u001B[35m==================================================================");
                input.nextLine();
            }
        }

        return letra;

    }

    public static boolean partidaReal() {
        Scanner in = new Scanner(System.in);
        int eleccion = -1;
        boolean retorno = false;

        boolean bandera = false;

        while (!bandera) {

            String mensaje = "=========================================================" + "\n";
            mensaje += "                 SELECCIONE TIPO DE PARTIDA              " + "\n";
            mensaje += "=========================================================" + "\n";
            mensaje += "1. REAL" + "\n";
            mensaje += "2. TEST" + "\n";
            mensaje += "=========================================================" + "\n";

            System.out.println(mensaje);

            eleccion = validarEleccion(in.nextInt(), 2, mensaje);
            bandera = true;
        }

        if (eleccion == 1) {
            retorno = true;
        }

        return retorno;

    }

    public static int[] tiradaDeDados(Partida partida) {
        int[] dados = new int[5];

        for (int i = 0; i < 5; i++) {
            int valor = readInteger("Ingrese el valor del dado " + (i + 1), "El valor debe ser un número entre 1 y 6", 1, 6);
            dados = partida.tirarDados(i, valor);
        }

        return dados;
    }

    public static int readInteger(String message, String errorMessage, int minimo, int maximo) {
        Scanner input = new Scanner(System.in);
        int selectedNumber = 0;
        boolean isDone = false;
        while (!isDone) {
            try {
                System.out.println(message);
                selectedNumber = input.nextInt();
                if (selectedNumber > maximo || selectedNumber < minimo) {
                    throw new Exception("Valor Incorrecto, el valor debe estar entre " + minimo + " y " + maximo);
                }
                isDone = true;
            } catch (Exception e) {
                System.out.println("\u001B[35m           DATO INGRESADO NO VÁLIDO, PRESIONE ENTER PARA CONTINUAR       ");
                System.out.println("\u001B[35m==================================================================");
                System.out.println("\u001B[35m" + errorMessage);
                System.out.println("\u001B[35m==================================================================");
                input.nextLine();
            }
        }

        return selectedNumber;
    }

    public static String readString(String message, String errorMessage) {
        Scanner input = new Scanner(System.in);
        String selectedString = "";
        boolean isDone = false;
        while (!isDone) {
            try {
                System.out.println(message);
                selectedString = input.nextLine();
                selectedString = selectedString.trim().toUpperCase();
                if (selectedString.length() > 20 || selectedString.length() < 3) {
                    errorMessage = "Valor Incorrecto, el valor debe estar entre " + 3 + " y " + 20;
                    throw new Exception(errorMessage);
                }
                for (int i = 0; i < selectedString.length(); i++) {
                    if (selectedString.charAt(i) < 65 || selectedString.charAt(i) > 90) {
                        errorMessage = "Valor Incorrecto, no nombre no puede contener caracteres numericos";
                        throw new Exception(errorMessage);
                    }
                }
                isDone = true;
            } catch (Exception e) {
                System.out.println("\u001B[35m    DATO INGRESADO NO VÁLIDO, PRESIONE ENTER PARA CONTINUAR       ");
                System.out.println("\u001B[35m==================================================================");
                System.out.println("\u001B[35m" + errorMessage);
                System.out.println("\u001B[35m==================================================================");
                input.nextLine();
            }
        }

        return selectedString;
    }

    public static boolean validarAlias(String alias, Dados juego) {

        return juego.verificarAlias(alias);

    }

    public static boolean jugandoDados(Dados juego, Partida partida, boolean real) {
        Scanner input = new Scanner(System.in);
        boolean jugando = true;

        while (jugando) {

            System.out.println("-----------------------------------------");
            System.out.println("ES EL TURNO DE: " + juego.devolverJugadorActual(partida).getAlias());
            boolean inalcanzable = juego.inalcanzable(partida);
            if (inalcanzable) {
                System.out.println("\u001B[36mYA NO TIENES POSIBILIDAD DE GANAR LA PARTIDA");
            }
            System.out.println("-----------------------------------------");
            System.out.print("\033[31m PUNTAJE JUGADOR 1: ");
            int[] puntaje = juego.calcularPuntaje(partida);
            System.out.println(puntaje[0]);
            System.out.print("\033[34m PUNTAJE JUGADOR 2: ");
            System.out.println(puntaje[1]);
            System.out.println("-----------------------------------------");
            mostrarMatriz(juego.getTablero(partida), partida.getLetraJugadorUno(), partida.getLetraJugadorDos());
            System.out.println("-----------------------------------------");

            //TIRO LOS DADOS
            int[] dados = new int[5];
            if (real) {
                dados = juego.tirarDados(partida, 0, 0);
            } else {
                dados = tiradaDeDados(partida);
            }

            // MUESTRO LOS DADOS
            mostrarDados(dados);

            //ELEGIR ACCION
            jugando = menuTurno(dados, juego, partida);

            //juego.pasarTurno(partida);
            //verificar tablero lleno
            boolean tableroLleno = juego.tableroCompleto(partida);
            if (tableroLleno) {
                jugando = false;
                System.out.println("SE COMPLETO EL TABLERO");
                System.out.println("-----------------------------------------");
                System.out.print("\033[31m PUNTAJE JUGADOR 1: ");
                puntaje = juego.calcularPuntaje(partida);
                System.out.println(puntaje[0]);
                System.out.print("\033[34m PUNTAJE JUGADOR 2: ");
                System.out.println(puntaje[1]);
                System.out.println("-----------------------------------------");
                mostrarMatriz(juego.getTablero(partida), partida.getLetraJugadorUno(), partida.getLetraJugadorDos());
                System.out.println("-----------------------------------------");
                int ganador = juego.ganadorPartida(partida);
                String resultado;
                switch (ganador) {
                    case 1:
                        resultado = partida.getJugadorUno().getAlias();

                        break;
                    case 2:
                        resultado = partida.getJugadorDos().getAlias();
                        break;

                    default:
                        resultado = "EMPATE";
                        break;

                }
                
                juego.finalizarPartida(partida);

                System.out.println("EL GANADOR ES: " + resultado);
            } else {
                System.out.println("FIN DEL TURNO, TOQUE ENTER PARA CONTINUAR");

            }
            input.nextLine();

        }

        input.nextLine();
        return true;
    }

    public static void mostrarMatriz(Tablero tablero, String letraUno, String letraDos) {
        String[][] tabla = tablero.getTabla();
        for (int i = 0; i < tabla.length; i++) {
            for (int j = 0; j < tabla[0].length; j++) {
                if (tabla[i][j].equals(letraUno)) {
                    System.out.print("\033[31m" + tabla[i][j] + "\t");
                } else if (tabla[i][j].equals(letraDos)) {
                    System.out.print("\033[34m" + tabla[i][j] + "\t");
                } else {
                    System.out.print("\033[32m" + tabla[i][j] + "\t");
                }
            }
            System.out.println();
        }
    }

    public static void mostrarDados(int[] dados) {

        System.out.println("LA JUGADA DE DADOS ES: ");

        for (int i = 0; i < 5; i++) {

            System.out.print("Dado: " + i + "-->" + dados[i] + "   ");

        }
        System.out.println("");
        System.out.println("-----------------------------------------");

    }

    public static boolean menuTurno(int[] dados, Dados juego, Partida partida) {
        Scanner input = new Scanner(System.in);
        boolean isValid = false;
        boolean seguirJugando = true;

        while (!isValid) {

            String mensaje = "=========================================================" + "\n";
            mensaje += "INDICAR OPCION" + "\n";
            mensaje += "=========================================================" + "\n";
            mensaje += "X. ABANDONA Y PIERDE LA PARTIDA \n";
            mensaje += "P. PASAR EL TURNO               \n";
            mensaje += "0. USAR SOLO EL DADO BASE      \n";
            mensaje += "A. PEDIR AYUDA                 \n";
            mensaje += "SELECCIONAR DADOS (N1 N2 N3 N4)                 \n";
            mensaje += "==========================================================" + "\n";

            System.out.println(mensaje);

            String eleccion = input.nextLine().toUpperCase();

            switch (eleccion) {

                case "X":

                    //<editor-fold desc="ABANDONA Y PIERDE">
                    isValid = juego.abandonarPartida(partida);
                    System.out.println("HAS ABANDONADO LA PARTIDA!");
                    isValid = true;
                    seguirJugando = false;
                    break;
                // </editor-fold> 

                case "P":

                    //<editor-fold desc="PASA EL TURNO"> 
                    isValid = juego.pasarTurno(partida);
                    System.out.println("PASO EL TURNO");
                    
                    juego.completarTablero(partida);

                    break;
                // </editor-fold> 

                case "0":

                    //<editor-fold desc="USA SOLO DADO BASE"> 
                    System.out.println("USA SOLO DADO BASE");
                    isValid = juego.cargarJugada(dados[0], partida);
                    if (!isValid) {
                        System.out.println("\u001B[35mLA POSICION SELECCIONADA YA ESTA OCUPADA");
                    } else {
                        juego.pasarTurno(partida);
                    }

                    break;
                // </editor-fold> 

                case "A":

                    //<editor-fold desc="PEDIR AYUDA"> 
                    boolean[] ayuda = juego.solicitarAyuda(dados, partida);
                    boolean[] complicarla = juego.complicarAyuda(dados, partida);
                    boolean existeJugada = false;
                    int j = 0;
                    while (j < 5 && !existeJugada) {
                        existeJugada = ayuda[j];
                        j++;
                    }

                    if (existeJugada) {
                        System.out.println("Mejor Jugada Posible");
                        for (int i = 0; i < 5; i++) {
                            if (ayuda[i]) {
                                System.out.println("Usar dado " + (i));
                            }
                        }
                        System.out.println("Mejor Jugada Posible para Complicar al Otro");
                        for (int i = 0; i < 5; i++) {
                            if (complicarla[i]) {
                                System.out.println("Usar dado " + (i));
                            }
                        }
                    } else {
                        System.out.println("\u001B[36mNo existe jugada posible");
                    }

                    System.out.println("PEDIR AYUDA");

                    break;
                // </editor-fold> 

                default:

                    //<editor-fold desc="ELEGIR VARIOS DADOS"> 
                    System.out.println("ELEGIR VARIOS DADOS");

                    eleccion = eleccion.toUpperCase();
                    int posicion = dados[0];
                    if (eleccion.contains("N1")) {
                        isValid = true;
                        posicion += dados[1];
                    }
                    if (eleccion.contains("N2")) {
                        posicion += dados[2];
                        isValid = true;
                    }
                    if (eleccion.contains("N3")) {
                        posicion += dados[3];
                        isValid = true;
                    }
                    if (eleccion.contains("N4")) {
                        posicion += dados[4];
                        isValid = true;
                    }
                    if (isValid) {
                        System.out.println("LA SUMA DE LA JUGADA ES: " + posicion);
                        if (posicion < 21) {
                            isValid = juego.cargarJugada(posicion, partida);
                            if (isValid) {
                                juego.pasarTurno(partida);
                            } else {
                                System.out.println("\u001B[35mLA POSICION SELECCIONADA YA ESTA OCUPADA");
                            }

                        } else {
                            System.out.println("\u001B[35mLA SUMA DE LA JUGADA NO PUEDE SER MAYOR A 20");
                            isValid = false;
                        }

                    }else{
                    
                        System.out.println("\u001B[35mELECCION INCORRECTA, INGRESE NUEVAMENTE");
                    
                    }
                    break;
                // </editor-fold>     

            }

        }
        return seguirJugando;
    }

}
