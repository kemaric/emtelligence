package com.example.emtelligence;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;

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
        
        int num = 0; //Make number of entries
        GraphViewData[] data = new GraphViewData[num];
        int sum = 0, date = 0; 
        for (int i=0; i<num; i++) { //Loop through all dates
           //sum = sum of all entries on that day
           data[date] = new GraphViewData(date, sum);
        }


       // graph with dynamically genereated horizontal and vertical labels
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