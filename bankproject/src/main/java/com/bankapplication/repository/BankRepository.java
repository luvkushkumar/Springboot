package com.bankapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankapplication.entity.BankAccount;


@Repository
public interface BankRepository extends JpaRepository<BankAccount, Long> {

}
