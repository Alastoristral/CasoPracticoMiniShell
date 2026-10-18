import java.io.IOException;
import java.util.Scanner;

public class Minishellampliacion {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        // Leer cuántos programas se quieren ejecutar (mínimo 3)
        int total = 0;
        while (total < 3) {
            System.out.print("Número de programas (mínimo 3): ");
            total = Integer.parseInt(teclado.nextLine());
        }

        // Leer los programas (ejemplos en Windows: notepad, calc, mspaint)
        String[] programas = new String[total];
        for (int i = 0; i < total; i++) {
            System.out.print("Programa " + (i + 1) + ": ");
            programas[i] = teclado.nextLine();
        }

        // Iniciar TODOS los procesos sin esperar entre ellos
        Process[] procesos = new Process[total];
        for (int i = 0; i < total; i++) {
            try {
                ProcessBuilder pb = new ProcessBuilder(programas[i]);
                procesos[i] = pb.start();
            } catch (IOException e) {
                System.out.println("No se ha podido iniciar el programa: " + programas[i]);
            }
        }

        // Mostrar PID y estado de cada proceso
        for (int i = 0; i < total; i++) {
            if (procesos[i] != null) {
                ProcessHandle handle = procesos[i].toHandle();
                System.out.println("Programa: " + programas[i]);
                System.out.println("PID: " + handle.pid());
                System.out.println("Activo: " + handle.isAlive());
            }
        }

        // Esperar a que finalicen todos y mostrar el código de salida
        try {
            for (int i = 0; i < total; i++) {
                if (procesos[i] != null) {
                    int codigo = procesos[i].waitFor();
                    System.out.println("Código de salida de " + programas[i] + ": " + codigo);
                }
            }
        } catch (InterruptedException e) {
            System.out.println("La espera ha sido interrumpida.");
        }
    }
}