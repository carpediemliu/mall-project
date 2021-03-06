package cn.e3mall.sso.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.e3mall.common.utils.E3Result;
import cn.e3mall.mapper.TbUserMapper;
import cn.e3mall.pojo.TbUser;
import cn.e3mall.pojo.TbUserExample;
import cn.e3mall.pojo.TbUserExample.Criteria;
import cn.e3mall.sso.service.RegisterService;

/**
 * 用户注册处理Service
 * <p>
 * Title: RegisterServiceImpl
 * </p>
 */

@Service
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	private TbUserMapper tbUserMapper;

	public E3Result checkData(String param, int type) {
		// 根据不同的type生成不同的查询条件
		TbUserExample tbUserExample = new TbUserExample();
		Criteria criteria = tbUserExample.createCriteria();
		// 1-用户名 2-手机号 3-邮箱
		if (type == 1) {
			criteria.andUsernameEqualTo(param);
		} else if (type == 2) {
			criteria.andPhoneEqualTo(param);
		} else if (type == 3) {
			criteria.andEmailEqualTo(param);
		} else {
			E3Result.build(400, "数据类型错误");
		}
		// 执行查询
		List<TbUser> list = tbUserMapper.selectByExample(tbUserExample);
		// 判断结果中是否包含数据
		if (list != null && list.size() > 0) {
			// 如果有数据，返回false
			return E3Result.ok(false);
		}
		return E3Result.ok(true);
	}

	@Override
	public E3Result register(TbUser user) {
		// 先进性数据有效性的校验
		// 用户名，密码，手机
		if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword()) || StringUtils.isBlank(user.getPhone())) {
			return E3Result.build(400, "用户数据不完整，注册失败");
		}
		//是否重复
		E3Result result = checkData(user.getUsername(), 1);
		if(!(boolean) result.getData()){
			return E3Result.build(400, "此用户已被占用，注册失败");
		}
		result = checkData(user.getPhone(), 2);
		if(!(boolean) result.getData()){
			return E3Result.build(400, "手机号已被占用，注册失败");
		}
		// 补全POJO的属性(date)
		user.setCreated(new Date());
		user.setUpdated(new Date());
		// password进行md5加密
		String md5Pass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		user.setPassword(md5Pass);
		// 将数据插入数据库中
		tbUserMapper.insert(user);
		// 返回添加成功
		return E3Result.ok();
	}
}
