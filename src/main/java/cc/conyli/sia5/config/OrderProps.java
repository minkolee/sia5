package cc.conyli.sia5.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Component
@ConfigurationProperties(prefix = "taco.order")
@Data
public class OrderProps {

    @Max(value = 3, message = "must between 1 and 3")
    @Min(value = 1, message = "must between 1 and 3")
    private int pageSize = 3;

    private int page = 0;
}
