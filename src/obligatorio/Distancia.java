// Juan Peyrot (num)
// Guillermo Diotti 285578

package obligatorio;

public class Distancia {

    public static void main(String[] args) {
        Partida partida = new Partida();
        Sistema sistema = new Sistema();
        partida.setPartida(partida); 

        Interfaz.menu(sistema, partida);

        System.out.println("\nGracias por probar nuestro juego :)");
    }
}