package com.schooler.ledcube.trigger.network;

import java.nio.ByteBuffer;

public class ChannelPacket {

	public int channel;
	public int value;

	public void writeToBuffer(byte[] buf) {
		ByteBuffer byteBuffer = ByteBuffer.wrap(buf);
		byteBuffer.position(0);
		byteBuffer.putInt(channel);
		byteBuffer.putInt(value);
	}

	public void readFromBuffer(byte[] buf) {
		ByteBuffer byteBuffer = ByteBuffer.wrap(buf);
		byteBuffer.position(0);
		channel = byteBuffer.getInt();
		value = byteBuffer.getInt();
	}
}
