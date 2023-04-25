package groupe12.EGA.repo;


import groupe12.EGA.model.Retrait;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RetraitRepo extends JpaRepository<Retrait, String> {
    List<Retrait> findByNumCompte(String numCompte);

}

