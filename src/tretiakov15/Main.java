package ua.khpi.oop.tretiakov15;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class Main {

    public static void main(String args[]) throws ClassNotFoundException, ParserConfigurationException, TransformerException, IOException {

String choose = args[0];

        if(choose.equals("-auto"))
        {
            //automode();
            Auto a = new Auto();
        a.auto();
        }else
        {
        Manual m = new Manual();
        m.switchCase();
            //manual();
        }
    }
}
