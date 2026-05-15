//javac Berkeley.java
//java Berkeley

import java.util.*;

public class Berkeley {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int[] time = new int[n];

        // Input times
        for (int i = 0; i < n; i++) {
            System.out.print("Enter time of process " + i + ": ");
            time[i] = sc.nextInt();
        }

        // Assume process 0 is master
        int masterTime = time[0];
        int sumDiff = 0;

        // Calculate differences
        for (int i = 0; i < n; i++) {
            int diff = time[i] - masterTime;
            sumDiff += diff;
        }

        // Average adjustment
        int avg = sumDiff / n;

        System.out.println("\nAverage adjustment: " + avg);

        // Adjust times
        for (int i = 0; i < n; i++) {
            time[i] = time[i] - avg;
            System.out.println("Updated time of process " + i + ": " + time[i]);
        }

        sc.close();
    }
}