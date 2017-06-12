package com.rogersandsons.eric.welltagnumberlookup;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import java.io.*;


public class WellLookUp extends AppCompatActivity {
    private static Context mContext;
    public void clearText(View view)
    {
        TextView tagNumID = (TextView) findViewById(R.id.TagNumID);
        tagNumID.setText("Tag Number: ");
        TextView drillersNameID = (TextView) findViewById (R.id.DrillersNameID);
        drillersNameID.setText("Driller's Name: ");
        TextView completeDateID = (TextView) findViewById(R.id.CompleteDate);
        completeDateID.setText("Complete Date: ");
        TextView casingDiamID = (TextView) findViewById(R.id.CasingDiam);
        casingDiamID.setText("Casing Diameter: ");
        TextView totalDepthID = (TextView) findViewById(R.id.TotalDepthID);
        totalDepthID.setText("Total Depth: ");
        TextView casingDepthID = (TextView) findViewById(R.id.CasingDepthID);
        casingDepthID.setText("Casing Depth: ");
        TextView casingTypeID = (TextView) findViewById(R.id.CasingTypeID);
        casingTypeID.setText("Casing Type: ");
        TextView pumpingRateID = (TextView) findViewById(R.id.PumpRateID);
        pumpingRateID.setText("Pumping Rate: ");
        TextView levelBeforeID = (TextView) findViewById(R.id.LevelBeforeID);
        levelBeforeID.setText("Level Before: ");
        TextView levelDuringID = (TextView) findViewById(R.id.LevelDuringID);
        levelDuringID.setText("Level During: ");
        TextView columnLengthID = (TextView) findViewById(R.id.ColumnLengthID);
        columnLengthID.setText("Column Length: ");
    }

    public void LookUp(View view) {
        String countyCode;
        String[] countyCodes = {"AA", "AL", "BA", "BC", "CA", "CE", "CH", "CL", "CO", "DO", "FR", "GA",
                "HA", "HO", "KE", "MO", "PG", "QA", "SM", "SO", "TA", "WA", "WI", "WO"};
        String[] wellDataSet;
        //String[] casingTypeList = {"PL", "ST"};
        String wellData = null;
        EditText tagNum = (EditText) findViewById(R.id.editText);
        String TagNum = tagNum.getText().toString();
        if (TagNum != null && !TagNum.equals("") && !TagNum.equals(" ")) {
            countyCode = TagNum.substring(0, 2).toUpperCase();
            String tagNumbers = TagNum.substring(2);
            TagNum = countyCode + tagNumbers;
            forEachLoop:
            for (String cC: countyCodes) {
                if (countyCode.equals(cC)) {
                    try {
                        InputStream is = getContext().getAssets().open(countyCode + ".txt");
                        String line;
                        BufferedReader br = new BufferedReader(new InputStreamReader(is));
                        while ((line = br.readLine()) != null) {
                            if (line.substring(0, 8).equals(TagNum)) {
                                wellData = line;
                                br.close();
                                is.close();
                                break forEachLoop;
                            } else {
                                wellData = "";
                            }

                        }
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                        return;
                    }


                }
            }
            if (wellData != null) {

                wellData = wellData.replaceAll("\t\t", "\t ");
                String[] wellDataTemp;
                wellDataTemp  = wellData.split("\t");
                wellDataSet = wellDataTemp.clone();
                for (int x = 0; x < wellDataSet.length; x++) {
                    if (wellDataSet[x] == null || wellDataSet[x].equals(" ")) {
                        wellDataSet[x] = "Not Available";
                    }
//                    if (wellDataSet[x].equals(casingTypeList[0])) {
//                        wellDataSet[x] = "Plastic";
//                    } else if (wellDataSet[x].equals(casingTypeList[1])) {
//                        wellDataSet[x] = "Steel";
//                    }
                }
                if (wellDataSet[0].equals("")) wellDataSet[0] = TagNum;
                TextView tagNumID = (TextView) findViewById(R.id.TagNumID);
                tagNumID.setText("Tag Number: " + wellDataSet[0]);
                TextView drillersNameID = (TextView) findViewById(R.id.DrillersNameID);
                drillersNameID.setText("Driller's Name: " + wellDataSet[1]);
                if(!wellDataSet[2].equals("Not Available")) {
                    wellDataSet[2] = wellDataSet[2].substring(wellDataSet[2].length() - 4);
                }
//                if(wellDataSet[2].length() == 8) {
//                    if (!wellDataSet[2].equals("Not Available")) {
//                        wellDataSet[2] = wellDataSet[2].substring(3);
//                    }
//                }
//                else if(wellDataSet[2].length() == 9) {
//                    if (!wellDataSet[2].equals("Not Available")) {
//                        wellDataSet[2] = wellDataSet[2].substring(4);
//                    }
//                }
//                else if(wellDataSet[2].length() == 10) {
//                    if (!wellDataSet[2].equals("Not Available")) {
//                        wellDataSet[2] = wellDataSet[2].substring(5);
//                    }
//                }
                TextView completeDateID = (TextView) findViewById(R.id.CompleteDate);
                completeDateID.setText("Complete Date: " + wellDataSet[2]);
                TextView casingDiamID = (TextView) findViewById(R.id.CasingDiam);
                casingDiamID.setText("Casing Diameter: " + wellDataSet[5]);
                TextView totalDepthID = (TextView) findViewById(R.id.TotalDepthID);
                totalDepthID.setText("Total Depth: " + wellDataSet[3]);
                TextView casingDepthID = (TextView) findViewById(R.id.CasingDepthID);
                casingDepthID.setText("Casing Depth: " + wellDataSet[6]);
                TextView casingTypeID = (TextView) findViewById(R.id.CasingTypeID);
                casingTypeID.setText("Casing Type: " + wellDataSet[4]);
                TextView pumpingRateID = (TextView) findViewById(R.id.PumpRateID);
                pumpingRateID.setText("Pumping Rate: " + wellDataSet[7]);
                TextView levelBeforeID = (TextView) findViewById(R.id.LevelBeforeID);
                levelBeforeID.setText("Level Before: " + wellDataSet[8]);
                TextView levelDuringID = (TextView) findViewById(R.id.LevelDuringID);
                levelDuringID.setText("Level During: " + wellDataSet[9]);
                TextView columnLengthID = (TextView) findViewById(R.id.ColumnLengthID);
                columnLengthID.setText("Column Length: " + wellDataSet[10]);
                tagNum.setText("");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_well_look_up);
        mContext = this;
    }
    public static Context getContext(){
        return mContext;
    }
}
