package br.com.qualify.ged.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;


@Data
@Entity(name = "tbl_documento")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_documento")
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private String titulo;

    private String anexo;

    @Transient
    private String urlAnexo;


}
