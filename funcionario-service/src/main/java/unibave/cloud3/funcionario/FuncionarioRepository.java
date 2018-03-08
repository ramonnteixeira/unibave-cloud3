package unibave.cloud3.funcionario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "/funcionarios", collectionResourceRel = "funcionarios")
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
	
}