package org.ecnanif.feeder.feederevent;

import org.ecnanif.feeder.model.MF;
import org.ecnanif.feeder.repository.DBFeederEventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

@RestController
public class DBFeederScheduledAndControlledEvent {
    private static final Logger LOG = LoggerFactory.getLogger(DBFeederScheduledAndControlledEvent.class);

    @Autowired
    private final DBFeederEventRepository eventRepository;

    public DBFeederScheduledAndControlledEvent(final DBFeederEventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
    @RequestMapping(value="/",method = RequestMethod.GET)
    @Scheduled(cron = "0 15 23 * * 1,2,3,4,5")
    public void loadDB() throws IOException, ParseException {
        Date currentDate = new Date();// new SimpleDateFormat("dd-MMM-yyyy").parse("28-DEC-2018");//
        URL url = new URL("https://www.amfiindia.com/spages/NAVAll.txt");
        URLConnection con = url.openConnection();
        InputStream in = con.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
        String line = null;
        boolean flag = false;
        String schemeType = "NULL";
        String category = "NULL";
        String fundHouse = "NULL";
        while ((line = bufferedReader.readLine()) != null) {
            if (line.trim().isEmpty()) {
                flag = true;
                continue;
            }
            if (flag) {
                if (line.startsWith("Open") || line.startsWith("Close")) {
                    String[] split = line.split("[(]");
                    schemeType = split[0];
                    category = split[1].substring(0, split.length - 1);
                } else if (line.endsWith("Fund")) {
                    fundHouse = line;
                }
                flag = false;
                continue;
            }
            String[] split = line.split("[;]");
            if (isInteger(split[0], 10)) {
                Date updateDate = new SimpleDateFormat("dd-MMM-yyyy").parse(split[5]);
                if (updateDate.compareTo(currentDate) == 0) {
                    if (split[4].trim().equalsIgnoreCase("N.A")) {
                        split[4] = "-1";
                    }
                    MF mf = new MF(new BigInteger(split[0]), schemeType, category, fundHouse, split[1], split[2], split[3], new BigDecimal(split[4]), new SimpleDateFormat("dd-MMM-yyyy").parse(split[5]));
                    eventRepository.save(mf);
                }
            } else {
                continue;
            }

        }
        //Spring Admin enable first
        //log unupdated funds and handle all exceptions here only so that data loading is not interrupted
        //hibernate versioning and old data for graphs
        //feign client in place of rest template
        //ControllerAdvice
        //repositoryrestcontroller for hateoas support vs restcontroller
        //MFResource use https://dzone.com/articles/applying-hateoas-to-a-rest-api-with-spring-boot
        //Load balance using eureka client's multiple instances
        LOG.debug("DB UPDATED!");
    }

    public static boolean isInteger(String s, int radix) {
        Scanner sc = new Scanner(s.trim());
        if (!sc.hasNextInt(radix)) return false;
        sc.nextInt(radix);
        return !sc.hasNext();
    }
}
