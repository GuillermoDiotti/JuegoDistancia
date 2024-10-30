// Juan Peyrot 275076
// Guillermo Diotti 285578

package obligatorio;

import java.util.*;

public class Partida {

    private Partida partida;

    private Tablero tablero = prepararTableros().get(0);

    private Tablero tableroGuias;
    
    private Tablero tableroGuiasAux;

    private Usuario azul;

    private Usuario rojo;

    private int[][] matDistancia = {
        {6, 5, 4, 4, 5, 6},
        {5, 3, 2, 2, 3, 5},
        {4, 2, 1, 1, 2, 4},
        {4, 2, 1, 1, 2, 4},
        {5, 3, 2, 2, 3, 5},
        {6, 5, 4, 4, 5, 6}
    };

    public String[][] getTableroGuias() {
        return this.tableroGuias.getCuerpo();
    }

    public void setTableroGuias(Tablero tab) {
        tableroGuias = tab;
    }
    
    public String[][] getTableroGuiasAux() {
        return this.tableroGuiasAux.getCuerpo();
    }

    public void setTableroGuiasAux(Tablero tab) {
        tableroGuiasAux = tab;
    }

    public Partida getPartida() {
        return this.partida;
    }

    public Tablero getTablero() {
        return this.tablero;
    }

    public int[][] getMatDistancia() {
        return this.matDistancia;
    }

    public void setPartida(Partida part) {
        this.partida = part;
    }

    public void setTablero(int tab) {
        this.tablero = prepararTableros().get(tab);
    }

    public void modificarTableroPartida(Tablero tab) {
        this.tablero = tab;
    }

    public void setAzul(Usuario azul) {
        this.azul = azul;
    }

    public void setRojo(Usuario rojo) {
        this.rojo = rojo;
    }

    public String jugar() {
        Scanner in = new Scanner(System.in);
        int turno = 0;
        String juega;
        boolean bool;
        String ganador = "";
        ifPartida:
        while ((cantAzules() > 0) && (cantRojas() > 0)) {

            ifSinMovimientos:
            if (true) {

                if (turno % 2 == 0) {
                    System.out.println("Turno del jugador " + "\u001B[31m" + "ROJO:" + "\u001B[0m");
                    juega = "R";
                } else {
                    System.out.println("Turno del jugador " + "\u001B[34m" + "AZUL:" + "\u001B[0m");
                    juega = "A";
                }
                
                if ( !MetodosAuxiliares.hayMovPosibles(partida, juega)){
                    turno++;
                    System.out.println("");
                    System.out.println("El jugador no tiene movimientos posibles");
                    System.out.println("");
                    break ifSinMovimientos;
                }
                
                System.out.println(tablero.toString());
                System.out.println("Ingrese la posicion de inicio");
                String inicio = in.nextLine().toUpperCase();
                if (inicio.equals("X")) {
                    ganador = opuestoDeJuega(juega);
                    break ifPartida;
                }

                bool = verificarPosicion(inicio) && hayFichaDeEseColor(inicio, juega);

                while (!bool) {
                    System.out.println("Posicion incorrecta. Por favor ingrese una posicion valida");
                    System.out.println(getTablero());
                    System.out.println("Ingrese la posicion de inicio");
                    inicio = in.nextLine().toUpperCase();
                    if (inicio.equals("X")) {
                        ganador = opuestoDeJuega(juega);
                        break ifPartida;
                    }
                    bool = verificarPosicion(inicio) && hayFichaDeEseColor(inicio, juega);
                }

                guiasSinCaptura(inicio, juega, partida);
                guiasConCaptura(inicio, juega, partida);

                if (MetodosAuxiliares.cantidadMovimientos(tableroGuias.getCuerpo()) == 0) {
                    System.out.println("");
                    System.out.println("Sin movimientos para esta posicion");
                    System.out.println("Reingrese Jugada");
                    System.out.println("");
                    break ifSinMovimientos;
                }

                System.out.println(tableroGuias.toString());

                System.out.println("Ingrese la posicion final");
                String fin = in.nextLine().toUpperCase();
                if (fin.equals("X")) {
                    ganador = opuestoDeJuega(juega);
                    break ifPartida;
                }
                bool = verificarPosicion(fin) && !hayFichaDeEseColor(fin, juega);
                while (!bool) {
                    System.out.println("Posicion incorrecta. Por favor ingrese una posicion valida");
                    System.out.println("Ingrese la posicion de final");
                    fin = in.nextLine().toUpperCase();
                    if (fin.equals("X")) {
                        ganador = opuestoDeJuega(juega);
                        break ifPartida;
                    }
                    bool = verificarPosicion(fin) && !hayFichaDeEseColor(fin, juega);
                }

                boolean movRealizado = false;
                while (!movRealizado) {
                    int filFin = obtenerFila(fin);
                    int colFin = obtenerColumna(fin);
                    String tab = tableroGuias.getCuerpo()[filFin][colFin];
                    if (tab.equals("#")) {
                        movCaptura();
                        tablero.getCuerpo()[filFin][colFin] = juega;
                        movRealizado = true;
                    } else if (tab.equals("*")) {
                        movCaptura();
                        tablero.getCuerpo()[filFin][colFin] = juega;
                        movRealizado = true;
                    } else {
                        System.out.println(tableroGuias.toString());
                        System.out.println("Posicion incorrecta. Por favor ingrese una posicion valida");
                        System.out.println("Ingrese la posicion de final");
                        fin = in.nextLine().toUpperCase();
                        if (fin.equals("X")) {
                            ganador = opuestoDeJuega(juega);
                            break ifPartida;
                        }
                        bool = verificarPosicion(fin) && !hayFichaDeEseColor(fin, juega);
                        while (!bool) {
                            System.out.println("Posicion incorrecta. Por favor ingrese una posicion valida");
                            System.out.println(tableroGuias.toString());
                            System.out.println("Ingrese la posicion de final");
                            fin = in.nextLine().toUpperCase();
                            if (fin.equals("X")) {
                                ganador = opuestoDeJuega(juega);
                                break ifPartida;
                            }
                            bool = verificarPosicion(fin) && !hayFichaDeEseColor(fin, juega);
                        }

                    }
                }
                if (cantRojas() == 0 || cantAzules() == 0) {
                    break ifPartida;
                }
                setTableroGuias(new Tablero(tablero.getCuerpo()));
                turno++;
            }
        }
        System.out.println("Fin de la partida");

        if (cantRojas() == 0) {
            ganador = "A";
        } else if (cantAzules() == 0) {
            ganador = "R";
        }

        setTablero(0);
        return ganador;
    }

    
    
    
    public void movCaptura() {
        for (int i = 0; i < tableroGuias.getCuerpo().length; i++) {
            for (int j = 0; j < tableroGuias.getCuerpo().length; j++) {
                if (tableroGuias.getCuerpo()[i][j].equals("E")) {
                    tablero.getCuerpo()[i][j] = " ";
                }

            }

        }

    }

    public boolean guiasBool(String dir, String juega, String opuestoDeJuega, int filaInicial, int columnaInicial, int i, int j) {
        boolean ok = false;
        String[][] tab = this.tablero.getCuerpo();

        switch (dir.toLowerCase()) {
            case "arriba":
                while (i >= 0) {

                    if (tab[i][j].equals(juega) && i != filaInicial) {
                        ok = false;
                        break;
                    }

                    if (tab[i][j].equals(opuestoDeJuega)) {
                        ok = true;
                        break;
                    }

                    i--;

                }
                return ok;

            case "abajo":
                while (i <= 5) {

                    if (tab[i][j].equals(juega) && i != filaInicial) {
                        ok = false;
                        break;
                    }

                    if (tab[i][j].equals(opuestoDeJuega)) {
                        ok = true;
                        break;
                    }

                    i++;

                }
                return ok;

            case "izquierda":
                while (j >= 0) {

                    if (tab[i][j].equals(juega) && j != columnaInicial) {
                        ok = false;
                        break;
                    }

                    if (tab[i][j].equals(opuestoDeJuega)) {
                        ok = true;
                        break;
                    }

                    j--;

                }
                return ok;

            case "derecha":
                while (j <= 5) {

                    if (tab[i][j].equals(juega) && j != columnaInicial) {
                        ok = false;
                        break;
                    }

                    if (tab[i][j].equals(opuestoDeJuega)) {
                        ok = true;
                        break;
                    }

                    j++;
                }
                return ok;

            case "arribaiz":
                while (i >= 0 && j >= 0) {

                    if (tab[i][j].equals(juega) && i != filaInicial && j != columnaInicial) {
                        ok = false;
                        break;
                    }

                    if (tab[i][j].equals(opuestoDeJuega)) {
                        ok = true;
                        break;
                    }

                    i--;
                    j--;

                }
                return ok;

            case "arribader":
                while ((i >= 0 && j <= 5)) {

                    if (tab[i][j].equals(juega) && i != filaInicial && j != columnaInicial) {
                        ok = false;
                        break;
                    }

                    if (tab[i][j].equals(opuestoDeJuega)) {
                        ok = true;
                        break;
                    }

                    i--;
                    j++;

                }
                return ok;

            case "abajoiz":
                while (i <= 5 && j >= 0) {

                    if (tab[i][j].equals(juega) && i != filaInicial && j != columnaInicial) {
                        ok = false;
                    }

                    if (tab[i][j].equals(opuestoDeJuega)) {
                        ok = true;
                    }

                    i++;
                    j--;

                }
                return ok;

            case "abajoder":
                while (i <= 5 && j <= 5) {

                    if (tab[i][j].equals(juega) && i != filaInicial && j != columnaInicial) {
                        ok = false;
                        break;
                    }

                    if (tab[i][j].equals(opuestoDeJuega)) {
                        ok = true;
                        break;
                    }

                    i++;
                    j++;

                }
                return ok;
        }

        return ok;
    }

    public void guiasConCaptura(String inicio, String juega, Partida partida) {

        String[][] tab = MetodosAuxiliares.copiarArray(tablero.getCuerpo());

        String opuestoDeJuega = opuestoDeJuega(juega);

        int filaInicial = obtenerFila(inicio);
        int columnaInicial = obtenerColumna(inicio);

        int i = filaInicial;
        int j = columnaInicial;

        boolean hacerArriba = guiasBool("arriba", juega, opuestoDeJuega, filaInicial, columnaInicial, i, j);
        boolean hacerAbajo = guiasBool("abajo", juega, opuestoDeJuega, filaInicial, columnaInicial, i, j);
        boolean hacerIzquierda = guiasBool("izquierda", juega, opuestoDeJuega, filaInicial, columnaInicial, i, j);
        boolean hacerDerecha = guiasBool("derecha", juega, opuestoDeJuega, filaInicial, columnaInicial, i, j);
        boolean hacerArribaIzquierda = guiasBool("arribaiz", juega, opuestoDeJuega, filaInicial, columnaInicial, i, j);
        boolean hacerArribaDerecha = guiasBool("arribader", juega, opuestoDeJuega, filaInicial, columnaInicial, i, j);
        boolean hacerAbajoDerecha = guiasBool("abajoder", juega, opuestoDeJuega, filaInicial, columnaInicial, i, j);
        boolean hacerAbajoIzquierda = guiasBool("abajoiz", juega, opuestoDeJuega, filaInicial, columnaInicial, i, j);

        tab[i][j] = "E";

        if (hacerArriba) {
            MetodosAuxiliares.movimientosConCaptura(inicio, juega, opuestoDeJuega, partida, "arriba");
        }

        if (hacerAbajo) {
            MetodosAuxiliares.movimientosConCaptura(inicio, juega, opuestoDeJuega, partida, "abajo");
        }

        if (hacerDerecha) {
            MetodosAuxiliares.movimientosConCaptura(inicio, juega, opuestoDeJuega, partida, "derecha");
        }

        if (hacerIzquierda) {
            MetodosAuxiliares.movimientosConCaptura(inicio, juega, opuestoDeJuega, partida, "izquierda");
        }

        if (hacerArribaDerecha) {
            MetodosAuxiliares.movimientosConCaptura(inicio, juega, opuestoDeJuega, partida, "arribader");
        }

        if (hacerArribaIzquierda) {
            MetodosAuxiliares.movimientosConCaptura(inicio, juega, opuestoDeJuega, partida, "arribaiz");
        }

        if (hacerAbajoDerecha) {
            MetodosAuxiliares.movimientosConCaptura(inicio, juega, opuestoDeJuega, partida, "abajoder");
        }

        if (hacerAbajoIzquierda) {
            MetodosAuxiliares.movimientosConCaptura(inicio, juega, opuestoDeJuega, partida, "abajoiz");
        }

    }

    public void guiasSinCaptura(String inicio, String juega, Partida partida) {
        
        String[][] tab = MetodosAuxiliares.copiarArray(tablero.getCuerpo());
        setTableroGuias(new Tablero(tab));

        String opuestoDeJuega = opuestoDeJuega(juega);

        int filaInicio = obtenerFila(inicio);
        int columnaInicio = obtenerColumna(inicio);

        int i = filaInicio;
        int j = columnaInicio;
        
        tab[i][j] = "E";

        if (i - 1 >= 0 && MetodosAuxiliares.movimientosSinCaptura(inicio, partida, "arriba")
                && (!tab[i - 1][j].equals(juega) && !tab[i - 1][j].equals(opuestoDeJuega))) {
            tab[i - 1][j] = "*";
        }

        if (i + 1 <= 5 && MetodosAuxiliares.movimientosSinCaptura(inicio, partida, "abajo")
                && (!tab[i + 1][j].equals(juega) && !tab[i + 1][j].equals(opuestoDeJuega))) {
            tab[i + 1][j] = "*";
        }

        if (j - 1 >= 0 && MetodosAuxiliares.movimientosSinCaptura(inicio, partida, "izquierda")
                && (!tab[i][j - 1].equals(juega) && !tab[i][j - 1].equals(opuestoDeJuega))) {
            tab[i][j - 1] = "*";
        }

        if (j + 1 <= 5 && MetodosAuxiliares.movimientosSinCaptura(inicio, partida, "derecha")
                && (!tab[i][j + 1].equals(juega) && !tab[i][j + 1].equals(opuestoDeJuega))) {
            tab[i][j + 1] = "*";
        }

        if (i - 1 >= 0 && j - 1 >= 0 && MetodosAuxiliares.movimientosSinCaptura(inicio, partida, "arribaiz")
                && (!tab[i - 1][j - 1].equals(juega) && !tab[i - 1][j - 1].equals(opuestoDeJuega))) {
            tab[i - 1][j - 1] = "*";
        }

        if (i - 1 >= 0 && j + 1 <= 5 && MetodosAuxiliares.movimientosSinCaptura(inicio, partida, "arribader")
                && (!tab[i - 1][j + 1].equals(juega) && !tab[i - 1][j + 1].equals(opuestoDeJuega))) {
            tab[i - 1][j + 1] = "*";
        }

        if (i + 1 <= 5 && j + 1 <= 5 && MetodosAuxiliares.movimientosSinCaptura(inicio, partida, "abajoder")
                && (!tab[i + 1][j + 1].equals(juega) && !tab[i + 1][j + 1].equals(opuestoDeJuega))) {
            tab[i + 1][j + 1] = "*";
        }

        if (i + 1 <= 5 && j - 1 >= 0 && MetodosAuxiliares.movimientosSinCaptura(inicio, partida, "abajoiz")
                && (!tab[i + 1][j - 1].equals(juega) && !tab[i + 1][j - 1].equals(opuestoDeJuega))) {
            tab[i + 1][j - 1] = "*";
        }

        setTableroGuias(new Tablero(tab));

    }

    public String opuestoDeJuega(String juega) {
        if (juega.equals("A")) {
            return "R";
        } else {
            return "A";
        }
    }

    public boolean verificarPosicion(String pos) {
        pos = pos.toUpperCase();
        boolean bool = false;
        String fil = "ABCDEF";
        String col = "123456";
        if (pos.length() == 2) {
            if (((fil).contains(pos.charAt(0) + "")) && ((col).contains(pos.charAt(1) + ""))) {
                bool = true;
            }
        } else {
            if (pos.equals("X")) {
                bool = true;
            }
        }
        return bool;
    }

    public boolean hayFichaDeEseColor(String pos, String juega) {
        boolean bool = false;
        int fil = obtenerFila(pos);
        int col = obtenerColumna(pos);
        if (pos.length() == 2) {
            Tablero tableroActual = getTablero();
            if ((col > -1 && col < 6) && (fil > -1 && fil < 6)) {
                bool = (tableroActual.getCuerpo())[fil][col].equals(juega);
            }
        }
        return bool;
    }

    public int cantRojas() {
        int cantRojas = 0;
        String[][] tablero = getTablero().getCuerpo();
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                if (tablero[i][j].equals("R")) {
                    cantRojas++;
                }
            }
        }
        return cantRojas;
    }

    public int cantAzules() {
        int cantAzules = 0;
        String[][] tablero = getTablero().getCuerpo();
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                if (tablero[i][j].equals("A")) {
                    cantAzules++;
                }
            }
        }
        return cantAzules;
    }

    public int obtenerDistancia(String pos) {
        int fila = obtenerFila(pos);
        int columna = obtenerColumna(pos);
        int[][] matDist = getMatDistancia();
        int dist = matDist[fila][columna];
        return dist;
    }

    public boolean movimientos(String dir, String inicio, String destino, String juega) {
        boolean retorno = false;
        String[][] tab = this.tablero.getCuerpo();

        int filaInicio = obtenerFila(inicio);
        int columnaInicio = obtenerColumna(inicio);

        int filaDestino = obtenerFila(destino);
        int columnaDestino = obtenerColumna(destino);

        int i = filaInicio;
        int j = columnaInicio;

        int distanciaInicio = getMatDistancia()[filaInicio][columnaInicio];
        int distanciaDestino = getMatDistancia()[filaDestino][columnaDestino];

        int distanciaActual;

        String opuestoDeJuega = opuestoDeJuega(juega);

        if (distanciaDestino > distanciaInicio) {
            return false;
        }

        switch (dir.toLowerCase()) {

            case "arriba":

                while (i >= 0) {

                    distanciaActual = getMatDistancia()[i][j];

                    if (i != filaInicio) {

                        if (distanciaActual > distanciaInicio) {

                            return false;
                        }

                        if (tab[i][j].equals(juega)) {

                            return false;
                        }

                        if (((j != columnaDestino))
                                && (tab[i][j].equals(juega))) {

                            return false;
                        }

                        if ((i == filaDestino)
                                && (j == columnaDestino)
                                && (tab[i][j].equals(opuestoDeJuega))) {
                            for (int k = 0; k < 6; k++) {

                                if (i + k == filaInicio && j == columnaInicio) {

                                    return true;
                                }

                                if (i + k == filaDestino) {
                                    continue;
                                }

                                if (!(this.tablero.getCuerpo()[i + k][j].equals("") && i != filaDestino && j != columnaDestino)) {

                                    return false;
                                }

                            }
                        }

                    }

                    i--;
                }

                return retorno;

            case "abajo":

                while (i <= 5) {

                    distanciaActual = getMatDistancia()[i][j];

                    if (i != filaInicio) {

                        if (distanciaActual > distanciaInicio) {

                            return false;
                        }

                        if (tab[i][j].equals(juega)) {

                            return false;
                        }

                        if (((j != columnaDestino))
                                && (tab[i][j].equals(juega))) {

                            return false;
                        }

                        if ((i == filaDestino)
                                && (j == columnaDestino)
                                && (tab[i][j].equals(opuestoDeJuega))) {
                            for (int k = 0; k < 6; k++) {

                                if (i - k == filaInicio && j == columnaInicio) {

                                    return true;
                                }

                                if (i + k == filaDestino) {
                                    continue;
                                }

                                if (!(this.tablero.getCuerpo()[i - k][j].equals("") && i != filaDestino && j != columnaDestino)) {

                                    return false;
                                }

                            }
                        }

                    }

                    i++;
                }

                return retorno;

            case "izquierda":
                while (j >= 0) {
                    distanciaActual = getMatDistancia()[i][j];
                    if (j != columnaInicio) {
                        if (distanciaActual > distanciaInicio) {
                            return false;
                        }

                        if (tab[i][j].equals(juega)) {
                            return false;
                        }

                        if (((j != columnaDestino)) && (tab[i][j].equals(juega))) {

                            return false;
                        }

                        if ((i == filaDestino) && (j == columnaDestino) && (tab[i][j].equals(opuestoDeJuega))) {
                            for (int k = 0; k < 6; k++) {

                                if (i == filaInicio && j + k == columnaInicio) {
                                    return true;
                                }

                                if (j + k == columnaDestino) {
                                    continue;
                                }

                                if (!(this.tablero.getCuerpo()[i][j + k].equals("") && i != filaDestino && j != columnaDestino)) {
                                    return false;
                                }

                            }
                        }

                    }

                    j--;
                }

                return retorno;

            case "derecha":

                while (j <= 5) {

                    distanciaActual = getMatDistancia()[i][j];

                    if (j != columnaInicio) {

                        if (distanciaActual > distanciaInicio) {

                            return false;
                        }

                        if (tab[i][j].equals(juega)) {

                            return false;
                        }

                        if (((j != columnaDestino))
                                && (tab[i][j].equals(juega))) {

                            return false;
                        }

                        if ((i == filaDestino)
                                && (j == columnaDestino)
                                && (tab[i][j].equals(opuestoDeJuega))) {
                            for (int k = 0; k < 6; k++) {

                                if (i == filaInicio && j - k == columnaInicio) {

                                    return true;
                                }

                                if (j - k == columnaDestino) {
                                    continue;
                                }

                                if (!(this.tablero.getCuerpo()[i][j - k].equals("") && i != filaDestino && j != columnaDestino)) {

                                    return false;
                                }

                            }
                        }

                    }

                    j++;
                }

                return retorno;

            case "arribaiz":

                while (i >= 0 && j >= 0) {

                    distanciaActual = getMatDistancia()[i][j];

                    if (j != columnaInicio && i != filaInicio) {

                        if (distanciaActual > distanciaInicio) {

                            return false;
                        }

                        if (tab[i][j].equals(juega)) {

                            return false;
                        }

                        if (((j != columnaDestino))
                                && (tab[i][j].equals(juega))) {

                            return false;
                        }

                        if ((i == filaDestino)
                                && (j == columnaDestino)
                                && (tab[i][j].equals(opuestoDeJuega))) {
                            return true;
                        }

                    }

                    i--;
                    j--;
                }

                return retorno;

            case "arribader":
                while (i >= 0 && j <= 5) {

                    distanciaActual = getMatDistancia()[i][j];

                    if (j != columnaInicio && i != filaInicio) {

                        if (distanciaActual > distanciaInicio) {

                            return false;
                        }

                        if (tab[i][j].equals(juega)) {

                            return false;
                        }

                        if (((j != columnaDestino))
                                && (tab[i][j].equals(juega))) {

                            return false;
                        }

                        if ((i == filaDestino)
                                && (j == columnaDestino)
                                && (tab[i][j].equals(opuestoDeJuega))) {
                            return true;
                        }

                    }

                    i--;
                    j++;
                }

                return retorno;

            case "abajoder":

                while (i <= 5 && j <= 5) {

                    distanciaActual = getMatDistancia()[i][j];

                    if (j != columnaInicio && i != filaInicio) {

                        if (distanciaActual > distanciaInicio) {

                            return false;
                        }

                        if (tab[i][j].equals(juega)) {

                            return false;
                        }

                        if (((j != columnaDestino))
                                && (tab[i][j].equals(juega))) {

                            return false;
                        }

                        if ((i == filaDestino)
                                && (j == columnaDestino)
                                && (tab[i][j].equals(opuestoDeJuega))) {
                            return true;
                        }

                    }

                    i++;
                    j++;
                }

                return retorno;

            case "abajoiz":

                while (i <= 5 && j >= 0) {

                    distanciaActual = getMatDistancia()[i][j];

                    if (j != columnaInicio && i != filaInicio) {

                        if (distanciaActual > distanciaInicio) {

                            return false;
                        }

                        if (tab[i][j].equals(juega)) {

                            return false;
                        }

                        if (((j != columnaDestino))
                                && (tab[i][j].equals(juega))) {

                            return false;
                        }

                        if ((i == filaDestino)
                                && (j == columnaDestino)
                                && (tab[i][j].equals(opuestoDeJuega))) {
                            return true;
                        }

                    }

                    i++;
                    j--;
                }

                return retorno;

        }
        return retorno;
    }

    public int obtenerFila(String pos) {
        String fil = "ABCDEF";
        String posicion = "";
        for (int i = 0; i < fil.length(); i++) {
            if ((pos.charAt(0) + "").equals(fil.charAt(i) + "")) {
                posicion += i;
            }

        }
        return Integer.parseInt(posicion);
    }

    public int obtenerColumna(String pos) {
        int col = Integer.parseInt(pos.charAt(1) + "");
        return col - 1;
    }

    public ArrayList<Tablero> prepararTableros() {
        ArrayList<Tablero> listaTableros = new ArrayList<>();
        listaTableros.add(Tablero.tableroStandard());
        listaTableros.add(Tablero.tableroPrecargado1());
        listaTableros.add(Tablero.tableroPrecargado2());
        return listaTableros;
    }

}