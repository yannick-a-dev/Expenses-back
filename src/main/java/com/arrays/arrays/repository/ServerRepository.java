package com.arrays.arrays.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arrays.arrays.model.Server;

public interface ServerRepository extends JpaRepository<Server, Long>{

	Server findByIpAddress(String ipAddress);
}
