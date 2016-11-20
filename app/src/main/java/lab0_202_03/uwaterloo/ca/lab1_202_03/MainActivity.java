package lab0_202_03.uwaterloo.ca.lab1_202_03;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;

import ca.uwaterloo.sensortoy.LineGraphView;

public class MainActivity extends AppCompatActivity {
    LineGraphView accelGraph;

    // Instantiate global listeners for resetting purposes
    LightSensorEventListener lightListener;
    AccelerometerEventListener accelListener;
    MagneticFieldSensorEventListener magFieldListener;
    RotationVectorSensorEventListener rotVectorListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout layout = (LinearLayout) findViewById(R.id.layout); // Create the parent layout
        layout.setOrientation(LinearLayout.VERTICAL); // Set the orientation to vertical

        // Instantiating accelerometer graph
        // Create the line graph that tracks the x, y, and z values of the accelerometer
        accelGraph = new LineGraphView(getApplicationContext(), 100, Arrays.asList("x", "y", "z"));
        layout.addView(accelGraph); // Add the graph to the parent layout
        accelGraph.setVisibility(View.VISIBLE); // Make the graph visible

        // Instantiating the different TextView labels
        // Light Sensor Label for outputting light sensor readings
        TextView lightSensorLabel = new TextView(getApplicationContext());
        lightSensorLabel.setTextColor(Color.BLACK);
        layout.addView(lightSensorLabel); // Add the label to the parent layout

        // Accelerometer Label for outputting accelerometer readings
        TextView accelSensorLabel = new TextView(getApplicationContext());
        accelSensorLabel.setTextColor(Color.BLACK);
        layout.addView(accelSensorLabel); // Add the label to the parent layout

        // Magnetic Field Label for outputting Magnetic Field readings
        TextView magFieldSensorLabel = new TextView(getApplicationContext());
        magFieldSensorLabel.setTextColor(Color.BLACK);
        layout.addView(magFieldSensorLabel); // Add the label to the parent layout

        // Rotation Vector Label for outputting Rotation Vector readings
        TextView rotationSensorLabel = new TextView(getApplicationContext());
        rotationSensorLabel.setTextColor(Color.BLACK);
        layout.addView(rotationSensorLabel); // Add the label to the parent layout

        // Request the sensor manager
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        // Get instances of each sensor with the sensor manager
        Sensor lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT); // Get the light sensor
        Sensor accelSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); // Get the accelerometer sensor
        Sensor magFieldSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD); // Get the magnetic field sensor
        Sensor rotVectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR); // Get the rotation vector sensor

        // Instantiate event listeners to receive the events from the sensors
        lightListener = new LightSensorEventListener(lightSensorLabel);
        accelListener = new AccelerometerEventListener(accelSensorLabel, accelGraph);
        magFieldListener = new MagneticFieldSensorEventListener(magFieldSensorLabel);
        rotVectorListener = new RotationVectorSensorEventListener(rotationSensorLabel);

        // Register each listener to the respective sensor
        sensorManager.registerListener(lightListener, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(accelListener, accelSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(magFieldListener, magFieldSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(rotVectorListener, rotVectorSensor, SensorManager.SENSOR_DELAY_NORMAL);

        // Button to reset the maximum values of the accelerometer, magnetic field, and rotation vector readings
        Button resetButton = new Button(getApplicationContext());
        resetButton.setText("Reset Max Values");
        resetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                accelListener.reset(); // Reset the accelerometer sensor
                magFieldListener.reset(); // Reset the magnetic field sensor
                rotVectorListener.reset(); // Reset the rotation vector sensor
            }
        });
        layout.addView(resetButton); // Add the button to the parent layout
    }
}
