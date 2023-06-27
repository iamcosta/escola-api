package app.netlify.iambarroso.escolaapi.rest.controller.advice;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ApiErrorResponse {
    private List<String> erros;

    public ApiErrorResponse(List<String> erros) {
        this.erros = erros;
    }

    public ApiErrorResponse(String erro) {
        this.erros = List.of(erro);
    }
}
