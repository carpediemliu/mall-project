package cn.e3mall.solrj;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class TestSolrJ {

	@Test
	public void addDocument() throws Exception {
		//创建SolrServer对象，创建一个连接，参数为Solr服务的url 默认collection1
		SolrServer solrServer = new HttpSolrServer("http://192.168.25.128:8080/solr/collection1");
		//创建一个文档对象SolrInputDocument
		SolrInputDocument solrInputDocument = new SolrInputDocument();
		//向文档对象中添加域。文档中必须包含一个id域，所有的域的名称必须在schema.xml中定义
		//更新即为增加，id一致即可
		solrInputDocument.addField("id", "doc01");
		solrInputDocument.addField("item_title", "测试商品01");
		solrInputDocument.addField("item_price", 1000);
		//把文档写入索引库
		solrServer.add(solrInputDocument);
		//提交
		solrServer.commit();
	}
	
	@Test
	public void testDelDocument() throws Exception {
		SolrServer solrServer = new HttpSolrServer("http://192.168.25.128:8080/solr/collection1");
		//删除文档
		solrServer.deleteById("doc01");//按id删除
		//solrServer.deleteByQuery("id:doc01"); 按查询条件删除
		//提交
		solrServer.commit();
	}
	
	//复杂条件搜索
	@Test
	public void testQueryIndex() throws Exception {
		//创建一个SolrServer对象
		SolrServer solrServer = new HttpSolrServer("http://192.168.25.128:8080/solr/collection1");
		//创建一个查询对象
		SolrQuery query = new SolrQuery();
		//设置查询条件
		query.setQuery("手机");
		//从0条开始取
		query.setStart(0);
		//取20条
		query.setRows(20);
		query.set("df", "item_title");
		query.setHighlight(true);
		query.addHighlightField("item_title");
		query.setHighlightSimplePre("<em>");
		query.setHighlightSimplePost("</em>");
		//执行查询
		QueryResponse response = solrServer.query(query);
		//取文档列表，获取总记录数
		SolrDocumentList documentList = response.getResults();
		//查询总记录数
		System.out.println("查询到的总记录数："+documentList.getNumFound());
		for (SolrDocument solrDocument : documentList) {
			Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
			List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
			String title="";
			//判断，防止是keywords查询方式，title可能为空的情况。
			if(list != null && list.size() >0){
				title = list.get(0);
			}else{
			title= (String) solrDocument.get("item_title");
			}
			System.out.println(title);
			System.out.println(solrDocument.get("item_sell_point"));
			System.out.println(solrDocument.get("item_price"));
			System.out.println(solrDocument.get("item_image"));
			System.out.println(solrDocument.get("item_category_name"));
		}
	}
}
