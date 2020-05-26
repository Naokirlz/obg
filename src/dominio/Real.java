package dominio;
/**
 * @author Cristian Palma - 208443 | Federico Alonso - 182999
 */
public class Real extends Partida {

    public Real(Jugador uno, Jugador dos, String cUno, String cDos) {
        super.setJugadorUno(uno);
        super.setJugadorDos(dos);
        super.setLetraJugadorUno(cUno);
        super.setLetraJugadorDos(cDos);
    }
    
    @Override
    public boolean abandonarPartida() {
        if (this.jugadorActual == 1) {

            setGanador(this.jugadorUno);

        } else {

            setGanador(this.jugadorDos);
            
        }

        return true;
    }

    @Override
    public boolean finalizarPartida() {

        int unGanador = this.ganadorPartida();

        switch (unGanador) {
            case 1:
                setGanador(this.jugadorUno);
                break;
            case 2:
                setGanador(this.jugadorDos);
                break;
            default:
                setGanador(null);
                break;
        }

        return true;
    }

    @Override
    public int[] tirarDados(int pos, int valor) {

        int[] dados = new int[5];

        for (int i = 0; i < dados.length; i++) {

            dados[i] = (int) Math.floor(Math.random() * (6 - 1 + 1) + 1);

        }

        return dados;

    }

}