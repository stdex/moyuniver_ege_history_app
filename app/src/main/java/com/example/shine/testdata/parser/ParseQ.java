package com.example.shine.testdata.parser;

import com.example.shine.testdata.MainActivity;
import com.example.shine.testdata.models.Answer;
import com.example.shine.testdata.models.ListQuestions;
import com.example.shine.testdata.models.Question;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class ParseQ {

    public static ListQuestions qList;

    public static ListQuestions parseQuestions(String s) {

        ArrayList<String[]> parselist = MainActivity.parseSharpLine(s);

        Map<String,Question> mQuestions = new LinkedHashMap<String,Question>();
        Multimap<String, Answer> mAnswers = ArrayListMultimap.create();

        if(parselist.size() > 0) {

            for(String[] item : parselist)  {
                Answer ans = new Answer(item[4], item[2], item[5], item[6], item[7]);
                mAnswers.put(item[1], ans);
            }

            for(String[] item : parselist)  {
                if(!"".equals(item[3])) {

                    Question question = new Question(item[1], item[2], item[3], item[8], mAnswers.get(item[1]));
                    mQuestions.put(item[1], question);
                }
            }

            String tid = parselist.get(0)[0];
            qList = new ListQuestions(tid, mQuestions);
        }

        return qList;
    }

}
