package com.mxj.lucene.test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class Document_test2 {
	
	public static void main(String[] args) throws IOException {
		//创建doc的过程分为以下几步
		//1.新建存储位置
		Path path=Paths.get("index");
		Directory directory = FSDirectory.open(path);
		
		//2配置
		Analyzer analyzer = new StandardAnalyzer();
		IndexWriterConfig config = new IndexWriterConfig(analyzer);
		
		//3获取索引库
		IndexWriter iwriter = new IndexWriter(directory, config);
		 
		//新建doc
		Document doc = new Document();
		
		
		//		FieldType type= new FieldType();
		//				  type.setTokenized(true);
		//				  type.setStored(true);
		
		FieldType type=TextField.TYPE_STORED;
		//添加字段
		doc.add(new Field("nameDesc", "I am rob", type));
		List<Document> list=new ArrayList<>();
		//添加doc
		iwriter.addDocument(doc);
//		iwriter.addDocument(list);
		
		//关闭
		iwriter.close();
		
		
		
		
	}

}
