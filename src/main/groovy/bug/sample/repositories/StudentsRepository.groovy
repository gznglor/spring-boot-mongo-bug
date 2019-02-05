package bug.sample.repositories

import bug.sample.domain.Student
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface StudentsRepository extends MongoRepository<Student, String>, StudentsRepositoryCustom {

  Student findOneById(String id)

}
