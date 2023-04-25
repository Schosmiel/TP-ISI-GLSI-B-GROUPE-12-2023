package groupe12.EGA.controller;

import groupe12.EGA.model.Depot;
import groupe12.EGA.model.Retrait;
import groupe12.EGA.model.Virement;
import groupe12.EGA.repo.DepoRepo;
import groupe12.EGA.repo.RetraitRepo;
import groupe12.EGA.repo.VirementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/operations")
public class OperationController {
    @Autowired
    private RetraitRepo retraitRepo;
    @Autowired
    private DepoRepo depoRepo;
    @Autowired
    private VirementRepo virementRepo;

    @GetMapping("/depots")
    public List<Depot> obtenirDepots(){
        return depoRepo.findAll();
    }
    @GetMapping(value="/depots/{txnid}")
    public ResponseEntity<Object> getDepotByTxnid(@PathVariable String txnid) {
        Optional<Depot> depot = depoRepo.findById(txnid);
        if (depot.isPresent()) {
            return ResponseEntity.ok(depot.get());
        } else {
            String message = "Dépot " + txnid + " non trouvé";
            Map<String, String> response = new HashMap<>();
            response.put("error", message);
            response.put("status", "NOT_FOUND");
            response.put("code", "404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/retraits")
    public List<Retrait> obtenirRetraits(){
        return retraitRepo.findAll();
    }

    @GetMapping(value="/retraits/{txnid}")
    public ResponseEntity<Object> getRetraitBytTxnid(@PathVariable String txnid) {
        Optional<Retrait> retrait = retraitRepo.findById(txnid);
        if (retrait.isPresent()) {
            return ResponseEntity.ok(retrait.get());
        } else {
            String message = "Retrait " + txnid + " non trouvé";
            Map<String, String> response = new HashMap<>();
            response.put("error", message);
            response.put("status", "NOT_FOUND");
            response.put("code", "404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }


    @GetMapping("/virements")
    public List<Virement> obtenirVirements(){
        return virementRepo.findAll();
    }
    @GetMapping(value="/virements/{txnid}")
    public ResponseEntity<Object> getVirementBytTxnid(@PathVariable String txnid) {
        Optional<Virement> virement = virementRepo.findById(txnid);
        if (virement.isPresent()) {
            return ResponseEntity.ok(virement.get());
        } else {
            String message = "Virement " + txnid + " non trouvé";
            Map<String, String> response = new HashMap<>();
            response.put("error", message);
            response.put("status", "NOT_FOUND");
            response.put("code", "404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

}

