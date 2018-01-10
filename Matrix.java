/*********************************************************
 Kyle Nguyen
 
 COMP 182
 
 Matrix Class
 
 Version 1.0
 
  The program works as expected, it prompts the user
  for input and returns the Matrix given the dimensions.
  
  It will perform all the operations endlessly
  until the user chooses the exit operation.
 *********************************************************/

import java.util.Arrays;
import java.util.Random;

public class Matrix {
	private int mRows; // Rows
	private int mCols; // Columns
	private int[][] mElements; // Row x Column Int Array

	/****************************************
	 * CONSTRUCTORS *
	 ****************************************/
	// Default Constructor:
	public Matrix() {
		this.mRows = 3;
		this.mCols = 3;
		this.mElements = new int[mRows][mCols];
		this.populate(-100, 100);
	}

	// Initialization Constructor given size
	public Matrix(int rows, int cols) {
		this.mRows = rows;
		this.mCols = cols;
		this.mElements = new int[mRows][mCols];
		this.populate(-100, 100);
	}

	// Initialize Array Constructor
	public Matrix(int[][] matrixArray) {
		this.mRows = matrixArray.length;
		this.mCols = matrixArray[0].length;
		this.mElements = matrixArray.clone();

	}

	/****************************************
	 * GET METHODS FOR ROW / COLUMN *
	 ****************************************/
	public int getRows() {
		return this.mRows;
	}

	public int getCols() {
		return this.mCols;
	}

	/****************************************
	 * Matrix CLONE *
	 ****************************************/
	public Matrix clone() {
		return new Matrix(this.mElements.clone());
	}

	/****************************************
	 * MATRIX POPULATE *
	 ****************************************/
	public void populate(int minValue, int maxValue) {
		Random genNums = new Random();

		for (int i = 0; i < mRows; i += 1) {
			for (int j = 0; j < mCols; j += 1) {
				this.mElements[i][j] = genNums.nextInt((maxValue - minValue) + 1) + minValue;
			}
		}
	}

	/****************************************
	 * MATRIX ADDITION *
	 ****************************************/
	public Matrix add(Matrix otherMatrix) {
		int[][] resultMatrixArray = new int[mRows][mCols];

		for (int i = 0; i < mRows; i += 1) {
			for (int j = 0; j < mCols; j += 1)
				resultMatrixArray[i][j] = this.mElements[i][j] + otherMatrix.mElements[i][j];
		}

		return new Matrix(resultMatrixArray);
	}

	/****************************************
	 * MATRIX SUBTRACTION *
	 ****************************************/
	public Matrix sub(Matrix otherMatrix) {
		int[][] resultMatrixArray = new int[mRows][mCols];

		for (int i = 0; i < mRows; i += 1) {
			for (int j = 0; j < mCols; j += 1)
				resultMatrixArray[i][j] = this.mElements[i][j] - otherMatrix.mElements[i][j];
		}

		return new Matrix(resultMatrixArray);
	}

	/****************************************
	 * MATRIX DOT PRODUCT *
	 ****************************************/
	public Matrix mul(Matrix otherMatrix) {
		int[][] resultMatrixArray = new int[mRows][otherMatrix.mCols]; // Rows of MatrixA, Cols of Matrix B

		for (int i = 0; i < mRows; i += 1)
			for (int j = 0; j < otherMatrix.mCols; j += 1)
				for (int k = 0; k < this.mCols; k += 1) {
					resultMatrixArray[i][j] += this.mElements[i][k] * otherMatrix.mElements[k][j];
				}

		return new Matrix(resultMatrixArray);
	}

	/****************************************
	 * MATRIX HADAMARD PRODUCT *
	 ****************************************/
	public Matrix had(Matrix otherMatrix) {
		int[][] resultMatrixArray = new int[mRows][mCols];

		for (int i = 0; i < mRows; i += 1) {
			for (int j = 0; j < mCols; j += 1)
				resultMatrixArray[i][j] = this.mElements[i][j] * otherMatrix.mElements[i][j];
		}

		return new Matrix(resultMatrixArray);
	}

	/****************************************
	 * MATRIX EQUAL *
	 ****************************************/
	public boolean isEqual(Matrix otherMatrix) {
		if (Arrays.deepEquals(this.mElements, otherMatrix.mElements)) { // Returns true if elements of the array are truly equal
			return true;
		}

		else
			return false;
	}

	/****************************************
	 * MATRIX TO STRING *
	 ****************************************/
	public String toString() {
		String displayMatrixArray = "";
		for (int[] i : this.mElements) { // For each int [][] Matrix, iterate to get int [] i
			displayMatrixArray += "[";

			for (int j : i) { // For each int [], iterate to get int elements
				displayMatrixArray += String.format(" % 6d", j); // Do something with elements
			}

			displayMatrixArray += "] \n"; // New line for next row
		}

		return displayMatrixArray;
	}
}
