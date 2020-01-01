package com.jeekrs.neuro_simulation.components.sensors;

import com.jeekrs.neuro_simulation.components.Component;
import com.jeekrs.neuro_simulation.neural_network.SensorNeuron;

public abstract class Sensor extends Component {
    public abstract int getNeuronNumber();
    public abstract SensorNeuron[] getSensorNeurons();
}
