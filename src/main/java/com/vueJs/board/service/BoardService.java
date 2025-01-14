package com.vueJs.board.service;

import com.vueJs.board.entity.Post;
import com.vueJs.board.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    // 생성
    public Post createPost(Post post) {
        return boardRepository.save(post);
    }

    // 조회
    public Post getPost(Long id) {
        return boardRepository.findById(id).orElseThrow( () -> new IllegalArgumentException("포스트가 없네요!"));
    }

    // 다 조회
    public List<Post> getAllPosts() {
        return boardRepository.findAll();
    }

    // 업데이트
    public Post updatePost(Long id, Post updatedPost) {
        Post post = getPost(id);
        post.setTitle(updatedPost.getTitle());
        post.setContent(updatedPost.getContent());
        return boardRepository.save(post);
    }

    // 삭제
    public void deletePost(Long id) {
        boardRepository.deleteById(id);
    }
}
