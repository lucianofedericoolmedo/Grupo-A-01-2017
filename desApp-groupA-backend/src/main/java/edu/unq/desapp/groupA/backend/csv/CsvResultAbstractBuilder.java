package edu.unq.desapp.groupA.backend.csv;

import java.util.List;

public abstract class CsvResultAbstractBuilder<BuildingType> {

	public abstract BuildingType build(List<String> csvParserResult);

}
