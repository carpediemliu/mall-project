package cn.e3mall.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * <p>Title: SearchResult</p>
 * <p>Description:搜索结果：包含总记录数，总页数，搜索条件的结果 </p>
 * @version 1.0
 */
public class SearchResult implements Serializable {

	private long recordCount;
	private int totalPages;
	private List<SearchItem> itemList;
	public long getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(long recordCount) {
		this.recordCount = recordCount;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public List<SearchItem> getItemList() {
		return itemList;
	}
	public void setItemList(List<SearchItem> itemList) {
		this.itemList = itemList;
	}
}
