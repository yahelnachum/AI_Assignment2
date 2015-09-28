Author:						Gregory Port, Cuong Nguyen, Duc Pham, Yahel Nachum
Date:						9/24/2015
Version:					1.0
Project ID:					Assignment #2
CS Class:					CS 4341
Programming Language:		Java
OS/Hardware dependencies:	

Problem Description: 		This assignment included solving three different puzzles 
							with the use of genetic algorithms. The first puzzle 
							consists of adding numbers to a goal value without going 
							over the goal value. The second puzzle consists of 
							putting an equal amount of numbers into three different 
							bins with a score consisting of multiplying the first bin 
							numbers together and adding the second bin numbers together. 
							The final puzzle included constructing buildings, trying 
							to get the highest possible legal building made from the 
							pieces given.

Overall Design:			

	Data representation 
							Each puzzle represents its data in different objects. 
							Puzzle1 uses the NumberSequence class to hold an array of 
							integers to represent a solution to the puzzle. Puzzle2 
							uses a Bins object that holds three different arrays of 
							doubles to represent a solution to the puzzle. Puzzle3 
							uses the Building and BuildingPiece object, where a 
							BuildingPiece represents a line of input from the input 
							file and a Building represents a collection of BuildingPieces 
							objects in a certain order as a solution to the puzzle.
	
	Algorithms 			
							There were certain algorithms to reproduce and mutate the 
							population for each puzzle. These algorithms are explained 
							in more detail in the write up documentation called 
							"Assignment2_Writeup.docx".
	
Program Assumptions 
      and Restrictions:		
      						The program assumes that the arguments have been inputed 
      						correctly. It also assumes that the input file given to 
      						read from has been formatted correctly according to the 
      						puzzle the user wants to solve.
Interfaces:
	User	
							The user of the program interacts with the program through 
							command line arguments. The first argument the user can 
							enter is which puzzle they would like to run (1,2, or 3). 
							The next argument is the input file to read from. The final 
							argument is how many seconds the user would like the genetic 
							algorithm to run before returing its solution to the puzzle.
	
Implementation Details:
	Data					
							In each of the puzzle they are simplified through the use of 
							objects to hold data and ask questions for sorting based on 
							fitness and score.
							
							Puzzle1 uses an object called a NumberSequence which just 
							holds an array of integers and a goal value.
							
							Puzzle2 uses an object called Bins which holds the three 
							different bins as double arrays.
							
							Puzzle3 uses two objects called Building and BuildingPiece. 
							A BuildingPiece describes a line of input from the input file. 
							A building is an object made up of building pieces to 
							construct a structure.
							
							Each puzzle has its own class calleld Puzzle(number) which keeps 
							track of , culls, reproduces, and mutates the population 
							each generation.
	
	Variables	
	
How to build the program:	To build the program you must take the following steps on the command line: 
							"(path to javac.exe)\javac.exe" -g src\*
							cd src
							java ga (puzzle int) "puzzle_input_file.txt" (seconds int)
							
							Ex.
							"C:\Program Files\Java\jdk####\bin\javac.exe" -g src\*
							cd src
							java ga 1 "puzzle1_input_file.txt" 10

Program Source:				
							ga.java
							
							PuzzleOne.java
							NumberSequence.java
							
							PuzzleTwo.java
							Bins.java
							
							PuzzleThree.java
							Building.java
							BuildingPiece.java
							
							Clock.java
							FileInputOutput.java

Additional Files:		
							Assignment2_Writeup.docx
							
							puzzle1_results.xlsx
							puzzle2_results.xlsx
							puzzle3_results.xlsx
							
							puzzle1_input_file.txt
							puzzle1_input_file2.txt
							puzzle2_input_file.txt
							puzzle3_input_file.txt
							

Results:					
							"puzzle1_input_file.txt" results from 5 different runs:
									************ add in results here ************
							"puzzle2_input_file.txt" results from 5 different runs:
									************ add in results here ************
							"puzzle3_input_file.txt" results from 5 different runs:
									************ add in results here ************

Test Procedures:			
							Testing included looking at the population every few 
							generations to see what the most fit, median fit, and 
							worst fit from the population are and how fast they get 
							better.

Test Data:
							Test data is given in the files under the "Additional Files:" 
							section of this REAMDE. Input files are named 
							"puzzle(number)_input_file(number).txt".

Performance Evaluation:
	Time/Space			
							The time it takes for the genetic algorithm to find a very good 
							solution is usually 250-300 milliseconds or within 250-300 
							generations. The program does not take up much space in memory 
							because only 100 objects are kept every generation and the 
							objects consist of very basic information like numbers and 
							string types.
	
	User Interface			
							
	
References:					Class book, class slides, stackoverflow partially for some of 
							the FileInputOutput code (website url given in FileInputOutput class)

