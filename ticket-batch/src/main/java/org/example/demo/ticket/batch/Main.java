package org.example.demo.ticket.batch;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.demo.ticket.business.contract.ManagerFactory;
import org.example.demo.ticket.model.bean.ticket.TicketStatut;
import org.example.demo.ticket.model.exception.TechnicalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.*;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Properties;


/**
 * Classe Principale de lancement des Batches.
 *
 * @author lgu
 */
public class Main {

    /** Logger pour la classe */
    private static final Log LOGGER = LogFactory.getLog(Main.class);

    /**
     * The entry point of application.
     *
     * @param pArgs the input arguments
     * @throws TechnicalException sur erreur technique
     */
    public static void main(String[] pArgs) throws TechnicalException {

        ApplicationContext vApplicationContext
                = new ClassPathXmlApplicationContext("classpath:/bootstrapContext.xml");
        ManagerFactory vManagerFactory
                = vApplicationContext.getBean("managerFactory", ManagerFactory.class);
        Properties ticketStatusConfiguration = vApplicationContext.getBean("ticketStatutConfiguration", Properties.class);

        try {
            if (pArgs.length < 1) {
                throw new TechnicalException("Veuillez préciser le traitement à effectuer !");
            }

            String vTraitementId = pArgs[0];
            if ("ExportTicketStatus".equals(vTraitementId)) {

                List<TicketStatut> ticketStatutList = vManagerFactory.getTicketManager().getListStatut();

                LOGGER.info("Execution du traitement : ExportTicketStatus");

                String batchDataFolderPath = vApplicationContext.getEnvironment().getProperty("application.ticket-batch.data");
                String filePath = batchDataFolderPath + ticketStatusConfiguration.getProperty("ticketStatusExportPath");

                File exportFile = new File(filePath);

                try{
                    BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
                    FileOutputStream fos = new FileOutputStream(exportFile);

                    for(TicketStatut ts : ticketStatutList) {
                        fos.write(ts.toString().getBytes());
                        fos.write("\n".getBytes());
                        LOGGER.info(ts.toString());
                    }
                }catch(FileNotFoundException e){
                    e.printStackTrace();
                }catch(IOException e){
                    e.printStackTrace();
                }
            } else {
                throw new TechnicalException("Traitement inconnu : " + vTraitementId);
            }
        } catch (Throwable vThrowable) {
            LOGGER.error(vThrowable);
            System.exit(1);
        }
    }
}
