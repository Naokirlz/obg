package dominio;

/**
 * @author Cristian Palma - 208443 | Federico Alonso - 182999
 */
public class Jugador implements Comparable<Jugador>{
    
    private String nombre;
    private String alias;
    private int edad;
    private int partidasGanadas;
    private int partidasJugadas;
    
    public Jugador (){
        this.nombre = "";
        this.alias = "";
        this.edad = 0;
        this.partidasGanadas = 0;
        this.partidasJugadas = 0;
    }
    
    public Jugador (int unaEdad, String unAlias, String unNombre){
        this.edad = unaEdad;
        this.alias = unAlias;
        this.nombre = unNombre;
        this.partidasGanadas = 0;
        this.partidasJugadas = 0;
    }

    public String getAlias() {
        return alias;
    }

    public int getEdad() {
        return edad;
    }

    public String getNombre() {
        return nombre;
    }
    
    public int getPartidasGanadas() {
        return partidasGanadas;
    }

    public int getPartidasJugadas() {
        return partidasJugadas;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setPartidasGanadas(int pardidasGanadas) {
        this.partidasGanadas = pardidasGanadas;
    }

    public void setPartidasJugadas(int partidasJugadas) {
        this.partidasJugadas = partidasJugadas;
    }

    @Override
    public boolean equals(Object o){
        
        Jugador unJugador = (Jugador) o;
        
        return this.getAlias().equals(unJugador.getAlias());
    }
    
    @Override
    public String toString(){
        
        int percent = (int) (((double) this.getPartidasGanadas() / this.getPartidasJugadas()) * 100);
        String porcentaje = Integer.toString(percent);
        String msj = this.getAlias();
        msj += " Nombre: " + this.getNombre() + "\n";
        msj += "Partidas Ganadas: " + this.getPartidasGanadas()+ "\n";
        msj += "Partidas Jugadas: " + this.getPartidasJugadas()+ "\n";
        msj += "Porcentaje de victorias: " + porcentaje + " % \n";
        
        return msj;
    }

    @Override
    public int compareTo(Jugador o) {
        
        int diff = (int) o.getPartidasGanadas() - this.getPartidasGanadas();
        
        if(diff == 0){
            diff = (int) this.getPartidasJugadas() - o.getPartidasJugadas();
        }
        
        return diff;
    }
}