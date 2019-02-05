package bug.sample.repositories

import bug.sample.domain.Student

interface StudentsRepositoryCustom {

  List<Student> findByTitles(Map<String, Object> parametersMap)

}
