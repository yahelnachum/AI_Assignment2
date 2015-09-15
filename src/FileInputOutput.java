import java.io.*;

// skeleton of file input reader is taken from website in the next comment line
// http://stackoverflow.com/questions/4716503/best-way-to-read-a-text-file-in-java
public class FileInputOutput {
	
	
	static void fileToPuzzleOne(String fileName) {
		try {
			// open up file
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			try {
				StringBuilder sb = new StringBuilder();
				String line = br.readLine();
				
				
				// build up string line by line
				while (line != null) {
					
					sb.append(line);
					line = br.readLine();
				}

				// change from string builder to string and take out tabs
				String everything = sb.toString();
				String noTabs = everything.replaceAll("\t", "");

				// close file
				br.close();
				
			} finally {

			}
		} catch (Exception e) {

		}
		
	}
	
	static void fileToPuzzleTwo(String fileName) {
		
	}
	
	static void fileToPuzzleThree(String fileName) {
		
	}
	
	
}

