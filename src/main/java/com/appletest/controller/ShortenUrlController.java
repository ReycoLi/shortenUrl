package com.appletest.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.appletest.jedis.JedisClient;

/**
 * <p>Title: ShortenUrlController</p>
 * <p>Company: www.appletest.com</p>
 * @author Zhe Li 
 * @version 1.0
 */

@Controller
public class ShortenUrlController {
	@Autowired
	private JedisClient jedisClient;
	
	/*
	 * get short url and store
	 */
	@RequestMapping("/shorten")
	public String showShortenedUrl(@RequestParam("longURL") String url, Model model){
		String id = "";
		try {
			//if the long URL has already exist short url
			if(jedisClient.exists(url)){
				id = jedisClient.get(url);
			} else {
				//incr index as id of long url
				Long idLong = jedisClient.incr("UrlIndex");
				
				//store result to Redis
				jedisClient.set(idLong+"", url);
				jedisClient.set(url, idLong+"");
				id = String.valueOf(idLong);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		String shortUrl = "http://localhost:9000/s/" + id;
		model.addAttribute("shortUrl", shortUrl);
		return "success";
	}
	
	/*
	 * redirect
	 */
	@RequestMapping("/s/{id}") 
	public void shortUrlRedirect(@PathVariable String id, HttpServletResponse response){
		try {
			String url = jedisClient.get(id);
			//redirect
			response.sendRedirect(url);
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				response.sendRedirect("http://localhost:9000/error.jsp");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
}
















