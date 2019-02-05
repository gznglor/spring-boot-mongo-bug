package bug.sample.services

import bug.sample.domain.Student
import bug.sample.repositories.StudentsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StudentService {

  @Autowired
  StudentsRepository repository

  Student getById(String id) {
    return repository.findOneById(id)
  }

}
