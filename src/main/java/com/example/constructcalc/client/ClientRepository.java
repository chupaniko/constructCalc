package com.example.constructcalc.client;

import com.example.constructcalc.client.Client;
import com.example.constructcalc.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    List<Client> findByUsr(AppUser usr);
}
