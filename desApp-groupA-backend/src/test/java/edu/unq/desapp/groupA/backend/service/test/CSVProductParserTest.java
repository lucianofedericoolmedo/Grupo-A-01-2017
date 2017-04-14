package edu.unq.desapp.groupA.backend.service.test;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.unq.desapp.groupA.backend.csv.CSVFileParser;
import edu.unq.desapp.groupA.backend.csv.CsvResultBasicProductBuilder;
import edu.unq.desapp.groupA.backend.csv.basicStructures.BasicProduct;

public class CSVProductParserTest {

	String folderPath = "./src/test/java/";
	String oneProductFile;
	String threeProductsFile;
	String doubleQuotesFile;
	String someFieldEmpty;

	CsvResultBasicProductBuilder basicProductBuilder;
	
	@Before
	public void setup() {
		oneProductFile = "testBasicCsv.csv";
		threeProductsFile = "testManyProductsCsv.csv";
		doubleQuotesFile = "testDoubleQuotesInsideQuotesCsv.csv";
		someFieldEmpty = "testSomeFieldsEmptyCsv.csv";
		basicProductBuilder = new CsvResultBasicProductBuilder();
	}
	
	@Test
	public void test_GivenACsvFileWithOneRow_WhenParsingIt_ThenShouldReturnAListWithOneBasicProductWithTheDataInTheFile() throws FileNotFoundException {
		Integer expectedAmountOfProductsInFile = 1;
		
		Long expectedIdForProduct0 = new Long(0);
		String expectedNameForProduct0 = "Surtidas";
		String expectedBrandForProduct0 = "Bagley";
		Integer expectedStockForProduct0 = 10;
		Double expectedPriceForProduct0 = 25.0;
		String expectedImageForProduct0 = "una-url";
		
		List<BasicProduct> basicProducts = CSVFileParser.parseCSVFile(folderPath + oneProductFile, basicProductBuilder);
		
		assertEquals(expectedAmountOfProductsInFile, (Integer) basicProducts.size());
		
		BasicProduct basicProduct0 = basicProducts.get(0);
		assertEquals(expectedIdForProduct0, basicProduct0.getId());
		assertEquals(expectedNameForProduct0, basicProduct0.getName());
		assertEquals(expectedBrandForProduct0, basicProduct0.getBrand());
		assertEquals(expectedStockForProduct0, basicProduct0.getStock());
		assertEquals(expectedPriceForProduct0, basicProduct0.getPrice());
		assertEquals(expectedImageForProduct0, basicProduct0.getImage());
	}

	@Test
	public void test_GivenACsvFileWithThreeRow_WhenParsingIt_ThenShouldReturnAListWithThreeBasicProductWithTheDataInTheFile() throws FileNotFoundException {
		Integer expectedAmountOfProductsInFile = 3;

		Long expectedIdForProduct0 = new Long(1);
		String expectedNameForProduct0 = "Pepas";
		String expectedBrandForProduct0 = "Dia";
		Integer expectedStockForProduct0 = 50;
		Double expectedPriceForProduct0 = 28.0;
		String expectedImageForProduct0 = "url-pepas";

		Long expectedIdForProduct1 = new Long(2);
		String expectedNameForProduct1 = "Anillos de limon";
		String expectedBrandForProduct1 = "Dia";
		Integer expectedStockForProduct1 = 80;
		Double expectedPriceForProduct1 = 12.50;
		String expectedImageForProduct1 = "url-anillitos";
		
		Long expectedIdForProduct2 = new Long(3);
		String expectedNameForProduct2 = "Papas Fritas";
		String expectedBrandForProduct2 = "Lay's";
		Integer expectedStockForProduct2 = 160;
		Double expectedPriceForProduct2 = 32.0;
		String expectedImageForProduct2 = "url-papitas";

		
		List<BasicProduct> basicProducts = CSVFileParser.parseCSVFile(folderPath + threeProductsFile, basicProductBuilder);
		
		assertEquals(expectedAmountOfProductsInFile, (Integer) basicProducts.size());
		
		BasicProduct basicProduct0 = basicProducts.get(0);
		assertEquals(expectedIdForProduct0, basicProduct0.getId());
		assertEquals(expectedNameForProduct0, basicProduct0.getName());
		assertEquals(expectedBrandForProduct0, basicProduct0.getBrand());
		assertEquals(expectedStockForProduct0, basicProduct0.getStock());
		assertEquals(expectedPriceForProduct0, basicProduct0.getPrice());
		assertEquals(expectedImageForProduct0, basicProduct0.getImage());
		
		BasicProduct basicProduct1 = basicProducts.get(1);
		assertEquals(expectedIdForProduct1, basicProduct1.getId());
		assertEquals(expectedNameForProduct1, basicProduct1.getName());
		assertEquals(expectedBrandForProduct1, basicProduct1.getBrand());
		assertEquals(expectedStockForProduct1, basicProduct1.getStock());
		assertEquals(expectedPriceForProduct1, basicProduct1.getPrice());
		assertEquals(expectedImageForProduct1, basicProduct1.getImage());

		BasicProduct basicProduct2 = basicProducts.get(2);
		assertEquals(expectedIdForProduct2, basicProduct2.getId());
		assertEquals(expectedNameForProduct2, basicProduct2.getName());
		assertEquals(expectedBrandForProduct2, basicProduct2.getBrand());
		assertEquals(expectedStockForProduct2, basicProduct2.getStock());
		assertEquals(expectedPriceForProduct2, basicProduct2.getPrice());
		assertEquals(expectedImageForProduct2, basicProduct2.getImage());
	}

	@Test
	public void test_GivenACsvFileWithOneRowAndQuotesInsideName_WhenParsingIt_ThenShouldReturnAListWithOneBasicProductWithTheDataInTheFileAndTheNameWithDoubleQuotesInside() throws FileNotFoundException {
		Integer expectedAmountOfProductsInFile = 1;
		
		Long expectedIdForProduct0 = new Long(10);
		String expectedNameForProduct0 = "Coca \"cocucha\" Cola";
		String expectedBrandForProduct0 = "Reginald Lee";
		Integer expectedStockForProduct0 = 10;
		Double expectedPriceForProduct0 = 32.0;
		String expectedImageForProduct0 = "url-coca";
		
		List<BasicProduct> basicProducts = CSVFileParser.parseCSVFile(folderPath + doubleQuotesFile, basicProductBuilder);
		
		assertEquals(expectedAmountOfProductsInFile, (Integer) basicProducts.size());
		
		BasicProduct basicProduct0 = basicProducts.get(0);
		assertEquals(expectedIdForProduct0, basicProduct0.getId());
		assertEquals(expectedNameForProduct0, basicProduct0.getName());
		assertEquals(expectedBrandForProduct0, basicProduct0.getBrand());
		assertEquals(expectedStockForProduct0, basicProduct0.getStock());
		assertEquals(expectedPriceForProduct0, basicProduct0.getPrice());
		assertEquals(expectedImageForProduct0, basicProduct0.getImage());
	}

	@Test
	public void test_GivenACsvFileWithOneRowWithSomeEmptyFields_WhenParsingIt_ThenShouldReturnAListWithOneBasicProductWithTheDataInTheFileAndNullWhereTheRowDoesNotHaveInformation() throws FileNotFoundException {
		Integer expectedAmountOfProductsInFile = 2;
		
		Long expectedIdForProduct0 = null;
		String expectedNameForProduct0 = "Pitusas";
		String expectedBrandForProduct0 = "";
		Integer expectedStockForProduct0 = 10;
		Double expectedPriceForProduct0 = 32.0;
		String expectedImageForProduct0 = "url-pitusas";

		Long expectedIdForProduct1 = new Long(12);
		String expectedNameForProduct1 = "Topolin";
		String expectedBrandForProduct1 = "Topolin";
		Integer expectedStockForProduct1 = null;
		Double expectedPriceForProduct1 = null;
		String expectedImageForProduct1 = "url-topolin";

		List<BasicProduct> basicProducts = CSVFileParser.parseCSVFile(folderPath + someFieldEmpty, basicProductBuilder);
		
		assertEquals(expectedAmountOfProductsInFile, (Integer) basicProducts.size());
		
		BasicProduct basicProduct0 = basicProducts.get(0);
		assertEquals(expectedIdForProduct0, basicProduct0.getId());
		assertEquals(expectedNameForProduct0, basicProduct0.getName());
		assertEquals(expectedBrandForProduct0, basicProduct0.getBrand());
		assertEquals(expectedStockForProduct0, basicProduct0.getStock());
		assertEquals(expectedPriceForProduct0, basicProduct0.getPrice());
		assertEquals(expectedImageForProduct0, basicProduct0.getImage());
		
		BasicProduct basicProduct1 = basicProducts.get(1);
		assertEquals(expectedIdForProduct1, basicProduct1.getId());
		assertEquals(expectedNameForProduct1, basicProduct1.getName());
		assertEquals(expectedBrandForProduct1, basicProduct1.getBrand());
		assertEquals(expectedStockForProduct1, basicProduct1.getStock());
		assertEquals(expectedPriceForProduct1, basicProduct1.getPrice());
		assertEquals(expectedImageForProduct1, basicProduct1.getImage());
	}

}
