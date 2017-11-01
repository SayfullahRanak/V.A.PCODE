package com.example.ranak.vapcode.Ui.vap;

import android.content.Context;
import android.os.Vibrator;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.ranak.vapcode.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by ranak on 12/10/17.
 */

public class VAPCODE {

    public interface mainFragmentListener{
        public void getPassword(List<String> password);
    }



    private static List<int[]> rowList ;
    int t=0;
    private  static float prev_startX=0;
    private  static float prev_startY=0;
    private  static boolean can_off_vibrator=false;
    private  static boolean button_one_check=false;
    private  static boolean button_two_check=false;
    private  static boolean button_three_check=false;
    private  static boolean button_four_check=false;
    private  static float distance=0;
    private  static float initial_X=0;
    private  static float initial_Y=0;
    private  static int px=1;
    private  static float startX=0;
    private  static float startY = 0;
    private  static int butn=0;
    private  static int getinput=0;
    private  static long upperlimit = 900;       //maximum time duration of a single vibration
    private  static long lowerlimit = 300;        //minimum time duration of a single vibration
    private  static long random = 0;              // temporary value for random numbers
    private  static long time_start = 0;          // for getting the system start time
    private  static long time_end = 0;            //for getting the system start time
    private  static long val = 0;                 // for vibration counting alforithm
    private  static long prv_val = 0;
    private  static long vibration_count = 0;    // for vibration counting alforithm
    private  static long[] pattern = new long[22];  // array that will sent to vibration function , with the sequence (vibration duration and waiting time )
    private  static long[] testpattern = new long[22];
    private  static float[] coar;
    //UniformRealDistribution u = new UniformRealDistribution(lowerlimit, upperlimit);

    //references
    private  static Random x = new Random();       // creating random object reference
    private  static Vibrator vibrator;     // creating vibrator object reference



    protected static void SetUpPasswordAreaGettingResult(View passwordArea,Fragment_VAPCODE mainFrag,final Context mContext){


        final Fragment_VAPCODE mainFragment =  mainFrag;
        final DrawView drawView = (DrawView)passwordArea.findViewById(R.id.passwordarea);

        final float[] coar=drawView.getCoor();

        pattern[0] = 0;
        pattern[1] = 40;
        for (int j=0;j<16;j++)
        {
            Log.d("fr",""+coar[j]);
        }
        for (int i = 2; i < 20; i++) {

            pattern[i] = 800;   // waiting time
            i++;   // gong to the next index
            pattern[i] = 40; // vibration duration
        }
        final float drag= (float) (0.083*drawView.getPixelsOfScreen());
        final float displacement=drag-((float)0.025*drag);
        rowList = new ArrayList<int[]>();
        //touch listener for the first button (left top)




        drawView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
//                int action = event.getAction() & MotionEvent.ACTION_MASK;
                int action = event.getActionMasked();

                //Log.d("CV", "Action ["+action+"]");
                switch(action) {
                    case MotionEvent.ACTION_DOWN:{

                        Log.d("In action down ", System.currentTimeMillis()+"");
                        float x=event.getX();
                        float y=event.getY();
                        if((x>=coar[0]) && (x<=coar[2]) && (y>=coar[1]) && (y<=coar[3]) && button_one_check==false){
                            butn=1;
                            if(!can_off_vibrator){

                                can_off_vibrator=true;
                                vibrator = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
                                time_start = System.currentTimeMillis();  // catch the system time when the vibration is started
                                vibrator.vibrate(pattern, -1);
                            }
                        }
                        else if((x>=coar[4]) && (x<=coar[6]) && (y>=coar[5]) && (y<=coar[7]) && button_two_check==false)
                        {
                            butn=2;
                            if(!can_off_vibrator){

                                can_off_vibrator=true;
                                vibrator = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
                                time_start = System.currentTimeMillis();  // catch the system time when the vibration is started
                                vibrator.vibrate(pattern, -1);
                            }
                        }
                        else if((x>=coar[8]) && (x<=coar[10]) && (y>=coar[9]) && (y<=coar[11]) && button_three_check==false)
                        {
                            butn=3;
                            if(!can_off_vibrator){

                                can_off_vibrator=true;
                                vibrator = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
                                time_start = System.currentTimeMillis();  // catch the system time when the vibration is started
                                vibrator.vibrate(pattern, -1);
                            }
                        }
                        else if((x>=coar[12]) && (x<=coar[14]) && (y>=coar[13]) && (y<=coar[15]) && button_four_check==false)
                        {
                            butn=4;
                            if(!can_off_vibrator){

                                can_off_vibrator=true;
                                vibrator = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
                                time_start = System.currentTimeMillis();  // catch the system time when the vibration is started
                                vibrator.vibrate(pattern, -1);
                            }
                        }
                        break;
                    }
                    case MotionEvent.ACTION_MOVE : {
                        //textView.setText("get "+event.getX()+" "+back.getWidth());
                        Log.d("In action move ", System.currentTimeMillis()+"");
                        float diffrenceX=0;
                        float diffrenceY=0;
                        float x=event.getX();
                        float y=event.getY();
                        startX=x;
                        startY=y;

                        diffrenceX=Math.abs(startX-prev_startX);
                        diffrenceY=Math.abs(startY-prev_startY);
                        double A=((diffrenceX*diffrenceX)+(diffrenceY*diffrenceY));
                        distance=(float)(Math.sqrt(A));

                        if((x>=coar[0]) && (x<=coar[2]) && (y>=coar[1]) && (y<=coar[3]) && button_one_check==false)
                        {
                            //drawView.SetColor();
                            //drawView.changecolor1();


                            butn=1;

                            if(((prev_startX==startX) && (prev_startY==startY)) && can_off_vibrator==false)
                            {

                                //1.setText("he "+" "+startX+"d "+startY);
                                //textView1.setText("he "+drawView.w+" "+drawView.h);

                                can_off_vibrator=true;
                                vibrator = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
                                time_start = System.currentTimeMillis();  // catch the system time when the vibration is started
                                vibrator.vibrate(pattern, -1);
                                initial_X=startX;
                                initial_Y=startY;
                            }
                            else if((distance<drag-displacement)  && can_off_vibrator==false)
                            {
                                can_off_vibrator=true;
                                vibrator = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
                                time_start = System.currentTimeMillis();  // catch the system time when the vibration is started
                                vibrator.vibrate(pattern, -1);
                                initial_X=startX;
                                initial_Y=startY;

                            }
                            else
                            {
                                float diffrence_from_initial_X=Math.abs(startX-initial_X);
                                float diffrence_from_initial_Y=Math.abs(startY-initial_Y);
                                double B=(diffrence_from_initial_X*diffrence_from_initial_X)+(diffrence_from_initial_Y*diffrence_from_initial_Y);
                                float distance_from_initial=(float) (Math.sqrt(B));
                                if((distance_from_initial>=drag)  && can_off_vibrator==true)
                                {
                                    //textView.setText("else "+px++);
                                    vibrator.cancel();
                                    time_end = System.currentTimeMillis();  // catch the system time when the vibration is released
                                    long press_duration = (time_end - time_start);  // calculate the total vibarting time ( by calculating the diffrence between "when the vibration started and when vibartion end")
                                    NumberOfvibrationAndDataEntry(press_duration, pattern, butn); // vibration count
                                    can_off_vibrator=false;
                                    button_one_check=true;
                                    button_two_check=false;
                                    button_three_check=false;
                                    button_four_check=false;

                                }
                            }


                        }
                        else if((x>=coar[4]) && (x<=coar[6]) && (y>=coar[5]) && (y<=coar[7]) && button_two_check==false)
                        {

                            //drawView.SetColor();
                            //drawView.changecolor2();

                            butn=2;
                            if(((prev_startX==startX) && (prev_startY==startY)) && can_off_vibrator==false)
                            {

                                //textView1.setText("he "+" "+startX+"d "+startY);
                                //textView.setText("he "+drag);

                                can_off_vibrator=true;
                                vibrator = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
                                time_start = System.currentTimeMillis();  // catch the system time when the vibration is started
                                vibrator.vibrate(pattern, -1);
                                initial_X=startX;
                                initial_Y=startY;
                            }
                            else if((distance<drag-displacement)  && can_off_vibrator==false)
                            {
                                can_off_vibrator=true;
                                vibrator = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
                                time_start = System.currentTimeMillis();  // catch the system time when the vibration is started
                                vibrator.vibrate(pattern, -1);
                                initial_X=startX;
                                initial_Y=startY;

                            }
                            else
                            {
                                float diffrence_from_initial_X=Math.abs(startX-initial_X);
                                float diffrence_from_initial_Y=Math.abs(startY-initial_Y);
                                double B=(diffrence_from_initial_X*diffrence_from_initial_X)+(diffrence_from_initial_Y*diffrence_from_initial_Y);
                                float distance_from_initial=(float) (Math.sqrt(B));
                                if((distance_from_initial>=drag)  && can_off_vibrator==true)
                                {
                                    //textView.setText("else "+px++);
                                    vibrator.cancel();
                                    time_end = System.currentTimeMillis();  // catch the system time when the vibration is released
                                    long press_duration = (time_end - time_start);  // calculate the total vibarting time ( by calculating the diffrence between "when the vibration started and when vibartion end")
                                    NumberOfvibrationAndDataEntry(press_duration, pattern, butn); // vibration count
                                    can_off_vibrator=false;

                                    button_four_check=false;
                                    button_three_check=false;
                                    button_two_check=true;
                                    button_one_check=false;

                                }
                            }

                        }
                        else if((x>=coar[8]) && (x<=coar[10]) && (y>=coar[9]) && (y<=coar[11]) && button_three_check==false)
                        {

                            //drawView.SetColor();
                            //drawView.changecolor3();

                            butn=3;
                            if(((diffrenceX==startX) && (diffrenceY==startY)) && can_off_vibrator==false)
                            {

                                //textView1.setText("he "+" "+startX+"d "+startY);
                                //textView.setText("he "+drag);

                                can_off_vibrator=true;
                                vibrator = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
                                time_start = System.currentTimeMillis();  // catch the system time when the vibration is started
                                vibrator.vibrate(pattern, -1);
                                initial_X=startX;
                                initial_Y=startY;
                            }
                            else if((distance<drag-displacement)  && can_off_vibrator==false)
                            {
                                can_off_vibrator=true;
                                vibrator = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
                                time_start = System.currentTimeMillis();  // catch the system time when the vibration is started
                                vibrator.vibrate(pattern, -1);
                                initial_X=startX;
                                initial_Y=startY;

                            }
                            else
                            {
                                float diffrence_from_initial_X=Math.abs(startX-initial_X);
                                float diffrence_from_initial_Y=Math.abs(startY-initial_Y);
                                double B=(diffrence_from_initial_X*diffrence_from_initial_X)+(diffrence_from_initial_Y*diffrence_from_initial_Y);
                                float distance_from_initial=(float) (Math.sqrt(B));
                                if((distance_from_initial>=drag)  && can_off_vibrator==true)
                                {
                                    //textView.setText("else "+px++);
                                    vibrator.cancel();
                                    time_end = System.currentTimeMillis();  // catch the system time when the vibration is released
                                    long press_duration = (time_end - time_start);  // calculate the total vibarting time ( by calculating the diffrence between "when the vibration started and when vibartion end")
                                    NumberOfvibrationAndDataEntry(press_duration, pattern, butn); // vibration count
                                    can_off_vibrator=false;

                                    button_four_check=false;
                                    button_three_check=true;
                                    button_two_check=false;
                                    button_one_check=false;
                                }
                            }

                        }
                        else if((x>=coar[12]) && (x<=coar[14]) && (y>=coar[13]) && (y<=coar[15]) && button_four_check==false)
                        {

                            //drawView.SetColor();
                            //drawView.changecolor4();

                            butn=4;

                            if(((diffrenceX==startX) && (diffrenceY==startY)) && can_off_vibrator==false)
                            {

                                //textView1.setText("he "+" "+startX+"d "+startY);
                                //textView.setText("he "+drag);

                                can_off_vibrator=true;
                                vibrator = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
                                time_start = System.currentTimeMillis();  // catch the system time when the vibration is started
                                vibrator.vibrate(pattern, -1);
                                initial_X=startX;
                                initial_Y=startY;
                            }
                            else if((distance<drag-displacement)  && can_off_vibrator==false)
                            {
                                can_off_vibrator=true;
                                vibrator = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
                                time_start = System.currentTimeMillis();  // catch the system time when the vibration is started
                                vibrator.vibrate(pattern, -1);
                                initial_X=startX;
                                initial_Y=startY;

                            }
                            else
                            {
                                float diffrence_from_initial_X=Math.abs(startX-initial_X);
                                float diffrence_from_initial_Y=Math.abs(startY-initial_Y);
                                double B=(diffrence_from_initial_X*diffrence_from_initial_X)+(diffrence_from_initial_Y*diffrence_from_initial_Y);
                                float distance_from_initial=(float) (Math.sqrt(B));
                                if((distance_from_initial>=drag)  && can_off_vibrator==true)
                                {
                                    //textView.setText("else "+px++);
                                    vibrator.cancel();
                                    time_end = System.currentTimeMillis();  // catch the system time when the vibration is released
                                    long press_duration = (time_end - time_start);  // calculate the total vibarting time ( by calculating the diffrence between "when the vibration started and when vibartion end")
                                    NumberOfvibrationAndDataEntry(press_duration, pattern, butn); // vibration count
                                    can_off_vibrator=false;

                                    button_four_check=true;
                                    button_three_check=false;
                                    button_two_check=false;
                                    button_one_check=false;
                                }
                            }

                        }
                        else
                        {
                            if( can_off_vibrator==true)
                            {
                                vibrator.cancel();
                                time_end = System.currentTimeMillis();  // catch the system time when the vibration is released
                                long press_duration = (time_end - time_start);  // calculate the total vibarting time ( by calculating the diffrence between "when the vibration started and when vibartion end")
                                NumberOfvibrationAndDataEntry(press_duration, pattern, butn); // vibration count
                                can_off_vibrator=false;

                                if(butn==1)
                                {
                                    button_four_check=false;
                                    button_three_check=false;
                                    button_two_check=false;
                                    button_one_check=true;
                                }
                                else if(butn==2)
                                {
                                    button_four_check=false;
                                    button_three_check=false;
                                    button_two_check=true;
                                    button_one_check=false;
                                }
                                else if(butn==3)
                                {
                                    button_four_check=false;
                                    button_three_check=true;
                                    button_two_check=false;
                                    button_one_check=false;
                                }
                                else if(butn==4)
                                {
                                    button_four_check=true;
                                    button_three_check=false;
                                    button_two_check=false;
                                    button_one_check=false;

                                }

                            }
                        }
                        //drawView.SetColor();
                        prev_startX=startX;
                        prev_startY=startY;
                        break;
                    }
                    case MotionEvent.ACTION_UP : {
                        //drawView.SetColor();

                        if(can_off_vibrator==true)
                        {
                            vibrator.cancel();
                            time_end=System.currentTimeMillis();
                            long press_duration=(time_end-time_start);  // calculate the total vibarting time ( by calculating the diffrence between "when the vibration started and when vibartion end")
                            //textView.setText("up");
                            NumberOfvibrationAndDataEntry(press_duration,pattern,butn); // vibration count

                        }
                        /*Intent intent = new Intent();
                        intent.putExtra("data",rowList.size());
                        setResult(RESULT_OK,intent);*/
//                        Log.d("Hello Hello Hello Hello", "yes yes yes yes");
                        List<String> finalrowList = new ArrayList<>();
                        for(int[] array : rowList){

                            String tempValue = array[0]+"|"+array[1];
                            finalrowList.add(tempValue);
                        }
                        mainFragment.getPassword(finalrowList);
                        rowList = new ArrayList<int[]>();
                        prev_startX=0;
                        prev_startY=0;
                        can_off_vibrator=false;
                        button_four_check=false;
                        button_three_check=false;
                        button_two_check=false;
                        button_one_check=false;


                        break;
                    }

                }

                return true;
            }
        });
    }

    private static void NumberOfvibrationAndDataEntry   (long duration,long[] pattern,int buttonnumber)
    {
        //long du=duration;
        long val=0;                 // for vibration counting alforithm
        long prv_val=0;
        long vibrations =0;    // for vibration counting alforithm

        for(int i=1;i<20;i++)
        {

            val=prv_val+pattern[i];
            prv_val=val;
            if(val<duration)
            {
                continue;
            }
            else
            {
                if((i%2)==0)
                {
                    vibrations=(i+1)/2;
                }
                else
                {
                    vibrations=i/2;
                    break;
                }
            }
        }
        rowList.add(new int[]{buttonnumber,(int)vibrations});
    }
}
