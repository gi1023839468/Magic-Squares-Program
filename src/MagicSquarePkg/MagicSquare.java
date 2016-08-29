/**
 * This program will check if it is a magic square or not.
 * The program will allow user to input their numbers (integers) and then the program check if the numbers meet requirement or not,
 * if it passes through, the program will process another checks, check the numbers only occur once and no repeat numbers in the
 * inputs. At the end, the program will form matrix and check if it is a magic square or not. If yes, then will return "True", 
 * otherwise, "False".
 */
package MagicSquarePkg;

/**
 * This is a main program package.
 * 
 * @author Haocheng Zhang
 *
 */
import java.io.*;
public class MagicSquare {

	/**
	 * Main class of the program.
	 * The class will handle the user inputs and check for the numbers of inputs. At the mean time, the class will utilize methods
	 * in order to accomplish the requirements. True if Magic matrix successfully constructed, otherwise False.
	 * @param args User input.
	 * @throws IOException throws any IO exceptions.
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader data =
				new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Please type all the integers you want to form the magic square (seperate by a space):");
		String input;
		Integer N2;
		input = data.readLine();
		if (!input.equals(null)){
			String [] list = input.split(" ");
			N2 = list.length;
			if(Math.sqrt(N2) % 1 == 0){
				if(CheckUnique(N2,list) == true){
					System.out.println(FormMatrix(N2,list));
				}
				else{
					System.out.println("The input values have repeat values, make sure each of the numbers only occur once.");
				}
			}
			else{
				System.out.println("Please check the values, make sure the proper number of input values was provided.");
			}
		}
		else{
			System.out.println("Did not recognize valid values, please input a set of valid values.");
			input = data.readLine();
		}
	}
	/**
	 * The Function will form a matrix list, then evaluate the values by columns, rows, and diagonals and check if it is a magic matrix.
	 * @param length The number of values you provided.
	 * @param array	The list contains all the numbers you provided.
	 * @return True or False.
	 */
	public static boolean FormMatrix(int length, String [] array){
		boolean formed = false;
		int N = (int)Math.sqrt(length);
		int index = 0;
		String [][] matrixList = new String [N][N];
		if(length == N*N){
			for(int i = 0; i < N; i ++){
				for(int k = 0; k < N; k++){
					matrixList[i][k] = array[index];
					index++;
					formed = true;
				}
			}
		}
		
		
		boolean magic = false;
		if(formed == true && length != 1){
			int [] totalArray = new int [length - 1];
			int numberRTotal = 0,numberCTotal=0,numberD1Total = 0,numberD2Total = 0,TotalIndex = 0;
			for(int i = 0; i < N;i++){
				for(int k = 0; k< N;k++){
					numberRTotal += Integer.parseInt(matrixList[i][k]);
					numberCTotal += Integer.parseInt(matrixList[k][i]);
				}
				totalArray[TotalIndex] = numberRTotal;
				numberRTotal = 0;
				TotalIndex ++;
				totalArray[TotalIndex] = numberCTotal;
				numberCTotal = 0;
				TotalIndex ++;
				numberD1Total += Integer.parseInt(matrixList[i][i]);
				numberD2Total += Integer.parseInt(matrixList[i][matrixList.length - (i+1)]);
			}
			if(numberD1Total != 0){
				totalArray[TotalIndex] = numberD1Total;
				numberD1Total = 0;
				TotalIndex++;
			}
			if(numberD2Total != 0){
				totalArray[TotalIndex] = numberD2Total;
				numberD2Total = 0;
				TotalIndex++;
			}
			for(int i = 0; i<totalArray.length;i++){
				for(int k = 1; k < totalArray.length;k++){
					if(totalArray[i] == totalArray[k]){
						magic = true;
					}
					else{
						magic = false;
						break;
					}
				}
			}
		}
		else{
			magic = true;
		}
		return magic;
	}
	/**
	 * The function will check the list with no any repeat numbers, if found a repeat numbers, will return false.
	 * @param length The number of values you provided.
	 * @param array	The list contains all the numbers you provided.
	 * @return True or False.
	 */
	public static boolean CheckUnique(int length, String [] array){
		boolean unique = true;
		if(length != 1){
			for(int i = 0; i < length; i ++){
				for(int j = 1+i; j< length; j++){
					if(array[i].equals(array[j])){
						unique = false;
					}
				}
			}
		}
		return unique;
		
	}

}
