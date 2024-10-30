// Juan Peyrot (num)
// Guillermo Diotti 285578

package obligatorio;

public class Tablero {

    private String[][] cuerpo;

    public void setCuerpo(String[][] tab) {
        this.cuerpo = tab;
    }

    public String[][] getCuerpo() {
        return this.cuerpo;
    }

    public Tablero(String[][] tab) {
        setCuerpo(tab);
    }

    @Override
    public String toString() {
        String res = "     1 2 3 4 5 6";
        res += "\n";
        String[] fil = {"A", "B", "C", "D", "E", "F"};
        for (int i = 0; i < this.cuerpo.length; i++) {
            res += "    +-+-+-+-+-+-+";
            res += "\n";
            for (int j = 0; j < this.cuerpo[0].length; j++) {

                if (j == 0) {
                    if (this.cuerpo[i][j].equals("A")) {
                        res += fil[i] + "   " + "\033[30m" + "|" + "\033[34m" + cuerpo[i][j] + "\033[30m" + "|";
                    } else if (this.cuerpo[i][j].equals("R")) {
                        res += fil[i] + "   " + "\033[30m" + "|" + "\033[31m" + cuerpo[i][j] + "\033[30m" + "|";
                    } else if (this.cuerpo[i][j].equals("*")) {
                        res += fil[i] + "   " + "\033[30m" + "|" + "\u001B[32m" + cuerpo[i][j] + "\033[30m" + "|";
                    } else if (this.cuerpo[i][j].equals("#")) {
                        res += fil[i] + "   " + "\033[30m" + "|" + "\u001B[32m" + cuerpo[i][j] + "\033[30m" + "|";
                    } else if (this.cuerpo[i][j].equals("E")) {
                        res += fil[i] + "   " + "\033[30m" + "|" + "\u001B[32m" + cuerpo[i][j] + "\033[30m" + "|";
                    } else {
                        res += "\033[30m" + fil[i] + "   " + "| |";
                    }
                } else {
                    if (this.cuerpo[i][j].equals("A")) {
                        res += "\033[34m" + this.cuerpo[i][j] + "\033[30m" + "|";
                    } else if (this.cuerpo[i][j].equals("R")) {
                        res += "\033[31m" + this.cuerpo[i][j] + "\033[30m" + "|";
                    } else if (this.cuerpo[i][j].equals("E")) {
                        res += "\u001B[32m" + this.cuerpo[i][j] + "\033[30m" + "|";
                    } else if (this.cuerpo[i][j].equals("*")) {
                        res += "\u001B[32m" + this.cuerpo[i][j] + "\033[30m" + "|";
                    } else if (this.cuerpo[i][j].equals("#")) {
                        res += "\u001B[32m" + this.cuerpo[i][j] + "\033[30m" + "|";
                    } else {
                        res += " |";
                    }
                }
            }
            res += "   " + fil[i];
            res += "\n";
        }
        res += "    +-+-+-+-+-+-+";
        res += "\n";
        res += "     1 2 3 4 5 6";
        return res;
    }

    public static Tablero tableroStandard() {
        String[][] t0
                = {
                    {"A", "R", "A", "R", "A", "R"},
                    {"R", "A", "R", "A", "R", "A"},
                    {"A", "R", "A", "R", "A", "R"},
                    {"R", "A", "R", "A", "R", "A"},
                    {"A", "R", "A", "R", "A", "R"},
                    {"R", "A", "R", "A", "R", "A"}
                };
        Tablero standard = new Tablero(t0);
        return standard;
    }

    public static Tablero tableroPrecargado1() {
        String[][] t1
                = {
                    {"R", " ", " ", " ", " ", " "},
                    {" ", " ", " ", " ", " ", " "},
                    {" ", " ", "A", " ", " ", " "},
                    {" ", " ", " ", " ", " ", "A"},
                    {" ", "A", " ", " ", " ", " "},
                    {" ", " ", " ", "R", " ", " "}
                };
        Tablero preset1 = new Tablero(t1);
        return preset1;
    }

    public static Tablero tableroPrecargado2() {

        String[][] t2
                = {
                    {"R", " ", " ", " ", " ", " "},
                    {" ", " ", " ", " ", " ", " "},
                    {" ", " ", " ", " ", " ", " "},
                    {" ", " ", " ", " ", " ", " "},
                    {" ", " ", " ", " ", " ", " "},
                    {" ", " ", " ", " ", "A", "A"}
                };

        Tablero preset2 = new Tablero(t2);
        return preset2;
    }

}