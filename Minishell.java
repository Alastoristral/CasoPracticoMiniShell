import java.io.IOException;
import java.util.Scanner;

public class Minishell {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        // Paso 1. Leer los programas (ejemplos en Windows: notepad, calc, mspaint)
        System.out.print("Primer programa: ");
        String programa1 = teclado.nextLine();
        System.out.print("Segundo programa: ");
        String programa2 = teclado.nextLine();

        try {
            // Paso 2. Crear los procesos
            ProcessBuilder pb1 = new ProcessBuilder(programa1);
            ProcessBuilder pb2 = new ProcessBuilder(programa2);

            // Paso 3. Iniciar los procesos sin esperar entre ellos
            Process proceso1 = pb1.start();
            Process proceso2 = pb2.start();

            // Paso 4. Información de los procesos
            ProcessHandle handle1 = proceso1.toHandle();
            ProcessHandle handle2 = proceso2.toHandle();
            System.out.println("PID 1: " + handle1.pid());
            System.out.println("Activo 1: " + handle1.isAlive());
            System.out.println("PID 2: " + handle2.pid());
            System.out.println("Activo 2: " + handle2.isAlive());

            // Paso 5. Esperar la finalización
            int codigo1 = proceso1.waitFor();
            int codigo2 = proceso2.waitFor();

            // Paso 6. Mostrar los resultados
            System.out.println("Código de salida 1: " + codigo1);
            System.out.println("Código de salida 2: " + codigo2);

        } catch (IOException e) {
            // Paso 7. Gestión de errores
            System.out.println("No se ha podido iniciar uno de los procesos.");
        } catch (InterruptedException e) {
            System.out.println("La espera ha sido interrumpida.");
        }
    }
}