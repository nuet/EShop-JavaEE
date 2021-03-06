package com.taotao.portal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.pojo.TbUser;
import com.taotao.portal.service.UserService;

/**
 * @author Charle Chung created on 2017年12月20日
 */
@Service
public class UserServiceImpl implements UserService {
	@Value("${SSO_BASE_URL}")
	public String SSO_BASE_URL;

	@Value("${SSO_USER_TOKEN}")
	public String SSO_USER_TOKEN;

	@Value("${SSO_PAGE_LOGIN}")
	public String SSO_PAGE_LOGIN;

	@Override
	public TbUser getUserByToken(String token) {
		try {
			String json = HttpClientUtil.doGet(SSO_BASE_URL + SSO_USER_TOKEN + token);
			TaotaoResult result = TaotaoResult.formatToPojo(json, TbUser.class);
			if (result.getStatus() == 200) {
				TbUser tbUser = (TbUser) result.getData();
				return tbUser;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
