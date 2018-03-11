package cn.e3mall.search.service;


import cn.e3mall.common.pojo.SearchResult;

public interface SearchService {
	//查询条件和分页条件，每页显示的记录数（该搜索服务用于适配手机和网页）
	SearchResult search(String keyword,int page,int rows) throws Exception;
}
