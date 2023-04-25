package groupe12.EGA.controller;

import groupe12.EGA.model.Client;
import groupe12.EGA.model.Compte;
import groupe12.EGA.repo.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/client")
public class ClientController
{
    @Autowired
    private ClientRepo clientRepo;
    @GetMapping("/liste")
    public List<Client> obtenirClients(){
        return clientRepo.findAll();
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Object> getClientById(@PathVariable long id) {
        Optional<Client> client = clientRepo.findById(id);
        if (client.isPresent()) {
            return ResponseEntity.ok(client.get());
        } else {
            String message = "Client " + id + " n'existe pas";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }
    @PostMapping("/enregister")
    public ResponseEntity<?> enregistrerClient(@RequestBody Client client){
        // Vérification si le courriel existe déjà
        Client clientByEmail = clientRepo.findByCourriel(client.getCourriel());
        if (clientByEmail != null) {
            return ResponseEntity.badRequest().body("Le courriel existe déjà.");
        }

        // Vérification si le numéro de téléphone existe déjà
       // Client clientByNumTel = clientRepo.findByNumTel(client.getNumTel());
        //if (clientByNumTel != null) {
          //  return ResponseEntity.badRequest().body("Le numéro de téléphone déjà associé à un Client.");
       // }

        // Enregistrement du client
        try {
            clientRepo.save(client);
            String message = "Client "+client.getNom()+" "+client.getPrenom()+" enregistré ...";
            return ResponseEntity.ok(message);
        } catch (Exception ex) {
            String message = "Une erreur s'est produite lors de l'enregistrement du client " + client.getNom() + " " +
                    client.getPrenom() + ". " + ex.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }
    @PutMapping(value ="/modifier/{id}")
    public ResponseEntity<?> modifierClient(@PathVariable long id, @RequestBody Client client){
        Optional<Client> optionalClient = clientRepo.findById(id);
        if (optionalClient.isPresent()) {
            Client clientModifie = optionalClient.get();

            // Vérification de l'unicité de l'email et du numéro de téléphone
            if (clientRepo.findByCourriel(client.getCourriel()) != null && !clientRepo.findByCourriel(client.getCourriel()).equals(clientModifie)) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Le courriel " + client.getCourriel() + " est déjà associé à un autre client");
            }
            if (clientRepo.findByNumTel(client.getNumTel()) != null && !clientRepo.findByNumTel(client.getNumTel()).equals(clientModifie)) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Le numéro de téléphone " + client.getNumTel() + " est déjà associé à un autre client");
            }
            clientModifie.setNom(client.getNom());
            clientModifie.setPrenom(client.getPrenom());
            clientModifie.setAdresse(client.getAdresse());
            clientModifie.setSexe(client.getSexe());
            clientModifie.setDateNaissance(client.getDateNaissance());
            clientModifie.setNumTel(client.getNumTel());
            clientModifie.setCourriel(client.getCourriel());
            clientModifie.setNationalite(client.getNationalite());
            clientRepo.save(clientModifie);

            return ResponseEntity.ok("Modifiée ...");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Le client avec l'ID " + id + " n'existe pas");
        }
    }
    @DeleteMapping(value ="/supprimer/{id}")
    public ResponseEntity<Object> supprimerClient(@PathVariable long id) {
        Optional<Client> client = clientRepo.findById(id);
        if (client.isPresent()) {
            clientRepo.delete(client.get());
            String message = "Client supprimé id : " + id;
            return ResponseEntity.ok(message);
        } else {
            String message = "Impossible de supprimer le client. Le client avec l'identifiant " + id + " n'existe pas.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }
    @GetMapping("/{clientId}/comptes")
    public ResponseEntity<Object> getComptesByClientId(@PathVariable Long clientId) {
        Optional<Client> optionalClient = clientRepo.findById(clientId);
        if (optionalClient.isPresent()) {
            List<Compte> comptes = optionalClient.get().getComptes();
            return ResponseEntity.ok(comptes);
        } else {
            String message = "Client " + clientId + " n'existe pas";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

}












