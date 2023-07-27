package org.springbus.sd.extensions;

import org.springbus.sd.model.ControlNet;

import java.util.ArrayList;
import java.util.List;

public class ControlNetUnit implements  Plugin {

    private  List<ControlNet> controlNets=new ArrayList<>();
    public ControlNetUnit(List<ControlNet> net){
        controlNets.addAll(net);
    }
    public ControlNetUnit(ControlNet net){
        controlNets.add(net);
    }

    @Override
    public Object toDict() {
        return controlNets;
    }

    @Override
    public String getName() {
        return "ControlNet";
    }


}

