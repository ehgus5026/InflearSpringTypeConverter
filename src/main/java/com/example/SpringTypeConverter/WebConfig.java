package com.example.SpringTypeConverter;

import com.example.SpringTypeConverter.converter.IntegerToStringConverter;
import com.example.SpringTypeConverter.converter.IpPortToStringConverter;
import com.example.SpringTypeConverter.converter.StringToIntegerConverter;
import com.example.SpringTypeConverter.converter.StringToIpPortConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer { // SpringWebMvc에 뭔가를 등록할 때

    // 스프링 내부에서 사용하는 ConversionService에 Converter를 추가
    // 스프링 내부에서는 수많은 Converter를 제공하는데 이렇게 수동으로 설정하면 이게 적용이 됨.
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToIntegerConverter());
        registry.addConverter(new IntegerToStringConverter());
        registry.addConverter(new IpPortToStringConverter());
        registry.addConverter(new StringToIpPortConverter());
    }

}
