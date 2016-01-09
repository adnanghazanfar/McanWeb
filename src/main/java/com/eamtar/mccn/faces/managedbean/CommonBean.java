package com.eamtar.mccn.faces.managedbean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.eamtar.mccn.faces.controller.AbstractController;
import com.eamtar.mccn.model.Skill;
import com.eamtar.mccn.model.Specialty;
import com.eamtar.mccn.service.ManagerService;
import com.eamtar.mccn.service.ServiceManagerFactory;
import com.eamtar.mccn.service.UserProfileService;

/**
 * @author ADNAN GHAZANFAR
 * @email adnan.ghazanfar@yahoo.com
 * @since 09 SEP, 2014
 */
@Component(value = "commonBean")
@Scope("session")
public class CommonBean extends AbstractController {

	private Map<Integer, Skill> skillsMap = null;
	private Map<Integer, Specialty> specialtyMap = null;

	private List<Integer> oneToTenList = null;
	private List<String> monthList = null;
	private List<Integer> nextTenYearList = null;
	private List<Integer> lastHunderdYearList = null;
	private List<String> egyptStateList = null;
	private List<String> usaStateList = null;
	private List<String> countryList = null;
	private List<String> addressTypeList = null;
	private List<String> academicTitleList = null;
	private List<String> experienceTypeList = null;
	private List<String> topInstitueList = null;
	
	@Autowired
	private ManagerService managerService;

	public CommonBean() {
		
		long sTime = System.currentTimeMillis();

		loadSkillList();
		loadSpecialtyList();
		loadAcademicTitleList();
		loadExperienceTypeList();
		loadOneToTenList();
		loadMonthList();
		loadListNextTenYears();
		loadListLastHunderdYears();
		loadUsaStatesList();
		loadEgyptStatesList();
		loadCountryList();
		loadTopInstituteList();
		loadAddressTypeList();
		
		long eTime = System.currentTimeMillis();
        double diff = (double)(eTime-sTime)/1000;
        logger.error("EXECUTION TIME LOADING COMMON BEAN ::: "+diff+"sec.");

	}


	public Map<Integer, Skill> getSkillsMap() {
		return skillsMap;
	}

	public void setSkillsMap(Map<Integer, Skill> skillsMap) {
		this.skillsMap = skillsMap;
	}

	public Map<Integer, Specialty> getSpecialtyMap() {
		return specialtyMap;
	}


	public void setSpecialtyMap(Map<Integer, Specialty> specialtyMap) {
		this.specialtyMap = specialtyMap;
	}


	public void setCountryList(List<String> countryList) {
		this.countryList = countryList;
	}

	public List<Integer> getNextTenYearList() {
		return nextTenYearList;
	}


	public void setNextTenYearList(List<Integer> nextTenYearList) {
		this.nextTenYearList = nextTenYearList;
	}


	public List<Integer> getOneToTenList() {
		return oneToTenList;
	}

	public void setOneToTenList(List<Integer> oneToTenList) {
		this.oneToTenList = oneToTenList;
	}

	public List<String> getMonthList() {
		return monthList;
	}


	public void setMonthList(List<String> monthList) {
		this.monthList = monthList;
	}


	public List<Integer> getLastHunderdYearList() {
		return lastHunderdYearList;
	}

	public void setLastHunderdYearList(List<Integer> lastHunderdYearList) {
		this.lastHunderdYearList = lastHunderdYearList;
	}

	public List<String> getEgyptStateList() {
		return egyptStateList;
	}


	public void setEgyptStateList(List<String> egyptStateList) {
		this.egyptStateList = egyptStateList;
	}


	public List<String> getUsaStateList() {
		return usaStateList;
	}

	public void setStateList(List<String> usaStateList) {
		this.usaStateList = usaStateList;
	}

	public List<String> getCountryList() {
		return countryList;
	}

	public void setCountyList(List<String> countryList) {
		this.countryList = countryList;
	}

	public List<String> getAddressTypeList() {
		return addressTypeList;
	}


	public void setAddressTypeList(List<String> addressTypeList) {
		this.addressTypeList = addressTypeList;
	}


	public List<String> getAcademicTitleList() {
		return academicTitleList;
	}

	public void setAcademicTitleList(List<String> academicTitleList) {
		this.academicTitleList = academicTitleList;
	}

	public List<String> getExperienceTypeList() {
		return experienceTypeList;
	}

	public void setExperienceTypeList(List<String> experienceTypeList) {
		this.experienceTypeList = experienceTypeList;
	}

	public List<String> getTopInstitueList() {
		return topInstitueList;
	}

	public void setTopInstitueList(List<String> topInstitueList) {
		this.topInstitueList = topInstitueList;
	}

	/*
	 * METHODS TO LOAD DATA FOR DROPDOWNS
	 */

	public Skill getSkillById(Integer skillId) {
		return skillsMap.get(skillId);
	}

	public void loadSkillList() {

		ManagerService managerService = ServiceManagerFactory
				.getServiceManager(getServletContext());
		UserProfileService userProfileService = managerService
				.getUserProfileService();
		
		skillsMap = new HashMap<Integer, Skill>();
		for (Skill skill : userProfileService.getAllSkills()) {
			skillsMap.put(skill.getSkillId(), skill);
		}

	}
	
	public Specialty getSpecialtyById(Integer specialtyId) {
		return specialtyMap.get(specialtyId);
	}
	
	public void loadSpecialtyList() {

		ManagerService managerService = ServiceManagerFactory
				.getServiceManager(getServletContext());
		UserProfileService userProfileService = managerService
				.getUserProfileService();
		
		specialtyMap = new LinkedHashMap<Integer, Specialty>();
		for (Specialty specialty : userProfileService.getSpecialties(null)) {
			specialtyMap.put(specialty.getSpecialtyId(), specialty);
		}

	}
	
	public void loadMonthList() {

		String[] months = {"JAN" , "FEB" , "MAR" , "APR", "MAY",
                "JUN", "JUL", "AUG", "SEP", "OCT",
                "NOV", "DEC"};
        monthList = new ArrayList<String>(Arrays.asList(months));

	}

	public void loadOneToTenList() {

		oneToTenList = new ArrayList<Integer>();
		for (int counter = 1; counter <= 10; counter++) {
			oneToTenList.add(counter);
		}

	}
	
	public void loadListNextTenYears() {

		nextTenYearList = new ArrayList<Integer>();
		int CURRENT_YEAR = Calendar.getInstance().get(Calendar.YEAR);
		int LAST_YEAR = CURRENT_YEAR + 10;
		for (int counter = LAST_YEAR; counter > CURRENT_YEAR; counter--) {
			nextTenYearList.add(counter);
		}

	}

	public void loadListLastHunderdYears() {

		lastHunderdYearList = new ArrayList<Integer>();
		int CURRENT_YEAR = Calendar.getInstance().get(Calendar.YEAR);
		int LAST_YEAR = CURRENT_YEAR - 100;
		for (int counter = CURRENT_YEAR; counter > LAST_YEAR; counter--) {
			lastHunderdYearList.add(counter);
		}

	}
	
	
	public void loadAddressTypeList() {

		addressTypeList = new ArrayList<String>();

		addressTypeList.add("Current");
		addressTypeList.add("Previous");
		addressTypeList.add("Country of Origin");
		
	}
	
	

	public void loadExperienceTypeList() {

		experienceTypeList = new ArrayList<String>();

		experienceTypeList.add("US clinical Experience before residency");
		experienceTypeList.add("Research");
		experienceTypeList.add("Residency");
		experienceTypeList.add("Clinical fellowship");
		experienceTypeList.add("Attending physician");
		experienceTypeList.add("Other work experience");
		
	}

	public void loadAcademicTitleList() {

		academicTitleList = new ArrayList<String>();

		academicTitleList.add("Medical School");
		academicTitleList.add("Master's Degree");
		academicTitleList.add("PhD Degree");
		academicTitleList.add("Other degrees");
		academicTitleList.add("Licensing Exam ( USMLE, PLAB, others )");
		academicTitleList.add("Other exams (TOEFL, GRE, ILETS, ....)");
		academicTitleList.add("Scholarship or Grant");

	}
	
	public void loadEgyptStatesList() {

		egyptStateList = new ArrayList<String>();

		egyptStateList.add("Alexandria");
		egyptStateList.add("Aswan");
		egyptStateList.add("Assiut");
		egyptStateList.add("Beheira");
		egyptStateList.add("Beni Suef");
		egyptStateList.add("Cairo");
		egyptStateList.add("Dakahlia");
		egyptStateList.add("Damietta");
		egyptStateList.add("Fayoum");
		egyptStateList.add("Gharbia");
		egyptStateList.add("Giza");
		egyptStateList.add("Ismailia");
		egyptStateList.add("Kafr el-Sheikh");
		egyptStateList.add("Minya");
		egyptStateList.add("Menofia");
		egyptStateList.add("Port Said");
		egyptStateList.add("Qalyubia");
		egyptStateList.add("Qena");
		egyptStateList.add("Al-Sharqia");
		egyptStateList.add("Sohag");
		egyptStateList.add("Suez");
		
	}

	public void loadUsaStatesList() {

		usaStateList = new ArrayList<String>();

		usaStateList.add("AL Alabama");
		usaStateList.add("AK Alaska");
		usaStateList.add("AZ Arizona");
		usaStateList.add("AR Arkansas");
		usaStateList.add("CA California");
		usaStateList.add("CO Colorado");
		usaStateList.add("CT Connecticut");
		usaStateList.add("DE Delaware");
		usaStateList.add("DC District of Columbia");
		usaStateList.add("FL Florida");
		usaStateList.add("GA Georgia");
		usaStateList.add("HI Hawaii");
		usaStateList.add("ID Idaho");
		usaStateList.add("IL Illinois");
		usaStateList.add("IN Indiana");
		usaStateList.add("IA Iowa");
		usaStateList.add("KS Kansas");
		usaStateList.add("KY Kentucky");
		usaStateList.add("LA Louisiana");
		usaStateList.add("ME Maine");
		usaStateList.add("MD Maryland");
		usaStateList.add("MA Massachusetts");
		usaStateList.add("MI Michigan");
		usaStateList.add("MN Minnesota");
		usaStateList.add("MS Mississippi");
		usaStateList.add("MO Missouri");
		usaStateList.add("MT Montana");
		usaStateList.add("NE Nebraska");
		usaStateList.add("NV Nevada");
		usaStateList.add("NH New Hampshire");
		usaStateList.add("NJ New Jersey");
		usaStateList.add("NM New Mexico");
		usaStateList.add("NY New York");
		usaStateList.add("NC North Carolina");
		usaStateList.add("ND North Dakota");
		usaStateList.add("OH Ohio");
		usaStateList.add("OK Oklahoma");
		usaStateList.add("OR Oregon");
		usaStateList.add("PA Pennsylvania");
		usaStateList.add("RI Rhode Island");
		usaStateList.add("SC South Carolina");
		usaStateList.add("SD South Dakota");
		usaStateList.add("TN Tennessee");
		usaStateList.add("TX Texas");
		usaStateList.add("UT Utah");
		usaStateList.add("VT Vermont");
		usaStateList.add("VA Virginia");
		usaStateList.add("WA Washington");
		usaStateList.add("WV West Virginia");
		usaStateList.add("WI Wisconsin");
		usaStateList.add("WY Wyoming");

	}

	public void loadCountryList() {

		countryList = new ArrayList<String>();

		countryList.add("Afganistan");
		countryList.add("Albania");
		countryList.add("Algeria");
		countryList.add("American Samoa");
		countryList.add("Andorra");
		countryList.add("Angola");
		countryList.add("Anguilla");
		countryList.add("Antigua &amp; Barbuda");
		countryList.add("Argentina");
		countryList.add("Armenia");
		countryList.add("Aruba");
		countryList.add("Australia");
		countryList.add("Austria");
		countryList.add("Azerbaijan");
		countryList.add("Bahamas");
		countryList.add("Bahrain");
		countryList.add("Bangladesh");
		countryList.add("Barbados");
		countryList.add("Belarus");
		countryList.add("Belgium");
		countryList.add("Belize");
		countryList.add("Benin");
		countryList.add("Bermuda");
		countryList.add("Bhutan");
		countryList.add("Bolivia");
		countryList.add("Bonaire");
		countryList.add("Bosnia &amp; Herzegovina");
		countryList.add("Botswana");
		countryList.add("Brazil");
		countryList.add("British Indian Ocean Ter");
		countryList.add("Brunei");
		countryList.add("Bulgaria");
		countryList.add("Burkina Faso");
		countryList.add("Burundi");
		countryList.add("Cambodia");
		countryList.add("Cameroon");
		countryList.add("Canada");
		countryList.add("Canary Islands");
		countryList.add("Cape Verde");
		countryList.add("Cayman Islands");
		countryList.add("Central African Republic");
		countryList.add("Chad");
		countryList.add("Channel Islands");
		countryList.add("Chile");
		countryList.add("China");
		countryList.add("Christmas Island");
		countryList.add("Cocos Island");
		countryList.add("Colombia");
		countryList.add("Comoros");
		countryList.add("Congo");
		countryList.add("Cook Islands");
		countryList.add("Costa Rica");
		countryList.add("Cote DIvoire");
		countryList.add("Croatia");
		countryList.add("Cuba");
		countryList.add("Curaco");
		countryList.add("Cyprus");
		countryList.add("Czech Republic");
		countryList.add("Denmark");
		countryList.add("Djibouti");
		countryList.add("Dominica");
		countryList.add("Dominican Republic");
		countryList.add("East Timor");
		countryList.add("Ecuador");
		countryList.add("Egypt");
		countryList.add("El Salvador");
		countryList.add("Equatorial Guinea");
		countryList.add("Eritrea");
		countryList.add("Estonia");
		countryList.add("Ethiopia");
		countryList.add("Falkland Islands");
		countryList.add("Faroe Islands");
		countryList.add("Fiji");
		countryList.add("Finland");
		countryList.add("France");
		countryList.add("French Guiana");
		countryList.add("French Polynesia");
		countryList.add("French Southern Ter");
		countryList.add("Gabon");
		countryList.add("Gambia");
		countryList.add("Georgia");
		countryList.add("Germany");
		countryList.add("Ghana");
		countryList.add("Gibraltar");
		countryList.add("Great Britain");
		countryList.add("Greece");
		countryList.add("Greenland");
		countryList.add("Grenada");
		countryList.add("Guadeloupe");
		countryList.add("Guam");
		countryList.add("Guatemala");
		countryList.add("Guinea");
		countryList.add("Guyana");
		countryList.add("Haiti");
		countryList.add("Hawaii");
		countryList.add("Honduras");
		countryList.add("Hong Kong");
		countryList.add("Hungary");
		countryList.add("Iceland");
		countryList.add("India");
		countryList.add("Indonesia");
		countryList.add("Iran");
		countryList.add("Iraq");
		countryList.add("Ireland");
		countryList.add("Isle of Man");
		countryList.add("Israel");
		countryList.add("Italy");
		countryList.add("Jamaica");
		countryList.add("Japan");
		countryList.add("Jordan");
		countryList.add("Kazakhstan");
		countryList.add("Kenya");
		countryList.add("Kiribati");
		countryList.add("Korea North");
		countryList.add("Korea Sout");
		countryList.add("Kuwait");
		countryList.add("Kyrgyzstan");
		countryList.add("Laos");
		countryList.add("Latvia");
		countryList.add("Lebanon");
		countryList.add("Lesotho");
		countryList.add("Liberia");
		countryList.add("Libya");
		countryList.add("Liechtenstein");
		countryList.add("Lithuania");
		countryList.add("Luxembourg");
		countryList.add("Macau");
		countryList.add("Macedonia");
		countryList.add("Madagascar");
		countryList.add("Malaysia");
		countryList.add("Malawi");
		countryList.add("Maldives");
		countryList.add("Mali");
		countryList.add("Malta");
		countryList.add("Marshall Islands");
		countryList.add("Martinique");
		countryList.add("Mauritania");
		countryList.add("Mauritius");
		countryList.add("Mayotte");
		countryList.add("Mexico");
		countryList.add("Midway Islands");
		countryList.add("Moldova");
		countryList.add("Monaco");
		countryList.add("Mongolia");
		countryList.add("Montserrat");
		countryList.add("Morocco");
		countryList.add("Mozambique");
		countryList.add("Myanmar");
		countryList.add("Nambia");
		countryList.add("Nauru");
		countryList.add("Nepal");
		countryList.add("Netherland Antilles");
		countryList.add("Netherlands");
		countryList.add("Nevis");
		countryList.add("New Caledonia");
		countryList.add("New Zealand");
		countryList.add("Nicaragua");
		countryList.add("Niger");
		countryList.add("Nigeria");
		countryList.add("Niue");
		countryList.add("Norfolk Island");
		countryList.add("Norway");
		countryList.add("Oman");
		countryList.add("Pakistan");
		countryList.add("Palau Island");
		countryList.add("Palestine");
		countryList.add("Panama");
		countryList.add("Papua New Guinea");
		countryList.add("Paraguay");
		countryList.add("Peru");
		countryList.add("Phillipines");
		countryList.add("Pitcairn Island");
		countryList.add("Poland");
		countryList.add("Portugal");
		countryList.add("Puerto Rico");
		countryList.add("Qatar");
		countryList.add("Republic of Montenegro");
		countryList.add("Republic of Serbia");
		countryList.add("Reunion");
		countryList.add("Romania");
		countryList.add("Russia");
		countryList.add("Rwanda");
		countryList.add("St Barthelemy");
		countryList.add("St Eustatius");
		countryList.add("St Helena");
		countryList.add("St Kitts-Nevis");
		countryList.add("St Lucia");
		countryList.add("St Maarten");
		countryList.add("St Pierre &amp; Miquelon");
		countryList.add("St Vincent &amp; Grenadines");
		countryList.add("Saipan");
		countryList.add("Samoa");
		countryList.add("Samoa American");
		countryList.add("San Marino");
		countryList.add("Sao Tome &amp; Principe");
		countryList.add("Saudi Arabia");
		countryList.add("Senegal");
		countryList.add("Serbia");
		countryList.add("Seychelles");
		countryList.add("Sierra Leone");
		countryList.add("Singapore");
		countryList.add("Slovakia");
		countryList.add("Slovenia");
		countryList.add("Solomon Islands");
		countryList.add("Somalia");
		countryList.add("South Africa");
		countryList.add("Spain");
		countryList.add("Sri Lanka");
		countryList.add("Sudan");
		countryList.add("Suriname");
		countryList.add("Swaziland");
		countryList.add("Sweden");
		countryList.add("Switzerland");
		countryList.add("Syria");
		countryList.add("Tahiti");
		countryList.add("Taiwan");
		countryList.add("Tajikistan");
		countryList.add("Tanzania");
		countryList.add("Thailand");
		countryList.add("Togo");
		countryList.add("Tokelau");
		countryList.add("Tonga");
		countryList.add("Trinidad &amp; Tobago");
		countryList.add("Tunisia");
		countryList.add("Turkey");
		countryList.add("Turkmenistan");
		countryList.add("Turks &amp; Caicos Is");
		countryList.add("Tuvalu");
		countryList.add("Uganda");
		countryList.add("Ukraine");
		countryList.add("United Arab Erimates");
		countryList.add("United Kingdom");
		countryList.add("United States of America");
		countryList.add("Uraguay");
		countryList.add("Uzbekistan");
		countryList.add("Vanuatu");
		countryList.add("Vatican City State");
		countryList.add("Venezuela");
		countryList.add("Vietnam");
		countryList.add("Virgin Islands (Brit)");
		countryList.add("Virgin Islands (USA)");
		countryList.add("Wake Island");
		countryList.add("Wallis &amp; Futana Is");
		countryList.add("Yemen");
		countryList.add("Zaire");
		countryList.add("Zambia");
		countryList.add("Zimbabwe");

	}

	public void loadTopInstituteList() {

		topInstitueList = new ArrayList<String>();

		topInstitueList.add("Ain Shams University");
		topInstitueList.add("Al-Azhar University - (Cairo, Damietta, Assiut)");
		topInstitueList.add("Alexandria Faculty of Medicine");
		topInstitueList.add("Al-Fayyum University");
		topInstitueList.add("Al-Minya University");
		topInstitueList.add("Assiut University");
		topInstitueList.add("Aswan University");
		topInstitueList.add("Banha University");
		topInstitueList.add("Bani Suwayf University");
		topInstitueList.add("Cairo University (Kasr El-Aini)");
		topInstitueList.add("Helwan University");
		topInstitueList.add("Kafr Ashaykh University");
		topInstitueList
				.add("Mansoura University & Mansoura Manchester Programme for Medical Education (MMPME)");
		topInstitueList.add("Minufiya University");
		topInstitueList
				.add("Misr University for Science and Technology - Private School");
		topInstitueList.add("October 6th University - Private School");
		topInstitueList.add("Portsaid University");
		topInstitueList.add("Sohag University");
		topInstitueList.add("South Valley University - Kena City");
		topInstitueList.add("Suez Canal University");
		topInstitueList.add("Tanta University");

	}
}
