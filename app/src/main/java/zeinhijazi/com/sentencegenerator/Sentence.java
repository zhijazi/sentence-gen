package zeinhijazi.com.sentencegenerator;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Zein on 8/15/2015.
 */
public class Sentence {
    private ArrayList<String> articles;
    private ArrayList<String> nouns;
    private ArrayList<String> prepositions;
    private ArrayList<String> verbs;

    private BufferedReader reader;
    private String randomSentence;
    private static ArrayList<String> sentenceHistory;

    private Random randomGen;

    public Sentence(Context context) {
        articles = new ArrayList<String>();
        nouns = new ArrayList<String>();
        prepositions = new ArrayList<String>();
        verbs = new ArrayList<String>();
        sentenceHistory = new ArrayList<String>();

        occupyArray(articles, "articles.txt", context);
        occupyArray(nouns, "nouns.txt", context);
        occupyArray(prepositions, "prepositions.txt", context);
        occupyArray(verbs, "verbs.txt", context);

        randomGen = new Random();
    }

    public void constructRandomSentence() {
        // TODO: Make a function that gets a random arraylist element so you dont have to look at this...
        randomSentence = articles.get(randomGen.nextInt(articles.size())) + " " +
                nouns.get(randomGen.nextInt(nouns.size())) + " " +
                verbs.get(randomGen.nextInt(verbs.size())) + " " +
                prepositions.get(randomGen.nextInt(prepositions.size())) + " " +
                articles.get(randomGen.nextInt(articles.size())) + " " +
                nouns.get(randomGen.nextInt(nouns.size()));
        randomSentence = randomSentence.substring(0,1).toUpperCase() + randomSentence.substring(1);
        sentenceHistory.add(randomSentence);
    }

    public String getRandomSentence() {
        return randomSentence;
    }

    public ArrayList<String> getSentenceHistory() {
        return sentenceHistory;
    }

    private void occupyArray(ArrayList<String> arrayList, String fileName, Context context) {
        try {
            reader = new BufferedReader(new InputStreamReader(context.getAssets().open(fileName)));

            String mLine = reader.readLine();
            while(mLine != null) {
                mLine = mLine.toLowerCase();
                arrayList.add(mLine);
                mLine = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
