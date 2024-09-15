package cn.com.ljw.base.config;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusPropertiesCustomizer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Steph_Lin
 * @date 2024/9/11
 */
@EnableTransactionManagement
@Configuration
@ConditionalOnClass(MybatisPlusAutoConfiguration.class)
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
@AutoConfigureBefore(MybatisPlusAutoConfiguration.class)
@MapperScan("cn.com.ljw.**.mapper")
public class MybatisPlusConfig {

    @Bean
    public MybatisPlusPropertiesCustomizer plusPropertiesCustomizer() {
        return properties -> {
            properties.setTypeAliasesPackage("cn.com.ljw.**");
            final String[] old = properties.getMapperLocations();
            final String names = StrUtil.join(",", old);
            if (names.contains("ljw")) {
                return;
            }
            final String[] locations = ArrayUtil.addAll(old, new String[]{
                    "classpath*:/cn/com/ljw/**/*.xml"
            });
            properties.setMapperLocations(locations);
        };
    }



}
