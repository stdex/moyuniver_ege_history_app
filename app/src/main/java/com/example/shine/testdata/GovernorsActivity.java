package com.example.shine.testdata;

import android.support.v4.app.Fragment;

import com.actionbarsherlock.app.ActionBar;
import com.example.shine.testdata.models.Governor;
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

public class GovernorsActivity extends AbstractTabStackNavigationActivity {

    String[] mGovernorArray;
    String[] mDescriptionArray;
    Multimap<String, Governor> mGovernorsTable = ArrayListMultimap.create();
    public static List<String> governorstrs = new ArrayList<String>();

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

        mGovernorArray = getResources().getStringArray(R.array.governorsNames);
        mDescriptionArray = getResources().getStringArray(R.array.governorsDates);


        for(int i = 0; i < mGovernorArray.length; i++)  {

            String governor = mGovernorArray[i].substring(mGovernorArray[i].split("[\\{\\}]")[1].length()+2);
            String tag = mGovernorArray[i].split("[\\{\\}]")[1];
            String desc = mDescriptionArray[i];

            Governor govobj = new Governor(tag, governor, desc);
            mGovernorsTable.put(tag, govobj);
        }

        List list_keys = new LinkedList(mGovernorsTable.keySet());
        List sorted_keys = new LinkedList();
        sorted_keys.add(list_keys.get(0));
        sorted_keys.add(list_keys.get(5));
        sorted_keys.add(list_keys.get(3));
        sorted_keys.add(list_keys.get(2));
        sorted_keys.add(list_keys.get(1));
        sorted_keys.add(list_keys.get(6));
        sorted_keys.add(list_keys.get(4));
        LinkedHashSet<String> dateKeysSet = new LinkedHashSet(sorted_keys);

        for (String tagb : dateKeysSet) {

            Collection<Governor> values = mGovernorsTable.get(tagb);

            governorstrs.clear();
            for (Governor gov : values) {
                governorstrs.add(gov.getDescription() + " - " + gov.getGovernor());
            }

            String[] govArr = new String[governorstrs.size()];
            govArr = governorstrs.toArray(govArr);

            addTab(
                    tagb,
                    new TabListFragment().setCharacters(govArr));


        }

    }


}
