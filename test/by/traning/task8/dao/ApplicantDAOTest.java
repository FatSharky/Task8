package by.traning.task8.dao;

import org.junit.Assert;
import org.junit.Test;

import by.traning.task8.dao.factory.DAOFactory;
import by.traning.task8.domains.Applicant;
import by.traning.task8.exception.DAOException;
import by.traning.task8.exception.DataDoesNotExistException;

public class ApplicantDAOTest {
	@Test
	public void create() throws DAOException, DataDoesNotExistException {
		DAOFactory daoFactory = DAOFactory.getDAOFactory();
		ApplicantDAO applicantDAO = daoFactory.getApplicantDAO();

		Applicant exceptedApplicant = new Applicant();

		exceptedApplicant.setEmail("vladbypinsk@mail.ru");
		exceptedApplicant.setPassword("das12");
		exceptedApplicant.setSurname("Гапеенко");
		exceptedApplicant.setName("Владислав");
		exceptedApplicant.setSecondName("Витальевич");

		applicantDAO.create(exceptedApplicant);
		Applicant actualApplicant = applicantDAO.findApplicantByLogin("vladbypinsk@mail.ru");
		Assert.assertEquals(exceptedApplicant.getEmail(), actualApplicant.getEmail());
		Assert.assertEquals(exceptedApplicant.getPassword(), actualApplicant.getPassword());
		Assert.assertEquals(exceptedApplicant.getSurname(), actualApplicant.getSurname());
		Assert.assertEquals(exceptedApplicant.getName(), actualApplicant.getName());
		Assert.assertEquals(exceptedApplicant.getSecondName(), actualApplicant.getSecondName());

	}

	@Test
	public void update() throws DAOException, DataDoesNotExistException {
		DAOFactory daoFactory = DAOFactory.getDAOFactory();
		ApplicantDAO applicantDAO = daoFactory.getApplicantDAO();

		Applicant exceptedApplicant = new Applicant();

		Applicant rollbackApplicant = new Applicant();
		rollbackApplicant.setEmail(exceptedApplicant.getEmail());
		rollbackApplicant.setPassword(exceptedApplicant.getPassword());
		rollbackApplicant.setSurname(exceptedApplicant.getSurname());
		rollbackApplicant.setName(exceptedApplicant.getName());
		rollbackApplicant.setSecondName(exceptedApplicant.getSecondName());

		exceptedApplicant.setEmail("vladbypinsk@mail.ru");
		exceptedApplicant.setPassword("das12");
		exceptedApplicant.setSurname("Гапеенко");
		exceptedApplicant.setName("Владислав");
		exceptedApplicant.setSecondName("Витальевич");

		applicantDAO.update(exceptedApplicant);

		Applicant actualApplicant = applicantDAO.findApplicantByLogin("vladbypinsk@mail.ru");

		applicantDAO.update(rollbackApplicant);

		Assert.assertEquals(exceptedApplicant.getEmail(), actualApplicant.getEmail());
		Assert.assertEquals(exceptedApplicant.getPassword(), actualApplicant.getPassword());
		Assert.assertEquals(exceptedApplicant.getSurname(), actualApplicant.getSurname());
		Assert.assertEquals(exceptedApplicant.getName(), actualApplicant.getName());
		Assert.assertEquals(exceptedApplicant.getSecondName(), actualApplicant.getSecondName());

	}

}
