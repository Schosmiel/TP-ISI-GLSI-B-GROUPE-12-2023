package groupe12.EGA.repo;


import groupe12.EGA.model.Virement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VirementRepo extends JpaRepository<Virement, String> {
    List<Virement> findByNumeroCompteSource(String NumeroCompteSource);
    List<Virement> findByNumeroCompteDest(String numeroCompteDest);
}

