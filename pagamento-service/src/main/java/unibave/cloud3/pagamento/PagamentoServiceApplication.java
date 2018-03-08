package unibave.cloud3.pagamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class PagamentoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PagamentoServiceApplication.class, args);
    }
}
