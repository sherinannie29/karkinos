package com.karkinos.testapplication.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.karkinos.testapplication.dao.ITestApplicationDao;

import model.Patient;

@RestController
@RequestMapping({ "/testApplication" })
public class testApplicationController {

	@Autowired
	private ITestApplicationDao testApplicationDao;

	@GetMapping({ "/status" })
	public String getStatus() {
		return "Application is up and running,";
	}

	@GetMapping({ "/patient" })
	public String getPatients() {
		String response = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			System.out.println("Fetching all patients");

			List<Patient> patients = testApplicationDao.getPatient();

			response = mapper.writeValueAsString(patients);

		} catch (Exception e) {
			System.out.println("Error occurred while fetching all patients.Error source : {}" + e.getStackTrace());
			e.printStackTrace();
		}

		return response;

	}

	@GetMapping({ "/patient/{id}" })
	public String getPatientById(@RequestParam(value = "id") int patientId) {
		String response = null;
		ObjectMapper mapper = new ObjectMapper();

		System.out.println("Fetching all patients");

		Patient patient = testApplicationDao.getPatient(patientId);

		try {
			response = mapper.writeValueAsString(patient);

		} catch (Exception e) {
			System.out.println("Error occurred while fetching patient.Error source : {}" + e.getStackTrace());
			response = "Error occurred while fetching patient";
		}

		return response;

	}

	@GetMapping({ "/patient/{name}" })
	public String getPatientByName(@RequestParam(value = "name") String patientName) {
		String response = null;
		ObjectMapper mapper = new ObjectMapper();

		System.out.println("Fetching all patients");

		Patient patient = testApplicationDao.getPatient(patientName);

		try {
			response = mapper.writeValueAsString(patient);

		} catch (Exception e) {
			System.out.println("Error occurred while fetching patient.Error source : {}" + e.getStackTrace());
			response = "Error occurred while fetching patient";
		}

		return response;

	}

	@PostMapping({ "/patient/{id}" })
	public void updatePatient(@RequestParam(value = "id") int patientId, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, true);

			ServletInputStream in = request.getInputStream();

			String requestString = in.toString();

			Patient requestObj = mapper.readValue(requestString, Patient.class);
			Patient patient = testApplicationDao.getPatient(patientId);
			patient.setGender(requestObj.getGender());
			patient.setMobileNumber(requestObj.getMobileNumber());
			patient.setName(requestObj.getName());

			testApplicationDao.save(patient);
			response.setStatus(200);
			response.getWriter().write("Update Successful");

		} catch (Exception e) {
			System.out.println("Error occurred while updating patient.Error source : {}" + e.getStackTrace());
			response.setStatus(500);
			response.getWriter().write("Update failure");

		}
	}

	@DeleteMapping({ "/patient/{id}" })
	public void deletePatient(@RequestParam(value = "id") int patientId, HttpServletResponse response)
			throws IOException {

		try {
			testApplicationDao.deletePatient(patientId);

			response.setStatus(200);
			response.getWriter().write("Delete Successful");

		} catch (Exception e) {
			System.out.println("Error occurred while deleting patient.Error source : {}" + e.getStackTrace());
			response.setStatus(500);
			response.getWriter().write("Delete failure");
		}

	}
}
