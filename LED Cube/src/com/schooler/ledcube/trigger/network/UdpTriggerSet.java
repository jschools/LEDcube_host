package com.schooler.ledcube.trigger.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import com.schooler.ledcube.trigger.TriggerSet;
import com.schooler.ledcube.util.IntArray;

public class UdpTriggerSet implements TriggerSet {

	public static final int PORT = 8989;

	private static final int NUM_CHANNELS = 32;

	private IntArray channels;
	
	private DatagramSocket socket;

	public static void main(String[] args) {
		UdpTriggerSet triggerSet = new UdpTriggerSet();
		while (true) {
		}
	}

	public UdpTriggerSet() {
		channels = new IntArray(NUM_CHANNELS);

		try {
			socket = new DatagramSocket(PORT);
		}
		catch (SocketException e) {
			e.printStackTrace();
		}

		new Thread(new ReceiverThread()).start();
	}
	
	@Override
	public int getTriggerValue(int channel) {
		return channels.get(channel);
	}

	@Override
	public int getNumChannels() {
		return NUM_CHANNELS;
	}

	private class ReceiverThread implements Runnable {

		private static final int PACKET_SIZE = 8;

		private DatagramPacket udpPacket;
		private byte[] buf;
		private ChannelPacket channelPacket;
		private int count;

		public ReceiverThread() {
			buf = new byte[16];
			udpPacket = new DatagramPacket(buf, PACKET_SIZE);
			channelPacket = new ChannelPacket();
			count = 0;
		}

		@Override
		public void run() {
			try {
				while (true) {
					socket.receive(udpPacket);
					channelPacket.readFromBuffer(udpPacket.getData());

					channels.set(channelPacket.channel, channelPacket.value);

					System.out.printf("%4d %2d::%8.4f\n", count++, channelPacket.channel,
							Float.intBitsToFloat(getTriggerValue(channelPacket.channel)));
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
