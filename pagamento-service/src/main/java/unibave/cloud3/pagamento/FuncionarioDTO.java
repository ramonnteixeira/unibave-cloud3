package unibave.cloud3.pagamento;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AccessLevel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
class FuncionarioDTO {

    private Long id;
    private String nome;
    private LocalDate admissao;
    private Long cpf;
    private String email;
    private BigDecimal salario;
    private LocalDate demissao;

}
