// Juan Peyrot 275076
// Guillermo Diotti 285578
package obligatorio;

import java.util.Arrays;

//Clase con metodos que ayudan a hacer validaciones y obtener datos de forma mas sencilla
public class MetodosAuxiliares {

    public static boolean movimientosSinCaptura(String inicio, Partida partida, String hacia) {
        int fila = partida.obtenerFila(inicio);
        int columna = partida.obtenerColumna(inicio);
        int distActual = partida.getMatDistancia()[fila][columna];
        int distNueva;

        switch (hacia) {

            case "arriba":
                if (fila - 1 >= 0) {
                    distNueva = partida.getMatDistancia()[fila - 1][columna];
                    return distNueva > distActual;
                } else {
                    return false;
                }

            case "abajo":
                if (fila - 1 >= 0) {
                    distNueva = partida.getMatDistancia()[fila + 1][columna];
                    return distNueva > distActual;
                } else {
                    return false;
                }

            case "izquierda":
                if (fila - 1 >= 0) {
                    distNueva = partida.getMatDistancia()[fila][columna - 1];
                    return distNueva > distActual;
                } else {
                    return false;
                }

            case "derecha":
                if (fila - 1 >= 0) {
                    distNueva = partida.getMatDistancia()[fila][columna + 1];
                    return distNueva > distActual;
                } else {
                    return false;
                }

            case "arribaiz":
                if (fila - 1 >= 0) {
                    distNueva = partida.getMatDistancia()[fila - 1][columna - 1];
                    return distNueva > distActual;
                } else {
                    return false;
                }

            case "arribader":
                if (fila - 1 >= 0) {
                    distNueva = partida.getMatDistancia()[fila - 1][columna + 1];
                    return distNueva > distActual;
                } else {
                    return false;
                }

            case "abajoder":
                if (fila - 1 >= 0) {
                    distNueva = partida.getMatDistancia()[fila + 1][columna + 1];
                    return distNueva > distActual;
                } else {
                    return false;
                }

            case "abajoiz":
                if (fila - 1 >= 0) {
                    distNueva = partida.getMatDistancia()[fila + 1][columna - 1];
                    return distNueva > distActual;
                } else {
                    return false;
                }

        }

        return false;

    }

    public static void movimientosConCaptura(String inicio, String juega, String capturar, Partida partida, String hacia) {
        String[][] matGuia = copiarArray(partida.getTableroGuias());

        int fila = partida.obtenerFila(inicio);
        int columna = partida.obtenerColumna(inicio);

        boolean cambiar = false;
        int distActual = partida.getMatDistancia()[fila][columna];
        int distNueva;

        int i = fila;
        int j = columna;

        switch (hacia) {

            case "arriba":
                while (i >= 0) {

                    if (i != fila && matGuia[i][j].equals(juega)) {
                        break;
                    }

                    if (matGuia[i][j].equals(capturar)) {
                        distNueva = partida.getMatDistancia()[i][j];

                        if (distNueva <= distActual) {
                            matGuia[i][j] = "#";
                            cambiar = true;
                            break;
                        }

                    }
                    i--;
                }

                if (cambiar) {
                    partida.setTableroGuias(new Tablero(matGuia));
                }
                break;

            case "abajo":
                while (i <= 5) {

                    if (i != fila && matGuia[i][j].equals(juega)) {
                        break;
                    }

                    if (matGuia[i][j].equals(capturar)) {
                        distNueva = partida.getMatDistancia()[i][j];

                        if (distNueva <= distActual) {
                            matGuia[i][j] = "#";
                            cambiar = true;
                            break;
                        }

                    }
                    i++;
                }

                if (cambiar) {
                    partida.setTableroGuias(new Tablero(matGuia));
                }
                break;

            case "izquierda":

                while (j >= 0) {

                    if (j != columna && matGuia[i][j].equals(juega)) {
                        break;
                    }

                    if (matGuia[i][j].equals(capturar)) {
                        distNueva = partida.getMatDistancia()[i][j];

                        if (distNueva <= distActual) {
                            matGuia[i][j] = "#";
                            cambiar = true;
                            break;
                        }

                    }
                    j--;
                }

                if (cambiar) {
                    partida.setTableroGuias(new Tablero(matGuia));
                }
                break;

            case "derecha":
                while (j <= 5) {

                    if (j != columna && matGuia[i][j].equals(juega)) {
                        break;
                    }

                    if (matGuia[i][j].equals(capturar)) {
                        distNueva = partida.getMatDistancia()[i][j];

                        if (distNueva <= distActual) {
                            matGuia[i][j] = "#";
                            cambiar = true;
                            break;
                        }

                    }
                    j++;
                }

                if (cambiar) {
                    partida.setTableroGuias(new Tablero(matGuia));
                }
                break;

            case "arribaiz":
                while (i >= 0 && j >= 0) {

                    if (i != fila && j != columna && matGuia[i][j].equals(juega)) {
                        break;
                    }

                    if (matGuia[i][j].equals(capturar)) {
                        distNueva = partida.getMatDistancia()[i][j];

                        if (distNueva <= distActual) {
                            matGuia[i][j] = "#";
                            cambiar = true;
                            break;
                        }

                    }
                    i--;
                    j--;
                }

                if (cambiar) {
                    partida.setTableroGuias(new Tablero(matGuia));
                }
                break;

            case "arribader":
                while (i >= 0 && j <= 5) {

                    if (i != fila && j != columna && matGuia[i][j].equals(juega)) {
                        break;
                    }

                    if (matGuia[i][j].equals(capturar)) {
                        distNueva = partida.getMatDistancia()[i][j];

                        if (distNueva <= distActual) {
                            matGuia[i][j] = "#";
                            cambiar = true;
                            break;
                        }

                    }
                    i--;
                    j++;
                }

                if (cambiar) {
                    partida.setTableroGuias(new Tablero(matGuia));
                }
                break;

            case "abajoder":
                while (i <= 5 && j <= 5) {

                    if (i != fila && j != columna && matGuia[i][j].equals(juega)) {
                        break;
                    }

                    if (matGuia[i][j].equals(capturar)) {
                        distNueva = partida.getMatDistancia()[i][j];

                        if (distNueva <= distActual) {
                            matGuia[i][j] = "#";
                            cambiar = true;
                            break;
                        }

                    }
                    i++;
                    j++;
                }

                if (cambiar) {
                    partida.setTableroGuias(new Tablero(matGuia));
                }
                break;

            case "abajoiz":
                while (i <= 5 && j >= 0) {

                    if (i != fila && j != columna && matGuia[i][j].equals(juega)) {
                        break;
                    }

                    if (matGuia[i][j].equals(capturar)) {
                        distNueva = partida.getMatDistancia()[i][j];

                        if (distNueva <= distActual) {
                            matGuia[i][j] = "#";
                            cambiar = true;
                            break;
                        }

                    }
                    i++;
                    j--;
                }

                if (cambiar) {
                    partida.setTableroGuias(new Tablero(matGuia));
                }
                break;

        }

    }

    public static String[][] copiarArray(String[][] arr) {

        String[][] copy = new String[arr.length][];
        for (int i = 0; i < arr.length; i++) {
            copy[i] = Arrays.copyOf(arr[i], arr[i].length);
        }

        return copy;
    }

    public static int cantidadMovimientos(String[][] tab) {

        int cant = 0;

        for (int i = 0; i < tab.length; i++) {

            for (int j = 0; j < tab[i].length; j++) {

                if (tab[i][j].equals("*") || tab[i][j].equals("#")) {
                    cant++;
                }

            }

        }
        return cant;
    }
    
    public static String obtenerPosicionString(int k, int l){
        String str = "ABCDEF";
        return str.charAt(k) + "" + (l+1);
        
    }
    
    
    public static boolean hayMovPosibles(Partida partida, String juega) {
        
        String[][] tab = partida.getTablero().getCuerpo();
        String inicio;
        
        for (int i = 0; i < tab.length; i++) {
            
            for (int j = 0; j < tab[0].length; j++) {
                
                
                if (tab[i][j].equals(juega)) {
                    inicio = obtenerPosicionString(i,j);
                    partida.guiasSinCaptura(inicio, juega, partida);
                    partida.guiasConCaptura(inicio, juega, partida);
                    partida.setTableroGuiasAux(new Tablero(tab));
                    
                    for (int k = 0; k < tab.length; k++) {
                       
                        for (int l = 0; l < tab[0].length; l++) {
                           
                            if (partida.getTableroGuias()[k][l].equals("#") || (partida.getTableroGuias()[k][l].equals("*"))) {
                                return true;
                            }

                        }

                    }

                }
            }

        }

        return false;
    }

    
}
