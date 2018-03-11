package cn.e3mall.search.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.common.pojo.SearchResult;
import cn.e3mall.search.dao.SearchDao;
import cn.e3mall.search.service.SearchService;

/**
 * 
 * <p>Title: SearchServiceImpl</p>
 * <p>Description:商品搜索Service </p>
 * @version 1.0
 */
@Service
public class SearchServiceImpl implements SearchService {
	
	@Autowired
	private SearchDao searchDao;

	@Override
	public SearchResult search(String keyword, int page, int rows) throws Exception {
		//创建一个SolrQuery对象
		SolrQuery query = new SolrQuery();
		//设置查询条件
		query.setQuery(keyword);
		//设置分页条件
		if(page <= 0)
			page = 1;
		query.setStart((page - 1)*rows); //起始数
		query.setRows(rows);
		//设置默认搜索域
		query.set("df", "item_title");
		//开启高亮显示
		query.setHighlight(true);
		query.addHighlightField("item_title");
		query.setHighlightSimplePre("<em style=\"color:red\">");
		query.setHighlightSimplePost("</em>");
		//调用dao进行查询
			SearchResult result = searchDao.search(query);
			//获取总记录数
			long recordCount = result.getRecordCount();
			//计算总页数
			//总页数 = 总记录数 / 显示的条数
			int totalPage = (int) (recordCount / rows);
			if(recordCount % rows > 0) 
				totalPage++;
			//添加到返回结果
			result.setTotalPages(totalPage);
		
		//返回结果
		return result;
	}

}
