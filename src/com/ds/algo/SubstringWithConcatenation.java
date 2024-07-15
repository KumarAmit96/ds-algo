package com.ds.algo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubstringWithConcatenation {

    public static void main(String[] args) {
        SubstringWithConcatenation solution = new SubstringWithConcatenation();
        String s = "wordgoodwordgoodbestword";
        String[] words = {"word","good","best","word"};
        System.out.println(solution.findSubstring(s, words));
    }

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.isEmpty() || words == null || words.length == 0) {
            return result;
        }

        int wordLength = words[0].length();
        int wordsCount = words.length;
        int totalLength = wordLength * wordsCount;

        // Create a frequency map for the words
        Map<String, Integer> wordMap = new HashMap<>();
        for (String word : words) {
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        }

        // Traverse through the string
        for (int i = 0; i <= s.length() - totalLength; i++) {
            Map<String, Integer> seenWords = new HashMap<>();
            int j = 0;
            while (j < wordsCount) {
                int wordIndex = i + j * wordLength;
                String word = s.substring(wordIndex, wordIndex + wordLength);
                if (wordMap.containsKey(word)) {
                    seenWords.put(word, seenWords.getOrDefault(word, 0) + 1);
                    if (seenWords.get(word) > wordMap.get(word)) {
                        break;
                    }
                } else {
                    break;
                }
                j++;
            }
            if (j == wordsCount) {
                result.add(i);
            }
        }

        return result;
    }
}
