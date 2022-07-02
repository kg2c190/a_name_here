package com.nonesofar.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nonesofar.springboot.entity.Asset;

@Repository
public interface AssetsRepo extends JpaRepository<Asset, Long> {

}
