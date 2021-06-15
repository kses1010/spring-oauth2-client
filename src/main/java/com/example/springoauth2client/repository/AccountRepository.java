package com.example.springoauth2client.repository;

import com.example.springoauth2client.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
