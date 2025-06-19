package utils;

import java.io.*;
import java.util.*;
import clases.Maquina;

public class TXTReader {
    private String pathMaquinas;
    private int piezasTotales;

    // Constructor que recibe el path
    public TXTReader(String pathMaquinas) {
        this.pathMaquinas = pathMaquinas;
        this.piezasTotales = 0;
    }

    public int getPiezasTotales() {
        return piezasTotales;
    }

    // Método para leer el archivo y devolver el total de piezas
    public void leerArchivo(List<Maquina> maquinas) throws IOException {


        try (BufferedReader br = new BufferedReader(new FileReader(pathMaquinas))) {
            String linea;

            // Leer la primera línea con el total de piezas
            if ((linea = br.readLine()) != null) {
                this.piezasTotales = Integer.parseInt(linea.trim());
            }

            // Leer las siguientes líneas con las máquinas y piezas
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 2) {
                    String nombre = partes[0].trim();
                    int piezas = Integer.parseInt(partes[1].trim());
                    maquinas.add(new Maquina(nombre, piezas));
                }
            }
        }


    }
}