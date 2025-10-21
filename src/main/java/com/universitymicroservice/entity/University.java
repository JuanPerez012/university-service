package com.universitymicroservice.entity;

import com.universitymicroservice.enums.RoundingModeType;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(
        name = "universities",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_university_code", columnNames = "code"),
                @UniqueConstraint(name = "uk_university_name", columnNames = "name")
        }
)
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String code;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(name = "logo_url", length = 255)
    private String logoUrl;

    @Column(name = "min_score", nullable = false, precision = 4, scale = 2)
    private BigDecimal minScore;

    @Column(name = "max_score", nullable = false, precision = 5, scale = 2)
    private BigDecimal maxScore;

    @Column(name = "passing_score", nullable = false, precision = 4, scale = 2)
    private BigDecimal passingScore;

    @Enumerated(EnumType.STRING)
    @Column(name = "rounding_mode", nullable = false, length = 15)
    private RoundingModeType roundingMode;

    @Column(name = "cuts_count", nullable = false)
    private Integer cutsCount;

    @Column(name = "cut_weight_percent", nullable = false, precision = 5, scale = 2)
    private BigDecimal cutWeightPercent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public BigDecimal getMinScore() {
        return minScore;
    }

    public void setMinScore(BigDecimal minScore) {
        this.minScore = minScore;
    }

    public BigDecimal getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(BigDecimal maxScore) {
        this.maxScore = maxScore;
    }

    public BigDecimal getPassingScore() {
        return passingScore;
    }

    public void setPassingScore(BigDecimal passingScore) {
        this.passingScore = passingScore;
    }

    public RoundingModeType getRoundingMode() {
        return roundingMode;
    }

    public void setRoundingMode(RoundingModeType roundingMode) {
        this.roundingMode = roundingMode;
    }

    public Integer getCutsCount() {
        return cutsCount;
    }

    public void setCutsCount(Integer cutsCount) {
        this.cutsCount = cutsCount;
    }

    public BigDecimal getCutWeightPercent() {
        return cutWeightPercent;
    }

    public void setCutWeightPercent(BigDecimal cutWeightPercent) {
        this.cutWeightPercent = cutWeightPercent;
    }
}
