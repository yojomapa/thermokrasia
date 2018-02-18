package si.primorska.service;

import java.time.LocalDateTime;

import javax.inject.Inject;

import si.primorska.api.Sample;
import si.primorska.db.SampleDao;

public class SampleServiceImpl implements SampleService {

  private SampleDao sampleDao;

  @Inject
  public SampleServiceImpl(SampleDao sampleDao) {
    this.sampleDao = sampleDao;
  }

  @Override
  public void save(Sample sample) {

    if (sample.getEndTime() == null) {
      sampleDao.insert(sample);
    } else {
      sampleDao.update(sample);
    }
  }

  @Override
  public Sample findByStartTime(LocalDateTime startTime) {
    return null;
  }
}
