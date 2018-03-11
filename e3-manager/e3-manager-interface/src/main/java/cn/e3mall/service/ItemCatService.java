package cn.e3mall.service;

import java.util.List;

import cn.e3mall.common.pojo.EasyUITreeNode;

/**
 * EasyUITreeNode
 * <p>Title: ItemCatService</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p> 
 * @version 1.0
 */
public interface ItemCatService {

	List<EasyUITreeNode> getItemCatList(long parentId);
}
