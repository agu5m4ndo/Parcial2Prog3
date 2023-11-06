import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //mutante
        //String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};

        //multiples coincidencias
        String[] dna = {"TTTTTT", "ATGTGC", "TTTTTT", "TGTTGG", "TTGTTA", "TCACTT"};

        //coincidencia vertical con 5 letras iguales
        //String[] dna = {"TAACGT", "TAGTTC", "TAATTT", "TATTGT", "TAGCAT", "TCAGTT"};

        //String[] dna = generarSecuencia();
        try {
            verificarValidez(dna);
            String dnaFormateado = String.join(", ", dna);
            if (isMutant(dna)) {
                System.out.println("La persona con secuencia {" + dnaFormateado + "} es mutante");
            } else {
                System.out.println("La persona con secuencia {" + dnaFormateado + "} no es mutante");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }



    public static boolean isMutant(String[] dna) {
        int count = 0;
        int indice1 = 0;
        int indice2 = 0;



        //transformar a matriz de char
        char[][] matriz = new char[dna.length][dna.length];

        for (int i = 0; i < dna.length; i++) {
            for (int j = 0; j < dna[0].length(); j++) {
                matriz[i][j] = dna[i].charAt(j);
            }
        }

        printMatriz(matriz);

        //Verificación horizontal
        List<Integer> indicesOcupadosH = new ArrayList<>();
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length - 3; j++) {
                if (matriz[i][j] == matriz[i][j + 1] && matriz[i][j] == matriz[i][j + 2] && matriz[i][j] == matriz[i][j + 3] && !indicesOcupadosH.contains(j)) {
                    count++;
                    indicesOcupadosH.add(j);
                    indicesOcupadosH.add(j + 1);
                    indicesOcupadosH.add(j + 2);
                    indicesOcupadosH.add(j + 3);
                    System.out.println("\u001B[32mCoincidencia en horizontal (" + (i + 1) + ", " + (j + 1) + ")\u001B[0m");
                }
            }
        }

        //Verificación vertical
        List<Integer> columnaOcupada = new ArrayList<>();
        for (int i = 0; i < matriz.length - 3; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                if (matriz[i][j] == matriz[i + 1][j] && matriz[i][j] == matriz[i + 2][j] && matriz[i][j] == matriz[i + 3][j] && !columnaOcupada.contains(j)) {
                    count++;
                    columnaOcupada.add(j);
                    System.out.println("\u001B[32mCoincidencia en vertical (" + (i + 1) + ", " + (j + 1) + ")\u001B[0m");
                }
            }
        }

        //verificación diagonal
        List<String> indicesOcupadosD = new ArrayList<>();
        for (int i = 0; i < matriz.length - 3; i++) {
            for (int j = 0; j < matriz[0].length - 3; j++) {
                if (matriz[i][j] == matriz[i + 1][j + 1] && matriz[i][j] == matriz[i + 2][j + 2] && matriz[i][j] == matriz[i + 3][j + 3] && !indicesOcupadosD.contains(i + ", " + j)) {
                    count++;
                    indicesOcupadosD.add(i + ", " + j);
                    indicesOcupadosD.add((i + 1) + ", " + (j + 1));
                    indicesOcupadosD.add((i + 2) + ", " + (j + 2));
                    indicesOcupadosD.add((i + 3) + ", " + (j + 3));
                    System.out.println("\u001B[32mCoincidencia en diagonal (" + (i + 1) + ", " + (j + 1) + ")\u001B[0m");
                }
            }
        }

        //verificación diagonal
        List<String> indicesOcupadosDI = new ArrayList<>();
        for (int i = 0; i < matriz.length - 3; i++) {
            for (int j = 3; j < matriz[0].length; j++) {
                if (matriz[i][j] == matriz[i + 1][j - 1] && matriz[i][j] == matriz[i + 2][j - 2] && matriz[i][j] == matriz[i + 3][j - 3] && !indicesOcupadosDI.contains(i + ", " + j)) {
                    count++;
                    indicesOcupadosDI.add(i + ", " + j);
                    indicesOcupadosDI.add((i + 1) + ", " + (j - 1));
                    indicesOcupadosDI.add((i + 2) + ", " + (j - 2));
                    indicesOcupadosDI.add((i + 3) + ", " + (j - 3));
                    System.out.println("\u001B[32mCoincidencia en diagonal inversa (" + (i + 1) + ", " + (j + 1) + ")\u001B[0m");
                }
            }
        }
        return count > 1;
    }

    //printar a matriz
    public static void printMatriz(char[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            System.out.println();
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
        }
        System.out.println();
    }

    public static boolean verificarValidez(String[] dna){
        //verificar que sea una matriz cuadrada
        for (int i = 0; i < dna.length; i++) {
            if (dna.length != dna[i].length()) {
                throw new IllegalArgumentException("\u001B[31mLa matriz no es cuadrada\u001B[0m");
            }
        }

        //verificar que solo contenga las letras A, T, C, G
        for (int i = 0; i < dna.length; i++) {
            for (int j = 0; j < dna[0].length(); j++) {
                if (dna[i].charAt(j) != 'A' && dna[i].charAt(j) != 'T' && dna[i].charAt(j) != 'C' && dna[i].charAt(j) != 'G') {
                    throw new IllegalArgumentException("\u001B[31mLa secuencia contiene caracteres no válidos\u001B[0m");
                }
            }
        }
        return true;
    }

    public static String[] generarSecuencia() {
        String[] dna = new String[6];
        String[] letras = {"A", "T", "C", "G"};
        for (int i = 0; i < dna.length; i++) {
            String secuencia = "";

            for (int j = 0; j < dna.length; j++) {
                secuencia += letras[(int) (Math.random() * 4)];
            }
            dna[i] = secuencia;

        }
        return dna;
    }
}