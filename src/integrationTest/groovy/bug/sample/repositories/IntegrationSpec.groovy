package bug.sample.repositories

import bug.sample.Application
import com.mongodb.BasicDBObject
import com.mongodb.DBObject
import com.mongodb.client.model.Filters
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import spock.lang.Stepwise

@SpringBootTest(classes = [Application], webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = [Application])
@Stepwise
class IntegrationSpec extends Specification {

  @Autowired
  MongoTemplate mongoTemplate

  DBObject getDBObjectById(String collection, String id) {
    new BasicDBObject(mongoTemplate.getCollection(collection).find(Filters.eq('_id', id)).first())
  }

}
