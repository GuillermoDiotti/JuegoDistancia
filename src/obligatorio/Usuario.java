package obligatorio;

public class Usuario implements Comparable<Usuario> {

    private String nombre;
    private int edad;
    private String alias;
    private int cantGanadas;
    private int cantJugadas;
    
    public String getAlias(){
        return this.alias;
    }
    
    public int getCantGanadas(){
        return this.cantGanadas;
    }
    
    public int getCantJugadas(){
        return this.cantJugadas;
    }
    
    public void setAlias( String al){
        this.alias = al;
    }
    
    public void setCantGanadas( int n ){
        this.cantGanadas = n;
    }
    
    public void setCantJugadas( int n ){
        this.cantJugadas = n;
    }

    @Override
    public String toString() {
        return this.alias + "  (" + this.nombre + ", " + this.edad + ")";
    }

    public String toStringRanking() {
        return this.alias + "  (Partidas jugadas " + this.cantJugadas + ", Partidas ganadas: " + this.cantGanadas + ")";
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getEdad() {
        return this.edad;
    }

    public void setNombre(String name) {
        this.nombre = name;
    }

    public void setEdad(int age) {
        this.edad = age;
    }

    
    public Usuario(String name, int age, String nick) {
        setNombre(name);
        setEdad(age);
        setAlias(nick);
        setCantGanadas(0);
        setCantJugadas(0);
    }

    public int compareTo(Usuario unUsuario){
        return unUsuario.getCantGanadas() - this.getCantGanadas();
    }
    
}