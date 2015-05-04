package com.example.emtelligence;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph2 extends Activity {
	private Button switchGraph;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph2);
        
        DataPoint[] posDataList, negDataList;
        GraphView graphView = (GraphView) findViewById(R.id.graph2);

        int num = 0;
        int sum = 0, minSum = 0, maxSum = 0;
        /*
         * Get the Journal Entries from
         */
        Log.d("Feteching: ", "Fetching Data ..");
        JournalDatabaseAdaper sqlhelper = new JournalDatabaseAdaper(this);
        ArrayList<JournalEntry> JournalList;
        JournalList = (ArrayList<JournalEntry>) sqlhelper.getJournalEntries(null,null,null);
        String date = "", tempDate = "";
        HashMap<Date,Integer> negEmotionDates = new HashMap<Date,Integer>();
        HashMap<Date,Integer> posEmotionDates = new HashMap<Date,Integer>();
        if(JournalList.size() != 0){

            //////NEW BLOCK/////
            int currScore,prevTotal;
            for(JournalEntry jEntry: JournalList){
                currScore = jEntry.getEmotion().getEv().getValue();
                if(currScore > 0){
                    if(posEmotionDates.containsKey(jEntry.getEntryDate())) {
                        prevTotal = posEmotionDates.get(jEntry.getEntryDate());
                        posEmotionDates.put(jEntry.getEntryDate(),++prevTotal);
                    }else{
                        posEmotionDates.put(jEntry.getEntryDate(),1);
                    }
                }else{
                    if(negEmotionDates.containsKey(jEntry.getEntryDate())) {
                        prevTotal = negEmotionDates.get(jEntry.getEntryDate());
                        negEmotionDates.put(jEntry.getEntryDate(),++prevTotal);
                    }else{
                        negEmotionDates.put(jEntry.getEntryDate(),1);
                    }
                }
            }

	        posDataList = new DataPoint[posEmotionDates.size()];
	        negDataList = new DataPoint[negEmotionDates.size()];

            List<Map.Entry<Date,Integer>> posPointList = new ArrayList<Map.Entry<Date, Integer>>(posEmotionDates.entrySet());
            List<Map.Entry<Date,Integer>> negPointList = new ArrayList<Map.Entry<Date, Integer>>(negEmotionDates.entrySet());

            Collections.sort(posPointList, new Comparator<Map.Entry<Date, Integer>>() {
                @Override
                public int compare(Map.Entry<Date, Integer> lhs, Map.Entry<Date, Integer> rhs) {
                    return lhs.getKey().compareTo(rhs.getKey());
                }
            });
            Collections.sort(negPointList, new Comparator<Map.Entry<Date, Integer>>() {
                @Override
                public int compare(Map.Entry<Date, Integer> lhs, Map.Entry<Date, Integer> rhs) {
                    return lhs.getKey().compareTo(rhs.getKey());
                }
            });

            int index = 0;
            for(Map.Entry<Date,Integer> pt:posPointList)
                posDataList[index++] = new DataPoint(pt.getKey(), pt.getValue());
            index=0;
            for(Map.Entry<Date,Integer> pt:negPointList)
                negDataList[index++] = new DataPoint(pt.getKey(), pt.getValue());

        }
        //For testing
        else{
	        num = 90;
	        //data = new GraphViewData[num];

            posDataList = new DataPoint[num];
            negDataList = new DataPoint[num];
	        for (int i=0; i<num; i++) {
	        	int numPosEnt = (int) (Math.random() * 20.0 - 10.0);
	        	int numNegEnt = (int) (Math.random() * 20.0 - 10.0);

                Calendar currDate = Calendar.getInstance();
                currDate.add(Calendar.DATE,(-(num-i)));
	            Date d1 = currDate.getTime();

                posDataList[i]= new DataPoint(d1,numPosEnt);
                negDataList[i]= new DataPoint(d1,numNegEnt);

	        }
        }
        /*final ArrayList<String> dates2 = dates;
        int start = dates.size() - 7;
        if(start < 0) start = 0;
        int numDisplay = 6;
        if(dates.size() <= numDisplay) numDisplay = dates.size() -1;*/
        
        
        // graph with dynamically generated horizontal and vertical labels
        /* Graph for cumulative sum */ 
        String heading = "Cumulative Emotional History";
        if(JournalList.size() == 0) heading = "Example " + heading;
        LineGraphSeries<DataPoint> positiveSeries = new LineGraphSeries<DataPoint>(posDataList);
        BarGraphSeries<DataPoint> negativeSeries = new BarGraphSeries<DataPoint>(negDataList);
        //LineGraphSeries<DataPoint> neutralSeries = new LineGraphSeries<DataPoint>(posDataList);

        graphView.addSeries(positiveSeries);
        graphView.addSeries(negativeSeries);
        //graphView.addSeries(neutralSeries);
        graphView.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getApplicationContext(),
                DateFormat.getDateInstance(DateFormat.SHORT)));
//        graphView.setCustomLabelFormatter(new CustomLabelFormatter() {
//           public String formatLabel(double value, boolean isValueX) {
//              if (isValueX) {
//            	  return dates2.get((int) value);
//              }
//              return null; // let graphview generate Y-axis label for us
//           }
//        });
//        graphView.setDrawDataPoints(true);
        //graphView.setDataPointsRadius(15f);
        graphView.setTitle(heading);
        
        // add data
        // set view port, start=2, size=40
        //graphView.getGridLabelRenderer().setNumHorizontalLabels(3);
        graphView.getViewport().setMaxY(10);
        graphView.getViewport().setMinY(0);
        graphView.getViewport().setScalable(true);
        graphView.getViewport().setScrollable(true);


//        /* Layout stuffs */ graphView.setManualYAxisBounds(maxSum, minSum);
//
//        graphView.setViewPort(start, numDisplay);
//        graphView.setScrollable(true);
//        graphView.setScalable(true);
//        graphView.getGraphViewStyle().setNumHorizontalLabels(3);
//
        int vertLabels = maxSum-minSum;
        while(vertLabels > 10) vertLabels /= 2;
//        graphView.getGraphViewStyle().setNumVerticalLabels(vertLabels + 1);

        switchGraph = (Button)findViewById(R.id.switchGraphButton2);
        switchGraph.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent graphActivity = new Intent(Graph2.this,Graph.class);
				startActivity(graphActivity);
			}
		});
        //GraphView layout = (GraphView) findViewById(R.id.graph2);

        //layout.addView(graphView);
    }
}