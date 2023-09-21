package com.web.exampleshinwoo.repository;

import com.web.exampleshinwoo.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Long>, BoardRepositoryCustom {

}
