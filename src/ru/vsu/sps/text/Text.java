package ru.vsu.sps.text;

import java.util.ArrayList;
import java.util.List;

public class Text {
    private final String text;
    private final char[] sentenceEndSymbols = {'!', '.', '?'};

    public Text(String s) {
        text = s;
    }

    private boolean charIsEndOfSentence(char ch) {
        for (char val : sentenceEndSymbols) {
            if (val == ch) {
                return true;
            }
        }
        return false;
    }

    private int getFirstSentenceBeginIndex(String s) {
        for (int index = 0; index < s.length(); index++) {
            if (!(charIsEndOfSentence(s.charAt(index)) || s.charAt(index) == ' ')) {
                return index;
            }
        }
        return -1;
    }

    private int getSentenceEndIndex(String s, int sentenceBeginIndex) {
        int endIndex = -1;
        if (sentenceBeginIndex > -1) {
            s = s.substring(sentenceBeginIndex);
            for (char val : sentenceEndSymbols) {
                int symbolIndex = s.indexOf(val);
                if (symbolIndex != -1 && (symbolIndex < endIndex || endIndex == -1)) {
                    endIndex = symbolIndex + sentenceBeginIndex;
                }
            }
        }
        return endIndex;
    }

    public List<String> getSentences() {
        List<String> sentences = new ArrayList<>();
        String text = this.text;

        int sentenceBeginIndex = getFirstSentenceBeginIndex(text);
        int sentenceEndIndex = getSentenceEndIndex(text, sentenceBeginIndex);
        while (sentenceEndIndex != -1 && sentenceBeginIndex != -1) {
            sentences.add(text.substring(sentenceBeginIndex, sentenceEndIndex + 1));

            text = text.substring(sentenceEndIndex);
            sentenceBeginIndex = getFirstSentenceBeginIndex(text);
            sentenceEndIndex = getSentenceEndIndex(text, sentenceBeginIndex);
        }

        return sentences;
    }

    public List<String> getQuestionSentences() {
        List<String> sentences = new ArrayList<>();
        for (String sentence : getSentences()) {
            if (sentence.endsWith("?")) {
                sentences.add(sentence);
            }
        }
        return sentences;
    }
}
