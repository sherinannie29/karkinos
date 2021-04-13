package model.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import model.Patient;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Integer> {

	public List<Patient> findAll();

	public Optional<Patient> findById(int id);

	public Patient findByName(String name);

	public Patient deleteById(int id);

}
