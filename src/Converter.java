import java.util.ArrayList;

public interface Converter {
	/**
	 * Converting of a file into another
	 * @param fileParth - path to file to read
	 * @param outputFilePath -path to file to write to
	 */
	public void convertFile(String fileParth, String outputFilePath);
	
	/**
	 * Stream based file converter, all data is given in the commandline.
	 * Thereafter 
	 */
	public void convertStream();
	
	/**
	 * Commandline converter
	 * @param rawFASTAQ - string of data using //n as line separator
	 * @return Arraylist of lines
	 */
	public ArrayList<String> convertRaw(String rawFASTAQ);
}
