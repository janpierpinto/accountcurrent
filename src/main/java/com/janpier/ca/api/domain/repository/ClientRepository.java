package com.janpier.ca.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.janpier.ca.api.models.Client;

/**
 * Repositório para ações da entidade Client.
 * @author janpi
 *
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
  Client findByCpf (String cpf);
}
