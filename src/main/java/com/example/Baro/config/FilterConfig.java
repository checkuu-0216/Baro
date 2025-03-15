package com.example.Baro.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FilterConfig {

    private final JwtUtil jwtUtil;

    // Filter 등록
    @Bean
    public FilterRegistrationBean<JwtSecurityFilter> jwtFilter() {
        FilterRegistrationBean<JwtSecurityFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new JwtSecurityFilter(jwtUtil));
        registrationBean.addUrlPatterns("/*");

        return registrationBean;
    }
}
