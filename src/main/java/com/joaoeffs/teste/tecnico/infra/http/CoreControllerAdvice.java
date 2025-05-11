package com.joaoeffs.teste.tecnico.infra.http;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.joaoeffs.teste.tecnico.infra.exception.ItemPedidoIdNotFoundException;
import com.joaoeffs.teste.tecnico.infra.exception.NumeroPedidoException;
import com.joaoeffs.teste.tecnico.infra.exception.situacao.PedidoCanceladoException;
import com.joaoeffs.teste.tecnico.infra.exception.situacao.PedidoFechadoException;
import com.joaoeffs.teste.tecnico.infra.exception.PedidoIdNotFoundException;
import com.joaoeffs.teste.tecnico.infra.exception.ProdutoServicoIdNotFoundException;
import com.joaoeffs.teste.tecnico.infra.exception.ProdutoServicoInativoException;
import com.joaoeffs.teste.tecnico.infra.exception.ProdutoServicoVinculadoPedidoException;

import io.swagger.v3.oas.annotations.Hidden;

@Hidden
@RestControllerAdvice
public class CoreControllerAdvice {

    @ExceptionHandler({
        ProdutoServicoIdNotFoundException.class,
        PedidoIdNotFoundException.class,
        ItemPedidoIdNotFoundException.class
    })
    public ResponseEntity<ApiErrorResponse> handleNotFound(Exception ex) {
        var response = new ApiErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler({
        ProdutoServicoVinculadoPedidoException.class,
        ProdutoServicoInativoException.class,
        PedidoFechadoException.class,
        PedidoCanceladoException.class,
        NumeroPedidoException.class
    })
    public ResponseEntity<ApiErrorResponse> handleBadRequest(RuntimeException ex) {
        var response = new ApiErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleBeanValidation(MethodArgumentNotValidException ex) {
        StringBuilder mensagens = new StringBuilder();

        for (FieldError error : ex.getBindingResult().getFieldErrors())
            mensagens.append(error.getDefaultMessage());

        var response = new ApiErrorResponse(HttpStatus.BAD_REQUEST, mensagens.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}
