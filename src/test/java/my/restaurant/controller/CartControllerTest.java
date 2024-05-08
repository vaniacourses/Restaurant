package my.restaurant.controller;

import my.restaurant.dto.ProductDTO;
import my.restaurant.modal.ShoppingCart;
import my.restaurant.modal.forms.ProductCartForm;
import my.restaurant.service.CartService;
import my.restaurant.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CartControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private CartService cartService;

    @InjectMocks
    private CartController cartController;

    @SuppressWarnings("deprecation")
	@BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRemoveFromCart() {
        RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
        UUID productId = UUID.randomUUID();
        ProductDTO productDTO = mock(ProductDTO.class);
        when(productService.getProduct(productId)).thenReturn(productDTO);

        String result = cartController.removeFromCart(productId, redirectAttributes);

        assertEquals("redirect:/cart", result);
        verify(cartService).removeItem(productDTO);
        verify(redirectAttributes).addFlashAttribute(anyString(), anyString());
    }
}



