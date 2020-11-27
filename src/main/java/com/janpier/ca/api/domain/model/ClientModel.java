package com.janpier.ca.api.domain.model;

/**
 * Modelo de cadastro para um novo cliente.
 * @author Janpier
 *
 */
public class ClientModel {
  
  private Long id;
  private String name;
  
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

}
