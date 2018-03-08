package unibave.cloud3.funcionario;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class FuncionarioServiceApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(FuncionarioServiceApplication.class, args);
    }

    @Autowired
    FuncionarioRepository repository;

    @Override
    public void run(String... args) throws Exception {
        repository.save(new Funcionario(null, "Zeca", LocalDate.of(2010, Month.OCTOBER, 31), Long.getLong("06786556932"), "zecabruxo@gmail.com", BigDecimal.valueOf(1987.00), null));
        repository.save(new Funcionario(null, "Juca", LocalDate.of(2012, Month.APRIL, 1), Long.getLong("07954266522"), "jukinha_taubate@hotmail.com", BigDecimal.valueOf(1632.00), null));
        repository.save(new Funcionario(null, "Joana", LocalDate.of(2013, Month.JANUARY, 23), Long.getLong("12344312947"), "joana.fulana@gmail.com", BigDecimal.valueOf(1425.00), null));
        repository.save(new Funcionario(null, "Jurema", LocalDate.of(2008, Month.MAY, 7), Long.getLong("00952446955"), "jureminha@bol.com.br", BigDecimal.valueOf(1840.00), LocalDate.of(2012, Month.MARCH, 26)));
        repository.save(new Funcionario(null, "Cleonice", LocalDate.of(2003, Month.FEBRUARY, 14), Long.getLong("00746585242"), "cleonice@ibest.com.br", BigDecimal.valueOf(2450.27), null));
    }
}
