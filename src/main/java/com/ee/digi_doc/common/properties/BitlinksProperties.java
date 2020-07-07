package com.ee.digi_doc.common.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "bitlinks")
public class BitlinksProperties {

    private String hostName;
    private String url;
    private String accessToken;

}
