package si.primorska.api;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Sample {

  private Long id;

  private LocalDateTime startTime;

  private LocalDateTime endTime;
}
