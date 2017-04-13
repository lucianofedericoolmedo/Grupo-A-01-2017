package edu.unq.desapp.groupA.backend.csv;

import java.util.List;

import edu.unq.desapp.groupA.backend.csv.basicStructures.BasicProduct;

public class CsvResultBasicProductBuilder extends CsvResultAbstractBuilder<BasicProduct> {

	@Override
	public BasicProduct build(List<String> csvParserResult) {
		if (csvParserResult.isEmpty()) {
			return null;
		}
		return new BasicProduct(csvParserResult);
	}

}
