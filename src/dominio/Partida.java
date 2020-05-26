package dominio;

/**
 * @author Cristian Palma - 208443 | Federico Alonso - 182999
 */
public class Partida {

    private Tablero tablero;
    protected Jugador ganador;
    protected int jugadorActual;
    protected Jugador jugadorUno;
    protected Jugador jugadorDos;
    private String letraJugadorUno;
    private String letraJugadorDos;

    public Partida() {
        this.jugadorUno = null;
        this.jugadorDos = null;
        this.tablero = new Tablero();
        this.jugadorActual = 0;
        this.letraJugadorUno = "";
        this.letraJugadorDos = "";
        this.ganador = null;
    }

    public Partida(Jugador unJugadorUno, Jugador unJugadorDos, String unaLetraJUno, String unaLetraJDos) {
        this.jugadorUno = unJugadorUno;
        this.jugadorDos = unJugadorDos;
        this.tablero = new Tablero();
        this.jugadorActual = 1;
        this.letraJugadorUno = unaLetraJUno;
        this.letraJugadorDos = unaLetraJDos;
        this.ganador = null;
    }

    public Jugador getGanador() {
        return ganador;
    }

    public int getJugadorActual() {
        return jugadorActual;
    }

    public Jugador getJugadorUno() {
        return jugadorUno;
    }

    public Jugador getJugadorDos() {
        return jugadorDos;
    }

    public String getLetraJugadorUno() {
        return letraJugadorUno;
    }

    public String getLetraJugadorDos() {
        return letraJugadorDos;
    }

    public Tablero getTablero() {
        return this.tablero;
    }

    public void setGanador(Jugador ganador) {
        this.ganador = ganador;
    }
    
    public void setJugadorUno(Jugador jugadorUno) {
        this.jugadorUno = jugadorUno;
    }

    public void setJugadorDos(Jugador jugadorDos) {
        this.jugadorDos = jugadorDos;
    }

    public void setJugadorActual(int jugadorActual) {
        this.jugadorActual = jugadorActual;
    }

    public void setLetraJugadorUno(String letraJugadorUno) {
        this.letraJugadorUno = letraJugadorUno;
    }

    public void setLetraJugadorDos(String letraJugadorDos) {
        this.letraJugadorDos = letraJugadorDos;
    }

    private void setTablero() {
        this.tablero = new Tablero();
    }

    public boolean[] solicitarAyuda(int[] dados, boolean complicarla) {
        
        String letraJA = this.letraJugadorDos;
        
        if (this.jugadorActual == 0) {
            letraJA = this.letraJugadorUno;
        }
        //Misma función, lo único que cambia es que considero a jugador actual como el contrincante, 
        //La combinación que sume más puntos es la que debo bloquear para complicarlo
        if (complicarla) {
            if (this.jugadorActual == 1) {
                letraJA = this.letraJugadorUno;
            }
        }
        
        return this.tablero.solicitarAyuda(dados, this.letraJugadorUno, this.letraJugadorDos, letraJA);
    }

    protected int ganadorPartida() {
        int unGanador = 1;
        setGanador(this.jugadorUno);
        
        int[] puntajes = this.tablero.calcularPuntaje(letraJugadorUno, letraJugadorDos);

        if (puntajes[0] < puntajes[1]) {
            unGanador = 2;
            setGanador(this.jugadorDos);
        } else if (puntajes[0] == puntajes[1]) {
            unGanador = 0;
            setGanador(null);
        }

        return unGanador;
    }

    public boolean pasarTurno() {
        if (jugadorActual == 0) {
            jugadorActual++;
        } else {
            jugadorActual = 0;
        }
        return true;
    }

    public boolean cargarJugada(int posicion) {
        String letra = letraJugadorDos;
        if (jugadorActual == 0) {
            letra = letraJugadorUno;
        }
        boolean realizado = this.tablero.cargarJugada(posicion, letra, letraJugadorUno, letraJugadorDos);
        return realizado;
    }

    public boolean tableroCompleto() {
        boolean completo = this.tablero.tableroCompleto(letraJugadorUno, letraJugadorDos);
        return completo;
    }

    public int[] calcularPuntaje() {
        int[] puntaje = this.tablero.calcularPuntaje(letraJugadorUno, letraJugadorDos);
        return puntaje;
    }

    public Jugador devolverJugadorActual() {

        Jugador jugador = jugadorUno;

        if (jugadorActual == 1) {
            jugador = jugadorDos;
        }

        return jugador;
    }

    /**
     * **** FUNCION INALCANZABLE, MUESTRA SI EL JUGADOR PUEDE ALCANZAR AL
     * OTRO EN PUNTOS O PERDIO
     *
     *****
     * @return boolean
     */
    public boolean inalcanzable() {
        /* letraJM letra jugador mio */
        String letraJM = this.letraJugadorDos;
        /* letraJM letra jugador otro*/
        String letraJO = this.letraJugadorUno;
        int[] puntaje = this.calcularPuntaje();
        int puntajeOtro = puntaje[0];
        if (this.jugadorActual == 0) {
            letraJM = this.letraJugadorUno;
            letraJO = this.letraJugadorDos;
            puntajeOtro = puntaje[1];
        }

        boolean inalcanzable = this.tablero.inalcanzable(letraJugadorUno, letraJugadorDos, letraJM, letraJO, puntajeOtro);

        return inalcanzable;
    }

    public int[] tirarDados(int pos, int valor) {

        int[] retorno = new int[5];

        return retorno;

    }

    //<editor-fold desc="MÉTODOS OVERRIDE"> 
    public boolean abandonarPartida() {

        return true;

    }

    public boolean finalizarPartida() {

        return true;

    }
    //</editor-fold>
}
