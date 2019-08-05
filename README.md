# library-management-service
### :books: Beta version
A library management service (LMS) application by Team ***Super Smoothstack-Jin*** (Stephen, Skyler, Juan)

## Implementation
Our LMS application follows the Data Access Object (DAO) design scheme.

```
App
> LMSApp.java

DAO
> AuthorDao.java
> BookDao.java
> PublisherDao.java

Model
> Author.java
> Book.java
> Publisher.java
```

The App class (LMSApp.java) implements the menu interface, program logic, and user input.

The DAO classes provide file processing (CSV files) and database methods (add/update/retrieve/remove) to the App class.

The Model classes hold data fields (e.g. Author name/id), which can be retrieved or modified by DAO objects.

### Methods
(Example from BookDao.java)

#### **File I/O**
```java
// Set is used to ensure there are no duplicates
public Set<Book> readBooks() throws IOException {
  BufferedReader buffer = new BufferedReader(new FileReader(fileRelativePath));
  String line;

  while((line = buffer.readLine()) != null) {
    String[] values = line.split(";");
    String name = values[0].trim();
    String id = values[1].trim();
    String authId = values[2].trim();
    String pubId = values[3].trim();

    books.add(new Book(name, id, authId, pubId));
  }

  buffer.close();
  return books;
}

public void saveToCSV() throws IOException {
  BufferedWriter buffer = new BufferedWriter(new FileWriter(fileRelativePath));

  books.stream()
  .forEach(book -> {
    String line = String.join("; ", book.getName(), book.getId(), book.getAuthId(), book.getPubId());
    try {
      buffer.write(line + "\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  });

  buffer.close();
}
```

#### **List iteration**
```java
// Uses Java 8 Stream and Lambda expressions
public void updateBook(String queryId, String newName, String newAuthId, String newPubId) {
  books.stream()
  .filter(book -> book.getId().equalsIgnoreCase(queryId))
  .forEach(book -> {
    book.setName(newName);
    book.setAuthId(newAuthId);
    book.setPubId(newPubId);
  });
}
```

## Task List
- [x] Implement model classes with relevant methods (print, getter/setter, equals, hashCode, compareTo)
- [x] Implement DAO classes for each model (read from CSV, add/update/retrieve/remove, save to CSV)
- [x] Implement main application (command-line interface)

- [ ] Next Steps
  - [ ] Input sanitizing (remove characters with special meaning; e.g. "//", "\n", and ";")
  - [ ] Input checking (for invalid or duplicate id values)
  - [ ] Exception handling (better error messages)
  - [ ] Unit testing
  - [ ] Improve app flow (return to title page, more forgiving input handling)
