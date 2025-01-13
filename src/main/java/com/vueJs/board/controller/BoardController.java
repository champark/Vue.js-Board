package com.vueJs.board.controller;

import com.vueJs.board.entity.Post;
import com.vueJs.board.service.BoardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "http://localhost:3000")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return boardService.getAllPosts();
    }

    @PostMapping
    public Post createPost(@RequestBody Post post) {
        return boardService.createPost(post);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        boardService.deletePost(id);
    }
}
