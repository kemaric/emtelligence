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
        GraphViewData[] data;
        /*
         * Get the Journal Entries from
         */
        
        ArrayList<JournalEntry> JournalList = new ArrayList<JournalEntry>();
        JournalEntry entry = null, nextEntry = null;

        if(JournalList.size() != 0){
	        ArrayList<String> dates = new ArrayList<String>();
	        String date = "", tempDate = "";
	        
	        int num = JournalList.size(); //Make number of entries
	        ArrayList<GraphViewData> dataList = new ArrayList<GraphViewData>();
	        int sum = 0, index = 0;
	        for (int day=0; index<num; day++) { //Loop through all dates
	        	entry = JournalList.get(index);
	        	sum = entry.getEmotion().getEv().getValue();
	        	date = "" + entry.getEntryDate().getMonth() + "/" + entry.getEntryDate().getDate() +
	        			"/" + entry.getEntryDate().getYear();
	        	date = date.substring(4, 11) + date.substring(24);
	        	dates.add(date);
	        	tempDate = date;
	            while(tempDate.equals(date) && index < num){
	            	index += 1;
	            	entry = JournalList.get(index);
	            	tempDate = "" + entry.getEntryDate().getMonth() + "/" + entry.getEntryDate().getDate() +
	            			"/" + entry.getEntryDate().getYear();
	            	if(tempDate.equals(date)){
	            		sum += entry.getEmotion().getEv().getValue();
	            	}
	            }
	            dataList.add(new GraphViewData(day, sum));
	        }
	        
	        data = (GraphViewData[]) dataList.toArray();
        }
        else{
	        //For testing
	        int num = 150;
	        data = new GraphViewData[num];
	        for (int i=0; i<num; i++) {
	                data[i] = new GraphViewData(i, i%4);
	        }
        }
       // graph with dynamically generated horizontal and vertical labels
        LineGraphView graphView = new LineGraphView(
    	      this // context
    	      , "Emotional History" // heaidng
    	);
        graphView.setDrawDataPoints(true);
        graphView.setDataPointsRadius(15f);
         
        // add data
        graphView.addSeries(new GraphViewSeries(data));
        // set view port, start=2, size=40
        graphView.setViewPort(2, 40);
        graphView.setScrollable(true);
        LinearLayout layout = (LinearLayout) findViewById(R.id.graph1);
        layout.addView(graphView);
    }
}