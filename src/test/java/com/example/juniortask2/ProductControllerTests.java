package com.example.juniortask2;

import com.example.juniortask2.controller.ProductController;
import com.example.juniortask2.entity.Product;
import com.example.juniortask2.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTests {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductController productController;

    @Test
    public void testGetAllProducts() {
        // Arrange
        List<Product> products = Arrays.asList(
                new Product(1L, "Product 1", "Description 1", 10.0, 5),
                new Product(2L, "Product 2", "Description 2", 15.0, 3)
        );
        when(productRepository.findAll()).thenReturn(products);

        // Act
        List<Product> result = productController.getAllProducts();

        // Assert
        assertThat(result).isEqualTo(products);
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void testGetProductById() {
        // Arrange
        Long productId = 1L;
        Product product = new Product(productId, "Product 1", "Description 1", 10.0, 5);
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        // Act
        ResponseEntity<Product> response = productController.getProductById(productId);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(product);
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    public void testGetProductById_ProductNotFound() {
        // Arrange
        Long productId = 1L;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Product> response = productController.getProductById(productId);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNull();
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    public void testCreateProduct() {
        // Arrange
        Product product = new Product(null, "Product 1", "Description 1", 10.0, 5);
        when(productRepository.save(product)).thenReturn(product);

        // Act
        ResponseEntity<Product> response = productController.createProduct(product);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(product);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void testUpdateProduct() {
        // Arrange
        Long productId = 1L;
        Product existingProduct = new Product(productId, "Product 1", "Description 1", 10.0, 5);
        Product updatedProduct = new Product(productId, "Updated Product", "Updated Description", 15.0, 8);
        when(productRepository.existsById(productId)).thenReturn(true);
        when(productRepository.save(updatedProduct)).thenReturn(updatedProduct);

        // Act
        ResponseEntity<Product> response = productController.updateProduct(productId, updatedProduct);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(updatedProduct);
        verify(productRepository, times(1)).existsById(productId);
        verify(productRepository, times(1)).save(updatedProduct);
    }

    @Test
    public void testUpdateProduct_ProductNotFound() {
        // Arrange
        Long productId = 1L;
        Product updatedProduct = new Product(productId, "Updated Product", "Updated Description", 15.0, 8);
        when(productRepository.existsById(productId)).thenReturn(false);

        // Act
        ResponseEntity<Product> response = productController.updateProduct(productId, updatedProduct);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNull();
        verify(productRepository, times(1)).existsById(productId);
        verify(productRepository, never()).save(updatedProduct);
    }
}

