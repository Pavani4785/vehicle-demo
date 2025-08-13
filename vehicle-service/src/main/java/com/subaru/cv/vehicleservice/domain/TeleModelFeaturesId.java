package com.subaru.cv.vehicleservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

/**
 * The type Tele model features' id.
 */
@Embeddable
@SuppressWarnings("unused")
public class TeleModelFeaturesId implements Serializable {

  @Serial
  private static final long serialVersionUID = -5887366074769503313L;

  @NotNull
  @Column(name = "FEATURE_ID", nullable = false)
  private long featureId;

  @NotNull
  @Column(name = "MODEL_ID", nullable = false)
  private long modelId;

  /**
   * Gets feature id.
   *
   * @return the feature id
   */
  public @NotNull long getFeatureId() {
    return featureId;
  }

  /**
   * Sets feature id.
   *
   * @param featureId the feature id
   */
  public void setFeatureId(@NotNull long featureId) {
    this.featureId = featureId;
  }

  /**
   * Gets model id.
   *
   * @return the model id
   */
  public @NotNull long getModelId() {
    return modelId;
  }

  /**
   * Sets model id.
   *
   * @param modelId the model id
   */
  public void setModelId(@NotNull long modelId) {
    this.modelId = modelId;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    TeleModelFeaturesId that = (TeleModelFeaturesId) o;
    return featureId == that.featureId && modelId == that.modelId;
  }

  @Override
  public int hashCode() {
    int result = Long.hashCode(featureId);
    result = 31 * result + Long.hashCode(modelId);
    return result;
  }
}
