package cn.e3mall.search.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.e3mall.common.pojo.SearchItem;
import cn.e3mall.common.pojo.SearchResult;

/**
 * 
 * <p>
 * Title: SearchDao
 * </p>
 * <p>
 * Description:商品搜索Dao
 * </p>
 * 
 * @version 1.0
 */
@Repository
public class SearchDao {

	@Autowired
	private SolrServer solrServer;

	// 根据查询条件查询索引
	public SearchResult search(SolrQuery query) throws SolrServerException {
		// 根据query查询结果
		QueryResponse response = solrServer.query(query);
		// 取查询结果
		SolrDocumentList solrDocumentList = response.getResults();
		// 取查询结果总记录数
		long numFound = solrDocumentList.getNumFound();
		// 取商品列表，高亮显示
		Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
		List<SearchItem> itemList = new ArrayList<>();
		System.out.println(solrServer);
		for (SolrDocument solrDocument : solrDocumentList) {
			// 搜索结果对象
			SearchItem item = new SearchItem();
			item.setId((String) solrDocument.get("id"));
			item.setCategory_name((String) solrDocument.get("item_category_name"));
			item.setImage((String) solrDocument.get("item_image"));
			item.setPrice((long) solrDocument.get("item_price"));
			item.setSell_point((String) solrDocument.get("item_sell_point"));
			// 取高亮显示
			List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
			System.out.println(list);
			String title = "";
			if (list != null && list.size() > 0) {
				title = list.get(0);
			} else {
				title = (String) solrDocument.get("item_title");
			}
			item.setTitle(title);
			// 添加到商品列表
			itemList.add(item);
		}
		// 将以上结果放入POJO对象
		SearchResult result = new SearchResult();
		result.setRecordCount(numFound);
		result.setItemList(itemList);
		// 返回结果
		return result;
	}
}
