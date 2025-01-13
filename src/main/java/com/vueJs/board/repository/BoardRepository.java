package com.vueJs.board.repository;

import com.vueJs.board.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Post, Long> {
}
