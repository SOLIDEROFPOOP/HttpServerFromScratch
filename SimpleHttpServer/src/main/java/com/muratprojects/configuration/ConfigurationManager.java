package com.muratprojects.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.muratprojects.config.HttpConfigurationException;
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
    public void loadConfigurationFile(String filePath) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            throw new HttpConfigurationException(e);
        }
        StringBuffer sb = new StringBuffer();
        int i;
        while (true){
            try {
                if (!((i = fileReader.read()) != -1)) break;
            } catch (IOException e) {
                throw new HttpConfigurationException(e);
            }
            sb.append((char) i);
        }
        JsonNode conf = null;
        try {
            conf = Json.parse(sb.toString());
        } catch (JsonProcessingException e) {
            throw new HttpConfigurationException("Error parsing configuration file", e);
        }
        try {
            myCurrentConfiguration = Json.fromJson(conf,Configuration.class);
        } catch (JsonProcessingException e) {
            throw new HttpConfigurationException("Error parsing config file internal",e);
        }
    }
    // TODO: this one too
    public Configuration getCurrentConfiguration(){
        if (myCurrentConfiguration == null){
            throw new HttpConfigurationException("No current configuration set");
        }
        return myCurrentConfiguration;
    }
}
