package com.example.blog_app;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class BlogController {
    private final BlogService blogService;
    private final BlogRepository blogRepository;

    public BlogController(BlogService blogService, BlogRepository blogRepository) {
        this.blogService = blogService;
        this.blogRepository = blogRepository;
    }

    @GetMapping("/blogs")
    public String blogs(Model model) {
        List<Blog> blogs = blogRepository.findAll();
        model.addAttribute("blogs", blogs);
        return "blogs";
    }

    @GetMapping("/blogs/newPost")
    public String newPost() {
        return "blogs/newPost";
    }

    @GetMapping("/blogs/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Optional<Blog> blogOpt = blogService.findById(id);
        if (blogOpt.isEmpty()) {
            return "redirect:/blogs";
        }
        model.addAttribute("blogs", blogOpt.get());
        return "blogs/detail";
    }

    @PostMapping("/blogs")
    public String create(@RequestParam String title, String texts, Model model) {
        model.addAttribute("title", title);
        model.addAttribute("texts", texts);
        return "registered";
    }
    
}