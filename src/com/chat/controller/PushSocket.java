package com.chat.controller;

import java.io.IOException;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import redis.clients.jedis.Jedis;

@ServerEndpoint("/PushSocket/{userName}")
public class PushSocket {
	 private Session session = null;

	@OnOpen
	public void onOpen(@PathParam("userName") String str,Session userSession) throws IOException {
		this.session =userSession;
		 @SuppressWarnings("resource")
		Jedis jedis = new Jedis("localhost", 6379);
		 jedis.auth("123456");
		 String sstr = jedis.get(str);
		 if(sstr!=null) {
		 session.getAsyncRemote().sendText(sstr);
		 jedis.del(str);
		 }
		}


	}

