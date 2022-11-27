package br.edu.fatecfranca.basketballapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SelectResponse {

    private String label;
    private Object value;

    public static SelectResponse of(String label, Object value) {
        return SelectResponse
                .builder()
                .label(label)
                .value(value)
                .build();
    }

}
