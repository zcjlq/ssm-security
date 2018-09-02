package com.ssm.security.core.social.weixin.connect;

import com.ssm.security.core.social.weixin.api.Weixin;
import com.ssm.security.core.social.weixin.api.WeixinUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * @author 贾令强
 * @since 2018/7/29 17:44
 */
public class WeixinAdapt implements ApiAdapter<Weixin> {

    private String openId;

    public WeixinAdapt() {
    }

    public WeixinAdapt(String openId) {
        this.openId = openId;
    }

    @Override
    public boolean test(Weixin api) {
        return true;
    }

    @Override
    public void setConnectionValues(Weixin api, ConnectionValues values) {
        WeixinUserInfo userInfo = api.getUserInfo(openId);
        values.setDisplayName(userInfo.getNickname());
        values.setProviderUserId(userInfo.getOpenid());
        values.setImageUrl(userInfo.getHeadimgurl());
    }

    @Override
    public UserProfile fetchUserProfile(Weixin api) {
        return null;
    }

    @Override
    public void updateStatus(Weixin api, String message) {

    }
}
