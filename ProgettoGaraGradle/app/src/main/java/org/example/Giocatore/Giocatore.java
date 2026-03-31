package org.example.Giocatore;

public interface Giocatore {
    void muovi(); // Logica di movimento
    int[] getPosizione(); // Restituisce la posizione attuale del giocatore
    String getNome(); // Nome del giocatore
}
