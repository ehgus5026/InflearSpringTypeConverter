package com.example.SpringTypeConverter.formatter;

import com.example.SpringTypeConverter.converter.IpPortToStringConverter;
import com.example.SpringTypeConverter.converter.StringToIpPortConverter;
import com.example.SpringTypeConverter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.format.support.DefaultFormattingConversionService;

import static org.assertj.core.api.Assertions.*;

public class FormattingConversionServiceTest {

    @Test
    void formattingConversionService() {
        // DefaultFormattingConversionService은 Conversion, Formatter 모두 상속 받은 자식 클래스
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();

        //  컨버터 등록
        conversionService.addConverter(new StringToIpPortConverter());
        conversionService.addConverter(new IpPortToStringConverter());

        // 포멧터 등록
        conversionService.addFormatter(new MyNumberFormatter());

        // 컨버터 사용
        IpPort ipPort = conversionService.convert("127.0.0.1:8080", IpPort.class);
        assertThat(ipPort).isEqualTo(new IpPort("127.0.0.1", 8080));

        // 포멧터
        assertThat(conversionService.convert(1000, String.class)).isEqualTo("1,000");
        assertThat(conversionService.convert("1,000", Long.class)).isEqualTo(1000L);
    }

}
