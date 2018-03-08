package unibave.cloud3.pagamento;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.util.Random;
import org.springframework.beans.factory.annotation.Value;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoRestController {

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    ObjectMapper objectMapper;

    @Value("app.percentual_desconto")
    Double percentualDesconto;

    Random rand = new Random(300);

    /**
     * Busca funcionários e calcula o pagamento para os funcionarios ativos (sem demissao assinada)
     */
    @GetMapping
    @SuppressWarnings("all")
    public List<PagamentoDTO> calcularPagamentos() throws Exception {
        ServiceInstance instance = discoveryClient.getInstances("funcionario-service").stream().findAny().get();
        HttpGet getRequest = new HttpGet(instance.getUri() + "/funcionarios");
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = client.execute(getRequest);

        String content = EntityUtils.toString(response.getEntity(), "UTF-8");

        List<FuncionarioDTO> funcionarios = objectMapper.readValue(content, objectMapper.getTypeFactory().constructCollectionType(List.class, FuncionarioDTO.class));

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
