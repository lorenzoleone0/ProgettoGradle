package org.example;

import org.example.Tracciato.Tracciato;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



class TracciatoTest {
    @Test
    void testCostruttoreEInizializzazione() {
        char[][] griglia = {
                {'P', ' ', 'X', 'X'},
                {' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' '},
                {'X', 'X', ' ', 'A'}
        };
        int[] partenza = {0, 0};
        int[] arrivo = {3, 3};

        Tracciato tracciato = new Tracciato(griglia, partenza, arrivo);

        assertArrayEquals(griglia, tracciato.getGriglia());
        assertEquals(0, tracciato.getDistanza(3, 3));
    }

    @Test
    void testIsPosizioneValida() {
        char[][] griglia = {
                {'P', ' ', 'X', 'X'},
                {' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' '},
                {'X', 'X', ' ', 'A'}
        };
        Tracciato tracciato = new Tracciato(griglia, new int[]{0, 0}, new int[]{3, 3});

        assertTrue(tracciato.isPosizioneValida(0, 1)); // Posizione libera
        assertFalse(tracciato.isPosizioneValida(0, 2)); // Ostacolo
        assertFalse(tracciato.isPosizioneValida(4, 4)); // Fuori dai limiti
    }

    @Test
    void testIsArrivato() {
        char[][] griglia = {
                {'P', ' ', 'X', 'X'},
                {' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' '},
                {'X', 'X', ' ', 'A'}
        };
        int[] arrivo = {3, 3};
        Tracciato tracciato = new Tracciato(griglia, new int[]{0, 0}, arrivo);

        assertTrue(tracciato.isArrivato(arrivo)); // Arrivo corretto
        assertFalse(tracciato.isArrivato(new int[]{0, 0})); // Non è l'arrivo
    }

    @Test
    void testCalcolaMappaDistanza() {
        char[][] griglia = {
                {'P', ' ', 'X', 'X'},
                {' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' '},
                {'X', 'X', ' ', 'A'}
        };
        int[] partenza = {0, 0};
        int[] arrivo = {3, 3};
        Tracciato tracciato = new Tracciato(griglia, partenza, arrivo);

        // Controlla alcune distanze calcolate
        assertEquals(0, tracciato.getDistanza(3, 3)); // Arrivo
        assertEquals(1, tracciato.getDistanza(3, 2)); // Vicino all'arrivo
        assertEquals(2, tracciato.getDistanza(1, 1)); // Lontano dall'arrivo
        assertEquals(3, tracciato.getDistanza(0, 0)); // Partenza
    }

    @Test
    void testStampaTracciato() {
        char[][] griglia = {
                {'P', ' ', 'X', 'X'},
                {' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' '},
                {'X', 'X', ' ', 'A'}
        };
        int[] partenza = {0, 0};
        int[] arrivo = {3, 3};
        Tracciato tracciato = new Tracciato(griglia, partenza, arrivo);


        tracciato.stampaTracciato();
    }


    /*@Test
    void testOttieniAdiacenti() {
        char[][] griglia = {
                {'P', ' ', 'X', 'X', 'X'},
                {' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' '},
                {'X', ' ', ' ', ' ', ' '},
                {'X', 'X', 'X', ' ', 'A'}
        };
        Tracciato tracciato = new Tracciato(griglia, new int[]{0, 0}, new int[]{4, 4});

        // Posizione al centro della griglia
        List<int[]> adiacenti = tracciato.ottieniAdiacenti(new int[]{2, 2});

        assertEquals(8, adiacenti.size()); // Ci sono 8 celle adiacenti valide
        assertTrue(adiacenti.contains(new int[]{1, 2}));
        assertTrue(adiacenti.contains(new int[]{1, 1}));

    }*/



}
