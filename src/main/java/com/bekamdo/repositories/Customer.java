package com.bekamdo.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface Customer extends ReactiveMongoRepository<Customer,String> {
}
