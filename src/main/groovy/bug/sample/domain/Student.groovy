package bug.sample.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document

import java.time.Instant

@Document(collection = 'sample_students')
@EqualsAndHashCode
@ToString
class Student implements AuditedResource{
  String id
  String name
  List<Book> books
  @JsonIgnore
  Set<Clazz> classes

  @CreatedDate // In Spring 2.1.1, comment out this annotation for test to pass
  Instant created
  @CreatedBy // In Spring 2.1.1, comment out this annotation for test to pass
  String createdBy
  @LastModifiedDate // In Spring 2.1.1, comment out this annotation for test to pass
  Instant lastModified
  @LastModifiedBy // In Spring 2.1.1, comment out this annotation for test to pass
  String lastModifiedBy

}
