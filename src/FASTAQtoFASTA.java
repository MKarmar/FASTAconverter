import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FASTAQtoFASTA implements Converter {

	/**
	 * File converter for FASTAQ to FASTA format. The file converter can convert files given a file
	 * path and a output file path. The method converters all sequences contained in the FASTAQ file
	 * and add them sequential to the FASTA file
	 */
	@Override
	public void convertFile(String fileParth, String outputFilePath) {
		try{
			BufferedReader br = new BufferedReader(new FileReader(fileParth)); //Create BufferedReader for the FASTAQ file
			BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath));//Create BuffereWriter for the FASTA file
			String sCurrentLine; //Placeholder for lines in the BufferedReader
			boolean qualityValueNext=false; //Boolean value to decide whether or not next line is a boolean value
			/**
			 * Itterate through all lines in the file, deciding which lines to add to the file and
			 * format the sequence ID's as they are found
			 */
			while ((sCurrentLine = br.readLine()) != null) {
				if(sCurrentLine.substring(0, 1).equals("@")){ //Test if a line is a sequence ID
					qualityValueNext = false; //mark that coming lines are not quality values
					sCurrentLine = ">"+sCurrentLine.substring(1); //Format sequence ID
					bw.write(sCurrentLine,0 , sCurrentLine.length()); //Add formatted sequence ID to buffered writer
					bw.newLine(); //Add a new line to the buffered writer
				}else if (sCurrentLine.substring(0, 1).equals("+")){ //Test if a line is a + (marking the next is quality values) and optionally sequence ID
					qualityValueNext = true; //mark that coming lines are quality values
				}else{ //Value lines
					if(qualityValueNext){ //If quality values do nothing
						
					}else{ //If sequence data
						bw.write(sCurrentLine,0 , sCurrentLine.length()); //add sequence data to the BuffereWriter
						bw.newLine(); //Add new line to BuffereWriter
					}
				}
			
			}
			bw.flush(); //BuffereWriter writes to file
		}catch (IOException e){
			e.printStackTrace(); //In case of I/O exception print stack trace for debugging purposes 
		}
		
	}

	
	/**
	 * This method is used for conversion of a raw FASTAQ
	 * formated string into a FASTA array list, with the 
	 * first entry being the description of the  nucleotide data.
	 * The second entry in the list is the nucleotide sequences.
	 */
	@Override
	public ArrayList<String> convertRaw(String rawFASTAQ) {
		ArrayList<String> FASTA = new ArrayList<String>(); //Holds raw result in array
		ArrayList<String> FASTAQarray = new ArrayList<String>(); //Split raw FASTAQ data into an array of 4
		
		/**	Split FASTAQ data around the string sequence newline and add
		*	to the FASTAQarray for selection to the FASTA array, with the line
		*	separator of //n
		*/
		for(String line: rawFASTAQ.split("//n")) {
			FASTAQarray.add(line); //Add lines to FASTAQ array
	    }
		
		
		FASTA.add(">"+FASTAQarray.get(0).substring(1)); //Stores description in correct format
		FASTA.add(FASTAQarray.get(1)); //Stores nucleotide sequence data
		return FASTA;
	}

	/**
	 * Standard stream based version of the conversion tools 
	 * for FASTAQ to FASTA nucleotide data.
	 */
	@Override
	public void convertStream() {
		Scanner scanner = new Scanner(System.in); //Scanner initialization listening to cmd
		System.out.println("Write FASTAQ data:");
		
		String startName = scanner.nextLine(); //First sequence of characters - Sequence ID
		String sequence = scanner.nextLine(); //Second sequence of characters - nucleotide sequence
		String qualityName = scanner.nextLine(); //Third sequence of characters - + and optionally sequence ID
		String qualityString = scanner.nextLine(); //Fourth sequence of characters - quality scores
		
		scanner.close(); //Closing scanner after all data has been read
		
		startName = ">"+startName.substring(1); //Formatting sequence ID to match FASTA format
		System.out.println(""); //New line for spacing
		System.out.println("FASTA format of the FASTAQ data");//Comment for readability
		System.out.println(startName);//Print the formated sequence ID
		System.out.println(sequence);//Print nucleotide sequence
	}

	
}
