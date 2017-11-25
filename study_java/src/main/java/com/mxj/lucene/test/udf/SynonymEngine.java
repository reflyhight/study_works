package com.mxj.lucene.test.udf;

import java.io.IOException;

public interface SynonymEngine {
	 String[] getSynonyms(String s) throws IOException;  
}
