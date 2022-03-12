package com.giulian.challenge.repository;

import com.giulian.challenge.model.Text;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TextRepository extends JpaRepository<Text, Long> {
}
