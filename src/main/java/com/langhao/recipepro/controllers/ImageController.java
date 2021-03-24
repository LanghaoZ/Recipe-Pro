package com.langhao.recipepro.controllers;

import com.langhao.recipepro.dto.RecipeDto;
import com.langhao.recipepro.services.ImageService;
import com.langhao.recipepro.services.RecipeService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class ImageController {

    private final ImageService imageService;
    private final RecipeService recipeService;

    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("recipe/{id}/image")
    public String showUploadForm(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findDtoById(Long.valueOf(id)));

        return "recipe/imageUploadForm";
    }

    @GetMapping("recipe/{id}/recipeImage")
    public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) throws IOException {
        RecipeDto recipeDto = recipeService.findDtoById(Long.valueOf(id));
        if (recipeDto != null && recipeDto.getImage() != null) {
            byte[] byteArray = new byte[recipeDto.getImage().length];
            int i = 0;

            for (Byte wrappedByte : recipeDto.getImage()) {
                byteArray[i++] = wrappedByte;
            }

            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
        }
    }

    @PostMapping("recipe/{id}/image")
    public String handleImagePost(@PathVariable String id, @RequestParam("imagefile") MultipartFile image) {
        imageService.saveImage(Long.valueOf(id), image);

        return "redirect:/recipe/show/" + id;
    }
}
