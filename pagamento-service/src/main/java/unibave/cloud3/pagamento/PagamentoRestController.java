package unibave.cloud3.pagamento;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pagamentos")
class PagamentoRestController {

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    ObjectMapper objectMapper;

    @Value("${app.percentual_desconto}")
    Double percentualDesconto;

    Random rand = new Random(300);
    
    @Autowired
    FuncionarioClient funcionarioClient;

    /**
     * Busca funcionários e calcula o pagamento para os funcionarios ativos (sem
     * demissao assinada)
     */
    @GetMapping
    List<PagamentoDTO> calcularPagamentos() throws Exception {
        return funcionarioClient.getFuncionarios()
                .stream()
                .filter(e -> e.getDemissao() == null)
                .map(
                        e -> PagamentoDTO.builder()
                                .funcionario(e)
                                .salarioBruto(e.getSalario())
                                .acrescimos(BigDecimal.valueOf(rand.nextDouble()))
                                .descontos(e.getSalario().multiply(BigDecimal.valueOf(percentualDesconto)))
                                .build()
                                .salarioLiquido()
                )
                .collect(Collectors.toList());
    }

}
