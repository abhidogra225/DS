//sudo apt update
// sudo apt install openmpi-bin openmpi-common libopenmpi-dev

// mpicc --version
// mpirun --version

// mpicc mpi_sum.c -o mpi_sum

//mpirun -np 4 ./mpi_sum
#include <stdio.h>
#include <mpi.h>

int main(int argc, char *argv[])
{
  int rank, size;
  int num[20]; // Total array size = 20

  MPI_Init(&argc, &argv);

  MPI_Comm_rank(MPI_COMM_WORLD, &rank); // Process ID
  MPI_Comm_size(MPI_COMM_WORLD, &size); // Total processes

  // Initialize array
  for (int i = 0; i < 20; i++)
  {
    num[i] = i + 1;
  }

  if (rank == 0) //Master rank --main collector
  {
    int s[4]; // To collect sums from other processes

    printf("Distribution at rank %d\n", rank);

    // Send 5 elements to each process (20/4 = 5)
    for (int i = 1; i < 4; i++)
    {
      MPI_Send(&num[i * 5], 5, MPI_INT, i, 1, MPI_COMM_WORLD);
    }

    int local_sum = 0;

    // Calculate sum of first 5 elements
    for (int i = 0; i < 5; i++)
    {
      local_sum += num[i];
    }

    // Receive sums from other processes
    for (int i = 1; i < 4; i++)
    {
      MPI_Recv(&s[i], 1, MPI_INT, i, 1, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
    }

    printf("Local sum at rank %d = %d\n", rank, local_sum);

    int total_sum = local_sum;

    for (int i = 1; i < 4; i++)
    {
      total_sum += s[i];
    }

    printf("Final Sum = %d\n", total_sum);
  }
  else
  {
    int k[5];

    // Receive data from master
    MPI_Recv(k, 5, MPI_INT, 0, 1, MPI_COMM_WORLD, MPI_STATUS_IGNORE);

    int local_sum = 0;

    for (int i = 0; i < 5; i++)
    {
      local_sum += k[i];
    }

    printf("Local sum at rank %d = %d\n", rank, local_sum);

    // Send result back to master
    MPI_Send(&local_sum, 1, MPI_INT, 0, 1, MPI_COMM_WORLD);
  }

  MPI_Finalize();
  return 0;
}