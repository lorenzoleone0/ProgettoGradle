package org.example.Tracciato;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CaricamentoTracciatoFile implements CaricamentoTracciato {

    @Override
    public Tracciato caricaTracciato(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String[] dimensioni = reader.readLine().split(",");
            int righe = Integer.parseInt(dimensioni[0]);
            int colonne = Integer.parseInt(dimensioni[1]);
            char[][] griglia = new char[righe][colonne];

            for (int i = 0; i < righe; i++) {
                griglia[i] = reader.readLine().replace(",", "").toCharArray();
            }

            String[] partenza = reader.readLine().split(","); // Posizione linea partenza
            String[] arrivo = reader.readLine().split(",");   // Posizione linea arrivo

            int[] lineaPartenza = {Integer.parseInt(partenza[0]), Integer.parseInt(partenza[1])};
            int[] lineaArrivo = {Integer.parseInt(arrivo[0]), Integer.parseInt(arrivo[1])};

            return new Tracciato(griglia, lineaPartenza, lineaArrivo);
        }
    }
}
