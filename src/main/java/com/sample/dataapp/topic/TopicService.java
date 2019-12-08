package com.sample.dataapp.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicService {

	@Autowired
	private TopicRepository topicRepository;

	private static List<Topic> topics = new ArrayList<>();

	public List<Topic> getAllTopics() {
		List<Topic> topics = new ArrayList<>();
        topicRepository.findAll().forEach(topics::add);
        return topics;
	}
	
	public void addTopic(Topic topic) {
		topicRepository.save(topic);
	}
}
