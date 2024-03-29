package com.lojadegames.lojadegames.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lojadegames.lojadegames.model.Categoria;


@Repository
public interface CategoriaRepository extends JpaRepository <Categoria, Long> {
	public List<Categoria>findAllBydescricaoContainingIgnoreCase(String descricao);

	
}
