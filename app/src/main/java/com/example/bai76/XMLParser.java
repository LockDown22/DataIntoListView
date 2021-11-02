package com.example.bai76;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XMLParser extends AsyncTask<String,Integer, ArrayList> {
    Context context;
    ArrayList<RSSItem> rssItems;
    ListView danhsach;

    public XMLParser(Context context, ArrayList<RSSItem> rssItems, ListView danhsach) {
        this.context = context;
        this.rssItems = rssItems;
        this.danhsach = danhsach;
    }

    @Override
    protected ArrayList doInBackground(String... strings) {

        try {

            URL url = new URL(strings[0]);
            InputSource is = new InputSource(url.openStream());
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = documentBuilderFactory.newDocumentBuilder();
            Document document = db.parse(is);

            NodeList nodeList = document.getElementsByTagName("item");
            int nodeListLength = nodeList.getLength();
            for(int i=0;i<nodeListLength;i++){
                RSSItem rssItem = new RSSItem();
                Node currentNode = nodeList.item(i);
                NodeList nChild = currentNode.getChildNodes();
                int childLength = nChild.getLength();
                for(int j = 0 ; j<childLength;j++){
                    Node thisNode = nChild.item(j);
                    String nodeName = thisNode.getNodeName();
                    String nodeValue = thisNode.getTextContent();
                    if(nodeValue!=null ){
                        if(nodeName.equalsIgnoreCase("title")){
                             rssItem.setTitle(nodeValue);
                        }
                        if(nodeName.equalsIgnoreCase("description")){
                            rssItem.setDescription(nodeValue);
                        }
                        if(nodeName.equalsIgnoreCase("date")){
                            rssItem.setDate(nodeValue);
                        }
                        if(nodeName.equalsIgnoreCase("link")){
                            rssItem.setLink(nodeValue);
                        }
                    }

                }
                rssItems.add(rssItem);

            }
        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
        return rssItems;

    }

    @Override
    protected void onPostExecute(ArrayList arrayList) {
        super.onPostExecute(arrayList);

       CustomAdapter adapter = new CustomAdapter(rssItems, context);
        danhsach.setAdapter(adapter);



    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        rssItems = new ArrayList<>();



    }
}
