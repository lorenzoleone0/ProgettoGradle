package org.example.Tracciato;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Tracciato {
    private final char[][] griglia; // La matrice che rappresenta il circuito
    private final int[] lineaPartenza; // Coordinata della linea di partenza
    private final int[] lineaArrivo;   // Coordinata della linea di arrivo
    private int[][] mappaDistanza; // Mappa distanza partenza/arrivo

    // Costruttore
    public Tracciato(char[][] griglia, int[] lineaPartenza, int[] lineaArrivo) {
        this.griglia = griglia;
        this.lineaPartenza = lineaPartenza;
        this.lineaArrivo = lineaArrivo;
        calcolaMappaDistanza();
    }

    // Metodo per verificare se una posizione è valida nel tracciato
    public boolean isPosizioneValida(int x, int y) {
        if (x < 0 || x >= griglia.length || y < 0 || y >= griglia[0].length) {
            return false;
        }
        return griglia[x][y] != 'X'; // 'X' rappresenta i bordi della pista
    }

    public boolean isArrivato(int[] posizione) {
        return posizione[0] == lineaArrivo[0] && posizione[1] == lineaArrivo[1];
    }

    public void calcolaMappaDistanza() {
        if (mappaDistanza != null) return;

        inizializzaMappaDistanza();
        BFS();
    }

    // Metodo per inizializzare la mappa di distanza
    public void inizializzaMappaDistanza() {
        int righe = griglia.length;
        int colonne = griglia[0].length;
        mappaDistanza = new int[righe][colonne];

        for (int i = 0; i < righe; i++) {
            for (int j = 0; j < colonne; j++) {
                mappaDistanza[i][j] = Integer.MAX_VALUE;
            }
        }
        mappaDistanza[lineaArrivo[0]][lineaArrivo[1]] = 0;
    }

    // Metodo per eseguire la BFS, ricerca del percorso piu breve
    public void BFS() {
        Queue<int[]> coda = new LinkedList<>();
        coda.add(lineaArrivo);

        while (!coda.isEmpty()) {
            int[] corrente = coda.poll();
            int distanzaCorrente = mappaDistanza[corrente[0]][corrente[1]];

            for (int[] adiacente : ottieniAdiacenti(corrente)) {
                int nuovaRiga = adiacente[0];
                int nuovaColonna = adiacente[1];

                if (mappaDistanza[nuovaRiga][nuovaColonna] == Integer.MAX_VALUE) {
                    mappaDistanza[nuovaRiga][nuovaColonna] = distanzaCorrente + 1;
                    coda.add(new int[]{nuovaRiga, nuovaColonna});
                }
            }
        }
    }

    // Metodo per ottenere le celle adiacenti valide
    public List<int[]> ottieniAdiacenti(int[] posizione) {
        int[][] direzioni = {
                {-1, 0}, {1, 0}, {0, -1}, {0, 1}, // Direzioni cardinali
                {-1, -1}, {-1, 1}, {1, -1}, {1, 1} // Diagonali
        };
        List<int[]> adiacenti = new ArrayList<>();

        for (int[] direzione : direzioni) {
            int nuovaRiga = posizione[0] + direzione[0];
            int nuovaColonna = posizione[1] + direzione[1];

            if (isPosizioneValida(nuovaRiga, nuovaColonna)) {
                adiacenti.add(new int[]{nuovaRiga, nuovaColonna});
            }
        }
        return adiacenti;
    }

    // Metodo per stampare il tracciat
    public void stampaTracciato() {
        for (int i = 0; i < griglia.length; i++) {
            for (int j = 0; j < griglia[i].length; j++) {
                if (i == lineaPartenza[0] && j == lineaPartenza[1]) {
                    System.out.print("P ");
                } else if (i == lineaArrivo[0] && j == lineaArrivo[1]) {
                    System.out.print("A ");
                } else {
                    System.out.print(griglia[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    // Getter
    public char[][] getGriglia() {
        return griglia;
    }
    public int getDistanza(int x, int y) {
        return mappaDistanza[x][y];
    }
}

