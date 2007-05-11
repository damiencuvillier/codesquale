package com.codesquale.parser;

import com.codesquale.metrics.MetricsFactoryProvider;

public class ParsingUnitFactory {
	private static ParsingUnitFactory _instance = null;

	private ParsingUnitFactory()
	{
	}
	
	
	public static ParsingUnitFactory getInstance()
	{
		if(_instance == null) _instance = new ParsingUnitFactory();
		
		return _instance;
	}
	
	public IParsingUnit createInstance()
	{
		return new ParsingUnit();
	}
}
