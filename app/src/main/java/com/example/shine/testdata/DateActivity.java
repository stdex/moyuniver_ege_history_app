package com.example.shine.testdata;

import android.support.v4.app.Fragment;

import com.actionbarsherlock.app.ActionBar;

import com.example.shine.testdata.models.Date;
import com.example.shine.testdata.tabsframework.AbstractTabStackNavigationActivity;
import com.example.shine.testdata.TabListFragment;
import com.example.shine.testdata.TabStringFragment;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;


public class DateActivity extends AbstractTabStackNavigationActivity {

    String[] mDateArray;
    String[] mEventsArray;
    Multimap<String, Date> mDateTable = ArrayListMultimap.create();
    public static List<String> datestrs = new ArrayList<String>();

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

      mDateArray = getResources().getStringArray(R.array.dates);
      mEventsArray = getResources().getStringArray(R.array.events);


      for(int i = 0; i < mDateArray.length; i++)  {

          String year = mDateArray[i].substring(mDateArray[i].split("[\\{\\}]")[1].length()+2);
          String century = mDateArray[i].split("[\\{\\}]")[1];
          String event = mEventsArray[i];

          Date date = new Date(century, year, event);
          mDateTable.put(century, date);
      }

      List list_keys = new LinkedList(mDateTable.keySet());
      Collections.sort(list_keys);
      list_keys.add(0, list_keys.get(list_keys.size()-1));
      LinkedHashSet<String> dateKeysSet = new LinkedHashSet(list_keys);

      for (String century : dateKeysSet) {

            Collection<Date> values = mDateTable.get(century);

            datestrs.clear();
            for (Date date : values) {
                datestrs.add(date.getYear() + " - " + date.getEvent());
            }

          String[] dateArr = new String[datestrs.size()];
          dateArr = datestrs.toArray(dateArr);

          addTab(
                  century,
                  new TabListFragment().setCharacters(dateArr));


      }

  }


}
