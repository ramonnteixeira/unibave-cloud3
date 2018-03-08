package unibave.cloud3.pagamento;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/pagamentos")
public class PagamentoRestController {

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    ObjectMapper objectMapper;

    @Value("${app.percentual_desconto}")
    Double percentualDesconto;

    Random rand = new Random(300);

    /**
     * Busca funcionários e calcula o pagamento para os funcionarios ativos (sem demissao assinada)
     */
    @GetMapping
    public List<PagamentoDTO> calcularPagamentos() throws Exception {
        ServiceInstance instance = discoveryClient.getInstances("funcionario-service").stream().findAny().get();
        HttpGet getRequest = new HttpGet(instance.getUri() + "/funcionarios");
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = client.execute(getRequest);
        String content = EntityUtils.toString(response.getEntity(), "UTF-8");

        Map mapContent = objectMapper.readValue(content, HashMap.class);
        List<Map> maps = (List) PropertyUtils.getProperty(mapContent, "_embedded.funcionarios");

        List<FuncionarioDTO> funcionarios = maps.stream()
                .map(e -> FuncionarioDTO.builder()
                .id(Long.valueOf(Optional.ofNullable(e.get("id")).orElse("0").toString()))
                .cpf(Long.valueOf(Optional.ofNullable(e.get("cpf")).orElse("0").toString()))
                .nome(Optional.ofNullable(e.get("nome")).orElse("").toString())
                .salario(new BigDecimal(Optional.ofNullable(e.get("salario")).orElse("0.0").toString()))
                .admissao(LocalDate.parse(e.get("admissao").toString()))
                .demissao(e.get("demissao") == null ? null : LocalDate.parse(e.get("demissao").toString()))
                .email(Optional.ofNullable(e.get("email")).orElse("").toString())
                .build()
                ).collect(Collectors.toList());

        return funcionarios.stream()
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
