package com.mxj.lucene.test.mine;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class LucenIndexUtil {

	static final String indexpath=loadIndexpath() ;
	private static final Analyzer analyzer = new StandardAnalyzer();
	
	private static String loadIndexpath(){
		try {
			Properties properties= new Properties();
			properties.load(LucenIndexUtil.class.getClassLoader().getResourceAsStream("lucene.properties"));
			return properties.getProperty("indexpath");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	

	/**
	 * 
	 * @param pathStr
	 * @return
	 * @throws IOException
	 */
	public static Directory getDirectory(String pathStr) throws IOException {
		Path path = Paths.get(pathStr);
		return FSDirectory.open(path);
	}

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public static Directory getDirectory() throws IOException {
		return getDirectory(indexpath);
	}

	/**
	 * 默认使用默认分词配置
	 * @return
	 */
	public static IndexWriterConfig getIndexWriterConfig() {
		IndexWriterConfig config = new IndexWriterConfig(analyzer);
		return config;
	}

	/**
	 * 返回默认分词配置
	 * @param analyzer
	 * @return
	 */
	public static IndexWriterConfig getIndexWriterConfig(Analyzer analyzer) {
		IndexWriterConfig config = new IndexWriterConfig(analyzer);
		return config;
	}

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public static IndexWriter getIndexWriter() throws IOException {
		IndexWriterConfig config = getIndexWriterConfig();
		// 默认情况下本来就是CREATE_OR_APPEND
		// OpenMode openMode=OpenMode.CREATE_OR_APPEND;
		// config.setOpenMode(openMode);
		IndexWriter iwriter = new IndexWriter(getDirectory(), config);
		return iwriter;
	}

	/**
	 * 
	 * @param analyzer
	 * @return
	 * @throws IOException
	 */
	public static IndexWriter getIndexWriter(Analyzer analyzer) throws IOException {
		IndexWriterConfig config = getIndexWriterConfig(analyzer);
		IndexWriter iwriter = new IndexWriter(getDirectory(), config);
		return iwriter;
	}
	
	/**
	 * 
	 * @param analyzer
	 * @param openMode
	 * @return
	 * @throws IOException
	 */
	public static IndexWriter getIndexWriter(Analyzer analyzer, OpenMode openMode) throws IOException {
		IndexWriterConfig config = getIndexWriterConfig(analyzer);
		config.setOpenMode(openMode);
		IndexWriter iwriter = new IndexWriter(getDirectory(), config);
		return iwriter;
	}

	/**
	 * 
	 * @param directory
	 * @param analyzer
	 * @param openMode
	 * @return
	 * @throws IOException
	 */
	public static IndexWriter getIndexWriter(Directory directory, Analyzer analyzer, OpenMode openMode)
			throws IOException {
		IndexWriterConfig config = getIndexWriterConfig(analyzer);
		config.setOpenMode(openMode);
		IndexWriter iwriter = new IndexWriter(directory, config);
		return iwriter;
	}

	/**
	 * 关闭writer
	 * @param iwriter
	 * @throws IOException
	 */
	public static void closeIndexWriter(IndexWriter iwriter) throws IOException {
		iwriter.commit();
		iwriter.close();
	}
	

	/**
	 * 添加一条索引
	 * @param doc
	 * @param iwriter
	 * @throws IOException
	 */
	public static void addIndex(Document doc, IndexWriter iwriter) throws IOException {
		iwriter.addDocument(doc);
		closeIndexWriter(iwriter);
	}

	/**
	 * 添加一个列表的索引
	 * @param docList
	 * @param iwriter
	 * @throws IOException
	 */
	public static void addIndex(List<Document> docList, IndexWriter iwriter) throws IOException {
		for (Document doc : docList) {
			iwriter.addDocument(doc);
		}
		
		closeIndexWriter(iwriter);
	}
	
	
	

}
