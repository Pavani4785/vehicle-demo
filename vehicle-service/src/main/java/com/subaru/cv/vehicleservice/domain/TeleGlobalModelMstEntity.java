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
 * The type Tele global model mst entity.
 */
@Entity
@Table(name = "TELE_GLOBAL_MODEL_MST")
@SuppressWarnings("unused")
public class TeleGlobalModelMstEntity extends AuditableEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "GLOBAL_MODEL_ID", nullable = false)
  private long id;

  @Size(max = 15)
  @NotNull
  @Column(name = "GLOBAL_MODEL_CODE", nullable = false, length = 15)
  private String globalModelCode;

  @Size(max = 15)
  @Column(name = "GLOBAL_OPTION_CODE", length = 15)
  private String globalOptionCode;

  @Size(max = 15)
  @NotNull
  @Column(name = "GLOBAL_SPEC_CODE", nullable = false, length = 15)
  private String globalSpecCode;

  @Size(max = 15)
  @NotNull
  @Column(name = "GLOBAL_EXTERIOR_COLOR", nullable = false, length = 15)
  private String globalExteriorColor;

  @Size(max = 15)
  @NotNull
  @Column(name = "GLOBAL_INTERIOR_COLOR", nullable = false, length = 15)
  private String globalInteriorColor;

  @OneToMany(mappedBy = "globalModel")
  private Set<TeleVinMasterEntity> teleVinMasters = new LinkedHashSet<>();

  /**
   * Gets global exterior color.
   *
   * @return the global exterior color
   */
  public @Size(max = 15) @NotNull String getGlobalExteriorColor() {
    return globalExteriorColor;
  }

  /**
   * Sets global exterior color.
   *
   * @param globalExteriorColor the global exterior color
   */
  public void setGlobalExteriorColor(@Size(max = 15) @NotNull String globalExteriorColor) {
    this.globalExteriorColor = globalExteriorColor;
  }

  /**
   * Gets global interior color.
   *
   * @return the global interior color
   */
  public @Size(max = 15) @NotNull String getGlobalInteriorColor() {
    return globalInteriorColor;
  }

  /**
   * Sets global interior color.
   *
   * @param globalInteriorColor the global interior color
   */
  public void setGlobalInteriorColor(@Size(max = 15) @NotNull String globalInteriorColor) {
    this.globalInteriorColor = globalInteriorColor;
  }

  /**
   * Gets global model code.
   *
   * @return the global model code
   */
  public @Size(max = 15) @NotNull String getGlobalModelCode() {
    return globalModelCode;
  }

  /**
   * Sets global model code.
   *
   * @param globalModelCode the global model code
   */
  public void setGlobalModelCode(@Size(max = 15) @NotNull String globalModelCode) {
    this.globalModelCode = globalModelCode;
  }

  /**
   * Gets global option code.
   *
   * @return the global option code
   */
  public @Size(max = 15) String getGlobalOptionCode() {
    return globalOptionCode;
  }

  /**
   * Sets global option code.
   *
   * @param globalOptionCode the global option code
   */
  public void setGlobalOptionCode(@Size(max = 15) String globalOptionCode) {
    this.globalOptionCode = globalOptionCode;
  }

  /**
   * Gets global spec code.
   *
   * @return the global spec code
   */
  public @Size(max = 15) @NotNull String getGlobalSpecCode() {
    return globalSpecCode;
  }

  /**
   * Sets global spec code.
   *
   * @param globalSpecCode the global spec code
   */
  public void setGlobalSpecCode(@Size(max = 15) @NotNull String globalSpecCode) {
    this.globalSpecCode = globalSpecCode;
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
   * Gets tele vin masters.
   *
   * @return the tele vin masters
   */
  public Set<TeleVinMasterEntity> getTeleVinMasters() {
    return teleVinMasters;
  }

  /**
   * Sets tele vin masters.
   *
   * @param teleVinMasters the tele vin masters
   */
  public void setTeleVinMasters(Set<TeleVinMasterEntity> teleVinMasters) {
    this.teleVinMasters = teleVinMasters;
  }
}
