package com.subaru.cv.vehicleservice.domain;

import com.subaru.cv.cafe.jpa.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * The type Tele model features entity.
 */
@Entity
@Table(name = "TELE_MODEL_FEATURES")
@SuppressWarnings("unused")
public class TeleModelFeaturesEntity extends AuditableEntity {
  @EmbeddedId
  private TeleModelFeaturesId id;

  @NotNull
  @Column(name = "EFFECTIVE_START_DATE", nullable = false)
  private LocalDate effectiveStartDate;

  @Column(name = "EFFECTIVE_END_DATE")
  private LocalDate effectiveEndDate;

  @MapsId("featureId")
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @OnDelete(action = OnDeleteAction.RESTRICT)
  @JoinColumn(name = "FEATURE_ID", nullable = false)
  private TeleFeaturesMstEntity feature;

  @MapsId("modelId")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.RESTRICT)
  @JoinColumn(name = "MODEL_ID", nullable = false)
  private TeleLocalModelMstEntity localModel;

  /**
   * Gets effective end date.
   *
   * @return the effective end date
   */
  public LocalDate getEffectiveEndDate() {
    return effectiveEndDate;
  }

  /**
   * Sets effective end date.
   *
   * @param effectiveEndDate the effective end date
   */
  public void setEffectiveEndDate(LocalDate effectiveEndDate) {
    this.effectiveEndDate = effectiveEndDate;
  }

  /**
   * Gets effective start date.
   *
   * @return the effective start date
   */
  public @NotNull LocalDate getEffectiveStartDate() {
    return effectiveStartDate;
  }

  /**
   * Sets effective start date.
   *
   * @param effectiveStartDate the effective start date
   */
  public void setEffectiveStartDate(@NotNull LocalDate effectiveStartDate) {
    this.effectiveStartDate = effectiveStartDate;
  }

  /**
   * Gets feature.
   *
   * @return the feature
   */
  public TeleFeaturesMstEntity getFeature() {
    return feature;
  }

  /**
   * Sets feature.
   *
   * @param feature the feature
   */
  public void setFeature(TeleFeaturesMstEntity feature) {
    this.feature = feature;
  }

  /**
   * Gets id.
   *
   * @return the id
   */
  public TeleModelFeaturesId getId() {
    return id;
  }

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(TeleModelFeaturesId id) {
    this.id = id;
  }

  /**
   * Gets local model.
   *
   * @return the local model
   */
  public TeleLocalModelMstEntity getLocalModel() {
    return localModel;
  }

  /**
   * Sets local model.
   *
   * @param localModel the local model
   */
  public void setLocalModel(TeleLocalModelMstEntity localModel) {
    this.localModel = localModel;
  }
}
