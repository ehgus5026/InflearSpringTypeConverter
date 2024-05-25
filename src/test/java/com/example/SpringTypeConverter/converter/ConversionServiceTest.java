package com.example.SpringTypeConverter.converter;

import com.example.SpringTypeConverter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.support.DefaultConversionService;

import static org.assertj.core.api.Assertions.*;

public class ConversionServiceTest {

    @Test
    void conversionService() {
        // 등록(Converter를 ConversionService에 직접 추가할 수 있음)
        // ConversionService -> 컨버터 사용에 초점, ConversionResistry -> 컨버터 등록에 초점 (인터페이스 분리 원칙(ISP) : 클라이언트가 자신이 사용하지 않는 메서드에 의존하지 말아야 한다.)
        DefaultConversionService conversionService = new DefaultConversionService();
        conversionService.addConverter(new StringToIntegerConverter());
        conversionService.addConverter(new IntegerToStringConverter());
        conversionService.addConverter(new StringToIpPortConverter());
        conversionService.addConverter(new IpPortToStringConverter());

        // 사용
        assertThat(conversionService.convert("10", Integer.class)).isEqualTo(10);
        assertThat(conversionService.convert(10, String.class)).isEqualTo("10");

        IpPort result = conversionService.convert("127.0.0.1:8080", IpPort.class);
        assertThat(result).isEqualTo(new IpPort("127.0.0.1", 8080));

        String ipPortString = conversionService.convert(new IpPort("127.0.0.1", 8080), String.class);
        assertThat(ipPortString).isEqualTo("127.0.0.1:8080");

        /*Integer result = conversionService.convert("10", Integer.class);
        assertThat(result).isEqualTo(10);*/
    }

}
