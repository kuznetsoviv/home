package ru.kuznetsoviv.drools.service;

import org.kie.api.runtime.KieSession;
import ru.kuznetsoviv.drools.config.DroolsBeanFactory;
import ru.kuznetsoviv.drools.model.Applicant;
import ru.kuznetsoviv.drools.model.SuggestedRole;

public class ApplicantService {

    private final KieSession kieSession = new DroolsBeanFactory().getKieSession();

    public SuggestedRole suggestARoleForApplicant(Applicant applicant, SuggestedRole suggestedRole) {
        kieSession.insert(applicant);
        kieSession.setGlobal("suggestedRole", suggestedRole);
        kieSession.fireAllRules();
        System.out.println(suggestedRole.getRole());
        return suggestedRole;

    }

}
