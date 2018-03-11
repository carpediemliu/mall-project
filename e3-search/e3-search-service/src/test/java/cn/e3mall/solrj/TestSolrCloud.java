package cn.e3mall.solrj;


import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * 
 * <p>Title: TestSolrCloud</p>
 * <p>Description:搜索集群测试 </p>
 * @version 1.0
 */
public class TestSolrCloud {
	
	@Test
	public void testAddDocument() throws Exception {
		//创建CloudSolrServer对象,连接Solr集群
		//zkHost:zookeeper的地址列表，包括端口号
		CloudSolrServer solrServer = new CloudSolrServer("192.168.25.128:2182,192.168.25.128:2183,192.168.25.128:2184");
		//设置一个defaultCollection属性
		solrServer.setDefaultCollection("collection2");
		//创建一个文档对象SolrInputDocument
		SolrInputDocument solrInputDocument = new SolrInputDocument();
		//向文档对象中添加域。文档中必须包含一个id域，所有的域的名称必须在schema.xml中定义
		solrInputDocument.addField("id", "solrCloud01");
		solrInputDocument.addField("item_title", "测试商品01");
		solrInputDocument.addField("item_price",123);
		//更新即为增加，id一致即可
		//把文档写入索引库
		solrServer.add(solrInputDocument);
		//提交
		solrServer.commit();
	}

}
