package org.tommap.accounts.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "accounts")
@RefreshScope //Enables runtime config refresh - Spring can update the mutable properties when /actuator/refresh is called
@Getter @Setter @ToString
public class AccountsContactInfoDto {
    private String message;
    private Map<String, String> contactDetails;
    private List<String> onCallSupport;
}