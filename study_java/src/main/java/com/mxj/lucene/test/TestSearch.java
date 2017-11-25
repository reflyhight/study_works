package com.mxj.lucene.test;


import java.io.IOException;  
import java.util.HashSet;  
import java.util.Set;  
import java.util.concurrent.ExecutorService;  
import java.util.concurrent.Executors;  
  
import org.apache.lucene.document.Document;  
import org.apache.lucene.index.Term;  
import org.apache.lucene.search.IndexSearcher;  
import org.apache.lucene.search.Query;  
import org.apache.lucene.search.ScoreDoc;  
import org.apache.lucene.search.TermQuery;  
import org.apache.lucene.search.TopDocs;  

public class TestSearch {
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		try {
			
			IndexSearcher searcher = SearchUtil.getIndexSearcherByParentPath("index",service);
			System.out.println(SearchUtil.getMaxDocId(searcher));
			Term term = new Term("content", "lucene");
			Query query = new TermQuery(term);
			TopDocs docs = SearchUtil.getScoreDocsByPerPage(2, 20, searcher, query);
			ScoreDoc[] scoreDocs = docs.scoreDocs;
			System.out.println("所有的数据总数为："+docs.totalHits);
			System.out.println("本页查询到的总数为："+scoreDocs.length);
			for (ScoreDoc scoreDoc : scoreDocs) {
				Document doc = SearchUtil.getDefaultFullDocument(searcher, scoreDoc.doc);
				//System.out.println(doc);
			}
			System.out.println("\n\n");
			TopDocs docsAll = SearchUtil.getScoreDocs(searcher, query);
			Set<String> fieldSet = new HashSet<String>();
			fieldSet.add("path");
			fieldSet.add("modified");
			for (int i = 0 ; i < 20 ; i ++) {
				Document doc = SearchUtil.getDocumentByListField(searcher, docsAll.scoreDocs[i].doc,fieldSet);
				System.out.println(doc);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			service.shutdownNow();
		}
	}

}