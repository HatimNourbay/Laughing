package com.example.hatim.laughing;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.stream.StreamSource;

/**
 * Created by Hatim on 09/05/2016.
 */
public class XMLParser {

    public ArrayList<ArticleItem> articles = null;

    public XMLParser(StringReader input){

        XmlPullParserFactory pullParserFactory;
        try {
            pullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = pullParserFactory.newPullParser();
            pullParserFactory.setNamespaceAware(true);


            parser.setInput(input);
            parseXML(parser);

        } catch (XmlPullParserException e) {

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void parseXML(XmlPullParser parser) throws XmlPullParserException,IOException
    {

        int eventType = parser.getEventType();
        ArticleItem currentArticle = null;

        while (eventType != XmlPullParser.END_DOCUMENT){
            String name = null;
            switch (eventType){
                case XmlPullParser.START_DOCUMENT:
                    articles = new ArrayList();
                    break;
                case XmlPullParser.START_TAG:
                    name = parser.getName();
                    if (name.equalsIgnoreCase("item")){
                        currentArticle = new ArticleItem();
                    } else if (currentArticle != null){
                        if (name.equalsIgnoreCase("title")){
                            currentArticle.title = parser.nextText();
                        } else if (name.equalsIgnoreCase("link")){
                            currentArticle.link = parser.nextText();
                        } else if (name.equalsIgnoreCase("description")){
                            currentArticle.description = parser.nextText();
                        } else if (name.equalsIgnoreCase("dc:creator")){
                            currentArticle.creator = parser.nextText();
                        }
                    }
                    break;
                case XmlPullParser.END_TAG:
                    name = parser.getName();
                    if (name.equalsIgnoreCase("item") && currentArticle != null){
                        articles.add(currentArticle);
                        //Log.w("Article",currentArticle.Title);
                    }
            }

            eventType = parser.next();

        }

        //Log.wtf("resultParse",articles.toString());
    }




}
