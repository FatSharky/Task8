package by.traning.task8.dao;

import org.junit.Assert;
import org.junit.Test;

import by.traning.task8.dao.factory.DAOFactory;
import by.traning.task8.domains.Company;
import by.traning.task8.domains.role.Role;
import by.traning.task8.exception.DAOException;
import by.traning.task8.exception.DataDoesNotExistException;

public class CompanyDAOTest {
	@Test
	public void create() throws DAOException, DataDoesNotExistException {
		DAOFactory daoFactory = DAOFactory.getDAOFactory();
		CompanyDAO companyDAO = daoFactory.getCompanyDAO();

		Company exceptedCompany = new Company();

		exceptedCompany.setCompanyLogin("clogin");
		exceptedCompany.setPassword("123asd");
		exceptedCompany.setLogo(null);
		exceptedCompany.setName("Jdos");
		exceptedCompany.setWebSite("www.ff.com");
		exceptedCompany.setRole(Role.COMPANY);

		companyDAO.create(exceptedCompany);
		Company actualCompany = companyDAO.findCompanyByLogin("clogin");
		Assert.assertEquals(exceptedCompany.getCompanyLogin(), actualCompany.getCompanyLogin());
		Assert.assertEquals(exceptedCompany.getPassword(), actualCompany.getPassword());
		Assert.assertEquals(exceptedCompany.getLogo(), actualCompany.getLogo());
		Assert.assertEquals(exceptedCompany.getName(), actualCompany.getName());
		Assert.assertEquals(exceptedCompany.getWebSite(), actualCompany.getWebSite());
		Assert.assertEquals(exceptedCompany.getRole(), actualCompany.getRole());

	}



}
