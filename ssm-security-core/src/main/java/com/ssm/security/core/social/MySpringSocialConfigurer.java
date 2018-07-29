package com.ssm.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @author 贾令强
 * @since 2018/7/26 22:12
 */
public class MySpringSocialConfigurer extends SpringSocialConfigurer {

    private String filterProcessUrl;

    public MySpringSocialConfigurer(String filterProcessUrl) {
        this.filterProcessUrl = filterProcessUrl;
    }

    /**
     * 为了
     *
     * @param object
     * @param <T>
     * @return
     */
    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter socialAuthenticationFilter = (SocialAuthenticationFilter) super.postProcess(object);
        socialAuthenticationFilter.setFilterProcessesUrl(filterProcessUrl);
        return (T) socialAuthenticationFilter;
    }
}
