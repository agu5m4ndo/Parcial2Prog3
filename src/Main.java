import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //mutante
        //String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};

        //no mutante
        //String[] dna = {"TTTTTT", "ATGTGC", "TTTTTT", "TGTTGG", "TTGTTA", "TCACTT"};

        String[] dna = {"TTACGT", "AAGTTC", "TTATTT", "AGTTGT", "GTGCAT", "TCAGTT"};
        //
        //String[] dna = {"TTGCGA", "ACGTGC", "TCTTTT", "AGTTGA", "GTATCA", "TCACCG"};
        //
        //String[] dna = {"TTGTAA", "ACGTTG", "TTATTT", "AGTAGG", "GTGCGA", "CTCAGG"};
        //
        //String[] dna = {"TCTCGA", "AGGTGC", "TTACTT", "AGTGGG", "GTGTCA", "TCACTA"};
        //
        //String[] dna = {"TTGCGA", "AAGTGC", "TTATTT", "AGTTGG", "GTGTCA", "TCACTG"};
        //
        //String[] dna = {"TTAGTA", "ATGTGC", "TTATTT", "AGTGGG", "GTGTCA", "TCACTG"};
        //
        //String[] dna = {"TTGGCA", "ACGTTG", "TTAGTT", "AGTTGG", "GTATCA", "TCACCG"};
        //
        //String[] dna = {"TTGAGA", "AAGTGC", "TTATTT", "AGCTGG", "GTGCAC", "TCAGTG"};

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
                    System.out.println("Coincidencia en horizontal (" + (i + 1) + ", " + (j + 1) + ")");
                }
            }
        }

        //Verificación vertical
        int columnaOcupada = 0;
        for (int i = 0; i < matriz.length - 3; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                if (matriz[i][j] == matriz[i + 1][j] && matriz[i][j] == matriz[i + 2][j] && matriz[i][j] == matriz[i + 3][j] && columnaOcupada != j) {
                    count++;
                    columnaOcupada = j;
                    System.out.println("Coincidencia en vertical (" + (i + 1) + ", " + (j + 1) + ")");
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
                    System.out.println("Coincidencia en diagonal (" + (i + 1) + ", " + (j + 1) + ")");
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
                    System.out.println("Coincidencia en diagonal inversa (" + (i + 1) + ", " + (j + 1) + ")");
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
                throw new IllegalArgumentException("La matriz no es cuadrada");
            }
        }

        //verificar que solo contenga las letras A, T, C, G
        for (int i = 0; i < dna.length; i++) {
            for (int j = 0; j < dna[0].length(); j++) {
                if (dna[i].charAt(j) != 'A' && dna[i].charAt(j) != 'T' && dna[i].charAt(j) != 'C' && dna[i].charAt(j) != 'G') {
                    throw new IllegalArgumentException("La secuencia contiene caracteres no válidos");
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