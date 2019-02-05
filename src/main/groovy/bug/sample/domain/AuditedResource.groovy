package bug.sample.domain

import java.time.Instant

interface AuditedResource {
  Instant getCreated()
  void setCreated(Instant created)
  String getCreatedBy()
  void setCreatedBy(String lastModifiedBy)
  Instant getLastModified()
  void setLastModified(Instant lastModified)
  String getLastModifiedBy()
  void setLastModifiedBy(String lastModifiedBy)
}