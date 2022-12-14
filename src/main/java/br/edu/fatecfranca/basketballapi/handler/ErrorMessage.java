package br.edu.fatecfranca.basketballapi.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {

    private String message;
    private String field;

    public ErrorMessage(String message) {
        this.message = message;
    }

}
