package com.colsubsidio.microservicebankapi.components.configuration;

import lombok.Generated;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/** Configuracion base del restTemplate
 * @author Javier Vanegas
 * @author Banco Cuscatlan
 * @version 1.0
 * @since 25/04/2022
*/
@Configuration
@Generated
public class RestTemplateConfig {

    /** 
     * @return RestTemplate
     */
    @Bean
    @Generated
    public RestTemplate restTemplate(){

        return new RestTemplate(getClientHttpRequestFactory());
    }

    
    /** 
     * @return SimpleClientHttpRequestFactory
     */
    private SimpleClientHttpRequestFactory getClientHttpRequestFactory() 
    {
        SimpleClientHttpRequestFactory clientHttpRequestFactory
                        = new SimpleClientHttpRequestFactory();
        //Connect timeout
        clientHttpRequestFactory.setConnectTimeout(15000);
        
        //Read timeout
        clientHttpRequestFactory.setReadTimeout(15000);
        return clientHttpRequestFactory;
    }
}