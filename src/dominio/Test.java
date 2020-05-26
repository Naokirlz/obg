package dominio;
/**
 * @author Cristian Palma - 208443 | Federico Alonso - 182999
*/

public class Test extends Partida {

    private final int [] dados = new int [5];

    public Test(Jugador uno, Jugador dos, String cUno, String cDos) {
        super.setJugadorUno(uno);
        super.setJugadorDos(dos);
        super.setLetraJugadorUno(cUno);
        super.setLetraJugadorDos(cDos);
    }
    
    public void setDados(int pos, int valor){
        dados[pos] = valor;
    }
    
    public int [] getDados(){
        return this.dados;
    }

    @Override
    public int[] tirarDados(int pos, int valor) {
        this.setDados(pos, valor);

        return dados;
    }

}