package br.com.qualify.ged.domain;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data

public class DocumentoRequest {

    @NotNull
    private String nome;
    @NotNull
    private String titulo;

}
