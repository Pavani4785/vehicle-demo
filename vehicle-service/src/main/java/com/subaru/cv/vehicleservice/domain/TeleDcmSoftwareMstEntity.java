package com.subaru.cv.vehicleservice.domain;

import com.subaru.cv.cafe.jpa.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * The type Tele dcm software mst entity.
 */
@Entity
@Table(name = "TELE_DCM_SOFTWARE_MST")
@SuppressWarnings("unused")
public class TeleDcmSoftwareMstEntity extends AuditableEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "DCM_SOFTWARE_ID", nullable = false)
  private long id;

  @Size(max = 25)
  @NotNull
  @Column(name = "DCM_SOFTWARE_VERSION", nullable = false, length = 25)
  private String dcmSoftwareVersion;

  @Size(max = 25)
  @Column(name = "DCM_SOFTWARE_SHORT_VERSION", length = 25)
  private String dcmSoftwareShortVersion;

  @Size(max = 25)
  @Column(name = "CONTI_R_NBR", length = 25)
  private String contiRNbr;

  @Size(max = 4000)
  @Column(name = "OFFICIAL_LOADS_INFO", length = 4000)
  private String officialLoadsInfo;

  @Size(max = 50)
  @Column(name = "RELEASE_NOTE", length = 50)
  private String releaseNote;

  @Column(name = "RELEASE_DATE")
  private LocalDate releaseDate;

  @NotNull
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @OnDelete(action = OnDeleteAction.RESTRICT)
  @JoinColumn(name = "DCM_GEN_ID", nullable = false)
  private TeleDcmGenMstEntity dcmGenMaster;

  @OneToMany(mappedBy = "dcmSoftwareMaster")
  private Set<TeleDcmMstEntity> teleDcmMsts = new LinkedHashSet<>();

  /**
   * Gets conti r nbr.
   *
   * @return the conti r nbr
   */
  public @Size(max = 25) String getContiRNbr() {
    return contiRNbr;
  }

  /**
   * Sets conti r nbr.
   *
   * @param contiRNbr the conti r nbr
   */
  public void setContiRNbr(@Size(max = 25) String contiRNbr) {
    this.contiRNbr = contiRNbr;
  }

  /**
   * Gets dcm gen master.
   *
   * @return the dcm gen master
   */
  public @NotNull TeleDcmGenMstEntity getDcmGenMaster() {
    return dcmGenMaster;
  }

  /**
   * Sets dcm gen master.
   *
   * @param dcmGenMaster the dcm gen master
   */
  public void setDcmGenMaster(@NotNull TeleDcmGenMstEntity dcmGenMaster) {
    this.dcmGenMaster = dcmGenMaster;
  }

  /**
   * Gets dcm software short version.
   *
   * @return the dcm software short version
   */
  public @Size(max = 25) String getDcmSoftwareShortVersion() {
    return dcmSoftwareShortVersion;
  }

  /**
   * Sets dcm software short version.
   *
   * @param dcmSoftwareShortVersion the dcm software short version
   */
  public void setDcmSoftwareShortVersion(@Size(max = 25) String dcmSoftwareShortVersion) {
    this.dcmSoftwareShortVersion = dcmSoftwareShortVersion;
  }

  /**
   * Gets dcm software version.
   *
   * @return the dcm software version
   */
  public @Size(max = 25) @NotNull String getDcmSoftwareVersion() {
    return dcmSoftwareVersion;
  }

  /**
   * Sets dcm software version.
   *
   * @param dcmSoftwareVersion the dcm software version
   */
  public void setDcmSoftwareVersion(@Size(max = 25) @NotNull String dcmSoftwareVersion) {
    this.dcmSoftwareVersion = dcmSoftwareVersion;
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
   * Gets official loads info.
   *
   * @return the official loads info
   */
  public @Size(max = 4000) String getOfficialLoadsInfo() {
    return officialLoadsInfo;
  }

  /**
   * Sets official loads info.
   *
   * @param officialLoadsInfo the official loads info
   */
  public void setOfficialLoadsInfo(@Size(max = 4000) String officialLoadsInfo) {
    this.officialLoadsInfo = officialLoadsInfo;
  }

  /**
   * Gets release date.
   *
   * @return the release date
   */
  public LocalDate getReleaseDate() {
    return releaseDate;
  }

  /**
   * Sets release date.
   *
   * @param releaseDate the release date
   */
  public void setReleaseDate(LocalDate releaseDate) {
    this.releaseDate = releaseDate;
  }

  /**
   * Gets release note.
   *
   * @return the release note
   */
  public @Size(max = 50) String getReleaseNote() {
    return releaseNote;
  }

  /**
   * Sets release note.
   *
   * @param releaseNote the release note
   */
  public void setReleaseNote(@Size(max = 50) String releaseNote) {
    this.releaseNote = releaseNote;
  }

  /**
   * Gets tele dcm msts.
   *
   * @return the tele dcm msts
   */
  public Set<TeleDcmMstEntity> getTeleDcmMsts() {
    return teleDcmMsts;
  }

  /**
   * Sets tele dcm msts.
   *
   * @param teleDcmMsts the tele dcm msts
   */
  public void setTeleDcmMsts(Set<TeleDcmMstEntity> teleDcmMsts) {
    this.teleDcmMsts = teleDcmMsts;
  }
}
