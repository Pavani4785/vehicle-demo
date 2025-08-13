package com.subaru.cv.vehicleservice.domain;

import com.subaru.cv.cafe.jpa.AuditableEntity;
import jakarta.persistence.Column;
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

/**
 * The type Tele carline mst entity.
 */
@Entity
@Table(name = "TELE_CARLINE_MST")
@SuppressWarnings("unused")
public class TeleCarlineMstEntity extends AuditableEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "CARLINE_ID", nullable = false)
  private long id;

  @Size(max = 250)
  @NotNull
  @Column(name = "CARLINE", nullable = false, length = 250)
  private String carline;

  @Size(max = 250)
  @Column(name = "CARLINE_DESC", length = 250)
  private String carlineDesc;

  @OneToMany(mappedBy = "carlineMaster")
  private Set<TeleLocalModelMstEntity> teleLocalModelMsts = new LinkedHashSet<>();

  /**
   * Gets carline.
   *
   * @return the carline
   */
  public @Size(max = 250) @NotNull String getCarline() {
    return carline;
  }

  /**
   * Sets carline.
   *
   * @param carline the carline
   */
  public void setCarline(@Size(max = 250) @NotNull String carline) {
    this.carline = carline;
  }

  /**
   * Gets carline desc.
   *
   * @return the carline desc
   */
  public @Size(max = 250) String getCarlineDesc() {
    return carlineDesc;
  }

  /**
   * Sets carline desc.
   *
   * @param carlineDesc the carline desc
   */
  public void setCarlineDesc(@Size(max = 250) String carlineDesc) {
    this.carlineDesc = carlineDesc;
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
   * Gets tele local model msts.
   *
   * @return the tele local model msts
   */
  public Set<TeleLocalModelMstEntity> getTeleLocalModelMsts() {
    return teleLocalModelMsts;
  }

  /**
   * Sets tele local model msts.
   *
   * @param teleLocalModelMsts the tele local model msts
   */
  public void setTeleLocalModelMsts(Set<TeleLocalModelMstEntity> teleLocalModelMsts) {
    this.teleLocalModelMsts = teleLocalModelMsts;
  }
}
