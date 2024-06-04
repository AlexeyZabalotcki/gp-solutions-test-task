package com.gpsolutions.util;

import com.gpsolutions.dto.Params;
import com.gpsolutions.model.Amenity;
import com.gpsolutions.model.Hotel;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;
import java.util.stream.Stream;

public class CustomSpecification {

    public static Specification<Hotel> searchWithParams(Params params) {
        return (root, query, criteriaBuilder) -> {
            Predicate namePredicate = Optional.ofNullable(params.getName())
                    .filter(name -> !name.isEmpty())
                    .map(name -> criteriaBuilder.like(root.get("name"), "%" + name + "%"))
                    .orElse(null);

            Predicate brandPredicate = Optional.ofNullable(params.getBrand())
                    .filter(brand -> !brand.isEmpty())
                    .map(brand -> criteriaBuilder.equal(root.get("brand"), brand))
                    .orElse(null);

            Predicate cityPredicate = Optional.ofNullable(params.getCity())
                    .filter(city -> !city.isEmpty())
                    .map(city -> criteriaBuilder.equal(root.get("address").get("city"), city))
                    .orElse(null);

            Predicate countyPredicate = Optional.ofNullable(params.getCountry())
                    .filter(county -> !county.isEmpty())
                    .map(country -> criteriaBuilder.equal(root.get("address").get("country"), country))
                    .orElse(null);

            Predicate amenityPredicate = Optional.ofNullable(params.getAmenity())
                    .filter(amenity -> !amenity.isEmpty())
                    .map(amenity -> {
                        Join<Hotel, Amenity> amenitiesJoin = root.join("amenities");
                        return criteriaBuilder.equal(amenitiesJoin.get("name"), amenity);
                    })
                    .orElse(null);

            return criteriaBuilder.and(Stream.of(namePredicate, brandPredicate, cityPredicate, countyPredicate, amenityPredicate)
                    .filter(predicate -> predicate != null)
                    .toArray(Predicate[]::new));
        };
    }
}
