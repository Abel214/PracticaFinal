/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.TDA.grafos;

import controlador.TDA.grafos.GrafosEtiquetadosDirigidos;
import controlador.TDA.grafos.PaintGraph;
import controlador.utiles.Utilidades;
import java.util.Stack;

/**
 *
 * @author Alejandro
 */
public class GrafoBellman<E> extends GrafosEtiquetadosDirigidos<E> {

    public Double[][] distancias;

    public GrafoBellman(Integer num_vertice, Class clazz) {
        super(num_vertice, clazz);
    }

    private void aplicarAlgoritmoBellmanFord(int nodoOrigen) throws Exception {
        distancias = new Double[num_vertice() + 1][num_vertice() + 1];

        for (int i = 1; i <= num_vertice(); i++) {
            for (int j = 1; j <= num_vertice(); j++) {
                if (i == j) {
                    distancias[i][j] = 0.0;
                } else if (existe_arista(i, j)) {
                    distancias[i][j] = peso_arista(i, j);
                } else {
                    distancias[i][j] = Double.POSITIVE_INFINITY;
                }
            }
        }

        // Inicializamos las distancias desde el nodo de origen
        for (int i = 1; i <= num_vertice(); i++) {
            if (i != nodoOrigen) {
                distancias[nodoOrigen][i] = Double.POSITIVE_INFINITY;
            }
        }

        // Relajamos las aristas repetidamente
        for (int i = 1; i <= num_vertice() - 1; i++) {
            for (int u = 1; u <= num_vertice(); u++) {
                for (int v = 1; v <= num_vertice(); v++) {
                    if (existe_arista(u, v)) {
                        double pesoUV = peso_arista(u, v);

                        if (distancias[nodoOrigen][u] + pesoUV < distancias[nodoOrigen][v]) {
                            distancias[nodoOrigen][v] = distancias[nodoOrigen][u] + pesoUV;
                        }
                    }
                }
            }
        }

        // Comprobamos si hay ciclos negativos después de las iteraciones
        for (int u = 1; u <= num_vertice(); u++) {
            for (int v = 1; v <= num_vertice(); v++) {
                if (existe_arista(u, v)) {
                    double pesoUV = peso_arista(u, v);

                    if (distancias[nodoOrigen][u] + pesoUV < distancias[nodoOrigen][v]) {
                        throw new Exception("Hay ciclo negativo");
                    }
                }
            }
        }

        // Imprimir las distancias con etiquetas
        System.out.println("Matriz de distancias con etiquetas después de aplicar Bellman-Ford:");

        // Imprimir encabezados de columnas
        System.out.printf("%-40s", ""); // Espacios adicionales para ajustar la alineación
        for (int i = 1; i <= num_vertice(); i++) {
            System.out.printf("%-30s", getLabelE(i));
        }
        System.out.println();

        for (int i = 1; i <= num_vertice(); i++) {
            // Imprimir etiqueta de fila
            System.out.printf("%-40s", getLabelE(i));

            for (int j = 1; j <= num_vertice(); j++) {
                // Imprimir valor de la matriz de distancias
                if (distancias[i][j] == Double.POSITIVE_INFINITY) {
                    System.out.printf("%-30s", "INF");
                } else {
                    System.out.printf("%-30.2f", distancias[i][j]);
                }
            }
            System.out.println();
        }

        // Actualizar la variable de instancia this.distancias
        this.distancias = distancias;
    }

    public String encontrarRutaMasCortaBellmanFord(int origen, int destino) throws Exception {
        try {
            aplicarAlgoritmoBellmanFord(origen);
        } catch (Exception e) {
            return "Error al aplicar el algoritmo de Bellman-Ford: " + e.getMessage();
        }

        if (distancias[origen][destino] == Double.POSITIVE_INFINITY) {
            return "No hay ruta entre " + getLabelE(origen) + " y " + getLabelE(destino);
        }

        StringBuilder rutaMasCorta = new StringBuilder();
        rutaMasCorta.append("Camino más corto desde ").append(getLabelE(origen)).append(" hasta ").append(getLabelE(destino)).append(":\n");

        int intermedio = destino;
        Stack<Integer> ruta = new Stack<>();
        ruta.push(destino);

        while (intermedio != origen) {
            for (int k = 1; k <= num_vertice(); k++) {
                if (distancias[origen][intermedio] == distancias[origen][k] + distancias[k][intermedio]) {
                    ruta.push(k);
                    intermedio = k;
                    break;
                }
            }
        }

        rutaMasCorta.append(getLabelE(origen));
        while (!ruta.isEmpty()) {
            rutaMasCorta.append(" -> ").append(getLabelE(ruta.pop()));
        }

        rutaMasCorta.append("\nSuma de los pesos: ").append(distancias[origen][destino]);

        return rutaMasCorta.toString();
    }

    public static void main(String[] args) {
        try {
            GrafoBellman<String> grafoBellman = new GrafoBellman<>(6, String.class);
            grafoBellman.labelVertice(1, "A");
            grafoBellman.labelVertice(2, "B");
            grafoBellman.labelVertice(3, "C");
            grafoBellman.labelVertice(4, "D");
            grafoBellman.labelVertice(5, "E");
            grafoBellman.labelVertice(6, "F");

            grafoBellman.insertEdge("A", "B", 2.0);
            grafoBellman.insertEdge("A", "C", 4.0);
            grafoBellman.insertEdge("B", "C", 1.0);
            grafoBellman.insertEdge("B", "D", 7.0);
            grafoBellman.insertEdge("C", "E", 3.0);
            grafoBellman.insertEdge("D", "F", 1.0);
            grafoBellman.insertEdge("E", "D", -2.0); // Peso negativo
            grafoBellman.insertEdge("E", "F", -5.0); // Peso negativo

            grafoBellman.aplicarAlgoritmoBellmanFord(1);
            grafoBellman.encontrarRutaMasCortaBellmanFord(1, 4);
            PaintGraph p = new PaintGraph();
            p.updateFile(grafoBellman);
            Utilidades.abrirNavegadorPredeterminadorWindows("d3/grafo.html");

        } catch (Exception e) {
            System.out.println("Error en el programa: " + e.getMessage());
        }
    }

}
