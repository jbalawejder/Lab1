package lab0_202_03.uwaterloo.ca.lab1_202_03;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import android.widget.TextView;

// Magnetic Field Sensor Event Listener
public class MagneticFieldSensorEventListener implements SensorEventListener{
    TextView output;
    double maxValues[] = {0, 0, 0};

    public MagneticFieldSensorEventListener (TextView outputView) {
        output = outputView;
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy){    }

    public void onSensorChanged(SensorEvent event){ // SensorEvent represents a single message from a single sensor
        if(event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            // If the maximum values are not being reset, then check if any current values are new maximums
            if (Math.abs(event.values[0]) > maxValues[0]) { // Record the absolute value of the maximum X component
                maxValues[0] = Math.abs(event.values[0]);
            }
            if (Math.abs(event.values[1]) > maxValues[1]) { // Record the absolute value of the maximum Y component
                maxValues[1] = Math.abs(event.values[1]);
            }
            if (Math.abs(event.values[2]) > maxValues[2]) { // Record the absolute value of the maximum Z component
                maxValues[2] = Math.abs(event.values[2]);
            }

            // Output maximum and current values for all components of the magnetic field
            output.setText(String.format("------ Maximum Magnetic Field Reading ------\n" +
                    "X: %f\n" +
                    "Y: %f\n" +
                    "Z: %f\n\n" +
                    "====== Magnetic Field Sensor ======\n" +
                    "X: %f\n" +
                    "Y: %f\n" +
                    "Z: %f\n" +
                    "", maxValues[0], maxValues[1], maxValues[2], event.values[0], event.values[1], event.values[2]));

        }
    }

    // Reset the maximum values of the x, y, and z components
    public void reset(){
        maxValues[0] = 0;
        maxValues[1] = 0;
        maxValues[2] = 0;
    }
}