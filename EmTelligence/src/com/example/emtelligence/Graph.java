package com.example.emtelligence;

import android.app.Activity;
import android.content.Intent;
import android.database.DatabaseErrorHandler;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph extends Activity {
	private Button switchGraph;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph);
        GraphView graphView = (GraphView) findViewById(R.id.graph1);
        DataPoint[] dataList;
        int num = 0;
        int sum = 0, minSum = 0, maxSum = 0;
        /*
         * Get the Journal Entries from
         */
        JournalDatabaseAdaper sqlhelper = new JournalDatabaseAdaper(this);
       // if(c.equals(null))
        	//db.
      //  c.moveToFirst();
       // c.getString(c.getColumnIndexOrThrow(columnName));
       // db.u
        Log.d("Feteching: ", "Fetching Data .."); 
        ArrayList<JournalEntry> JournalList;
        //JournalEntry entry = null;
        Set<String> dates = new HashSet<String>();
        String date = "", tempDate = "";
      JournalList = (ArrayList<JournalEntry>) sqlhelper.getJournalEntries(null,null,null);
        	
      //Creating a hashmap of the past entries to display on the graph.
        HashMap<Date,Integer> emotionValueDates = new HashMap<Date,Integer>();
     
        if(JournalList.size() != 0){
	        
	        num = JournalList.size(); //Make number of entries
	        //ArrayList<JournalEntry> dataList = new ArrayList<JournalEntry>();
	        //Calendar cal = Calendar.getInstance();
	        //int index = 0;
	        for(JournalEntry entry: JournalList){
//	        	cal.setTime(entry.getEntryDate());
//	        	date = "" + cal.get(Calendar.MONTH) + "/" +
//	        			cal.get(Calendar.DATE) +"/"+
//	        			cal.get(Calendar.YEAR);
//	        	int score = pastXdays.get(date);
//	        	pastXdays.put(date,score+entry.getEmotion().getEv().getValue());
                int entryScore = entry.getEmotion().getEv().getValue();
                if(emotionValueDates.containsKey(entry.getEntryDate())){
                    int prevDateScore = emotionValueDates.get(entry.getEntryDate());
                    emotionValueDates.put(entry.getEntryDate(),prevDateScore+entryScore);
                }else{
                    emotionValueDates.put(entry.getEntryDate(),entryScore);
                }
	        }
	        List<Map.Entry<Date,Integer>> entryDates = new ArrayList<Map.Entry<Date, Integer>>(emotionValueDates.entrySet());
	        Collections.sort(entryDates, new Comparator<Map.Entry<Date, Integer>>() {
                @Override
                public int compare(Map.Entry<Date, Integer> lhs, Map.Entry<Date, Integer> rhs) {
                    return lhs.getKey().compareTo(rhs.getKey());
                }
            });

	        
//	        for (int day=0; day<num; day++) { //Loop through all dates
//	        	entry = JournalList.get(day);
//	        	sum = entry.getEmotion().getEv().getValue();
//	        	cal.setTime(entry.getEntr
            int index = 0;
            dataList = new DataPoint[entryDates.size()];
            for(Map.Entry<Date,Integer> entryDate: entryDates){
                dataList[index++] = new DataPoint(entryDate.getKey(),entryDate.getValue());
            }
                //dataList.add(new GraphViewData());yDate());
//	        	date = "" + entry.getEntryDate().getMonth() + "/" + entry.getEntryDate().getDate();
//	        	//date = date.substring(4, 11) + date.substring(24);
//	        	dates.add(date);
//	        	tempDate = date;
//	            while(tempDate.equals(date) && index <= num-1){
//	            	entry = JournalList.get(index);
//	            	cal.setTime(entry.getEntryDate());
//	            	tempDate = "" + cal.get(Calendar.MONTH) + "/" + cal.get(Calendar.DATE);
//	            	if(tempDate.equals(date)){
//	            		sum += entry.getEmotion().getEv().getValue();
//	            	}
//	            	index += 1;
//
//	            }
//	            minSum = Math.min(sum, minSum);
//	            maxSum = Math.max(sum, maxSum);
//	            dataList.add(new GraphViewData(day, sum));
//	        }
	        
	       // data = (GraphViewData[]) dataList.toArray();
	    }
        else{
	        //For testing
	        num = 150;
	        dataList = new DataPoint[num];
	        for (int i=0; i<num; i++) {
	        	sum = (int) (Math.random() * 20.0 - 10.0);
	            minSum = Math.min(sum, minSum);
	            maxSum = Math.max(sum, maxSum);
                Calendar currDate = Calendar.getInstance();
                currDate.add(Calendar.DATE,(-(num-i)));
                Date d1 = currDate.getTime();

                dataList[i]= new DataPoint(d1,sum);

            }
        }
        final Set<String> dates2 = dates;
        int start = dates.size() - 7;
        if(start < 0) start = 0;
        int numDisplay = 6;
        if(dates.size() <= numDisplay) numDisplay = dates.size() -1;
        
        // graph with dynamically generated horizontal and vertical labels
        /* Graph for sum in single day */ 
        String heading = "Emotional History";

        if(JournalList.size() == 0) heading = "Example " + heading;
        LineGraphSeries graphSeries = new LineGraphSeries(dataList);


//        graphView.setCustomLabelFormatter(new CustomLabelFormatter() {
//           public String formatLabel(double value, boolean isValueX) {
//              if (isValueX) {
//            	  return (String) dates2.toArray()[(int) value];
//              }
//              return null; // let graphview generate Y-axis label for us
//           }
//        });
        //graphView.setDrawDataPoints(true);
        //graphView.setDataPointsRadius(15f);
        
        
        // add data
        graphView.addSeries(graphSeries);
        graphView.setTitle(heading);
        graphView.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getApplicationContext(), DateFormat.getDateInstance(DateFormat.SHORT)));
        graphView.getViewport().setScrollable(true);
        //graphView.addSeries(new GraphViewSeries(data));
        // set view port, start=2, size=40
        //graphView.setManualYAxisBounds(maxSum, minSum);
        graphView.getViewport().setMaxY(10);
        graphView.getViewport().setMinY(-10);
        //Log.d("Info: ", "MinSum:" + minSum+" MaxSum:"+maxSum);
        graphView.getViewport().setScalable(true);
//        graphView.setViewPort(start, numDisplay);
//        graphView.setScrollable(true);
//        graphView.setScalable(true);
        //graphView.getGridLabelRenderer().setNumHorizontalLabels();

        int vertLabels = maxSum-minSum;
        while(vertLabels > 10) vertLabels /= 2;
        //graphView.getGraphViewStyle().setNumVerticalLabels(vertLabels + 1);
        graphView.getGridLabelRenderer().setNumVerticalLabels(5);
        
        /* Layout stuffs */
        switchGraph = (Button)findViewById(R.id.switchGraphButton1);
        switchGraph.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent graphActivity = new Intent(Graph.this,Graph2.class);
				startActivity(graphActivity);
			}
		});
        //GraphView layout = (GraphView) findViewById(R.id.graph1);

    }
    
    public static ArrayList<String> getLastNDays(int numDays){
    	ArrayList<String> dates = new ArrayList<String>();
    	if (numDays > 0){
    		 Calendar currentDate = Calendar.getInstance();
    		 while(numDays > 0){
    			 dates.add(""+currentDate.get(Calendar.MONTH)+"/"+currentDate.get(Calendar.DAY_OF_MONTH));
    			 currentDate.add(Calendar.DATE, -1);
    			 numDays--;
    		 }

//    		 
//    	     int currMonthMin = currentDate.getMinimum(currentDate.MONTH); //The minimum
//    	     if(currentDate.getTime().getDate() - currMonthMin  >= numDays){
//    	    	 
//    	     }else{
//    	    	int numMonths = numDays % 30;
//    	    	int tmpMonth = currentDate.getTime().getMonth();
//    	    	int tmpDate = currentDate.getTime().getDate();
//    	    	String day;
//    	    	while(numMonths > 0 ){
//    	    		day = tmpMonth+"/"+tmpDate;
//    	    		dates.add(day);
//    	    		if(tmpDate == 1){
//    	    			
//    	    			//TO DO
//    	    			//tmpMonth = currentDate.set
//    	    		}
//    	    		
//    	    	}
//    	     }
    	}
    	return dates;
    }
}