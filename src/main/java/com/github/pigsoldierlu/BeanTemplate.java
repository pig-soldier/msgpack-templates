package com.github.pigsoldierlu;

import org.msgpack.MessageTypeException;
import org.msgpack.packer.Packer;
import org.msgpack.template.AbstractTemplate;
import org.msgpack.unpacker.Unpacker;

import java.io.IOException;

/**
 * Created by pigsoldier on 15/5/26.
 */
public class BeanTemplate<T> extends AbstractTemplate<T> {


    private Class<T> clz;

    public BeanTemplate(Class<T> clz) {
        super();
        this.clz = clz;
    }

    @Override
    public void write(Packer pk, T v, boolean required) throws IOException {
        if (null == v) {
            if (required) {
                throw new MessageTypeException("Attempted to write null");
            }
            pk.writeNil();
            return;
        }

        pk.write(v);
    }

    @Override
    public T read(Unpacker u, T to, boolean required) throws IOException {
        if (!required && u.trySkipNil()) {
            return null;
        }
        return u.read(this.clz);
    }
}
