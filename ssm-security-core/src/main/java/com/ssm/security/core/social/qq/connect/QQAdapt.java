package com.ssm.security.core.social.qq.connect;

import com.ssm.security.core.social.qq.api.connect.QQ;
import com.ssm.security.core.social.qq.api.connect.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * @author 贾令强
 * @since 2018/7/22 16:34
 */
public class QQAdapt implements ApiAdapter<QQ> {
    /**
     * 测试是否连通
     *
     * @param api
     * @return
     */
    @Override
    public boolean test(QQ api) {
        return true;
    }

    /**
     * 在api和connection中作适配
     *
     * @param api
     * @param values
     */
    @Override
    public void setConnectionValues(QQ api, ConnectionValues values) {
        QQUserInfo userInfo = api.getUserInfo();
        values.setDisplayName(userInfo.getNickname());
        values.setImageUrl(userInfo.getFigureurl_qq_1());
        values.setProfileUrl(null);
        values.setProviderUserId(userInfo.getOpenId());
    }

    /**
     * 获取用户个人信息
     * 绑定解绑时再写
     *
     * @param api
     * @return
     */
    @Override
    public UserProfile fetchUserProfile(QQ api) {
        return null;
    }

    @Override
    public void updateStatus(QQ api, String message) {
        // 类似微博发一条消息，qq不提供此功能
    }
}
