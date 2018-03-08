package unibave.cloud3.funcionario;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AccessLevel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
class Funcionario {

    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private LocalDate admissao;
    private Long cpf;
    private String email;
    private BigDecimal salario;
    private LocalDate demissao;

}
