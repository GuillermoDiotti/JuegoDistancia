// Juan Peyrot (num)
// Guillermo Diotti 285578

package obligatorio;

import java.util.*;

public class Sistema {

    private ArrayList<Usuario> listaJugadores;

    public ArrayList getListaJugadores() {
        return this.listaJugadores;
    }

    public void addJugador(Usuario jugador) {
        this.listaJugadores.add(jugador);
    }

    public String toStringJugadores() {
        String res = "";
        for (int i = 0; i < this.listaJugadores.size(); i++) {
            res += ("\n" + (i + 1) + ".  " + this.listaJugadores.get(i).toString());
        }
        return res;
    }

    public String toStringJugadoresRanking() {
        ordenarLista();
        String res = "";
        for (int i = 0; i < this.listaJugadores.size(); i++) {
            res += ("\n" + (i + 1) + ".  " + this.listaJugadores.get(i).toStringRanking());
        }
        return res;
    }

    public Sistema() {
        this.listaJugadores = new ArrayList<>();
    }

    public ArrayList ordenarLista() {
        Collections.sort(listaJugadores);
        return listaJugadores;
    }

}