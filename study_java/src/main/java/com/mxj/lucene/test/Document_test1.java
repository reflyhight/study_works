package com.mxj.lucene.test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class Document_test1 {

	
	public static void main(String[] args) throws IOException, ParseException {
//		Document document= new Document();
//		
//		FieldType type= new FieldType();
//		type.setStored(true);
//		type.setTokenized(true);
//		
//		IndexableField field= new Field("Name", "hello world", type);
//		document.add(field);
//		
//		Path dir=Path;
//		FSDirectory.listAll(dir)
		
		Analyzer analyzer = new StandardAnalyzer();

	    Path path=Paths.get("index");
		// Store the index in memory:
//	    Directory directory = new RAMDirectory();
	    // To store an index on disk, use this instead:
	    Directory directory = FSDirectory.open(path);
	    IndexWriterConfig config = new IndexWriterConfig(analyzer);
	    IndexWriter iwriter = new IndexWriter(directory, config);
	    Document doc = new Document();
	    String text = "This is the text to be indexed.";
//	    FieldType type = TextField.TYPE_STORED;
	    FieldType type = TextField.TYPE_NOT_STORED;
	    
	    doc.add(new Field("fieldname", text, type));
	    iwriter.addDocument(doc);
	    iwriter.close();
	    
	    // Now search the index:
	    DirectoryReader ireader = DirectoryReader.open(directory);
	    IndexSearcher isearcher = new IndexSearcher(ireader);
	    // Parse a simple query that searches for "text":
	    QueryParser parser = new QueryParser("fieldname", analyzer);
	    Query query = parser.parse("text");
	    ScoreDoc[] hits = isearcher.search(query, 1000, Sort.INDEXORDER).scoreDocs;
	    
	    
//	    assertEquals(1, hits.length);
	    // Iterate through the results:
	    for (int i = 0; i < hits.length; i++) {
	      Document hitDoc = isearcher.doc(hits[i].doc);
	      System.out.println( hitDoc.get("fieldname"));
	    }
	    ireader.close();
	    directory.close();
		
	}
	
	
	
}
