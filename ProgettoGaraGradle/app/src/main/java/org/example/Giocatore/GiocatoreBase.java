package org.example.Giocatore;

import org.example.Tracciato.Tracciato;

public abstract class GiocatoreBase implements Giocatore {
    protected String nome;
    protected int[] posizione; // [riga, colonna]
    protected Tracciato tracciato;

    public GiocatoreBase(String nome, int[] posizioneIniziale, Tracciato tracciato) {
        this.nome = nome;
        this.posizione = posizioneIniziale;
        this.tracciato = tracciato;
    }

    @Override
    public int[] getPosizione() {
        return posizione;
    }

    @Override
    public String getNome() {
        return nome;
    }

    /*protected boolean posizioneValida(int[] nuovaPosizione) {
        int riga = nuovaPosizione[0];
        int colonna = nuovaPosizione[1];
        return riga >= 0 && riga < tracciato.getGriglia().length &&
                colonna >= 0 && colonna < tracciato.getGriglia()[0].length &&
                tracciato.getGriglia()[riga][colonna] != 'X';
    }*/
}