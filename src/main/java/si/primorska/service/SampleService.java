package si.primorska.service;

import java.time.LocalDateTime;

import si.primorska.api.Sample;

public interface SampleService {

  void save(Sample sample);

  Sample findByStartTime(LocalDateTime startTime);

}
