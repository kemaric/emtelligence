package com.example.emtelligence;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;

import com.jjoe64.graphview.BarGraphView;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

public class Graph extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph); //Not sure what to put here
        
        /*
         * Get the Journal Entries from
         */
/*
 * For testing purposes  
        ArrayList<JournalEntry> JournalList = new ArrayList<JournalEntry>();
        JournalEntry entry = null, nextEntry = null;

        
        ArrayList<String> dates = new ArrayList<String>();
        String date = "", tempDate = "";
        
        int num = JournalList.size(); //Make number of entries
        ArrayList<GraphViewData> dataList = new ArrayList<GraphViewData>();
        int sum = 0, index = 0;
        for (int day=0; index<num; day++) { //Loop through all dates
        	entry = JournalList.get(index);
        	sum = entry.getScore();
        	date = entry.getEntryDate().toString();
        	date = date.substring(4, 11) + date.substring(24);
        	dates.add(date);
        	tempDate = date;
            while(tempDate.equals(date) && index < num){
            	index += 1;
            	entry = JournalList.get(index);
            	tempDate = entry.getEntryDate().toString();
            	tempDate = date.substring(4, 11) + date.substring(24);
            	if(tempDate.equals(date)){
            		sum += entry.getScore();
            	}
            }
            dataList.add(new GraphViewData(day, sum));
        }

        GraphViewData[] data = (GraphViewData[]) dataList.toArray();
*/
        //For testing
        // draw sin curve
        int num = 150;
        GraphViewData[] data = new GraphViewData[num];
        for (int i=0; i<num; i++) {
                data[i] = new GraphViewData(i, i%4 - 2);
        }
        
       // graph with dynamically generated horizontal and vertical labels
        GraphView graphView;
        graphView = new LineGraphView(
            this
            , "GraphViewDemo"
        );
         
        // add data
        graphView.addSeries(new GraphViewSeries(data));
        // set view port, start=2, size=40
        graphView.setViewPort(2, 40);
        graphView.setScrollable(true);
        LinearLayout layout = (LinearLayout) findViewById(R.id.graph1);
        layout.addView(graphView);
    }
}