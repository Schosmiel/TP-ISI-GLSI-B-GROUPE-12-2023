package groupe12.EGA.repo;



import groupe12.EGA.model.Client;
import groupe12.EGA.model.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompteRepo extends JpaRepository<Compte, String> {


    List<Compte> findByProprietaire(Client proprietaire);

}

