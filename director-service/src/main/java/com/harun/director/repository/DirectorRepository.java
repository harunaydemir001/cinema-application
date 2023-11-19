package com.harun.director.repository;

import com.harun.director.dto.DirectorDTO;
import com.harun.director.model.Director;
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

public interface DirectorRepository extends JpaRepository<Director, Long>, JpaSpecificationExecutor<Director> {
    default Page<Director> findByFilter(Pageable pageable, DirectorDTO directorDTO){
        return findAll(specification(directorDTO), pageable);
    }

    default Specification<Director> specification(DirectorDTO directorDTO) {
        return (Root<Director> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            Predicate mainPredicate = criteriaBuilder.conjunction();

            if (!ObjectUtils.isEmpty(directorDTO.getId())) {
                Predicate predicate = criteriaBuilder.equal(root.get("id"), directorDTO.getId());
                mainPredicate = criteriaBuilder.and(mainPredicate, predicate);
            }

            if (!ObjectUtils.isEmpty(directorDTO.getName())) {
                Predicate predicate = criteriaBuilder.equal(root.get("name"), directorDTO.getName());
                mainPredicate = criteriaBuilder.and(mainPredicate, predicate);
            }

            if (!ObjectUtils.isEmpty(directorDTO.getSurname())) {
                Predicate predicate = criteriaBuilder.equal(root.get("surname"), directorDTO.getSurname());
                mainPredicate = criteriaBuilder.and(mainPredicate, predicate);
            }

            if (!ObjectUtils.isEmpty(directorDTO.getStatus())) {
                Predicate predicate = criteriaBuilder.equal(root.get("status"), directorDTO.getStatus());
                mainPredicate = criteriaBuilder.and(mainPredicate, predicate);
            }

            if (!ObjectUtils.isEmpty(directorDTO.getLastUpdate())) {
                Predicate predicate = criteriaBuilder.equal(root.get("last_update"), directorDTO.getLastUpdate());
                mainPredicate = criteriaBuilder.and(mainPredicate, predicate);
            }

            if (!ObjectUtils.isEmpty(directorDTO.getAge())) {
                Predicate predicate = criteriaBuilder.equal(root.get("age"), directorDTO.getAge());
                mainPredicate = criteriaBuilder.and(mainPredicate, predicate);
            }

            if (!ObjectUtils.isEmpty(directorDTO.getPrize())) {
                Predicate predicate = criteriaBuilder.equal(root.get("prize"), directorDTO.getPrize());
                mainPredicate = criteriaBuilder.and(mainPredicate, predicate);
            }

            if (!ObjectUtils.isEmpty(directorDTO.getBirthDay())) {
                Predicate predicate = criteriaBuilder.equal(root.get("birth_day"), directorDTO.getBirthDay());
                mainPredicate = criteriaBuilder.and(mainPredicate, predicate);
            }

            return mainPredicate;

        };
    }
}
