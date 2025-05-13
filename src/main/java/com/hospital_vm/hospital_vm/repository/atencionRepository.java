package com.hospital_vm.hospital_vm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital_vm.hospital_vm.model.Atencion;

@Repository
public interface atencionRepository extends JpaRepository<Atencion,Integer>{


}
