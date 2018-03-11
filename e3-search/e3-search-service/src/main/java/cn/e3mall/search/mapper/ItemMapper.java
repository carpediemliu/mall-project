package cn.e3mall.search.mapper;

import java.util.List;

import cn.e3mall.common.pojo.SearchItem;

public interface ItemMapper {

	//获取数据库的POJO，存放至索引库
	List<SearchItem>  getItemList();
	//根据id查询数据库，同步索引库用
	SearchItem getItemById(long itemId);
	
}
