# spring-boot-mongo-bug
A sample project demonstrating a possible bug with Spring Boot 2.1.1. 

It appears that using the annotations @CreatedDate, @CreatedBy, @LastModifiedDate, and @LastModifiedBy on domain fields causes this error: 
```
org.springframework.data.mapping.MappingException: Cannot lookup property @groovy.transform.Generated() 
@com.fasterxml.jackson.annotation.JsonIgnore(value=true)private java.util.Set bug.sample.domain.Student.classes 
on null intermediate! Original path was: classes.created on bug.sample.domain.Student.
``` 

### How to reproduce the bug
After cloning or forking the project, run
```
./gradlew clean build
```
to output errors noted above. The failing tests are **StudentsRepositoryImplIntegrationSpec. find a student by books they are currently borrowing** and **StudentsRepositoryIntegrationSpec. find one student by id**.

### Other observations
1. Downgrading to Spring Boot 2.0.5 without any further changes to the existing code produces a successful build.

2. Upgrading to Spring Boot 2.1.1 and commenting out the annotations mentioned above, will produce an assertion error in the tests. Ex: 
```
students[0].created // In Spring Boot 2.1.1, this assertion will fail if the annotations on the domain objects are commented out
|       |   |
|       |   null
|       bug.sample.domain.Student(5c58b38246b18e9d8925cb8c, Jill, [bug.sample.domain.Book@7d92c00f], null, null, null, null, null)
[bug.sample.domain.Student(5c58b38246b18e9d8925cb8c, Jill, [bug.sample.domain.Book@7d92c00f], null, null, null, null, null)]
``` 
Without the annotation, the created field is not populated.

3. And of course, upgrading to Spring Boot 2.1.1, commenting out the annotations mentioned above, and commenting out the 'created' field assertions in the test will produce a successful build.


