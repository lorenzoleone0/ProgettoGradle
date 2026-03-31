package org.example.Giocatore;

import org.example.Tracciato.Tracciato;

import java.util.HashSet;
import java.util.Set;

public class Bot2 extends GiocatoreBase {
    private final Set<String> posizioniVisitate; // Per memorizzare le caselle già visitate
    private boolean rallentamentoInCorso = false;
    private int turniSenzaProgresso = 0;

    public Bot2(String nome, int[] posizioneIniziale, Tracciato tracciato) {
        super(nome, posizioneIniziale, tracciato);
        this.posizioniVisitate = new HashSet<>();
        registraPosizione(); // Registra la posizione iniziale
    }

    @Override
    public void muovi() {

        // Gestione del rallentamento nelle curve
        if (rallentamentoInCorso) {
            rallentamentoInCorso = false;
            System.out.println(getNome() + " rallenta nella curva e perde un turno");
            return;
        }

        // Controlla se è in una curva e imposta il rallentamento
        if (Curva()) {
            rallentamentoInCorso = true;
        }

        // Calcola la prossima mossa
        int[] nuovaPosizione = calcolaMigliorMossa();

        if (nuovaPosizione != null) {
            aggiornaPosizione(nuovaPosizione);
            turniSenzaProgresso = 0;
        } else {
            turniSenzaProgresso++;

        }
    }

    private int[] calcolaMigliorMossa() {
        int migliorDistanza = Integer.MAX_VALUE;
        int[] migliorPosizione = null;

        // Esplora tutte le direzioni
        int[][] direzioni = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] direzione : direzioni) {
            int nuovaRiga = posizione[0] + direzione[0];
            int nuovaColonna = posizione[1] + direzione[1];

            // Verifica la validità della posizione
            if (posizioneValida(nuovaRiga, nuovaColonna) && !posizioneVisitata(nuovaRiga, nuovaColonna)) {
                int distanza = tracciato.getDistanza(nuovaRiga, nuovaColonna);
                if (distanza < migliorDistanza) {
                    migliorDistanza = distanza;
                    migliorPosizione = new int[]{nuovaRiga, nuovaColonna};
                }
            }
        }
        return migliorPosizione;
    }

    private void aggiornaPosizione(int[] nuovaPosizione) {
        posizione = nuovaPosizione;
        registraPosizione();
    }

    private boolean posizioneVisitata(int x, int y) {
        return posizioniVisitate.contains(x + "," + y);
    }

    private void registraPosizione() {
        posizioniVisitate.add(posizione[0] + "," + posizione[1]);
    }

    private boolean Curva() {
        char[][] griglia = tracciato.getGriglia();
        return griglia[posizione[0]][posizione[1]] == 'C';
    }
    private boolean posizioneValida(int x, int y) {
        char[][] griglia = tracciato.getGriglia();
        return x >= 0 && x < griglia.length && y >= 0 && y < griglia[0].length && griglia[x][y] != 'X';
    }

}