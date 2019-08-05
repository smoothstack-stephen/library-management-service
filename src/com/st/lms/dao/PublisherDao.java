package com.st.lms.dao;
import java.io.*;
import java.util.*;
import java.util.stream.*;
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
			String[] values = line.split(";");
			String name = values[0].trim();			
			String id = values[1].trim();
			String address = values[2].trim();
			
			publishers.add(new Publisher(name, id, address));
		}
		
		buffer.close();
		return publishers;
	}
	
	public void addPublisher(String name, String id, String address) {
		sanitize(name, id, address);
		if (!isDuplicateId(id)) publishers.add(new Publisher(name, id, address));
		else System.out.println("Publisher Id already exists, cannot add publisher with duplicate Id.");
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
		publishers = publishers.stream()
					.filter(publisher -> !publisher.getId().equalsIgnoreCase(queryId))
					.collect(Collectors.toSet());
	}

	public void saveToCSV() throws IOException {
		BufferedWriter buffer = new BufferedWriter(new FileWriter(fileRelativePath));
		
		publishers.stream()
		.forEach(publisher -> {
			String line = String.join("; ", publisher.getName(), publisher.getId(), publisher.getAddress());
			try {
				buffer.write(line + "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		buffer.close();		
	}
	
	// Removes special characters (just ; at the moment)
	public void sanitize(String ...args) {
		Set<String> strings = new TreeSet<>(Arrays.asList(args));
		strings.stream()
		.forEach(string -> string.replaceAll(";", ""));
	}
	
	// Checks if there already exists a Publisher with some Id value
	public boolean isDuplicateId(String idToCheck) {
		return publishers.stream().anyMatch(publisher -> publisher.getId().equalsIgnoreCase(idToCheck));
	}
	
}
