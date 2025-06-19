package clases;

import utils.TXTReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Servicio {
    private List<Maquina> maquinas;
    private List<Maquina> mejorResultadoBactracking;
    private int piezasTotales;
    private int contador = 0;
    private int contadorGreddy = 0;
    private int suma = 0;


    public Servicio(String pathMaquinas) {
        TXTReader lector = new TXTReader(pathMaquinas);
        maquinas = new ArrayList<>();
        try {
            lector.leerArchivo(maquinas);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.piezasTotales= lector.getPiezasTotales();
    }

    
    public List<Maquina> getMaquinas() {
        List<Maquina> copia = new ArrayList<Maquina>(maquinas);
    	return copia;
    }
    
    
    public int getPiezasTotales() {
        return this.piezasTotales;
    }
    
    
    public int getContador() {
        return contador;
    }
    
    
    public int getContadorGreddy() {
        return contadorGreddy;
    }
    
    
	public int getSuma() {
		return this.suma;
	}

    
    /*
     * Estrategia Backtracking:
     * - Árbol de exploración: en cada nivel se añade una máquina (permitiendo repetirlas) y se
     *   exploran todas las combinaciones posibles de secuencias cuya suma parcial no supere el objetivo.
     * - Estado: la lista actual de máquinas usadas y la suma parcial de piezas.
     * - Estado solución: la lista actual de máquinas donde la suma parcial == piezasTotales.
     * - Poda:
     *   1) Si la suma parcial > piezasTotales, se corta la rama.
     *   2) Si ya existe una solución mejor (menor número de arranques) y la rama actual es igual o peor,
     *      se poda para no explorar secuencias más largas innecesarias.
     */
    public List<Maquina> bactracking() {
        /*
         * Estrategia Backtracking:
         * - El estado es la lista de máquinas usadas hasta el momento y la suma parcial de piezas.
         * - En cada nivel se prueba usar una máquina, y se generan hijos repitiendo cualquiera.
         * - Estado final: suma de piezas == total requerido.
         * - Poda: si la suma parcial supera el total de piezas, cortamos la rama.
         */
        mejorResultadoBactracking = new ArrayList<>();
        bactracking(new ArrayList<>(), 0,0);
        return mejorResultadoBactracking;
    }

    
    private void bactracking(List<Maquina> listaDeMaquinaAux, int index, int piezasProducidas) {
        contador++;
        
        if (piezasProducidas == piezasTotales) {
            if ((mejorResultadoBactracking.isEmpty()) || (listaDeMaquinaAux.size() < mejorResultadoBactracking.size())) {
                mejorResultadoBactracking = new ArrayList<>(listaDeMaquinaAux); 
            }
            return;
        }
        
        if((!mejorResultadoBactracking.isEmpty()) && (listaDeMaquinaAux.size() >= mejorResultadoBactracking.size())) {
              return;
         }
        
        for (int i = index; i < maquinas.size(); i++) {
            Maquina m = maquinas.get(i);
            if (piezasProducidas + m.getCantPiezas() <= piezasTotales) {
                listaDeMaquinaAux.add(m);
                bactracking(listaDeMaquinaAux, i, (piezasProducidas + m.getCantPiezas()));
                listaDeMaquinaAux.remove(listaDeMaquinaAux.size() - 1); 
            }
        }
    }

    
    /*
     * Estrategia Greedy:
     * - Candidatos: tomamos todas las máquinas ordenadas de mayor a menor segun su producción de piezas.
     * - Selección: iteramos sobre la lista ordenada, y en cada paso escogemos la primer máquina que no exceda el total restante de piezas a producir.
     * - Consideraciones:
     *   * Es muy eficiente pero NO garantiza una solución óptima para todos los conjuntos de máquinas,.
     *      por ejemplo para casos que sean imposibles de llegar (EJ: querer 94 piezas pero tenes las siguientes maquinas: [M1,7] [M2,10])
     *   * Por eso si al terminar la suma nos da que != piezasTotales, entonces concluimos que
     *      no se encuentra solución para la configuración dada.
     */
    public List<Maquina> greedy() {
        List<Maquina> seleccionadas = new ArrayList<>();
        int index = 0;
        
        List<Maquina> ordenadas = new ArrayList<>(maquinas);
        ordenadas.sort((m1, m2) -> Integer.compare(m2.getCantPiezas(), m1.getCantPiezas()));   // Ordenar las máquinas de mayor a menor cantidad de piezas
        
        while (suma < piezasTotales && index < ordenadas.size()){
           Maquina maquina = ordenadas.get(index);
           contadorGreddy++;
           if(suma + maquina.getCantPiezas() <= piezasTotales) {
               seleccionadas.add(maquina);
               suma += maquina.getCantPiezas();
           } else if (index < ordenadas.size()){
               index++;
           }
        }
        
        if (suma != piezasTotales) {
        	System.out.println("No se puede resolver con Greedy");
        	suma = 0;
            return new ArrayList<>(); 
        }
        
        return seleccionadas;
    }
}
