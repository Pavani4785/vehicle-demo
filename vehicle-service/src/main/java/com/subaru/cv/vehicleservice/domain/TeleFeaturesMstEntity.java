package com.subaru.cv.vehicleservice.domain;

import com.subaru.cv.cafe.jpa.AuditableEntity;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;
import org.hibernate.type.YesNoConverter;

/**
 * The type Tele features mst entity.
 */
@Entity
@Table(name = "TELE_FEATURES_MST")
@AttributeOverrides({
    @AttributeOverride(name = "createUser", column = @Column(name = "CREATE_USER", nullable = false, length = 100)),
    @AttributeOverride(name = "createDate", column = @Column(name = "CREATE_DATE", nullable = false))
})
@SuppressWarnings("unused")
public class TeleFeaturesMstEntity extends AuditableEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "FEATURE_ID", nullable = false)
  private long id;

  @Size(max = 250)
  @NotNull
  @Column(name = "FEATURE_CODE", nullable = false, length = 250)
  private String featureCode;

  @Size(max = 250)
  @NotNull
  @Column(name = "FEATURE_DESC", nullable = false, length = 250)
  private String featureDesc;

  @Size(max = 1)
  @NotNull
  @Convert(converter = YesNoConverter.class)
  @Column(name = "REMOTE_FLG", nullable = false, length = 1)
  private boolean remoteFlg;

  @Size(max = 1)
  @Convert(converter = YesNoConverter.class)
  @Column(name = "DELIVERY_CHECKLIST_FLG", length = 1)
  private Boolean deliveryChecklistFlg;

  @Size(max = 1)
  @Convert(converter = YesNoConverter.class)
  @Column(name = "MONRONEY_LABEL_FLG", length = 1)
  private Boolean monroneyLabelFlg;

  @OneToMany(mappedBy = "feature")
  private Set<TeleModelFeaturesEntity> teleModelFeatures = new LinkedHashSet<>();

  /**
   * Gets delivery checklist flg.
   *
   * @return the delivery checklist flg
   */
  public @Size(max = 1) Boolean getDeliveryChecklistFlg() {
    return deliveryChecklistFlg;
  }

  /**
   * Sets delivery checklist flg.
   *
   * @param deliveryChecklistFlg the delivery checklist flg
   */
  public void setDeliveryChecklistFlg(@Size(max = 1) Boolean deliveryChecklistFlg) {
    this.deliveryChecklistFlg = deliveryChecklistFlg;
  }

  /**
   * Gets feature code.
   *
   * @return the feature code
   */
  public @Size(max = 250) @NotNull String getFeatureCode() {
    return featureCode;
  }

  /**
   * Sets feature code.
   *
   * @param featureCode the feature code
   */
  public void setFeatureCode(@Size(max = 250) @NotNull String featureCode) {
    this.featureCode = featureCode;
  }

  /**
   * Gets feature desc.
   *
   * @return the feature desc
   */
  public @Size(max = 250) @NotNull String getFeatureDesc() {
    return featureDesc;
  }

  /**
   * Sets feature desc.
   *
   * @param featureDesc the feature desc
   */
  public void setFeatureDesc(@Size(max = 250) @NotNull String featureDesc) {
    this.featureDesc = featureDesc;
  }

  /**
   * Gets id.
   *
   * @return the id
   */
  public long getId() {
    return id;
  }

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Gets monroney label flg.
   *
   * @return the monroney label flg
   */
  public @Size(max = 1) Boolean getMonroneyLabelFlg() {
    return monroneyLabelFlg;
  }

  /**
   * Sets monroney label flg.
   *
   * @param monroneyLabelFlg the monroney label flg
   */
  public void setMonroneyLabelFlg(@Size(max = 1) Boolean monroneyLabelFlg) {
    this.monroneyLabelFlg = monroneyLabelFlg;
  }

  /**
   * Is remote flg boolean.
   *
   * @return the boolean
   */
  public @Size(max = 1) @NotNull boolean isRemoteFlg() {
    return remoteFlg;
  }

  /**
   * Sets remote flg.
   *
   * @param remoteFlg the remote flg
   */
  public void setRemoteFlg(@Size(max = 1) @NotNull boolean remoteFlg) {
    this.remoteFlg = remoteFlg;
  }

  /**
   * Gets tele model features.
   *
   * @return the tele model features
   */
  public Set<TeleModelFeaturesEntity> getTeleModelFeatures() {
    return teleModelFeatures;
  }

  /**
   * Sets tele model features.
   *
   * @param teleModelFeatures the tele model features
   */
  public void setTeleModelFeatures(Set<TeleModelFeaturesEntity> teleModelFeatures) {
    this.teleModelFeatures = teleModelFeatures;
  }
}
