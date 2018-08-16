import java.util.ArrayList;

public class Main {
	public static void main(String [] args)
	{
		Converter conv = new FASTAQtoFASTA();// Initialization of converter

		/**
		 * Call to file based converter
		 */
		if(args[0].equals("File")){
			conv.convertFile(args[1], args[2]);//Call to file converter with arguments file path and output path
		}
		/**
		 * Call to raw conversion
		 */
		else if (args[0].equals("Raw")){
			ArrayList<String> result = conv.convertRaw(args[1]);//Call to file converter with string argument
			System.out.println("");//Spacing in comandline
			System.out.println("The result of the conversion:");//Comment for commandline
			/**
			 * Printing each all lines from the results
			 */
			for(String line: result){
				System.out.println(line);//print single line
			}
		}
		/**
		 * Call to std stream based conversion 
		 */
		else if (args[0].equals("Stream")){
			conv.convertStream();//Call to std stream based conversion 
		}
		/**
		 * Illigal conversion method
		 */
		else{
			throw new IllegalArgumentException(String.format("Invalid imputtype"));
		}
	}
}
