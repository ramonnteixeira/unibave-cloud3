package unibave.cloud3.funcionario;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/funcionarios")
@RestController
public class FuncionarioController {
    
    @Autowired
    FuncionarioRepository repository;
    
    @GetMapping
    public List<Funcionario> listar() {
        return repository.findAll();
    }
}
