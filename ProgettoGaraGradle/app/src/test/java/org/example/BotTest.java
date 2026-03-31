package org.example;

import org.example.Giocatore.Bot1;
import org.example.Tracciato.Tracciato;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BotTest {
    private Tracciato tracciato;
    private Bot1 bot;

    @BeforeEach
    void setUp() {
        // Creazione di un tracciato di esempio
        char[][] griglia = {
                {'P', '.', '.', '.', 'C'},
                {'X', 'X', 'X', 'X', '.'},
                {'X', 'X', 'X', 'X', 'A'}
        };
        int[] lineaPartenza={0,0};
        int[] lineaArrivo = {2, 4};
        tracciato = new Tracciato(griglia, lineaArrivo, lineaPartenza);

        // Creazione del bot nella posizione iniziale (0,0)
        bot = new Bot1("TestBot", new int[]{0, 0}, tracciato);
    }

    @Test
    void testInizializzazioneBot() {
        assertEquals("TestBot", bot.getNome());
        assertArrayEquals(new int[]{0, 0}, bot.getPosizione());
    }

    @Test
    void testMovimentoSemplice() {
        bot.muovi();
        assertArrayEquals(new int[]{0, 1}, bot.getPosizione());
    }

    @Test
    void testRallentamentoInCurva() {
        bot.setPosizione(new int[]{0, 4});
        bot.muovi();
        assertArrayEquals(new int[]{0, 3}, bot.getPosizione());
    }


    /*@Test
    void testRaggiungereLineaArrivo() {
        bot.setPosizione(new int[]{2, 4});
        bot.muovi();
        assertTrue(bot.isArrivato());
    }*/
}
