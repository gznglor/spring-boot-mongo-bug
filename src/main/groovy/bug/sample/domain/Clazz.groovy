package bug.sample.domain

import groovy.transform.EqualsAndHashCode
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate

import java.time.Instant

@EqualsAndHashCode
class Clazz implements AuditedResource {

  String title

  @CreatedDate // In Spring 2.1.1, comment out this annotation for test to pass
  Instant created
  @CreatedBy // In Spring 2.1.1, comment out this annotation for test to pass
  String createdBy
  @LastModifiedDate // In Spring 2.1.1, comment out this annotation for test to pass
  Instant lastModified
  @LastModifiedBy // In Spring 2.1.1, comment out this annotation for test to pass
  String lastModifiedBy

}
