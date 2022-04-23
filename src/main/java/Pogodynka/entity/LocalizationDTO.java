package Pogodynka.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LocalizationDTO {
    @JsonProperty("EnglishName")
    public String englishName;
    public Country country;
    public GeoPosition geoPosition;
    public AdministrativeArea administrativeArea;

    public Localization toDomain() {
        return new Localization(this.englishName,this.geoPosition.latitude,
                this.geoPosition.longtitude,this.administrativeArea.englishName,
                this.country.englishName);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Country{
        @JsonProperty("EnglishName")
        public String englishName;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class GeoPosition{
        @JsonProperty("Latitude")
        public float latitude;
        @JsonProperty("Longtitude")
        public float longtitude;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AdministrativeArea{
        @JsonProperty("EnglishName")
        public String englishName;
    }
}
