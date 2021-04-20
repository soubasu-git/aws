package org.ecnanif.feeder;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class TestMain {

    public static List<String> readFileInList(String fileName)
    {

        List<String> lines = Collections.emptyList();
        try
        {
            lines =
                    Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        }

        catch (IOException e)
        {

            // do something
            e.printStackTrace();
        }
        return lines;
    }
    public static void main(String[] args)
    {
        BigInteger bigInteger = new BigInteger("-1");
        System.out.println(bigInteger);
        List l = readFileInList(".\\test.txt");

        Iterator<String> itr = l.iterator();
       // while (itr.hasNext())
           // System.out.println(itr.next());
    }
}
