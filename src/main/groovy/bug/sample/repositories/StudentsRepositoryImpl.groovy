package bug.sample.repositories

import bug.sample.domain.Student
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.stereotype.Repository

import static org.springframework.data.mongodb.core.query.Query.query

@Repository
class StudentsRepositoryImpl implements StudentsRepositoryCustom {

  @Autowired
  private MongoTemplate mongoTemplate

  private static final List<FieldFilter> FILTERS = [
    new FieldFilter(fieldName: 'name', fuzzy: false),
    new FieldFilter(fieldName: 'id', fuzzy: false)
  ]

  @SuppressWarnings('ExplicitCallToAndMethod')
  @Override
  List<Student> findByTitles(Map<String, Object> parametersMap) {

    Criteria criteria = new Criteria()
    parametersMap.each { String field, Object value ->
      if (field == 'query') {
        // Apply the query string and generate the needed criteria
        criteria = QueryClauseUtil.applyQueryCriteria(value as String, FILTERS, criteria)
      } else if (field == 'title') {
        criteria = criteria.and('books').elemMatch(Criteria.where('title').is(value))
      }
    }

    List<Student> samples = mongoTemplate.find(query(criteria), Student)

    return samples
  }

}
