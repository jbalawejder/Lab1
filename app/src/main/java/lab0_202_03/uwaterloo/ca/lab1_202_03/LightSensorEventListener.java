package lab0_202_03.uwaterloo.ca.lab1_202_03;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;

// Light Sensor Event Listener
public class LightSensorEventListener implements SensorEventListener{
    TextView output;

    public LightSensorEventListener(TextView outputView){
        output = outputView;
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy){    }

    public void onSensorChanged(SensorEvent event){ // SensorEvent represents a single message from a single sensor
        if(event.sensor.getType() == Sensor.TYPE_LIGHT){
            output.setText(String.format("====== Light Sensor ======\n" +
                    "%f\n" +
                    "", event.values[0])); // Output the maximum reading and the current reading

        }
    }
}
