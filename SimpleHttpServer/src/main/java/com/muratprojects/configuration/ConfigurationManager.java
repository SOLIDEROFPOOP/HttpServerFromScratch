package com.muratprojects.configuration;

import com.fasterxml.jackson.databind.JsonNode;
import com.muratprojects.util.Json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConfigurationManager {
    private static ConfigurationManager configurationManager;
    private static Configuration myCurrentConfiguration;

    private ConfigurationManager(){
    }

    public static ConfigurationManager getInstance(){
        if (configurationManager == null){
            return new ConfigurationManager();
        }
        return configurationManager;
    }
    // TODO: load configuration file
    public void loadConfigurationFile(String filePath) throws IOException {
        FileReader fileReader = new FileReader(filePath);
        StringBuffer sb = new StringBuffer();
        int i;
        while ((i = fileReader.read()) != -1){
            sb.append((char) i);
        }
        JsonNode conf = Json.parse(sb.toString());
        myCurrentConfiguration = Json.fromJson(conf,Configuration.class);
    }
    // TODO: this one too
    public void getCurrentConfiguration(){

    }
}
