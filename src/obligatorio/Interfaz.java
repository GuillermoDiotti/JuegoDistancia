// Juan Peyrot (num)
// Guillermo Diotti 285578

package obligatorio;

import java.util.*;

public class Interfaz {

    

    public static void menu(Sistema sistema, Partida partida) {
        Scanner in = new Scanner(System.in);
        int opcion = 0;
        while (opcion != 5) {
            opcionesMenu();
            System.out.println("\nElija su opcion (1-5)");
            opcion = pedirEntero("Opcion: ", 1, 5);
            switch (opcion) {
                case 1:
                    sistema.addJugador(opcion1(sistema));
                    break;
                case 2:
                    opcion2(partida);
                    break;
                case 3:
                    opcion3(sistema, partida);
                    break;
                case 4:
                    System.out.println("Ranking: ");
                    opcion4(sistema);
                    break;
                case 5:
                    break;
            }
        }
    }

    public static void opcionesMenu() {
        System.out.println("");
        System.out.println(" --------------- ");
        System.out.println("|      MENU     |");
        System.out.println(" --------------- ");
        System.out.println("");
        System.out.println("1. Ingresar jugadores");
        System.out.println("2. Elegir Tablero");
        System.out.println("3. Iniciar Partida");
        System.out.println("4. Rankings");
        System.out.println("5. Fin");
    }

    public static Usuario opcion1(Sistema sistema) {
        Scanner in = new Scanner(System.in);
        String nombre = "";
        int edad = 0;
        String alias = "";
        System.out.print("Nombre: ");
        nombre = in.nextLine();
        edad = pedirEntero("Edad: ", 12, 99);
        System.out.print("Alias: ");
        alias = in.nextLine();
        boolean bool = chequearAliasRepetido(alias, sistema.getListaJugadores());
        while (bool) {
            System.out.println("\nAlias ya existente, ingrese otro nuevo\n");
            System.out.print("Alias: ");
            alias = in.nextLine();
            bool = chequearAliasRepetido(alias, sistema.getListaJugadores());
        }
        System.out.println("\nJugador ingresado con exito");
        Usuario jugador = new Usuario(nombre, edad, alias);
        return jugador;
    }

    public static boolean chequearAliasRepetido(String apodo, ArrayList<Usuario> listaJugadores) {
        boolean bool = false;
        for (int i = 0; i < listaJugadores.size(); i++) {
            if (listaJugadores.get(i).getAlias().equals(apodo)) {
                bool = true;
            }
        }
        return bool;
    }

    public static void opcion2(Partida partida) {
        Scanner in = new Scanner(System.in);
        System.out.println("\nElija el tablero:\n");
        System.out.println("0 - Tablero Standard");
        System.out.println("1 - Precargado 1 para pruebas");
        System.out.println("2 - Precargado 2 para pruebas");
        int tableroElegido = pedirEntero("Tablero: ", 0, 2);
        switch (tableroElegido) {
            case 0 -> {
                System.out.println("\n\nHas elegido: Tablero Standard\n\n");
            }
            case 1 -> {
                System.out.println("\n\nHas elegido: Precargado 1 para pruebas\n\n");
            }
            case 2 -> {
                System.out.println("\n\nHas elegido: Precargado 2 para pruebas\n\n");
            }
        }
        partida.setTablero(tableroElegido);
    }

    public static void opcion3(Sistema sistema, Partida partida) {
        Scanner in = new Scanner(System.in);
        if (sistema.getListaJugadores().size() >= 2) {
            System.out.println("Elija al jugador " + "\u001B[31m" + "ROJO" + "\u001B[0m");
            System.out.println(sistema.toStringJugadores());
            int intJugadorRojo = pedirEntero("Jugador Rojo: ", 1, sistema.getListaJugadores().size());
            Usuario jugadorRojo = (Usuario) sistema.getListaJugadores().get(intJugadorRojo - 1);
            partida.setRojo(jugadorRojo);
            jugadorRojo.setCantJugadas(jugadorRojo.getCantJugadas() + 1);
            System.out.println("Elija al jugador " + "\u001B[34m" + "AZUL" + "\u001B[0m");
            System.out.println(sistema.toStringJugadores());
            int intJugadorAzul = pedirEntero("Jugador Azul: ", 1, sistema.getListaJugadores().size());
            while (intJugadorAzul == intJugadorRojo) {
                System.out.println("\nEste jugador ya ha sido seleccionado, debes elegir uno diferente\n");
                System.out.println("\nJugador invalido, ingrese un numero entre 1 y " + sistema.getListaJugadores().size() + " (ambos inclusives)\n");
                intJugadorAzul = pedirEntero("Jugador Azul: ", 1, sistema.getListaJugadores().size());
            }
            Usuario jugadorAzul = (Usuario) sistema.getListaJugadores().get(intJugadorAzul - 1);
            partida.setAzul(jugadorAzul);
            jugadorAzul.setCantJugadas(jugadorAzul.getCantJugadas() + 1);
            System.out.println("\n\nJUEGAN: " + jugadorRojo.getAlias() + " VS " + jugadorAzul.getAlias());

            String ganador = partida.jugar();
            if (ganador.equals("A")) {
                System.out.println("Ganador: " + jugadorAzul.getAlias());
                jugadorAzul.setCantGanadas(jugadorAzul.getCantGanadas() + 1);
            }
            else if (ganador.equals("R")) {
                System.out.println("Ganador: " + jugadorRojo.getAlias());
                jugadorRojo.setCantGanadas(jugadorRojo.getCantGanadas() + 1);
            }
        } else {
            System.out.println("\n\nCantidad de jugadores insuficiente, se necesita un minimo de 2 jugadores para comenzar a jugar\n\n");
        }
    }

    public static void opcion4(Sistema sistema) {
        System.out.println(sistema.toStringJugadoresRanking());
    }

    public static int pedirEntero(String mensaje, int min, int max) {
        Scanner in = new Scanner(System.in);
        int dato = 0;
        boolean ok = false;
        while (!ok) {
            try {
                System.out.print(mensaje);
                dato = in.nextInt();
                in.nextLine();
                if (dato < min || dato > max) {
                    System.out.println("Valor fuera de rango, reingrese");
                } else {
                    ok = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("\nIngrese solo numeros");
                in.nextLine();
            }
        }
        return dato;
    }

}