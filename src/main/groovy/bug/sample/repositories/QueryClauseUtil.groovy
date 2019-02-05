package bug.sample.repositories

import org.springframework.data.mongodb.core.query.Criteria
import static org.springframework.data.mongodb.core.query.Criteria.where

class QueryClauseUtil {

  static Criteria applyQueryCriteria(String queryString, List<FieldFilter> filters, Criteria baseCriteria = null) {
    Criteria criteria = baseCriteria ?: new Criteria()

    if (queryString) {
      String[] tokens = queryString.split('\\s+')

      List<Criteria> andOps = tokens.collect { String queryToken ->
        List<Criteria> orOps = filters.collect { FieldFilter fieldFilter ->
          return fieldFilter.fuzzy ?
            where(fieldFilter.fieldName).regex("${queryToken}", 'i') :
            where(fieldFilter.fieldName).is(queryToken)
        }

        return new Criteria().orOperator(orOps as Criteria[])
      }

      criteria.andOperator(andOps as Criteria[])
    }

    return criteria
  }
}
