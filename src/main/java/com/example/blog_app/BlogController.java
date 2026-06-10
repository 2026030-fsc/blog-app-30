package com.example.blog_app;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String newPost(@ModelAttribute BlogForm blogForm) {
        return "blogs/newPost";
    }

    // 詳細ページへの移動
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
    public String create(@ModelAttribute BlogForm form, Model model) {
        model.addAttribute("title", form.getTitle());
        model.addAttribute("texts", form.getTexts());
        blogService.save(form);
        return "redirect:/blogs/registered"; //GetMapping
    }

    @GetMapping("/blogs/registered")
    public String registerd() {
        return "blogs/registerd"; // ファイルを返す
    }
}