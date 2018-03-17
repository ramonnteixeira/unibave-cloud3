package unibave.cloud3.pagamento;

import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PROTECTED)
@Builder
class PagamentoDTO {

    private FuncionarioDTO funcionario;
    private BigDecimal salarioBruto;
    private BigDecimal acrescimos;
    private BigDecimal descontos;
    private BigDecimal salarioLiquido;

    PagamentoDTO salarioLiquido() {
        salarioLiquido = salarioBruto.add(acrescimos).subtract(descontos);
        return this;
    }
}
