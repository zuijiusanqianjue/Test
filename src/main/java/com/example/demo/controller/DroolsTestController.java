package com.example.demo.controller;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.ReleaseId;
import org.kie.api.builder.model.KieModuleModel;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;
@Controller
public class DroolsTestController {
    @Autowired
    private TestResourceList testResourceList;
    private static final String RULES_PATH = "tb";
    @RequestMapping("/drools")
    public  void drools() throws IOException {
        // TODO Auto-generated method stub
        KieServices ks = KieServices.Factory.get();
        KieFileSystem kfs = ks.newKieFileSystem();
        ReleaseId rid = ks.newReleaseId("org.drools", "kiemodulemodel","1.0");
        kfs.generateAndWritePomXML(rid);
        KieModuleModel kModuleModel = ks.newKieModuleModel();
        kModuleModel.newKieBaseModel("kiemodulemodel")
                .newKieSessionModel("ksession");
        kfs.writeKModuleXML(kModuleModel.toXML());
        System.out.println(kModuleModel.toXML());
        for (File file : testResourceList.getRuleFiles(RULES_PATH )) {
            kfs.write("src/main/resources/" + file.getName(), ResourceFactory.newClassPathResource(RULES_PATH + File.separator + file.getName(), "UTF-8"));
            System.out.println("1111111111111111111111111111"+file.getName());
        }

        KieBuilder kb = ks.newKieBuilder(kfs);
        kb.buildAll();
        if (kb.getResults().hasMessages(Message.Level.ERROR)) {
            throw new RuntimeException("Build Errors:\n"+kb.getResults().toString());
        }
        KieContainer kContainer = ks.newKieContainer(rid);
        kContainer.updateToVersion( rid );
        KieSession kieSession = kContainer.newKieSession("ksession");
        kieSession.fireAllRules();
        System.out.println("1111111111111111111111111111");
    }

}
