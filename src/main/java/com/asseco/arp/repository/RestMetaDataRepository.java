package com.asseco.arp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asseco.arp.domain.RestMetaData;

public interface RestMetaDataRepository extends JpaRepository<RestMetaData, Long> {

}
