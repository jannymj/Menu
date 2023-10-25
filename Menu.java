import java.util.Scanner;
import java.util.InputMismatchException;

public class Menu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\n**************************************************");
            System.out.println("       Menú de Algoritmos de Ordenamiento");
            System.out.println("**************************************************");
            System.out.println("\nPor favor, elije un algoritmo de ordenamiento:");
            System.out.println("\n [1] Bubble Sort");
            System.out.println("\n [2] Improved Bubble Sort");
            System.out.println("\n [3] Selection Sort");
            System.out.println("\n [4] Insertion Sort");
            System.out.println("\n [5] QuickSort");
            System.out.println("\n [6] Shell Sort");
            System.out.println("\n [7] Radix Sort");
            System.out.println("\n [8] Salir");

            System.out.print("\n Selecciona una opción porfis: ");
            int opcion;

            try {
                opcion = sc.nextInt(); // Intenta leer un entero
            } catch (InputMismatchException e) {
                System.out.println("\n Ash Opción inválida. Por favor, ingresa un número válido.");
                sc.next(); // Limpia el búfer de entrada
                continue; // Vuelve al inicio del bucle
            }

            switch (opcion) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    System.out.println("\n Muy buena opcion, vamos a ordenar un arreglo.");
                    procesarAlgoritmoDeOrdenamiento(opcion, sc);
                    break;
                case 8:
                    System.out.println("\n Gracias por utilizar mi menú de algoritmos de ordenamiento. ¡Bye!");
                    salir = true;
                    break;
                default:
                    System.out.println("\n Ash Opción inválida. Por favor, elige una opción válida (1-8).");
                    break;
            }
        }
        sc.close();
    }

    public static void procesarAlgoritmoDeOrdenamiento(int opcion, Scanner sc) {
        System.out.print("\n Ingresa la cantidad de elementos que quieres que tenga tu arreglo: ");
        int n = sc.nextInt();
        int[] arreglo = new int[n];

        System.out.print("\n Ingresa los números separados por un espacio: ");
        for (int i = 0; i < n; i++) {
            arreglo[i] = sc.nextInt();
        }

        System.out.println("\n Los números que se ingresaron son:");
        imprimirArreglo(arreglo);

        String nombreAlgoritmo = obtenerNombreAlgoritmo(opcion);
        System.out.println("\n Iniciando ordenamiento con el algoritmo " + nombreAlgoritmo + "........");

        if (opcion == 1) {
            BubbleSort(arreglo);
        } else if (opcion == 2) {
            ImprovedBubbleSort(arreglo);
        } else if (opcion == 3) {
            SelectionSort(arreglo);
        } else if (opcion == 4) {
            InsertionSort(arreglo);
        } else if (opcion == 5) {
            QuickSort(arreglo, 0, n - 1);
        } else if (opcion == 6) {
            shellSort(arreglo);
        } else if (opcion == 7) {
            radixSort(arreglo);
        }

        System.out.println("\n Su arreglo ordenado utilizando el algoritmo " + nombreAlgoritmo + " es:");
        imprimirArreglo(arreglo);
    }

    public static String obtenerNombreAlgoritmo(int opcion) {
        switch (opcion) {
            case 1:
                return "Bubble Sort";
            case 2:
                return "Improved Bubble Sort";
            case 3:
                return "Selection Sort";
            case 4:
                return "Insertion Sort";
            case 5:
                return "Quick Sort";
            case 6:
                return "Shell Sort";
            case 7:
                return "Radix Sort";
            default:
                return "Desconocido";
        }
    }

    public static void BubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static void ImprovedBubbleSort(int[] array) {
        int n = array.length;
        boolean intercambiado;
        do {
            intercambiado = false;
            for (int i = 0; i < n - 1; i++) {
                if (array[i] > array[i + 1]) {
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    intercambiado = true;
                }
            }
            n--;
        } while (intercambiado);
    }

    public static void SelectionSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
    }

    public static void InsertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    public static void QuickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivot = partition(array, low, high);
            QuickSort(array, low, pivot - 1);
            QuickSort(array, pivot + 1, high);
        }
    }

    public static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }

    public static void shellSort(int[] arreglo) {
        int n = arreglo.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int temp = arreglo[i];
                int j = i;
                while (j >= gap && arreglo[j - gap] > temp) {
                    arreglo[j] = arreglo[j - gap];
                    j -= gap;
                }
                arreglo[j] = temp;
            }
        }
    }

    public static void radixSort(int[] array) {
        int max = getMax(array);
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(array, exp);
        }
    }

    public static int getMax(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    public static void countingSort(int[] array, int exp) {
        int n = array.length;
        int[] output = new int[n];
        int[] count = new int[10];

        for (int i = 0; i < n; i++) {
            count[(array[i] / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            output[count[(array[i] / exp) % 10] - 1] = array[i];
            count[(array[i] / exp) % 10]--;
        }

        for (int i = 0; i < n; i++) {
            array[i] = output[i];
        }
    }

    public static void imprimirArreglo(int[] arr) {
        for (int valor : arr) {
            System.out.print(valor + " ");
        }
        System.out.println();
    }
}
