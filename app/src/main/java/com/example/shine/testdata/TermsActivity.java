package com.example.shine.testdata;

import android.support.v4.app.Fragment;

import com.actionbarsherlock.app.ActionBar;
import com.example.shine.testdata.models.Date;
import com.example.shine.testdata.models.Term;
import com.example.shine.testdata.tabsframework.AbstractTabStackNavigationActivity;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

public class TermsActivity extends AbstractTabStackNavigationActivity {

    String[] mTermArray;
    String[] mDescriptionArray;
    Multimap<String, Term> mTermTable = ArrayListMultimap.create();
    public static List<String> termstrs = new ArrayList<String>();

    public void addTab(final String title, final Fragment fragment) {
        final ActionBar.Tab tab = getSupportActionBar().newTab();
        tab.setText(title);
        addTab(tab, fragment);
    }

    /**
     * overriden call back method where the tabs are created
     */
    @Override
    protected void createTabs() {

        mTermArray = getResources().getStringArray(R.array.terms);
        mDescriptionArray = getResources().getStringArray(R.array.definitions);


        for(int i = 0; i < mTermArray.length; i++)  {

            String term = mTermArray[i].substring(mTermArray[i].split("[\\{\\}]")[1].length()+2);
            String tag = mTermArray[i].split("[\\{\\}]")[1];
            String desc = mDescriptionArray[i];

            Term termobj = new Term(tag, term, desc);
            mTermTable.put(tag, termobj);
        }

        List list_keys = new LinkedList(mTermTable.keySet());
        Collections.sort(list_keys);
        LinkedHashSet<String> dateKeysSet = new LinkedHashSet(list_keys);

        for (String tagb : dateKeysSet) {

            Collection<Term> values = mTermTable.get(tagb);

            termstrs.clear();
            for (Term term : values) {
                termstrs.add(term.getTerm() + " - " + term.getDescription());
            }

            String[] termArr = new String[termstrs.size()];
            termArr = termstrs.toArray(termArr);

            addTab(
                    tagb,
                    new TabListFragment().setCharacters(termArr));


        }

    }


}
