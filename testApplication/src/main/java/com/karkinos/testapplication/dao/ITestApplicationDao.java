package com.karkinos.testapplication.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import model.Patient;

@Component
public interface ITestApplicationDao {

	public List<Patient> getPatient();

	public Patient getPatient(int id);

	public Patient getPatient(String name);

	public void save(Patient patient);

	public void deletePatient(int id);

}
