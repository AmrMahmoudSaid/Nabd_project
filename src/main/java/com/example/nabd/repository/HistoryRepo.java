package com.example.nabd.repository;

import com.example.nabd.entity.History;
import com.example.nabd.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public interface HistoryRepo extends JpaRepository<History,Long> {
    List<History> findByPatientH(Patient patient);
}
