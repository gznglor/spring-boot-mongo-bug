package bug.sample.repositories

import bug.sample.domain.Student
import bug.sample.domain.Book
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Unroll

class StudentsRepositoryImplIntegrationSpec extends IntegrationSpec {

  @Autowired
  StudentsRepository studentsRepository

  void cleanup() {
    studentsRepository.deleteAll()
  }

  @Unroll
  void 'find a student by books they are currently borrowing'() {
    given:
    // Create 2 students with a list of borrowed books
    Student student1 = new Student(
      name: 'Jill',
      books: [
        new Book(title: 'Dead Mountaineers Hotel', author: 'Arkady and Boris Strugatsky')
      ]
    )

    Student student2 = new Student(
      name: 'Jack',
      books: [
        new Book(title: 'All the Devils Are Here', author: 'Bethany McLean and Joe Nocera')
      ]
    )

    studentsRepository.save(student1)
    studentsRepository.save(student2)

    Map<String, Object> filters = ['title': 'Dead Mountaineers Hotel']

    when:
    List<Student> students = studentsRepository.findByTitles(filters)

    then:
    students.size() == 1
    students[0].name == 'Jill'
    students[0].created // In Spring Boot 2.1.1, this assertion will fail if the annotations on the domain objects are commented out
  }
}
