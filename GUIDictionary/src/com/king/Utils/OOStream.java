
package com.king.Utils;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
/*
* class extends ObjectOutputStream cuz we need to override writeStreamHeader method
* so we can append data to history file which is binary file && contains Search Model
*/

public class OOStream extends ObjectOutputStream {
    public OOStream(OutputStream out) throws IOException {
        super(out);
    }
    
    @Override
    protected void writeStreamHeader() throws IOException {
        reset();
    }
    
}
