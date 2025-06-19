import java.util.List;

import clases.Maquina;
import clases.Servicio;

public class Main {
    public static void main(String[] args) {
            Servicio s1 = new Servicio("src/data/Maquinas.txt");
            System.out.println(s1.getMaquinas());
            System.out.println("*Bactraking: ");
            List<Maquina> back = s1.bactracking();
            System.out.println("Solucion Obtenida: " + back);
            System.out.println("Piezas Producidas: " + s1.getPiezasTotales());
            System.out.println("Cantidad de Puestas en Funcionamiento: " + back.size());
            System.out.println("Estados Generados: " + s1.getContador());
            System.out.println("*Greedy: ");
            List<Maquina> gree = s1.greedy();
            System.out.println("Solucion Obtenida: " + gree);
            System.out.println("Piezas Producidas: " + s1.getSuma());
            System.out.println("Cantidad de Puestas en Funcionamiento: " + gree.size());
            System.out.println("Candidatos Considerados: " + s1.getContadorGreddy());
        }
}