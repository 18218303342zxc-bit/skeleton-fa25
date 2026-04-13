package main;

import edu.princeton.cs.algs4.In;

import java.util.*;

import static main.TimeSeries.MAX_YEAR;
import static main.TimeSeries.MIN_YEAR;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {

    // TODO: Add any necessary static/instance variables.
    private static class wordinfo {
        int year;
        double frequency;

        public wordinfo(int a, double b){
            year = a;
            frequency = b;
        }

        public int getYear(){
            return year;
        }
        public double getFrequency(){
            return frequency;
        }

    }
    private Map<String, ArrayList<wordinfo>> worddata;
    private Map<Integer,Double> totaldata;
    /**
     * Constructs an NGramMap from WORDHISTORYFILENAME and YEARHISTORYFILENAME.
     */
    public NGramMap(String wordHistoryFilename, String yearHistoryFilename) {
        worddata = new HashMap<>();
        totaldata = new TreeMap<>();
        In inword = new In(wordHistoryFilename);
        String word = "";
        String wor ="";
        boolean firstime = true;
        ArrayList<wordinfo> tem = new ArrayList<>();
        while (!inword.isEmpty()) {
            String oneline = inword.readLine();
            String[] item = oneline.split("\t");
            if (!word.equals(item[0]) ){
               wor = word;
               word = item[0];
               if (firstime){
                   firstime = false;
               } else if (!firstime) {
                   worddata.put(wor, tem);
               }
                tem = new ArrayList<>();
            }

            if(word.equals(item[0])) {

                tem.add(new wordinfo(Integer.parseInt(item[1]),Double.parseDouble(item[2])));
                if(!inword.hasNextLine()){
                    worddata.put(word, tem);
                }
            }


        }
        In intotal = new In(yearHistoryFilename);
        while (!intotal.isEmpty()) {
            String oneline = intotal.readLine();
            String[] item = oneline.split(",");
            totaldata.put(Integer.parseInt(item[0]),Double.parseDouble(item[1]));
        }
        // TODO: Fill in this constructor. See the "NGramMap Tips" section of the spec for help.
    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        TimeSeries result = new TimeSeries();
        if (!worddata.containsKey(word)){
            return result;
        }
        for (int i = 0; i < worddata.get(word).size(); i++) {
            if (worddata.get(word).get(i).getYear() >= startYear && worddata.get(word).get(i).getYear() <= endYear){
                result.put(worddata.get(word).get(i).getYear(),worddata.get(word).get(i).getFrequency());
            }
            if (worddata.get(word).get(i).getYear() > endYear) {
                return result;
            }

        }
        return result;
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word) {
        TimeSeries result = new TimeSeries();
        if (!worddata.containsKey(word)){
            return result;
        }
        for (int i = 0; i < worddata.get(word).size(); i++) {

                result.put(worddata.get(word).get(i).getYear(),worddata.get(word).get(i).getFrequency());
            }


        return result;
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        TimeSeries result = new TimeSeries();
        for (int i : totaldata.keySet()) {
            result.put(i,totaldata.get(i));
        }
        return result;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        TimeSeries result = new TimeSeries();
        if (!worddata.containsKey(word)){
            return result;
        }
        for (int i = 0; i < worddata.get(word).size(); i++) {
            if (worddata.get(word).get(i).getYear() >= startYear && worddata.get(word).get(i).getYear() <= endYear){
                result.put(worddata.get(word).get(i).getYear(),worddata.get(word).get(i).getFrequency()/totaldata.get(worddata.get(word).get(i).getYear()));
            }
            if (worddata.get(word).get(i).getYear() > endYear) {
                return result;
            }

        }
        return result;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        TimeSeries result = new TimeSeries();
        if (!worddata.containsKey(word)){
            return result;
        }
        for (int i = 0; i < worddata.get(word).size(); i++) {
            result.put(worddata.get(word).get(i).getYear(),worddata.get(word).get(i).getFrequency()/totaldata.get(worddata.get(word).get(i).getYear()));
        }
        return result;
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        double totalword = 0;
        TimeSeries result = new TimeSeries();
        for (int i = startYear; i <= endYear; i++){
            for(String word: words)  {
                int j = 0;
                while (j <= worddata.get(word).size() && worddata.get(word).get(j).getYear() < i ) {
                      j++;
                }
                if (worddata.get(word).get(j).getYear() == i) {
                    totalword += worddata.get(word).get(j).getFrequency();
                }

            }
            result.put(i,totalword/totaldata.get(i));
        }
        return result;
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        double totalword = 0;
        TimeSeries result = new TimeSeries();
        for (int i = MIN_YEAR; i <= MAX_YEAR; i++){
            for(String word: words)  {
                int j = 0;
                while (j < worddata.get(word).size() && worddata.get(word).get(j).getYear() < i ) {
                    j++;
                }
                if (worddata.get(word).get(j).getYear() == i) {
                    totalword += worddata.get(word).get(j).getFrequency();
                }

            }
            result.put(i,totalword/totaldata.get(i));
        }
        return result;
    }

    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.
}
