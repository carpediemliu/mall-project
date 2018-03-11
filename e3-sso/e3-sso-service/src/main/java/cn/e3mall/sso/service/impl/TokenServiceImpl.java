package cn.e3mall.sso.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import cn.e3mall.common.jedis.JedisClient;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.common.utils.JsonUtils;
import cn.e3mall.pojo.TbUser;
import cn.e3mall.sso.service.TokenService;

/**
 * 根据token取用户信息
 * <p>
 * Title: TokenServiceImpl
 * </p>
 */
public class TokenServiceImpl implements TokenService {

	@Autowired
	private JedisClient jedisClient;

	@Value("${SESSION_EXPIRE}")
	private Integer SESSION_EXPIRE;

	@Override
	public E3Result getUserByToken(String token) {
		//根据token到redis中取出用户信息
		String json = jedisClient.get("SESSION:"+token);
		if(StringUtils.isBlank(json)){
			//取不到，登陆已经过期，返回登陆过期
			return E3Result.build(201, "用户登录已经过期");
		}
		//取到用户信息，更新token的过期时间
		TbUser user = JsonUtils.jsonToPojo(json,TbUser.class);
		jedisClient.expire("SESSION:"+token, SESSION_EXPIRE);
		//返回结果，E3Result包含TbUser对象
		return E3Result.ok(user);
	}

}
