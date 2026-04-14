package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;

import java.util.List;

public class HistoryTextHandler extends NgordnetQueryHandler {
    private NGramMap data;
    public HistoryTextHandler(NGramMap map){
        data = map;
    }
    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startyear = q.startYear();
        int endyear = q.endYear();
        String response ="";
        for(String i: words){

            TimeSeries tem = data.weightHistory(i,startyear,endyear);
            if (tem == null) {
                response = response + i + ": {none}\n";
            } else {
            response = response + i + ": " + tem.toString() + "\n";
            }


        }
        return response;
    }
}
