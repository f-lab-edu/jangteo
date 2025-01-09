package com.flab.jangteoapi.item.controller;

import com.flab.jangteoapi.item.dto.ItemSaveRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/items")
public class ItemController {

    @PostMapping
    public ResponseEntity registerItem(@RequestBody @Valid ItemSaveRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity updateItem(@RequestBody @Valid ItemSaveRequest request) {

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity deleteItem(@PathVariable UUID itemId) {

        return ResponseEntity.ok().build();
    }
}
