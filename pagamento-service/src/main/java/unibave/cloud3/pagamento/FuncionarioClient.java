package unibave.cloud3.pagamento;

import java.util.List;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "funcionario-service", 
        fallback = FuncionarioClientFallback.class,
        configuration = FeignConfiguration.class)
interface FuncionarioClient {

    @RequestMapping(value = "/funcionarios", method = RequestMethod.GET)
    List<FuncionarioDTO> getFuncionarios();

}
