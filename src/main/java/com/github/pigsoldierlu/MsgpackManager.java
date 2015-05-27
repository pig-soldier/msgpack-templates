package com.github.pigsoldierlu;

import org.msgpack.MessagePack;
import org.msgpack.MessageTypeException;
import org.msgpack.packer.Packer;
import org.msgpack.template.AbstractTemplate;
import org.msgpack.template.ListTemplate;
import org.msgpack.template.Template;
import org.msgpack.template.Templates;
import org.msgpack.unpacker.Unpacker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.msgpack.template.Templates.*;



/**
 * Created by pigsoldier on 14-6-10.
 */

public class MsgpackManager implements IMsgpackManager{

    private static final class SingletonHolder {
        private static final MsgpackManager INSTANSE = new MsgpackManager();
    }

    public static MsgpackManager getInstance() {
        return SingletonHolder.INSTANSE;
    }

    @Override
    public MessagePack getMessagePack() {
        return messagePack;
    }

    public MsgpackManager() {
        this.messagePack = new MessagePack();
    }

    private MessagePack messagePack;

    public static <T> byte[] encode(T object) throws IOException {
        return MsgpackManager.getInstance().getMessagePack().write(object);
    }

    public static<T> T decode(byte[] bytes, Class<T> clazz) throws IOException {
        return MsgpackManager.getInstance().getMessagePack().read(bytes, clazz);
    }


    public static<T> T decode(byte[] bytes) throws IOException {
        return (T) MsgpackManager.getInstance().getMessagePack().read(bytes);
    }

    public static <T>  T decode(byte[] bytes, Template<T> tmpl) throws IOException {
        return MsgpackManager.getInstance().getMessagePack().read(bytes, tmpl);
    }


}
