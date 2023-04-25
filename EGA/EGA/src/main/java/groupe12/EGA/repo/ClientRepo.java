package groupe12.EGA.repo;


import groupe12.EGA.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepo extends JpaRepository<Client, Long> {
    Client findByCourriel(String courriel);
    Client findByNumTel(String numTel);

}

