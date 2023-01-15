package cn.itcast.feign.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * @Author JianXin
 * @Date 2023/1/5 15:04
 * @Github https://github.com/JackyST0
 */
public class DefaultFeignConfiguration {

    @Bean
    public Logger.Level logLevel(){
        return Logger.Level.BASIC;
    }
}
