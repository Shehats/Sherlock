package com.sherlock.Mycroft.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

@Configuration
public class TwitterCrawlerConfig {
    @Value("${spring.social.twitter.appId}")
    private String appId;

    @Value("${spring.social.twitter.appSecret}")
    private String appSecret;

    @Value("${spring.social.twitter.accessToken}")
    private String accessToken;

    @Value("${spring.social.twitter.accessTokenSecret}")
    private String accessTokenSecret;

    @Bean
    Twitter twitter() {
        return new TwitterTemplate(appId, appSecret, accessToken, accessTokenSecret);
    }
}
