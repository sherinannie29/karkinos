package com.karkinos.testapplication.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.karkinos.testapplication.dao.ITestApplicationDao;

import model.Patient;
import model.repo.PatientRepo;

@Component
public class testApplicationDaoImpl implements ITestApplicationDao {
	@Autowired
	private PatientRepo patientRepo;

	public List<Patient> getPatient() {
		return patientRepo.findAll();
	}

	public Patient getPatient(int id) {
		Optional<Patient> patient = patientRepo.findById(id);
		return patient.get();

	}

	public Patient getPatient(String name) {
		return patientRepo.findByName(name);
	}

	public void save(Patient patient) {
		patientRepo.saveAndFlush(patient);
	}

	public void deletePatient(int id) {
		patientRepo.deleteById(id);
	}
}
