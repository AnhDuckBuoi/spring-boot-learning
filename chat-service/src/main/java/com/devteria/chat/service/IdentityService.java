package com.devteria.chat.service;

import com.devteria.chat.dto.ApiResponse;
import com.devteria.chat.dto.request.IntrospectRequest;
import com.devteria.chat.dto.response.IntrospectResponse;
import com.devteria.chat.repository.httpclient.IdentityClient;
import feign.FeignException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IdentityService {
    IdentityClient identityClient;
    public IntrospectResponse introspect(IntrospectRequest request){
        try{
            var res = identityClient.introspect(request);
            if(Objects.isNull(res)) return IntrospectResponse.builder()
                    .valid(false)
                    .build();
            return res.getResult();
        }
        catch (FeignException e){
            log.info("Introspect failed");
            return IntrospectResponse.builder()
                    .valid(false)
                    .build();
        }
    }
}
