package dominio;

/**
 *
 * @author Cristian Palma - 208443 | Federico Alonso - 182999
 */
public class Tablero {

    private String[][] tabla;

    public Tablero() {
        String[][] tab = new String[4][5];

        for (int i = 0; i < 20; i++) {
            tab[(int) ((i) / 5)][(int) ((i) % 5)] = String.valueOf(i + 1);
        }
        this.tabla = tab;
    }

    public String[][] getTabla() {
        return this.tabla;
    }
    
    private boolean esLetra(int posicion, String letraJU, String letraJD) {
        boolean esLetra = false;
        String elemento = tabla[(int) ((posicion - 1) / 5)][(int) ((posicion - 1) % 5)];
        if (elemento.equals(letraJU)
                || elemento.equals(letraJD)) {
            esLetra = true;
        }

        return esLetra;
    }

    public boolean cargarJugada(int posicion, String letra, String letraJU, String letraJD) {

        boolean incorrecto = esLetra(posicion, letraJU, letraJD);
        if (!incorrecto) {
            tabla[(int) ((posicion - 1) / 5)][(int) ((posicion - 1) % 5)] = letra;
        }

        return !incorrecto;
    }

    public boolean tableroCompleto(String letraJU, String letraJD) {
        boolean completo = true;

        int i = 0;

        while (i < 20 && completo) {
            if (!tabla[(int) ((i) / 5)][(int) ((i) % 5)].equals(letraJU)
                    && !tabla[(int) ((i) / 5)][(int) ((i) % 5)].equals(letraJD)) {
                completo = false;
            }
            i++;
        }

        return completo;
    }

    public int[] calcularPuntaje(String letraJU, String letraJD){
        int[] puntaje = new int[2];
        int puntos = 0;
        String anterior = "";
        int repetido = 1;
        
        //calcular puntaje por filas
        puntaje = puntajeFila(puntaje, letraJU);
//        // la siguiente diagonal \
        puntaje = puntajeDiagonalNwSe(puntaje, letraJU);
//        // la siguiente diagonal /
        puntaje = puntajeDiagonalNeSw(puntaje, letraJU);
//        //calcular puntaje en vertical
        puntaje = puntajeColumna(puntaje, letraJU);
        
        return puntaje;
    }
    
    public int[] puntajeFila(int[] puntaje, String letraJU) {
        String anterior = "";
        int repetido = 0;
        for (int i = 0; i < 4; i++) {
            anterior = "";
            repetido = 1;
            for (int j = 0; j < 5; j++) {
                if (anterior.equals(this.tabla[i][j])) {
                    repetido++;
                } else {
                    //puede ser el primero de la fila o cuando cambia de valor
                    //cambia de valor
                    if (repetido >= 3) {
                        if (anterior.equals(letraJU)) {
                            puntaje[0] += repetido;
                        } else {
                            puntaje[1] += repetido;
                        }
                    }
                    //primero de la fila
                    anterior = this.tabla[i][j];
                    repetido = 1;
                }

                //si llegué al final, sumo el puntaje si corresponde
                if (j == 4 && repetido >= 3) {
                    if (anterior.equals(letraJU)) {
                        puntaje[0] += repetido;
                    } else {
                        puntaje[1] += repetido;
                    }
                }
            }
        }
        return puntaje;
    }

    public int[] puntajeColumna(int[] puntaje, String letraJU){
        int repetido;
        String anterior;
        //calcular puntaje en vertical
        for(int j=0; j<5; j++){
            anterior = "";
            repetido = 1;
            for(int i=0; i<4; i++){
                
                if(anterior.equals(this.tabla[i][j])){
                    repetido++;
                }else{
                    //puede ser el primero de la fila o cuando cambia de valor
                    //cambia de valor
                    if(repetido >= 3){
                        if(anterior.equals(letraJU)){
                            puntaje[0] += repetido;
                        }else{
                            puntaje[1] += repetido;
                        }
                    }
                    //primero de la fila
                    anterior = this.tabla[i][j];
                    repetido = 1;
                }
                
                //si llegué al final, sumo el puntaje si corresponde
                if(i == 3 && repetido >= 3){
                    if(anterior.equals(letraJU)){
                        puntaje[0] += repetido;
                    }else{
                        puntaje[1] += repetido;
                    }
                }
            }
        }
        return puntaje;
    }
    
    public int[] puntajeDiagonalNeSw(int[] puntaje, String letraJU){
    
        int repetido = 0;
        String anterior = "";
        
        int x = 3;
        int y = 3;
        
        // la siguiente diagonal /
        for(int i=0; i<8; i++){
            anterior = "";
            repetido = 1;
            
            int posX = 0;
            int posY = 0;
            
            if(i<4){
                posX = 4;
                posY = y;
                while(posX>0 && posY<4){
                    if(anterior.equals(this.tabla[posY][posX])){
                        repetido++;
                    }else{
                        //puede ser el primero de la fila o cuando cambia de valor
                        //cambia de valor
                        if(repetido >= 3){
                            if(anterior.equals(letraJU)){
                                puntaje[0] += repetido;
                            }else{
                                puntaje[1] += repetido;
                            }
                        }
                        //primero de la fila
                        anterior = this.tabla[posY][posX];
                        repetido = 1;
                    }
                    //si llegué al final, sumo el puntaje si corresponde
                    if((posX == 0 || posY == 3) && repetido >= 3){
                        if(anterior.equals(letraJU)){
                            puntaje[0] += repetido;
                        }else{
                            puntaje[1] += repetido;
                        }
                    }
                    posX--;
                    posY++;
                }
                y--;
            }else{
                posX = x;
                posY = 0;
                
                while(posX>=0 && posY<4){
                    if(anterior.equals(this.tabla[posY][posX])){
                        repetido++;
                    }else{
                        //puede ser el primero de la fila o cuando cambia de valor
                        //cambia de valor
                        if(repetido >= 3){
                            if(anterior.equals(letraJU)){
                                puntaje[0] += repetido;
                            }else{
                                puntaje[1] += repetido;
                            }
                        }
                        //primero de la fila
                        anterior = this.tabla[posY][posX];
                        repetido = 1;
                    }
                    //si llegué al final, sumo el puntaje si corresponde
                    if((posX == 0 || posY == 3) && repetido >= 3){
                        if(anterior.equals(letraJU)){
                            puntaje[0] += repetido;
                        }else{
                            puntaje[1] += repetido;
                        }
                    }
                    posX--;
                    posY++;
                }
                x--;
            }
        }
        
        return puntaje;
    }
     
    public int[] puntajeDiagonalNwSe(int[] puntaje, String letraJU) {

        int repetido = 0;
        String anterior = "";

        int x = 1;
        int y = 3;

        // la siguiente diagonal \
        for (int i = 0; i < 8; i++) {
            anterior = "";
            repetido = 1;

            int posX = 0;
            int posY = 0;

            if (i < 4) {
                posX = 0;
                posY = y;
                while (posX < 5 && posY < 4) {
                    if (anterior.equals(this.tabla[posY][posX])) {
                        repetido++;
                    } else {
                        //puede ser el primero de la fila o cuando cambia de valor
                        //cambia de valor
                        if (repetido >= 3) {
                            if (anterior.equals(letraJU)) {
                                puntaje[0] += repetido;
                            } else {
                                puntaje[1] += repetido;
                            }
                        }
                        //primero de la fila
                        anterior = this.tabla[posY][posX];
                        repetido = 1;
                    }
                    //si llegué al final, sumo el puntaje si corresponde
                    if ((posX == 4 || posY == 3) && repetido >= 3) {
                        if (anterior.equals(letraJU)) {
                            puntaje[0] += repetido;
                        } else {
                            puntaje[1] += repetido;
                        }
                    }
                    posX++;
                    posY++;
                }
                y--;
            } else {
                posX = x;
                posY = 0;

                while (posX < 5 && posY < 4) {
                    if (anterior.equals(this.tabla[posY][posX])) {
                        repetido++;
                    } else {
                        //puede ser el primero de la fila o cuando cambia de valor
                        //cambia de valor
                        if (repetido >= 3) {
                            if (anterior.equals(letraJU)) {
                                puntaje[0] += repetido;
                            } else {
                                puntaje[1] += repetido;
                            }
                        }
                        //primero de la fila
                        anterior = this.tabla[posY][posX];
                        repetido = 1;
                    }
                    //si llegué al final, sumo el puntaje si corresponde
                    if ((posX == 4 || posY == 3) && repetido >= 3) {
                        if (anterior.equals(letraJU)) {
                            puntaje[0] += repetido;
                        } else {
                            puntaje[1] += repetido;
                        }
                    }
                    posX++;
                    posY++;
                }
                x++;
            }
        }
        return puntaje;
    }

    public boolean[] solicitarAyuda(int[] dados, String letraJU, String letraJD, String letraJA) {
        boolean esLetra = true;

        boolean[] ayuda = new boolean[5];
        ayuda[0] = true;
        int puntajeMaximo = Integer.MIN_VALUE;
        int caso = 0;
        boolean[] mejorAyuda = new boolean[5];

        //int i = 0;
        // solo el base ||| base + 1 (C4,1) ||| base + 2 (C4,2)....
        int total = 1 + 4 + 6 + 4 + 1;

        for (int i = 0; i < total; i++) {

            //reseteo el dado
            for (int j = 1; j < 5; j++) {
                ayuda[j] = false;
            }

            int suma = dados[0];

            //combinaciones posibles de dados
            switch (i + 1) {
                case 1:
                    break;
                case 2:
                    ayuda[1] = true;
                    suma += dados[1];
                    break;
                case 3:
                    ayuda[2] = true;
                    suma += dados[2];
                    break;
                case 4:
                    ayuda[3] = true;
                    suma += dados[3];
                    break;
                case 5:
                    ayuda[4] = true;
                    suma += dados[4];
                    break;
                case 6:
                    ayuda[1] = true;
                    ayuda[2] = true;
                    suma += dados[1] + dados[2];
                    break;
                case 7:
                    ayuda[1] = true;
                    ayuda[3] = true;
                    suma += dados[1] + dados[3];
                    break;
                case 8:
                    ayuda[1] = true;
                    ayuda[4] = true;
                    suma += dados[1] + dados[4];
                    break;
                case 9:
                    ayuda[2] = true;
                    ayuda[3] = true;
                    suma += dados[2] + dados[3];
                    break;
                case 10:
                    ayuda[2] = true;
                    ayuda[4] = true;
                    suma += dados[2] + dados[4];
                    break;
                case 11:
                    ayuda[3] = true;
                    ayuda[4] = true;
                    suma += dados[3] + dados[4];
                    break;
                case 12:
                    ayuda[1] = true;
                    ayuda[2] = true;
                    ayuda[3] = true;
                    suma += dados[1] + dados[2] + dados[3];
                    break;
                case 13:
                    ayuda[1] = true;
                    ayuda[2] = true;
                    ayuda[4] = true;
                    suma += dados[1] + dados[2] + dados[4];
                    break;
                case 14:
                    ayuda[2] = true;
                    ayuda[3] = true;
                    ayuda[4] = true;
                    suma += dados[2] + dados[3] + dados[4];
                    break;
                case 15:
                    ayuda[1] = true;
                    ayuda[3] = true;
                    ayuda[4] = true;
                    suma += dados[1] + dados[3] + dados[4];
                    break;
                case 16:
                    ayuda[1] = true;
                    ayuda[2] = true;
                    ayuda[3] = true;
                    ayuda[4] = true;
                    suma += dados[1] + dados[2] + dados[3] + dados[4];
                    break;
            }
            
            if (suma>20){
                suma = dados[0];
            }

            esLetra = esLetra(suma, letraJU, letraJD);
            if (!esLetra) {
                Tablero nuevaTabla = new Tablero();
                for (int fila = 0; fila < 4; fila++) {
                    for (int col = 0; col < 5; col++) {
                        nuevaTabla.tabla[fila][col] = this.tabla[fila][col];
                    }
                }
                nuevaTabla.cargarJugada(suma, letraJA, letraJU, letraJD);
                int[] nuevoPuntaje = nuevaTabla.calcularPuntaje(letraJU, letraJD);
                if (letraJA.equals(letraJU)) {
                    int puntosJugada = nuevoPuntaje[0];
                    if (puntosJugada > puntajeMaximo) {
                        puntajeMaximo = puntosJugada;
                        caso = i + 1;
                        for (int j = 0; j < 5; j++) {
                            mejorAyuda[j] = ayuda[j];
                        }
                    }
                } else {
                    int puntosJugada = nuevoPuntaje[1];
                    if (puntosJugada > puntajeMaximo) {
                        puntajeMaximo = puntosJugada;
                        caso = i + 1;
                        for (int j = 0; j < 5; j++) {
                            mejorAyuda[j] = ayuda[j];
                        }
                    }
                }

            }
        }

//        if(esLetra){
//            for(int j=0; j<5; j++){
//                ayuda[j] = false;
//            }
//        }
        return mejorAyuda;
    }

    public boolean inalcanzable(String letraJU, String letraJD, String letraJM, String letraJO, int puntajeOtro){
        boolean alcanzable = false;
        
        Tablero nuevoTablero = new Tablero();
        for(int fila=0; fila<4; fila++){
            for(int col=0; col<5; col++){
                if(!this.tabla[fila][col].equals(letraJO)){
                    nuevoTablero.tabla[fila][col] = letraJM;
                }else{
                    nuevoTablero.tabla[fila][col] = letraJO;
                }
            }
        }
        
        int[] puntaje = nuevoTablero.calcularPuntaje(letraJU, letraJD);
        if(letraJU.equals(letraJM)){
            int puntos = puntaje[0];
            alcanzable = (puntos>=puntajeOtro);
        }else{
            int puntos = puntaje[1];
            alcanzable = (puntos>=puntajeOtro);
        }
        return !alcanzable;
    }
    
    @Override
    public String toString() {
        return this.tabla.toString();
    }

    public String mostrarMatriz() {
        String msj = "";
        for (int i = 0; i < this.tabla.length; i++) {
            for (int j = 0; j < this.tabla[0].length; j++) {
                msj += this.tabla[i][j] + " ";
            }
            msj.substring(0, msj.length() - 1);
            msj += "|";
        }
        msj.substring(0, msj.length() - 1);
        return msj;
    }

    
}
