package com.lokesh.messaging.functions;

import com.lokesh.messaging.dto.AccountsMsgDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class MessageFunctions {

    private static final Logger logger = LoggerFactory.getLogger(MessageFunctions.class);

    /*
    * Function exposed as Rest API Post endpoint http://localhost:9010/email by spring-cloud-starter-function-web Dependency
    */
    @Bean
    public Function<AccountsMsgDto, AccountsMsgDto> email() {
        Function<AccountsMsgDto, AccountsMsgDto> emailLambda = accountsMsgDto -> {
            logger.info("Sending email to {} from Messaging Micro Service. Lambda Input: {}, Output: {}", accountsMsgDto.email(), accountsMsgDto, accountsMsgDto);
            return accountsMsgDto;
        };
        return emailLambda;
    }

    /*
     * Function exposed as Rest API Post endpoint http://localhost:9010/sms by spring-cloud-starter-function-web Dependency
     */
    @Bean
    public Function<AccountsMsgDto, Long> sms() {
        Function<AccountsMsgDto, Long> smsLambda = accountsMsgDto -> {
            logger.info("Sending sms to {} from Messaging Micro Service. Lambda Input: {}, Output: {}", accountsMsgDto.mobileNumber(), accountsMsgDto, accountsMsgDto.accountNumber());
            return accountsMsgDto.accountNumber();
        };
        return smsLambda;
    }

}
