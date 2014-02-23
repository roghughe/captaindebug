package com.captaindebug.errortrack.publish;

import org.springframework.stereotype.Service;

import com.captaindebug.errortrack.Publisher;

@Service
public class EmailPublisher implements Publisher {

	@Override
	public <T> boolean publish(T report) {
		// TODO Auto-generated method stub
		return false;
	}

}
