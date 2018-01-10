/*********************************************************
 Kyle Nguyen
 
 COMP 182
 
 Main Class
 
 Version 1.0
 
  The program works as expected, it prompts the user
  for input and returns the Matrix given the dimensions.
  
  It will perform all the operations endlessly
  until the user chooses the exit operation.
*********************************************************/
import java.util.Scanner;

class Main {
	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		String A = "A"; // Matrix A identifier
		String B = "B"; // Matrix B identifier

		// Method to get dimensions for MatrixA and Matrix B
		Matrix myMatrixA = getDimensions(A);
		Matrix myMatrixB = getDimensions(B);

		// Matrix C is a clone of Matrix A
		Matrix myMatrixC = myMatrixA.clone();

		// What operation?
		operation(myMatrixA.clone(), myMatrixB.clone(), myMatrixC.clone());

		scan.close(); // Close scanner
		System.exit(0);
	}

	/****************************************
	 * GET DIMENSIONS FOR MATRIX *
	 ****************************************/
	public static Matrix getDimensions(String identity) {
		int nRowsMatrix = 0;
		int nColsMatrix = 0;

		String matrixIdentifier = identity;

		boolean bInputValid = true; // Flag for valid input

		// Get row(s) for Matrix
		System.out.print("Enter the # of row(s) for Matrix " + matrixIdentifier + ": ");
		do {
			String sRowsMatrix = scan.nextLine();

			// Did you enter anything?
			if (sRowsMatrix.isEmpty()) {
				System.out.println("You didn't type anything!\r\n");
				System.out.print("Enter a valid # of row(s) for Matrix " + matrixIdentifier + ": ");
			}

			// Is it a number?
			else if (isNumeric(sRowsMatrix)) {
				{
					nRowsMatrix = Integer.parseInt(sRowsMatrix);

					// Is it positive?
					if (nRowsMatrix <= 0) {
						System.out.print("\r\nEnter a positive # for the row(s) of Matrix " + matrixIdentifier + ": ");
					}
					
					// Yes! It's a valid input, get column(s)
					else {

						// Get column(s) for Matrix
						System.out.print("\r\nEnter the # of column(s) for Matrix " + matrixIdentifier + ": ");
						do {
							String sColsMatrix = scan.nextLine();

							// Did you enter anything?
							if (sColsMatrix.isEmpty()) {
								System.out.println("You didn't type anything!\r\n");
								System.out.print("Enter a valid # of column(s) for Matrix " + matrixIdentifier + ": ");
							}

							// Is it a number?
							else if (isNumeric(sColsMatrix)) {
								{
									nColsMatrix = Integer.parseInt(sColsMatrix);

									// Is it positive?
									if (nColsMatrix <= 0) {
										System.out.print("\r\nEnter a positive # for the column(s) of Matrix " + matrixIdentifier + ": ");
									}

									// Yes! End the while loop and get dimensions.
									else {
										System.out.println();
										bInputValid = false;

									}
								}
							}

							// Invalid input
							else {
								System.out.print("\r\nEnter a valid # of column(s) for Matrix " + matrixIdentifier + ": ");
							}

						} while (bInputValid);
					}
				}
			}
			
			// Invalid Input
			else {
				System.out.print("\r\nEnter a valid # of row(s) for Matrix " + matrixIdentifier + ": ");
			}

		} while (bInputValid);

		// Matrix constructor (rows, cols)
		Matrix myMatrix = new Matrix(nRowsMatrix, nColsMatrix);
		return myMatrix;
	}

	/****************************************
	 * GET OPERATION TO DO WHAT *
	 ****************************************/
	public static void operation(Matrix A, Matrix B, Matrix C) {
		// Everything is a clone now, it's safe to perform operations without modifying data.
		Matrix myMatrixA = A;
		Matrix myMatrixB = B;
		Matrix myMatrixC = C;

		int nArow = myMatrixA.getRows();
		int nAcol = myMatrixA.getCols();

		int nBrow = myMatrixB.getRows();
		int nBcol = myMatrixB.getCols();

		// Using Class Matrix toString method
		String sMatrixA = myMatrixA.toString();
		String sMatrixB = myMatrixB.toString();
		String sMatrixC = myMatrixC.toString();

		boolean notEnding = true;

		// Always loop until program ends
		while (notEnding) {
			System.out.println();
			System.out.println("1.  Add");
			System.out.println("2.  Subtract");
			System.out.println("3.  Multiply (Dot Product)");
			System.out.println("4.  Hadamard Product");
			System.out.println("5.  Clone of Matrix");
			System.out.println("6.  Equals?");
			System.out.println("7.  Exit");
			System.out.println();

			// Method to get what VALID operation was picked
			int whatOp = whichOperation();

			// Which operation from (1 - 7)?
			switch (whatOp) {
			/****************************************
			 * MATRIX ADDITION *
			 ****************************************/
			case 1: {
				// Valid dimensions?
				if (nArow != nBrow || nAcol != nBcol) {
					System.out.println("Invalid dimensions for Addition.");
					System.out.println();
					break;
				}

				// Yes! Perform addition.
				else {
					printMatrixAandB(sMatrixA, sMatrixB);

					String sMResultAdd = myMatrixA.add(myMatrixB).toString();

					System.out.println("Addition: Matrix A + Matrix B");
					System.out.println(sMResultAdd);
					break;
				}
			}

			/****************************************
			 * MATRIX SUBTRACTION *
			 ****************************************/
			case 2: {
				// Valid dimensions?
				if (nArow != nBrow || nAcol != nBcol) {
					System.out.println("Invalid dimensions for Subtraction.");
					System.out.println();
					break;
				}

				// Yes! Perform subtraction.
				else {
					printMatrixAandB(sMatrixA, sMatrixB);

					String sMResultSub = myMatrixA.sub(myMatrixB).toString();

					System.out.println("Subtraction: Matrix A - Matrix B");
					System.out.println(sMResultSub);
					break;
				}
			}

			/****************************************
			 * MATRIX DOT PRODUCT *
			 ****************************************/
			case 3: {
				// Valid dimensions?
				if (nAcol != nBrow) {
					System.out.println("Invalid dimensions for Multiplication (Dot Product).");
					System.out.println();
					break;
				}

				// Yes! Perform dot product(multiplication).
				else {
					printMatrixAandB(sMatrixA, sMatrixB);

					String sMResultMul = myMatrixA.mul(myMatrixB).toString();

					System.out.println("Multiplication (Dot Product): Matrix A * Matrix B");
					System.out.println(sMResultMul);
					break;
				}
			}

			/****************************************
			 * MATRIX HADAMARD PRODUCT *
			 ****************************************/
			case 4: {
				// Valid dimensions?
				if (nArow != nBrow || nAcol != nBcol) {
					System.out.println("Invalid dimensions for Hadamard Product.");
					System.out.println();
					break;
				}

				// Yes! Perform Hadamard product.
				else {
					printMatrixAandB(sMatrixA, sMatrixB);

					String sMResultHad = myMatrixA.had(myMatrixB).toString();

					System.out.println("Hadamard Product: Matrix A o Matrix B:");
					System.out.println(sMResultHad);
					break;
				}
			}

			/****************************************
			 * CLONE OF MATRIX A/B *
			 ****************************************/
			case 5: {
				boolean bInputValid = true;

				System.out.print("Which Matrix do you want to clone?  A or B? ");

				do {
					String sAorB = scan.nextLine();

					// Did you enter anything?
					if (sAorB.isEmpty()) {
						System.out.println("You didn't type anything!\r\n");
						System.out.print("Please choose either Matrix A or Matrix B. ");
					}

					// Is it equal to "a" or "A"? Yes? Make clone of MatrixA then end while loop!
					else if (sAorB.equals("a") || sAorB.equals("A")) {
						System.out.println();
						printMatrixAandB(sMatrixA, sMatrixB);

						System.out.println("Clone of Matrix A:");
						String sMclone = myMatrixA.toString();
						System.out.println(sMclone);
						bInputValid = false;
					}

					// Is it equal to "b" or "B"? Yes? Make clone of MatrixB then end while loop!
					else if (sAorB.equals("b") || sAorB.equals("B")) {
						printMatrixAandB(sMatrixA, sMatrixB);

						System.out.println("Clone of Matrix B:");
						String sMclone = myMatrixB.toString();
						System.out.println(sMclone);
						bInputValid = false;
					}

					// Invalid input
					else {
						System.out.println("Invalid Matrix option!\r\n");
						System.out.print("Please choose either Matrix A or Matrix B. ");
					}
				} while (bInputValid);

				break;
			}

			/****************************************
			 * MATRIX A/B EQUAL TO MATRIX C*
			 ****************************************/
			case 6: {
				boolean bInputValid = true;

				System.out.print("Which Matrix do you want to compare?  A or B? ");
				do {
					String sAorB = scan.nextLine();

					if (sAorB.isEmpty()) {

						// Did you enter anything?
						System.out.println("You didn't type anything!\r\n");
						System.out.print("Please choose either Matrix A or Matrix B. ");
					}

					// Is it equal to "a" or "A"? Yes? Do comparison, then end while loop!
					else if (sAorB.equals("a") || sAorB.equals("A")) {
						System.out.println();
						printMatrixAandB(sMatrixA, sMatrixB);

						System.out.println("Matrix C:");
						System.out.println(sMatrixC);

						// Matrix Class method isEqual
						boolean equal = myMatrixA.isEqual(myMatrixC);
						if (equal) {
							System.out.println("Matrix A is EQUAL to Matrix C!");
						} else {
							System.out.println("Matrix A is NOT EQUAL to Matrix C!");
						}

						bInputValid = false;
					}

					// Is it equal to "b" or "B"? Yes? Do comparison, then end while loop!
					else if (sAorB.equals("b") || sAorB.equals("B")) {
						printMatrixAandB(sMatrixA, sMatrixB);

						System.out.println("Matrix C:");
						System.out.println(sMatrixC);

						// Class Matrix method isEqual
						boolean equal = myMatrixB.isEqual(myMatrixC);
						if (equal) {
							System.out.println("Matrix B is EQUAL to Matrix C!");
						} else {
							System.out.println("Matrix B is NOT EQUAL to Matrix C!");
						}

						bInputValid = false;
					}

					// Invalid input
					else {
						System.out.println("Invalid Matrix option!\r\n");
						System.out.print("Please choose either Matrix A or Matrix B. ");
					}
				} while (bInputValid);

				break;
			}

			/****************************************
			 * PROGRAM ENDING *
			 ****************************************/
			case 7: {
				System.out.print("Program Ended . . . ");
				notEnding = false; // End while loop
				break;
			}

			/****************************************
			 * SOMETHING WRONG.. *
			 ****************************************/
			default: {
				System.out.print("Something Wrong . . . "); // Who knows what happened if it got here
				notEnding = false; // End while loop
				break;
			}
			}
		}

	}

	/****************************************
	 * IS IT A NUMBER *
	 ****************************************/
	public static boolean isNumeric(String input) {
		try {
			Integer.parseInt(input);

			return true;

		}

		catch (Exception e) {
			return false;
		}
	}

	/****************************************
	 * WHAT OPERATION DID YOU PICK? *
	 ****************************************/
	public static int whichOperation() {
		int whatOp = 0;

		boolean bInputValid = true;

		// Get operation
		System.out.print("What operation do you want to do? ");
		do {
			String sInputOp = scan.nextLine();

			// Did you enter anything?
			if (sInputOp.isEmpty()) {
				System.out.println("You didn't type anything!\r\n");
				System.out.print("Enter a valid operation from 1 through 7: ");
			}

			// Is it a number?
			else if (isNumeric(sInputOp)) {
				whatOp = Integer.parseInt(sInputOp);

				// Is it within the range (1 - 7)?
				if (whatOp > 7 || whatOp < 1) {
					System.out.print("\r\nEnter an operation ONLY from 1 through 7: ");
				}

				// Yes, it's a valid operation! End while loop and do "operation".
				else {
					System.out.println();
					bInputValid = false;
				}
			}
			
			// Invalid input
			else {
				System.out.print("\r\nEnter a valid operation from 1 through 7: ");
			}
		} while (bInputValid);

		return whatOp;
	}

	/****************************************
	 * PRINT OUT MATRIX A AND MATRIX B *
	 ****************************************/
	public static void printMatrixAandB(String matrixA, String matrixB) {
		String sMatrixA = matrixA;
		String sMatrixB = matrixB;

		System.out.println("Matrix A:");
		System.out.println(sMatrixA);

		System.out.println("Matrix B:");
		System.out.println(sMatrixB);
	}
}
