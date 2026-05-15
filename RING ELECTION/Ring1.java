import java.util.Scanner;

public class Ring1 {

    public static void main(String[] args) {

        int temp, i, j;

        Rr proc[] = new Rr[10];

        // Object initialization
        for (i = 0; i < proc.length; i++) {

            proc[i] = new Rr();
        }

        Scanner in = new Scanner(System.in);

        System.out.println("Enter the number of processes:");

        int num = in.nextInt();

        // Input process details
        for (i = 0; i < num; i++) {

            proc[i].index = i;

            System.out.println("Enter ID of process:");

            proc[i].id = in.nextInt();

            proc[i].state = "active";

            proc[i].f = 0;
        }

        // Sorting process IDs
        for (i = 0; i < num - 1; i++) {

            for (j = 0; j < num - 1; j++) {

                if (proc[j].id > proc[j + 1].id) {

                    temp = proc[j].id;

                    proc[j].id = proc[j + 1].id;

                    proc[j + 1].id = temp;
                }
            }
        }

        // Display processes
        System.out.println("\nProcesses are:");

        for (i = 0; i < num; i++) {

            System.out.print("[" + i + "] "
                    + proc[i].id + " ");
        }

        int init;
        int ch;
        int temp1;
        int temp2;

        int arr[] = new int[10];

        // Highest ID becomes inactive coordinator
        proc[num - 1].state = "inactive";

        System.out.println("\nProcess "
                + proc[num - 1].id
                + " selected as coordinator");

        while (true) {

            System.out.println(
                    "\n1. Election\n2. Quit");

            ch = in.nextInt();

            for (i = 0; i < num; i++) {

                proc[i].f = 0;
            }

            switch (ch) {

                case 1:

                    System.out.println(
                            "\nEnter process number "
                                    + "which initiates election:");

                    init = in.nextInt();

                    temp2 = init;

                    temp1 = init + 1;

                    i = 0;

                    while (temp2 != temp1) {

                        if (temp1 == num) {

                            temp1 = 0;
                        }

                        if ("active".equals(proc[temp1].state)
                                && proc[temp1].f == 0) {

                            System.out.println(
                                    "\nProcess "
                                            + proc[init].id
                                            + " sends message to "
                                            + proc[temp1].id);

                            proc[temp1].f = 1;

                            init = temp1;

                            arr[i] = proc[temp1].id;

                            i++;
                        }

                        temp1++;
                    }

                    System.out.println(
                            "\nProcess "
                                    + proc[init].id
                                    + " sends message to "
                                    + proc[temp2].id);

                    arr[i] = proc[temp2].id;

                    i++;

                    int max = -1;

                    // Find highest ID
                    for (j = 0; j < i; j++) {

                        if (max < arr[j]) {

                            max = arr[j];
                        }
                    }

                    // New coordinator
                    System.out.println(
                            "\nProcess "
                                    + max
                                    + " selected as coordinator");

                    for (i = 0; i < num; i++) {

                        if (proc[i].id == max) {

                            proc[i].state = "inactive";
                        }
                    }

                    break;

                case 2:

                    System.out.println(
                            "Program terminated...");

                    in.close();

                    return;

                default:

                    System.out.println(
                            "Invalid choice");
            }
        }
    }
}

class Rr {

    public int index;

    public int id;

    public int f;

    String state;
}
