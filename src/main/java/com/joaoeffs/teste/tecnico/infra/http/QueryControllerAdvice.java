package com.joaoeffs.teste.tecnico.infra.http;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Slice;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import io.swagger.v3.oas.annotations.Hidden;

@Hidden
@ControllerAdvice
public class QueryControllerAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return Slice.class.isAssignableFrom(returnType.getParameterType());
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
        Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        if (body instanceof Slice<?> slice) {
            Map<String, Object> customBody = new HashMap<>();
            customBody.put("totalElements", slice.getNumberOfElements());
            customBody.put("items", slice.getContent());
            customBody.put("hasNext", slice.hasNext());
            return customBody;
        }

        return body;
    }
}
