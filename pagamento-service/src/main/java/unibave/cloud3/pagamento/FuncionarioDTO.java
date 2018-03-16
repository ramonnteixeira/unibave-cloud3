package unibave.cloud3.pagamento;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class FuncionarioDTO {

    private Long id;
    private String nome;
    private LocalDate admissao;
    private Long cpf;
    private String email;
    private BigDecimal salario;
    private LocalDate demissao;

}
