package cn.e3mall.sso.service;

import cn.e3mall.common.utils.E3Result;
import cn.e3mall.pojo.TbUser;

public interface RegisterService {
	
	//用户注册校验
	E3Result checkData(String param,int type);
	//用户注册
	E3Result register(TbUser user);
}
