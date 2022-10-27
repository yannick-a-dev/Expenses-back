package com.arrays.arrays.service;

import java.io.IOException;
import java.util.Collection;

import com.arrays.arrays.model.Server;

public interface ServerService {

	Server create(Server server);
	static Server ping(String ipAddress) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}
	Collection<Server> list(int limit);
	Server get(Long id);
	Server update(Server server);
	Boolean delete(Long id);
}
