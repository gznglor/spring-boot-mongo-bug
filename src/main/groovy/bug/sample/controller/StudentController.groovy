package bug.sample.controller

import bug.sample.domain.Student
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import bug.sample.services.StudentService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping('/samples')
class StudentController {

  @Autowired
  StudentService service

  @GetMapping('/{id}')
  @ResponseStatus(HttpStatus.OK)
  Student getOne(@PathVariable String id) {
    return service.getById(id)
  }

}
