package cn.e3mall.sso.service;

import cn.e3mall.common.utils.E3Result;

public interface LoginService {

	//用户登录Service，参数：用户名，密码
	/*业务逻辑：
	 * 1.用户名密码校验 
	 * 2.生成token
	 * 3.把用户信息写入redis，key为token，value为用户信息
	 * 4.设置session的过期时间
	 * 5.返回token,由web层通过response写入cookie 				
	 */
	//返回E3Result，并将token包含在其中
	E3Result userLogin(String username,String password);
}
