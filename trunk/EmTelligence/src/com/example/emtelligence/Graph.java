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
import com.jjoe64.graphview.CustomLabelFormatter;

public class Graph extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph); //Not sure what to put here
        GraphViewData[] data;
        int num = 0;
        int sum = 0, minSum = 0, maxSum = 0;
        /*
         * Get the Journal Entries from
         */
        
        ArrayList<JournalEntry> JournalList = new ArrayList<JournalEntry>();
        JournalEntry entry = null, nextEntry = null;
        ArrayList<String> dates = new ArrayList<String>();
        String date = "", tempDate = "";

        if(JournalList.size() != 0){
	        
	        num = JournalList.size(); //Make number of entries
	        ArrayList<GraphViewData> dataList = new ArrayList<GraphViewData>();
	        int index = 0;
	        for (int day=0; index<num; day++) { //Loop through all dates
	        	entry = JournalList.get(index);
	        	sum = entry.getEmotion().getEv().getValue();
	        	date = "" + entry.getEntryDate().getMonth() + "/" + entry.getEntryDate().getDate();
	        	date = date.substring(4, 11) + date.substring(24);
	        	dates.add(date);
	        	tempDate = date;
	            while(tempDate.equals(date) && index < num){
	            	index += 1;
	            	entry = JournalList.get(index);
	            	tempDate = "" + entry.getEntryDate().getMonth() + "/" + entry.getEntryDate().getDate();
	            	if(tempDate.equals(date)){
	            		sum += entry.getEmotion().getEv().getValue();
	            	}
	            }
	            minSum = Math.min(sum, minSum);
	            maxSum = Math.max(sum, maxSum);
	            dataList.add(new GraphViewData(day, sum));
	        }
	        
	        data = (GraphViewData[]) dataList.toArray();
        }
        else{
	        //For testing
	        num = 150;
	        data = new GraphViewData[num];
	        for (int i=0; i<num; i++) {
	        	sum = (int) (Math.random() * 20.0 - 10.0);
	            minSum = Math.min(sum, minSum);
	            maxSum = Math.max(sum, maxSum);
	            data[i] = new GraphViewData(i, sum);
	            date = "0"+(i/30 + 1)+"/";
	            if((i%30+1) < 10)
	            	date = date + "0";
	            date = date + (i%30 + 1);
	            dates.add(date);
	        }
        }
       // graph with dynamically generated horizontal and vertical labels
        String heading = "Emotional History";
        if(JournalList.size() == 0) heading = "Example " + heading;
        LineGraphView graphView = new LineGraphView(
    	      this // context
    	      , heading // heaidng
    	);
        
        final ArrayList<String> dates2 = dates;
        graphView.setCustomLabelFormatter(new CustomLabelFormatter() {
           public String formatLabel(double value, boolean isValueX) {
              if (isValueX) {
            	  return dates2.get((int) value);
              }
              return null; // let graphview generate Y-axis label for us
           }
        });
        graphView.setDrawDataPoints(true);
        //graphView.setDataPointsRadius(15f);
        
        
        // add data
        graphView.addSeries(new GraphViewSeries(data));
        // set view port, start=2, size=40
        graphView.setManualYAxisBounds(maxSum, minSum);
        
        graphView.setViewPort(2, 6);
        graphView.setScrollable(true);
        graphView.setScalable(true);
        graphView.getGraphViewStyle().setNumHorizontalLabels(3);
        
        int vertLabels = maxSum-minSum;
        while(vertLabels > 10) vertLabels /= 2;
        graphView.getGraphViewStyle().setNumVerticalLabels(vertLabels + 1);
        
        LinearLayout layout = (LinearLayout) findViewById(R.id.graph1);
        layout.addView(graphView);
    }
}