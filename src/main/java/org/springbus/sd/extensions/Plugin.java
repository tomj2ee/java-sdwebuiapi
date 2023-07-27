package org.springbus.sd.extensions;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface Plugin {
    Object toDict();
    @JsonIgnore
    String  getName();
}
