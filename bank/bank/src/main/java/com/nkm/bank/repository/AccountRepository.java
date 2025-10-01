package com.nkm.bank.repository;

import com.nkm.bank.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, String> {


}
