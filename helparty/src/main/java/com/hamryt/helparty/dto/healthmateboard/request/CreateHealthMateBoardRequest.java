package com.hamryt.helparty.dto.healthmateboard.request;

import javax.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateHealthMateBoardRequest {

  @NotEmpty
  private String gym;

  @NotEmpty
  private String content;

  @NotEmpty
  private String startTime;

  @NotEmpty
  private String endTime;

  @Builder
  public CreateHealthMateBoardRequest(
      String gym, String content, String startTime, String endTime
  ){
    this.gym = gym;
    this.content = content;
    this.startTime = startTime;
    this.endTime = endTime;
  }
}
