package com.example.constructcalc.client;

import com.example.constructcalc.calculation.repositories.ClientCalculationRepository;
import com.example.constructcalc.client.model.Client;
import com.example.constructcalc.client.payload.ClientInfo;
import com.example.constructcalc.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    private ClientRepository clientRepository;
    private UserRepository userRepository;
    private ClientCalculationRepository clientCalculationRepository;

    public ClientController(ClientRepository clientRepository, UserRepository userRepository, ClientCalculationRepository clientCalculationRepository){
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
        this.clientCalculationRepository = clientCalculationRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Client>> getAllClients(){
        List<Client> allClientNames = clientRepository.findAll();
        return new ResponseEntity<>(allClientNames, HttpStatus.OK);
    }

    @GetMapping("/findByUser/{username}")
    public ResponseEntity<List<Client>> getByUser(@PathVariable(name="username") String username)
    {
        System.out.println(userRepository.findUserByUsername(username));
        List<Client> clients = clientRepository.findByUsr(userRepository.findUserByUsername(username).get());
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Client> saveClient(@RequestBody Client client){
        Client savedClient = clientRepository.save(client);

        if (savedClient == null){
            return ResponseEntity.status(422).build();
        }
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(savedClient.getId())
                .toUri();
        return ResponseEntity.created(uri)
                .body(savedClient);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<List<Client>> deleteClient(@PathVariable(name="id") int id){
        clientRepository.delete(clientRepository.getReferenceById(id));
        return getAllClients();
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<ClientInfo> getClientById(@PathVariable int id){
        Client client = clientRepository.findById(id).get();
        return new ResponseEntity<>(new ClientInfo(client, clientCalculationRepository.findByClient(client)), HttpStatus.OK);
    }
}
