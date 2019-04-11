package hello.jedis.example.pubsub;

import redis.clients.jedis.JedisPubSub;

public class MyPubSub extends JedisPubSub{

	
	public void onMessage(String channel, String message) { 
		System.out.println("onMessage:channel="+channel+", msg="+message);
	}

	public void onPMessage(String pattern, String channel, String message) {
		System.out.println("onPMessage:pattern="+pattern+", channel="+channel+", msg="+message);
	}

	public void onSubscribe(String channel, int subscribedChannels) {
		System.out.println("onSubscribe:channel="+channel+", subscribedChannels="+subscribedChannels);
	}

	public void onUnsubscribe(String channel, int subscribedChannels) {
		System.out.println("onUnsubscribe:channel="+channel+", subscribedChannels="+subscribedChannels); 
	}

	public void onPUnsubscribe(String pattern, int subscribedChannels) {
		System.out.println("onPUnsubscribe:pattern="+pattern+", subscribedChannels="+subscribedChannels); 
	}

	public void onPSubscribe(String pattern, int subscribedChannels) { 
		System.out.println("onPSubscribe:pattern="+pattern+", subscribedChannels="+subscribedChannels); 
	}

	public void onPong(String pattern) {
		System.out.println("onPong:pattern="+pattern); 
	}
}
