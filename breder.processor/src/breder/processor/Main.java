package breder.processor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import breder.processor.node.BProcessor;
import breder.processor.parser.Grammar;
import breder.processor.parser.ParseException;

public class Main {
	
	public static void main(String[] args) throws FileNotFoundException, ParseException {
		Grammar g = new Grammar(new FileInputStream("test/example.txt"));
		BProcessor init = g.init();
	}

}
