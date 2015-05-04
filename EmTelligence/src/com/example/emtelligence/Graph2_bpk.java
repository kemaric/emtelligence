//package com.example.emtelligence;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.LinearLayout;
//
//import com.jjoe64.graphview.CustomLabelFormatter;
//import com.jjoe64.graphview.GraphView.GraphViewData;
//import com.jjoe64.graphview.GraphViewSeries;
//import com.jjoe64.graphview.LineGraphView;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//
//public class Graph2_bpk extends Activity {
//	private Button switchGraph;
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.graph2);
//
//        GraphViewData[] data;
//        int num = 0;
//        int sum = 0, minSum = 0, maxSum = 0;
//        /*
//         * Get the Journal Entries from
//         */
//        ArrayList<JournalEntry> JournalList = new ArrayList<JournalEntry>();
//        JournalEntry entry = null;
//        ArrayList<String> dates = new ArrayList<String>();
//        String date = "", tempDate = "";
//        HashMap<Date,Integer> emotionValueDates = new HashMap<Date,Integer>();
//        if(JournalList.size() != 0){
//
//            //////NEW BLOCK/////
//            for(JournalEntry jEntry: JournalList){
//                int currScore = jEntry.getEmotion().getEv().getValue();
//                if(emotionValueDates.containsKey(jEntry.getEntryDate())) {
//                    int prevDateScore = emotionValueDates.get(jEntry.getEntryDate());
//                    emotionValueDates.put(jEntry.getEntryDate(),prevDateScore+currScore);
//                }else{
//                    emotionValueDates.put(jEntry.getEntryDate(),currScore);
//                }
//
//            }
//            //////NEW BLOCK/////
//
//
//
//
//
//	        num = JournalList.size(); //Make number of entries
//	        ArrayList<GraphViewData> dataList = new ArrayList<GraphViewData>();
//	        ArrayList<GraphViewData> dataListTotal = new ArrayList<GraphViewData>();
//	        int index = 0;
//	        for (int day=0; index<num; day++) { //Loop through all dates
//	        	entry = JournalList.get(index);
//	        	sum += entry.getEmotion().getEv().ordinal();
//	        	date = "" + entry.getEntryDate().getMonth() + "/" + entry.getEntryDate().getDate();
//	        	//date = date.substring(4, 11) + date.substring(24);
//
//	        	dates.add(date);
//	        	tempDate = date;
//	            while(tempDate.equals(date) && index < num){
//	            	index += 1;
//	            	entry = JournalList.get(index);
//	            	tempDate = "" + entry.getEntryDate().getMonth() + "/" + entry.getEntryDate().getDate();
//	            	if(tempDate.equals(date)){
//	            		sum += entry.getEmotion().getEv().ordinal();
//	            	}
//	            }
//	            minSum = Math.min(sum, minSum);
//	            maxSum = Math.max(sum, maxSum);
//	            dataList.add(new GraphViewData(day, sum));
//	        }
//
//	        data = (GraphViewData[]) dataList.toArray();
//
//        }
//        //For testing
//        else{
//	    num = 150;
//	        data = new GraphViewData[num];
//	        for (int i=0; i<num; i++) {
//	        	sum += (int) (Math.random() * 20.0 - 10.0);
//	            minSum = Math.min(sum, minSum);
//	            maxSum = Math.max(sum, maxSum);
//	            data[i] = new GraphViewData(i, sum);
//	            date = "0"+(i/30 + 1)+"/";
//	            if((i%30+1) < 10)
//	            	date = date + "0";
//	            date = date + (i%30 + 1);
//	            dates.add(date);
//	        }
//        }
//        final ArrayList<String> dates2 = dates;
//        int start = dates.size() - 7;
//        if(start < 0) start = 0;
//        int numDisplay = 6;
//        if(dates.size() <= numDisplay) numDisplay = dates.size() -1;
//
//
//        // graph with dynamically generated horizontal and vertical labels
//        /* Graph for cumulative sum */
//        String heading = "Cumulative Emotional History";
//        if(JournalList.size() == 0) heading = "Example " + heading;
//        LineGraphView graphView = new LineGraphView(
//    	      this // context
//    	      , heading // heaidng
//    	);
//
//        graphView.setCustomLabelFormatter(new CustomLabelFormatter() {
//           public String formatLabel(double value, boolean isValueX) {
//              if (isValueX) {
//            	  return dates2.get((int) value);
//              }
//              return null; // let graphview generate Y-axis label for us
//           }
//        });
//        graphView.setDrawDataPoints(true);
//        //graphView.setDataPointsRadius(15f);
//
//
//        // add data
//        graphView.addSeries(new GraphViewSeries(data));
//        // set view port, start=2, size=40
//        graphView.setManualYAxisBounds(maxSum, minSum);
//
//        graphView.setViewPort(start, numDisplay);
//        graphView.setScrollable(true);
//        graphView.setScalable(true);
//        graphView.getGraphViewStyle().setNumHorizontalLabels(3);
//
//        int vertLabels = maxSum-minSum;
//        while(vertLabels > 10) vertLabels /= 2;
//        graphView.getGraphViewStyle().setNumVerticalLabels(vertLabels + 1);
//
//
//        /* Layout stuffs */
//        switchGraph = (Button)findViewById(R.id.switchGraphButton2);
//        switchGraph.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				Intent graphActivity = new Intent(Graph2_bpk.this,Graph.class);
//				startActivity(graphActivity);
//			}
//		});
//        LinearLayout layout = (LinearLayout) findViewById(R.id.graph2);
//        layout.addView(graphView);
//    }
//}