package com.wyz.pms.common.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 *  @author: PUING
 *  @Date: 2020/11/29 22:18
 *  @Description: mybatis plus 配置类
 */
@Configuration
public class MybatisPlusConfig {

    /***
     * 分页插件
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 配置逻辑删除，3.1.1 以下版本需要配置
     * @Description:
     * @return
     */
//	@Bean
//	public ISqlInjector iSqlInjector() {
//		return new LogicSqlInjector();
//	}
}

