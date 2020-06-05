package ru.kuznetsoviv.drools.service;

import org.junit.Before;
import org.junit.Test;
import ru.kuznetsoviv.drools.model.Applicant;
import ru.kuznetsoviv.drools.model.SuggestedRole;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ApplicantServiceIntegrationTest {


    private ApplicantService applicantService;

    @Before
    public void setup() {
        applicantService = new ApplicantService();
    }

    @Test
    public void whenCriteriaMatching_ThenSuggestManagerRole() {
        Applicant applicant = new Applicant("Davis", 37, 1600000.0, 11);
        SuggestedRole suggestedRole = new SuggestedRole();
        applicantService.suggestARoleForApplicant(applicant, suggestedRole);
        assertEquals("Manager", suggestedRole.getRole());
    }

    @Test
    public void whenCriteriaMatching_ThenSuggestSeniorDeveloperRole() {
        Applicant applicant = new Applicant("John", 37, 1200000.0, 8);
        SuggestedRole suggestedRole = new SuggestedRole();
        applicantService.suggestARoleForApplicant(applicant, suggestedRole);
        assertEquals("Senior developer", suggestedRole.getRole());
    }

    @Test
    public void whenCriteriaMatching_ThenSuggestDeveloperRole() {
        Applicant applicant = new Applicant("Davis", 37, 800000.0, 3);
        SuggestedRole suggestedRole = new SuggestedRole();
        applicantService.suggestARoleForApplicant(applicant, suggestedRole);
        assertEquals("Developer", suggestedRole.getRole());
    }

    @Test
    public void whenCriteriaNotMatching_ThenNoRole() {
        Applicant applicant = new Applicant("John", 37, 1200000.0, 5);
        SuggestedRole suggestedRole = new SuggestedRole();
        applicantService.suggestARoleForApplicant(applicant, suggestedRole);
        assertNull(suggestedRole.getRole());
    }

}
