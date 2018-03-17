package unibave.cloud3.pagamento;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
class FuncionarioClientFallback implements FuncionarioClient {

    @Override
    public List<FuncionarioDTO> getFuncionarios() {
        return new ArrayList<>();
    }
    
}
