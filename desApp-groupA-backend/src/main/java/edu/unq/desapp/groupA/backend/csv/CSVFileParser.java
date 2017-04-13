package edu.unq.desapp.groupA.backend.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import edu.unq.desapp.groupA.backend.csv.basicStructures.BasicStructure;

public class CSVFileParser {

	public static final char SEPARATOR = ',';
	public static final char QUOTE = '"';
	public static final char IGNORE_LINE = '#';

	public static <BuildingType extends BasicStructure> List<BuildingType> parseCSVFile(String csvFilePath, CsvResultAbstractBuilder<BuildingType> builder) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File(csvFilePath));
		List<BuildingType> basicStructures = new LinkedList<BuildingType>();
		
		while (scanner.hasNext()) {
			BuildingType basicStructure = builder.build(parseLine(scanner.nextLine())); 
			basicStructures.add(basicStructure);
		}
		scanner.close();
		
		return basicStructures;
	}

	private static List<String> parseLine(String csvLine) {
		return parseLine(csvLine, SEPARATOR, QUOTE, IGNORE_LINE);
	}

	private static List<String> parseLine(String csvLine, char separator, char quote, char ignoreLine) {
		if (isEmptyLine(csvLine)) {
			return null;
		}

		List<String> result = new LinkedList<String>();
		char[] csvArray = csvLine.toCharArray();

		StringBuffer currentValue = new StringBuffer();
		Boolean isInsideQuotes = false;
		Boolean possibleEscapedDoubleQuote = false;

		for (char csvChar : csvArray) {
			if (shouldIgnoreRestOfLine(csvChar, ignoreLine)) {
				result.add(currentValue.toString());
				isInsideQuotes = false;
				possibleEscapedDoubleQuote = false;
				return result;
			}

			if (isQuote(csvChar, quote)) {
				if (isInsideQuotes) {
					if (possibleEscapedDoubleQuote) {
						possibleEscapedDoubleQuote = false;
						currentValue.append(csvChar);
					} else {
						possibleEscapedDoubleQuote = true;
					}
				} else {
					isInsideQuotes = !isInsideQuotes;
				}
			} else {
				possibleEscapedDoubleQuote = false;
				if (isSeparator(csvChar, separator) && !isInsideQuotes) {
					result.add(currentValue.toString());
				} else {
					currentValue.append(csvChar);
				}
			}
			
		}
		result.add(currentValue.toString());
		return result;
	}
	
	private static Boolean isEmptyLine(String csvLine) {
		return csvLine == null || csvLine == ""; 
	}
	
	private static Boolean shouldIgnoreRestOfLine(char currentChar, char ignoreIndicator) {
		return currentChar == ignoreIndicator;
	}
	
	private static Boolean isQuote(char currentChar, char quoteIndicator) {
		return currentChar == quoteIndicator;
	}

	private static Boolean isSeparator(char currentChar, char separatorIndicator) {
		return currentChar == separatorIndicator;
	}

}
