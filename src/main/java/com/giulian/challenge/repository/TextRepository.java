package com.giulian.challenge.repository;

import com.giulian.challenge.model.Text;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TextRepository extends JpaRepository<Text, Long> {


//    public Text findByChars(int chars);
    List<Text> findByChars(int chars);
    Optional<Text> findByHash(String hash);


}
