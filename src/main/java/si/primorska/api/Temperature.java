package si.primorska.api;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Temperature {

  private Long id;

  private String sensorId;

  private LocalDateTime sensedDate;

  private Double temp;

  private char tempUnit;


}
