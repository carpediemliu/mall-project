package cn.e3mall.search.service;

import cn.e3mall.common.utils.E3Result;

/**
 * 导入商品数据至索引库 
 * <p>Title: SearchItemService</p>
 * <p>Description: </p>
 * @version 1.0
 */
public interface SearchItemService {
	E3Result importAllItems();//返回boolean
}
