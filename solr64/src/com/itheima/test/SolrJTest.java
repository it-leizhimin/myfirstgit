package com.itheima.test;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/** 
* @author lijin E-mail: lijin1@itcast.cn 
* @date   2017年10月11日 上午11:59:56 
* 类说明 
*/
public class SolrJTest {

	/**
	 * 演示用Solrj操作solr服务器
	 * 添加/修改
	 * @throws IOException 
	 * @throws SolrServerException 
	 */
	@Test
	public void test() throws Exception {
		//1、创建HttpSolrServer连接：连接Solr服务器
		HttpSolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr/");
		//2、创建文档对象：SolrInputDocument
		SolrInputDocument doc = new SolrInputDocument();
		//3、文档中添加数据
		doc.addField("id", "c001");
		doc.addField("name", "瓜子");
		
		//4、执行添加到索引库
		solrServer.add(doc);
		//5、提交
		solrServer.commit();
	}

	/**
	 * 演示Solrj操作Solr服务器
	 * 删除
	 * @throws Exception
	 */
	@Test
	public void testDeleteById() throws Exception {
		//1、创建连接
		HttpSolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr/");
		//2、执行删除
		//根据ID删除
//		solrServer.deleteById("c001");
		//删除所有
		solrServer.deleteByQuery("*:*");
		//3、提交
		solrServer.commit();
	}
	
	/**
	 * 演示solrj操作solr服务器
	 * 查询
	 * @throws Exception
	 */
	@Test
	public void testQuery() throws Exception {
		//1、创建连接
		HttpSolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr/");
		//2、创建查询条件对象：封装条件
		SolrQuery query = new SolrQuery();
		query.setQuery("*:*");
		//3、执行查询，返回一个人响应
		QueryResponse queryResponse = solrServer.query(query);
		//4、根据这个响应获取文档集合
		SolrDocumentList documentList = queryResponse.getResults();
		System.err.println("====总记录数size====" + documentList.size());
		System.err.println("====总记录数NumFound====" + documentList.getNumFound());
		//5、遍历文档集合
		for (SolrDocument doc : documentList) {
			//6、打印文档数据
			System.err.println("====id=====" + doc.get("id"));
			System.err.println("====name=====" + doc.get("name"));
		}
	}
	
}
