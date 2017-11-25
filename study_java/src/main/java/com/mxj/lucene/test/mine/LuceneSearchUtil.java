package com.mxj.lucene.test.mine;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.BooleanQuery.Builder;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.store.FSDirectory;

public class LuceneSearchUtil {

	public static DirectoryReader getIndexReader(String indexPath) throws IOException {
		return DirectoryReader.open(FSDirectory.open(Paths.get(indexPath)));
	}
	
	public static IndexSearcher getIndexSearcherByIndexPath()
			throws IOException {
		IndexReader reader = getIndexReader(LucenIndexUtil.indexpath);
		return new IndexSearcher(reader);
	}

	public static IndexSearcher getIndexSearcherByIndexPath(String indexPath)
			throws IOException {
		IndexReader reader = getIndexReader(indexPath);
		return new IndexSearcher(reader);
	}
	
	public static IndexSearcher getIndexSearcherByIndexPath(String indexPath, ExecutorService service)
			throws IOException {
		IndexReader reader = getIndexReader(indexPath);
		return new IndexSearcher(reader, service);
	}

	/**
	 * 组装 Must 查询集 MUST相当于sql中的 and连接两个查询条件
	 * 
	 * @param querys
	 * @return
	 */
	public static Query mustUp(Query... querys) {
		Builder builder = new BooleanQuery.Builder();
		for (Query qr : querys) {
			builder.add(qr, Occur.MUST);
		}
		return builder.build();
	}

	/**
	 * 组装Should 查询集 Should相当于sql中的in 接多个条件，表示或者
	 * 
	 * @param querys
	 * @return
	 */
	public static Query shouldUp(Query... querys) {
		Builder builder = new BooleanQuery.Builder();
		for (Query qr : querys) {
			builder.add(qr, Occur.SHOULD);
		}

		return builder.build();
	}

	/**
	 * 组件Not 相当于sql中的 not
	 * 
	 * @param mainQr
	 * @param mustnotQr
	 * @return
	 */
	public static Query mustNot(Query mainQr, Query mustnotQr) {
		Builder builder = new BooleanQuery.Builder();
		builder.add(mainQr, Occur.MUST);
		builder.add(mainQr, Occur.MUST_NOT);
		return builder.build();
	}

	/**
	 * 获取所有
	 * 
	 * @param searcher
	 * @param qr
	 * @return
	 * @throws IOException
	 */
	public static TopDocs searchAll(IndexSearcher searcher, Query qr) throws IOException {
		TopDocs docs = searcher.search(qr, Integer.MAX_VALUE);
		return docs;
	}
	
	
	public static List<Document> searchAllDocByQr(Query qr) throws IOException {
		IndexSearcher iSearcher = getIndexSearcherByIndexPath();
		TopDocs allDocs = searchAll(iSearcher, qr);
		ScoreDoc[] scoreDocs = allDocs.scoreDocs;
		List<Document> list = new ArrayList<>();
		for (ScoreDoc scoreDoc : scoreDocs) {
			Document doc = iSearcher.doc(scoreDoc.doc);
			list.add(doc);
		}
		return list;
	}

	/**
	 * 
	 * @param fieldName
	 * @param lineStr
	 * @param analyzer
	 * @return
	 * @throws ParseException
	 */
	public static Query mkQrOfStr(String fieldName,String lineStr, Analyzer analyzer) throws ParseException {
		QueryParser qp = new QueryParser(fieldName, analyzer);
		return qp.parse(lineStr);
	}
	
	/**
	 * 
	 * @param fieldName
	 * @param lineStr
	 * @return
	 * @throws ParseException
	 */
	public static Query mkQrOfStr(String fieldName,String lineStr) throws ParseException {
		QueryParser qp = new QueryParser(fieldName, new StandardAnalyzer());
		return qp.parse(lineStr);
	}

	/**
	 * 
	 * @param fieldName
	 * @param termStr
	 * @return
	 */
	public static Query termQr(String fieldName, String termStr) {
		Term term = new Term(fieldName, termStr);
		return new TermQuery(term);
	}
	
	/**
	 * 切割获得 
	 * @param fieldName
	 * @param lineStr
	 * @param cutRegex
	 * @return
	 */
	public static Query mkQrbySpilt2Term(String fieldName,String lineStr,String cutRegex){
		String[] split = lineStr.split(cutRegex);
		Query[]qrs= new Query[split.length];
		
		for(int i=0;i<split.length;i++){
			String termStr=split[i];
			qrs[i]=termQr( fieldName,  termStr);
		}
		
		return shouldUp(qrs);
	}

	
	
}
