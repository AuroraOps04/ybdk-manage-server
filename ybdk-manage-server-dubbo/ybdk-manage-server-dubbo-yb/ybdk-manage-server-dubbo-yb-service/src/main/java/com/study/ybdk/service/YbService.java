package com.study.ybdk.service;

import com.study.ybdk.entity.YibanUser;

import java.util.List;

public interface YbService {

    /**
     * 登陆易班并且存储登陆后的cookie
     * @param yibanUser 易班用户
     * @return 0: 登陆失败 1： 帐号密码错误
     */
    int login(YibanUser yibanUser) throws InterruptedException;

    /**
     * 更新易班用户的个人信息，注意这里的易班用户是已经存在的用户
     * @param yibanUser 易班用户
     * @return 1 更新成功 0 更新失败
     */
    int refreshUserInfo(YibanUser yibanUser);

    /**
     * 根据id获取易班用户
     * @param id 易班用户id
     * @return YibanUser
     */
    YibanUser getYibanUserById(Integer id);

    /**
     * 根据易班用户ids获取易班用户
     * @param ids 易班用户ids
     */
    List<YibanUser> listYibanUserByIds(List<Integer> ids);

}
