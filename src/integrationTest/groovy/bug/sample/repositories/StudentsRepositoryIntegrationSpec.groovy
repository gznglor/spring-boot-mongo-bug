package bug.sample.repositories

import bug.sample.domain.Student
import bug.sample.domain.Clazz
import org.springframework.beans.factory.annotation.Autowired

class StudentsRepositoryIntegrationSpec extends IntegrationSpec {

  @Autowired
  StudentsRepository repository

  void cleanup() {
    repository.deleteAll()
  }

  void 'find one student by id'() {
    given: 'create and save a sample student with some data'
    Student sample = repository.save(new Student(
      id: '123',
      name: 'Sample Test'))

    when: 'return the student given id'
    Student result = repository.findOneById('123')

    then: 'confirm the correct student is returned'
    result.id == sample.getId()
    result.name == sample.getName()
    result.created // In Spring Boot 2.1.1, this assertion will fail if the annotations on the domain objects are commented out
  }

}
