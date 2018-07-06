package com.niuker.service;

import org.springframework.stereotype.Service;

@Service
public class WendaService {
	public String getMessage(int userId) {
		return "WendaService Message:" + String.valueOf(userId);
	}
}
