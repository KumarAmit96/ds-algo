package com.ds.algo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubstringWithConcatenation {

    public static void main(String[] args) {
        SubstringWithConcatenation solution = new SubstringWithConcatenation();
        String s = "barfoothefoobarman";
        String[] words = {"foo","bar"};
        System.out.println(solution.findSubstringByTypeOne(s, words));
        System.out.println(solution.findSubstringByTypeTwo(s, words));
    }

    public List<Integer> findSubstringByTypeOne(String s, String[] words) {
        Map<String, Integer> cnt = new HashMap<>();
        for (String w : words) {
            cnt.merge(w, 1, Integer::sum);
        }
        int m = s.length(), n = words.length;
        int k = words[0].length();
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < k; ++i) {
            Map<String, Integer> cnt1 = new HashMap<>();
            int l = i, r = i;
            int t = 0;
            while (r + k <= m) {
                String w = s.substring(r, r + k);
                r += k;
                if (!cnt.containsKey(w)) {
                    cnt1.clear();
                    l = r;
                    t = 0;
                    continue;
                }
                cnt1.merge(w, 1, Integer::sum);
                ++t;
                while (cnt1.get(w) > cnt.get(w)) {
                    String remove = s.substring(l, l + k);
                    l += k;
                    cnt1.merge(remove, -1, Integer::sum);
                    --t;
                }
                if (t == n) {
                    ans.add(l);
                }
            }
        }
        return ans;
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