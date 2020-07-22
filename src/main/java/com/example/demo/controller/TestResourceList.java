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
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class TestResourceList {

    public  List<File> getRuleFiles(String RULES_PATH ) throws IOException {
        List<File> list = new ArrayList<File>();
        String filePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        File rootDir = new File(filePath);
        File[] files = rootDir.listFiles();
        for (File itemFile : files) {
            if (itemFile.isDirectory() && itemFile.getName().equals(RULES_PATH)) {
                for (File f : itemFile.listFiles()) {
                    if (f.getName().endsWith(".drl")) {
                        list.add(f);
                    }
                }
            }
        }
        return list;
    }


}







