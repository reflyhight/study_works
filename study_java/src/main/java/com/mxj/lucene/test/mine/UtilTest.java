package com.mxj.lucene.test.mine;


import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.junit.Test;


public class UtilTest {
	
	
	@Test
	public void mkIndex(){
		try {
			Analyzer analyzer= new StopAnalyzer(CharArraySet.EMPTY_SET);
			IndexWriter iwriter = LucenIndexUtil.getIndexWriter(analyzer,OpenMode.CREATE);
			Document doc= new Document();
			doc.add(new Field("propertys_str", "青春|漂亮|17to29", TextField.TYPE_STORED));
			LucenIndexUtil.addIndex(doc, iwriter);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void searchIndex(){
		try {
			Term term= new Term("propertys_str", "漂亮");
			TermQuery termQr= new TermQuery(term);
			
			IndexSearcher searcher = LuceneSearchUtil.getIndexSearcherByIndexPath();
			TopDocs searchAll = LuceneSearchUtil.searchAll(searcher, termQr);
			
			ScoreDoc[] scoreDocs = searchAll.scoreDocs;
			for (ScoreDoc scoreDoc : scoreDocs) {
				Document doc = searcher.doc(scoreDoc.doc);
				System.out.println(doc);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) throws Exception {
		
		
	}

}
