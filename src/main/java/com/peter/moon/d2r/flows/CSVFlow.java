package com.peter.moon.d2r.flows;

import com.opencsv.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Iterator;

public class CSVFlow implements Flow, Iterable<String[]> {
    private static final Logger logger = LoggerFactory.getLogger(CSVFlow.class);

    @Override
    public CSVRow process() throws IOException {
        String fileName = "C:\\Users\\peter\\Desktop\\all2.csv";

        try {
            FileInputStream fis = new FileInputStream(fileName);
            InputStreamReader isr = new InputStreamReader(fis,
                    Charset.forName("GBK"));
            CSVReader reader = new CSVReader(isr);
            logger.info(String.valueOf(reader));
            return new CSVRow(reader);
        } catch (FileNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Iterator<String[]> iterator() {
        return null;
    }
}
