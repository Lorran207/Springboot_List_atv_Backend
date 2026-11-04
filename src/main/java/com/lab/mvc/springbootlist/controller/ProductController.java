package com.lab.mvc.springbootlist.controller;

import com.lab.mvc.springbootlist.dto.ProductRequestDTO;
import com.lab.mvc.springbootlist.dto.ProductResponseDTO;
import com.lab.mvc.springbootlist.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public String listProducts(Model model) {
        List<ProductResponseDTO> products = service.listAll();
        model.addAttribute("listProducts", products);
        return "index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new ProductRequestDTO(null, "", "", "", null, 0));
        model.addAttribute("isEdit", false);
        return "new_product";
    }

    @PostMapping("/save")
    public String saveProduct(
            @Valid @ModelAttribute("product") ProductRequestDTO product,
            BindingResult result,
            RedirectAttributes attr) {

        if (result.hasErrors()) {
            return (product.id() == null) ? "new_product" : "edit_product";
        }

        service.save(product);
        attr.addFlashAttribute("success", "Registro salvo com sucesso!");
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditProductPage(@PathVariable("id") Long id, Model model) {
        ProductResponseDTO response = service.get(id);

        ProductRequestDTO product = new ProductRequestDTO(
                response.id(),
                response.nome(),
                response.marca(),
                response.paisFabricacao(),
                response.preco(),
                response.estoque()
        );

        model.addAttribute("product", product);
        return "edit_product";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id, RedirectAttributes attr) {
        service.delete(id);
        attr.addFlashAttribute("success", "Registro excluído com sucesso!");
        return "redirect:/products";
    }
}
