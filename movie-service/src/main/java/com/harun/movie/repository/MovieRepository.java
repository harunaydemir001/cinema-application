package com.harun.movie.repository;

import com.harun.movie.dto.MovieDTO;
import com.harun.movie.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.util.ObjectUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public interface MovieRepository extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {

    default Page<Movie> findByFilter(Pageable pageable, MovieDTO movieDTO) {
        return findAll(specification(movieDTO), pageable);
    }

    default Specification<Movie> specification(MovieDTO movieDTO) {
        return (Root<Movie> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            Predicate mainPredicate = criteriaBuilder.conjunction();

            if (!ObjectUtils.isEmpty(movieDTO.getId())) {
                Predicate predicate = criteriaBuilder.equal(root.get("id"), movieDTO.getId());
                mainPredicate = criteriaBuilder.and(mainPredicate, predicate);
            }

            if (!ObjectUtils.isEmpty(movieDTO.getName())) {
                Predicate predicate = criteriaBuilder.equal(root.get("name"), movieDTO.getName());
                mainPredicate = criteriaBuilder.and(mainPredicate, predicate);
            }

            if (!ObjectUtils.isEmpty(movieDTO.getReleaseDate())) {
                Predicate predicate = criteriaBuilder.equal(root.get("release_date"), movieDTO.getReleaseDate());
                mainPredicate = criteriaBuilder.and(mainPredicate, predicate);
            }

            if (!ObjectUtils.isEmpty(movieDTO.getLanguage())) {
                Predicate predicate = criteriaBuilder.equal(root.get("language"), movieDTO.getLanguage());
                mainPredicate = criteriaBuilder.and(mainPredicate, predicate);
            }

            if (!ObjectUtils.isEmpty(movieDTO.getGenre())) {
                Predicate predicate = criteriaBuilder.equal(root.get("genre"), movieDTO.getGenre());
                mainPredicate = criteriaBuilder.and(mainPredicate, predicate);
            }

            if (!ObjectUtils.isEmpty(movieDTO.getQuality())) {
                Predicate predicate = criteriaBuilder.equal(root.get("quality"), movieDTO.getQuality());
                mainPredicate = criteriaBuilder.and(mainPredicate, predicate);
            }

            if (!ObjectUtils.isEmpty(movieDTO.getIsOscar())) {
                Predicate predicate = criteriaBuilder.equal(root.get("is_oscar"), movieDTO.getIsOscar());
                mainPredicate = criteriaBuilder.and(mainPredicate, predicate);
            }

            if (!ObjectUtils.isEmpty(movieDTO.getLength())) {
                Predicate predicate = criteriaBuilder.equal(root.get("length"), movieDTO.getLength());
                mainPredicate = criteriaBuilder.and(mainPredicate, predicate);
            }

            if (!ObjectUtils.isEmpty(movieDTO.getImdbPoint())) {
                Predicate predicate = criteriaBuilder.equal(root.get("imdb_point"), movieDTO.getImdbPoint());
                mainPredicate = criteriaBuilder.and(mainPredicate, predicate);
            }


            if (!ObjectUtils.isEmpty(movieDTO.getSpecialFeatures())) {
                Predicate predicate = criteriaBuilder.equal(root.get("special_features"), movieDTO.getSpecialFeatures());
                mainPredicate = criteriaBuilder.and(mainPredicate, predicate);
            }

            if (!ObjectUtils.isEmpty(movieDTO.getLastUpdate())) {
                Predicate predicate = criteriaBuilder.equal(root.get("last_update"), movieDTO.getLastUpdate());
                mainPredicate = criteriaBuilder.and(mainPredicate, predicate);
            }

            if (!ObjectUtils.isEmpty(movieDTO.getStatus())) {
                Predicate predicate = criteriaBuilder.equal(root.get("status"), movieDTO.getStatus());
                mainPredicate = criteriaBuilder.and(mainPredicate, predicate);
            }

            return mainPredicate;

        };
    }
}
