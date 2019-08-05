package com.st.lms.dao;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;
import com.st.lms.model.Publisher;

public class PublisherDao {

	private String fileRelativePath = System.getProperty("user.dir") + "\\src\\publishers.csv";
	private Set<Publisher> publishers = new TreeSet<>();

	
	public PublisherDao() throws IOException {
		readPublishers();
	}
	public Set<Publisher> readPublishers() throws IOException {
		BufferedReader buffer = new BufferedReader(new FileReader(fileRelativePath));
		String line;
		
		while((line = buffer.readLine()) != null) {
			String[] values = line.split(",");
			publishers.add(new Publisher(values[0], values[1], values[2]));
		}
		
		buffer.close();
		return publishers;
	}
	
	public void addPublisher(String name, String id, String address) {
		publishers.add(new Publisher(name, id, address));
	}
	
	public void updatePublisher(String queryId, String newName, String newAddress) {
		publishers.stream()
		.filter(publisher -> publisher.getId().equalsIgnoreCase(queryId))
		.forEach(publisher -> {
			publisher.setName(newName);
			publisher.setAddress(newAddress);
		});
	}
	
	public void retrievePublisher(String queryId) {
		publishers.stream()
		.filter(publisher -> publisher.getId().equalsIgnoreCase(queryId))
		.forEach(publisher -> publisher.printInfo());
	}
	
	public void removePublisher(String queryId) {
		publishers.stream()
		.filter(publisher -> publisher.getId().equalsIgnoreCase(queryId))
		.forEach(publisher -> publishers.remove(publisher));
	}
	
}
