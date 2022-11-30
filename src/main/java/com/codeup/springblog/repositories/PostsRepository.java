package com.codeup.springblog.repositories;

import com.codeup.weywotspringblog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Post, Long> {
    Post findById(long id);
}