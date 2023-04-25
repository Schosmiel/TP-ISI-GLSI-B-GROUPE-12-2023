package groupe12.EGA.repo;


import groupe12.EGA.model.Depot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepoRepo extends JpaRepository<Depot, String> {
    List<Depot> findByNumCompte(String numCompte);
}

